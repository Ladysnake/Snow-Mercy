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

    public static TexturedModelData boomboxModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        modelPartData.addChild(EntityModelPartNames.HEAD, ModelPartBuilder.create().mirrored(true).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(-0.5F, -0.5F, -0.5F)).uv(34, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F), ModelTransform.of(0.0F, 4.0F, 0.0F, 0.0F, 0.0F, 0.0F));
        modelPartData.addChild("upper_body", ModelPartBuilder.create().uv(0, 16).mirrored(true).cuboid(-5.0F, -10.0F, -5.0F, 10.0F, 10.0F, 10.0F, new Dilation(-0.5F, -0.5F, -0.5F)), ModelTransform.of(0.0F, 13.0F, 0.0F, 0.0F, 0.0F, 0.0F));
        modelPartData.addChild("lower_body", ModelPartBuilder.create().uv(0, 36).mirrored(true).cuboid(-6.0F, -12.0F, -6.0F, 12.0F, 12.0F, 12.0F, new Dilation(-0.5F, -0.5F, -0.5F)), ModelTransform.of(0.0F, 24.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        return TexturedModelData.of(modelData, 128, 64);
    }

    public static TexturedModelData rocketsModelData() {
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

    public static TexturedModelData snugglesModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        Dilation dilation = new Dilation(-0.5F);
        modelPartData.addChild(EntityModelPartNames.HEAD, ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -7.0F, -4.0F, 8.0F, 7.0F, 8.0F, dilation), ModelTransform.pivot(0.0F, 4.0F, 0.0F));
        modelPartData.addChild("upper_body", ModelPartBuilder.create().uv(0, 15).cuboid(-5.0F, -10.0F, -5.0F, 10.0F, 10.0F, 10.0F, dilation), ModelTransform.pivot(0.0F, 13.0F, 0.0F));
        modelPartData.addChild("lower_body", ModelPartBuilder.create().uv(0, 35).cuboid(-6.0F, -12.0F, -6.0F, 12.0F, 12.0F, 12.0F, dilation), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
        modelPartData.getChild(EntityModelPartNames.HEAD).addChild("tnt", ModelPartBuilder.create().uv(42, 3).mirrored(true).cuboid(-0.5F, -28.0F, -0.75F, 1.0F, 3.0F, 2.0F, new Dilation(-0.5F, -0.5F, -0.5F)).uv(48, 0).cuboid(-2.0F, -26.0F, -2.0F, 4.0F, 6.0F, 4.0F, new Dilation(-0.5F, -0.5F, -0.5F)), ModelTransform.of(0.0F, 14.0F, 0.0F, 0.0F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 128, 64);
    }

    public static TexturedModelData mortarsModelData() {
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
