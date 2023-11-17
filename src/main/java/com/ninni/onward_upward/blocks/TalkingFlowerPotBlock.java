package com.ninni.onward_upward.blocks;

import com.ninni.onward_upward.registry.OUBlocks;
import com.ninni.onward_upward.registry.OUSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class TalkingFlowerPotBlock extends FlowerPotBlock {

    public TalkingFlowerPotBlock(Properties properties) {
        super(OUBlocks.TALKING_FLOWER, properties);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        level.playSound(null, blockPos, OUSoundEvents.TALKING_FLOWER_POT_REMOVE, SoundSource.BLOCKS, 1, 1);
        return super.use(blockState, level, blockPos, player, interactionHand, blockHitResult);
    }
}
