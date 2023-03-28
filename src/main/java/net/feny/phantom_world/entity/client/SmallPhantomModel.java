package net.feny.phantom_world.entity.client;

import net.feny.phantom_world.PhantomWorld;
import net.feny.phantom_world.entity.custom.SmallPhantom;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class SmallPhantomModel extends GeoModel<SmallPhantom> {
    @Override
    public Identifier getModelResource(SmallPhantom animatable) {
        return new Identifier(PhantomWorld.MOD_ID, "geo/small_phantom.geo.json");
    }

    @Override
    public Identifier getTextureResource(SmallPhantom animatable) {
        return new Identifier(PhantomWorld.MOD_ID, "textures/entity/small_phantom.png");
    }

    @Override
    public Identifier getAnimationResource(SmallPhantom animatable) {
        return new Identifier(PhantomWorld.MOD_ID, "animations/small_phantom.animation.json");

    }

    @Override
    public void setCustomAnimations(SmallPhantom animatable, long instanceId, AnimationState<SmallPhantom> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
            head.setRotY(entityData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
        }
    }
}
