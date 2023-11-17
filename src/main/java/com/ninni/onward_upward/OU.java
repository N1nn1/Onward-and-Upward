package com.ninni.onward_upward;

import com.google.common.reflect.Reflection;
import com.ninni.onward_upward.registry.*;
import net.fabricmc.api.ModInitializer;


public class OU implements ModInitializer {
	public static String MOD_ID = "onward_upward";

	@Override
	public void onInitialize() {
		Reflection.initialize(
				OUCreativeModeTab.class,
				OUItems.class,
				OUBlocks.class
		);
	}
}