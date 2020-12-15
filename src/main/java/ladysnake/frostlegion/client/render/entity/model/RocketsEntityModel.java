package ladysnake.frostlegion.client.render.entity.model;

import net.minecraft.client.model.ModelPart;
import net.minecraft.entity.Entity;

public class RocketsEntityModel<T extends Entity> extends EvilSnowGolemEntityModel<T> {
    private final ModelPart launcher;
    private final ModelPart rocket;
    private final ModelPart tip;
    private final ModelPart tip_r1;
    private final ModelPart rocket2;
    private final ModelPart tip2;
    private final ModelPart launcher2;
    private final ModelPart rocket3;
    private final ModelPart tip3;
    private final ModelPart tip_r2;
    private final ModelPart rocket4;
    private final ModelPart tip4;

    public RocketsEntityModel() {
        textureWidth = 128;
        textureHeight = 64;

        head = new ModelPart(this);
        head.setPivot(0.0F, 4.0F, 0.0F);
        head.setTextureOffset(0, 0).addCuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, -0.5F, true);
        head.setTextureOffset(34, 0).addCuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, true);

        piece1 = new ModelPart(this);
        piece1.setPivot(0.0F, 13.0F, 0.0F);
        piece1.setTextureOffset(0, 16).addCuboid(-5.0F, -10.0F, -5.0F, 10.0F, 10.0F, 10.0F, -0.5F, true);

        launcher = new ModelPart(this);
        launcher.setPivot(-6.5F, -6.5F, -1.0F);
        piece1.addChild(launcher);
        launcher.setTextureOffset(40, 23).addCuboid(-2.5F, -2.5F, -3.0F, 5.0F, 5.0F, 8.0F, -0.5F, true);

        rocket = new ModelPart(this);
        rocket.setPivot(0.0F, 0.0F, 0.0F);
        launcher.addChild(rocket);
        rocket.setTextureOffset(66, 25).addCuboid(-1.5F, -1.5F, -4.0F, 3.0F, 3.0F, 8.0F, -0.5F, true);

        tip = new ModelPart(this);
        tip.setPivot(0.0F, 0.0F, 0.0F);
        rocket.addChild(tip);
        tip.setTextureOffset(88, 28).addCuboid(-0.5F, -2.5F, -7.0F, 1.0F, 5.0F, 4.0F, -0.5F, true);

        tip_r1 = new ModelPart(this);
        tip_r1.setPivot(0.0F, 0.0F, -7.0F);
        tip.addChild(tip_r1);
        setRotationAngle(tip_r1, 0.0F, 0.0F, -1.5708F);
        tip_r1.setTextureOffset(88, 28).addCuboid(-0.5F, -2.5F, 0.0F, 1.0F, 5.0F, 4.0F, -0.5F, true);

        rocket2 = new ModelPart(this);
        rocket2.setPivot(0.0F, 0.0F, -12.0F);
        launcher.addChild(rocket2);
        setRotationAngle(rocket2, 0.0F, 0.0873F, 0.0F);

        tip2 = new ModelPart(this);
        tip2.setPivot(0.0F, 0.0F, 0.0F);
        rocket2.addChild(tip2);

        launcher2 = new ModelPart(this);
        launcher2.setPivot(6.5F, -6.5F, -1.0F);
        piece1.addChild(launcher2);
        launcher2.setTextureOffset(40, 23).addCuboid(-2.5F, -2.5F, -3.0F, 5.0F, 5.0F, 8.0F, -0.5F, true);

        rocket3 = new ModelPart(this);
        rocket3.setPivot(0.0F, 0.0F, 0.0F);
        launcher2.addChild(rocket3);
        rocket3.setTextureOffset(66, 25).addCuboid(-1.5F, -1.5F, -4.0F, 3.0F, 3.0F, 8.0F, -0.5F, true);

        tip3 = new ModelPart(this);
        tip3.setPivot(0.0F, 0.0F, 0.0F);
        rocket3.addChild(tip3);
        tip3.setTextureOffset(88, 28).addCuboid(-0.5F, -2.5F, -7.0F, 1.0F, 5.0F, 4.0F, -0.5F, true);

        tip_r2 = new ModelPart(this);
        tip_r2.setPivot(0.0F, 0.0F, -7.0F);
        tip3.addChild(tip_r2);
        setRotationAngle(tip_r2, 0.0F, 0.0F, -1.5708F);
        tip_r2.setTextureOffset(88, 28).addCuboid(-0.5F, -2.5F, 0.0F, 1.0F, 5.0F, 4.0F, -0.5F, true);

        rocket4 = new ModelPart(this);
        rocket4.setPivot(0.0F, 0.0F, -12.0F);
        launcher2.addChild(rocket4);
        setRotationAngle(rocket4, 0.0F, 0.0873F, 0.0F);

        tip4 = new ModelPart(this);
        tip4.setPivot(0.0F, 0.0F, 0.0F);
        rocket4.addChild(tip4);

        piece2 = new ModelPart(this);
        piece2.setPivot(0.0F, 24.0F, 0.0F);
        piece2.setTextureOffset(0, 36).addCuboid(-6.0F, -12.0F, -6.0F, 12.0F, 12.0F, 12.0F, -0.5F, true);
    }
}
