package ladysnake.snowmercy.client.render.entity.model;// Made with Blockbench 4.0.5
// Exported for Minecraft version 1.17 with Mojang mappings
// Paste this class into your mod and generate all required imports


import ladysnake.snowmercy.common.entity.IceHeartEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

import java.util.Random;

public class IceHeartEntityModel<T extends Entity> extends EntityModel<T> {
    private final ModelPart heart;
    private float yaw = 0;
    private float pitch = 0;
    private float roll = 0;


    public IceHeartEntityModel(ModelPart root) {
        this.heart = root.getChild("heart");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        ModelPartData heart = modelPartData.addChild("heart", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F), ModelTransform.pivot(0.0F, 4.0F, 0.0F));

        return TexturedModelData.of(modelData, 32, 32);
    }

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        if (entity instanceof IceHeartEntity && ((IceHeartEntity) entity).isActive()) {
            this.heart.yaw = yaw += new Random().nextFloat()/20f;
            this.heart.pitch = pitch += new Random().nextFloat()/20f;
            this.heart.roll = roll += new Random().nextFloat()/20f;
        } else {
            this.heart.yaw = yaw += 0.001f;
            this.heart.pitch = pitch += 0.001f;
            this.heart.roll = roll += 0.001f;
        }
//        this.heart.setPivot(0f, (float) (89f+Math.sin(entity.age/50f)*10f), 0f);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        heart.render(matrices, vertices, light, overlay, red, green, blue, alpha);
    }
}