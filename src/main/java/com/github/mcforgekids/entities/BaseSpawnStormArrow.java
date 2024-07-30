package com.github.mcforgekids.entities;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;

// Usage:
//  - Create a new entity class extending BaseSpawnStormArrow.
//  - Implement spawnStorm() to create the correct entity type. Its position will be set automatically.
//  - Add the new class to the entity registry.
//  - Add a renderer for the new entity type.
public abstract class BaseSpawnStormArrow extends AbstractArrow {
    private boolean spawnedStorm = false;

    protected BaseSpawnStormArrow(EntityType<? extends BaseSpawnStormArrow> entityType, Level level) {
        super(entityType, level);
    }

    protected BaseSpawnStormArrow(EntityType<? extends BaseSpawnStormArrow> entityType, Level level, LivingEntity shooter, ItemStack stack) {
        super(entityType, shooter, level, stack);
    }

    // Abstract interface
    // Child class must implement this by spawning an entity
    // It will be automatically positioned at the impact point
    protected abstract Entity spawnStorm(Entity target);

    private void internalSpawnOnImpact(Entity target) {
        // Only spawn once
        if (spawnedStorm) {
            return;
        }
        spawnedStorm = true;

        if (!level().isClientSide) {
            Entity storm = spawnStorm(target);
            storm.setPos(position());
            level().addFreshEntity(storm);
        }
    }

    @Override
    protected void onHitBlock(@NotNull BlockHitResult hit) {
        super.onHitBlock(hit);
        internalSpawnOnImpact(null);
    }

    @Override
    protected void onHitEntity(@NotNull EntityHitResult hit) {
        super.onHitEntity(hit);
        internalSpawnOnImpact(hit.getEntity());
    }

    @Override
    protected @NotNull ItemStack getDefaultPickupItem() {
        return ItemStack.EMPTY; // No pickup allowed for this arrow
    }
}
