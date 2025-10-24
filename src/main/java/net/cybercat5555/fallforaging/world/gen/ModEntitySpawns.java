package net.cybercat5555.fallforaging.world.gen;

import net.cybercat5555.fallforaging.entity.ModEntities;
import net.fabricmc.fabric.api.biome.v1.BiomeModification;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnLocationTypes;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;

public class ModEntitySpawns {
    public static void addSpawns() {
        BiomeModifications.addSpawn(BiomeSelectors.tag(ConventionalBiomeTags.IS_FOREST),
                SpawnGroup.CREATURE, ModEntities.SQUIRREL,  100, 2,5 );

        SpawnRestriction.register(ModEntities.SQUIRREL, SpawnLocationTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, AnimalEntity::isValidNaturalSpawn);

    }
}
