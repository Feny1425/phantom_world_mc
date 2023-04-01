/*
 * Decompiled with CFR 0.1.1 (FabricMC 57d88659).
 */
package net.feny.phantom_world.util;

import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.feny.phantom_world.item.ModItems;
import net.feny.phantom_world.item.StructureCompass;
import net.minecraft.client.item.CompassAnglePredicateProvider;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.item.CompassItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public class ModModelPredicateProviderRegistry {
    public static void registerModModel() {
    }

    private static void registerStructureCompass(Item compass) {
        ModelPredicateProviderRegistry.register(compass, new Identifier("angle"), new CompassAnglePredicateProvider((world, stack, entity) -> StructureCompass.createSpawnPos(world)));
    }
}


