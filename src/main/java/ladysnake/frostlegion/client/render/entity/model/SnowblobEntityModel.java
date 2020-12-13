package ladysnake.frostlegion.client.render.entity.model;

import ladysnake.frostlegion.common.FrostLegion;
import ladysnake.frostlegion.common.entity.SnowblobEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SnowblobEntityModel extends AnimatedGeoModel<SnowblobEntity> {
    @Override
    public Identifier getModelLocation(SnowblobEntity object) {
        return new Identifier(FrostLegion.MODID, "geo/entity/snowblob.geo.json");
    }

    @Override
    public Identifier getTextureLocation(SnowblobEntity object) {
        return new Identifier(FrostLegion.MODID, "textures/entity/snowblob.png");
    }

    @Override
    public Identifier getAnimationFileLocation(SnowblobEntity snowblobEntity) {
        return new Identifier(FrostLegion.MODID, "animations/entity/snowblob.animation.json");
    }
}