package com.github.mcforgekids.entities;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class HailstormEntity extends BaseSpawnStormEntity {
    public HailstormEntity(EntityType<HailstormEntity> entityType, Level level) {
        super(entityType, level);
    }

    public HailstormEntity(Level level, Vec3 pos, Entity owner, Entity target) {
        super(EntityRegistry.HAILSTORM_ENTITY_TYPE.get(), level, pos, owner, target);
    }

    protected Entity spawnChild() {
        return new Snowball(level(), getLivingOwner());
    }
}
