package ladysnake.frostlegion.client.render.entity;

import ladysnake.frostlegion.common.FrostLegion;
import ladysnake.frostlegion.common.entity.IcicleEntity;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.util.Identifier;

public class IcicleEntityRenderer extends ProjectileEntityRenderer<IcicleEntity> {
    public static final Identifier TEXTURE = new Identifier(FrostLegion.MODID, "textures/entity/projectiles/icicle.png");

    public IcicleEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher);
    }

    public Identifier getTexture(IcicleEntity icicleEntity) {
        return TEXTURE;
    }
}
