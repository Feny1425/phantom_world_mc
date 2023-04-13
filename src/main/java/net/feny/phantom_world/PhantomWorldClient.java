package net.feny.phantom_world;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.feny.phantom_world.block.ModBlocks;
import net.feny.phantom_world.block.entity.ModBlockEntities;
import net.feny.phantom_world.block.entity.PhantomPortalBlockEntity;
import net.feny.phantom_world.block.entity.client.PhantomBookHolderBlockEntityRenderer;
import net.feny.phantom_world.block.entity.client.PhantomPortalBlockEntityRenderer;
import net.feny.phantom_world.networking.ModMessages;
import net.feny.phantom_world.screen.PhantomBookHolderScreen;
import net.feny.phantom_world.screen.ScreenHandlers;
import net.feny.phantom_world.util.ModModelPredicateProviderRegistry;
import net.feny.phantom_world.entity.ModEntities;
import net.feny.phantom_world.entity.client.SmallPhantomRenderer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class PhantomWorldClient implements ClientModInitializer {



    @Override
    public void onInitializeClient() {
       /* Key Binding
         KeyBinding keyBinding;

        keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.examplemod.spook", // The translation key of the keybinding's name
                InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
                GLFW.GLFW_KEY_R, // The keycode of the key
                "category.examplemod.test" // The translation key of the keybinding's category.
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (keyBinding.wasPressed()) {
                client.player.sendMessage(Text.literal("Key 1 was pressed!"), false);
            }
        });*/


        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PHANTOM_LEAVES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BLOOD, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PHANTOM_SAPLING, RenderLayer.getCutout());

        EntityRendererRegistry.register(ModEntities.SMALL_PHANTOM_ENTITY_TYPE, SmallPhantomRenderer::new);
        ModModelPredicateProviderRegistry.registerModModel();
        HandledScreens.register(ScreenHandlers.PHANTOM_BOOK_HOLDER_SCREEN_HANDLER, PhantomBookHolderScreen::new);
        BlockEntityRendererRegistry.register(ModBlockEntities.PHANTOM_BOOK_HOLDER_ENTITY, PhantomBookHolderBlockEntityRenderer::new);
        BlockEntityRendererRegistry.register(ModBlockEntities.PHANTOM_PORTAL, PhantomPortalBlockEntityRenderer::new);
        ModMessages.registerS2CPackets();

    }
}
