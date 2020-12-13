package ladysnake.frostlegion.common.entity.ai.goal;

import java.util.EnumSet;

import ladysnake.frostlegion.common.entity.BallistaEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.math.MathHelper;

public class BallistaProjectileAttackGoal extends Goal {
    private final BallistaEntity ballistaEntity;
    private final RangedAttackMob owner;
    private LivingEntity target;
    private int updateCountdownTicks;
    private final double mobSpeed;
    private int seenTargetTicks;
    private final float maxShootRange;
    private final float squaredMaxShootRange;

    public BallistaProjectileAttackGoal(BallistaEntity ballistaEntity, double mobSpeed, float maxShootRange) {
        this.updateCountdownTicks = -1;
        if (!(ballistaEntity instanceof LivingEntity)) {
            throw new IllegalArgumentException("ArrowAttackGoal requires Mob implements RangedAttackMob");
        } else {
            this.owner = ballistaEntity;
            this.ballistaEntity = ballistaEntity;
            this.mobSpeed = mobSpeed;
            this.maxShootRange = maxShootRange;
            this.squaredMaxShootRange = maxShootRange * maxShootRange;
            this.setControls(EnumSet.of(Goal.Control.MOVE, Goal.Control.LOOK));
        }
    }

    public boolean canStart() {
        LivingEntity livingEntity = this.ballistaEntity.getTarget();
        if (livingEntity != null && livingEntity.isAlive()) {
            this.target = livingEntity;
            return true;
        } else {
            return false;
        }
    }

    public boolean shouldContinue() {
        return this.canStart() || !this.ballistaEntity.getNavigation().isIdle();
    }

    public void stop() {
        this.target = null;
        this.seenTargetTicks = 0;
        this.updateCountdownTicks = -1;
    }

    public void tick() {
        double d = this.ballistaEntity.squaredDistanceTo(this.target.getX(), this.target.getY(), this.target.getZ());
        boolean bl = this.ballistaEntity.getVisibilityCache().canSee(this.target);
        if (bl) {
            ++this.seenTargetTicks;
        } else {
            this.seenTargetTicks = 0;
        }

        if (d <= (double)this.squaredMaxShootRange && this.seenTargetTicks >= 5) {
            this.ballistaEntity.getNavigation().stop();
        } else {
            this.ballistaEntity.getNavigation().startMovingTo(this.target, this.mobSpeed);
        }

        this.ballistaEntity.getLookControl().lookAt(this.target, 30.0F, 30.0F);
        float f;
        if (--this.updateCountdownTicks == 0) {
            if (!bl) {
                return;
            }

            f = MathHelper.sqrt(d) / this.maxShootRange;
            float g = MathHelper.clamp(f, 0.1F, 1.0F);
            this.owner.attack(this.target, g);
            this.updateCountdownTicks = MathHelper.floor(Math.max(1f, 20f - ((float) ballistaEntity.frostLevel)));
        } else if (this.updateCountdownTicks < 0) {
            f = MathHelper.sqrt(d) / this.maxShootRange;
            this.updateCountdownTicks = MathHelper.floor(Math.max(1f, 20f - ((float) ballistaEntity.frostLevel)));
        }

    }
}
