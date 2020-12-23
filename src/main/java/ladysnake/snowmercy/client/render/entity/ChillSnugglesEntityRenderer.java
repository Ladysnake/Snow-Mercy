package ladysnake.snowmercy.client.render.entity;

import ladysnake.snowmercy.client.render.entity.model.SnugglesEntityModel;
import ladysnake.snowmercy.common.SnowMercy;
import ladysnake.snowmercy.common.entity.ChillSnugglesEntity;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.util.Identifier;

public class ChillSnugglesEntityRenderer extends WeaponizedSnowGolemEntityRenderer<ChillSnugglesEntity, SnugglesEntityModel<ChillSnugglesEntity>> {
    private static final Identifier TEXTURE = new Identifier(SnowMercy.MODID, "textures/entity/chill_snuggles.png");

    public ChillSnugglesEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher, new SnugglesEntityModel<>(), 0.5F);
    }

    public Identifier getTexture(ChillSnugglesEntity snugglesEntity) {
        return TEXTURE;
    }
}

