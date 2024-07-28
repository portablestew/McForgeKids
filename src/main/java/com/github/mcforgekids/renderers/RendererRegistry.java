package com.github.mcforgekids.renderers;

import com.github.mcforgekids.McForgeKids;
import com.github.mcforgekids.entities.EntityRegistry;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.NoopRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = McForgeKids.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class RendererRegistry {
    @SubscribeEvent
    public static void doSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(EntityRegistry.HAILSTORM_ARROW_ENTITY_TYPE.get(), HailstormArrowRenderer::new);
        EntityRenderers.register(EntityRegistry.HAILSTORM_ENTITY_TYPE.get(), NoopRenderer::new);
    }
}
