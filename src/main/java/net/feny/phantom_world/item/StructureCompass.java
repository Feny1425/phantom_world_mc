package net.feny.phantom_world.item;

import net.minecraft.item.Item;
import net.minecraft.item.Vanishable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.GlobalPos;
import net.minecraft.world.StructureLocator;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureType;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class StructureCompass extends Item implements Vanishable {


    public StructureCompass(Item.Settings settings) {
        super(settings);

    }


    @Nullable
    public static GlobalPos createSpawnPos(World world) {
        return world.getDimension().natural() ? GlobalPos.create(world.getRegistryKey(), new BlockPos(0, 0, 0)) : null;
    }


}
