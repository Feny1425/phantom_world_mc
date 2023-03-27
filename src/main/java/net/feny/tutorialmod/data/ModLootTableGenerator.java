package net.feny.tutorialmod.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.feny.tutorialmod.block.ModBlocks;
import net.feny.tutorialmod.item.ModItems;

public class ModLootTableGenerator extends FabricBlockLootTableProvider {
    public ModLootTableGenerator(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.FERO_BLOCK);

        addDrop(ModBlocks.FERO_ORE, oreDrops(ModBlocks.FERO_ORE, ModItems.RAW_FERO));
        addDrop(ModBlocks.DEEPSLATE_FERO_ORE, oreDrops(ModBlocks.DEEPSLATE_FERO_ORE, ModItems.RAW_FERO));
    }
}
