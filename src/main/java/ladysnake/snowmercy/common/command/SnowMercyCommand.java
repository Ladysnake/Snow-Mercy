package ladysnake.snowmercy.common.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import ladysnake.snowmercy.cca.SnowMercyComponents;
import net.minecraft.network.MessageType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;

import static net.minecraft.text.Style.EMPTY;

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
        SnowMercyComponents.SNOWMERCY.get(source.getWorld()).startEvent(source.getWorld());
        return 0;
    }

    private static int executeStop(ServerCommandSource source) {
        SnowMercyComponents.SNOWMERCY.get(source.getWorld()).stopEvent(source.getWorld());
        return 0;
    }
}
