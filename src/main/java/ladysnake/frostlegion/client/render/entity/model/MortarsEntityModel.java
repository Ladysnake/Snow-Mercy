package ladysnake.frostlegion.client.render.entity.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.CompositeEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

public class MortarsEntityModel<T extends Entity> extends CompositeEntityModel<T> {
    private final ModelPart head;
    private final ModelPart helmet_r1;
    private final ModelPart piece1;
    private final ModelPart mortar;
    private final ModelPart launcher_r1;
    private final ModelPart icicle;
    private final ModelPart cube_r1;
    private final ModelPart icicle2;
    private final ModelPart cube_r2;
    private final ModelPart icicle3;
    private final ModelPart cube_r3;
    private final ModelPart icicle4;
    private final ModelPart cube_r4;
    private final ModelPart piece2;

    public MortarsEntityModel() {
        textureWidth = 128;
        textureHeight = 64;
        head = new ModelPart(this);
        head.setPivot(0.0F, 4.0F, 0.0F);
        head.setTextureOffset(0, 0).addCuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, -0.5F, true);

        helmet_r1 = new ModelPart(this);
        helmet_r1.setPivot(0.0F, -4.0F, 0.0F);
        head.addChild(helmet_r1);
        setRotationAngle(helmet_r1, -0.1309F, 0.0F, 0.0F);
        helmet_r1.setTextureOffset(32, 0).addCuboid(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, true);

        piece1 = new ModelPart(this);
        piece1.setPivot(0.0F, 13.0F, 0.0F);
        piece1.setTextureOffset(0, 16).addCuboid(-5.0F, -10.0F, -5.0F, 10.0F, 10.0F, 10.0F, -0.5F, true);

        mortar = new ModelPart(this);
        mortar.setPivot(0.0F, -2.0F, -6.0F);
        piece1.addChild(mortar);


        launcher_r1 = new ModelPart(this);
        launcher_r1.setPivot(0.0F, 0.0F, 0.0F);
        mortar.addChild(launcher_r1);
        setRotationAngle(launcher_r1, 0.3054F, 0.0F, 0.0F);
        launcher_r1.setTextureOffset(48, 38).addCuboid(-4.0F, -7.0F, -4.0F, 8.0F, 14.0F, 8.0F, -0.5F, true);

        icicle = new ModelPart(this);
        icicle.setPivot(-1.5F, -5.0F, 0.0F);
        mortar.addChild(icicle);
        setRotationAngle(icicle, 0.3054F, 0.0F, 0.0F);
        icicle.setTextureOffset(80, 49).addCuboid(-1.5F, -11.0F, 0.0F, 3.0F, 11.0F, 0.0F, 0.0F, false);

        cube_r1 = new ModelPart(this);
        cube_r1.setPivot(0.0F, -5.5F, 0.0F);
        icicle.addChild(cube_r1);
        setRotationAngle(cube_r1, 0.0F, -1.5708F, 0.0F);
        cube_r1.setTextureOffset(80, 49).addCuboid(-1.5F, -5.5F, 0.0F, 3.0F, 11.0F, 0.0F, 0.0F, false);

        icicle2 = new ModelPart(this);
        icicle2.setPivot(1.5F, -5.0F, 0.0F);
        mortar.addChild(icicle2);
        setRotationAngle(icicle2, 0.3054F, 0.0F, 0.0F);
        icicle2.setTextureOffset(80, 49).addCuboid(-1.5F, -11.0F, 0.0F, 3.0F, 11.0F, 0.0F, 0.0F, false);

        cube_r2 = new ModelPart(this);
        cube_r2.setPivot(0.0F, -5.5F, 0.0F);
        icicle2.addChild(cube_r2);
        setRotationAngle(cube_r2, 0.0F, -1.5708F, 0.0F);
        cube_r2.setTextureOffset(80, 49).addCuboid(-1.5F, -5.5F, 0.0F, 3.0F, 11.0F, 0.0F, 0.0F, false);

        icicle3 = new ModelPart(this);
        icicle3.setPivot(-1.5F, -4.0F, -3.0F);
        mortar.addChild(icicle3);
        setRotationAngle(icicle3, 0.3054F, 0.0F, 0.0F);
        icicle3.setTextureOffset(80, 49).addCuboid(-1.5F, -11.0F, 0.0F, 3.0F, 11.0F, 0.0F, 0.0F, false);

        cube_r3 = new ModelPart(this);
        cube_r3.setPivot(0.0F, -5.5F, 0.0F);
        icicle3.addChild(cube_r3);
        setRotationAngle(cube_r3, 0.0F, -1.5708F, 0.0F);
        cube_r3.setTextureOffset(80, 49).addCuboid(-1.5F, -5.5F, 0.0F, 3.0F, 11.0F, 0.0F, 0.0F, false);

        icicle4 = new ModelPart(this);
        icicle4.setPivot(1.5F, -4.0F, -3.0F);
        mortar.addChild(icicle4);
        setRotationAngle(icicle4, 0.3054F, 0.0F, 0.0F);
        icicle4.setTextureOffset(80, 49).addCuboid(-1.5F, -11.0F, 0.0F, 3.0F, 11.0F, 0.0F, 0.0F, false);

        cube_r4 = new ModelPart(this);
        cube_r4.setPivot(0.0F, -5.5F, 0.0F);
        icicle4.addChild(cube_r4);
        setRotationAngle(cube_r4, 0.0F, -1.5708F, 0.0F);
        cube_r4.setTextureOffset(80, 49).addCuboid(-1.5F, -5.5F, 0.0F, 3.0F, 11.0F, 0.0F, 0.0F, false);

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

    @Override
    public void render(MatrixStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        head.render(matrixStack, buffer, packedLight, packedOverlay);
        piece1.render(matrixStack, buffer, packedLight, packedOverlay);
        piece2.render(matrixStack, buffer, packedLight, packedOverlay);
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
}
