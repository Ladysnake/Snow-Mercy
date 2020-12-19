package ladysnake.snowmercy.client.render.entity;

import ladysnake.snowmercy.client.render.entity.model.SnugglesEntityModel;
import ladysnake.snowmercy.common.SnowMercy;
import ladysnake.snowmercy.common.entity.SnugglesEntity;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.util.Identifier;

public class SnugglesEntityRenderer extends WeaponizedSnowGolemEntityRenderer<SnugglesEntity, SnugglesEntityModel<SnugglesEntity>> {
    private static final Identifier TEXTURE = new Identifier(SnowMercy.MODID, "textures/entity/snuggles.png");

    public SnugglesEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher, new SnugglesEntityModel<>(), 0.5F);
    }

    public Identifier getTexture(SnugglesEntity snugglesEntity) {
        return TEXTURE;
    }
}

