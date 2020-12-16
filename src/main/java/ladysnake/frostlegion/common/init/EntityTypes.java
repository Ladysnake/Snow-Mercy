package ladysnake.frostlegion.common.init;

import com.google.common.collect.HashBiMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import ladysnake.frostlegion.client.render.entity.model.MortarsEntityModel;
import ladysnake.frostlegion.client.render.entity.model.RocketsEntityModel;
import ladysnake.frostlegion.client.render.entity.model.SawmanEntityModel;
import ladysnake.frostlegion.client.render.entity.model.SnugglesEntityModel;
import ladysnake.frostlegion.common.FrostLegion;
import ladysnake.frostlegion.common.SnowGolemEntityData;
import ladysnake.frostlegion.common.entity.*;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EntityTypes {
    public static EntityType<SnugglesEntity> SNUGGLES;
    public static EntityType<RocketsEntity> ROCKETS;
    public static EntityType<MortarsEntity> MORTARS;
    public static EntityType<SawmanEntity> SAWMAN;
    public static EntityType<SnowGolemHeadEntity> SNOW_GOLEM_HEAD;

    public static EntityType<IcicleEntity> ICICLE;

    public static final Int2ObjectOpenHashMap<SnowGolemEntityData> GOLEM_MODELS_AND_TEXTURES = new Int2ObjectOpenHashMap<>();
    public static final HashBiMap<Integer, EntityType<? extends EvilSnowGolemEntity>> GOLEM_IDS = HashBiMap.create();

    public static void init() {
        SNUGGLES = register("mister_snuggles", FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, SnugglesEntity::new).dimensions(EntityDimensions.changing(0.7F, 1.9F)).trackRangeBlocks(8).build());
        ROCKETS = register("aftermarket_snowman", FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, RocketsEntity::new).dimensions(EntityDimensions.changing(0.7F, 1.9F)).trackRangeBlocks(8).build());
        MORTARS = register("snow_mortar", FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, MortarsEntity::new).dimensions(EntityDimensions.changing(0.7F, 1.9F)).trackRangeBlocks(8).build());
        SAWMAN = register("sawman", FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, SawmanEntity::new).dimensions(EntityDimensions.changing(0.7F, 1.9F)).trackRangeBlocks(8).build());
        SNOW_GOLEM_HEAD = register("snow_golem_head", FabricEntityTypeBuilder.<SnowGolemHeadEntity>create(SpawnGroup.MONSTER, SnowGolemHeadEntity::new).dimensions(EntityDimensions.changing(0.5f, 0.6f)).trackRangeBlocks(8).build());

        ICICLE = register("icicle", FabricEntityTypeBuilder.<IcicleEntity>create(SpawnGroup.MISC, IcicleEntity::new).dimensions(EntityDimensions.changing(0.5f, 0.5f)).trackRangeBlocks(4).trackedUpdateRate(20).build());

        FabricDefaultAttributeRegistry.register(EntityTypes.SNUGGLES, SnugglesEntity.createEntityAttributes());
        FabricDefaultAttributeRegistry.register(EntityTypes.ROCKETS, RocketsEntity.createEntityAttributes());
        FabricDefaultAttributeRegistry.register(EntityTypes.MORTARS, MortarsEntity.createEntityAttributes());
        FabricDefaultAttributeRegistry.register(EntityTypes.SAWMAN, SawmanEntity.createEntityAttributes());
        FabricDefaultAttributeRegistry.register(EntityTypes.SNOW_GOLEM_HEAD, SnowGolemHeadEntity.createEntityAttributes());

        GOLEM_MODELS_AND_TEXTURES.put(0, new SnowGolemEntityData(SAWMAN, new SawmanEntityModel<>(), new Identifier(FrostLegion.MODID, "textures/entity/sawman.png")));
        GOLEM_IDS.put(0, SAWMAN);
        GOLEM_MODELS_AND_TEXTURES.put(1,  new SnowGolemEntityData(SNUGGLES, new SnugglesEntityModel<>(), new Identifier(FrostLegion.MODID, "textures/entity/snuggles.png")));
        GOLEM_IDS.put(1, SNUGGLES);
        GOLEM_MODELS_AND_TEXTURES.put(2,  new SnowGolemEntityData(ROCKETS, new RocketsEntityModel<>(), new Identifier(FrostLegion.MODID, "textures/entity/rockets.png")));
        GOLEM_IDS.put(2, ROCKETS);
        GOLEM_MODELS_AND_TEXTURES.put(3,  new SnowGolemEntityData(MORTARS, new MortarsEntityModel<>(), new Identifier(FrostLegion.MODID, "textures/entity/mortars.png")));
        GOLEM_IDS.put(3, MORTARS);
    }

    private static <T extends Entity> EntityType<T> register(String s, EntityType<T> entityType) {
        return Registry.register(Registry.ENTITY_TYPE, FrostLegion.MODID + ":" + s, entityType);
    }
}
