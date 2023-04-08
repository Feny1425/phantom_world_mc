package net.feny.phantom_world.item.custom;

import net.feny.phantom_world.PhantomWorld;
import net.feny.phantom_world.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.block.pattern.BlockPatternBuilder;
import net.minecraft.block.pattern.CachedBlockPosition;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.EnderEyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.predicate.block.BlockStatePredicate;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

public class PhantomBookItem extends Item {
    public static final int TICKS_PER_SECOND = 20;
    public static final String BOOK_PROGRESS = "book_progress";
    private int bookProgress = 1;
    public static final int MAX_BOOK_PROGRESS = 8;
    private Boolean usedOnEntity = false;
    private Boolean usedOnBlock = false;
    private ItemUsageContext context;
    private int tick = 0;
    private LivingEntity entity = null;
    private boolean startEarthquake;
    private BlockPos center;
    public PhantomBookItem(Settings settings) {
        super(settings);
    }


 /*   @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockPos blockPos;
        ItemStack stack = context.getStack();
        World world = context.getWorld();
        BlockState blockState = world.getBlockState(blockPos = context.getBlockPos());
        if (!blockState.isOf(ModBlocks.PHANTOM_BOOK_HOLDER) || blockState.get(PhantomBookHolderBlock.HAS_BOOK).booleanValue()) {
            return ActionResult.PASS;
        }
        if (world.isClient) {
            return ActionResult.SUCCESS;
        }
        if(stack.hasNbt()){
            bookProgress = stack.getNbt().getInt("book_progress");
        }
        BlockState blockState2 = (BlockState)blockState.with(PhantomBookHolderBlock.HAS_BOOK, true).with(PhantomBookHolderBlock.BOOK_PROGRESS, bookProgress);
        Block.pushEntitiesUpBeforeBlockChange(blockState, blockState2, world, blockPos);
        world.setBlockState(blockPos, blockState2, Block.NOTIFY_LISTENERS);
        world.updateComparators(blockPos, ModBlocks.PHANTOM_BOOK_HOLDER);
        context.getStack().decrement(1);
        world.syncWorldEvent(5000, blockPos, 0);
        return ActionResult.CONSUME;
    }
*/
    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity _entity, Hand hand) {
        if(stack.hasNbt()){
            bookProgress = stack.getNbt().getInt(BOOK_PROGRESS);
        }
        user.sendMessage(Text.of(String.valueOf(bookProgress)),true);
            entity = _entity;
            usedOnEntity = true;

        spawnParticles(user.world,_entity.getBlockPos());
        return super.useOnEntity(stack, user, _entity, hand);
    }

    BlockPattern.Result result = null;
    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        this.context = context;
        usedOnBlock = true;

        result = getCompletedFramePattern().searchAround(context.getWorld(), context.getBlockPos());

        return super.useOnBlock(context);
    }


    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 2_000_000_000;
    }

    //      /clone 83 -61 77 75 -60 85 87 -60 40
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (startEarthquake && center != null){
            tick++;
            if (tick < 370) {
                openCenter(world,center,1,1);
            } else if (tick < 375) {
                openCenter(world,center,1,2);
                openCenter(world,center,2,1);
            }else if (tick < 380) {
                openCenter(world,center,1, 3);
                openCenter(world,center,2, 2);
                openCenter(world,center,3, 1);
            }else if (tick < 420) {
                openThePortal(world,center);
                startEarthquake = false;
                tick = 0;
            }


        }
        else {
            startEarthquake = false;
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    private void openThePortal(World world, BlockPos center) {
    }

    private void openCenter(World world, BlockPos blockPos, int i, int i1) {
        BlockPos pos = blockPos;
        for (int k = 1; k < i; k++){
            pos = pos.down();
        }
        setTwoBlocksAir(world, pos.add(i1, 0, 0));
        setTwoBlocksAir(world, pos.add(-i1, 0, 0));
        setTwoBlocksAir(world, pos.add(0, 0, i1));
        setTwoBlocksAir(world, pos.add(0, 0, -i1));
        switch (i1) {
            case 1:
                setTwoBlocksAir(world, pos);
                break;
            case 2:
                setTwoBlocksAir(world, pos.add(1, 0, 1));
                setTwoBlocksAir(world, pos.add(-1, 0, 1));
                setTwoBlocksAir(world, pos.add(1, 0, -1));
                setTwoBlocksAir(world, pos.add(-1, 0, -1));
                setTwoBlocksAir(world, pos.add(1, 0, 1));
                setTwoBlocksAir(world, pos.add(-1, 0, 1));
                setTwoBlocksAir(world, pos.add(1, 0, -1));
                setTwoBlocksAir(world, pos.add(-1, 0, -1));
                break;
            case 3:
                setTwoBlocksAir(world, pos.add(2, 0, 1));
                setTwoBlocksAir(world, pos.add(-2, 0, 1));
                setTwoBlocksAir(world, pos.add(2, 0, -1));
                setTwoBlocksAir(world, pos.add(-2, 0, -1));
                setTwoBlocksAir(world, pos.add(1, 0, 2));
                setTwoBlocksAir(world, pos.add(-1, 0, 2));
                setTwoBlocksAir(world, pos.add(1, 0, -2));
                setTwoBlocksAir(world, pos.add(-1, 0, -2));
                break;

        }
    }

    private void setTwoBlocksAir(World world,BlockPos pos){
        world.setBlockState(pos.down().down(), Blocks.MAGMA_BLOCK.getDefaultState(), Block.NOTIFY_LISTENERS);
        world.removeBlock(pos,true);
        world.removeBlock(pos.down(),true);

    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (usedOnEntity) {

        }

        if (usedOnBlock && context != null)
            if (context.getWorld().getBlockState(context.getBlockPos()).getBlock() == ModBlocks.FERO_SHEET) {
                if (result != null && !startEarthquake) {
                    tick++;
                    LightningEntity lightningEntity = (LightningEntity) EntityType.LIGHTNING_BOLT.create(world);
                    if (360 > tick && (tick %40 == 0||tick < 10)) {
                        world.playSound(
                                null, // Player - if non-null, will play sound for every nearby player *except* the specified player
                                (center = result.getFrontTopLeft().add(-4,0,-4)).down(), // The position of where the sound will come from
                                PhantomWorld.HUM_EVENT, // The sound that will play
                                SoundCategory.BLOCKS, // This determines which of the volume sliders affect this sound
                                tick/360.0f, //Volume multiplier, 1 is normal, 0.5 is half volume, etc
                                0.7f // Pitch multiplier, 1 is normal, 0.5 is half pitch, etc
                        );
                    }
                    if (120 > tick && tick > 10) {
                        randomSpawnParticle(world,result.getFrontTopLeft().add(-4,0,-4),ParticleTypes.SOUL);
                    }
                    else if (240 > tick && tick > 130) {
                        randomSpawnParticle(world,result.getFrontTopLeft().add(-3,0,-3),ParticleTypes.SOUL);
                        randomSpawnParticle(world,result.getFrontTopLeft().add(-5,0,-3),ParticleTypes.SOUL);
                        randomSpawnParticle(world,result.getFrontTopLeft().add(-3,0,-5),ParticleTypes.SOUL);
                        randomSpawnParticle(world,result.getFrontTopLeft().add(-5,0,-5),ParticleTypes.SOUL);

                    } else if (350 > tick && tick > 250) {
                        randomSpawnParticle(world,result.getFrontTopLeft().add(0,0,-4),ParticleTypes.SOUL);
                        randomSpawnParticle(world,result.getFrontTopLeft().add(-4,0,0),ParticleTypes.SOUL);
                        randomSpawnParticle(world,result.getFrontTopLeft().add(-8,0,-4),ParticleTypes.SOUL);
                        randomSpawnParticle(world,result.getFrontTopLeft().add(-4,0,-8),ParticleTypes.SOUL);

                        randomSpawnParticle(world,result.getFrontTopLeft().add(-1,0,-1),ParticleTypes.SOUL);
                        randomSpawnParticle(world,result.getFrontTopLeft().add(-1,0,-7),ParticleTypes.SOUL);
                        randomSpawnParticle(world,result.getFrontTopLeft().add(-7,0,-1),ParticleTypes.SOUL);
                        randomSpawnParticle(world,result.getFrontTopLeft().add(-7,0,-7),ParticleTypes.SOUL);

                    }
                    else if (360 < tick) {
                        if (!world.isClient && !startEarthquake) {
                            lightningEntity.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(result.getFrontTopLeft().add(0,0,-4)));
                            world.spawnEntity(lightningEntity);
                            lightningEntity.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(result.getFrontTopLeft().add(-4,0,0)));
                            world.spawnEntity(lightningEntity);
                            lightningEntity.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(result.getFrontTopLeft().add(-8,0,-4)));
                            world.spawnEntity(lightningEntity);
                            lightningEntity.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(result.getFrontTopLeft().add(-4,0,-8)));
                            world.spawnEntity(lightningEntity);
                            lightningEntity.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(result.getFrontTopLeft().add(-1,0,-1)));
                            world.spawnEntity(lightningEntity);
                            lightningEntity.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(result.getFrontTopLeft().add(-1,0,-7)));
                            world.spawnEntity(lightningEntity);
                            lightningEntity.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(result.getFrontTopLeft().add(-7,0,-1)));
                            world.spawnEntity(lightningEntity);
                            lightningEntity.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(result.getFrontTopLeft().add(-7,0,-7)));
                            world.spawnEntity(lightningEntity);
                            startEarthquake = true;
                            world.playSound(
                                    null, // Player - if non-null, will play sound for every nearby player *except* the specified player
                                    result.getFrontTopLeft().add(-4,0,-4).down(), // The position of where the sound will come from
                                    PhantomWorld.EARTHQUAKE_EVENT, // The sound that will play
                                    SoundCategory.BLOCKS, // This determines which of the volume sliders affect this sound
                                    5f, //Volume multiplier, 1 is normal, 0.5 is half volume, etc
                                    1f // Pitch multiplier, 1 is normal, 0.5 is half pitch, etc
                            );
                        }
                    }
                }
                else {
                    return;
                }

            }
        super.usageTick(world, user, stack, remainingUseTicks);
    }

    private void spawnParticles(World world, BlockPos pos){
        world.addParticle(ParticleTypes.SOUL,
                pos.getX(),
                pos.getY(),
                pos.getZ(),
                0.05 * (double) (world.random.nextBoolean() ? 1 : -1),
                0.02,
                0.05 * (double) (world.random.nextBoolean() ? 1 : -1));
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

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        usedOnEntity = false;
        usedOnBlock = false;
        context = null;
        entity = null;
        result= null;
        tick = 0;

        /*
        if(stack.hasNbt()) {
            bookProgress = stack.getNbt().getInt("book_progress");
        }
        if (bookProgress < MAX_BOOK_PROGRESS)
            bookProgress++;
        NbtCompound nbt = new NbtCompound();
        nbt.putInt("book_progress",bookProgress);
        stack.setNbt(nbt);*/
        super.onStoppedUsing(stack, world, user, remainingUseTicks);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.NONE;
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ServerWorld serverWorld;
        BlockPos blockPos;
        ItemStack itemStack = user.getStackInHand(hand);
        BlockHitResult hitResult = EnderEyeItem.raycast(world, user, RaycastContext.FluidHandling.NONE);
        if (((HitResult)hitResult).getType() == HitResult.Type.BLOCK && world.getBlockState(hitResult.getBlockPos()).isOf(ModBlocks.PHANTOM_BOOK_HOLDER)) {
            return TypedActionResult.pass(itemStack);
        }
        user.setCurrentHand(hand);
        return TypedActionResult.consume(itemStack);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return stack.hasNbt();
    }
    private static BlockPattern COMPLETED_DRAW;

    public static BlockPattern getCompletedFramePattern() {
        if (COMPLETED_DRAW == null) {
            COMPLETED_DRAW = BlockPatternBuilder.start().aisle(
                    "???v?v???",
                            "?v?vvv?v?",
                            "????v????",
                            "vv?v?v?vv",
                            "?vv?^?vv?",
                            "vv?v?v?vv",
                            "????v????",
                            "?v?vvv?v?",
                            "???v?v???")
                    .where('?', CachedBlockPosition.matchesBlockState(BlockStatePredicate.forBlock(Blocks.AIR)))
                    .where('^', CachedBlockPosition.matchesBlockState(BlockStatePredicate.forBlock(ModBlocks.FERO_SHEET)))
                    .where('v', CachedBlockPosition.matchesBlockState(BlockStatePredicate.forBlock(ModBlocks.BLOOD)))
                    .build();
        }
        return COMPLETED_DRAW;
    }
}
