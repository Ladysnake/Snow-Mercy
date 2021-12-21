/*
 * Decompiled with CFR 0.0.9 (FabricMC cc05e23f).
 */
package ladysnake.snowmercy.client.render.entity;

import ladysnake.snowmercy.client.SnowMercyClient;
import ladysnake.snowmercy.client.render.entity.model.IceHeartEntityModel;
import ladysnake.snowmercy.common.SnowMercy;
import ladysnake.snowmercy.common.entity.IceHeartEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.*;

import java.util.ArrayList;
import java.util.List;

@Environment(value = EnvType.CLIENT)
public class IceHeartEntityRenderer extends EntityRenderer<IceHeartEntity> {
    public static final Identifier BEAM_TEXTURE = new Identifier(SnowMercy.MODID, "textures/entity/ice_heart_beam.png");
    public static final int MAX_BEAM_HEIGHT = 1024;
    private static final Identifier TEXTURE = new Identifier(SnowMercy.MODID, "textures/entity/heart_of_ice.png");
    private IceHeartEntityModel<IceHeartEntity> model;

    public IceHeartEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.shadowRadius = 0;
        this.shadowOpacity = 0;

        this.model = new IceHeartEntityModel(context.getPart(SnowMercyClient.HEART_OF_ICE_MODEL_LAYER));
    }

    private static void renderBeam(IceHeartEntity iceHeartEntity, MatrixStack matrices, VertexConsumerProvider vertexConsumers, float tickDelta, long worldTime, int yOffset, int maxY, float[] color) {
        renderBeam(iceHeartEntity, matrices, vertexConsumers, BEAM_TEXTURE, tickDelta, 1.0f, worldTime, yOffset, maxY, color, 0.2f, 0.25f);
    }

    public static void renderBeam(IceHeartEntity iceHeartEntity, MatrixStack matrices, VertexConsumerProvider vertexConsumers, Identifier textureId, float tickDelta, float heightScale, long worldTime, int yOffset, int maxY, float[] color, float innerRadius, float outerRadius) {
        int i = yOffset + maxY;
        matrices.push();
        matrices.translate(0, 0.25, 0);
        float f = (float) Math.floorMod(worldTime, 40) + tickDelta;
        float g = maxY < 0 ? f : -f;
        float h = MathHelper.fractionalPart(g * 0.2f - (float) MathHelper.floor(g * 0.1f));
        float j = color[0];

        float k = color[1];
        float l = color[2];
        if (iceHeartEntity.isActive()) {
            k = (float) Math.abs(Math.cos(worldTime / 10f));
            l = (float) Math.abs(Math.cos(worldTime / 10f));
        }

        matrices.push();
        matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(f * 2.25f - 45.0f));
        float m = 0.0f;
        float n = innerRadius;
        float o = innerRadius;
        float p = 0.0f;
        float q = -innerRadius;
        float r = 0.0f;
        float s = 0.0f;
        float t = -innerRadius;
        float u = 0.0f;
        float v = 1.0f;
        float w = -1.0f + h;
        float x = (float) maxY * heightScale * (0.5f / innerRadius) + w;
        renderBeamLayer(matrices, vertexConsumers.getBuffer(RenderLayer.getBeaconBeam(textureId, false)), j, k, l, 1.0f, yOffset, i, 0.0f, n, o, 0.0f, q, 0.0f, 0.0f, t, 0.0f, 1.0f, x, w);
        matrices.pop();
        m = -outerRadius;
        n = -outerRadius;
        o = outerRadius;
        p = -outerRadius;
        q = -outerRadius;
        r = outerRadius;
        s = outerRadius;
        t = outerRadius;
        u = 0.0f;
        v = 1.0f;
        w = -1.0f + h;
        x = (float) maxY * heightScale + w;
        renderBeamLayer(matrices, vertexConsumers.getBuffer(RenderLayer.getBeaconBeam(textureId, true)), j, k, l, 0.125f, yOffset, i, m, n, o, p, q, r, s, t, 0.0f, 1.0f, x, w);
        matrices.pop();
    }

    private static void renderBeamLayer(MatrixStack matrices, VertexConsumer vertices, float red, float green, float blue, float alpha, int yOffset, int height, float x1, float z1, float x2, float z2, float x3, float z3, float x4, float z4, float u1, float u2, float v1, float v2) {
        MatrixStack.Entry entry = matrices.peek();
        Matrix4f matrix4f = entry.getPositionMatrix();
        Matrix3f matrix3f = entry.getNormalMatrix();
        renderBeamFace(matrix4f, matrix3f, vertices, red, green, blue, alpha, yOffset, height, x1, z1, x2, z2, u1, u2, v1, v2);
        renderBeamFace(matrix4f, matrix3f, vertices, red, green, blue, alpha, yOffset, height, x4, z4, x3, z3, u1, u2, v1, v2);
        renderBeamFace(matrix4f, matrix3f, vertices, red, green, blue, alpha, yOffset, height, x2, z2, x4, z4, u1, u2, v1, v2);
        renderBeamFace(matrix4f, matrix3f, vertices, red, green, blue, alpha, yOffset, height, x3, z3, x1, z1, u1, u2, v1, v2);
    }

    private static void renderBeamFace(Matrix4f positionMatrix, Matrix3f normalMatrix, VertexConsumer vertices, float red, float green, float blue, float alpha, int yOffset, int height, float x1, float z1, float x2, float z2, float u1, float u2, float v1, float v2) {
        renderBeamVertex(positionMatrix, normalMatrix, vertices, red, green, blue, alpha, height, x1, z1, u2, v1);
        renderBeamVertex(positionMatrix, normalMatrix, vertices, red, green, blue, alpha, yOffset, x1, z1, u2, v2);
        renderBeamVertex(positionMatrix, normalMatrix, vertices, red, green, blue, alpha, yOffset, x2, z2, u1, v2);
        renderBeamVertex(positionMatrix, normalMatrix, vertices, red, green, blue, alpha, height, x2, z2, u1, v1);
    }

    /**
     * @param v the top-most coordinate of the texture region
     * @param u the left-most coordinate of the texture region
     */
    private static void renderBeamVertex(Matrix4f positionMatrix, Matrix3f normalMatrix, VertexConsumer vertices, float red, float green, float blue, float alpha, int y, float x, float z, float u, float v) {
        vertices.vertex(positionMatrix, x, y, z).color(red, green, blue, alpha).texture(u, v).overlay(OverlayTexture.DEFAULT_UV).light(LightmapTextureManager.MAX_LIGHT_COORDINATE).normal(normalMatrix, 0.0f, 1.0f, 0.0f).next();
    }

    public void render(IceHeartEntity iceHeartEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {

        VertexConsumerProvider.Immediate immediate = MinecraftClient.getInstance().getBufferBuilders().getEntityVertexConsumers();
        VertexConsumer vertexConsumer2 = immediate.getBuffer(RenderLayer.getEntityCutoutNoCull(this.getTexture(iceHeartEntity)));

        float g1 = 1.0f;
        float b1 = 1.0f;
        long l = iceHeartEntity.getWorld().getTime();
        if (iceHeartEntity.isActive()) {
            g1 = (float) Math.abs(Math.cos(l / 10f));
            b1 = (float) Math.abs(Math.cos(l / 10f));
        }

        this.model.setAngles(iceHeartEntity, 0, 0, 0, 0, 0);
        this.model.render(matrixStack, vertexConsumer2, i, OverlayTexture.DEFAULT_UV, 1.0f, g1, b1, 1.0f);
        immediate.draw();

        super.render(iceHeartEntity, f, g, matrixStack, vertexConsumerProvider, i);

        List<BeaconBlockEntity.BeamSegment> list = new ArrayList<>();
        list.add(new BeaconBlockEntity.BeamSegment(new float[]{1.0f, 1.0f, 1.0f}));
        int k = 0;
        for (int m = 0; m < list.size(); ++m) {
            BeaconBlockEntity.BeamSegment beamSegment = list.get(m);
            renderBeam(iceHeartEntity, matrixStack, vertexConsumerProvider, f, l, k, m == list.size() - 1 ? 1024 : beamSegment.getHeight(), beamSegment.getColor());
            k += beamSegment.getHeight();
        }
    }

    @Override
    public Identifier getTexture(IceHeartEntity entity) {
        return TEXTURE;
    }

    @Override
    protected int getBlockLight(IceHeartEntity entity, BlockPos pos) {
        return 15;
    }

    @Override
    public boolean shouldRender(IceHeartEntity entity, Frustum frustum, double x, double y, double z) {
        return true;
    }

}

