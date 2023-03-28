package net.feny.phantom_world.world.dimension;

import net.feny.phantom_world.PhantomWorld;
import net.feny.phantom_world.block.ModBlocks;
import net.feny.phantom_world.item.ModItems;
import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

public class ModDimensions {

    public static final RegistryKey<World> PHANTOM_DIMENSION_KEY = RegistryKey.of(RegistryKeys.WORLD,
            new Identifier(PhantomWorld.MOD_ID,"phantom"));
    public static final RegistryKey<DimensionType> PHANTOM_TYPE_KEY = RegistryKey.of(RegistryKeys.DIMENSION_TYPE, PHANTOM_DIMENSION_KEY.getValue());

    public static void register(){
        PhantomWorld.LOGGER.debug("Registering Dimension for "+ PhantomWorld.MOD_ID);

        CustomPortalBuilder.beginPortal()
                .frameBlock(ModBlocks.FERO_BLOCK)
                .destDimID(PHANTOM_DIMENSION_KEY.getValue())
                .tintColor(0,255,255)
                .lightWithItem(ModItems.PHANTOM_WORLD_STARTER)
                .onlyLightInOverworld()
                .forcedSize(2,3)
                .registerPortal();
    }
}
