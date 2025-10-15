package net.cybercat5555.fallforaging.block;

import net.cybercat5555.fallforaging.FallForaging;
import net.cybercat5555.fallforaging.block.custom.PeanutCropBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static final Block PEANUT_CROP = registerBlockWithoutBlockItem( "peanut_crop",
        new PeanutCropBlock(AbstractBlock.Settings.create().noCollision()
                .ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP).pistonBehavior(PistonBehavior.DESTROY).mapColor(MapColor.DARK_GREEN)));

    private static Block registerBlock(String name, Block block){
        registerBlockItems(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(FallForaging.MOD_ID, name),block);
    }

    private static Block registerBlockWithoutBlockItem(String name, Block block){
        return Registry.register(Registries.BLOCK, Identifier.of(FallForaging.MOD_ID, name),block);
    }

    public static void registerBlockItems(String name, Block block){
        Registry.register(Registries.ITEM, Identifier.of(FallForaging.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));

    }
    public static void registerModBlocks(){
        FallForaging.LOGGER.info("Registering Mod Blocks for "+ FallForaging.MOD_ID);
    }
}
