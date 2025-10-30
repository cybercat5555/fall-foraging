package net.cybercat5555.fallforaging;

import net.cybercat5555.fallforaging.block.ModBlocks;
import net.cybercat5555.fallforaging.entity.ModEntities;
import net.cybercat5555.fallforaging.entity.client.SquirrelModel;
import net.cybercat5555.fallforaging.entity.client.SquirrelRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.render.RenderLayer;

public class FallForagingClient implements ClientModInitializer {

    public void onInitializeClient(){
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PEANUT_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CRANBERRY_BUSH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FALLEN_ACORNS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HANGING_ACORNS, RenderLayer.getCutout());

        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> BiomeColors.getFoliageColor(view, pos), ModBlocks.HANGING_ACORNS);



        EntityModelLayerRegistry.registerModelLayer(SquirrelModel.SQUIRREL, SquirrelModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.SQUIRREL, SquirrelRenderer::new);
    }
}
