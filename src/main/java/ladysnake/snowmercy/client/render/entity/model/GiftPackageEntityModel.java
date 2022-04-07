package ladysnake.snowmercy.client.render.entity.model;// Made with Blockbench 4.0.5
// Exported for Minecraft version 1.17 with Mojang mappings
// Paste this class into your mod and generate all required imports


import ladysnake.snowmercy.common.entity.GiftPackageEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

public class GiftPackageEntityModel<T extends Entity> extends EntityModel<T> {
    private final ModelPart gift;

    public GiftPackageEntityModel(ModelPart root) {
        this.gift = root.getChild("gift");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        ModelPartData gift = modelPartData.addChild("gift", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 11F, 0.0F));

        ModelPartData box = gift.addChild("box", ModelPartBuilder.create().uv(0, 60).cuboid(-5.0F, -10.0F, -5.0F, 10.0F, 10.0F, 10.0F), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData tie = gift.addChild("tie", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData cube_r1 = tie.addChild("cube_r1", ModelPartBuilder.create().uv(30, 65).cuboid(0.0F, -2.0F, -1.001F, 4.0F, 3.0F, 2.0F), ModelTransform.of(0.0F, -10.0F, 0.0F, 0.0F, 0.0F, -0.3054F));

        ModelPartData cube_r2 = tie.addChild("cube_r2", ModelPartBuilder.create().uv(30, 65).cuboid(-4.0F, -2.0F, -0.999F, 4.0F, 3.0F, 2.0F), ModelTransform.of(0.0F, -10.0F, 0.0F, 0.0F, 0.0F, 0.3054F));

        ModelPartData parachute = gift.addChild("parachute", ModelPartBuilder.create().uv(0, 0).cuboid(-16.0F, -55.0F, -16.0F, 32.0F, 28.0F, 32.0F), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData ropes = parachute.addChild("ropes", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData cube_r3 = ropes.addChild("cube_r3", ModelPartBuilder.create().uv(0, 111).cuboid(-23.0F, -27.0F, 0.0F, 46.0F, 17.0F, 0.0F), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

        ModelPartData cube_r4 = ropes.addChild("cube_r4", ModelPartBuilder.create().uv(0, 111).cuboid(-23.0F, -27.0F, 0.0F, 46.0F, 17.0F, 0.0F), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        return TexturedModelData.of(modelData, 128, 128);
    }

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        if (((GiftPackageEntity) entity).hasParachute()) {
            gift.getChild("parachute").visible = true;
        } else {
            gift.getChild("parachute").visible = false;
        }

        gift.yaw = entity.getYaw();
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        gift.render(matrices, vertices, light, overlay, red, green, blue, alpha);
    }
}