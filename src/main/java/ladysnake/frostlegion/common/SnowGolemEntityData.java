package ladysnake.frostlegion.common;

import ladysnake.frostlegion.common.entity.EvilSnowGolemEntity;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;

public class SnowGolemEntityData {
    private EntityType<? extends EvilSnowGolemEntity> entityType;
    private EntityModel<? extends EvilSnowGolemEntity> entityModel;
    private Identifier texture;


    public SnowGolemEntityData(EntityType<? extends EvilSnowGolemEntity> entityType, EntityModel<? extends EvilSnowGolemEntity> entityModel, Identifier texture) {
        this.entityType = entityType;
        this.entityModel = entityModel;
        this.texture = texture;
    }

    public EntityType<? extends EvilSnowGolemEntity> getEntityType() {
        return this.entityType;
    }

    public EntityModel<? extends EvilSnowGolemEntity> getEntityModel() {
        return this.entityModel;
    }

    public Identifier getTexture() {
        return texture;
    }
}
