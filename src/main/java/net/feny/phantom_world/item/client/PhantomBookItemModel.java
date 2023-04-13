package net.feny.phantom_world.item.client;

import net.feny.phantom_world.PhantomWorld;
import net.feny.phantom_world.item.custom.PhantomBookItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class PhantomBookItemModel extends GeoModel<PhantomBookItem> {
    @Override
    public Identifier getModelResource(PhantomBookItem animatable) {
        return new Identifier(PhantomWorld.MOD_ID,"geo/phantom_world_starter.geo.json");
    }

    @Override
    public Identifier getTextureResource(PhantomBookItem animatable) {
        return new Identifier(PhantomWorld.MOD_ID,"textures/item/phantom_world_starter.png");
    }

    @Override
    public Identifier getAnimationResource(PhantomBookItem animatable) {
        return new Identifier(PhantomWorld.MOD_ID,"animations/phantom_world_starter.animation.json");
    }
}
