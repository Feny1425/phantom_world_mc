package net.feny.phantom_world.block.custom;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class BloodBlock extends Block {


    public BloodBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        /*if (getCompletedFramePattern().searchAround(world,pos) != null){
            BlockPos center = getCompletedFramePattern().searchAround(world,pos).getFrontTopLeft().add(-3,0,-2);
            randomSpawnParticle(world,center,ParticleTypes.SOUL);

        }*/
    }



    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        BlockState blockState = world.getBlockState(pos.down());
        if (!blockState.isSideSolidFullSquare(world, pos, Direction.UP)|| blockState.isOf(Blocks.HOPPER)){
            world.breakBlock(pos,false);
        }
        super.neighborUpdate(state, world, pos, sourceBlock, sourcePos, notify);
    }

    public static VoxelShape SHAPE = Block.createCuboidShape(0,0,0,16,0.02f,16);

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (this.isSliding(pos, entity)) {
            this.addCollisionEffects(world, entity);
        }
        super.onEntityCollision(state, world, pos, entity);
    }

    private boolean isSliding(BlockPos pos, Entity entity) {
        if (entity.getY() > (double)pos.getY() + 0.9375 - 1.0E-7) {
            return false;
        }
        if (entity.getVelocity().y >= -0.08) {
            return false;
        }
        double d = Math.abs((double)pos.getX() + 0.5 - entity.getX());
        double e = Math.abs((double)pos.getZ() + 0.5 - entity.getZ());
        double f = 0.4375 + (double)(entity.getWidth() / 2.0f);
        return d + 1.0E-7 > f || e + 1.0E-7 > f;
    }
    private void addCollisionEffects(World world, Entity entity) {
            if (world.random.nextInt(5) == 0) {
                entity.playSound(SoundEvents.BLOCK_HONEY_BLOCK_SLIDE, 1.0f, 1.0f);
            }

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


    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {

        return super.onUse(state, world, pos, player, hand, hit);
    }

    public void randomSpawnParticle(World world, BlockPos pos, ParticleEffect particle) {
        Random random = world.getRandom();
        world.addImportantParticle(particle,
                true,
                (double) pos.getX() + 0.5 + random.nextDouble() / 3.0 * (double) (random.nextBoolean() ? 1 : -1),
                (double) pos.getY() + random.nextDouble() + random.nextDouble(),
                (double) pos.getZ() + 0.5 + random.nextDouble() / 3.0 * (double) (random.nextBoolean() ? 1 : -1),
                0.03 * (double) (random.nextBoolean() ? 1 : -1),
                0.07,
                0.03 * (double) (random.nextBoolean() ? 1 : -1));

    }
}
