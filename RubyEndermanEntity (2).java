package net.tutorial.rubymod.entity.custom;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class RubyEndermanEntity extends EnderMan {

    private ServerPlayer grabbedPlayer;
    private int grabTimer = 0;
    private int grabCooldown = 0;

    public RubyEndermanEntity(EntityType<? extends EnderMan> type, Level level) {
        super(type, level);
    }

    @Override
    public void tick() {
        super.tick();

        if (grabCooldown > 0) grabCooldown--;

        if (!this.level().isClientSide) {

            if (grabbedPlayer != null) {
                grabTimer++;

                // 🧠 悬空控制（抓取状态）
                grabbedPlayer.setNoGravity(true);
                grabbedPlayer.setDeltaMovement(0, 0.05, 0);

                // 💀 到时间抛飞
                if (grabTimer > 40) {
                    throwPlayer();
                }
            }
        }
    }

    private void throwPlayer() {

        if (!(grabbedPlayer instanceof ServerPlayer player)) return;

        player.setNoGravity(false);
        player.stopRiding();

        // 🌪 末影人方向
        Vec3 look = this.getLookAngle().normalize();

        double strength = 1.8;

        player.setDeltaMovement(
                look.x * strength,
                1.1,
                look.z * strength
        );

        player.hurtMarked = true;

        // 💥 爆炸粒子
        ((ServerLevel)this.level()).sendParticles(
                ParticleTypes.EXPLOSION,
                player.getX(),
                player.getY(),
                player.getZ(),
                2,
                0.2, 0.2, 0.2,
                0.0
        );

        ((ServerLevel)this.level()).sendParticles(
                ParticleTypes.POOF,
                player.getX(),
                player.getY(),
                player.getZ(),
                20,
                0.5, 0.5, 0.5,
                0.05
        );

        // 🔊 音效
        this.level().playSound(
                null,
                player.blockPosition(),
                SoundEvents.GENERIC_EXPLODE,
                SoundSource.HOSTILE,
                1.0F,
                1.0F
        );

        // 🧹 清理状态
        grabbedPlayer = null;
        grabTimer = 0;
        grabCooldown = 80;
    }

    // 👉 你原本抓人逻辑应该在这里（示例）
    public void grab(ServerPlayer player) {
        if (grabCooldown > 0 || grabbedPlayer != null) return;

        this.grabbedPlayer = player;
        this.grabTimer = 0;

        player.setNoGravity(true);
        player.setDeltaMovement(0, 0, 0);
    }
}
