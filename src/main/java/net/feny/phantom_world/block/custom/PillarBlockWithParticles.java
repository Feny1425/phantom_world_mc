package net.feny.phantom_world.block.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class PillarBlockWithParticles extends PillarBlock {

    protected final ParticleEffect particle;
    protected final ParticleEffect particle2;
    protected final Boolean moreParticles;

    /**
     * Access widened by fabric-transitive-access-wideners-v1 to accessible
     */
    public PillarBlockWithParticles(Settings settings, ParticleEffect particle, ParticleEffect particle2, Boolean moreParticles) {
        super(settings);
        this.particle = particle;
        this.particle2 = particle2;
        this.moreParticles = moreParticles;
    }


    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        randomSpawnParticle(world, pos);

    }

    public void randomSpawnParticle(World world, BlockPos pos) {
        Random random = world.getRandom();
        world.addImportantParticle(this.particle,
                true,
                (double) pos.getX() + 0.5 + random.nextDouble() / 3.0 * (double) (random.nextBoolean() ? 1 : -1),
                (double) pos.getY() + random.nextDouble() + random.nextDouble(),
                (double) pos.getZ() + 0.5 + random.nextDouble() / 3.0 * (double) (random.nextBoolean() ? 1 : -1),
                0.03 * (double) (random.nextBoolean() ? 1 : -1),
                0.07,
                0.03 * (double) (random.nextBoolean() ? 1 : -1));
        if (moreParticles){
            world.addImportantParticle(this.particle2,
                    true,
                    (double) pos.getX() + 0.5 + random.nextDouble() / 3.0 * (double) (random.nextBoolean() ? 1 : -1),
                    (double) pos.getY() + random.nextDouble() + random.nextDouble(),
                    (double) pos.getZ() + 0.5 + random.nextDouble() / 3.0 * (double) (random.nextBoolean() ? 1 : -1),
                    0.05 * (double) (random.nextBoolean() ? 1 : -1),
                    0.07,
                    0.05 * (double) (random.nextBoolean() ? 1 : -1));
        }
    }
}

