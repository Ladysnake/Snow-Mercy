package ladysnake.frostlegion.common.entity.ai.goal;

import java.util.EnumSet;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.math.MathHelper;

public class SalvoProjectileAttackGoal extends Goal {
    private MobEntity mob;
    private RangedAttackMob owner;
    private LivingEntity target;
    private int updateCountdownTicks;
    private double mobSpeed;
    private int seenTargetTicks;
    private int minIntervalTicks;
    private int maxIntervalTicks;
    private float maxShootRange;
    private float squaredMaxShootRange;
    private int maxSalvo;
    private int currentSalvo;
    private int cooldownBetweenShots;
    private int currentCooldownBetweenShots;

    public SalvoProjectileAttackGoal(RangedAttackMob mob, double mobSpeed, int intervalTicks, float maxShootRange, int maxSalvo, int cooldownBetweenShots) {
        this(mob, mobSpeed, intervalTicks, intervalTicks, maxShootRange, maxSalvo, cooldownBetweenShots);
    }

    public SalvoProjectileAttackGoal(RangedAttackMob mob, double mobSpeed, int minIntervalTicks, int maxIntervalTicks, float maxShootRange, int maxSalvo, int cooldownBetweenShots) {
        this.updateCountdownTicks = -1;
        if (!(mob instanceof LivingEntity)) {
            throw new IllegalArgumentException("SalvoProjectileAttackGoal requires Mob implements RangedAttackMob");
        } else {
            this.owner = mob;
            this.mob = (MobEntity)mob;
            this.mobSpeed = mobSpeed;
            this.minIntervalTicks = minIntervalTicks;
            this.maxIntervalTicks = maxIntervalTicks;
            this.maxShootRange = maxShootRange;
            this.squaredMaxShootRange = maxShootRange * maxShootRange;
            this.setControls(EnumSet.of(Goal.Control.MOVE, Goal.Control.LOOK));
            this.maxSalvo = maxSalvo;
            this.cooldownBetweenShots = cooldownBetweenShots;
        }
    }

    public boolean canStart() {
        LivingEntity livingEntity = this.mob.getTarget();
        if (livingEntity != null && livingEntity.isAlive()) {
            this.target = livingEntity;
            return true;
        } else {
            return false;
        }
    }

    public boolean shouldContinue() {
        return this.canStart() || !this.mob.getNavigation().isIdle();
    }

    public void stop() {
        this.target = null;
        this.seenTargetTicks = 0;
        this.updateCountdownTicks = -1;
    }

    public void tick() {
        double d = this.mob.squaredDistanceTo(this.target.getX(), this.target.getY(), this.target.getZ());
        boolean bl = this.mob.getVisibilityCache().canSee(this.target);
        if (bl) {
            ++this.seenTargetTicks;
        } else {
            this.seenTargetTicks = 0;
        }

        if (d <= (double)this.squaredMaxShootRange && this.seenTargetTicks >= 5) {
            this.mob.getNavigation().stop();
        } else {
            this.mob.getNavigation().startMovingTo(this.target, this.mobSpeed);
        }

        this.mob.getLookControl().lookAt(this.target, 30.0F, 30.0F);
        float f;
        if (--this.updateCountdownTicks == 0) {
            if (!bl) {
                return;
            }

            f = MathHelper.sqrt(d) / this.maxShootRange;
            float g = MathHelper.clamp(f, 0.1F, 1.0F);
            this.currentCooldownBetweenShots = this.cooldownBetweenShots;
            this.currentSalvo = 0;
            this.updateCountdownTicks = MathHelper.floor(f * (float) (this.maxIntervalTicks - this.minIntervalTicks) + (float) this.minIntervalTicks);
        } else if (this.updateCountdownTicks < 0) {
            f = MathHelper.sqrt(d) / this.maxShootRange;
            this.updateCountdownTicks = MathHelper.floor(f * (float)(this.maxIntervalTicks - this.minIntervalTicks) + (float)this.minIntervalTicks);
        }

        if (this.currentSalvo < this.maxSalvo) {
            if (this.currentCooldownBetweenShots-- <= 0) {
                f = MathHelper.sqrt(d) / this.maxShootRange;
                float g = MathHelper.clamp(f, 0.1F, 1.0F);
                this.owner.attack(this.target, g);

                this.currentCooldownBetweenShots = this.cooldownBetweenShots;
                this.currentSalvo++;
            }
        }
    }
}
