package net.feny.phantom_world.item;

import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.feny.phantom_world.PhantomWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.KillCommand;
import net.minecraft.stat.Stats;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class PhantomBookItem extends Item {
    public static final int TICKS_PER_SECOND = 20;
    private Boolean usedOnEntity = false;
    private LivingEntity entity = null;
    public PhantomBookItem(Settings settings) {
        super(settings);
    }


    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity _entity, Hand hand) {

            entity = _entity;
            usedOnEntity = true;

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
                entity.kill();
                usedOnEntity = false;
                entity = null;
            }
        }
        super.usageTick(world, user, stack, remainingUseTicks);
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        usedOnEntity = false;
        entity = null;
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
        ItemStack itemStack = user.getStackInHand(hand);
        user.setCurrentHand(hand);
        return TypedActionResult.consume(itemStack);
    }

}
