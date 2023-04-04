package net.feny.phantom_world;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.feny.phantom_world.block.ModBlocks;
import net.feny.phantom_world.block.ModFlammableBlockRegistry;
import net.feny.phantom_world.block.ModStrippedBlockRegistry;
import net.feny.phantom_world.block.custom.PhantomBookHolderBlock;
import net.feny.phantom_world.block.entity.ModBlockEntities;
import net.feny.phantom_world.block.entity.PhantomBookHolderEntity;
import net.feny.phantom_world.entity.ModEntities;
import net.feny.phantom_world.entity.custom.SmallPhantom;
import net.feny.phantom_world.item.ModItemGroup;
import net.feny.phantom_world.item.ModItems;
import net.feny.phantom_world.networking.ModMessages;
import net.feny.phantom_world.screen.PhantomBookHolderScreenHandler;
import net.feny.phantom_world.world.dimension.ModDimensions;
import net.feny.phantom_world.world.gen.ModWorldGeneration;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PhantomWorld implements ModInitializer {
	public static final String MOD_ID = "phantom_world";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);




	@Override
	public void onInitialize() {
		ModItemGroup.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModWorldGeneration.generateModWorldGen();
		ModFlammableBlockRegistry.registerFlammableBlocks();
		ModStrippedBlockRegistry.registerStrippedBlocks();
		FabricDefaultAttributeRegistry.register(ModEntities.SMALL_PHANTOM_ENTITY_TYPE, SmallPhantom.setAttributes());
		ModBlockEntities.registerBlockEntities();
		ModMessages.registerC2SPackets();
		ModDimensions.register();


	}
}