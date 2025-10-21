package net.cybercat5555.fallforaging.util;

import net.cybercat5555.fallforaging.FallForaging;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Items{
        public static final TagKey<Item> ACORNS = createTag( "ACORNS");

        private static TagKey<Item> createTag(String name){
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(FallForaging.MOD_ID, name));
        }
    }
}
