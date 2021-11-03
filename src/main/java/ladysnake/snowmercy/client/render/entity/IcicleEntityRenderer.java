package ladysnake.snowmercy.client.render.entity;

import ladysnake.snowmercy.common.SnowMercy;
import ladysnake.snowmercy.common.entity.IcicleEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;

public class IcicleEntityRenderer extends ProjectileEntityRenderer<IcicleEntity> {
    public static final Identifier TEXTURE = new Identifier(SnowMercy.MODID, "textures/entity/projectiles/icicle.png");

    public IcicleEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    public Identifier getTexture(IcicleEntity arrowEntity) {
        return TEXTURE;
    }
}
