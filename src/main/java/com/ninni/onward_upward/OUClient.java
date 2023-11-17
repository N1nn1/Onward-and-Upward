package com.ninni.onward_upward;

import com.ninni.onward_upward.registry.OUBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;


public class OUClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(),
				OUBlocks.TALKING_FLOWER
		);
	}
}