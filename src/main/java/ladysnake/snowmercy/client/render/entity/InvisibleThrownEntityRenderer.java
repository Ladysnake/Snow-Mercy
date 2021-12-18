package ladysnake.snowmercy.client.render.entity;

import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.entity.projectile.thrown.ThrownEntity;
import net.minecraft.util.Identifier;

public class InvisibleThrownEntityRenderer extends EntityRenderer<ThrownEntity> {
    public InvisibleThrownEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Override
    public Identifier getTexture(ThrownEntity entity) {
        return null;
    }
}
