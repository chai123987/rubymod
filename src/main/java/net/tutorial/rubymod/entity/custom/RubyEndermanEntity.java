package net.tutorial.rubymod.entity.custom;
 
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
 
public class RubyEndermanEntity extends EnderMan {
 
    // 抱起技能：冷却计时器（tick数）
    private int grabCooldown = 0;
    // 被抱起的目标
    private Player grabbedPlayer = null;
    private int grabTimer = 0;
 
    public RubyEndermanEntity(EntityType<? extends EnderMan> type, Level level) {
        super(type, level);
    }
 
    public static AttributeSupplier.Builder createAttributes() {
        return EnderMan.createAttributes()
                .add(Attributes.MAX_HEALTH, 60.0D)          // 血量 60（原版40）
                .add(Attributes.ATTACK_DAMAGE, 12.0D)        // 伤害 12（原版7）
                .add(Attributes.MOVEMENT_SPEED, 0.35D)       // 移速更快
                .add(Attributes.FOLLOW_RANGE, 64.0D);        // 追踪范围更远
    }
 
    @Override
    protected void registerGoals() {
        // 保留原版末影人的目标选择，但不继承全部 goal（防止 super 重复加）
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 0.6D));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }
 
    // ========== 不怕水 ==========
    @Override
    public boolean isSensitiveToWater() {
        return false;   // 红宝石末影人免疫水
    }
 
    // ========== 传送速度更快：覆盖传送冷却 ==========
    @Override
    protected boolean teleport() {
        if (!this.level().isClientSide() && this.isAlive()) {
            double x = this.getX() + (this.getRandom().nextDouble() - 0.5D) * 64.0D;
            double y = this.getY() + (this.getRandom().nextInt(64) - 32);
            double z = this.getZ() + (this.getRandom().nextDouble() - 0.5D) * 64.0D;
            return this.randomTeleport(x, y, z, true);
        }
        return false;
    }
 
    // ========== "抱起扔天上"技能 ==========
    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide()) return;
 
        // 冷却递减
        if (grabCooldown > 0) grabCooldown--;
 
        // 如果正在抓着玩家，持续把玩家抬到自己上方
        if (grabbedPlayer != null) {
            grabTimer--;
            if (grabTimer > 0 && grabbedPlayer.isAlive()) {
                // 把玩家位置锁定到末影人头顶一点点
                grabbedPlayer.setPos(this.getX(), this.getY() + 2.5, this.getZ());
                grabbedPlayer.setDeltaMovement(Vec3.ZERO);
            } else {
                // 松手：给玩家一个猛烈的向上速度（扔上天）
                if (grabbedPlayer.isAlive()) {
                    grabbedPlayer.setDeltaMovement(
                            (this.getRandom().nextDouble()-0.5)*0.5,
                            3.5D,   // 向上速度，足够飞很高
                            (this.getRandom().nextDouble()-0.5)*0.5
                    );
                    // 提示玩家
                    grabbedPlayer.displayClientMessage(
                            Component.literal("§c红宝石末影人把你扔上了天空！"), true);
                }
                grabbedPlayer = null;
                grabTimer = 0;
                grabCooldown = 200; // 冷却 10 秒
            }
            return;
        }
 
        // 没在抓人时：检测附近玩家，触发抓取
        if (grabCooldown == 0 && this.getTarget() != null) {
            LivingEntity target = this.getTarget();
            if (target instanceof Player player && !player.isCreative() && !player.isSpectator()) {
                double dist = this.distanceTo(player);
                // 距离 5 格以内、自己血量够（大于 20）才触发
                if (dist < 5.0D && this.getHealth() > 20.0D) {
                    // 传送到玩家身旁
                    this.teleportTowards(player);
                    // 开始抓取
                    grabbedPlayer = player;
                    grabTimer = 40;  // 抓着 2 秒（40 tick）再扔
                    this.playSound(SoundEvents.ENDERMAN_SCREAM, 1.0F, 0.8F);
                    player.displayClientMessage(
                            Component.literal("§c红宝石末影人抓住了你！"), true);
                    grabCooldown = 1; // 占位，防止下帧重复触发
                }
            }
        }
    }
 
    // 传送到目标附近（技能用）
    private void teleportTowards(LivingEntity target) {
        Vec3 pos = target.position();
        this.randomTeleport(pos.x, pos.y, pos.z, true);
    }
 
    // ========== NBT 存读（存冷却，避免重载后立刻触发）==========
    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("GrabCooldown", grabCooldown);
    }
 
    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        grabCooldown = tag.getInt("GrabCooldown");
    }
}
 
