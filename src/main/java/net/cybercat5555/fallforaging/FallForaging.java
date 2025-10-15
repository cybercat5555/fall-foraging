package net.cybercat5555.fallforaging;

import net.cybercat5555.fallforaging.block.ModBlocks;
import net.cybercat5555.fallforaging.item.ModItemGroups;
import net.cybercat5555.fallforaging.item.ModItems;
import net.fabricmc.api.ModInitializer;

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

	}
}