package ladysnake.frostlegion.common.init;

import ladysnake.frostlegion.common.FrostLegion;
import ladysnake.frostlegion.common.entity.SnowmineEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.registry.Registry;

public class EntityTypes {
    public static EntityType<SnowmineEntity> SNOWMINE;

    public static void init() {
        // throwable explosives
        SNOWMINE = register("snowmine", FabricEntityTypeBuilder.create(SpawnGroup.MISC, SnowmineEntity::new).dimensions(EntityDimensions.changing(0.7F, 1.9F)).trackRangeBlocks(8).build());
    }

    private static <T extends Entity> EntityType<T> register(String s, EntityType<T> entityType) {
        return Registry.register(Registry.ENTITY_TYPE, FrostLegion.MODID + ":" + s, entityType);
    }
}
