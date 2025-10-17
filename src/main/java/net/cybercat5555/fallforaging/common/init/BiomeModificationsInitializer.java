package net.cybercat5555.fallforaging.common.init;

import net.cybercat5555.fallforaging.FallForaging;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.PlacedFeature;

import java.util.function.Predicate;

public class BiomeModificationsInitializer {

    public static final RegistryKey<PlacedFeature> PATCH_CRANBERRY_BUSH = RegistryKey.of(RegistryKeys.PLACED_FEATURE, FallForaging.id("patch_cranberry_bush"));


    public static void init() {


        BiomeModifications.addFeature(BiomeSelectors.tag(ConventionalBiomeTags.IS_SWAMP),
                GenerationStep.Feature.VEGETAL_DECORATION, PATCH_CRANBERRY_BUSH);


    }


}
