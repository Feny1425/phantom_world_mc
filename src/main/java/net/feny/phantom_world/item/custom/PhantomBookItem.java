package net.feny.phantom_world.item.custom;

import net.feny.phantom_world.block.ModBlocks;
import net.feny.phantom_world.block.models.PhantomBookHolderBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.EnderEyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

public class PhantomBookItem extends Item {
    public static final int TICKS_PER_SECOND = 20;
    private int bookProgress = 1;
    private int HIGH_BOOK_PROGRESS = 6;
    private Boolean usedOnEntity = false;
    private LivingEntity entity = null;
    public PhantomBookItem(Settings settings) {
        super(settings);
    }


    @Override
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

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity _entity, Hand hand) {
        if(stack.hasNbt()){
            bookProgress = stack.getNbt().getInt("book_progress");
        }
        user.sendMessage(Text.of(String.valueOf(bookProgress)),true);
            entity = _entity;
            usedOnEntity = true;

        spawnParticles(user.world,_entity.getPos());
        return super.useOnEntity(stack, user, _entity, hand);
    }
    public static float getPullProgress(int useTicks) {
        float f = (float)useTicks / 20.0f;
        if ((f = (f * f + f * 2.0f) / 3.0f) > 1.0f) {
            f = 1.0f;
        }
        return f;
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (usedOnEntity){
            if(getPullProgress(this.getMaxUseTime(stack) - remainingUseTicks) > 0.5){
                //entity.kill();
                usedOnEntity = false;
                entity = null;
            }
        }
        super.usageTick(world, user, stack, remainingUseTicks);
    }

    private void spawnParticles(World world, Vec3d pos){
        world.addParticle(ParticleTypes.SOUL,
                pos.getX(),
                pos.getY(),
                pos.getZ(),
                0.05 * (double) (world.random.nextBoolean() ? 1 : -1),
                0.09,
                0.05 * (double) (world.random.nextBoolean() ? 1 : -1));
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        usedOnEntity = false;
        entity = null;
        if(stack.hasNbt()) {
            bookProgress = stack.getNbt().getInt("book_progress");
        }
        if (bookProgress < HIGH_BOOK_PROGRESS)
            bookProgress++;
        NbtCompound nbt = new NbtCompound();
        nbt.putInt("book_progress",bookProgress);
        stack.setNbt(nbt);
        super.onStoppedUsing(stack, world, user, remainingUseTicks);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.NONE;
    }
    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 72000;
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
}
