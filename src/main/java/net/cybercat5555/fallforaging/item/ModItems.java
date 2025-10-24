package net.cybercat5555.fallforaging.item;

import net.cybercat5555.fallforaging.FallForaging;
import net.cybercat5555.fallforaging.block.ModBlocks;
import net.cybercat5555.fallforaging.entity.ModEntities;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item ACORN = registerItem( "acorn",
            new AliasedBlockItem(ModBlocks.FALLEN_ACORNS, new Item.Settings()));
    public static final Item DARK_ACORN = registerItem( "dark_acorn",
            new Item(new Item.Settings()));
    public static final Item CRANBERRY = registerItem( "cranberry",
            new AliasedBlockItem(ModBlocks.CRANBERRY_BUSH, new Item.Settings().food(ModFoodComponents.CRANBERRY)));
    public static final Item PEANUT = registerItem( "peanut",
            new AliasedBlockItem(ModBlocks.PEANUT_CROP, new Item.Settings().food(ModFoodComponents.PEANUT)));
    public static final Item PUMPKIN_SLICE = registerItem( "pumpkin_slice", new Item(new Item.Settings().food(ModFoodComponents.PUMPKIN_SLICE)));
    public static final Item PEPITAS = registerItem( "pepitas", new Item(new Item.Settings().food(ModFoodComponents.PEPITAS)));
    public static final Item SUNFLOWER_SEEDS = registerItem( "sunflower_seeds", new Item(new Item.Settings().food(ModFoodComponents.SUNFLOWER_SEEDS)));
    public static final Item BOTTLED_SQUIRREL = registerItem( "bottled_squirrel", new Item(new Item.Settings()));

    public static final Item RAW_SQUIRREL = registerItem( "raw_squirrel", new Item(new Item.Settings().food(ModFoodComponents.RAW_SQUIRREL)));

    public static final Item COOKED_SQUIRREL = registerItem( "cooked_squirrel", new Item(new Item.Settings().food(ModFoodComponents.COOKED_SQUIRREL)));

    public static final Item ROASTED_ACORN = registerItem( "roasted_acorn", new Item(new Item.Settings().food(ModFoodComponents.ROASTED_ACORN)));

    public static final Item SQUIRREL_SPAWN_EGG = registerItem("squirrel_spawn_egg",
            new SpawnEggItem(ModEntities.SQUIRREL,  0x8E877D, 0xC49450, new Item.Settings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(FallForaging.MOD_ID, name), item);
    }

    public static void registerModItems () {
        FallForaging.LOGGER.info("Registering Mod Items for" + FallForaging.MOD_ID);
    }
}
