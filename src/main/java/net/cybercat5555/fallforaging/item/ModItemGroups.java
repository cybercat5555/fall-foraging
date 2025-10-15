package net.cybercat5555.fallforaging.item;

import net.cybercat5555.fallforaging.FallForaging;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup FALL_FORAGING_ITEMS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(FallForaging.MOD_ID,  "fall_foraging_items"),
            FabricItemGroup.builder().icon( () -> new ItemStack(ModItems.ACORN))
                    .displayName(Text.translatable( "itemgroup.fallforaging.fall_foraging_items"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.ACORN);
                        entries.add(ModItems.DARK_ACORN);
                        entries.add(ModItems.CRANBERRY);
                        entries.add(ModItems.PEANUT);
                        entries.add(ModItems.PUMPKIN_SLICE);
                        entries.add(ModItems.PEPITAS);
                        entries.add(ModItems.SUNFLOWER_SEEDS);
                        entries.add(ModItems.BOTTLED_SQUIRREL);
                    })


                    .build());

    public static void registerItemGroups(){
        FallForaging.LOGGER.info("Registering item groups for " + FallForaging.MOD_ID);
    }
}
