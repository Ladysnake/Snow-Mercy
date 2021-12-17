package ladysnake.snowmercy.client.render.entity;

import ladysnake.snowmercy.common.entity.BurningCoalEntity;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

public class BurningCoalEntityRenderer extends EntityRenderer<BurningCoalEntity> {
    public BurningCoalEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Override
    public Identifier getTexture(BurningCoalEntity entity) {
        return null;
    }
}
