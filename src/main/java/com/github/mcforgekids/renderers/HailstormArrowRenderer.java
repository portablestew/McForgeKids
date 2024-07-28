package com.github.mcforgekids.renderers;

import com.github.mcforgekids.entities.HailstormArrowProjectile;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class HailstormArrowRenderer extends ArrowRenderer<HailstormArrowProjectile> {
    public static final ResourceLocation TEXTURE = new ResourceLocation("mcforgekids:textures/hailstorm_arrow.png");

    public HailstormArrowRenderer(EntityRendererProvider.Context manager) {
        super(manager);
    }

    public @NotNull ResourceLocation getTextureLocation(@NotNull HailstormArrowProjectile arrow) {
        return TEXTURE;
    }
}
