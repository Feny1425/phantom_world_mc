package net.feny.phantom_world.item.client;

import net.feny.phantom_world.item.custom.PhantomBookItem;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class PhantomBookItemRenderer extends GeoItemRenderer<PhantomBookItem> {
    public PhantomBookItemRenderer() {
        super(new PhantomBookItemModel());
    }
}
