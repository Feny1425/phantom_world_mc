package net.feny.phantom_world.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.feny.phantom_world.block.ModBlocks;
import net.feny.phantom_world.item.ModItems;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.ModelIds;
import net.minecraft.data.client.Models;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.FERO_BLOCK);
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.FERO_ORE);
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.DEEPSLATE_FERO_ORE);

        //region phantom tree
        blockStateModelGenerator.registerLog(ModBlocks.STRIPPED_PHANTOM_LOG).log(ModBlocks.STRIPPED_PHANTOM_LOG).wood(ModBlocks.STRIPPED_PHANTOM_WOOD);
        blockStateModelGenerator.registerLog(ModBlocks.PHANTOM_LOG).log(ModBlocks.PHANTOM_LOG).wood(ModBlocks.PHANTOM_WOOD);
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.PHANTOM_LEAVES);
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.PHANTOM_PLANKS);
        blockStateModelGenerator.registerTintableCrossBlockState(ModBlocks.PHANTOM_SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);
        //endregion

        blockStateModelGenerator.registerParentedItemModel(ModItems.SMALL_PHANTOM_SPAWN_EGG, ModelIds.getMinecraftNamespacedItem("template_spawn_egg"));
    }



    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.FERO, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_FERO, Models.GENERATED);
    }
}
