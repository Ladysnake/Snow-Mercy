package ladysnake.snowmercy.client.render.entity;

import ladysnake.snowmercy.client.render.entity.model.RocketsEntityModel;
import ladysnake.snowmercy.common.SnowMercy;
import ladysnake.snowmercy.common.entity.RocketsEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.util.Identifier;

public class RocketsEntityRenderer extends WeaponizedSnowGolemEntityRenderer<RocketsEntity, RocketsEntityModel<RocketsEntity>> {
    private static final Identifier TEXTURE = new Identifier(SnowMercy.MODID, "textures/entity/rockets.png");

    public RocketsEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher, new RocketsEntityModel<>(), 0.5F);
    }

    public Identifier getTexture(RocketsEntity rocketsEntity) {
        return TEXTURE;
    }
}

