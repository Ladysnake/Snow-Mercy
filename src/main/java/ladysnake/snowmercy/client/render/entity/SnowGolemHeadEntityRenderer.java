package ladysnake.snowmercy.client.render.entity;

import ladysnake.snowmercy.client.SnowMercyClient;
import ladysnake.snowmercy.client.render.entity.model.SnowGolemHeadEntityModel;
import ladysnake.snowmercy.client.render.entity.model.SnugglesEntityModel;
import ladysnake.snowmercy.common.SnowMercy;
import ladysnake.snowmercy.common.entity.SnowGolemHeadEntity;
import ladysnake.snowmercy.common.entity.SnugglesEntity;
import ladysnake.snowmercy.common.entity.WeaponizedSnowGolemEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class SnowGolemHeadEntityRenderer extends WeaponizedSnowGolemEntityRenderer<SnowGolemHeadEntity, SnowGolemHeadEntityModel> {
    private static final Identifier TEXTURE_SNUGGLES = new Identifier(SnowMercy.MODID, "textures/entity/snuggles.png");
    private static final Identifier TEXTURE_SAW = new Identifier(SnowMercy.MODID, "textures/entity/sawman.png");
    private static final Identifier TEXTURE_ROCKETS = new Identifier(SnowMercy.MODID, "textures/entity/rockets.png");
    private static final Identifier TEXTURE_MORTARS = new Identifier(SnowMercy.MODID, "textures/entity/mortars.png");
    private static final Identifier TEXTURE_CHILL_SNUGGLES = new Identifier(SnowMercy.MODID, "textures/entity/chill_snuggles.png");

    public SnowGolemHeadEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new SnowGolemHeadEntityModel(context.getPart(SnowMercyClient.SNOW_GOLEM_HEAD_MODEL_LAYER)), 0.5F);
    }

    @Override
    public void render(WeaponizedSnowGolemEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    

    @Override
    public Identifier getTexture(WeaponizedSnowGolemEntity snowGolemEntity) {
        switch (((SnowGolemHeadEntity) snowGolemEntity).getGolemType()) {
            case 0:
                return TEXTURE_SAW;
            case 1:
                return TEXTURE_SNUGGLES;
            case 2:
                return TEXTURE_ROCKETS;
            case 3:
                return TEXTURE_MORTARS;
            default:
                return TEXTURE_CHILL_SNUGGLES;
        }
    }
}
