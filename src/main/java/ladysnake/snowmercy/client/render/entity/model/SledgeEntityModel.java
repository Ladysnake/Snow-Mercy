package ladysnake.snowmercy.client.render.entity.model;

import ladysnake.snowmercy.common.entity.SledgeEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class SledgeEntityModel extends SinglePartEntityModel<SledgeEntity> {
    private final ModelPart sledge;

    public SledgeEntityModel(ModelPart root) {
        this.sledge = root.getChild("sledge");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        ModelPartData sledge = modelPartData.addChild("sledge", ModelPartBuilder.create().uv(0, 0).cuboid(-10.0F, 0.0F, -6.0F, 12.0F, 4.0F, 23.0F)
                .uv(0, 27).cuboid(-10.0F, -1.001F, -2.0F, 12.0F, 1.0F, 19.0F)
                .uv(0, 58).cuboid(-13.001F, 0.0F, -1.0F, 3.0F, 3.0F, 12.0F)
                .uv(0, 58).cuboid(2.001F, 0.0F, -1.0F, 3.0F, 3.0F, 12.0F)
                .uv(22, 63).cuboid(3.5F, 0.0F, 11.0F, 0.0F, 3.0F, 4.0F)
                .uv(22, 63).cuboid(-11.5F, 0.0F, 11.0F, 0.0F, 3.0F, 4.0F), ModelTransform.pivot(4.0F, 1.90F, -6.0F));

        sledge.addChild("cube_r1", ModelPartBuilder.create().uv(0, 47).cuboid(-7.0F, -5.0F, 0.0F, 14.0F, 5.0F, 6.0F, new Dilation(0.001F)), ModelTransform.of(-4.0F, 4.0F, -6.0F, 0.7418F, 0.0F, 0.0F));

        return TexturedModelData.of(modelData, 128, 128);
    }

    @Override
    public void setAngles(SledgeEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        this.sledge.render(matrices, vertices, light, overlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart getPart() {
        return this.sledge;
    }
}

