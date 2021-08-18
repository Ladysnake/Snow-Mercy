package ladysnake.snowmercy.client.render.entity;

import ladysnake.snowmercy.client.SnowMercyClient;
import ladysnake.snowmercy.client.render.WeaponizedSnowmanPumpkinFeatureRenderer;
import ladysnake.snowmercy.client.render.entity.model.WeaponizedSnowGolemEntityModel;
import ladysnake.snowmercy.common.entity.WeaponizedSnowGolemEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class WeaponizedSnowGolemEntityRenderer<T extends MobEntity, M extends EntityModel<T>> extends MobEntityRenderer<WeaponizedSnowGolemEntity, WeaponizedSnowGolemEntityModel<WeaponizedSnowGolemEntity>> {
    private static final Identifier TEXTURE = new Identifier("textures/entity/snow_golem.png");

    public WeaponizedSnowGolemEntityRenderer(EntityRendererFactory.Context context, M entityModel, float f) {
        super(context, (WeaponizedSnowGolemEntityModel<WeaponizedSnowGolemEntity>) entityModel, f);
        this.addFeature(new WeaponizedSnowmanPumpkinFeatureRenderer(this));
    }

    public WeaponizedSnowGolemEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new WeaponizedSnowGolemEntityModel<>(context.getPart(SnowMercyClient.SNOW_GOLEM_HEAD_MODEL_LAYER)), 0.5F);
        this.addFeature(new WeaponizedSnowmanPumpkinFeatureRenderer(this));
    }

    public Identifier getTexture(WeaponizedSnowGolemEntity snowGolemEntity) {
        return TEXTURE;
    }
}
