package ladysnake.snowmercy.client.render.entity.model;

import ladysnake.snowmercy.common.entity.WeaponizedSnowGolemEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;

@Environment(EnvType.CLIENT)
public class SnugglesEntityModel<T extends WeaponizedSnowGolemEntity> extends WeaponizedSnowGolemEntityModel<T> {
    public SnugglesEntityModel(ModelPart root) {
        super(root);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        Dilation dilation = new Dilation(-0.5F);
        modelPartData.addChild(EntityModelPartNames.HEAD, ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -7.0F, -4.0F, 8.0F, 7.0F, 8.0F, dilation), ModelTransform.pivot(0.0F, 4.0F, 0.0F));
        modelPartData.addChild("upper_body", ModelPartBuilder.create().uv(0, 15).cuboid(-5.0F, -10.0F, -5.0F, 10.0F, 10.0F, 10.0F, dilation), ModelTransform.pivot(0.0F, 13.0F, 0.0F));
        modelPartData.addChild("lower_body", ModelPartBuilder.create().uv(0, 35).cuboid(-6.0F, -12.0F, -6.0F, 12.0F, 12.0F, 12.0F, dilation), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
        modelPartData.getChild(EntityModelPartNames.HEAD).addChild("tnt", ModelPartBuilder.create().uv(42, 3).mirrored(true).cuboid(-0.5F, -28.0F, -0.75F, 1.0F, 3.0F, 2.0F, new Dilation(-0.5F, -0.5F, -0.5F)).uv(48, 0).cuboid(-2.0F, -26.0F, -2.0F, 4.0F, 6.0F, 4.0F, new Dilation(-0.5F, -0.5F, -0.5F)), ModelTransform.of(0.0F, 14.0F, 0.0F, 0.0F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }
}
