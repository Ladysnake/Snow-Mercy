package ladysnake.snowmercy.client.render;

import ladysnake.snowmercy.client.render.entity.SnugglesEntityRenderer;
import ladysnake.snowmercy.client.render.entity.WeaponizedSnowGolemEntityRenderer;
import ladysnake.snowmercy.client.render.entity.model.WeaponizedSnowGolemEntityModel;
import ladysnake.snowmercy.common.entity.WeaponizedSnowGolemEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3f;

@Environment(EnvType.CLIENT)
public class WeaponizedSnowmanPumpkinFeatureRenderer extends FeatureRenderer<WeaponizedSnowGolemEntity, WeaponizedSnowGolemEntityModel<WeaponizedSnowGolemEntity>> {
    public WeaponizedSnowmanPumpkinFeatureRenderer(WeaponizedSnowGolemEntityRenderer featureRendererContext) {
        super(featureRendererContext);
    }

    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, WeaponizedSnowGolemEntity snowGolemEntity, float f, float g, float h, float j, float k, float l) {
        if (snowGolemEntity.getHead() == 2) {
            MinecraftClient minecraftClient = MinecraftClient.getInstance();
            boolean bl = minecraftClient.hasOutline(snowGolemEntity) && snowGolemEntity.isInvisible();
            if (!snowGolemEntity.isInvisible() || bl) {
                matrixStack.push();
                this.getContextModel().getHead().rotate(matrixStack);
                matrixStack.translate(0.0D, -0.34375D, 0.0D);
                matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(180.0F));
                matrixStack.scale(0.625F, -0.625F, -0.625F);
                ItemStack itemStack = new ItemStack(Blocks.CARVED_PUMPKIN);
                if (bl) {
                    BlockState blockState = Blocks.CARVED_PUMPKIN.getDefaultState();
                    BlockRenderManager blockRenderManager = minecraftClient.getBlockRenderManager();
                    BakedModel bakedModel = blockRenderManager.getModel(blockState);
                    int n = LivingEntityRenderer.getOverlay(snowGolemEntity, 0.0F);
                    matrixStack.translate(-0.5D, -0.5D, -0.5D);
                    blockRenderManager.getModelRenderer().render(matrixStack.peek(), vertexConsumerProvider.getBuffer(RenderLayer.getOutline(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE)), blockState, bakedModel, 0.0F, 0.0F, 0.0F, i, n);
                } else {
                    minecraftClient.getItemRenderer().renderItem(snowGolemEntity, itemStack, ModelTransformation.Mode.HEAD, false, matrixStack, vertexConsumerProvider, snowGolemEntity.world, i, LivingEntityRenderer.getOverlay(snowGolemEntity, 0.0F), snowGolemEntity.getId());
                }

                matrixStack.pop();
            }
        }
    }
}

