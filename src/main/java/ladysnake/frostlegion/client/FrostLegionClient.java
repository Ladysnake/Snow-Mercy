package ladysnake.frostlegion.client;

import ladysnake.frostlegion.client.network.EntityDispatcher;
import ladysnake.frostlegion.client.render.entity.*;
import ladysnake.frostlegion.client.render.entity.model.EvilSnowGolemEntityModel;
import ladysnake.frostlegion.common.entity.EvilSnowGolemEntity;
import ladysnake.frostlegion.common.init.EntityTypes;
import ladysnake.frostlegion.common.network.Packets;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;

public class FrostLegionClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        registerRenders();
    }

    public static void registerRenders() {
        ClientSidePacketRegistry.INSTANCE.register(Packets.SPAWN, EntityDispatcher::spawnFrom);

        EntityRendererRegistry.INSTANCE.register(EntityTypes.SNUGGLES, (manager, context) -> new SnugglesEntityRenderer(manager));
        EntityRendererRegistry.INSTANCE.register(EntityTypes.ROCKETS, (manager, context) -> new RocketsEntityRenderer(manager));
        EntityRendererRegistry.INSTANCE.register(EntityTypes.MORTARS, (manager, context) -> new MortarsEntityRenderer(manager));
        EntityRendererRegistry.INSTANCE.register(EntityTypes.SAWMAN, (manager, context) -> new SawmanEntityRenderer(manager));
        EntityRendererRegistry.INSTANCE.register(EntityTypes.SNOW_GOLEM_HEAD, (manager, context) -> new SnowGolemHeadEntityRenderer(manager));

        EntityRendererRegistry.INSTANCE.register(EntityTypes.ICICLE, (manager, context) -> new IcicleEntityRenderer(manager));
    }

}
