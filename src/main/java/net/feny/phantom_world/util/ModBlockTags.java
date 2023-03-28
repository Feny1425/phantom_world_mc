package net.feny.phantom_world.util;

import net.feny.phantom_world.PhantomWorld;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModBlockTags {
    public static final TagKey<Item> PHANTOM_LOGS = TagKey.of(RegistryKeys.ITEM, new Identifier(PhantomWorld.MOD_ID, "phantom_logs"));
}
