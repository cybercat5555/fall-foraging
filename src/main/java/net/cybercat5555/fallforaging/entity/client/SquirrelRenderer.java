package net.cybercat5555.fallforaging.entity.client;

import net.cybercat5555.fallforaging.FallForaging;
import net.cybercat5555.fallforaging.entity.custom.SquirrelEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class SquirrelRenderer extends MobEntityRenderer<SquirrelEntity, SquirrelModel<SquirrelEntity>> {
    public SquirrelRenderer(EntityRendererFactory.Context context) {
        super(context, new SquirrelModel<>(context.getPart(SquirrelModel.SQUIRREL)), 0.2f);
    }

    @Override
    public Identifier getTexture(SquirrelEntity entity) {
        return Identifier.of(FallForaging.MOD_ID, "textures/entity/squirrel/squirrel.png");
    }

    @Override
    public void render(SquirrelEntity livingEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        if(livingEntity.isBaby()) {
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            matrixStack.scale(1f, 1f, 1f);
        }

        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
