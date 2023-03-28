package net.feny.phantom_world.entity.client;

import net.feny.phantom_world.PhantomWorld;
import net.feny.phantom_world.entity.custom.SmallPhantom;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SmallPhantomRenderer extends GeoEntityRenderer<SmallPhantom> {
    public SmallPhantomRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new SmallPhantomModel());
    }

    @Override
    public Identifier getTexture(SmallPhantom animatable) {
        return new Identifier(PhantomWorld.MOD_ID, "textures/entity/small_phantom.png");

    }

    @Override
    public void render(SmallPhantom entity, float entityYaw, float partialTick, MatrixStack poseStack, VertexConsumerProvider bufferSource, int packedLight) {
        if (entity.isBaby()){
            //poseStack.scale(0.4f,0.4f,0.4f);
        }
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
