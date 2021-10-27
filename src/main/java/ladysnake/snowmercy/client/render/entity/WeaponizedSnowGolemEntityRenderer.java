package ladysnake.snowmercy.client.render.entity;

import ladysnake.snowmercy.client.render.WeaponizedSnowmanPumpkinFeatureRenderer;
import ladysnake.snowmercy.client.render.entity.model.WeaponizedSnowGolemEntityModel;
import ladysnake.snowmercy.common.entity.WeaponizedSnowGolemEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class WeaponizedSnowGolemEntityRenderer<E extends WeaponizedSnowGolemEntity, M extends WeaponizedSnowGolemEntityModel<E>> extends MobEntityRenderer<E, M> {
    private final Identifier texture;

    public WeaponizedSnowGolemEntityRenderer(EntityRendererFactory.Context context, M entityModel, float shadowRadius, Identifier texture) {
        super(context, entityModel, shadowRadius);
        this.texture = texture;
        this.addFeature(new WeaponizedSnowmanPumpkinFeatureRenderer<>(this));
    }

    public Identifier getTexture(WeaponizedSnowGolemEntity snowGolemEntity) {
        return texture;
    }
}
