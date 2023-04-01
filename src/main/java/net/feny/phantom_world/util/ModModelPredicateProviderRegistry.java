/*
 * Decompiled with CFR 0.1.1 (FabricMC 57d88659).
 */
package net.feny.phantom_world.util;

import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.feny.phantom_world.PhantomWorld;
import net.feny.phantom_world.item.ModItems;
import net.minecraft.client.item.CompassAnglePredicateProvider;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class ModModelPredicateProviderRegistry {
    public static void registerModModel() {
        registerPhantomBook(ModItems.PHANTOM_WORLD_STARTER);
    }

    private static void registerPhantomBook(Item book) {

        FabricModelPredicateProviderRegistry.register(book,new Identifier("open"),
                (stack, world, entity, seed) -> entity != null && entity.isUsingItem()
                            && entity.getActiveItem() == stack ? 1.0f : 0.0f);

    }
}


