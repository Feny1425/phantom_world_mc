package net.feny.phantom_world.block.entity;


import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.feny.phantom_world.PhantomWorld;
import net.feny.phantom_world.block.ModBlocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static net.feny.phantom_world.PhantomWorld.MOD_ID;

public class ModBlockEntities {
    public static BlockEntityType<PhantomBookHolderEntity> PHANTOM_BOOK_HOLDER_ENTITY;
    public static BlockEntityType<PhantomPortalBlockEntity> PHANTOM_PORTAL;

    public static void registerBlockEntities() {
        PHANTOM_BOOK_HOLDER_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(MOD_ID, "phantom_book_holder"), FabricBlockEntityTypeBuilder.create(PhantomBookHolderEntity::new, ModBlocks.PHANTOM_BOOK_HOLDER.getDefaultState().getBlock()).build(null));
        PHANTOM_PORTAL = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(MOD_ID, "phantom_portal"), FabricBlockEntityTypeBuilder.create(PhantomPortalBlockEntity::new, ModBlocks.PHANTOM_PORTAL.getDefaultState().getBlock()).build(null));

    }
}