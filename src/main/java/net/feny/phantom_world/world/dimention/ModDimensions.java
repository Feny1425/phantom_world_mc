package net.feny.phantom_world.world.dimention;

import net.feny.phantom_world.PhantomWorld;

public class ModDimensions {
   /* public static final RegistryKey<World> PHANTOM_DIMENSION_KEY = RegistryKey.of(RegistryKeys.WORLD,
            new Identifier(phantom_world.MOD_ID,"phantom_dim"));
    public static final RegistryKey<DimensionType> PHANTOM_TYPE_KEY = RegistryKey.of(RegistryKeys.DIMENSION_TYPE, PHANTOM_DIMENSION_KEY.getValue());
*/
    public static void register(){
        PhantomWorld.LOGGER.debug("Registering ModDim for "+ PhantomWorld.MOD_ID);
    }
}
