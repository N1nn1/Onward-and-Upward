package com.ninni.onward_upward;

import com.google.common.reflect.Reflection;
import com.ninni.onward_upward.registry.OUBlocks;
import com.ninni.onward_upward.registry.OUCreativeModeTab;
import com.ninni.onward_upward.registry.OUItems;
import com.ninni.onward_upward.registry.OUSoundEvents;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;


public class OU implements ModInitializer {
	public static String MOD_ID = "onward_upward";
	public static final ResourceKey<PlacedFeature> PATCH_TALKING_FLOWERS = ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(MOD_ID, "patch_talking_flowers"));

	@Override
	public void onInitialize() {
		Reflection.initialize(
				OUCreativeModeTab.class,
				OUItems.class,
				OUBlocks.class,
				OUSoundEvents.class
		);

		//TODO make this not generate in huge patches
		BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_OVERWORLD), GenerationStep.Decoration.VEGETAL_DECORATION, PATCH_TALKING_FLOWERS);
	}
}