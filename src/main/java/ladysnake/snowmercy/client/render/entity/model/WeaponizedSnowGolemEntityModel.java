package ladysnake.snowmercy.client.render.entity.model;

import ladysnake.snowmercy.common.entity.WeaponizedSnowGolemEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class WeaponizedSnowGolemEntityModel<T extends WeaponizedSnowGolemEntity> extends SinglePartEntityModel<T> {
    protected ModelPart root;
    protected ModelPart upperBody;
    protected ModelPart head;

    public WeaponizedSnowGolemEntityModel(ModelPart root) {
        this.root = root;
        this.head = root.getChild(EntityModelPartNames.HEAD);
        this.upperBody = root.getChild("upper_body");
    }

    public WeaponizedSnowGolemEntityModel(ModelPart root, boolean bl) {
        this.root = root;
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        Dilation dilation = new Dilation(-0.5F);
        modelPartData.addChild(EntityModelPartNames.HEAD, ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, dilation), ModelTransform.pivot(0.0F, 4.0F, 0.0F));
        modelPartData.addChild("upper_body", ModelPartBuilder.create().uv(0, 16).cuboid(-5.0F, -10.0F, -5.0F, 10.0F, 10.0F, 10.0F, dilation), ModelTransform.pivot(0.0F, 13.0F, 0.0F));
        modelPartData.addChild("lower_body", ModelPartBuilder.create().uv(0, 36).cuboid(-6.0F, -12.0F, -6.0F, 12.0F, 12.0F, 12.0F, dilation), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }

    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.head.yaw = headYaw * 0.017453292F;
        this.head.pitch = headPitch * 0.017453292F;
        this.upperBody.yaw = headYaw * 0.017453292F * 0.25F;
        this.head.visible = entity.getHead() == 1;
    }

    public ModelPart getPart() {
        return this.root;
    }

    public ModelPart getHead() {
        return this.head;
    }
}
