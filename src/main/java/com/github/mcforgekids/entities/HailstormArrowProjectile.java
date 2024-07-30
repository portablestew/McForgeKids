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

public class HailstormArrowProjectile extends AbstractArrow {
    private boolean spawnedHailstorm = false;

    public HailstormArrowProjectile(EntityType<HailstormArrowProjectile> entityType, Level level) {
        super(entityType, level);
    }

    public HailstormArrowProjectile(LivingEntity shooter, Level level, ItemStack stack) {
        super(EntityRegistry.HAILSTORM_ARROW_ENTITY_TYPE.get(), shooter, level, stack);
    }

    private void spawnHailstorm(Entity target) {
        // Only spawn once
        if (spawnedHailstorm) {
            return;
        }
        spawnedHailstorm = true;

        if (!level().isClientSide) {
            BaseSpawnStormEntity storm = new HailstormEntity(level(), position(), getOwner(), target);
            level().addFreshEntity(storm);
        }
    }

    @Override
    protected void onHitBlock(@NotNull BlockHitResult hit) {
        super.onHitBlock(hit);
        spawnHailstorm(null);
    }

    @Override
    protected void onHitEntity(@NotNull EntityHitResult hit) {
        super.onHitEntity(hit);
        spawnHailstorm(hit.getEntity());
    }

    @Override
    protected @NotNull ItemStack getDefaultPickupItem() {
        return ItemStack.EMPTY; // No pickup allowed for this arrow
    }
}
