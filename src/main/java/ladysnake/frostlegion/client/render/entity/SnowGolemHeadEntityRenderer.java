package ladysnake.frostlegion.client.render.entity;

import com.google.common.collect.Lists;
import ladysnake.frostlegion.client.render.entity.model.EvilSnowGolemEntityModel;
import ladysnake.frostlegion.common.entity.SnowGolemHeadEntity;
import ladysnake.frostlegion.common.init.EntityTypes;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.PlayerModelPart;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;

import java.util.Iterator;
import java.util.List;

@Environment(EnvType.CLIENT)
public class SnowGolemHeadEntityRenderer extends EntityRenderer<SnowGolemHeadEntity> {
    protected final List<FeatureRenderer<LivingEntity, EntityModel<LivingEntity>>> features = Lists.newArrayList();

    public SnowGolemHeadEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher);
    }

    public void render(SnowGolemHeadEntity golemHead, float yaw, float tickdelta, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int light) {
        EvilSnowGolemEntityModel model = this.getModel(golemHead);
        
        matrixStack.push();
        model.handSwingProgress = this.getHandSwingProgress(golemHead, tickdelta);
        model.riding = golemHead.hasVehicle();
        model.child = golemHead.isBaby();
        float h = MathHelper.lerpAngleDegrees(tickdelta, golemHead.prevBodyYaw, golemHead.bodyYaw);
        float j = MathHelper.lerpAngleDegrees(tickdelta, golemHead.prevHeadYaw, golemHead.headYaw);
        float k = j - h;
        float o;
        if (golemHead.hasVehicle() && golemHead.getVehicle() instanceof LivingEntity) {
            LivingEntity livingEntity2 = (LivingEntity)golemHead.getVehicle();
            h = MathHelper.lerpAngleDegrees(tickdelta, livingEntity2.prevBodyYaw, livingEntity2.bodyYaw);
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

        float m = MathHelper.lerp(tickdelta, golemHead.prevPitch, golemHead.pitch);
        float p;
        if (golemHead.getPose() == EntityPose.SLEEPING) {
            Direction direction = golemHead.getSleepingDirection();
            if (direction != null) {
                p = golemHead.getEyeHeight(EntityPose.STANDING) - 0.1F;
                matrixStack.translate((double)((float)(-direction.getOffsetX()) * p), 0.0D, (double)((float)(-direction.getOffsetZ()) * p));
            }
        }

        o = this.getAnimationProgress(golemHead, tickdelta);
        this.setupTransforms(golemHead, matrixStack, o, h, tickdelta);
        matrixStack.scale(-1.0F, -1.0F, 1.0F);
        this.scale(golemHead, matrixStack, tickdelta);
        matrixStack.translate(0.0D, -0.3D, 0.0D);
        p = 0.0F;
        float q = 0.0F;
        if (!golemHead.hasVehicle() && golemHead.isAlive()) {
            p = MathHelper.lerp(tickdelta, golemHead.lastLimbDistance, golemHead.limbDistance);
            q = golemHead.limbAngle - golemHead.limbDistance * (1.0F - tickdelta);
            if (golemHead.isBaby()) {
                q *= 3.0F;
            }

            if (p > 1.0F) {
                p = 1.0F;
            }
        }

        model.animateModel(golemHead, q, p, tickdelta);
        model.setAngles(golemHead, q, p, o, k, m);
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        boolean bl = this.isVisible(golemHead);
        boolean bl2 = !bl && !golemHead.isInvisibleTo(minecraftClient.player);
        boolean bl3 = minecraftClient.hasOutline(golemHead);
        RenderLayer renderLayer = this.getRenderLayer(golemHead, bl, bl2, bl3);
        if (renderLayer != null) {
            VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(renderLayer);
            int r = getOverlay(golemHead, this.getAnimationCounter(golemHead, tickdelta));
            model.head.render(matrixStack, vertexConsumer, light, r, 1.0F, 1.0F, 1.0F, bl2 ? 0.15F : 1.0F);
        }

        if (!golemHead.isSpectator()) {
            Iterator var23 = this.features.iterator();

            while(var23.hasNext()) {
                FeatureRenderer<LivingEntity, EntityModel<LivingEntity>> featureRenderer = (FeatureRenderer)var23.next();
                featureRenderer.render(matrixStack, vertexConsumerProvider, light, golemHead, q, p, tickdelta, o, k, m);
            }
        }

        matrixStack.pop();
        super.render(golemHead, yaw, tickdelta, matrixStack, vertexConsumerProvider, light);
    }

    public Identifier getTexture(SnowGolemHeadEntity golemHeadEntity) {
        return EntityTypes.GOLEM_MODELS_AND_TEXTURES.get(golemHeadEntity.getGolemType()).getTexture();
    }

    public EvilSnowGolemEntityModel getModel(SnowGolemHeadEntity golemHeadEntity) {
        return (EvilSnowGolemEntityModel) EntityTypes.GOLEM_MODELS_AND_TEXTURES.get(golemHeadEntity.getGolemType()).getEntityModel();
    }

    public static int getOverlay(LivingEntity entity, float whiteOverlayProgress) {
        return OverlayTexture.packUv(OverlayTexture.getU(whiteOverlayProgress), OverlayTexture.getV(entity.hurtTime > 0 || entity.deathTime > 0));
    }

    /**
     * Gets the render layer appropriate for rendering the passed entity. Returns null if the entity should not be rendered.
     */
    protected RenderLayer getRenderLayer(SnowGolemHeadEntity entity, boolean showBody, boolean translucent, boolean showOutline) {
        Identifier identifier = this.getTexture(entity);
        EvilSnowGolemEntityModel model = this.getModel(entity);
        if (translucent) {
            return RenderLayer.getItemEntityTranslucentCull(identifier);
        } else if (showBody) {
            return model.getLayer(identifier);
        } else {
            return showOutline ? RenderLayer.getOutline(identifier) : null;
        }
    }

    protected boolean isVisible(LivingEntity entity) {
        return !entity.isInvisible();
    }

    private static float getYaw(Direction direction) {
        switch(direction) {
            case SOUTH:
                return 90.0F;
            case WEST:
                return 0.0F;
            case NORTH:
                return 270.0F;
            case EAST:
                return 180.0F;
            default:
                return 0.0F;
        }
    }

    /**
     * Returns if this entity is shaking in the way a zombie villager,
     * zombie, husk, or piglin undergoing conversion shakes.
     * husk, or piglin are undergoing conversion.
     */
    protected boolean isShaking(LivingEntity entity) {
        return false;
    }

    protected void setupTransforms(LivingEntity entity, MatrixStack matrices, float animationProgress, float bodyYaw, float tickDelta) {
        if (this.isShaking(entity)) {
            bodyYaw += (float)(Math.cos((double)entity.age * 3.25D) * 3.141592653589793D * 0.4000000059604645D);
        }

        EntityPose entityPose = entity.getPose();
        if (entityPose != EntityPose.SLEEPING) {
            matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(180.0F - bodyYaw));
        }

        if (entity.deathTime > 0) {
            float f = ((float)entity.deathTime + tickDelta - 1.0F) / 20.0F * 1.6F;
            f = MathHelper.sqrt(f);
            if (f > 1.0F) {
                f = 1.0F;
            }

            matrices.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(f * this.getLyingAngle(entity)));
        } else if (entity.isUsingRiptide()) {
            matrices.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(-90.0F - entity.pitch));
            matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(((float)entity.age + tickDelta) * -75.0F));
        } else if (entityPose == EntityPose.SLEEPING) {
            Direction direction = entity.getSleepingDirection();
            float g = direction != null ? getYaw(direction) : bodyYaw;
            matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(g));
            matrices.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(this.getLyingAngle(entity)));
            matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(270.0F));
        } else if (entity.hasCustomName() || entity instanceof PlayerEntity) {
            String string = Formatting.strip(entity.getName().getString());
            if (("Dinnerbone".equals(string) || "Grumm".equals(string)) && (!(entity instanceof PlayerEntity) || ((PlayerEntity)entity).isPartVisible(PlayerModelPart.CAPE))) {
                matrices.translate(0.0D, (double)(entity.getHeight() + 0.1F), 0.0D);
                matrices.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(180.0F));
            }
        }

    }

    protected float getHandSwingProgress(LivingEntity entity, float tickDelta) {
        return entity.getHandSwingProgress(tickDelta);
    }

    /**
     * This value is passed to other methods when calculating angles for animation.
     * It's typically just the sum of the entity's age (in ticks) and the passed in tickDelta.
     */
    protected float getAnimationProgress(LivingEntity entity, float tickDelta) {
        return (float)entity.age + tickDelta;
    }

    protected float getLyingAngle(LivingEntity entity) {
        return 90.0F;
    }

    protected float getAnimationCounter(LivingEntity entity, float tickDelta) {
        return 0.0F;
    }

    protected void scale(LivingEntity entity, MatrixStack matrices, float amount) {
    }

}

