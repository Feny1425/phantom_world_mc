package net.feny.phantom_world.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.feny.phantom_world.PhantomWorld;
import net.feny.phantom_world.block.ModBlocks;
import net.feny.phantom_world.entity.ModEntities;
import net.feny.phantom_world.item.custom.HeartItem;
import net.feny.phantom_world.item.custom.PhantomBookItem;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item FERO = registerItem("fero",
            new Item(new FabricItemSettings()));
    public static final Item RAW_FERO = registerItem("raw_fero",
            new Item(new FabricItemSettings()));
    public static final Item PHANTOM_STARTER_RECIPE = registerItem("phantom_starter_recipe",
            new Item(new FabricItemSettings()));
    public static final Item SMALL_PHANTOM_SPAWN_EGG = registerItem("small_phantom_spawn_egg",
            new SpawnEggItem(ModEntities.SMALL_PHANTOM_ENTITY_TYPE,0xc6fdfe, 0x000000, new FabricItemSettings()));

    public static final Item HEART = Registry.register(Registries.ITEM, new Identifier(PhantomWorld.MOD_ID, "heart"),
            new HeartItem(ModBlocks.BLOOD, new FabricItemSettings().maxDamage(44)));


    /** phantom book*/
    public static final Item PHANTOM_WORLD_STARTER = registerItem("phantom_world_starter",
            new PhantomBookItem(new FabricItemSettings().maxCount(1).fireproof()));
    /**Pages*/
    public static final Item PAGE0 = registerItem("page0_phantom_book",
            new Item(new FabricItemSettings().maxCount(1).fireproof()));
    public static final Item PAGE1 = registerItem("page1_phantom_book",
            new Item(new FabricItemSettings().maxCount(1).fireproof()));
    public static final Item PAGE2 = registerItem("page2_phantom_book",
            new Item(new FabricItemSettings().maxCount(1).fireproof()));
    public static final Item PAGE3 = registerItem("page3_phantom_book",
            new Item(new FabricItemSettings().maxCount(1).fireproof()));
    public static final Item PAGE4 = registerItem("page4_phantom_book",
            new Item(new FabricItemSettings().maxCount(1).fireproof()));
    public static final Item PAGE5 = registerItem("page5_phantom_book",
            new Item(new FabricItemSettings().maxCount(1).fireproof()));
    public static final Item PAGE6 = registerItem("page6_phantom_book",
            new Item(new FabricItemSettings().maxCount(1).fireproof()));
    public static final Item PAGE6_PASS = registerItem("page6_phantom_book_pass",
            new Item(new FabricItemSettings().maxCount(1).fireproof()));

    public static final Item PASS = registerItem("pass",
            new Item(new FabricItemSettings().maxCount(1)));



    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM,new Identifier(PhantomWorld.MOD_ID,name),item);
    }
    public static void addItemsToItemGroup(){
        addToItemGroup(ModItemGroup.FERO,FERO);
        addToItemGroup(ModItemGroup.FERO,RAW_FERO);
        addToItemGroup(ModItemGroup.FERO,SMALL_PHANTOM_SPAWN_EGG);
        addToItemGroup(ModItemGroup.FERO,PHANTOM_STARTER_RECIPE);
        addToItemGroup(ModItemGroup.FERO,PHANTOM_WORLD_STARTER);
        addToItemGroup(ModItemGroup.FERO,PAGE1);
        addToItemGroup(ModItemGroup.FERO,PAGE2);
        addToItemGroup(ModItemGroup.FERO,PAGE3);
        addToItemGroup(ModItemGroup.FERO,PAGE4);
        addToItemGroup(ModItemGroup.FERO,PAGE5);
        addToItemGroup(ModItemGroup.FERO,PAGE6);
        addToItemGroup(ModItemGroup.FERO,PASS);
        addToItemGroup(ModItemGroup.FERO,HEART);
    }
    private static void addToItemGroup(ItemGroup group, Item item){
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));
    }
    public static void registerModItems(){
        PhantomWorld.LOGGER.info("Registering Mod Items for " + PhantomWorld.MOD_ID);
        addItemsToItemGroup();
    }

}
