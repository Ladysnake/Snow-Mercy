package ladysnake.snowmercy.client.render.entity.model;// Made with Blockbench 4.0.5
// Exported for Minecraft version 1.17 with Mojang mappings
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

public class IcePillarEntityModel<T extends Entity> extends EntityModel<T> {
    private final ModelPart pillar;

    public IcePillarEntityModel(ModelPart root) {
        this.pillar = root.getChild("pillar");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        ModelPartData pillar = modelPartData.addChild("pillar", ModelPartBuilder.create().uv(0, 19).cuboid(-11.0F, -77.0F, -8.0F, 22.0F, 65.0F, 16.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 89.0F, 0.0F));

        ModelPartData cube_r1 = pillar.addChild("cube_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-57.0F, -57.0F, -9.0F, 16.0F, 16.0F, 18.0F, new Dilation(0.01F)), ModelTransform.of(0.0F, -8.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

        ModelPartData cube_r2 = pillar.addChild("cube_r2", ModelPartBuilder.create().uv(0, 0).cuboid(-5.0F, -5.0F, -9.0F, 16.0F, 16.0F, 18.0F, new Dilation(0.01F)), ModelTransform.of(0.0F, -8.0F, 0.0F, 0.0F, 0.0F, -2.3562F));

        return TexturedModelData.of(modelData, 128, 128);
    }

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.pillar.yaw = entity.age / 100f;
        this.pillar.setPivot(0f, (float) (89f+Math.sin(entity.age/50f)*10f), 0f);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        pillar.render(matrices, vertices, light, overlay, red, green, blue, alpha);
    }
}