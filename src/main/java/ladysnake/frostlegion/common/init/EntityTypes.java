package ladysnake.frostlegion.common.init;

import ladysnake.frostlegion.common.FrostLegion;
import ladysnake.frostlegion.common.entity.SnowblobEntity;
import ladysnake.frostlegion.common.entity.SnowgglerEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.registry.Registry;

public class EntityTypes {
    public static EntityType<SnowgglerEntity> SNOWGGLER;
    public static EntityType<SnowblobEntity> SNOWBLOB;

    public static void init() {
        SNOWBLOB = register("snowblob", FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, SnowblobEntity::new).dimensions(EntityDimensions.changing(0.5F, 0.5F)).trackRangeBlocks(8).build());
        SNOWGGLER = register("snowggler", FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, SnowgglerEntity::new).dimensions(EntityDimensions.changing(0.7F, 1.9F)).trackRangeBlocks(8).build());

        FabricDefaultAttributeRegistry.register(EntityTypes.SNOWBLOB, SnowblobEntity.createEntityAttributes());
        FabricDefaultAttributeRegistry.register(EntityTypes.SNOWGGLER, SnowgglerEntity.createEntityAttributes());
    }

    private static <T extends Entity> EntityType<T> register(String s, EntityType<T> entityType) {
        return Registry.register(Registry.ENTITY_TYPE, FrostLegion.MODID + ":" + s, entityType);
    }
}
