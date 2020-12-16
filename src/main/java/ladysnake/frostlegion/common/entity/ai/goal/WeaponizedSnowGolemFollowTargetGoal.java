package ladysnake.frostlegion.common.entity.ai.goal;

import ladysnake.frostlegion.common.entity.WeaponizedSnowGolemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.FollowTargetGoal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;

import java.util.function.Predicate;

public class WeaponizedSnowGolemFollowTargetGoal extends FollowTargetGoal<LivingEntity> {
    public final int requiredHead;

    public WeaponizedSnowGolemFollowTargetGoal(WeaponizedSnowGolemEntity mob, Class targetClass, boolean checkVisibility, int requiredHead) {
        super(mob, targetClass, checkVisibility);
        this.requiredHead = requiredHead;
    }

    public WeaponizedSnowGolemFollowTargetGoal(WeaponizedSnowGolemEntity mob, Class targetClass, boolean checkVisibility, boolean checkCanNavigate, int requiredHead) {
        super(mob, targetClass, checkVisibility, checkCanNavigate);
        this.requiredHead = requiredHead;
    }

    public WeaponizedSnowGolemFollowTargetGoal(WeaponizedSnowGolemEntity mob, Class targetClass, int reciprocalChance, boolean checkVisibility, boolean checkCanNavigate, int requiredHead, Predicate<LivingEntity> targetPredicate) {
        super(mob, targetClass, reciprocalChance, checkVisibility, checkCanNavigate, targetPredicate);
        this.requiredHead = requiredHead;
    }

    @Override
    public boolean canStart() {
        boolean hasCorrectHead = this.requiredHead == ((WeaponizedSnowGolemEntity) mob).getHead();

        return super.canStart() && hasCorrectHead && targetPredicate.test(mob, targetEntity);
    }

    @Override
    public boolean shouldContinue() {
        boolean hasCorrectHead = this.requiredHead == ((WeaponizedSnowGolemEntity) mob).getHead();

        return super.shouldContinue() && hasCorrectHead && targetPredicate.test(mob, targetEntity);
    }
}
