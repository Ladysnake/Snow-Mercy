package ladysnake.snowmercy.client.render.entity.model;

import ladysnake.snowmercy.common.entity.WeaponizedSnowGolemEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModelPartNames;

@Environment(EnvType.CLIENT)
public class MortarsEntityModel<T extends WeaponizedSnowGolemEntity> extends WeaponizedSnowGolemEntityModel<T> {
    public MortarsEntityModel(ModelPart root) {
        super(root);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        modelPartData.addChild(EntityModelPartNames.HEAD, ModelPartBuilder.create().mirrored(true).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(-0.5F, -0.5F, -0.5F)), ModelTransform.of(0.0F, 4.0F, 0.0F, 0.0F, 0.0F, 0.0F));
        modelPartData.addChild("upper_body", ModelPartBuilder.create().uv(0, 16).mirrored(true).cuboid(-5.0F, -10.0F, -5.0F, 10.0F, 10.0F, 10.0F, new Dilation(-0.5F, -0.5F, -0.5F)), ModelTransform.of(0.0F, 13.0F, 0.0F, 0.0F, 0.0F, 0.0F));
        modelPartData.addChild("lower_body", ModelPartBuilder.create().uv(0, 36).mirrored(true).cuboid(-6.0F, -12.0F, -6.0F, 12.0F, 12.0F, 12.0F, new Dilation(-0.5F, -0.5F, -0.5F)), ModelTransform.of(0.0F, 24.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        modelPartData.getChild(EntityModelPartNames.HEAD).addChild("helmet_r1", ModelPartBuilder.create().uv(32, 0).mirrored(true).cuboid(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F), ModelTransform.of(0.0F, -4.0F, 0.0F, -0.1309F, 0.0F, 0.0F));
        modelPartData.getChild("upper_body").addChild("mortar", ModelPartBuilder.create(), ModelTransform.of(0.0F, -2.0F, -6.0F, 0.0F, 0.0F, 0.0F));
        modelPartData.getChild("upper_body").getChild("mortar").addChild("launcher_r1", ModelPartBuilder.create().uv(48, 38).mirrored(true).cuboid(-4.0F, -7.0F, -4.0F, 8.0F, 14.0F, 8.0F, new Dilation(-0.5F, -0.5F, -0.5F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.3054F, 0.0F, 0.0F));
        modelPartData.getChild("upper_body").getChild("mortar").addChild("icicle", ModelPartBuilder.create().uv(80, 49).cuboid(-1.5F, -11.0F, 0.0F, 3.0F, 11.0F, 0.0F), ModelTransform.of(-1.5F, -5.0F, 0.0F, 0.3054F, 0.0F, 0.0F));
        modelPartData.getChild("upper_body").getChild("mortar").addChild("cube_r1", ModelPartBuilder.create().uv(80, 49).cuboid(-1.5F, -5.5F, 0.0F, 3.0F, 11.0F, 0.0F), ModelTransform.of(0.0F, -5.5F, 0.0F, 0.0F, -1.5708F, 0.0F));
        modelPartData.getChild("upper_body").getChild("mortar").addChild("icicle2", ModelPartBuilder.create().uv(80, 49).cuboid(-1.5F, -11.0F, 0.0F, 3.0F, 11.0F, 0.0F), ModelTransform.of(1.5F, -5.0F, 0.0F, 0.3054F, 0.0F, 0.0F));
        modelPartData.getChild("upper_body").getChild("mortar").addChild("cube_r2", ModelPartBuilder.create().uv(80, 49).cuboid(-1.5F, -5.5F, 0.0F, 3.0F, 11.0F, 0.0F), ModelTransform.of(0.0F, -5.5F, 0.0F, 0.0F, -1.5708F, 0.0F));
        modelPartData.getChild("upper_body").getChild("mortar").addChild("icicle3", ModelPartBuilder.create().uv(80, 49).cuboid(-1.5F, -11.0F, 0.0F, 3.0F, 11.0F, 0.0F), ModelTransform.of(-1.5F, -4.0F, -3.0F, 0.3054F, 0.0F, 0.0F));
        modelPartData.getChild("upper_body").getChild("mortar").addChild("cube_r3", ModelPartBuilder.create().uv(80, 49).cuboid(-1.5F, -5.5F, 0.0F, 3.0F, 11.0F, 0.0F), ModelTransform.of(0.0F, -5.5F, 0.0F, 0.0F, -1.5708F, 0.0F));
        modelPartData.getChild("upper_body").getChild("mortar").addChild("icicle4", ModelPartBuilder.create().uv(80, 49).cuboid(-1.5F, -11.0F, 0.0F, 3.0F, 11.0F, 0.0F), ModelTransform.of(1.5F, -4.0F, -3.0F, 0.3054F, 0.0F, 0.0F));
        modelPartData.getChild("upper_body").getChild("mortar").addChild("cube_r4", ModelPartBuilder.create().uv(80, 49).cuboid(-1.5F, -5.5F, 0.0F, 3.0F, 11.0F, 0.0F), ModelTransform.of(0.0F, -5.5F, 0.0F, 0.0F, -1.5708F, 0.0F));

        return TexturedModelData.of(modelData, 128, 64);
    }
}
