package ladysnake.snowmercy.client.render.entity;

import ladysnake.snowmercy.client.render.entity.model.HeadmasterEntityModel;
import ladysnake.snowmercy.common.entity.HeadmasterEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class HeadmasterEntityRenderer extends GeoEntityRenderer<HeadmasterEntity> {
    public HeadmasterEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new HeadmasterEntityModel());
        this.shadowRadius = 1.2F;
    }
}
