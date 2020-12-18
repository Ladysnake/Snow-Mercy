package ladysnake.snowmercy.client.render.entity.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.CompositeEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

public class WeaponizedSnowGolemEntityModel<T extends Entity> extends CompositeEntityModel<T> {
    public ModelPart head;
    public ModelPart piece1;
    public ModelPart piece2;

    public WeaponizedSnowGolemEntityModel() {
        head = new ModelPart(this);
        head.setPivot(0.0F, 4.0F, 0.0F);
        head.setTextureOffset(0, 0).addCuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, -0.5F, true);

        piece1 = new ModelPart(this);
        piece1.setPivot(0.0F, 13.0F, 0.0F);
        piece1.setTextureOffset(0, 16).addCuboid(-5.0F, -10.0F, -5.0F, 10.0F, 10.0F, 10.0F, -0.5F, true);

        piece2 = new ModelPart(this);
        piece2.setPivot(0.0F, 24.0F, 0.0F);
        piece2.setTextureOffset(0, 36).addCuboid(-6.0F, -12.0F, -6.0F, 12.0F, 12.0F, 12.0F, -0.5F, true);
    }

    @Override
    public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.yaw = netHeadYaw * 0.017453292F;
        this.head.pitch = headPitch * 0.017453292F;
        this.piece1.yaw = netHeadYaw * 0.017453292F * 0.25F;
    }

    public void setRotationAngle(ModelPart bone, float x, float y, float z) {
        bone.pitch = x;
        bone.yaw = y;
        bone.roll = z;
    }

    @Override
    public Iterable<ModelPart> getParts() {
        return ImmutableList.of(head, piece1, piece2);
    }

    @Override
    public void render(MatrixStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        head.render(matrixStack, buffer, packedLight, packedOverlay);
        piece1.render(matrixStack, buffer, packedLight, packedOverlay);
        piece2.render(matrixStack, buffer, packedLight, packedOverlay);
    }
}
