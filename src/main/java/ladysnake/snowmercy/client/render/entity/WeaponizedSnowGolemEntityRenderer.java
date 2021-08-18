package ladysnake.snowmercy.client.render.entity;

import ladysnake.snowmercy.common.entity.WeaponizedSnowGolemEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.feature.SnowmanPumpkinFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.SnowGolemEntityModel;
import net.minecraft.entity.passive.SnowGolemEntity;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class WeaponizedSnowGolemEntityRenderer extends MobEntityRenderer<WeaponizedSnowGolemEntity, SnowGolemEntityModel<WeaponizedSnowGolemEntity>> {
    private static final Identifier TEXTURE = new Identifier("textures/entity/snow_golem.png");

    public WeaponizedSnowGolemEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new SnowGolemEntityModel<>(context.getPart(EntityModelLayers.SNOW_GOLEM)), 0.5F);
        //this.addFeature(new SnowmanPumpkinFeatureRenderer(this));
    }

    public Identifier getTexture(WeaponizedSnowGolemEntity snowGolemEntity) {
        return TEXTURE;
    }
}
