package ladysnake.frostlegion.client.render.entity;

import ladysnake.frostlegion.client.render.entity.model.SnowgglerEntityModel;
import ladysnake.frostlegion.common.FrostLegion;
import ladysnake.frostlegion.common.entity.SnowgglerEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class SnowgglerEntityRenderer extends MobEntityRenderer<SnowgglerEntity, SnowgglerEntityModel<SnowgglerEntity>> {
    private static final Identifier TEXTURE = new Identifier(FrostLegion.MODID, "textures/entity/snowmine.png");

    public SnowgglerEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher, new SnowgglerEntityModel(), 0.5F);
    }

    public Identifier getTexture(SnowgglerEntity snowgglerEntity) {
        return TEXTURE;
    }
}

