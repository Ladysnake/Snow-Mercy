package ladysnake.snowmercy.client.render.entity;

import ladysnake.snowmercy.client.SnowMercyClient;
import ladysnake.snowmercy.client.render.entity.model.RocketsEntityModel;
import ladysnake.snowmercy.client.render.entity.model.SnugglesEntityModel;
import ladysnake.snowmercy.common.SnowMercy;
import ladysnake.snowmercy.common.entity.RocketsEntity;
import ladysnake.snowmercy.common.entity.SnugglesEntity;
import ladysnake.snowmercy.common.entity.WeaponizedSnowGolemEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class RocketsEntityRenderer extends WeaponizedSnowGolemEntityRenderer<RocketsEntity, RocketsEntityModel<RocketsEntity>> {
    private static final Identifier TEXTURE = new Identifier(SnowMercy.MODID, "textures/entity/rockets.png");

    public RocketsEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new RocketsEntityModel<>(context.getPart(SnowMercyClient.ROCKETS_MODEL_LAYER)), 0.5F);
    }

    @Override
    public Identifier getTexture(WeaponizedSnowGolemEntity snowGolemEntity) {
        return TEXTURE;
    }
}
