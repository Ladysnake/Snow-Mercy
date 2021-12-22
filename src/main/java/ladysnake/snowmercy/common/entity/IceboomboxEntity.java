package ladysnake.snowmercy.common.entity;

import ladysnake.snowmercy.common.entity.ai.goal.FollowGoal;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;

public class IceboomboxEntity extends WeaponizedSnowGolemEntity {
    public IceboomboxEntity(EntityType<IceboomboxEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createEntityAttributes() {
        return WeaponizedSnowGolemEntity.createEntityAttributes().add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 0.0d);
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(1, new FollowGoal(this, 1.0D, false, 4));
    }

    @Override
    public WeaponizedGolemType getGolemType() {
        return WeaponizedGolemType.BOOMBOX;
    }

    @Override
    public void tick() {
        super.tick();

        if (world.isClient && this.age % 10 == 0) {
            world.addParticle(ParticleTypes.NOTE, this.getX(), this.getY() + 2.1, this.getZ(), (double) random.nextInt(13) / 24.0, 0.0, 0.0);
        }
    }
}
