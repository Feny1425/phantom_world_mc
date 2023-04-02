package net.feny.phantom_world.block;


import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.feny.phantom_world.PhantomWorld;
import net.feny.phantom_world.block.models.PhantomBookHolderBlock;
import net.feny.phantom_world.block.models.PillarBlockWithParticles;
import net.feny.phantom_world.item.ModItemGroup;
import net.feny.phantom_world.world.tree.PhantomSaplingGenerator;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class ModBlocks {
    public static final Block FERO_BLOCK = registerBlock("fero_block",
            new Block(FabricBlockSettings.of(Material.METAL).strength(4.0f).requiresTool()), ModItemGroup.FERO);

    public static final Block FERO_ORE = registerBlock("fero_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.of(Material.STONE).strength(4.0f).requiresTool(),
                    UniformIntProvider.create(2, 6)), ModItemGroup.FERO);
    public static final Block DEEPSLATE_FERO_ORE = registerBlock("deepslate_fero_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.of(Material.STONE).strength(4.0f).requiresTool(),
                    UniformIntProvider.create(2, 6)), ModItemGroup.FERO);
    public static final Block PHANTOM_BOOK_HOLDER = registerBlock("phantom_book_holder",
            new PhantomBookHolderBlock(FabricBlockSettings.of(Material.METAL).strength(2.0f).nonOpaque()), ModItemGroup.FERO);




    //region phantom blocks
    public static final Block STRIPPED_PHANTOM_LOG = registerBlock("stripped_phantom_log",
            new PillarBlockWithParticles(FabricBlockSettings.copyOf(Blocks.STRIPPED_ACACIA_LOG).strength(4.0f).requiresTool().luminance(10), ParticleTypes.SOUL, ParticleTypes.SOUL, true), ModItemGroup.FERO);
    public static final Block PHANTOM_WOOD = registerBlock("phantom_wood",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.ACACIA_WOOD).strength(4.0f).requiresTool().luminance(2)), ModItemGroup.FERO);
    public static final Block STRIPPED_PHANTOM_WOOD = registerBlock("stripped_phantom_wood",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_ACACIA_WOOD).strength(4.0f).requiresTool().luminance(10)), ModItemGroup.FERO);
    public static final Block PHANTOM_LOG = registerBlock("phantom_log",
            new PillarBlockWithParticles(FabricBlockSettings.copyOf(Blocks.ACACIA_LOG).strength(4.0f).requiresTool().luminance(3), ParticleTypes.SOUL, ParticleTypes.SOUL, false), ModItemGroup.FERO);

    public static final Block PHANTOM_LEAVES = registerBlock("phantom_leaves",
            new LeavesBlock(FabricBlockSettings.copyOf(Blocks.ACACIA_LEAVES).strength(4.0f).requiresTool().luminance(10)), ModItemGroup.FERO);
    public static final Block PHANTOM_SAPLING = registerBlock("phantom_sapling",
            new SaplingBlock(new PhantomSaplingGenerator(),FabricBlockSettings.copyOf(Blocks.ACACIA_SAPLING).strength(4.0f).requiresTool().luminance(4)), ModItemGroup.FERO);
    public static final Block PHANTOM_PLANKS = registerBlock("phantom_planks",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.ACACIA_PLANKS).strength(4.0f).requiresTool().luminance(10)), ModItemGroup.FERO);

    //endregion




    private static Block registerBlock(String name, Block block, ItemGroup group) {
        registerBlockItem(name, block, group);
        return Registry.register(Registries.BLOCK, new Identifier(PhantomWorld.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block, ItemGroup group) {
        Item item = Registry.register(Registries.ITEM, new Identifier(PhantomWorld.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));
        return item;
    }

    public static void registerModBlocks() {
        PhantomWorld.LOGGER.info("Registering ModBlocks for " + PhantomWorld.MOD_ID);
    }
}