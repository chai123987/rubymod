if (!this.level().isClientSide && this.grabbedPlayer != null && this.grabTimer > 40) {

    ServerPlayer player = (ServerPlayer) this.grabbedPlayer;

    player.setNoGravity(false);

    double strength = 1.6;

    Vec3 look = this.getLookAngle().normalize();

    double dx = look.x * strength;
    double dy = 1.1;
    double dz = look.z * strength;

    player.setDeltaMovement(dx, dy, dz);
    player.hurtMarked = true;

    // 💥 爆炸粒子
    ((ServerLevel) this.level()).sendParticles(
        ParticleTypes.EXPLOSION,
        player.getX(),
        player.getY(),
        player.getZ(),
        2, 0.2, 0.2, 0.2, 0.0
    );

    ((ServerLevel) this.level()).sendParticles(
        ParticleTypes.POOF,
        player.getX(),
        player.getY(),
        player.getZ(),
        20, 0.5, 0.5, 0.5, 0.05
    );

    // 🔊 音效
    this.level().playSound(
        null,
        player.getX(),
        player.getY(),
        player.getZ(),
        SoundEvents.GENERIC_EXPLODE,
        SoundSource.HOSTILE,
        1.0F,
        1.0F
    );

    this.grabbedPlayer = null;
    this.grabTimer = 0;
    this.grabCooldown = 80;
}
