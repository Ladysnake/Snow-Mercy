package ladysnake.snowmercy.common;

import ladysnake.snowmercy.common.entity.WeaponizedSnowGolemEntity;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;

public class SnowGolemEntityData {
    private final EntityType<? extends WeaponizedSnowGolemEntity> entityType;
    private final TexturedModelData entityModel;
    private final Identifier texture;

    public SnowGolemEntityData(EntityType<? extends WeaponizedSnowGolemEntity> entityType, TexturedModelData entityModel, Identifier texture) {
        this.entityType = entityType;
        this.entityModel = entityModel;
        this.texture = texture;
    }

    public EntityType<? extends WeaponizedSnowGolemEntity> getEntityType() {
        return this.entityType;
    }

    public TexturedModelData getEntityModel() {
        return this.entityModel;
    }

    public Identifier getTexture() {
        return texture;
    }
}
