package ladysnake.snowmercy.client.render.entity;

import ladysnake.snowmercy.client.SnowMercyClient;
import ladysnake.snowmercy.client.render.WeaponizedSnowmanPumpkinFeatureRenderer;
import ladysnake.snowmercy.client.render.entity.model.SnugglesEntityModel;
import ladysnake.snowmercy.common.SnowMercy;
import ladysnake.snowmercy.common.entity.SnugglesEntity;
import ladysnake.snowmercy.common.entity.WeaponizedSnowGolemEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class SnugglesEntityRenderer extends WeaponizedSnowGolemEntityRenderer<SnugglesEntity, SnugglesEntityModel<SnugglesEntity>> {
    private static final Identifier TEXTURE = new Identifier(SnowMercy.MODID, "textures/entity/snuggles.png");

    public SnugglesEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new SnugglesEntityModel<>(context.getPart(SnowMercyClient.SNUGGLES_MODEL_LAYER)), 0.5F);
    }

    @Override
    public Identifier getTexture(WeaponizedSnowGolemEntity snowGolemEntity) {
        return TEXTURE;
    }
}
