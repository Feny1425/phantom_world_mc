package net.feny.tutorialmod.world.dimention;

import net.feny.tutorialmod.TutorialMod;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

public class ModDimensions {
   /* public static final RegistryKey<World> PHANTOM_DIMENSION_KEY = RegistryKey.of(RegistryKeys.WORLD,
            new Identifier(TutorialMod.MOD_ID,"phantom_dim"));
    public static final RegistryKey<DimensionType> PHANTOM_TYPE_KEY = RegistryKey.of(RegistryKeys.DIMENSION_TYPE, PHANTOM_DIMENSION_KEY.getValue());
*/
    public static void register(){
        TutorialMod.LOGGER.debug("Registering ModDim for "+ TutorialMod.MOD_ID);
    }
}
