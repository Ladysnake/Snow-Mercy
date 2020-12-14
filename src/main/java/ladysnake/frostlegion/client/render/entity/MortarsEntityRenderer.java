package ladysnake.frostlegion.client.render.entity;

import ladysnake.frostlegion.client.render.entity.model.RocketsEntityModel;
import ladysnake.frostlegion.common.FrostLegion;
import ladysnake.frostlegion.common.entity.MortarsEntity;
import ladysnake.frostlegion.common.entity.RocketsEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class MortarsEntityRenderer extends MobEntityRenderer<MortarsEntity, RocketsEntityModel<MortarsEntity>> {
    private static final Identifier TEXTURE = new Identifier(FrostLegion.MODID, "textures/entity/rockets.png");

    public MortarsEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher, new RocketsEntityModel<>(), 0.5F);
    }

    public Identifier getTexture(MortarsEntity rocketsEntity) {
        return TEXTURE;
    }
}

