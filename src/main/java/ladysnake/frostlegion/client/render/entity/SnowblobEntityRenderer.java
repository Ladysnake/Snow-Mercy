package ladysnake.frostlegion.client.render.entity;

import ladysnake.frostlegion.client.render.entity.model.SnowblobEntityModel;
import ladysnake.frostlegion.client.render.entity.model.SnowgglerEntityModel;
import ladysnake.frostlegion.common.FrostLegion;
import ladysnake.frostlegion.common.entity.SnowblobEntity;
import ladysnake.frostlegion.common.entity.SnowgglerEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class SnowblobEntityRenderer extends MobEntityRenderer<SnowblobEntity, SnowblobEntityModel<SnowblobEntity>> {
    private static final Identifier TEXTURE = new Identifier(FrostLegion.MODID, "textures/entity/snowblob.png");

    public SnowblobEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher, new SnowblobEntityModel(), 0.5F);
    }

    public Identifier getTexture(SnowblobEntity snowgglerEntity) {
        return TEXTURE;
    }
}

