package com.github.mcforgekids.entities;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

// Usage:
//  - Create a new entity class extending BaseSpawnStormEntity.
//  - Implement the spawnChild() function to return a new entity.
//      - It will be automatically added to the level with the correct position and velocity.
//      - Optionally use, if needed: getOwner, getLivingOwner, getTarget.
//  - Override any of the constants tp alter spawn rate and positioning.
//  - Don't forget to add the new class to the entity registry.
public abstract class BaseSpawnStormEntity extends Entity {
    // Constants, but non-final and may be overwritten from child classes
    protected int numToSpawn = 400;
    protected int stormDuration = 40; // Ticks
    protected double spawnHeight = 20;
    protected double spawnHeightVariance = 1;
    protected double spawnRadius = 8;
    protected double verticalSpeed = -1.5;
    protected double targetedAimStrength = 0.08; // In lieu of solving quadratics
    protected double targetedHorizontalVariance = 0.1;
    protected double nonTargetedHorizontalVariance = 0.5;

    // Abstract interface
    // Child class must override to spawn the desired object
    protected abstract Entity spawnChild();

    private Entity owner;
    private Entity target;

    private int ticks = 0; // Clock elapsed
    private int spawned = 0; // Number spawned so far

    protected BaseSpawnStormEntity(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    protected BaseSpawnStormEntity(EntityType<?> entityType, Level level, Entity owner, Entity target) {
        super(entityType, level);
        this.owner = owner;
        this.target = target;
    }

    // Access the owner entity, who spawned this storm
    protected Entity getOwner() {
        return owner;
    }

    // Access the owner as a living entity, if they are one
    // Returns null if the owner is not a living entity
    protected LivingEntity getLivingOwner() {
        return owner instanceof LivingEntity ? (LivingEntity) owner : null;
    }

    // Access the target entity
    protected Entity getTarget() {
        return target;
    }

    private void spawnRandom() {
        if (level().isClientSide) {
            return;
        }

        Vec3 spawnPos = new Vec3(
                Math.random() * spawnRadius * 2 - spawnRadius,
                Math.random() * spawnHeightVariance - spawnHeightVariance * 0.5 + spawnHeight,
                Math.random() * spawnRadius * 2 - spawnRadius);
        spawnPos = spawnPos.add(position());

        Entity child = spawnChild();
        child.setPos(spawnPos);
        child.setDeltaMovement(calcSpawnVelocity(spawnPos));
        level().addFreshEntity(child);
    }

    private Vec3 calcSpawnVelocity(Vec3 spawnPos) {
        Vec3 velocity = Vec3.ZERO;
        double speedVariance = nonTargetedHorizontalVariance;
        if (target != null) {
            speedVariance = targetedHorizontalVariance;

            // Aim towards the target in the horizontal plane
            Vec3 dirToTarget = new Vec3(
                    target.position().x - spawnPos.x,
                    0,
                    target.position().z - spawnPos.z);
            velocity = velocity.add(dirToTarget.scale(targetedAimStrength));
        }

        velocity = velocity.add(
                Math.random() * speedVariance * 2 - speedVariance,
                verticalSpeed,
                Math.random() * speedVariance * 2 - speedVariance);
        return velocity;
    }

    @Override
    public void tick() {
        super.tick();
        ticks++;

        // Generate hail at the requested rate
        int targetSpawned = numToSpawn * ticks / stormDuration;
        while (spawned < targetSpawned) {
            spawnRandom();
            spawned++;
        }

        // Discard after duration expires
        if (ticks >= stormDuration) {
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
