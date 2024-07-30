package com.github.mcforgekids.entities;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class FirestormEntity extends BaseSpawnStormEntity {
    public FirestormEntity(EntityType<FirestormEntity> entityType, Level level) {
        super(entityType, level);
    }

    public FirestormEntity(Level level, Vec3 pos, Entity owner, Entity target) {
        super(EntityRegistry.FIRESTORM_ENTITY_TYPE.get(), level, pos, owner, target);
    }

    protected Entity spawnChild() {
        return new SmallFireball(level(), getLivingOwner(), getX(), getY(), getZ());
    }
}
