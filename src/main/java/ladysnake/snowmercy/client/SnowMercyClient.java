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
        registerRenders();
    }

    public static void registerRenders() {
        EntityModelLayerRegistry.registerModelLayer(SNUGGLES_MODEL_LAYER, WeaponizedSnowGolemEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(CHILL_SNUGGLES_MODEL_LAYER, WeaponizedSnowGolemEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(ROCKETS_MODEL_LAYER, WeaponizedSnowGolemEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(MORTARS_MODEL_LAYER, WeaponizedSnowGolemEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(SAWMAN_MODEL_LAYER, WeaponizedSnowGolemEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(SNOW_GOLEM_HEAD_MODEL_LAYER, WeaponizedSnowGolemEntityModel::getTexturedModelData);

        EntityRendererRegistry.INSTANCE.register(EntityTypes.SNUGGLES, WeaponizedSnowGolemEntityRenderer::new);
        EntityRendererRegistry.INSTANCE.register(EntityTypes.CHILL_SNUGGLES, WeaponizedSnowGolemEntityRenderer::new);
        EntityRendererRegistry.INSTANCE.register(EntityTypes.ROCKETS, WeaponizedSnowGolemEntityRenderer::new);
        EntityRendererRegistry.INSTANCE.register(EntityTypes.MORTARS, WeaponizedSnowGolemEntityRenderer::new);
        EntityRendererRegistry.INSTANCE.register(EntityTypes.SAWMAN, WeaponizedSnowGolemEntityRenderer::new);
        EntityRendererRegistry.INSTANCE.register(EntityTypes.SNOW_GOLEM_HEAD, WeaponizedSnowGolemEntityRenderer::new);

        EntityRendererRegistry.INSTANCE.register(EntityTypes.ICICLE, IcicleEntityRenderer::new);
    }
}
