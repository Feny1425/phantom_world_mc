package net.feny.phantom_world.block.models;

import net.feny.phantom_world.block.entity.ModBlocksEntities;
import net.feny.phantom_world.item.ModItems;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemFrameItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.*;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class PhantomBookHolderBlock extends HorizontalFacingBlock {

    public static final BooleanProperty HAS_BOOK = Properties.HAS_BOOK;
    public static final IntProperty BOOK_PROGRESS = IntProperty.of("book_progress",0,6);

    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public PhantomBookHolderBlock(Settings settings) {
        super(settings);
    }

    public static VoxelShape SHAPE = Block.createCuboidShape(3,0,3,13,14,13);

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing()).with(HAS_BOOK, false).with(BOOK_PROGRESS,0);
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (state.get(HAS_BOOK)){
            NbtCompound nbt = new NbtCompound();
            nbt.putInt("book_progress", state.get(BOOK_PROGRESS));
            ItemStack item = ModItems.PHANTOM_WORLD_STARTER.getDefaultStack();
            item.setNbt(nbt);
            player.giveItemStack(item);
        }
        super.onBreak(world, pos, state, player);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (state.get(HAS_BOOK)){
            NbtCompound nbt = new NbtCompound();
            nbt.putInt("book_progress", state.get(BOOK_PROGRESS));
            ItemStack item = ModItems.PHANTOM_WORLD_STARTER.getDefaultStack();
            item.setNbt(nbt);
            player.giveItemStack(item);
            state = (BlockState)state.cycle(HAS_BOOK).with(BOOK_PROGRESS,0);
            world.setBlockState(pos, state, Block.NOTIFY_ALL);
            this.updateNeighbors(state, world, pos);
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }
    private void updateNeighbors(BlockState state, World world, BlockPos pos) {
        world.updateNeighborsAlways(pos, this);
        world.updateNeighborsAlways(pos.offset(PhantomBookHolderBlock.getDirection(state).getOpposite()), this);
    }
    protected static Direction getDirection(BlockState state) {
        return state.get(FACING);
    }
    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(HAS_BOOK,FACING,BOOK_PROGRESS);
    }





}
