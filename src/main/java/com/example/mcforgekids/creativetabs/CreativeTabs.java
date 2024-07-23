package com.example.mcforgekids.creativetabs;

import java.util.List;

import com.example.mcforgekids.McForgeKids;
import com.example.mcforgekids.items.ItemRegistry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class CreativeTabs {
    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "mcforgekids" namespace
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, McForgeKids.MODID);

    // Creates a creative tab with the id "mcforgekids:example_tab" for the example item, that is placed after the combat tab
    public static final RegistryObject<CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("example_tab", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> ItemRegistry.EXAMPLE_ITEM.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
            	for (RegistryObject<Item> item : ItemRegistry.EXAMPLE_TAB_ITEMS) {
            		output.accept(item.get()); // Add the example item to the tab. For your own tabs, this method is preferred over the event            		
            	}
            }).build());

    // Initialized from McForgeKids main
    public CreativeTabs(IEventBus modEventBus)
    {
    	// Register the Deferred Register to the mod event bus so tabs get registered
        CREATIVE_MODE_TABS.register(modEventBus);
    	
        // Register the items to a creative tab
        modEventBus.addListener(this::addCreative);
    }

    // Add the example block items to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
    	List<RegistryObject<Item>> items = ItemRegistry.CREATIVE_TAB_ITEMS.get(event.getTabKey());
    	if (items != null)
    	{
	    	for (RegistryObject<Item> item : items) {
	    		event.accept(item);
			}
    	}
    }
}
