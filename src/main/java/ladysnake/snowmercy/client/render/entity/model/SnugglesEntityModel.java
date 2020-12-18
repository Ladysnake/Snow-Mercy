package ladysnake.snowmercy.client.render.entity.model;

import net.minecraft.client.model.ModelPart;
import net.minecraft.entity.Entity;

public class SnugglesEntityModel<T extends Entity> extends WeaponizedSnowGolemEntityModel<T> {
    private final ModelPart tnt;

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

        piece1 = new ModelPart(this);
        piece1.setPivot(0.0F, 13.0F, 0.0F);
        piece1.setTextureOffset(0, 15).addCuboid(-5.0F, -10.0F, -5.0F, 10.0F, 10.0F, 10.0F, -0.5F, true);

        piece2 = new ModelPart(this);
        piece2.setPivot(0.0F, 24.0F, 0.0F);
        piece2.setTextureOffset(0, 35).addCuboid(-6.0F, -12.0F, -6.0F, 12.0F, 12.0F, 12.0F, -0.5F, true);
    }
}
