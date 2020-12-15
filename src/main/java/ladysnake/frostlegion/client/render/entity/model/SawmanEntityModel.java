package ladysnake.frostlegion.client.render.entity.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.CompositeEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

public class SawmanEntityModel<T extends Entity> extends CompositeEntityModel<T> {
    private final ModelPart head;
    private final ModelPart headsaw_r1;
    private final ModelPart piece1;
    private final ModelPart piece2;
    private final ModelPart saw;

    public SawmanEntityModel() {
        textureWidth = 128;
        textureHeight = 64;
        head = new ModelPart(this);
        head.setPivot(0.0F, 4.0F, 0.0F);
        head.setTextureOffset(0, 0).addCuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, -0.5F, true);

        headsaw_r1 = new ModelPart(this);
        headsaw_r1.setPivot(-1.0F, -7.0F, -0.5F);
        head.addChild(headsaw_r1);
        setRotationAngle(headsaw_r1, 0.0F, 0.0F, 1.1781F);
        headsaw_r1.setTextureOffset(37, 34).addCuboid(-6.0F, 0.0F, -5.5F, 12.0F, 1.0F, 12.0F, -0.5F, true);

        piece1 = new ModelPart(this);
        piece1.setPivot(0.0F, 13.0F, 0.0F);
        piece1.setTextureOffset(0, 16).addCuboid(-5.0F, -10.0F, -5.0F, 10.0F, 9.0F, 10.0F, -0.5F, true);

        piece2 = new ModelPart(this);
        piece2.setPivot(0.0F, 24.0F, 0.0F);
        piece2.setTextureOffset(0, 35).addCuboid(-6.0F, -11.0F, -6.0F, 12.0F, 11.0F, 12.0F, -0.5F, true);

        saw = new ModelPart(this);
        saw.setPivot(0.0F, -11.5F, 0.0F);
        piece2.addChild(saw);
        saw.setTextureOffset(34, 47).addCuboid(-7.0F, 1.0F, -7.0F, 14.0F, -2.0F, 14.0F, 1.0F, true);
        saw.setTextureOffset(32, 10).addCuboid(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, -0.5F, true);
    }

    @Override
    public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.yaw = netHeadYaw * 0.017453292F;
        this.head.pitch = headPitch * 0.017453292F;
        this.piece1.yaw = netHeadYaw * 0.017453292F * 0.25F;
        this.setRotationAngle(this.saw, 0, entity.age, 0);
    }

    @Override
    public void render(MatrixStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        head.render(matrixStack, buffer, packedLight, packedOverlay);
        piece1.render(matrixStack, buffer, packedLight, packedOverlay);
        piece2.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    @Override
    public Iterable<ModelPart> getParts() {
        return ImmutableList.of(this.piece1, this.piece2, this.head);
    }

    public void setRotationAngle(ModelPart bone, float x, float y, float z) {
        bone.pitch = x;
        bone.yaw = y;
        bone.roll = z;
    }

}
