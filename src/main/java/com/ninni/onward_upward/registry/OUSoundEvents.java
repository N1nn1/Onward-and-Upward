package com.ninni.onward_upward.registry;

import com.ninni.onward_upward.OU;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class OUSoundEvents {

    public static final SoundEvent TALKING_FLOWER_WONDER = register("block.talking_flower.wonder");
    public static final SoundEvent TALKING_FLOWER_CONTACT = register("block.talking_flower.contact");
    public static final SoundEvent TALKING_FLOWER_INTERACTED = register("block.talking_flower.interacted");
    public static final SoundEvent TALKING_FLOWER_UNDERWATER = register("block.talking_flower.underwater");
    public static final SoundEvent TALKING_FLOWER_IN_THE_DARK = register("block.talking_flower.in_the_dark");
    public static final SoundEvent TALKING_FLOWER_UNDERGROUND = register("block.talking_flower.underground");
    public static final SoundEvent TALKING_FLOWER_IN_THE_SKY = register("block.talking_flower.in_the_sky");
    public static final SoundEvent TALKING_FLOWER_PLACED = register("block.talking_flower.placed");
    public static final SoundEvent TALKING_FLOWER_DESTROYED = register("block.talking_flower.destroyed");

    public static final SoundEvent TALKING_FLOWER_POT = register("block.talking_flower.pot");
    public static final SoundEvent TALKING_FLOWER_POT_REMOVE = register("block.talking_flower.pot_remove");


    private static SoundEvent register(String name) {
        ResourceLocation id = new ResourceLocation(OU.MOD_ID, name);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
    }
}
