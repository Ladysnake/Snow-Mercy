package ladysnake.frostlegion.common;

import ladysnake.frostlegion.common.entity.WeaponizedSnowGolemEntity;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;

public class SnowGolemEntityData {
    private EntityType<? extends WeaponizedSnowGolemEntity> entityType;
    private EntityModel<? extends WeaponizedSnowGolemEntity> entityModel;
    private Identifier texture;


    public SnowGolemEntityData(EntityType<? extends WeaponizedSnowGolemEntity> entityType, EntityModel<? extends WeaponizedSnowGolemEntity> entityModel, Identifier texture) {
        this.entityType = entityType;
        this.entityModel = entityModel;
        this.texture = texture;
    }

    public EntityType<? extends WeaponizedSnowGolemEntity> getEntityType() {
        return this.entityType;
    }

    public EntityModel<? extends WeaponizedSnowGolemEntity> getEntityModel() {
        return this.entityModel;
    }

    public Identifier getTexture() {
        return texture;
    }
}
