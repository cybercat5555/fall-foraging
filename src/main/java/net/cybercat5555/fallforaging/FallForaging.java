package net.cybercat5555.fallforaging;

import net.cybercat5555.fallforaging.block.ModBlocks;
import net.cybercat5555.fallforaging.item.ModItemGroups;
import net.cybercat5555.fallforaging.item.ModItems;
import net.cybercat5555.fallforaging.world.gen.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FallForaging implements ModInitializer {
	public static final String MOD_ID = "fall-foraging";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModWorldGeneration.generateModWorldGen();

		CompostingChanceRegistry.INSTANCE.add(ModItems.CRANBERRY, 0.25f);
		CompostingChanceRegistry.INSTANCE.add(ModItems.PEANUT, 0.15f);
		CompostingChanceRegistry.INSTANCE.add(ModItems.PUMPKIN_SLICE, 0.25f);
		CompostingChanceRegistry.INSTANCE.add(ModItems.ACORN, 0.15f);
		CompostingChanceRegistry.INSTANCE.add(ModItems.DARK_ACORN, 0.15f);
		CompostingChanceRegistry.INSTANCE.add(ModItems.PEPITAS, 0.15f);
		CompostingChanceRegistry.INSTANCE.add(ModItems.SUNFLOWER_SEEDS, 0.15f);



	}
}