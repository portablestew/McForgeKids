package com.github.mcforgekids.items;

import java.util.List;
import java.util.Map;

import com.github.mcforgekids.McForgeKids;
import com.github.mcforgekids.blocks.BlockRegistry;

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
    
    // Creates a new BlockItem with the id "mcforgekids:test_block", combining the namespace and path
    public static final RegistryObject<Item> TEST_BLOCK_ITEM = ITEMS.register("test_block", () -> new BlockItem(BlockRegistry.TEST_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> TEST_BLOCK_ITEM2 = ITEMS.register("test_block_2", () -> new BlockItem(BlockRegistry.TEST_BLOCK_2.get(), new Item.Properties()));

    // Creates a new food item with the id "mcforgekids:test_consumable_item", nutrition 1 and saturation 2
    public static final RegistryObject<Item> TEST_CONSUMABLE_ITEM = ITEMS.register("test_consumable_item", () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
            .alwaysEdible().nutrition(1).saturationModifier(2f).build())));
    
    // List of items to include in existing creative mode tabs
    public static final Map<ResourceKey<CreativeModeTab>, List<RegistryObject<Item>>> CREATIVE_TAB_ITEMS = Map.of(
    		CreativeModeTabs.BUILDING_BLOCKS, List.of(
    				TEST_BLOCK_ITEM, 
    				TEST_BLOCK_ITEM2
			)
	);
    
    // List of items to include in the TEST_TAB
    public static final List<RegistryObject<Item>> TEST_TAB_ITEMS = List.of(
    		TEST_CONSUMABLE_ITEM
	);
}
