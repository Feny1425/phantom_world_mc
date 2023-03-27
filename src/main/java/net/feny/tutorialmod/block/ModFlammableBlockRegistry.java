package net.feny.tutorialmod.block;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;

public class ModFlammableBlockRegistry {
    public static void registerFlammableBlocks(){
        FlammableBlockRegistry registry = FlammableBlockRegistry.getDefaultInstance();

        registry.add(ModBlocks.PHANTOM_PLANKS,5,20);
        registry.add(ModBlocks.PHANTOM_LEAVES,30,60);
        registry.add(ModBlocks.PHANTOM_WOOD,5,20);
        registry.add(ModBlocks.STRIPPED_PHANTOM_WOOD,5,5);
        registry.add(ModBlocks.STRIPPED_PHANTOM_LOG,5,5);
        registry.add(ModBlocks.PHANTOM_LOG,5,5);
        registry.add(ModBlocks.PHANTOM_SAPLING,30,60);
    }
}
