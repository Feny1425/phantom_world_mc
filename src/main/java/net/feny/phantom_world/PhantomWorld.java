package net.feny.phantom_world;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.feny.phantom_world.block.ModBlocks;
import net.feny.phantom_world.block.ModFlammableBlockRegistry;
import net.feny.phantom_world.block.ModStrippedBlockRegistry;
import net.feny.phantom_world.block.entity.ModBlockEntities;
import net.feny.phantom_world.effect.ModEffects;
import net.feny.phantom_world.entity.ModEntities;
import net.feny.phantom_world.entity.custom.SmallPhantom;
import net.feny.phantom_world.item.ModItemGroup;
import net.feny.phantom_world.item.ModItems;
import net.feny.phantom_world.networking.ModMessages;
import net.feny.phantom_world.potion.ModPotions;
import net.feny.phantom_world.world.dimension.ModDimensions;
import net.feny.phantom_world.world.gen.ModWorldGeneration;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PhantomWorld implements ModInitializer {
	public static final String MOD_ID = "phantom_world";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);




	public static final Identifier EARTHQUAKE_ID = new Identifier(MOD_ID,"earthquake");
	public static SoundEvent EARTHQUAKE_EVENT = SoundEvent.of(EARTHQUAKE_ID);
	public static final Identifier HUM_ID = new Identifier(MOD_ID,"hum");
	public static SoundEvent HUM_EVENT = SoundEvent.of(HUM_ID);
	@Override
	public void onInitialize() {
		Registry.register(Registries.SOUND_EVENT, PhantomWorld.EARTHQUAKE_ID, EARTHQUAKE_EVENT);
		Registry.register(Registries.SOUND_EVENT, PhantomWorld.HUM_ID, HUM_EVENT);

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
		ModEffects.registerEffects();
		ModPotions.registerPotions();


	}
}