package net.cybercat5555.fallforaging.block.custom;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.cybercat5555.fallforaging.block.ModBlocks;
import net.cybercat5555.fallforaging.item.ModItems;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;

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

    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        int i = (Integer)state.get(AGE);
        boolean bl = i == 3;
        return !bl && stack.isOf(Items.BONE_MEAL) ? ItemActionResult.SKIP_DEFAULT_BLOCK_INTERACTION : super.onUseWithItem(stack, state, world, pos, player, hand, hit);
    }

    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        int i = (Integer)state.get(AGE);
        boolean bl = i == 3;
        if (i > 1) {
            int j = 1 + world.random.nextInt(2);
            dropStack(world, pos, new ItemStack(ModItems.ACORN, j + (bl ? 1 : 0)));
            world.playSound((PlayerEntity)null, pos, SoundEvents.BLOCK_SWEET_BERRY_BUSH_PICK_BERRIES, SoundCategory.BLOCKS, 1.0F, 0.8F + world.random.nextFloat() * 0.4F);
            BlockState blockState = (BlockState)state.with(AGE, 1);
            world.setBlockState(pos, blockState, 2);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(player, blockState));
            return ActionResult.success(world.isClient);
        } else {
            return super.onUse(state, world, pos, player, hit);
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

