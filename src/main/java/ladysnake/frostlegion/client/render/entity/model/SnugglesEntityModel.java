package ladysnake.frostlegion.client.render.entity.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.CompositeEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class SnugglesEntityModel<T extends Entity> extends CompositeEntityModel<T> {
    private final ModelPart head;
    private final ModelPart tnt;
    private final ModelPart upper;
    private final ModelPart lower;

    public SnugglesEntityModel() {
        textureWidth = 64;
        textureHeight = 64;
        head = new ModelPart(this);
        head.setPivot(0.0F, 4.0F, 0.0F);
        head.setTextureOffset(0, 0).addCuboid(-4.0F, -7.0F, -4.0F, 8.0F, 7.0F, 8.0F, -0.5F, true);

        tnt = new ModelPart(this);
        tnt.setPivot(0.0F, 14.0F, 0.0F);
        head.addChild(tnt);
        tnt.setTextureOffset(42, 3).addCuboid(-0.5F, -28.0F, -0.75F, 1.0F, 3.0F, 2.0F, -0.5F, true);
        tnt.setTextureOffset(48, 0).addCuboid(-2.0F, -26.0F, -2.0F, 4.0F, 6.0F, 4.0F, -0.5F, true);

        upper = new ModelPart(this);
        upper.setPivot(0.0F, 13.0F, 0.0F);
        upper.setTextureOffset(0, 15).addCuboid(-5.0F, -10.0F, -5.0F, 10.0F, 10.0F, 10.0F, -0.5F, true);

        lower = new ModelPart(this);
        lower.setPivot(0.0F, 24.0F, 0.0F);
        lower.setTextureOffset(0, 35).addCuboid(-6.0F, -12.0F, -6.0F, 12.0F, 12.0F, 12.0F, -0.5F, true);
    }

    @Override
    public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.yaw = netHeadYaw * 0.017453292F;
        this.head.pitch = headPitch * 0.017453292F;
        this.upper.yaw = netHeadYaw * 0.017453292F * 0.25F;
        float f = MathHelper.sin(this.upper.yaw);
        float g = MathHelper.cos(this.upper.yaw);
    }

    @Override
    public void render(MatrixStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        head.render(matrixStack, buffer, packedLight, packedOverlay);
        upper.render(matrixStack, buffer, packedLight, packedOverlay);
        upper.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    @Override
    public Iterable<ModelPart> getParts() {
        return ImmutableList.of(this.upper, this.lower, this.head);
    }

    public void setRotationAngle(ModelPart bone, float x, float y, float z) {
        bone.pitch = x;
        bone.yaw = y;
        bone.roll = z;
    }

}
