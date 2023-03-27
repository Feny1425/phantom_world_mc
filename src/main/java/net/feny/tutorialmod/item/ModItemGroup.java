package net.feny.tutorialmod.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.feny.tutorialmod.TutorialMod;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroup {
    public static ItemGroup FERO;

    public static void registerItemGroups() {
        FERO = FabricItemGroup.builder(new Identifier(TutorialMod.MOD_ID, "fero"))
                .displayName(Text.translatable("itemgroup.fero"))
                .icon(() -> new ItemStack(ModItems.FERO)).build();
    }

}
