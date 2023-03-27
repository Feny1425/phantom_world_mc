package net.feny.tutorialmod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.feny.tutorialmod.TutorialMod;
import net.feny.tutorialmod.entity.ModEntities;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item FERO = registerItem("fero",
            new Item(new FabricItemSettings()));
    public static final Item RAW_FERO = registerItem("raw_fero",
            new Item(new FabricItemSettings()));
    public static final Item SMALL_PHANTOM_SPAWN_EGG = registerItem("small_phantom_spawn_egg",
            new SpawnEggItem(ModEntities.SMALL_PHANTOM_ENTITY_TYPE,0xc6fdfe, 0x000000, new FabricItemSettings()));




    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM,new Identifier(TutorialMod.MOD_ID,name),item);
    }
    public static void addItemsToItemGroup(){
        addToItemGroup(ModItemGroup.FERO,FERO);
        addToItemGroup(ModItemGroup.FERO,RAW_FERO);
        addToItemGroup(ModItemGroup.FERO,SMALL_PHANTOM_SPAWN_EGG);
    }
    private static void addToItemGroup(ItemGroup group, Item item){
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));
    }
    public static void registerModItems(){
        TutorialMod.LOGGER.info("Registering Mod Items for " + TutorialMod.MOD_ID);
        addItemsToItemGroup();
    }

}
