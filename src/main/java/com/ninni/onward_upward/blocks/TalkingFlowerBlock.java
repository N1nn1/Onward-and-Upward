package com.ninni.onward_upward.blocks;

import com.ninni.onward_upward.registry.OUSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("deprecation")
public class TalkingFlowerBlock extends BushBlock implements SimpleWaterloggedBlock {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty TALKING = BooleanProperty.create("talking");
    protected static final VoxelShape SHAPE = Block.box(5.0, 0.0, 5.0, 11.0, 10.0, 11.0);


    public TalkingFlowerBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(TALKING, false).setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
    }

    @Override
    public void playerWillDestroy(Level level, BlockPos blockPos, BlockState blockState, Player player) {
        level.playSound(null, blockPos, OUSoundEvents.TALKING_FLOWER_DESTROYED, SoundSource.BLOCKS, 1, 1);
        super.playerWillDestroy(level, blockPos, blockState, player);
    }


    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if (player.getItemInHand(interactionHand).isEmpty() && !blockState.getValue(TALKING)) {
            this.talk(level, blockPos, blockState, TalkingFlowerDialogue.INTERACTED);
            return InteractionResult.SUCCESS;
        }
        return super.use(blockState, level, blockPos, player, interactionHand, blockHitResult);
    }

    public void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        TalkingFlowerDialogue dialogue;

        if (serverLevel.dimension() == Level.OVERWORLD && blockPos.getY() > 100) dialogue = TalkingFlowerDialogue.IN_THE_SKY;
        else if (serverLevel.dimension() == Level.OVERWORLD && blockPos.getY() < 50) dialogue = TalkingFlowerDialogue.UNDERGROUND;
        else if (serverLevel.isNight() && !blockState.getValue(WATERLOGGED)) dialogue = TalkingFlowerDialogue.IN_THE_DARK;
        else if (blockState.getValue(WATERLOGGED)) dialogue = TalkingFlowerDialogue.UNDERWATER;
        else dialogue = TalkingFlowerDialogue.WONDER;

        //TODO this doesnt make the flower talk
        if (randomSource.nextInt(3) == 0 && !blockState.getValue(TALKING)) {
            this.talk(serverLevel, blockPos, blockState, dialogue);
        }

    }

    @Override
    public void tick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        serverLevel.gameEvent(GameEvent.BLOCK_ACTIVATE, blockPos, GameEvent.Context.of(blockState));
        serverLevel.setBlockAndUpdate(blockPos, blockState.setValue(TALKING, false));
    }

    @Override
    public void entityInside(BlockState blockState, Level level, BlockPos blockPos, Entity entity) {
        if (entity instanceof Player && !blockState.getValue(TALKING)) {
            this.talk(level, blockPos, blockState, TalkingFlowerDialogue.CONTACT);
        }
        super.entityInside(blockState, level, blockPos, entity);
    }


    public void talk(Level level, BlockPos blockPos, BlockState blockState, TalkingFlowerDialogue dialogue) {
        level.setBlock(blockPos, blockState.setValue(TALKING, true), 4);
        SoundEvent event;
        switch (dialogue) {
            case CONTACT -> event = OUSoundEvents.TALKING_FLOWER_CONTACT;
            case WONDER -> event = OUSoundEvents.TALKING_FLOWER_WONDER;
            case INTERACTED -> event = OUSoundEvents.TALKING_FLOWER_INTERACTED;
            case UNDERWATER -> event = OUSoundEvents.TALKING_FLOWER_UNDERWATER;
            case IN_THE_DARK -> event = OUSoundEvents.TALKING_FLOWER_IN_THE_DARK;
            case UNDERGROUND -> event = OUSoundEvents.TALKING_FLOWER_UNDERGROUND;
            case IN_THE_SKY -> event = OUSoundEvents.TALKING_FLOWER_IN_THE_SKY;
            case PLACED -> event = OUSoundEvents.TALKING_FLOWER_PLACED;
            default -> event = SoundEvents.EMPTY;
        }
        if (level instanceof ServerLevel serverLevel && blockState.getValue(WATERLOGGED)) serverLevel.sendParticles(ParticleTypes.BUBBLE_COLUMN_UP, blockPos.getX() + 0.5, blockPos.getY() + 0.2, blockPos.getZ() + 0.5, 15,0.05,0.5,0.05,0.2);

        level.playSound(null, blockPos, event, SoundSource.BLOCKS, 1, 1);
        level.scheduleTick(blockPos, this, dialogue.getDuration());
    }


    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return SHAPE;
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        LevelAccessor levelAccessor = blockPlaceContext.getLevel();
        BlockPos blockPos = blockPlaceContext.getClickedPos();
        boolean bl = levelAccessor.getFluidState(blockPos).getType() == Fluids.WATER;
        this.talk(blockPlaceContext.getLevel(), blockPos, this.defaultBlockState(), TalkingFlowerDialogue.PLACED);
        return this.defaultBlockState().setValue(WATERLOGGED, bl).setValue(FACING, blockPlaceContext.getHorizontalDirection().getOpposite());
    }

    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState2, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos blockPos2) {
        if (blockState.getValue(WATERLOGGED)) {
            levelAccessor.scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(levelAccessor));
        }
        return super.updateShape(blockState, direction, blockState2, levelAccessor, blockPos, blockPos2);
    }

    public FluidState getFluidState(BlockState blockState) {
        return blockState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(blockState);
    }

    protected boolean mayPlaceOn(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return true;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(TALKING, FACING, WATERLOGGED);
    }
}
