package ladysnake.frostlegion.client.render.entity;

import ladysnake.frostlegion.client.render.entity.model.SnowblobEntityModel;
import ladysnake.frostlegion.common.entity.SnowblobEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import software.bernie.geckolib3.renderer.geo.GeoEntityRenderer;

@Environment(EnvType.CLIENT)
public class SnowblobEntityRenderer extends GeoEntityRenderer<SnowblobEntity> {
    public SnowblobEntityRenderer(EntityRenderDispatcher renderManager) {
        super(renderManager, new SnowblobEntityModel());
    }
}

