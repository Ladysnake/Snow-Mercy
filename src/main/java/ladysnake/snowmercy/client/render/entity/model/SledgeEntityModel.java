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

        ModelPartData sledge = modelPartData.addChild("sledge", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -4.0F, -8.0F, 8.0F, 4.0F, 16.0F)
                .uv(0, 20).cuboid(-4.0F, -5.001F, -4.0F, 8.0F, 1.0F, 12.0F)
                .uv(0, 49).cuboid(-7.001F, -4.0F, -3.0F, 3.0F, 3.0F, 12.0F)
                .uv(0, 49).cuboid(4.001F, -4.0F, -3.0F, 3.0F, 3.0F, 12.0F)
                .uv(22, 29).cuboid(5.5F, -4.0F, 9.0F, 0.0F, 3.0F, 4.0F)
                .uv(22, 29).cuboid(-5.5F, -4.0F, 9.0F, 0.0F, 3.0F, 4.0F), ModelTransform.pivot(0.0F, 5.90F, -4.0F));

        sledge.addChild("cube_r1", ModelPartBuilder.create().uv(0, 33).cuboid(-4.0F, -5.0F, 0.0F, 8.0F, 5.0F, 6.0F, new Dilation(0.001F)), ModelTransform.of(0.0F, 0.0F, -8.0F, 0.7418F, 0.0F, 0.0F));

        return TexturedModelData.of(modelData, 64, 64);
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

