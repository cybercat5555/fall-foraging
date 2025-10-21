package net.cybercat5555.fallforaging.entity.client;

import net.cybercat5555.fallforaging.FallForaging;
import net.cybercat5555.fallforaging.entity.custom.SquirrelEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class SquirrelModel<T extends SquirrelEntity>  extends SinglePartEntityModel<T> {
    public static final EntityModelLayer SQUIRREL = new EntityModelLayer(Identifier.of(FallForaging.MOD_ID, "squirrel"),"main");

        private final ModelPart squirrel;
        private final ModelPart body;
        private final ModelPart breathingrig;
        private final ModelPart head;
        private final ModelPart lEar;
        private final ModelPart lEar2;
        private final ModelPart tail01;
        private final ModelPart lArm;
        private final ModelPart lArm2;
        private final ModelPart lFoot;
        private final ModelPart rFoot;
        public SquirrelModel(ModelPart root) {
            this.squirrel = root.getChild("squirrel");
            this.body = this.squirrel.getChild("body");
            this.breathingrig = this.body.getChild("breathingrig");
            this.head = this.body.getChild("head");
            this.lEar = this.head.getChild("lEar");
            this.lEar2 = this.head.getChild("lEar2");
            this.tail01 = this.body.getChild("tail01");
            this.lArm = this.squirrel.getChild("lArm");
            this.lArm2 = this.squirrel.getChild("lArm2");
            this.lFoot = this.squirrel.getChild("lFoot");
            this.rFoot = this.squirrel.getChild("rFoot");
        }
        public static TexturedModelData getTexturedModelData() {
            ModelData modelData = new ModelData();
            ModelPartData modelPartData = modelData.getRoot();
            ModelPartData squirrel = modelPartData.addChild("squirrel", ModelPartBuilder.create(), ModelTransform.pivot(-0.5F, 21.25F, 3.0F));

            ModelPartData body = squirrel.addChild("body", ModelPartBuilder.create().uv(0, 7).cuboid(-2.0F, -2.25F, 0.5F, 5.0F, 4.0F, 4.0F, new Dilation(-0.1F)), ModelTransform.pivot(0.0F, 0.0F, -3.0F));

            ModelPartData breathingrig = body.addChild("breathingrig", ModelPartBuilder.create().uv(0, 0).cuboid(-1.5F, -2.0F, -3.0F, 4.0F, 3.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

            ModelPartData head = body.addChild("head", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -1.75F, -2.5F));

            ModelPartData cube_r1 = head.addChild("cube_r1", ModelPartBuilder.create().uv(19, 10).cuboid(-1.0F, -2.7071F, -2.2929F, 2.0F, 3.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.5F, 1.0F, -0.5F, 0.7854F, 0.0F, 0.0F));

            ModelPartData lEar = head.addChild("lEar", ModelPartBuilder.create(), ModelTransform.pivot(1.5F, -1.25F, -1.25F));

            ModelPartData cube_r2 = lEar.addChild("cube_r2", ModelPartBuilder.create().uv(20, 4).cuboid(0.0F, -3.0F, -1.0F, 0.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.5F, 0.5F, 0.0F, 0.5672F, 0.0873F));

            ModelPartData lEar2 = head.addChild("lEar2", ModelPartBuilder.create(), ModelTransform.pivot(-0.5F, -1.25F, -1.25F));

            ModelPartData cube_r3 = lEar2.addChild("cube_r3", ModelPartBuilder.create().uv(20, 4).mirrored().cuboid(0.0F, -3.0F, -1.0F, 0.0F, 3.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 0.5F, 0.5F, 0.0F, -0.5672F, -0.0873F));

            ModelPartData tail01 = body.addChild("tail01", ModelPartBuilder.create().uv(0, 16).cuboid(-1.5F, -6.0F, -2.0F, 4.0F, 7.0F, 3.0F, new Dilation(0.0F))
                    .uv(0, 26).cuboid(-1.5F, -6.0F, 1.0F, 4.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -1.0F, 5.0F, 0.48F, 0.0F, 0.0F));

            ModelPartData lArm = squirrel.addChild("lArm", ModelPartBuilder.create().uv(18, 0).cuboid(-0.5F, 0.0F, -1.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(2.0F, 1.0F, -4.5F));

            ModelPartData lArm2 = squirrel.addChild("lArm2", ModelPartBuilder.create().uv(18, 0).mirrored().cuboid(-0.5F, 0.0F, -1.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(-1.0F, 1.0F, -4.5F));

            ModelPartData lFoot = squirrel.addChild("lFoot", ModelPartBuilder.create(), ModelTransform.pivot(2.0F, 1.75F, 0.5F));

            ModelPartData cube_r4 = lFoot.addChild("cube_r4", ModelPartBuilder.create().uv(18, 19).cuboid(-1.0326F, -0.25F, -3.4979F, 2.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.5F, 0.0F, -0.25F, 0.0873F, -0.1309F, 0.0F));

            ModelPartData rFoot = squirrel.addChild("rFoot", ModelPartBuilder.create(), ModelTransform.pivot(-1.0F, 1.75F, 0.5F));

            ModelPartData cube_r5 = rFoot.addChild("cube_r5", ModelPartBuilder.create().uv(18, 19).mirrored().cuboid(-0.9674F, -0.25F, -3.4979F, 2.0F, 1.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-0.5F, 0.0F, -0.25F, 0.0873F, 0.1309F, 0.0F));
            return TexturedModelData.of(modelData, 32, 32);
        }


        @Override
        public void setAngles(SquirrelEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
            this.getPart().traverse().forEach(ModelPart::resetTransform);
            this.setHeadAngles(netHeadYaw, headPitch);

            this.animateMovement(SquirrelAnimations.WALKING, limbSwing, limbSwingAmount, 2f, 2.5f);
            this.updateAnimation(entity.idleAnimationState, SquirrelAnimations.IDLE, ageInTicks, 1f);

        }

    private void setHeadAngles(float headYaw, float headPitch) {
        headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0F);
        headPitch = MathHelper.clamp(headPitch, -25.0F, 45.0F);

        this.head.yaw = headYaw * 0.017453292F;
        this.head.pitch = headPitch * 0.017453292F;
    }
        @Override
        public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
            squirrel.render(matrices, vertexConsumer, light, overlay, color);
        }

        @Override
    public ModelPart getPart(){
            return squirrel;
        }
    }

