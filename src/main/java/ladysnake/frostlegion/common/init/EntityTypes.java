package ladysnake.frostlegion.common.init;

import ladysnake.frostlegion.common.FrostLegion;
import ladysnake.frostlegion.common.entity.*;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.registry.Registry;

public class EntityTypes {
    public static EntityType<SnugglesEntity> SNUGGLES;
    public static EntityType<RocketsEntity> ROCKETS;
    public static EntityType<MortarsEntity> MORTARS;
    public static EntityType<IcicleEntity> ICICLE;

    public static void init() {
        SNUGGLES = register("mister_snuggles", FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, SnugglesEntity::new).dimensions(EntityDimensions.changing(0.7F, 1.9F)).trackRangeBlocks(8).build());
        ROCKETS = register("aftermarket_snow_golem", FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, RocketsEntity::new).dimensions(EntityDimensions.changing(0.7F, 1.9F)).trackRangeBlocks(8).build());
        MORTARS = register("mortars", FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, MortarsEntity::new).dimensions(EntityDimensions.changing(0.7F, 1.9F)).trackRangeBlocks(8).build());

        ICICLE = register("icicle", FabricEntityTypeBuilder.<IcicleEntity>create(SpawnGroup.MISC, IcicleEntity::new).dimensions(EntityDimensions.changing(0.5f, 0.5f)).trackRangeBlocks(4).trackedUpdateRate(20).build());
//        EXPLOSIVE_ORNAMENT = register("explosive_ornament", FabricEntityTypeBuilder.<ExplosiveOrnamentEntity>create(SpawnGroup.MISC, ExplosiveOrnamentEntity::new).dimensions(EntityDimensions.changing(0.25f, 0.25f)).trackRangeBlocks(64).trackedUpdateRate(1).forceTrackedVelocityUpdates(true).build());

        FabricDefaultAttributeRegistry.register(EntityTypes.SNUGGLES, SnugglesEntity.createEntityAttributes());
        FabricDefaultAttributeRegistry.register(EntityTypes.ROCKETS, RocketsEntity.createEntityAttributes());
        FabricDefaultAttributeRegistry.register(EntityTypes.MORTARS, MortarsEntity.createEntityAttributes());
    }

    private static <T extends Entity> EntityType<T> register(String s, EntityType<T> entityType) {
        return Registry.register(Registry.ENTITY_TYPE, FrostLegion.MODID + ":" + s, entityType);
    }
}
