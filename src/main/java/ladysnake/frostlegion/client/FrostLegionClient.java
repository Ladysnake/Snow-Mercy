package ladysnake.frostlegion.client;

import ladysnake.frostlegion.client.network.EntityDispatcher;
import ladysnake.frostlegion.client.render.entity.RocketsEntityRenderer;
import ladysnake.frostlegion.client.render.entity.SnowblobEntityRenderer;
import ladysnake.frostlegion.client.render.entity.SnugglesEntityRenderer;
import ladysnake.frostlegion.common.init.EntityTypes;
import ladysnake.frostlegion.common.network.Packets;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;

public class FrostLegionClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        registerRenders();
    }

    public static void registerRenders() {
        ClientSidePacketRegistry.INSTANCE.register(Packets.SPAWN, EntityDispatcher::spawnFrom);

        EntityRendererRegistry.INSTANCE.register(EntityTypes.SNUGGLES, (manager, context) -> new SnugglesEntityRenderer(manager));
        EntityRendererRegistry.INSTANCE.register(EntityTypes.ROCKETS, (manager, context) -> new RocketsEntityRenderer(manager));
    }

}
