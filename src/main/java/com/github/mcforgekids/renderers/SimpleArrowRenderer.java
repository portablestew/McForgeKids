package com.github.mcforgekids.renderers;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.projectile.AbstractArrow;
import org.jetbrains.annotations.NotNull;

public class SimpleArrowRenderer extends ArrowRenderer<AbstractArrow> {
    private final ResourceLocation texture;

    public SimpleArrowRenderer(EntityRendererProvider.Context manager, String texturePath) {
        super(manager);
        texture = new ResourceLocation(texturePath);
    }

    public @NotNull ResourceLocation getTextureLocation(@NotNull AbstractArrow arrow) {
        return texture;
    }
}
