package com.github.mcforgekids.items;

import com.github.mcforgekids.entities.FirestormArrowProjectile;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class FirestormArrowItem extends ArrowItem {
    public FirestormArrowItem(Properties props) {
        super(props);
    }

    @Override
    public @NotNull AbstractArrow createArrow(@NotNull Level level, @NotNull ItemStack stack, @NotNull LivingEntity shooter) {
        return new FirestormArrowProjectile(shooter, level, stack);
    }
}
