package net.tutorial.rubymod.entity.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
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
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;

public class RubyEndermanEntity extends EnderMan {

    // 同步到客户端：是否正在抓人（用于触发拿方块动画）
    private static final EntityDataAccessor<Boolean> GRABBING =
            SynchedEntityData.defineId(RubyEndermanEntity.class, EntityDataSerializers.BOOLEAN);

    private int grabCooldown = 0;
    private Player grabbedPlayer = null;
    private int grabTimer = 0;

    public RubyEndermanEntity(EntityType<? extends EnderMan> type, Level level) {
        super(type, level);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(GRABBING, false);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return EnderMan.createAttributes()
                .add(Attributes.MAX_HEALTH, 60.0D)
                .add(Attributes.ATTACK_DAMAGE, 12.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.35D)
                .add(Attributes.FOLLOW_RANGE, 64.0D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 0.6D));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    // 不怕水
    @Override
    public boolean isSensitiveToWater() {
        return false;
    }

    // 传送更快
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

    // 抱起动作：让末影人"拿着"一个方块（触发原版拿方块的姿势）
    private void startGrabPose() {
        // 设置末影人持有方块状态 → 触发原版"抱着方块"的手臂动画
        this.setCarriedBlock(Blocks.DIRT.defaultBlockState());
        this.entityData.set(GRABBING, true);
    }

    private void stopGrabPose() {
        this.setCarriedBlock(null);
        this.entityData.set(GRABBING, false);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide()) return;

        if (grabCooldown > 0) grabCooldown--;

        // 正在抓着玩家
        if (grabbedPlayer != null) {
            grabTimer--;
            if (grabTimer > 0 && grabbedPlayer.isAlive()) {
                // 把玩家锁在末影人头顶
                grabbedPlayer.setPos(this.getX(), this.getY() + 2.2, this.getZ());
                grabbedPlayer.setDeltaMovement(Vec3.ZERO);
                grabbedPlayer.fallDistance = 0.0F; // 防止抓着时积累坠落伤害
            } else {
                // 松手，扔上天
                stopGrabPose();
                if (grabbedPlayer.isAlive()) {
                    grabbedPlayer.setDeltaMovement(
                            (this.getRandom().nextDouble() - 0.5) * 0.4,
                            3.8D,
                            (this.getRandom().nextDouble() - 0.5) * 0.4
                    );
                    grabbedPlayer.displayClientMessage(
                            Component.literal("§c红宝石末影人把你扔上了天空！"), true);
                }
                grabbedPlayer = null;
                grabTimer = 0;
                grabCooldown = 200; // 冷却 10 秒
            }
            return;
        }

        // 检测触发抓取：距离 4 格以内、冷却结束、有目标
        if (grabCooldown == 0 && this.getTarget() instanceof Player player
                && !player.isCreative() && !player.isSpectator()
                && this.distanceTo(player) < 4.0D
                && this.getHealth() > 20.0D) {

            this.randomTeleport(player.getX(), player.getY(), player.getZ(), true);
            grabbedPlayer = player;
            grabTimer = 50; // 抓着 2.5 秒
            grabCooldown = 201; // 立刻锁住冷却（>0），tick 开始时先 -1 变 200
            startGrabPose();
            this.playSound(SoundEvents.ENDERMAN_SCREAM, 1.0F, 0.8F);
            player.displayClientMessage(
                    Component.literal("§c红宝石末影人抓住了你！"), true);
        }
    }

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
