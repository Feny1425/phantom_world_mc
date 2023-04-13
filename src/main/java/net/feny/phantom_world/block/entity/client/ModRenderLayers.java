package net.feny.phantom_world.block.entity.client;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderPhase;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.render.block.entity.EndPortalBlockEntityRenderer;

public class ModRenderLayers
        extends RenderLayer  {
    private static final RenderLayer PHANTOM_PORTAL = RenderLayer.of("phantom_portal", VertexFormats.POSITION, VertexFormat.DrawMode.QUADS, 256, false, false, MultiPhaseParameters.builder().program(END_PORTAL_PROGRAM).texture(RenderPhase.Textures.create().add(PhantomPortalBlockEntityRenderer.SKY_TEXTURE, false, false).add(PhantomPortalBlockEntityRenderer.PORTAL_TEXTURE, false, false).build()).build(false));
    private static final RenderLayer PHANTOM_GATEWAY = RenderLayer.of("phantom_gateway", VertexFormats.POSITION, VertexFormat.DrawMode.QUADS, 256, false, false, MultiPhaseParameters.builder().program(END_GATEWAY_PROGRAM).texture(RenderPhase.Textures.create().add(PhantomPortalBlockEntityRenderer.SKY_TEXTURE, false, false).add(PhantomPortalBlockEntityRenderer.PORTAL_TEXTURE, false, false).build()).build(false));

    public static RenderLayer getPhantomGateway() {
        return PHANTOM_GATEWAY;
    }

    public static RenderLayer getPhantomPortal() {
        return PHANTOM_PORTAL;
    }

    public ModRenderLayers(String name, VertexFormat vertexFormat, VertexFormat.DrawMode drawMode, int expectedBufferSize, boolean hasCrumbling, boolean translucent, Runnable startAction, Runnable endAction) {
        super(name, vertexFormat, drawMode, expectedBufferSize, hasCrumbling, translucent, startAction, endAction);
    }
}
