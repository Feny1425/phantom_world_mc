package net.feny.phantom_world;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.feny.phantom_world.block.ModBlocks;
import net.feny.phantom_world.block.ModFlammableBlockRegistry;
import net.feny.phantom_world.block.ModStrippedBlockRegistry;
import net.feny.phantom_world.entity.ModEntities;
import net.feny.phantom_world.entity.custom.SmallPhantom;
import net.feny.phantom_world.item.ModItemGroup;
import net.feny.phantom_world.item.ModItems;
import net.feny.phantom_world.world.dimention.ModDimensions;
import net.feny.phantom_world.world.gen.ModWorldGeneration;
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
		ModDimensions.register();
		ModFlammableBlockRegistry.registerFlammableBlocks();
		ModStrippedBlockRegistry.registerStrippedBlocks();

		FabricDefaultAttributeRegistry.register(ModEntities.SMALL_PHANTOM_ENTITY_TYPE, SmallPhantom.setAttributes());


	}
}