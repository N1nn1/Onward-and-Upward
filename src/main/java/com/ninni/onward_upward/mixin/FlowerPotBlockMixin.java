package com.ninni.onward_upward.mixin;

import com.ninni.onward_upward.registry.OUItems;
import com.ninni.onward_upward.registry.OUSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FlowerPotBlock.class)
public abstract class FlowerPotBlockMixin extends Block {

    @Shadow @Final private Block content;

    public FlowerPotBlockMixin(Properties properties) {
        super(properties);
    }

    @Inject(method = "use", at = @At("HEAD"))
    public void OU$use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult, CallbackInfoReturnable<InteractionResult> cir) {
        if (player.getItemInHand(interactionHand).getItem() == OUItems.TALKING_FLOWER && this.content == Blocks.AIR) {
            level.playSound(null, blockPos, OUSoundEvents.TALKING_FLOWER_POT, SoundSource.BLOCKS, 1, 1);
        }

    }
}
