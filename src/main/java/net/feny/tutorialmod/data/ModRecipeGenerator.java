package net.feny.tutorialmod.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.feny.tutorialmod.block.ModBlocks;
import net.feny.tutorialmod.item.ModItems;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeGenerator extends FabricRecipeProvider {
    public ModRecipeGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        offerSmelting(exporter, List.of(ModItems.RAW_FERO), RecipeCategory.MISC, ModItems.FERO,
                0.7f, 200, "fero");

        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.FERO, RecipeCategory.DECORATIONS,
                ModBlocks.FERO_BLOCK);


        phantom(exporter);
    }

    private static void phantom(Consumer<RecipeJsonProvider> exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.PHANTOM_WOOD,4)
                .pattern("SS")
                .pattern("SS")
                .input('S', ModBlocks.PHANTOM_LOG)
                .criterion(FabricRecipeProvider.hasItem(ModBlocks.PHANTOM_LOG),
                        FabricRecipeProvider.conditionsFromItem(ModBlocks.PHANTOM_LOG))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ModBlocks.PHANTOM_WOOD)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.STRIPPED_PHANTOM_WOOD,4)
                .pattern("SS")
                .pattern("SS")
                .input('S', ModBlocks.STRIPPED_PHANTOM_LOG)
                .criterion(FabricRecipeProvider.hasItem(ModBlocks.STRIPPED_PHANTOM_LOG),
                        FabricRecipeProvider.conditionsFromItem(ModBlocks.STRIPPED_PHANTOM_LOG))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ModBlocks.STRIPPED_PHANTOM_WOOD)));
    }
}
