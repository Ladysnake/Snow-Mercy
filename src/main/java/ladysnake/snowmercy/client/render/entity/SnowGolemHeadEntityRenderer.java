package ladysnake.snowmercy.client.render.entity;

import ladysnake.snowmercy.client.SnowMercyClient;
import ladysnake.snowmercy.client.render.entity.model.MortarsEntityModel;
import ladysnake.snowmercy.client.render.entity.model.RocketsEntityModel;
import ladysnake.snowmercy.client.render.entity.model.SawmanEntityModel;
import ladysnake.snowmercy.client.render.entity.model.SnugglesEntityModel;
import ladysnake.snowmercy.common.entity.SnowGolemHeadEntity;
import ladysnake.snowmercy.common.entity.WeaponizedSnowGolemEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.LivingEntity;
import net.minecraft.scoreboard.AbstractTeam;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3f;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class SnowGolemHeadEntityRenderer extends EntityRenderer<SnowGolemHeadEntity> {
    private final EntityModel<WeaponizedSnowGolemEntity> snuggles;
    private final EntityModel<WeaponizedSnowGolemEntity> chill;
    private final EntityModel<WeaponizedSnowGolemEntity> rockets;
    private final EntityModel<WeaponizedSnowGolemEntity> mortars;
    private final EntityModel<WeaponizedSnowGolemEntity> saw;

    public SnowGolemHeadEntityRenderer(EntityRendererFactory.Context context) {
        super(context);//, new WeaponizedSnowGolemEntityModel<>(context.getPart(SnowMercyClient.SNOW_GOLEM_HEAD_MODEL_LAYER)), 0.5F);
        this.shadowRadius = 0.5F;

        snuggles = new SnugglesEntityModel<>(context.getPart(SnowMercyClient.SNUGGLES_MODEL_LAYER));
        chill = new SnugglesEntityModel<>(context.getPart(SnowMercyClient.CHILL_SNUGGLES_MODEL_LAYER));
        rockets = new RocketsEntityModel<>(context.getPart(SnowMercyClient.ROCKETS_MODEL_LAYER));
        mortars = new MortarsEntityModel<>(context.getPart(SnowMercyClient.MORTARS_MODEL_LAYER));
        saw = new SawmanEntityModel<>(context.getPart(SnowMercyClient.SAWMAN_MODEL_LAYER));
    }

    @Override
    public Identifier getTexture(SnowGolemHeadEntity entity) {
        return SnowMercyClient.GOLEM_MODELS_AND_TEXTURES.get(entity.getGolemType()).getTexture();
    }

    protected EntityModel<WeaponizedSnowGolemEntity> model;
    public EntityModel<WeaponizedSnowGolemEntity> getModel() {
        return this.model;
    }

    public void render(SnowGolemHeadEntity livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.push();

        switch (livingEntity.getGolemType()) {
            case 0:
                model = snuggles;
                break;
            case 1:
                model = chill;
                break;
            case 2:
                model = rockets;
                break;
            case 3:
                model = mortars;
                break;
            default:
                model = saw;
                break;
        }

        this.model.handSwingProgress = this.getHandSwingProgress(livingEntity, g);
        this.model.riding = livingEntity.hasVehicle();
        this.model.child = livingEntity.isBaby();
        float h = MathHelper.lerpAngleDegrees(g, livingEntity.prevBodyYaw, livingEntity.bodyYaw);
        float j = MathHelper.lerpAngleDegrees(g, livingEntity.prevHeadYaw, livingEntity.headYaw);
        float k = j - h;
        float o;
        if (livingEntity.hasVehicle() && livingEntity.getVehicle() instanceof LivingEntity) {
            LivingEntity livingEntity2 = (LivingEntity)livingEntity.getVehicle();
            h = MathHelper.lerpAngleDegrees(g, livingEntity2.prevBodyYaw, livingEntity2.bodyYaw);
            k = j - h;
            o = MathHelper.wrapDegrees(k);
            if (o < -85.0F) {
                o = -85.0F;
            }
            if (o >= 85.0F) {
                o = 85.0F;
            }
            h = j - o;
            if (o * o > 2500.0F) {
                h += o * 0.2F;
            }
            k = j - h;
        }
        float m = MathHelper.lerp(g, livingEntity.prevPitch, livingEntity.getPitch());
        float p;
        o = this.getAnimationProgress(livingEntity, g);
        this.setupTransforms(livingEntity, matrixStack, h, g);
        matrixStack.scale(-1.0F, -1.0F, 1.0F);
        matrixStack.translate(0.0D, -1.5010000467300415D, 0.0D);
        p = 0.0F;
        float q = 0.0F;
        if (!livingEntity.hasVehicle() && livingEntity.isAlive()) {
            p = MathHelper.lerp(g, livingEntity.lastLimbDistance, livingEntity.limbDistance);
            q = livingEntity.limbAngle - livingEntity.limbDistance * (1.0F - g);
            if (livingEntity.isBaby()) {
                q *= 3.0F;
            }
            if (p > 1.0F) {
                p = 1.0F;
            }
        }
        this.model.animateModel(livingEntity, q, p, g);
        this.model.setAngles(livingEntity, q, p, o, k, m);
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        boolean bl = this.isVisible(livingEntity);
        boolean bl2 = !bl && !livingEntity.isInvisibleTo(minecraftClient.player);
        boolean bl3 = minecraftClient.hasOutline(livingEntity);
        RenderLayer renderLayer = this.getRenderLayer(livingEntity, bl, bl2, bl3);
        if (renderLayer != null) {
            VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(renderLayer);
            int r = getOverlay(livingEntity, 0);
            this.model.render(matrixStack, vertexConsumer, i, r, 1.0F, 1.0F, 1.0F, bl2 ? 0.15F : 1.0F);
        }

        matrixStack.pop();
        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Nullable
    protected RenderLayer getRenderLayer(SnowGolemHeadEntity entity, boolean showBody, boolean translucent, boolean showOutline) {
        Identifier identifier = this.getTexture(entity);
        if (translucent) {
            return RenderLayer.getItemEntityTranslucentCull(identifier);
        } else if (showBody) {
            return this.model.getLayer(identifier);
        } else {
            return showOutline ? RenderLayer.getOutline(identifier) : null;
        }
    }

    public static int getOverlay(LivingEntity entity, float whiteOverlayProgress) {
        return OverlayTexture.packUv(OverlayTexture.getU(whiteOverlayProgress), OverlayTexture.getV(entity.hurtTime > 0 || entity.deathTime > 0));
    }

    protected boolean isVisible(SnowGolemHeadEntity entity) {
        return !entity.isInvisible();
    }

    protected void setupTransforms(SnowGolemHeadEntity entity, MatrixStack matrices, float bodyYaw, float tickDelta) {
        EntityPose entityPose = entity.getPose();
        if (entityPose != EntityPose.SLEEPING) {
            matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(180.0F - bodyYaw));
        }
        if (entity.deathTime > 0) {
            float f = ((float)entity.deathTime + tickDelta - 1.0F) / 20.0F * 1.6F;
            f = MathHelper.sqrt(f);
            if (f > 1.0F) {
                f = 1.0F;
            }
            matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(f * 90.0F));
        } else if (entity.isUsingRiptide()) {
            matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(-90.0F - entity.getPitch()));
            matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(((float)entity.age + tickDelta) * -75.0F));
        } else if (entity.hasCustomName()) {
            String string = Formatting.strip(entity.getName().getString());
            if (("Dinnerbone".equals(string) || "Grumm".equals(string))) {
                matrices.translate(0.0D, entity.getHeight() + 0.1F, 0.0D);
                matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(180.0F));
            }
        }
    }

    protected float getHandSwingProgress(SnowGolemHeadEntity entity, float tickDelta) {
        return entity.getHandSwingProgress(tickDelta);
    }

    protected float getAnimationProgress(SnowGolemHeadEntity entity, float tickDelta) {
        return (float)entity.age + tickDelta;
    }

    protected boolean hasLabel(SnowGolemHeadEntity livingEntity) {
        double d = this.dispatcher.getSquaredDistanceToCamera(livingEntity);
        float f = livingEntity.isSneaky() ? 32.0F : 64.0F;
        if (d >= (double)(f * f)) {
            return false;
        } else {
            MinecraftClient minecraftClient = MinecraftClient.getInstance();
            ClientPlayerEntity clientPlayerEntity = minecraftClient.player;
            boolean bl = !livingEntity.isInvisibleTo(clientPlayerEntity);
            AbstractTeam abstractTeam = livingEntity.getScoreboardTeam();
            AbstractTeam abstractTeam2 = null;
            if (clientPlayerEntity != null) {
                abstractTeam2 = clientPlayerEntity.getScoreboardTeam();
            }
            if (abstractTeam != null) {
                AbstractTeam.VisibilityRule visibilityRule = abstractTeam.getNameTagVisibilityRule();
                switch(visibilityRule) {
                    case ALWAYS:
                        return bl;
                    case NEVER:
                        return false;
                    case HIDE_FOR_OTHER_TEAMS:
                        return abstractTeam2 == null ? bl : abstractTeam.isEqual(abstractTeam2) && (abstractTeam.shouldShowFriendlyInvisibles() || bl);
                    case HIDE_FOR_OWN_TEAM:
                        return abstractTeam2 == null ? bl : !abstractTeam.isEqual(abstractTeam2) && bl;
                    default:
                        return true;
                }
            }
            return MinecraftClient.isHudEnabled() && livingEntity != minecraftClient.getCameraEntity() && bl && !livingEntity.hasPassengers();
        }
    }
}
