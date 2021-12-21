package ladysnake.snowmercy.common.init;

import ladysnake.snowmercy.common.SnowMercy;
import ladysnake.snowmercy.common.entity.*;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.util.registry.Registry;

public class SnowMercyEntities {
    public static EntityType<PolarBearerEntity> POLAR_BEARER;
    public static EntityType<TundrabidEntity> TUNDRABID;
    public static EntityType<IceballEntity> ICEBALL;

    public static EntityType<SnugglesEntity> SNUGGLES;
    public static EntityType<ChillSnugglesEntity> CHILL_SNUGGLES;
    public static EntityType<RocketsEntity> ROCKETS;
    public static EntityType<MortarsEntity> MORTARS;
    public static EntityType<SawmanEntity> SAWMAN;
    public static EntityType<IceboomboxEntity> BOOMBOX;
    public static EntityType<SnowGolemHeadEntity> SNOW_GOLEM_HEAD;
    public static EntityType<HeadmasterEntity> HEADMASTER;

    public static EntityType<IcicleEntity> ICICLE;
    public static EntityType<BurningCoalEntity> BURNING_COAL;
    public static EntityType<FreezingWindEntity> FREEZING_WIND;
    public static EntityType<SledgeEntity> SLEDGE;
    public static EntityType<IceHeartEntity> HEART_OF_ICE;

    public static void init() {
        TrackedDataHandlerRegistry.register(WeaponizedGolemType.TRACKED_DATA_HANDLER);

        POLAR_BEARER = register("polar_bearer", FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, PolarBearerEntity::new).specificSpawnBlocks(Blocks.POWDER_SNOW).dimensions(EntityDimensions.changing(1.4f, 1.4f)).trackRangeBlocks(10).build());
        TUNDRABID = register("tundrabid", FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, TundrabidEntity::new).dimensions(EntityDimensions.changing(0.6F, 0.7F)).trackRangeBlocks(8).build());
        ICEBALL = register("iceball", FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, IceballEntity::new).dimensions(EntityDimensions.changing(2.04f, 2.04f)).trackRangeBlocks(10).build());

        SNUGGLES = register("mister_snuggles", FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, SnugglesEntity::new).dimensions(EntityDimensions.changing(0.7F, 1.9F)).trackRangeBlocks(8).build());
        CHILL_SNUGGLES = register("mister_chill_snuggles", FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, ChillSnugglesEntity::new).dimensions(EntityDimensions.changing(0.7F, 1.9F)).trackRangeBlocks(8).build());
        ROCKETS = register("aftermarket_snowman", FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, RocketsEntity::new).dimensions(EntityDimensions.changing(0.7F, 1.9F)).trackRangeBlocks(8).build());
        MORTARS = register("ice_mortar", FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, MortarsEntity::new).dimensions(EntityDimensions.changing(0.7F, 1.9F)).trackRangeBlocks(8).build());
        SAWMAN = register("sawman", FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, SawmanEntity::new).dimensions(EntityDimensions.changing(0.7F, 1.9F)).trackRangeBlocks(8).build());
        BOOMBOX = register("iceboombox", FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, IceboomboxEntity::new).dimensions(EntityDimensions.changing(0.7F, 1.9F)).trackRangeBlocks(8).build());

        SNOW_GOLEM_HEAD = register("snow_golem_head", FabricEntityTypeBuilder.<SnowGolemHeadEntity>create(SpawnGroup.MONSTER, SnowGolemHeadEntity::new).dimensions(EntityDimensions.changing(0.5f, 0.6f)).trackRangeBlocks(8).build());
        HEADMASTER = register("headmaster", FabricEntityTypeBuilder.<HeadmasterEntity>create(SpawnGroup.MONSTER, HeadmasterEntity::new).dimensions(EntityDimensions.changing(2.0f, 4.0f)).trackRangeBlocks(8).build());

        ICICLE = register("icicle", FabricEntityTypeBuilder.<IcicleEntity>create(SpawnGroup.MISC, IcicleEntity::new).dimensions(EntityDimensions.changing(0.5f, 0.5f)).trackRangeBlocks(4).trackedUpdateRate(20).build());
        BURNING_COAL = register("burning_coal", FabricEntityTypeBuilder.<BurningCoalEntity>create(SpawnGroup.MISC, BurningCoalEntity::new).dimensions(EntityDimensions.changing(1.0f, 1.0f)).trackRangeBlocks(4).trackedUpdateRate(20).build());
        FREEZING_WIND = register("freezing_wind", FabricEntityTypeBuilder.<FreezingWindEntity>create(SpawnGroup.MISC, FreezingWindEntity::new).dimensions(EntityDimensions.changing(1.0f, 1.0f)).trackRangeBlocks(4).trackedUpdateRate(20).build());
        SLEDGE = register("hammersledge", FabricEntityTypeBuilder.<SledgeEntity>create(SpawnGroup.MISC, SledgeEntity::new).dimensions(EntityDimensions.changing(1.375f, 0.5625f)).trackRangeBlocks(10).build());
        HEART_OF_ICE = register("heart_of_ice", FabricEntityTypeBuilder.<IceHeartEntity>create(SpawnGroup.MISC, IceHeartEntity::new).dimensions(EntityDimensions.changing(0.5f, 0.5f)).trackRangeBlocks(10).build());

        FabricDefaultAttributeRegistry.register(SnowMercyEntities.SNUGGLES, SnugglesEntity.createEntityAttributes());
        FabricDefaultAttributeRegistry.register(SnowMercyEntities.CHILL_SNUGGLES, SnugglesEntity.createEntityAttributes());
        FabricDefaultAttributeRegistry.register(SnowMercyEntities.ROCKETS, RocketsEntity.createEntityAttributes());
        FabricDefaultAttributeRegistry.register(SnowMercyEntities.MORTARS, MortarsEntity.createEntityAttributes());
        FabricDefaultAttributeRegistry.register(SnowMercyEntities.SAWMAN, SawmanEntity.createEntityAttributes());
        FabricDefaultAttributeRegistry.register(SnowMercyEntities.BOOMBOX, IceboomboxEntity.createEntityAttributes());
        FabricDefaultAttributeRegistry.register(SnowMercyEntities.SNOW_GOLEM_HEAD, SnowGolemHeadEntity.createEntityAttributes());
        FabricDefaultAttributeRegistry.register(SnowMercyEntities.HEADMASTER, HeadmasterEntity.createHeadmasterAttributes());
        FabricDefaultAttributeRegistry.register(SnowMercyEntities.POLAR_BEARER, PolarBearerEntity.createPolarBearAttributes());
        FabricDefaultAttributeRegistry.register(SnowMercyEntities.TUNDRABID, TundrabidEntity.createFoxAttributes());
        FabricDefaultAttributeRegistry.register(SnowMercyEntities.ICEBALL, HostileEntity.createHostileAttributes());
    }

    private static <T extends Entity> EntityType<T> register(String s, EntityType<T> entityType) {
        return Registry.register(Registry.ENTITY_TYPE, SnowMercy.MODID + ":" + s, entityType);
    }
}
