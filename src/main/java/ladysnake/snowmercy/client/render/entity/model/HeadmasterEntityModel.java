package ladysnake.snowmercy.client.render.entity.model;

import ladysnake.snowmercy.common.SnowMercy;
import ladysnake.snowmercy.common.entity.HeadmasterEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class HeadmasterEntityModel extends AnimatedGeoModel<HeadmasterEntity> {
    @Override
    public Identifier getModelLocation(HeadmasterEntity object) {
        return new Identifier(SnowMercy.MODID, "geo/headmaster.geo.json");
    }

    @Override
    public Identifier getTextureLocation(HeadmasterEntity object) {
        return new Identifier(SnowMercy.MODID, "textures/entity/headmaster.png");
    }

    @Override
    public Identifier getAnimationFileLocation(HeadmasterEntity animatable) {
        return new Identifier(SnowMercy.MODID, "animations/headmaster.animations.json");
    }

    @Override
    public void setLivingAnimations(HeadmasterEntity headmaster, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(headmaster, uniqueID, customPredicate);
        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);

        if (!headmaster.isTurret()) {
            IBone head = this.getAnimationProcessor().getBone("head");
            IBone torso = this.getAnimationProcessor().getBone("torso");

            head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
            head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));

            torso.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F) * 0.25f);
        } else {
            IBone pretorso = this.getAnimationProcessor().getBone("pretorso");

            pretorso.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
            pretorso.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
        }

    }

}
