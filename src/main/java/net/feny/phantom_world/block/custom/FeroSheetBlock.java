package net.feny.phantom_world.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class FeroSheetBlock extends Block {
    public FeroSheetBlock(Settings settings) {
        super(settings);
    }

    public static VoxelShape SHAPE = Block.createCuboidShape(0,0,0,16,0.02f,16);

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        BlockState blockState = world.getBlockState(pos.down());
        if (!blockState.isSideSolidFullSquare(world, pos, Direction.UP)|| blockState.isOf(Blocks.HOPPER)){
            world.breakBlock(pos,true);
        }
        super.neighborUpdate(state, world, pos, sourceBlock, sourcePos, notify);
    }
    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.down();
        BlockState blockState = world.getBlockState(blockPos);
        BlockState blockState2 = world.getBlockState(pos);
        return this.canRunOnTop(world, blockPos, blockState,blockState2);
    }

    private boolean canRunOnTop(BlockView world, BlockPos pos, BlockState floor,BlockState place) {
        return (floor.isSideSolidFullSquare(world, pos, Direction.UP) || floor.isOf(Blocks.HOPPER))&& (place.isAir());
    }
}
