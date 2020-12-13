package ladysnake.frostlegion.client;

import ladysnake.frostlegion.client.network.EntityDispatcher;
import ladysnake.frostlegion.client.render.entity.SnowblobEntityRenderer;
import ladysnake.frostlegion.client.render.entity.SnowgglerEntityRenderer;
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
        EntityRendererRegistry.INSTANCE.register(EntityTypes.SNOWBLOB, (manager, context) -> new SnowblobEntityRenderer(manager));
        EntityRendererRegistry.INSTANCE.register(EntityTypes.SNOWGGLER, (manager, context) -> new SnowgglerEntityRenderer(manager));

        ClientSidePacketRegistry.INSTANCE.register(Packets.SPAWN, EntityDispatcher::spawnFrom);
    }

}
