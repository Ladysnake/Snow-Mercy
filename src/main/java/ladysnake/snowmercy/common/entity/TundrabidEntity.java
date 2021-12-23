package ladysnake.snowmercy.common.entity;

import ladysnake.snowmercy.common.entity.ai.goal.GoToHeartGoal;
import ladysnake.snowmercy.mixin.FoxEntityInvoker;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class TundrabidEntity extends FoxEntity implements SnowMercyEnemy {
    public TundrabidEntity(EntityType<? extends FoxEntity> entityType, World world) {
        super(entityType, world);

        ((FoxEntityInvoker) this).invokeSetType(Type.SNOW);
    }

    @Override
    protected void initGoals() {
        super.initGoals();

        this.targetSelector.add(1, new GoToHeartGoal(this, 1.0f, false, 20));
    }

    public static DefaultAttributeContainer.Builder createTundrabidAttributes() {
        return FoxEntity.createFoxAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3f).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 64.0f);
    }

    @Override
    public boolean canFreeze() {
        return false;
    }

    @Override
    public boolean isFreezing() {
        return false;
    }

    @Override
    protected void addPowderSnowSlowIfNeeded() {
    }

    @Override
    public boolean isPersistent() {
        return true;
    }

    @Override
    public boolean canTarget(LivingEntity target) {
        return target instanceof PlayerEntity || target instanceof WeaponizedSnowGolemEntity;
    }
}
