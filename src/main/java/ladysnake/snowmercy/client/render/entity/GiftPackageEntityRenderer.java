/*
 * Decompiled with CFR 0.0.9 (FabricMC cc05e23f).
 */
package ladysnake.snowmercy.client.render.entity;

import ladysnake.snowmercy.client.SnowMercyClient;
import ladysnake.snowmercy.client.render.entity.model.GiftPackageEntityModel;
import ladysnake.snowmercy.common.SnowMercy;
import ladysnake.snowmercy.common.entity.GiftPackageEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

import java.util.Random;

@Environment(value = EnvType.CLIENT)
public class GiftPackageEntityRenderer extends EntityRenderer<GiftPackageEntity> {
    private static final Identifier TEXTURE = new Identifier(SnowMercy.MODID, "textures/entity/gift_package.png");
    private final GiftPackageEntityModel<GiftPackageEntity> model;

    public GiftPackageEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.shadowRadius = 0;
        this.shadowOpacity = 0;

        this.model = new GiftPackageEntityModel<>(context.getPart(SnowMercyClient.GIFT_PACKAGE_MODEL_LAYER));

        Random random = new Random();
    }

    public void render(GiftPackageEntity giftPackageEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {

        VertexConsumerProvider.Immediate immediate = MinecraftClient.getInstance().getBufferBuilders().getEntityVertexConsumers();
        VertexConsumer vertexConsumer2 = immediate.getBuffer(RenderLayer.getEntityCutoutNoCull(this.getTexture(giftPackageEntity)));


        matrixStack.scale(-1.0f, -1.0f, 1.0f);
        matrixStack.translate(0, -0.7, 0);

        this.model.setAngles(giftPackageEntity, 0, 0, 0, 0, 0);
        this.model.render(matrixStack, vertexConsumer2, i, OverlayTexture.DEFAULT_UV, 1, 1, 1, 1.0f);
        immediate.draw();

        super.render(giftPackageEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public Identifier getTexture(GiftPackageEntity entity) {
        return TEXTURE;
    }

    @Override
    public boolean shouldRender(GiftPackageEntity entity, Frustum frustum, double x, double y, double z) {
        return true;
    }

}

