package ladysnake.snowmercy.client;

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import ladysnake.snowmercy.client.render.entity.*;
import ladysnake.snowmercy.client.render.entity.model.*;
import ladysnake.snowmercy.common.SnowGolemEntityData;
import ladysnake.snowmercy.common.SnowMercy;
import ladysnake.snowmercy.common.init.EntityTypes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class SnowMercyClient implements ClientModInitializer {
    public static final EntityModelLayer SNUGGLES_MODEL_LAYER = new EntityModelLayer(new Identifier(SnowMercy.MODID, "snuggles"), "main");
    public static final EntityModelLayer CHILL_SNUGGLES_MODEL_LAYER = new EntityModelLayer(new Identifier(SnowMercy.MODID, "chill_snuggles"), "main");
    public static final EntityModelLayer ROCKETS_MODEL_LAYER = new EntityModelLayer(new Identifier(SnowMercy.MODID, "rockets"), "main");
    public static final EntityModelLayer MORTARS_MODEL_LAYER = new EntityModelLayer(new Identifier(SnowMercy.MODID, "mortars"), "main");
    public static final EntityModelLayer SAWMAN_MODEL_LAYER = new EntityModelLayer(new Identifier(SnowMercy.MODID, "sawman"), "main");
    public static final EntityModelLayer SNOW_GOLEM_HEAD_MODEL_LAYER = new EntityModelLayer(new Identifier(SnowMercy.MODID, "snow_golem_head"), "main");

    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(SNUGGLES_MODEL_LAYER, SnugglesEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(CHILL_SNUGGLES_MODEL_LAYER, SnugglesEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(ROCKETS_MODEL_LAYER, RocketsEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(MORTARS_MODEL_LAYER, MortarsEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(SAWMAN_MODEL_LAYER, SawmanEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(SNOW_GOLEM_HEAD_MODEL_LAYER, SnowGolemHeadEntityModel::getTexturedModelData);

        EntityRendererRegistry.INSTANCE.register(EntityTypes.SNUGGLES, SnugglesEntityRenderer::new);
        EntityRendererRegistry.INSTANCE.register(EntityTypes.CHILL_SNUGGLES, ChillSnugglesEntityRenderer::new);
        EntityRendererRegistry.INSTANCE.register(EntityTypes.ROCKETS, RocketsEntityRenderer::new);
        EntityRendererRegistry.INSTANCE.register(EntityTypes.MORTARS, MortarsEntityRenderer::new);
        EntityRendererRegistry.INSTANCE.register(EntityTypes.SAWMAN, SawmanEntityRenderer::new);
        EntityRendererRegistry.INSTANCE.register(EntityTypes.SNOW_GOLEM_HEAD, SnowGolemHeadEntityRenderer::new);

        EntityRendererRegistry.INSTANCE.register(EntityTypes.ICICLE, IcicleEntityRenderer::new);
    }
}
