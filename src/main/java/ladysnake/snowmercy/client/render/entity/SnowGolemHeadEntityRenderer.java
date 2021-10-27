package ladysnake.snowmercy.client.render.entity;

import ladysnake.snowmercy.client.SnowMercyClient;
import ladysnake.snowmercy.client.render.entity.model.SnowGolemHeadEntityModel;
import ladysnake.snowmercy.common.entity.SnowGolemHeadEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class SnowGolemHeadEntityRenderer extends MobEntityRenderer<SnowGolemHeadEntity, SnowGolemHeadEntityModel> {
    public SnowGolemHeadEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new SnowGolemHeadEntityModel(context.getPart(SnowMercyClient.SNOW_GOLEM_HEAD_MODEL_LAYER)), 0.5F);
    }

    @Override
    public Identifier getTexture(SnowGolemHeadEntity head) {
        return head.getGolemType().getTexture();
    }
}
