package ladysnake.frostlegion.client.render.entity;

import ladysnake.frostlegion.client.render.entity.model.SnowmineEntityModel;
import ladysnake.frostlegion.common.FrostLegion;
import ladysnake.frostlegion.common.entity.SnowmineEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class SnowmineEntityRenderer extends MobEntityRenderer<SnowmineEntity, SnowmineEntityModel<SnowmineEntity>> {
    private static final Identifier TEXTURE = new Identifier(FrostLegion.MODID, "textures/entity/snowmine.png");

    public SnowmineEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher, new SnowmineEntityModel(), 0.5F);
    }

    public Identifier getTexture(SnowmineEntity snowmineEntity) {
        return TEXTURE;
    }
}

