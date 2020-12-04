package ladysnake.frostlegion.common;

import ladysnake.frostlegion.common.entity.SnowgglerEntity;
import ladysnake.frostlegion.common.init.Blocks;
import ladysnake.frostlegion.common.init.EntityTypes;
import ladysnake.frostlegion.common.init.Items;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;

public class FrostLegion implements ModInitializer {
    public static final String MODID = "frostlegion";

    @Override
    public void onInitialize() {
        Blocks.init();
        Items.init();
        EntityTypes.init();

        FabricDefaultAttributeRegistry.register(EntityTypes.SNOWGGLER, SnowgglerEntity.createSnowGolemAttributes());
    }
}
