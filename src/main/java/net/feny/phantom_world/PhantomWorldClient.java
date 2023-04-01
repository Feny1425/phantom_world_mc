package net.feny.phantom_world;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.feny.phantom_world.block.ModBlocks;
import net.feny.phantom_world.util.ModModelPredicateProviderRegistry;
import net.feny.phantom_world.entity.ModEntities;
import net.feny.phantom_world.entity.client.SmallPhantomRenderer;
import net.minecraft.client.render.RenderLayer;

public class PhantomWorldClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PHANTOM_LEAVES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PHANTOM_SAPLING, RenderLayer.getCutout());

        EntityRendererRegistry.register(ModEntities.SMALL_PHANTOM_ENTITY_TYPE, SmallPhantomRenderer::new);
        ModModelPredicateProviderRegistry.registerModModel();
    }
}
