package ladysnake.snowmercy.client.render.entity;

import ladysnake.snowmercy.common.SnowMercy;
import ladysnake.snowmercy.common.entity.IceballEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.feature.SlimeOverlayFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.SlimeEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

@Environment(value = EnvType.CLIENT)
public class IceballEntityRenderer
        extends MobEntityRenderer<IceballEntity, SlimeEntityModel<IceballEntity>> {
    private static final Identifier TEXTURE = new Identifier(SnowMercy.MODID, "textures/entity/iceball.png");

    public IceballEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new SlimeEntityModel(context.getPart(EntityModelLayers.SLIME)), 0.25f);
        this.addFeature(new SlimeOverlayFeatureRenderer<IceballEntity>(this, context.getModelLoader()));
    }

    @Override
    public void render(IceballEntity IceballEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        this.shadowRadius = 0.25f * (float) IceballEntity.getSize();
        super.render(IceballEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    protected void scale(IceballEntity IceballEntity, MatrixStack matrixStack, float f) {
        float g = 0.999f;
        matrixStack.scale(0.999f, 0.999f, 0.999f);
        matrixStack.translate(0.0, 0.001f, 0.0);
        float h = IceballEntity.getSize();
        float i = MathHelper.lerp(f, IceballEntity.lastStretch, IceballEntity.stretch) / (h * 0.5f + 1.0f);
        float j = 1.0f / (i + 1.0f);
        matrixStack.scale(j * h, 1.0f / j * h, j * h);
    }

    @Override
    public Identifier getTexture(IceballEntity IceballEntity) {
        return TEXTURE;
    }
}

