package ladysnake.frostlegion.client.render.entity;

import ladysnake.frostlegion.client.render.entity.model.SawmanEntityModel;
import ladysnake.frostlegion.common.FrostLegion;
import ladysnake.frostlegion.common.entity.SawmanEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class SawmanEntityRenderer extends WeaponizedSnowGolemEntityRenderer<SawmanEntity, SawmanEntityModel<SawmanEntity>> {
    private static final Identifier TEXTURE = new Identifier(FrostLegion.MODID, "textures/entity/sawman.png");

    public SawmanEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher, new SawmanEntityModel<>(), 0.5F);
    }

    public Identifier getTexture(SawmanEntity SawmanEntity) {
        return TEXTURE;
    }
}

