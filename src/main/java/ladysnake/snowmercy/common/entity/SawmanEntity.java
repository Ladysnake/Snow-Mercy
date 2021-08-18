package ladysnake.snowmercy.common.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.World;

public class SawmanEntity extends WeaponizedSnowGolemEntity {
    public SawmanEntity(EntityType<SawmanEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(1, new MeleeAttackGoal(this, 1.0D, false));
    }
}
