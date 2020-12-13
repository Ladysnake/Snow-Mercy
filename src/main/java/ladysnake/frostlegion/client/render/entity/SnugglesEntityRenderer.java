package ladysnake.frostlegion.client.render.entity;

import ladysnake.frostlegion.client.render.entity.model.SnugglesEntityModel;
import ladysnake.frostlegion.common.FrostLegion;
import ladysnake.frostlegion.common.entity.SnugglesEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class SnugglesEntityRenderer extends MobEntityRenderer<SnugglesEntity, SnugglesEntityModel<SnugglesEntity>> {
    private static final Identifier TEXTURE = new Identifier(FrostLegion.MODID, "textures/entity/snuggles.png");

    public SnugglesEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher, new SnugglesEntityModel(), 0.5F);
    }

    public Identifier getTexture(SnugglesEntity snugglesEntity) {
        return TEXTURE;
    }
}

