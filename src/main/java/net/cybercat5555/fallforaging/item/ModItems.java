package net.cybercat5555.fallforaging.item;

import net.cybercat5555.fallforaging.FallForaging;
import net.cybercat5555.fallforaging.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item ACORN = registerItem( "acorn", new Item(new Item.Settings()));
    public static final Item DARK_ACORN = registerItem( "dark_acorn", new Item(new Item.Settings()));
    public static final Item CRANBERRY = registerItem( "cranberry",
            new AliasedBlockItem(ModBlocks.CRANBERRY_BUSH, new Item.Settings().food(ModFoodComponents.CRANBERRY)));
    public static final Item PEANUT = registerItem( "peanut",
            new AliasedBlockItem(ModBlocks.PEANUT_CROP, new Item.Settings().food(ModFoodComponents.PEANUT)));
    public static final Item PUMPKIN_SLICE = registerItem( "pumpkin_slice", new Item(new Item.Settings().food(ModFoodComponents.PUMPKIN_SLICE)));
    public static final Item PEPITAS = registerItem( "pepitas", new Item(new Item.Settings().food(ModFoodComponents.PEPITAS)));
    public static final Item SUNFLOWER_SEEDS = registerItem( "sunflower_seeds", new Item(new Item.Settings().food(ModFoodComponents.SUNFLOWER_SEEDS)));
    public static final Item BOTTLED_SQUIRREL = registerItem( "bottled_squirrel", new Item(new Item.Settings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(FallForaging.MOD_ID, name), item);
    }

    public static void registerModItems () {
        FallForaging.LOGGER.info("Registering Mod Items for" + FallForaging.MOD_ID);
    }
}
