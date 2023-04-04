package net.feny.phantom_world;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.feny.phantom_world.block.ModBlocks;
import net.feny.phantom_world.block.entity.ModBlockEntities;
import net.feny.phantom_world.block.entity.client.PhantomBookHolderBlockEntityRenderer;
import net.feny.phantom_world.networking.ModMessages;
import net.feny.phantom_world.screen.PhantomBookHolderScreen;
import net.feny.phantom_world.screen.ScreenHandlers;
import net.feny.phantom_world.util.ModModelPredicateProviderRegistry;
import net.feny.phantom_world.entity.ModEntities;
import net.feny.phantom_world.entity.client.SmallPhantomRenderer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;

public class PhantomWorldClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PHANTOM_LEAVES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PHANTOM_SAPLING, RenderLayer.getCutout());

        EntityRendererRegistry.register(ModEntities.SMALL_PHANTOM_ENTITY_TYPE, SmallPhantomRenderer::new);
        ModModelPredicateProviderRegistry.registerModModel();
        HandledScreens.register(ScreenHandlers.PHANTOM_BOOK_HOLDER_SCREEN_HANDLER, PhantomBookHolderScreen::new);
        BlockEntityRendererRegistry.register(ModBlockEntities.PHANTOM_BOOK_HOLDER_ENTITY, PhantomBookHolderBlockEntityRenderer::new);
        ModMessages.registerS2CPackets();
    }
}
