package net.feny.tutorialmod.block;

import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;

public class ModStrippedBlockRegistry {
    public static void registerStrippedBlocks() {
        StrippableBlockRegistry.register(ModBlocks.PHANTOM_LOG, ModBlocks.STRIPPED_PHANTOM_LOG);
        StrippableBlockRegistry.register(ModBlocks.PHANTOM_WOOD, ModBlocks.STRIPPED_PHANTOM_WOOD);
    }
}
