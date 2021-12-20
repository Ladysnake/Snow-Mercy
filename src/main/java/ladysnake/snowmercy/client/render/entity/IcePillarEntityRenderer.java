/*
 * Decompiled with CFR 0.0.9 (FabricMC cc05e23f).
 */
package ladysnake.snowmercy.client.render.entity;

import ladysnake.snowmercy.client.SnowMercyClient;
import ladysnake.snowmercy.client.render.entity.model.IcePillarEntityModel;
import ladysnake.snowmercy.common.SnowMercy;
import ladysnake.snowmercy.common.entity.IcePillarEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

@Environment(value = EnvType.CLIENT)
public class IcePillarEntityRenderer extends EntityRenderer<IcePillarEntity> {
    private static final Identifier TEXTURE = new Identifier(SnowMercy.MODID, "textures/entity/ice_pillar.png");
    private IcePillarEntityModel<IcePillarEntity> model;

    public IcePillarEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.shadowRadius = 0;
        this.shadowOpacity = 0;

        this.model = new IcePillarEntityModel(context.getPart(SnowMercyClient.ICE_PILLAR_MODEL_LAYER));
    }

    public void render(IcePillarEntity icePillarEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {

        VertexConsumerProvider.Immediate immediate = MinecraftClient.getInstance().getBufferBuilders().getEntityVertexConsumers();
        VertexConsumer vertexConsumer2 = immediate.getBuffer(RenderLayer.getEntityCutoutNoCull(this.getTexture(icePillarEntity)));

        this.model.setAngles(icePillarEntity, 0, 0, 0, 0, 0);
        this.model.render(matrixStack, vertexConsumer2, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0f);
        immediate.draw();

        super.render(icePillarEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public Identifier getTexture(IcePillarEntity entity) {
        return TEXTURE;
    }

    @Override
    protected int getBlockLight(IcePillarEntity entity, BlockPos pos) {
        return 15;
    }
}

