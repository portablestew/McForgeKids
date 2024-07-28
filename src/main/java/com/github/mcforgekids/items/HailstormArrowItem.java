package com.github.mcforgekids.items;

import com.github.mcforgekids.entities.HailstormArrowProjectile;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class HailstormArrowItem extends ArrowItem {
    public HailstormArrowItem(Properties props) {
        super(props);
    }

    @Override
    public @NotNull AbstractArrow createArrow(@NotNull Level level, @NotNull ItemStack stack, @NotNull LivingEntity shooter) {
        return new HailstormArrowProjectile(shooter, level, stack);
    }
}
