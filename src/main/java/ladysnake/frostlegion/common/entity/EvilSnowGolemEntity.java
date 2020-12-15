package ladysnake.frostlegion.common.entity;

import net.minecraft.enchantment.FrostWalkerEnchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.passive.SnowGolemEntity;
import net.minecraft.world.World;

public class EvilSnowGolemEntity extends SnowGolemEntity implements Monster {
    public EvilSnowGolemEntity(EntityType<? extends SnowGolemEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createEntityAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 4.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.4D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 32).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 50);
    }

    @Override
    public void tick() {
        super.tick();

        FrostWalkerEnchantment.freezeWater(this, this.world, this.getBlockPos(), 0);
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if (!(source.getAttacker() instanceof EvilSnowGolemEntity)) {
            return super.damage(source, amount);
        } else {
            return false;
        }
    }

    @Override
    public boolean hurtByWater() {
        return false;
    }
}
