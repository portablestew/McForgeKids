package com.github.mcforgekids.blocks;

import com.github.mcforgekids.McForgeKids;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockRegistry {
	// Static registration called from McForgeKids main
	public static void register(IEventBus modEventBus)
	{
        // Register the Deferred Register to the mod event bus so blocks get registered
        BLOCKS.register(modEventBus);
	}
	
    // Create a Deferred Register to hold Blocks which will all be registered under the "mcforgekids" namespace
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, McForgeKids.MODID);
	
    // Creates a new Block with the id "mcforgekids:test_block", combining the namespace and path
    public static final RegistryObject<Block> TEST_BLOCK = BLOCKS.register("test_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE)));
    public static final RegistryObject<Block> TEST_BLOCK_2 = BLOCKS.register("test_block_2", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.SAND)));
}
