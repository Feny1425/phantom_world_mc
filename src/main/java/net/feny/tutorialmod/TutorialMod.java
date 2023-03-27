package net.feny.tutorialmod;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.feny.tutorialmod.block.ModBlocks;
import net.feny.tutorialmod.block.ModFlammableBlockRegistry;
import net.feny.tutorialmod.block.ModStrippedBlockRegistry;
import net.feny.tutorialmod.entity.ModEntities;
import net.feny.tutorialmod.entity.custom.SmallPhantom;
import net.feny.tutorialmod.item.ModItemGroup;
import net.feny.tutorialmod.item.ModItems;
import net.feny.tutorialmod.world.dimention.ModDimensions;
import net.feny.tutorialmod.world.gen.ModWorldGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TutorialMod implements ModInitializer {
	public static final String MOD_ID = "tutorialmod";
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