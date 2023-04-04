package net.feny.phantom_world.screen;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.feny.phantom_world.block.ModBlocks;
import net.feny.phantom_world.block.entity.PhantomBookHolderEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

import static net.feny.phantom_world.PhantomWorld.MOD_ID;

public class ScreenHandlers {
    public static final ScreenHandlerType<PhantomBookHolderScreenHandler> PHANTOM_BOOK_HOLDER_SCREEN_HANDLER;



    static {
        PHANTOM_BOOK_HOLDER_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(new Identifier(MOD_ID, "phantom_book_holder"), PhantomBookHolderScreenHandler::new);
    }
}
