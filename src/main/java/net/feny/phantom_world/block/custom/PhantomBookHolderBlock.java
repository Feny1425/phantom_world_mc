package net.feny.phantom_world.block.custom;

import net.feny.phantom_world.block.entity.PhantomBookHolderEntity;
import net.feny.phantom_world.item.ModItems;
import net.feny.phantom_world.item.custom.PhantomBookItem;
import net.feny.phantom_world.screen.ScreenHandlers;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import static net.feny.phantom_world.block.entity.ModBlockEntities.PHANTOM_BOOK_HOLDER_ENTITY;
import static net.feny.phantom_world.item.custom.PhantomBookItem.MAX_BOOK_PROGRESS;

public class PhantomBookHolderBlock extends BlockWithEntity {

    public static final BooleanProperty HAS_BOOK = Properties.HAS_BOOK;
    public static final IntProperty SELECTED_PAGE = IntProperty.of("selected_page",0,6);

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
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing()).with(HAS_BOOK, false).with(SELECTED_PAGE,0);
    }

    /*@Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (state.get(HAS_BOOK)){
            NbtCompound nbt = new NbtCompound();
            nbt.putInt("book_progress", state.get(BOOK_PROGRESS));
            ItemStack item = ModItems.PHANTOM_WORLD_STARTER.getDefaultStack();
            item.setNbt(nbt);
            player.giveItemStack(item);
        }
        super.onBreak(world, pos, state, player);
    }*/



    public void updateNeighbors(BlockState state, World world, BlockPos pos) {
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
        builder.add(HAS_BOOK,FACING,SELECTED_PAGE);
    }
/*
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos,
                              PlayerEntity player, Hand hand, BlockHitResult hit) {

        if (state.get(HAS_BOOK) && player.isSneaking()){
            NbtCompound nbt = new NbtCompound();
            nbt.putInt("book_progress", state.get(BOOK_PROGRESS));
            ItemStack item = ModItems.PHANTOM_WORLD_STARTER.getDefaultStack();
            item.setNbt(nbt);
            player.giveItemStack(item);
            state = (BlockState)state.cycle(HAS_BOOK).with(BOOK_PROGRESS,0);
            world.setBlockState(pos, state, Block.NOTIFY_ALL);
            this.updateNeighbors(state, world, pos);
        }
        return ActionResult.SUCCESS;
    }*/



    /**
     * Block Entity*/

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new PhantomBookHolderEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        //With inheriting from BlockWithEntity this defaults to INVISIBLE, so we need to change that!
        return BlockRenderType.MODEL;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            //This will call the createScreenHandlerFactory method from BlockWithEntity, which will return our blockEntity casted to
            //a namedScreenHandlerFactory. If your block class does not extend BlockWithEntity, it needs to implement createScreenHandlerFactory.
            NamedScreenHandlerFactory screenHandlerFactory = state.createScreenHandlerFactory(world, pos);

            if (player.isSneaking()){
                if (screenHandlerFactory != null) {
                    //With this call the server will request the client to open the appropriate Screenhandler
                    player.openHandledScreen(screenHandlerFactory);
                }
            }
            else {
                int selected_page = state.get(SELECTED_PAGE);
                if (state.get(HAS_BOOK) ){
                    state = (BlockState)state.with(SELECTED_PAGE,selected_page < MAX_BOOK_PROGRESS-1? selected_page+1:0);
                    world.setBlockState(pos, state, Block.NOTIFY_ALL);
                    this.updateNeighbors(state, world, pos);
                }
            }
        }
        return ActionResult.SUCCESS;
    }


    //This method will drop all items onto the ground when the block is broken
    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof PhantomBookHolderEntity) {
                ItemScatterer.spawn(world, pos, (PhantomBookHolderEntity)blockEntity);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, PHANTOM_BOOK_HOLDER_ENTITY,PhantomBookHolderEntity::tick);
    }
}
