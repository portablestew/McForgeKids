package com.example.mcforgekids.items;

import java.util.List;
import java.util.Map;

import com.example.mcforgekids.McForgeKids;
import com.example.mcforgekids.blocks.BlockRegistry;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemRegistry {
	// Static registration called from McForgeKids main
	public static void register(IEventBus modEventBus)
	{
        // Register the Deferred Register to the mod event bus so items get registered
        ITEMS.register(modEventBus);
	}
	
    // Create a Deferred Register to hold Items which will all be registered under the "mcforgekids" namespace
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, McForgeKids.MODID);
    
    // Creates a new BlockItem with the id "mcforgekids:example_block", combining the namespace and path
    public static final RegistryObject<Item> EXAMPLE_BLOCK_ITEM = ITEMS.register("example_block", () -> new BlockItem(BlockRegistry.EXAMPLE_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> EXAMPLE_BLOCK_ITEM2 = ITEMS.register("example_block_2", () -> new BlockItem(BlockRegistry.EXAMPLE_BLOCK_2.get(), new Item.Properties()));

    // Creates a new food item with the id "mcforgekids:example_id", nutrition 1 and saturation 2
    public static final RegistryObject<Item> EXAMPLE_ITEM = ITEMS.register("example_item", () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
            .alwaysEdible().nutrition(1).saturationModifier(2f).build())));
    
    // List of items to include in existing creative mode tabs
    public static final Map<ResourceKey<CreativeModeTab>, List<RegistryObject<Item>>> CREATIVE_TAB_ITEMS = Map.of(
    		CreativeModeTabs.BUILDING_BLOCKS, List.of(
    				EXAMPLE_BLOCK_ITEM, 
    				EXAMPLE_BLOCK_ITEM2
			)
	);
    
    // List of items to include in the EXAMPLE_TAB
    public static final List<RegistryObject<Item>> EXAMPLE_TAB_ITEMS = List.of(
    		EXAMPLE_ITEM
	);
}
