package com.ninni.onward_upward.registry;

import com.ninni.onward_upward.OU;
import com.ninni.onward_upward.blocks.TalkingFlowerBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class OUBlocks {

    public static final Block TALKING_FLOWER = register("talking_flower", new TalkingFlowerBlock(FabricBlockSettings.create().mapColor(MapColor.COLOR_YELLOW).noCollision().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY)));

    private static Block register(String id, Block block) {
        return Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(OU.MOD_ID, id), block);
    }
}
