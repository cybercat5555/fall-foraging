package net.cybercat5555.fallforaging.block.custom;

import com.mojang.serialization.MapCodec;
import net.cybercat5555.fallforaging.item.ModItems;
import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

import java.util.function.BiFunction;

public class FallenAcornsBlock extends PlantBlock {

    public static final MapCodec<FallenAcornsBlock> CODEC = createCodec(FallenAcornsBlock::new);

    public static final DirectionProperty FACING;
    public static final IntProperty ACORN_AMOUNT;
    private static final BiFunction<Direction, Integer, VoxelShape> FACING_AND_AMOUNT_TO_SHAPE;

    public MapCodec<FallenAcornsBlock> getCodec() {
        return CODEC;
    }

    public FallenAcornsBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)((BlockState)this.stateManager.getDefaultState()).with(FACING, Direction.NORTH)).with(ACORN_AMOUNT, 1));
    }

    public ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state) {
        return new ItemStack(ModItems.ACORN);
    }

    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return (BlockState)state.with(FACING, rotation.rotate((Direction)state.get(FACING)));
    }

    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation((Direction)state.get(FACING)));
    }

    public boolean canReplace(BlockState state, ItemPlacementContext context) {
        return !context.shouldCancelInteraction() && context.getStack().isOf(this.asItem()) && (Integer)state.get(ACORN_AMOUNT) < 4 ? true : super.canReplace(state, context);
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return (VoxelShape)FACING_AND_AMOUNT_TO_SHAPE.apply((Direction)state.get(FACING), (Integer)state.get(ACORN_AMOUNT));
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState blockState = ctx.getWorld().getBlockState(ctx.getBlockPos());
        return blockState.isOf(this) ? (BlockState)blockState.with(ACORN_AMOUNT, Math.min(4, (Integer)blockState.get(ACORN_AMOUNT) + 1)) : (BlockState)this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{FACING, ACORN_AMOUNT});
    }


    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        int i = (Integer)state.get(ACORN_AMOUNT);
        if (i < 4) {
            world.setBlockState(pos, (BlockState)state.with(ACORN_AMOUNT, i + 1), 2);
        } else {
            dropStack(world, pos, new ItemStack(this));
        }

    }

    static {
        FACING = Properties.HORIZONTAL_FACING;
        ACORN_AMOUNT = IntProperty.of("acorn_count", 0, 4);
        FACING_AND_AMOUNT_TO_SHAPE = Util.memoize((facing, acorn_count) -> {
            VoxelShape[] voxelShapes = new VoxelShape[]{Block.createCuboidShape(8.0, 0.0, 8.0, 16.0, 1.0, 16.0), Block.createCuboidShape(8.0, 0.0, 0.0, 16.0, 1.0, 8.0), Block.createCuboidShape(0.0, 0.0, 0.0, 8.0, 1.0, 8.0), Block.createCuboidShape(0.0, 0.0, 8.0, 8.0, 1.0, 16.0)};
            VoxelShape voxelShape = VoxelShapes.empty();

            for(int i = 0; i < acorn_count; ++i) {
                int j = Math.floorMod(i - facing.getHorizontal(), 4);
                voxelShape = VoxelShapes.union(voxelShape, voxelShapes[j]);
            }

            return voxelShape.asCuboid();
        });
    }
}
