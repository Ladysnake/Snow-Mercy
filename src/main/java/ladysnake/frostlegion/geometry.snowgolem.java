// Made with Blockbench 3.7.4
	// Exported for Minecraft version 1.15
	// Paste this class into your mod and generate all required imports

	package com.example.mod;

	public class geometry.snowgolem extends EntityModel<Entity> {
private final ModelPart head;
	private final ModelPart piece1;
	private final ModelPart piece2;
public geometry.snowgolem() {
		textureWidth = 64;
		textureHeight = 64;
		head = new ModelPart(this);
		head.setPivot(0.0F, 4.0F, 0.0F);
		head.setTextureOffset(0, 0).addCuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, -0.5F, true);
		head.setTextureOffset(48, 0).addCuboid(-2.0F, -11.0F, -2.0F, 4.0F, 4.0F, 4.0F, -0.5F, false);
		head.setTextureOffset(42, 3).addCuboid(-0.5F, -13.0F, -0.75F, 1.0F, 3.0F, 2.0F, -0.5F, false);

		piece1 = new ModelPart(this);
		piece1.setPivot(0.0F, 13.0F, 0.0F);
		piece1.setTextureOffset(0, 16).addCuboid(-5.0F, -10.0F, -5.0F, 10.0F, 10.0F, 10.0F, -0.5F, true);

		piece2 = new ModelPart(this);
		piece2.setPivot(0.0F, 24.0F, 0.0F);
		piece2.setTextureOffset(0, 36).addCuboid(-6.0F, -12.0F, -6.0F, 12.0F, 12.0F, 12.0F, -0.5F, true);
}
@Override
public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
}
@Override
public void render(MatrixStack matrixStack, VertexConsumer	buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		
		head.render(matrixStack, buffer, packedLight, packedOverlay);
		piece1.render(matrixStack, buffer, packedLight, packedOverlay);
		piece2.render(matrixStack, buffer, packedLight, packedOverlay);
}
public void setRotationAngle(ModelPart bone, float x, float y, float z) {
		bone.pitch = x;
		bone.yaw = y;
		bone.roll = z;
}

	}