package ladysnake.snowmercy.common.init;

import ladysnake.snowmercy.common.SnowMercy;
import ladysnake.snowmercy.common.entity.*;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.util.registry.Registry;

public class SnowMercyEntities {
    public static EntityType<SnugglesEntity> SNUGGLES;
    public static EntityType<ChillSnugglesEntity> CHILL_SNUGGLES;
    public static EntityType<RocketsEntity> ROCKETS;
    public static EntityType<MortarsEntity> MORTARS;
    public static EntityType<SawmanEntity> SAWMAN;
    public static EntityType<IceboomboxEntity> BOOMBOX;
    public static EntityType<SnowGolemHeadEntity> SNOW_GOLEM_HEAD;

    public static EntityType<IcicleEntity> ICICLE;

    public static void init() {
        TrackedDataHandlerRegistry.register(WeaponizedGolemType.TRACKED_DATA_HANDLER);

        SNUGGLES = register("mister_snuggles", FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, SnugglesEntity::new).dimensions(EntityDimensions.changing(0.7F, 1.9F)).trackRangeBlocks(8).build());
        CHILL_SNUGGLES = register("mister_chill_snuggles", FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, ChillSnugglesEntity::new).dimensions(EntityDimensions.changing(0.7F, 1.9F)).trackRangeBlocks(8).build());
        ROCKETS = register("aftermarket_snowman", FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, RocketsEntity::new).dimensions(EntityDimensions.changing(0.7F, 1.9F)).trackRangeBlocks(8).build());
        MORTARS = register("ice_mortar", FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, MortarsEntity::new).dimensions(EntityDimensions.changing(0.7F, 1.9F)).trackRangeBlocks(8).build());
        SAWMAN = register("sawman", FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, SawmanEntity::new).dimensions(EntityDimensions.changing(0.7F, 1.9F)).trackRangeBlocks(8).build());
        BOOMBOX = register("iceboombox", FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, IceboomboxEntity::new).dimensions(EntityDimensions.changing(0.7F, 1.9F)).trackRangeBlocks(8).build());

        SNOW_GOLEM_HEAD = register("snow_golem_head", FabricEntityTypeBuilder.<SnowGolemHeadEntity>create(SpawnGroup.MONSTER, SnowGolemHeadEntity::new).dimensions(EntityDimensions.changing(0.5f, 0.6f)).trackRangeBlocks(8).build());

        ICICLE = register("icicle", FabricEntityTypeBuilder.<IcicleEntity>create(SpawnGroup.MISC, IcicleEntity::new).dimensions(EntityDimensions.changing(0.5f, 0.5f)).trackRangeBlocks(4).trackedUpdateRate(20).build());

        FabricDefaultAttributeRegistry.register(SnowMercyEntities.SNUGGLES, SnugglesEntity.createEntityAttributes());
        FabricDefaultAttributeRegistry.register(SnowMercyEntities.CHILL_SNUGGLES, SnugglesEntity.createEntityAttributes());
        FabricDefaultAttributeRegistry.register(SnowMercyEntities.ROCKETS, RocketsEntity.createEntityAttributes());
        FabricDefaultAttributeRegistry.register(SnowMercyEntities.MORTARS, MortarsEntity.createEntityAttributes());
        FabricDefaultAttributeRegistry.register(SnowMercyEntities.SAWMAN, SawmanEntity.createEntityAttributes());
        FabricDefaultAttributeRegistry.register(SnowMercyEntities.BOOMBOX, IceboomboxEntity.createEntityAttributes());
        FabricDefaultAttributeRegistry.register(SnowMercyEntities.SNOW_GOLEM_HEAD, SnowGolemHeadEntity.createEntityAttributes());
    }

    private static <T extends Entity> EntityType<T> register(String s, EntityType<T> entityType) {
        return Registry.register(Registry.ENTITY_TYPE, SnowMercy.MODID + ":" + s, entityType);
    }
}
