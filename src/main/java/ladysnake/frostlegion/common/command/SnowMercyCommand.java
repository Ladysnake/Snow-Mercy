package ladysnake.frostlegion.common.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import ladysnake.frostlegion.cca.SnowMercyComponents;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.TranslatableText;

public class SnowMercyCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder) CommandManager.literal("snowmercy").requires((serverCommandSource) -> {
            return serverCommandSource.hasPermissionLevel(2);
        })).then(((LiteralArgumentBuilder)CommandManager.literal("start").executes((commandContext) -> {
            return executeStart(commandContext.getSource());
        }))).then(((LiteralArgumentBuilder)CommandManager.literal("stop").executes((commandContext) -> {
            return executeStop(commandContext.getSource());
        }))));
    }

    private static int executeStart(ServerCommandSource source) {
        SnowMercyComponents.SNOWMERCY.get(source.getWorld()).setEventOngoing(true);
        source.sendFeedback(new TranslatableText("commands.snowmercy.start"), true);
        return 0;
    }

    private static int executeStop(ServerCommandSource source) {
        SnowMercyComponents.SNOWMERCY.get(source.getWorld()).setEventOngoing(false);
        source.sendFeedback(new TranslatableText("commands.snowmercy.stop"), true);
        return 0;
    }
}
