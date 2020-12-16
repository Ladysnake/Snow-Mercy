package ladysnake.frostlegion.client.render.entity;

import ladysnake.frostlegion.client.render.entity.model.RocketsEntityModel;
import ladysnake.frostlegion.common.FrostLegion;
import ladysnake.frostlegion.common.entity.RocketsEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class RocketsEntityRenderer extends WeaponizedSnowGolemEntityRenderer<RocketsEntity, RocketsEntityModel<RocketsEntity>> {
    private static final Identifier TEXTURE = new Identifier(FrostLegion.MODID, "textures/entity/rockets.png");

    public RocketsEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher, new RocketsEntityModel<>(), 0.5F);
    }

    public Identifier getTexture(RocketsEntity rocketsEntity) {
        return TEXTURE;
    }
}

