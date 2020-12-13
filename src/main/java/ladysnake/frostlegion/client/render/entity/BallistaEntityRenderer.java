package ladysnake.frostlegion.client.render.entity;

import ladysnake.frostlegion.client.render.entity.model.BallistaEntityModel;
import ladysnake.frostlegion.client.render.entity.model.SnowgglerEntityModel;
import ladysnake.frostlegion.common.FrostLegion;
import ladysnake.frostlegion.common.entity.BallistaEntity;
import ladysnake.frostlegion.common.entity.SnowgglerEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class BallistaEntityRenderer extends MobEntityRenderer<BallistaEntity, BallistaEntityModel<BallistaEntity>> {
    private static final Identifier TEXTURE = new Identifier(FrostLegion.MODID, "textures/entity/ballista.png");

    public BallistaEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher, new BallistaEntityModel(), 0.5F);
    }

    public Identifier getTexture(BallistaEntity ballistaEntityModel) {
        return TEXTURE;
    }
}

