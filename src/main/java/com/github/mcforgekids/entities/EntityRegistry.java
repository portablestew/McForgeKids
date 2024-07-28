package com.github.mcforgekids.entities;

import com.github.mcforgekids.McForgeKids;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityRegistry {
    // Static registration called from McForgeKids main
    public static void register(IEventBus modEventBus) {
        ENTITY_TYPES.register(modEventBus);
    }

    private static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, McForgeKids.MODID);

    // An arrow that spams snowballs where it lands
    public static final RegistryObject<EntityType<HailstormArrowProjectile>> HAILSTORM_ARROW_ENTITY_TYPE = ENTITY_TYPES.register("hailstorm_arrow",
            () -> EntityType.Builder.of((EntityType.EntityFactory<HailstormArrowProjectile>) HailstormArrowProjectile::new, MobCategory.MISC).build("hailstorm_arrow"));
    public static final RegistryObject<EntityType<HailstormEntity>> HAILSTORM_ENTITY_TYPE = ENTITY_TYPES.register("hailstorm",
            () -> EntityType.Builder.of((EntityType.EntityFactory<HailstormEntity>) HailstormEntity::new, MobCategory.MISC).build("hailstorm"));
}
