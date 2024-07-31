package com.github.mcforgekids.entities;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.level.Level;

public class FirestormEntity extends BaseSpawnStormEntity {
    public FirestormEntity(EntityType<FirestormEntity> entityType, Level level) {
        super(entityType, level);
    }

    public FirestormEntity(Level level, Entity owner, Entity target) {
        super(EntityRegistry.FIRESTORM_ENTITY_TYPE.get(), level, owner, target);
    }

    protected Entity spawnChild() {
        // Note: zeroes are projectile "power" (acceleration), not position
        return new SmallFireball(level(), getLivingOwner(), 0, 0, 0);
    }
}
