package ladysnake.snowmercy.client;

import ladysnake.snowmercy.client.render.entity.*;
import ladysnake.snowmercy.client.render.entity.model.SawmanEntityModel;
import ladysnake.snowmercy.client.render.entity.model.SledgeEntityModel;
import ladysnake.snowmercy.client.render.entity.model.SnowGolemHeadEntityModel;
import ladysnake.snowmercy.client.render.entity.model.WeaponizedSnowGolemEntityModel;
import ladysnake.snowmercy.client.sound.BoomboxMovingSoundInstance;
import ladysnake.snowmercy.common.SnowMercy;
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
import net.minecraft.client.render.entity.EndCrystalEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.sound.AbstractBeeSoundInstance;
import net.minecraft.client.sound.AggressiveBeeSoundInstance;
import net.minecraft.client.sound.PassiveBeeSoundInstance;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.BeeEntity;

import java.util.function.Function;

public class SnowMercyClient implements ClientModInitializer {
    public static final EntityModelLayer SNOW_GOLEM_HEAD_MODEL_LAYER = new EntityModelLayer(SnowMercy.id("snow_golem_head"), "main");
    public static final EntityModelLayer SLEDGE_MODEL_LAYER = new EntityModelLayer(SnowMercy.id("sledge"), "main");

    @Override
    public void onInitializeClient() {

        registerRenderer(WeaponizedGolemType.SAWMAN, SawmanEntityModel::new, SawmanEntityModel::getTexturedModelData);
        registerRenderer(WeaponizedGolemType.ROCKETS, WeaponizedSnowGolemEntityModel::rocketsModelData);
        registerRenderer(WeaponizedGolemType.SNUGGLES, WeaponizedSnowGolemEntityModel::snugglesModelData);
        registerRenderer(WeaponizedGolemType.MORTARS, WeaponizedSnowGolemEntityModel::mortarsModelData);
        registerRenderer(WeaponizedGolemType.CHILL_SNUGGLES, WeaponizedSnowGolemEntityModel::snugglesModelData);
        registerRenderer(WeaponizedGolemType.BOOMBOX, WeaponizedSnowGolemEntityModel::boomboxModelData);

        EntityModelLayerRegistry.registerModelLayer(SNOW_GOLEM_HEAD_MODEL_LAYER, SnowGolemHeadEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(SLEDGE_MODEL_LAYER, SledgeEntityModel::getTexturedModelData);
        EntityRendererRegistry.INSTANCE.register(SnowMercyEntities.SNOW_GOLEM_HEAD, SnowGolemHeadEntityRenderer::new);
        EntityRendererRegistry.INSTANCE.register(SnowMercyEntities.ICICLE, IcicleEntityRenderer::new);
        EntityRendererRegistry.INSTANCE.register(SnowMercyEntities.BURNING_COAL, BurningCoalEntityRenderer::new);
        EntityRendererRegistry.INSTANCE.register(SnowMercyEntities.SLEDGE, SledgeEntityRenderer::new);

        ClientEntityEvents.ENTITY_LOAD.register((entity, world) -> {
            if (entity instanceof IceboomboxEntity) {
                MinecraftClient.getInstance().getSoundManager().playNextTick(new BoomboxMovingSoundInstance((IceboomboxEntity) entity));
            }
        });
    }

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
}
