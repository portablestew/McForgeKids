package com.github.mcforgekids.entities;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class HailstormEntity extends Entity {
    private static final int NUM_HAILS_TO_SPAWN = 400;
    private static final int SPAWN_DURATION = 20; // Ticks
    private static final double SPAWN_HEIGHT = 20;
    private static final double SPAWN_HEIGHT_VARIANCE = 1;
    private static final double SPAWN_RADIUS = 8;
    private static final double VERTICAL_SPEED = -1.5;
    private static final double TARGETED_AIM_STRENGTH = 0.08; // In lieu of solving quadratics
    private static final double TARGETED_HORIZONTAL_VARIANCE = 0;//0.5;
    private static final double UNTARGETED_HORIZONTAL_VARIANCE = 0.5;

    private Entity owner;
    private Entity target;
    private int ticks = 0; // Clock elapsed
    private int spawned = 0; // Number spawned so far

    public HailstormEntity(EntityType<HailstormEntity> entityType, Level level) {
        super(entityType, level);
        canUpdate(true);
    }

    public HailstormEntity(Level level, double x, double y, double z, Entity owner, Entity target) {
        super(EntityRegistry.HAILSTORM_ENTITY_TYPE.get(), level);
        setPos(x, y, z);
        this.owner = owner;
        this.target = target;
    }

    private void spawnRandomHail() {
        if (level().isClientSide) {
            return;
        }

        Vec3 spawnPos = new Vec3(
                Math.random() * SPAWN_RADIUS * 2 - SPAWN_RADIUS,
                Math.random() * SPAWN_HEIGHT_VARIANCE - SPAWN_HEIGHT_VARIANCE + SPAWN_HEIGHT,
                Math.random() * SPAWN_RADIUS * 2 - SPAWN_RADIUS);
        spawnPos = spawnPos.add(position());

        Snowball hail = new Snowball(level(), spawnPos.x, spawnPos.y, spawnPos.z);
        hail.setDeltaMovement(calcSpawnVelocity(spawnPos));

        if (owner != null) {
            hail.setOwner(owner);
        }
        level().addFreshEntity(hail);
    }

    private Vec3 calcSpawnVelocity(Vec3 spawnPos) {
        Vec3 velocity = Vec3.ZERO;
        double speedVariance = UNTARGETED_HORIZONTAL_VARIANCE;
        if (target != null) {
            speedVariance = TARGETED_HORIZONTAL_VARIANCE;

            // Aim towards the target in the horizontal plane
            Vec3 dirToTarget = new Vec3(
                    target.position().x - spawnPos.x,
                    0,
                    target.position().z - spawnPos.z);
            velocity = velocity.add(dirToTarget.scale(TARGETED_AIM_STRENGTH));
        }

        velocity = velocity.add(
                Math.random() * speedVariance * 2 - speedVariance,
                VERTICAL_SPEED,
                Math.random() * speedVariance * 2 - speedVariance);
        return velocity;
    }

    @Override
    public void tick() {
        super.tick();
        ticks++;

        // Generate hail at the requested rate
        int targetSpawned = NUM_HAILS_TO_SPAWN * ticks / SPAWN_DURATION;
        while (spawned < targetSpawned) {
            spawnRandomHail();
            spawned++;
        }

        // Discard after duration expires
        if (ticks >= SPAWN_DURATION) {
            discard();
        }
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder builder) {
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag tag) {
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag tag) {
    }
}
