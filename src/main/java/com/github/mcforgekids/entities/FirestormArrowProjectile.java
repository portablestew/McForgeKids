package com.github.mcforgekids.entities;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class FirestormArrowProjectile extends BaseSpawnStormArrow {
    public FirestormArrowProjectile(EntityType<FirestormArrowProjectile> entityType, Level level) {
        super(entityType, level);
    }

    public FirestormArrowProjectile(LivingEntity shooter, Level level, ItemStack stack) {
        super(EntityRegistry.FIRESTORM_ARROW_ENTITY_TYPE.get(), level, shooter, stack);
    }

    protected Entity spawnStorm(Entity target) {
        return new FirestormEntity(level(), getOwner(), target);
    }
}
