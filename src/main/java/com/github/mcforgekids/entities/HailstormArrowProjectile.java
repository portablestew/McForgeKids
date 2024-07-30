package com.github.mcforgekids.entities;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class HailstormArrowProjectile extends BaseSpawnStormArrow {
    public HailstormArrowProjectile(EntityType<HailstormArrowProjectile> entityType, Level level) {
        super(entityType, level);
    }

    public HailstormArrowProjectile(LivingEntity shooter, Level level, ItemStack stack) {
        super(EntityRegistry.HAILSTORM_ARROW_ENTITY_TYPE.get(), level, shooter, stack);
    }

    protected Entity spawnStorm(Entity target) {
        return new HailstormEntity(level(), getOwner(), target);
    }
}
