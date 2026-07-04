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

    @Override
    public boolean isSensitiveToWater() {
        return false;
    }

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

    private void startGrabPose() {
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

        if (grabbedPlayer != null) {
            grabTimer--;
            if (grabTimer > 0 && grabbedPlayer.isAlive()) {
                grabbedPlayer.setPos(this.getX(), this.getY() + 2.2, this.getZ());
                grabbedPlayer.setDeltaMovement(Vec3.ZERO);
                grabbedPlayer.fallDistance = 0.0F;
            } else {
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
                grabCooldown = 200;
            }
            return;
        }

        if (grabCooldown == 0 && this.getTarget() instanceof Player player
