package ladysnake.snowmercy.client.render.entity.model;

import ladysnake.snowmercy.common.entity.SnowGolemHeadEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModelPartNames;

@Environment(EnvType.CLIENT)
public class SnowGolemHeadEntityModel extends WeaponizedSnowGolemEntityModel<SnowGolemHeadEntity> {
    protected ModelPart rockets_head;
    protected ModelPart mortars_head;
    protected ModelPart saw_head;
    protected ModelPart boombox_head;

    public SnowGolemHeadEntityModel(ModelPart root) {
        super(root, true);
        this.head = root.getChild(EntityModelPartNames.HEAD);
        this.rockets_head = root.getChild("rockets_head");
        this.mortars_head = root.getChild("mortars_head");
        this.saw_head = root.getChild("saw_head");
        this.boombox_head = root.getChild("boombox_head");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        Dilation dilation = new Dilation(-0.5F);

        modelPartData.addChild(EntityModelPartNames.HEAD, ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -7.0F, -4.0F, 8.0F, 7.0F, 8.0F, dilation), ModelTransform.pivot(0.0F, 24.0f, 0.0F));
        modelPartData.getChild(EntityModelPartNames.HEAD).addChild("tnt", ModelPartBuilder.create().uv(42, 3).mirrored(true).cuboid(-0.5F, -28.0F, -0.75F, 1.0F, 3.0F, 2.0F, new Dilation(-0.5F, -0.5F, -0.5F)).uv(48, 0).cuboid(-2.0F, -26.0F, -2.0F, 4.0F, 6.0F, 4.0F, new Dilation(-0.5F, -0.5F, -0.5F)), ModelTransform.of(0.0F, 14.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        modelPartData.addChild("rockets_head", ModelPartBuilder.create().mirrored(true).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(-0.5F, -0.5F, -0.5F)).uv(34, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F), ModelTransform.of(0.0F, 24.0f, 0.0F, 0.0F, 0.0F, 0.0F));

        modelPartData.addChild("boombox_head", ModelPartBuilder.create().mirrored(true).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(-0.5F, -0.5F, -0.5F)).uv(34, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F), ModelTransform.of(0.0F, 24.0f, 0.0F, 0.0F, 0.0F, 0.0F));

        modelPartData.addChild("mortars_head", ModelPartBuilder.create().mirrored(true).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(-0.5F, -0.5F, -0.5F)), ModelTransform.of(0.0F, 24.0f, 0.0F, 0.0F, 0.0F, 0.0F));
        modelPartData.getChild("mortars_head").addChild("helmet_r1", ModelPartBuilder.create().uv(32, 0).mirrored(true).cuboid(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F), ModelTransform.of(0.0F, -4.0F, 0.0F, -0.1309F, 0.0F, 0.0F));

        modelPartData.addChild("saw_head", ModelPartBuilder.create().mirrored(true).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(-0.5F, -0.5F, -0.5F)), ModelTransform.of(0.0F, 24.0f, 0.0F, 0.0F, 0.0F, 0.0F));
        modelPartData.getChild("saw_head").addChild("headsaw_r1", ModelPartBuilder.create().uv(37, 34).mirrored(true).cuboid(-6.0F, 0.0F, -5.5F, 12.0F, 1.0F, 12.0F, new Dilation(-0.5F, -0.5F, -0.5F)), ModelTransform.of(-1.0F, -7.0F, -0.5F, 0.0F, 0.0F, 1.1781F));

        return TexturedModelData.of(modelData, 128, 64);
    }

    @Override
    public void setAngles(SnowGolemHeadEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.head.yaw = headYaw * 0.017453292F;
        this.head.pitch = headPitch * 0.017453292F;
        this.boombox_head.yaw = headYaw * 0.017453292F;
        this.boombox_head.pitch = headPitch * 0.017453292F;
        this.rockets_head.yaw = headYaw * 0.017453292F;
        this.rockets_head.pitch = headPitch * 0.017453292F;
        this.mortars_head.yaw = headYaw * 0.017453292F;
        this.mortars_head.pitch = headPitch * 0.017453292F;
        this.saw_head.yaw = headYaw * 0.017453292F;
        this.saw_head.pitch = headPitch * 0.017453292F;
        head.visible = false;
        rockets_head.visible = false;
        mortars_head.visible = false;
        saw_head.visible = false;
        boombox_head.visible = false;
        switch (entity.getGolemType()) {
            case SNUGGLES, CHILL_SNUGGLES -> head.visible = true;
            case ROCKETS -> rockets_head.visible = true;
            case MORTARS -> mortars_head.visible = true;
            case SAWMAN -> saw_head.visible = true;
            case BOOMBOX -> boombox_head.visible = true;
        }
    }
}
