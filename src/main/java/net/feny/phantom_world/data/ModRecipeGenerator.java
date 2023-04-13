package net.feny.phantom_world.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.feny.phantom_world.PhantomWorld;
import net.feny.phantom_world.block.ModBlocks;
import net.feny.phantom_world.item.ModItems;
import net.feny.phantom_world.util.ModBlockTags;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
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


        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.PHANTOM_WORLD_STARTER,1)
                .pattern("DDD")
                .pattern("SBS")
                .pattern("SSS")
                .input('S', ModItems.FERO)
                .input('D', ModBlocks.FERO_BLOCK)
                .input('B', Items.BOOK)
                .criterion(FabricRecipeProvider.hasItem(ModItems.PHANTOM_STARTER_RECIPE),
                        FabricRecipeProvider.conditionsFromItem(ModItems.PHANTOM_STARTER_RECIPE))
                .offerTo(exporter, new Identifier(PhantomWorld.MOD_ID,FabricRecipeProvider.getRecipeName(ModItems.PHANTOM_WORLD_STARTER)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModBlocks.FERO_SHEET,3)
                .pattern("DDD")
                .input('D', ModItems.FERO)
                .criterion(FabricRecipeProvider.hasItem(ModItems.FERO),
                        FabricRecipeProvider.conditionsFromItem(ModItems.FERO))
                .offerTo(exporter, new Identifier(PhantomWorld.MOD_ID,FabricRecipeProvider.getRecipeName(ModBlocks.FERO_SHEET)));

        phantom(exporter);
        armor(exporter);
    }
    private static void armor(Consumer<RecipeJsonProvider> exporter){
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.FERO_HELMET,1)
                .pattern("DDD")
                .pattern("D D")
                .input('D', ModItems.FERO)
                .criterion(FabricRecipeProvider.hasItem(ModItems.FERO),
                        FabricRecipeProvider.conditionsFromItem(ModItems.FERO))
                .offerTo(exporter, new Identifier(PhantomWorld.MOD_ID,FabricRecipeProvider.getRecipeName(ModItems.FERO_HELMET)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.FERO_CHESTPLATE,1)
                .pattern("D D")
                .pattern("DDD")
                .pattern("DDD")
                .input('D', ModItems.FERO)
                .criterion(FabricRecipeProvider.hasItem(ModItems.FERO),
                        FabricRecipeProvider.conditionsFromItem(ModItems.FERO))
                .offerTo(exporter, new Identifier(PhantomWorld.MOD_ID,FabricRecipeProvider.getRecipeName(ModItems.FERO_CHESTPLATE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.FERO_LEGGINGS,1)
                .pattern("DDD")
                .pattern("D D")
                .pattern("D D")
                .input('D', ModItems.FERO)
                .criterion(FabricRecipeProvider.hasItem(ModItems.FERO),
                        FabricRecipeProvider.conditionsFromItem(ModItems.FERO))
                .offerTo(exporter, new Identifier(PhantomWorld.MOD_ID,FabricRecipeProvider.getRecipeName(ModItems.FERO_LEGGINGS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.FERO_BOOTS,1)
                .pattern("D D")
                .pattern("D D")
                .input('D', ModItems.FERO)
                .criterion(FabricRecipeProvider.hasItem(ModItems.FERO),
                        FabricRecipeProvider.conditionsFromItem(ModItems.FERO))
                .offerTo(exporter, new Identifier(PhantomWorld.MOD_ID,FabricRecipeProvider.getRecipeName(ModItems.FERO_BOOTS)));

    }

    private static void phantom(Consumer<RecipeJsonProvider> exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PHANTOM_WOOD,3)
                .pattern("SS")
                .pattern("SS")
                .input('S', ModBlocks.PHANTOM_LOG)
                .criterion(FabricRecipeProvider.hasItem(ModBlocks.PHANTOM_LOG),
                        FabricRecipeProvider.conditionsFromItem(ModBlocks.PHANTOM_LOG))
                .offerTo(exporter, new Identifier(PhantomWorld.MOD_ID,FabricRecipeProvider.getRecipeName(ModBlocks.PHANTOM_WOOD)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.STRIPPED_PHANTOM_WOOD,3)
                .pattern("SS")
                .pattern("SS")
                .input('S', ModBlocks.STRIPPED_PHANTOM_LOG)
                .criterion(FabricRecipeProvider.hasItem(ModBlocks.STRIPPED_PHANTOM_LOG),
                        FabricRecipeProvider.conditionsFromItem(ModBlocks.STRIPPED_PHANTOM_LOG))
                .offerTo(exporter, new Identifier(PhantomWorld.MOD_ID,FabricRecipeProvider.getRecipeName(ModBlocks.STRIPPED_PHANTOM_WOOD)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS,ModBlocks.PHANTOM_PLANKS,4)
                .input(ModBlockTags.PHANTOM_LOGS)
                .criterion(FabricRecipeProvider.hasItem(ModBlocks.PHANTOM_LOG),
                        FabricRecipeProvider.conditionsFromItem(ModBlocks.PHANTOM_LOG))
                .criterion(FabricRecipeProvider.hasItem(ModBlocks.STRIPPED_PHANTOM_LOG),
                        FabricRecipeProvider.conditionsFromItem(ModBlocks.STRIPPED_PHANTOM_LOG))
                .offerTo(exporter,new Identifier(PhantomWorld.MOD_ID,FabricRecipeProvider.getRecipeName(ModBlocks.PHANTOM_PLANKS)));
    }
}
