package com.ninni.onward_upward.registry;

import com.ninni.onward_upward.OU;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

public class OUItems {

    public static final Item TALKING_FLOWER = register("talking_flower", new BlockItem(OUBlocks.TALKING_FLOWER, new FabricItemSettings()));

    private static Item register(String id, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(OU.MOD_ID, id), item);
    }
}
