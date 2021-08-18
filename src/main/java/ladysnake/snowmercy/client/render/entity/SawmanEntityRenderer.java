package ladysnake.snowmercy.client.render.entity;

import ladysnake.snowmercy.client.SnowMercyClient;
import ladysnake.snowmercy.client.render.entity.model.SawmanEntityModel;
import ladysnake.snowmercy.client.render.entity.model.SnugglesEntityModel;
import ladysnake.snowmercy.common.SnowMercy;
import ladysnake.snowmercy.common.entity.SawmanEntity;
import ladysnake.snowmercy.common.entity.SnugglesEntity;
import ladysnake.snowmercy.common.entity.WeaponizedSnowGolemEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class SawmanEntityRenderer extends WeaponizedSnowGolemEntityRenderer<SawmanEntity, SawmanEntityModel<SawmanEntity>> {
    private static final Identifier TEXTURE = new Identifier(SnowMercy.MODID, "textures/entity/sawman.png");

    public SawmanEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new SawmanEntityModel<>(context.getPart(SnowMercyClient.SAWMAN_MODEL_LAYER)), 0.5F);
    }

    @Override
    public Identifier getTexture(WeaponizedSnowGolemEntity snowGolemEntity) {
        return TEXTURE;
    }
}
