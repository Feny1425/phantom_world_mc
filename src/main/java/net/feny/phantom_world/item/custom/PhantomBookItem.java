package net.feny.phantom_world.item.custom;

import net.feny.phantom_world.PhantomWorld;
import net.feny.phantom_world.block.ModBlocks;
import net.feny.phantom_world.item.client.PhantomBookItemRenderer;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.block.pattern.BlockPatternBuilder;
import net.minecraft.block.pattern.CachedBlockPosition;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
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
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.RenderProvider;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.RenderUtils;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class PhantomBookItem extends Item implements GeoItem {
    public static final String BOOK_PROGRESS = "book_progress";
    public static final int MAX_BOOK_PROGRESS = 7;
    private static String ID = null;
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


 @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity _entity, Hand hand) {
        if(stack.hasNbt()){
            //bookProgress = stack.getNbt().getInt(BOOK_PROGRESS);
        }
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
        int start = 380;
        if (startEarthquake && center != null){
            tick++;
            if (tick < start+5) {
                openCenter(world,center,1,1);
            } else if (tick < start +7) {
                openCenter(world,center,1,2);
                openCenter(world,center,2,1);
            }else if (tick < start+10) {
                openCenter(world,center,1, 4);
                openCenter(world,center,1, 3);
                openCenter(world,center,2, 2);
                openCenter(world,center,3, 1);
            }else if (tick < start+14) {
                startEarthquake = false;
                tick = 0;
            }


        }
        else {
            startEarthquake = false;
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }


    private void openCenter(World world, BlockPos blockPos, int i, int i1) {
        BlockPos pos = blockPos;
        for (int k = 1; k < i; k++){
            pos = pos.down();
        }
        setTwoBlocksAir(world, pos.add(i1, i1==4?1:0, 0));
        setTwoBlocksAir(world, pos.add(-i1, i1==4?1:0, 0));
        setTwoBlocksAir(world, pos.add(0, i1==4?1:0, i1));
        setTwoBlocksAir(world, pos.add(0, i1==4?1:0, -i1));
        if (i1<4){
            setTwoBlocksAir(world, pos.add((i1-1), (i1-2), (i1-1)));
            setTwoBlocksAir(world, pos.add(-(i1-1), (i1-2), (i1-1)));
            setTwoBlocksAir(world, pos.add((i1-1), (i1-2), -(i1-1)));
            setTwoBlocksAir(world, pos.add(-(i1-1), (i1-2), -(i1-1)));
            setTwoBlocksAir(world, pos.add((i1-1), (i1-2), (i1-1)));
            setTwoBlocksAir(world, pos.add(-(i1-1), (i1-2), (i1-1)));
            setTwoBlocksAir(world, pos.add((i1-1), (i1-2), -(i1-1)));
            setTwoBlocksAir(world, pos.add(-(i1-1), (i1-2), -(i1-1)));
        }
        switch (i1) {
            case 1:
                setTwoBlocksAir(world, pos);
                if (i == 3){
                    setPortal(world, pos);
                    setPortal(world, pos.add(1, 0, 0));
                    setPortal(world, pos.add(-1, 0, 0));
                    setPortal(world, pos.add(0, 0, 1));
                    setPortal(world, pos.add(0, 0, -1));
                }
                break;
            case 2:
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
            case 4:
                setTwoBlocksAir(world, pos.add(1, 1, 3));
                setTwoBlocksAir(world, pos.add(-1, 1, 3));
                setTwoBlocksAir(world, pos.add(1, 1, -3));
                setTwoBlocksAir(world, pos.add(-1, 1, -3));
                setTwoBlocksAir(world, pos.add(3, 1, -1));
                setTwoBlocksAir(world, pos.add(3, 1, 1));
                setTwoBlocksAir(world, pos.add(-3, 1, -1));
                setTwoBlocksAir(world, pos.add(-3, 1, 1));



        }
    }

    private void setTwoBlocksAir(World world,BlockPos pos){
        world.setBlockState(pos.down().down(), ModBlocks.PHANTOM_MAGMA.getDefaultState(), Block.NOTIFY_LISTENERS);
        world.removeBlock(pos,true);
        world.removeBlock(pos.down(),true);

    }
    private void setPortal(World world,BlockPos pos){
        world.setBlockState(pos.down(), ModBlocks.PHANTOM_PORTAL.getDefaultState(), Block.NOTIFY_LISTENERS);

    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (usedOnEntity) {

        }

        if (usedOnBlock && context != null && stack.hasNbt())
            if (context.getWorld().getBlockState(context.getBlockPos()).getBlock() == ModBlocks.FERO_SHEET && stack.getNbt().getInt(BOOK_PROGRESS) == MAX_BOOK_PROGRESS) {
                if (result != null && tick < 365) {
                    tick++;

                    if (360 > tick && (tick %40 == 0||tick < 10)) {
                        world.playSound(
                                null, // Player - if non-null, will play sound for every nearby player *except* the specified player
                                (center = result.getFrontTopLeft().add(-4,0,-4)), // The position of where the sound will come from
                                PhantomWorld.HUM_EVENT, // The sound that will play
                                SoundCategory.BLOCKS, // This determines which of the volume sliders affect this sound
                                tick/300f, //Volume multiplier, 1 is normal, 0.5 is half volume, etc
                                1f // Pitch multiplier, 1 is normal, 0.5 is half pitch, etc
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
                            startEarthquake = true;
                            world.playSound(
                                    null, // Player - if non-null, will play sound for every nearby player *except* the specified player
                                    result.getFrontTopLeft().add(-4,0,-4), // The position of where the sound will come from
                                    PhantomWorld.EARTHQUAKE_EVENT, // The sound that will play
                                    SoundCategory.BLOCKS, // This determines which of the volume sliders affect this sound
                                    5f, //Volume multiplier, 1 is normal, 0.5 is half volume, etc
                                    1f // Pitch multiplier, 1 is normal, 0.5 is half pitch, etc
                            );
                            LightningEntity lightningEntity = (LightningEntity) EntityType.LIGHTNING_BOLT.create(world);
                            lightningEntity.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(center.down(4)));
                            world.spawnEntity(lightningEntity);
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
        ((PhantomBookItem)stack.getItem()).isUsing =false;

        super.onStoppedUsing(stack, world, user, remainingUseTicks);
    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ServerWorld serverWorld;
        BlockPos blockPos;
        ((PhantomBookItem)user.getStackInHand(hand).getItem()).isUsing =true;

        ItemStack itemStack = user.getStackInHand(hand);
        BlockHitResult hitResult = EnderEyeItem.raycast(world, user, RaycastContext.FluidHandling.NONE);
        if (((HitResult)hitResult).getType() == HitResult.Type.BLOCK && world.getBlockState(hitResult.getBlockPos()).isOf(ModBlocks.PHANTOM_BOOK_HOLDER)) {
            return TypedActionResult.pass(itemStack);
        }
        user.setCurrentHand(hand);
        return TypedActionResult.consume(itemStack);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return super.getUseAction(stack);
    }

    @Override
    public boolean shouldPlayAnimsWhileGamePaused() {
        return false;
    }


    @Override
    public boolean hasGlint(ItemStack stack) {
        return stack.hasNbt();
    }
    private static BlockPattern COMPLETED_DRAW;

    public static BlockPattern getCompletedFramePattern() {
        if (COMPLETED_DRAW == null) {
            COMPLETED_DRAW = BlockPatternBuilder.start().aisle(
                    "A??v?v??A",
                            "?v?vvv?v?",
                            "????v????",
                            "vv?v?v?vv",
                            "?vv?^?vv?",
                            "vv?v?v?vv",
                            "????v????",
                            "?v?vvv?v?",
                            "A??v?v??A")
                    .where('?', CachedBlockPosition.matchesBlockState(BlockStatePredicate.forBlock(Blocks.AIR)))
                    .where('^', CachedBlockPosition.matchesBlockState(BlockStatePredicate.forBlock(ModBlocks.FERO_SHEET)))
                    .where('v', CachedBlockPosition.matchesBlockState(BlockStatePredicate.forBlock(ModBlocks.BLOOD)))
                    .where('A', CachedBlockPosition.matchesBlockState(BlockStatePredicate.ANY))
                    .build();
        }
        return COMPLETED_DRAW;
    }




    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    private final Supplier<Object> renderProvider = GeoItem.makeRenderer(this);
    @Override
    public void createRenderer(Consumer<Object> consumer) {
        consumer.accept(new RenderProvider() {
            private final PhantomBookItemRenderer renderer = new PhantomBookItemRenderer();

            @Override
            public BuiltinModelItemRenderer getCustomRenderer() {
                return this.renderer;
            }
        });
    }

    @Override
    public Supplier<Object> getRenderProvider() {
        return renderProvider;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller",0,this::predicate));
    }

    boolean isUsing;
    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> tAnimationState) {
        if (this.isUsing){
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("open", Animation.LoopType.PLAY_ONCE).thenLoop("opened"));
        }
        else {
            tAnimationState.getController().stop();
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
        }
        return PlayState.CONTINUE;
    }

    @Override
    public double getTick(Object itemStack) {
        return RenderUtils.getCurrentTick();
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
