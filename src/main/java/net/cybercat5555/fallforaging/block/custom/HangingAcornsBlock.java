package net.cybercat5555.fallforaging.block.custom;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.cybercat5555.fallforaging.block.ModBlocks;
import net.cybercat5555.fallforaging.item.ModItems;
import net.minecraft.block.*;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class HangingAcornsBlock extends PlantBlock implements Fertilizable {

    public static final MapCodec<HangingAcornsBlock> CODEC = createCodec(HangingAcornsBlock::new);
    public static final IntProperty AGE;
    public static final int MAX_AGE = 3;
    private static final VoxelShape[] SHAPES;
    public static final BooleanProperty HANGING;

    public MapCodec<HangingAcornsBlock> getCodec() {
        return CODEC;
    }

    public HangingAcornsBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)this.stateManager.getDefaultState()).with(AGE, 0));
    }



    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{AGE}).add(new Property[]{HANGING});
    }



    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Vec3d vec3d = state.getModelOffset(world, pos);
        VoxelShape voxelShape;
        if (!(Boolean)state.get(HANGING)) {
            voxelShape = SHAPES[3];
        } else {
            voxelShape = SHAPES[(Integer)state.get(AGE)];
        }

        return voxelShape.offset(vec3d.x, vec3d.y, vec3d.z);
    }

    public ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state) {
        return new ItemStack(ModItems.ACORN);
    }

    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return isHanging(state) ? world.getBlockState(pos.up()).isOf(Blocks.OAK_LEAVES) : super.canPlaceAt(state, world, pos);
    }

    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!isHanging(state)) {
            if (!isFullyGrown(state)) {
                world.setBlockState(pos, (BlockState)state.cycle(AGE), 2);
            }

        }
    }

    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        return !isHanging(state) || !isFullyGrown(state);
    }

    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        int i = Math.min(3, (Integer)state.get(AGE) + 1);
        world.setBlockState(pos, (BlockState)state.with(AGE, i), 2);
    }


    private static boolean isHanging(BlockState state) {
        return (Boolean)state.get(HANGING);
    }

    private static boolean isFullyGrown(BlockState state) {
        return (Integer)state.get(AGE) == 3;
    }

    public static BlockState getDefaultHangingState() {
        return getHangingState(0);
    }

    public static BlockState getHangingState(int age) {
        return (BlockState)((BlockState) ModBlocks.HANGING_ACORNS.getDefaultState().with(HANGING, true)).with(AGE, age);
    }

    static {
        AGE = Properties.AGE_3;
            SHAPES = new VoxelShape[]{Block.createCuboidShape(0.0, 10.0, 0.0, 16.0, 16.0, 16.0), Block.createCuboidShape(0.0, 10.0, 0.0, 16.0, 16.0, 16.0), Block.createCuboidShape(0.0, 10.0, 0.0, 16.0, 16.0, 16.0), Block.createCuboidShape(0.0, 10.0, 0.0, 16.0, 16.0, 16.0)};
        HANGING = Properties.HANGING;
    }
}

