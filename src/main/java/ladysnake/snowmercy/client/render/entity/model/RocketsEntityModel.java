package ladysnake.snowmercy.client.render.entity.model;

import ladysnake.snowmercy.common.entity.WeaponizedSnowGolemEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModelPartNames;

@Environment(EnvType.CLIENT)
public class RocketsEntityModel<T extends WeaponizedSnowGolemEntity> extends WeaponizedSnowGolemEntityModel<T> {
    public RocketsEntityModel(ModelPart root) {
        super(root);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        modelPartData.addChild(EntityModelPartNames.HEAD, ModelPartBuilder.create().mirrored(true).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(-0.5F, -0.5F, -0.5F)).uv(34, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F), ModelTransform.of(0.0F, 4.0F, 0.0F, 0.0F, 0.0F, 0.0F));
        modelPartData.addChild("upper_body", ModelPartBuilder.create().uv(0, 16).mirrored(true).cuboid(-5.0F, -10.0F, -5.0F, 10.0F, 10.0F, 10.0F, new Dilation(-0.5F, -0.5F, -0.5F)), ModelTransform.of(0.0F, 13.0F, 0.0F, 0.0F, 0.0F, 0.0F));
        modelPartData.addChild("lower_body", ModelPartBuilder.create().uv(0, 36).mirrored(true).cuboid(-6.0F, -12.0F, -6.0F, 12.0F, 12.0F, 12.0F, new Dilation(-0.5F, -0.5F, -0.5F)), ModelTransform.of(0.0F, 24.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        modelPartData.getChild("upper_body").addChild("launcher", ModelPartBuilder.create().uv(40, 23).mirrored(true).cuboid(-2.5F, -2.5F, -3.0F, 5.0F, 5.0F, 8.0F, new Dilation(-0.5F, -0.5F, -0.5F)), ModelTransform.of(-6.5F, -6.5F, -1.0F, 0.0F, 0.0F, 0.0F));
        modelPartData.getChild("upper_body").getChild("launcher").addChild("rocket", ModelPartBuilder.create().uv(66, 25).mirrored(true).cuboid(-1.5F, -1.5F, -4.0F, 3.0F, 3.0F, 8.0F, new Dilation(-0.5F, -0.5F, -0.5F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));
        modelPartData.getChild("upper_body").getChild("launcher").addChild("tip", ModelPartBuilder.create().uv(88, 28).mirrored(true).cuboid(-0.5F, -2.5F, -7.0F, 1.0F, 5.0F, 4.0F, new Dilation(-0.5F, -0.5F, -0.5F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));
        modelPartData.getChild("upper_body").getChild("launcher").addChild("tip_r1", ModelPartBuilder.create().uv(88, 28).mirrored(true).cuboid(-0.5F, -2.5F, 0.0F, 1.0F, 5.0F, 4.0F, new Dilation(-0.5F, -0.5F, -0.5F)), ModelTransform.of(0.0F, 0.0F, -7.0F, 0.0F, 0.0F, -1.5708F));
        modelPartData.getChild("upper_body").getChild("launcher").addChild("rocket2", ModelPartBuilder.create(), ModelTransform.of(0.0F, 0.0F, -12.0F, 0.0F, 0.0873F, 0.0F));
        modelPartData.getChild("upper_body").getChild("launcher").addChild("tip2", ModelPartBuilder.create(), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        modelPartData.getChild("upper_body").addChild("launcher2", ModelPartBuilder.create().uv(40, 23).mirrored(true).cuboid(-2.5F, -2.5F, -3.0F, 5.0F, 5.0F, 8.0F, new Dilation(-0.5F, -0.5F, -0.5F)), ModelTransform.of(6.5F, -6.5F, -1.0F, 0.0F, 0.0F, 0.0F));
        modelPartData.getChild("upper_body").getChild("launcher2").addChild("rocket3", ModelPartBuilder.create().uv(66, 25).mirrored(true).cuboid(-1.5F, -1.5F, -4.0F, 3.0F, 3.0F, 8.0F, new Dilation(-0.5F, -0.5F, -0.5F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));
        modelPartData.getChild("upper_body").getChild("launcher2").addChild("tip3", ModelPartBuilder.create().uv(88, 28).mirrored(true).cuboid(-0.5F, -2.5F, -7.0F, 1.0F, 5.0F, 4.0F, new Dilation(-0.5F, -0.5F, -0.5F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));
        modelPartData.getChild("upper_body").getChild("launcher2").addChild("tip_r2", ModelPartBuilder.create().uv(88, 28).mirrored(true).cuboid(-0.5F, -2.5F, 0.0F, 1.0F, 5.0F, 4.0F, new Dilation(-0.5F, -0.5F, -0.5F)), ModelTransform.of(0.0F, 0.0F, -7.0F, 0.0F, 0.0F, -1.5708F));
        modelPartData.getChild("upper_body").getChild("launcher2").addChild("rocket4", ModelPartBuilder.create(), ModelTransform.of(0.0F, 0.0F, -12.0F, 0.0F, 0.0873F, 0.0F));
        modelPartData.getChild("upper_body").getChild("launcher2").addChild("tip4", ModelPartBuilder.create(), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        return TexturedModelData.of(modelData, 128, 64);
    }
}
