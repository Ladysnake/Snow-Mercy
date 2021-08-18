package ladysnake.snowmercy.client.render.entity.model;

import ladysnake.snowmercy.common.entity.WeaponizedSnowGolemEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModelPartNames;

@Environment(EnvType.CLIENT)
public class SawmanEntityModel<T extends WeaponizedSnowGolemEntity> extends WeaponizedSnowGolemEntityModel<T> {
    private final ModelPart saw;

    public SawmanEntityModel(ModelPart root) {
        super(root);
        this.saw = root.getChild("saw");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        modelPartData.addChild(EntityModelPartNames.HEAD, ModelPartBuilder.create().mirrored(true).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(-0.5F, -0.5F, -0.5F)), ModelTransform.of(0.0F, 4.0F, 0.0F, 0.0F, 0.0F, 0.0F));
        modelPartData.addChild("upper_body", ModelPartBuilder.create().uv(0, 16).mirrored(true).cuboid(-5.0F, -10.0F, -5.0F, 10.0F, 9.0F, 10.0F, new Dilation(-0.5F, -0.5F, -0.5F)), ModelTransform.of(0.0F, 13.0F, 0.0F, 0.0F, 0.0F, 0.0F));
        modelPartData.addChild("lower_body", ModelPartBuilder.create().uv(0, 35).mirrored(true).cuboid(-6.0F, -11.0F, -6.0F, 12.0F, 11.0F, 12.0F, new Dilation(-0.5F, -0.5F, -0.5F)), ModelTransform.of(0.0F, 24.0F, 0.0F, 0.0F, 0.0F, 0.0F));
        modelPartData.getChild(EntityModelPartNames.HEAD).addChild("headsaw_r1", ModelPartBuilder.create().uv(37, 34).mirrored(true).cuboid(-6.0F, 0.0F, -5.5F, 12.0F, 1.0F, 12.0F, new Dilation(-0.5F, -0.5F, -0.5F)), ModelTransform.of(-1.0F, -7.0F, -0.5F, 0.0F, 0.0F, 1.1781F));
        modelPartData.addChild("saw", ModelPartBuilder.create().uv(34, 47).mirrored(true).cuboid(-7.0F, 25.0F, -7.0F, 14.0F, -2.0F, 14.0F, new Dilation(1.0F, 1.0F, 1.0F)).uv(32, 10).cuboid(-1.5F, 22.5F, -1.5F, 3.0F, 3.0F, 3.0F, new Dilation(-0.5F, -0.5F, -0.5F)), ModelTransform.of(0.0F, -11.5F, 0.0F, 0.0F, 0.0F, 0.0F));

        return TexturedModelData.of(modelData, 128, 64);
    }

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        super.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
        this.saw.yaw = entity.age;
    }
}
