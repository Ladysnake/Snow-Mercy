package ladysnake.frostlegion.common;

import ladysnake.frostlegion.common.command.SnowMercyCommand;
import ladysnake.frostlegion.common.init.Blocks;
import ladysnake.frostlegion.common.init.EntityTypes;
import ladysnake.frostlegion.common.init.Items;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;

public class FrostLegion implements ModInitializer {
    public static final String MODID = "frostlegion";

    @Override
    public void onInitialize() {
        Blocks.init();
        Items.init();
        EntityTypes.init();

        CommandRegistrationCallback.EVENT.register((commandDispatcher, b) ->
                SnowMercyCommand.register(commandDispatcher)
        );
    }
}
