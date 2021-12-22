package ladysnake.snowmercy.client;

import ladysnake.snowmercy.client.render.entity.*;
import ladysnake.snowmercy.client.render.entity.model.*;
import ladysnake.snowmercy.client.sound.BoomboxMovingSoundInstance;
import ladysnake.snowmercy.common.SnowMercy;
import ladysnake.snowmercy.common.entity.GiftPackageEntity;
import ladysnake.snowmercy.common.entity.IceboomboxEntity;
import ladysnake.snowmercy.common.entity.WeaponizedGolemType;
import ladysnake.snowmercy.common.entity.WeaponizedSnowGolemEntity;
import ladysnake.snowmercy.common.init.SnowMercyEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientEntityEvents;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.FoxEntityRenderer;
import net.minecraft.client.render.entity.PolarBearEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.entity.EntityType;

import java.util.function.Function;

public class SnowMercyClient implements ClientModInitializer {
    public static final EntityModelLayer SNOW_GOLEM_HEAD_MODEL_LAYER = new EntityModelLayer(SnowMercy.id("snow_golem_head"), "main");
    public static final EntityModelLayer SLEDGE_MODEL_LAYER = new EntityModelLayer(SnowMercy.id("sledge"), "main");
    public static final EntityModelLayer HEART_OF_ICE_MODEL_LAYER = new EntityModelLayer(SnowMercy.id("heart_of_ice"), "main");
    public static final EntityModelLayer GIFT_PACKAGE_MODEL_LAYER = new EntityModelLayer(SnowMercy.id("gift_package"), "main");

    public static void registerRenderer(
            WeaponizedGolemType entityType,
            EntityModelLayerRegistry.TexturedModelDataProvider modelDataProvider) {
        registerRenderer(entityType, WeaponizedSnowGolemEntityModel::new, modelDataProvider);
    }

    public static <E extends WeaponizedSnowGolemEntity, M extends WeaponizedSnowGolemEntityModel<E>> void registerRenderer(
            WeaponizedGolemType type,
            Function<ModelPart, M> entityModel,
            EntityModelLayerRegistry.TexturedModelDataProvider modelDataProvider) {
        EntityModelLayer layer = new EntityModelLayer(type.getId(), "main");
        EntityModelLayerRegistry.registerModelLayer(layer, modelDataProvider);
        @SuppressWarnings("unchecked") EntityType<E> entityType = (EntityType<E>) type.getEntityType();
        EntityRendererRegistry.INSTANCE.register(entityType, ctx -> new WeaponizedSnowGolemEntityRenderer<>(ctx, entityModel.apply(ctx.getPart(layer)), 0.5F, type.getTexture()));
    }

    @Override
    public void onInitializeClient() {
        registerRenderer(WeaponizedGolemType.SAWMAN, SawmanEntityModel::new, SawmanEntityModel::getTexturedModelData);
        registerRenderer(WeaponizedGolemType.ROCKETS, WeaponizedSnowGolemEntityModel::rocketsModelData);
        registerRenderer(WeaponizedGolemType.SNUGGLES, WeaponizedSnowGolemEntityModel::snugglesModelData);
        registerRenderer(WeaponizedGolemType.MORTARS, WeaponizedSnowGolemEntityModel::mortarsModelData);
        registerRenderer(WeaponizedGolemType.CHILL_SNUGGLES, WeaponizedSnowGolemEntityModel::snugglesModelData);
        registerRenderer(WeaponizedGolemType.BOOMBOX, WeaponizedSnowGolemEntityModel::boomboxModelData);

        EntityRendererRegistry.INSTANCE.register(SnowMercyEntities.SNOW_GOLEM_HEAD, SnowGolemHeadEntityRenderer::new);
        EntityRendererRegistry.INSTANCE.register(SnowMercyEntities.HEADMASTER, HeadmasterEntityRenderer::new);
        EntityRendererRegistry.INSTANCE.register(SnowMercyEntities.ICICLE, IcicleEntityRenderer::new);

        EntityRendererRegistry.INSTANCE.register(SnowMercyEntities.BURNING_COAL, InvisibleThrownEntityRenderer::new);
        EntityRendererRegistry.INSTANCE.register(SnowMercyEntities.FREEZING_WIND, InvisibleThrownEntityRenderer::new);
        EntityRendererRegistry.INSTANCE.register(SnowMercyEntities.SLEDGE, SledgeEntityRenderer::new);
        EntityRendererRegistry.INSTANCE.register(SnowMercyEntities.HEART_OF_ICE, IceHeartEntityRenderer::new);
        EntityRendererRegistry.INSTANCE.register(SnowMercyEntities.GIFT_PACKAGE, GiftPackageEntityRenderer::new);

        EntityRendererRegistry.INSTANCE.register(SnowMercyEntities.POLAR_BEARER, PolarBearEntityRenderer::new);
        EntityRendererRegistry.INSTANCE.register(SnowMercyEntities.TUNDRABID, FoxEntityRenderer::new);
        EntityRendererRegistry.INSTANCE.register(SnowMercyEntities.ICEBALL, IceballEntityRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(SNOW_GOLEM_HEAD_MODEL_LAYER, SnowGolemHeadEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(SLEDGE_MODEL_LAYER, SledgeEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(HEART_OF_ICE_MODEL_LAYER, IceHeartEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(GIFT_PACKAGE_MODEL_LAYER, GiftPackageEntityModel::getTexturedModelData);

        ClientEntityEvents.ENTITY_LOAD.register((entity, world) -> {
            if (entity instanceof IceboomboxEntity) {
                MinecraftClient.getInstance().getSoundManager().playNextTick(new BoomboxMovingSoundInstance((IceboomboxEntity) entity));
            }
        });
    }
}
