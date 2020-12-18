package ladysnake.snowmercy.client.render.entity;

import ladysnake.snowmercy.client.render.entity.model.SawmanEntityModel;
import ladysnake.snowmercy.common.SnowMercy;
import ladysnake.snowmercy.common.entity.SawmanEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.util.Identifier;

public class SawmanEntityRenderer extends WeaponizedSnowGolemEntityRenderer<SawmanEntity, SawmanEntityModel<SawmanEntity>> {
    private static final Identifier TEXTURE = new Identifier(SnowMercy.MODID, "textures/entity/sawman.png");

    public SawmanEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher, new SawmanEntityModel<>(), 0.5F);
    }

    public Identifier getTexture(SawmanEntity SawmanEntity) {
        return TEXTURE;
    }
}

