package ladysnake.snowmercy.common.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import ladysnake.snowmercy.cca.SnowMercyComponents;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

public class SnowMercyCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder) ((LiteralArgumentBuilder) CommandManager
                .literal("snowmercy").requires((serverCommandSource) -> serverCommandSource.hasPermissionLevel(2)))
                .then((CommandManager.literal("start").executes((commandContext) -> executeStart(commandContext.getSource()))))
                .then(CommandManager.literal("stop").executes((commandContext) -> executeStop(commandContext.getSource()))));
    }

    private static int executeStart(ServerCommandSource source) {
        SnowMercyComponents.SNOWMERCY.get(source.getWorld()).startEvent(source.getWorld());
        return 0;
    }

    private static int executeStop(ServerCommandSource source) {
        SnowMercyComponents.SNOWMERCY.get(source.getWorld()).stopEvent(source.getWorld());
        return 0;
    }
}
