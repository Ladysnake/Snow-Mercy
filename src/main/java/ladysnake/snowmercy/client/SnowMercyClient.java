package ladysnake.snowmercy.client;

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import ladysnake.snowmercy.client.network.EntityDispatcher;
import ladysnake.snowmercy.client.render.entity.*;
import ladysnake.snowmercy.client.render.entity.model.MortarsEntityModel;
import ladysnake.snowmercy.client.render.entity.model.RocketsEntityModel;
import ladysnake.snowmercy.client.render.entity.model.SawmanEntityModel;
import ladysnake.snowmercy.client.render.entity.model.SnugglesEntityModel;
import ladysnake.snowmercy.common.SnowGolemEntityData;
import ladysnake.snowmercy.common.SnowMercy;
import ladysnake.snowmercy.common.init.EntityTypes;
import ladysnake.snowmercy.common.network.Packets;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.util.Identifier;

public class SnowMercyClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        registerRenders();
    }

    public static final Int2ObjectOpenHashMap<SnowGolemEntityData> GOLEM_MODELS_AND_TEXTURES = new Int2ObjectOpenHashMap<>();

    public static void registerRenders() {
        ClientSidePacketRegistry.INSTANCE.register(Packets.SPAWN, EntityDispatcher::spawnFrom);

        EntityRendererRegistry.INSTANCE.register(EntityTypes.SNUGGLES, (manager, context) -> new SnugglesEntityRenderer(manager));
        EntityRendererRegistry.INSTANCE.register(EntityTypes.ROCKETS, (manager, context) -> new RocketsEntityRenderer(manager));
        EntityRendererRegistry.INSTANCE.register(EntityTypes.MORTARS, (manager, context) -> new MortarsEntityRenderer(manager));
        EntityRendererRegistry.INSTANCE.register(EntityTypes.SAWMAN, (manager, context) -> new SawmanEntityRenderer(manager));
        EntityRendererRegistry.INSTANCE.register(EntityTypes.SNOW_GOLEM_HEAD, (manager, context) -> new SnowGolemHeadEntityRenderer(manager));

        EntityRendererRegistry.INSTANCE.register(EntityTypes.ICICLE, (manager, context) -> new IcicleEntityRenderer(manager));

        GOLEM_MODELS_AND_TEXTURES.put(0, new SnowGolemEntityData(EntityTypes.SAWMAN, new SawmanEntityModel<>(), new Identifier(SnowMercy.MODID, "textures/entity/sawman.png")));
        GOLEM_MODELS_AND_TEXTURES.put(1,  new SnowGolemEntityData(EntityTypes.SNUGGLES, new SnugglesEntityModel<>(), new Identifier(SnowMercy.MODID, "textures/entity/snuggles.png")));
        GOLEM_MODELS_AND_TEXTURES.put(2,  new SnowGolemEntityData(EntityTypes.ROCKETS, new RocketsEntityModel<>(), new Identifier(SnowMercy.MODID, "textures/entity/rockets.png")));
        GOLEM_MODELS_AND_TEXTURES.put(3,  new SnowGolemEntityData(EntityTypes.MORTARS, new MortarsEntityModel<>(), new Identifier(SnowMercy.MODID, "textures/entity/mortars.png")));
    }

}
