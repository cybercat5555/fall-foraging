package net.cybercat5555.fallforaging.block;

import net.cybercat5555.fallforaging.FallForaging;
import net.cybercat5555.fallforaging.block.custom.CranberryBushBlock;
import net.cybercat5555.fallforaging.block.custom.FallenAcornsBlock;
import net.cybercat5555.fallforaging.block.custom.PeanutCropBlock;
import net.cybercat5555.fallforaging.block.custom.HangingAcornsBlock;
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

    public static final Block CRANBERRY_BUSH = registerBlockWithoutBlockItem("cranberry_bush",
        new CranberryBushBlock(AbstractBlock.Settings.create().noCollision()
                .ticksRandomly().breakInstantly().sounds(BlockSoundGroup.SWEET_BERRY_BUSH).pistonBehavior(PistonBehavior.DESTROY).mapColor(MapColor.DARK_GREEN)));

    public static final Block HANGING_ACORNS = registerBlockWithoutBlockItem("hanging_acorns",
            new HangingAcornsBlock(AbstractBlock.Settings.create().noCollision()
                    .ticksRandomly().breakInstantly().sounds(BlockSoundGroup.SWEET_BERRY_BUSH).pistonBehavior(PistonBehavior.DESTROY).mapColor(MapColor.DARK_GREEN)));

    public static final Block FALLEN_ACORNS = registerBlockWithoutBlockItem("fallen_acorns",
            new FallenAcornsBlock(AbstractBlock.Settings.create().noCollision()
                    .breakInstantly().sounds(BlockSoundGroup.SWEET_BERRY_BUSH).pistonBehavior(PistonBehavior.DESTROY).mapColor(MapColor.BROWN)));

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
