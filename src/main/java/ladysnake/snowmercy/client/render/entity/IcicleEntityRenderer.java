package ladysnake.snowmercy.client.render.entity;

import ladysnake.snowmercy.common.SnowMercy;
import ladysnake.snowmercy.common.entity.IcicleEntity;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;

public class IcicleEntityRenderer extends ProjectileEntityRenderer<IcicleEntity> {
    public static final Identifier TEXTURE = new Identifier(SnowMercy.MODID, "textures/entity/projectiles/icicle.png");

    public IcicleEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher);
    }

    public Identifier getTexture(IcicleEntity icicleEntity) {
        return TEXTURE;
    }
}
