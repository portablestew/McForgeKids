package com.github.mcforgekids.renderers;

import com.github.mcforgekids.McForgeKids;
import com.github.mcforgekids.entities.EntityRegistry;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
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
        // Hailstorm arrow
        EntityRenderers.register(EntityRegistry.HAILSTORM_ARROW_ENTITY_TYPE.get(),
                (EntityRendererProvider.Context context) ->
                        new SimpleArrowRenderer(context, "mcforgekids:textures/hailstorm_arrow.png"));
        EntityRenderers.register(EntityRegistry.HAILSTORM_ENTITY_TYPE.get(), NoopRenderer::new);

        // Firestorm arrow
        EntityRenderers.register(EntityRegistry.FIRESTORM_ARROW_ENTITY_TYPE.get(),
                (EntityRendererProvider.Context context) ->
                        new SimpleArrowRenderer(context, "mcforgekids:textures/firestorm_arrow.png"));
        EntityRenderers.register(EntityRegistry.FIRESTORM_ENTITY_TYPE.get(), NoopRenderer::new);
    }
}
