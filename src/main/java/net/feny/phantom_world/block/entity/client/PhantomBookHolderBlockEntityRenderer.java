package net.feny.phantom_world.block.entity.client;

import net.feny.phantom_world.block.custom.PhantomBookHolderBlock;
import net.feny.phantom_world.block.entity.PhantomBookHolderEntity;
import net.feny.phantom_world.item.ModItems;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.entity.model.BookModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.world.LightType;

public class PhantomBookHolderBlockEntityRenderer implements BlockEntityRenderer<PhantomBookHolderEntity> {

    public PhantomBookHolderBlockEntityRenderer(BlockEntityRendererFactory.Context context){
    }

    @Override
    public void render(PhantomBookHolderEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();

        ItemStack itemStack = entity.getRenderStack();
        matrices.push();
        matrices.translate(0.5f, 1.3f, 0.5f);
        float scale = 0.7f;
        matrices.scale(scale, scale, scale);
        matrices.multiply(RotationAxis.POSITIVE_X.rotation(0));
        float h;
        float g = (float)entity.ticks + tickDelta;
        matrices.translate(0.0f, 0.1f + MathHelper.sin(g * 0.1f) * 0.01f, 0.0f);
        for (h = entity.renderRotation - entity.lastBookRotation; h >= (float)Math.PI; h -= (float)Math.PI * 2) {
        }
        while (h < (float)(-Math.PI)) {
            h += (float)Math.PI * 2;
        }
        float k = entity.lastBookRotation + h * tickDelta;
        matrices.multiply(RotationAxis.POSITIVE_Y.rotation(-k+degreeToRad(90)));

        itemRenderer.renderItem(itemStack, ModelTransformationMode.GUI, light,overlay, matrices, vertexConsumers,entity.getWorld(), 1);
        matrices.pop();
    }
    private float degreeToRad(int degree){
        return (float) (degree*Math.PI/180);
    }

}
