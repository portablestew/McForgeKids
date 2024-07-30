package com.github.mcforgekids.entities;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.level.Level;

public class HailstormEntity extends BaseSpawnStormEntity {
    public HailstormEntity(EntityType<HailstormEntity> entityType, Level level) {
        super(entityType, level);
    }

    public HailstormEntity(Level level, Entity owner, Entity target) {
        super(EntityRegistry.HAILSTORM_ENTITY_TYPE.get(), level, owner, target);
    }

    protected Entity spawnChild() {
        return new Snowball(level(), getLivingOwner());
    }
}
