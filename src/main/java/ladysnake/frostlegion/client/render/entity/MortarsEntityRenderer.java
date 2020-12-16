package ladysnake.frostlegion.client.render.entity;

import ladysnake.frostlegion.client.render.entity.model.MortarsEntityModel;
import ladysnake.frostlegion.common.FrostLegion;
import ladysnake.frostlegion.common.entity.MortarsEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class MortarsEntityRenderer extends WeaponizedSnowGolemEntityRenderer<MortarsEntity, MortarsEntityModel<MortarsEntity>> {
    private static final Identifier TEXTURE = new Identifier(FrostLegion.MODID, "textures/entity/mortars.png");

    public MortarsEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher, new MortarsEntityModel<>(), 0.5F);
    }

    public Identifier getTexture(MortarsEntity mortarsEntity) {
        return TEXTURE;
    }
}

