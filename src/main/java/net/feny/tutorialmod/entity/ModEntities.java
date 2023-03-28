package net.feny.tutorialmod.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.feny.tutorialmod.TutorialMod;
import net.feny.tutorialmod.entity.custom.SmallPhantom;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<SmallPhantom> SMALL_PHANTOM_ENTITY_TYPE = Registry.register(
            Registries.ENTITY_TYPE,new Identifier(TutorialMod.MOD_ID,"small_phantom"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE,SmallPhantom::new)
                    .dimensions(EntityDimensions.fixed(0.7f,0.9f))
                    .build());

}
