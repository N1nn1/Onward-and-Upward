package com.ninni.onward_upward.registry;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;

import static com.ninni.onward_upward.registry.OUItems.*;

public class OUCreativeModeTab {

    static {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> {
            entries.addAfter(Items.PINK_PETALS, TALKING_FLOWER);
        });
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.REDSTONE_BLOCKS).register(entries -> {
            entries.addAfter(Items.STONE_BUTTON, TALKING_FLOWER);
        });
    }

}
