package net.feny.phantom_world.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.EndPortalBlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class PhantomPortalBlockEntity
        extends BlockEntity {
    public PhantomPortalBlockEntity(BlockPos blockPos, BlockState state) {
        super(ModBlockEntities.PHANTOM_PORTAL, blockPos, state);
    }

    public boolean shouldDrawSide(Direction direction) {
        return direction.getAxis() == Direction.Axis.Y;
    }
}

