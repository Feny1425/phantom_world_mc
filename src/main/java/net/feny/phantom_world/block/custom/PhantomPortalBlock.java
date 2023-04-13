package net.feny.phantom_world.block.custom;

import net.feny.phantom_world.block.ModBlocks;
import net.feny.phantom_world.block.entity.PhantomPortalBlockEntity;
import net.feny.phantom_world.world.dimension.ModDimensions;
import net.kyrptonaught.customportalapi.portal.frame.PortalFrameTester;
import net.kyrptonaught.customportalapi.util.CustomTeleporter;
import net.minecraft.block.BlockState;
import net.minecraft.block.EndPortalBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.World;

public class PhantomPortalBlock extends EndPortalBlock {
    public PhantomPortalBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new PhantomPortalBlockEntity(pos, state);
    }
    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (entity.isPlayer())
        if (world instanceof ServerWorld && entity.canUsePortals() && VoxelShapes.matchesAnywhere(VoxelShapes.cuboid(entity.getBoundingBox().offset(-pos.getX(), -pos.getY(), -pos.getZ())), state.getOutlineShape(world, pos), BooleanBiFunction.AND)) {
            MinecraftServer server = world.getServer();
            ServerPlayerEntity player = (ServerPlayerEntity) entity;
            ServerWorld serverWorld = server.getWorld(world.getRegistryKey() == ModDimensions.PHANTOM_DIMENSION_KEY? World.OVERWORLD:ModDimensions.PHANTOM_DIMENSION_KEY);
            if (serverWorld != null){
                BlockPos destPos = getDest(pos,serverWorld,world.getRegistryKey() == ModDimensions.PHANTOM_DIMENSION_KEY);
                player.teleport(serverWorld,destPos.getX(),destPos.getY(), destPos.getZ(),player.bodyYaw,player.prevYaw);
                for (int i = -1;i<2;i++){
                    for (int k = -1 ; k < 2;k++)
                    serverWorld.setBlockState(destPos.down().add(i,0,k), ModBlocks.STRIPPED_PHANTOM_WOOD.getDefaultState());
                }
            }
        }
    }
    public static BlockPos getDest(BlockPos pos, World destWorld, boolean isInDimension) {
        int y = 61;

        if (!isInDimension) {
            y = pos.getY();
        }

        BlockPos destPos = new BlockPos(pos.getX(), y, pos.getZ());
        int tries = 0;
        while (!(destWorld.getBlockState(destPos).isAir() && destWorld.getBlockState(destPos.up()).isAir() && !destWorld.getBlockState(destPos.down()).isAir()) && tries < 1000) {
            destPos = destWorld.getBlockState(destPos.down()).isAir()? destPos.down():destPos.up(2);
            tries++;
        }

        return destPos;
    }
    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        double d = (double)pos.getX() + random.nextDouble();
        double e = (double)pos.getY() + 0.8;
        double f = (double)pos.getZ() + random.nextDouble();
        world.addParticle(ParticleTypes.SOUL, d, e, f, 0.0, 0.0, 0.0);
    }
}
