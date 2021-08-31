package ladysnake.snowmercy.common;

import ladysnake.snowmercy.common.entity.WeaponizedSnowGolemEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class SnowGolemEntityData {
    private final EntityType<? extends WeaponizedSnowGolemEntity> entityType;
    private final Function<EntityRendererFactory.Context, ? extends WeaponizedSnowGolemEntity> entityModel;
    private final Identifier texture;
    private final EntityModelLayer layer;

    public SnowGolemEntityData(EntityType<? extends WeaponizedSnowGolemEntity> entityType,
                               Function<EntityRendererFactory.Context, ? extends WeaponizedSnowGolemEntity> entityModel,
                               Identifier texture, EntityModelLayer layer) {
        this.entityType = entityType;
        this.entityModel = entityModel;
        this.texture = texture;
        this.layer = layer;
    }

    public EntityType<? extends WeaponizedSnowGolemEntity> getEntityType() {
        return this.entityType;
    }

    public Function<EntityRendererFactory.Context, ? extends WeaponizedSnowGolemEntity> getEntityModel() {
        return this.entityModel;
    }

    public Identifier getTexture() {
        return texture;
    }

    public EntityModelLayer getLayer() {
        return layer;
    }
}
