package net.feny.phantom_world.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.feny.phantom_world.block.ModBlocks;
import net.feny.phantom_world.item.ModItems;

public class ModLootTableGenerator extends FabricBlockLootTableProvider {
    public ModLootTableGenerator(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.FERO_BLOCK);
        addDrop(ModBlocks.PHANTOM_SAPLING);
        addDrop(ModBlocks.PHANTOM_LOG);
        addDrop(ModBlocks.PHANTOM_WOOD);
        addDrop(ModBlocks.PHANTOM_PLANKS);
        addDrop(ModBlocks.STRIPPED_PHANTOM_LOG);
        addDrop(ModBlocks.STRIPPED_PHANTOM_WOOD);
        addDrop(ModBlocks.PHANTOM_BOOK_HOLDER);

        addDrop(ModBlocks.FERO_ORE, oreDrops(ModBlocks.FERO_ORE, ModItems.RAW_FERO));
        addDrop(ModBlocks.DEEPSLATE_FERO_ORE, oreDrops(ModBlocks.DEEPSLATE_FERO_ORE, ModItems.RAW_FERO));
    }
}
