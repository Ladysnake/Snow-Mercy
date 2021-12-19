package ladysnake.snowmercy.common.entity;

import ladysnake.snowmercy.common.entity.RocketsEntity;
import ladysnake.snowmercy.common.entity.WeaponizedSnowGolemEntity;
import ladysnake.snowmercy.common.init.SnowMercyEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.PillagerEntity;
import net.minecraft.entity.passive.PolarBearEntity;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class PolarBearerEntity extends PolarBearEntity {
    public PolarBearerEntity(EntityType<? extends PolarBearEntity> entityType, World world) {
        super(entityType, world);

    }

    @Override
    public boolean isUniversallyAngry(World world) {
        return true;
    }

    @Override
    public boolean shouldAngerAt(LivingEntity entity) {
        if (!this.canTarget(entity)) {
            return false;
        }
        if (entity.getType() == EntityType.PLAYER && this.isUniversallyAngry(entity.world)) {
            return true;
        }
        return entity.getUuid().equals(this.getAngryAt());
    }

    @Override
    public boolean canTarget(LivingEntity target) {
        if (target instanceof WeaponizedSnowGolemEntity && ((WeaponizedSnowGolemEntity) target).getHead() == 1 || target instanceof PolarBearEntity) {
            return false;
        }

        return super.canTarget(target);
    }

    @Override
    public boolean canBeControlledByRider() {
        return !this.isAiDisabled() && this.getPrimaryPassenger() instanceof LivingEntity;
    }

    @Override
    @Nullable
    public Entity getPrimaryPassenger() {
        return this.getFirstPassenger();
    }

    @Override
    public void tick() {
        super.tick();

        if (!world.isClient) {
            if (this.age <= 5 && this.getPassengerList().isEmpty()) {
                RocketsEntity rider = SnowMercyEntities.ROCKETS.create(world);
                rider.initialize((ServerWorldAccess) world, world.getLocalDifficulty(this.getBlockPos()), SpawnReason.JOCKEY, null, null);
                rider.setPosition(this.getX(), this.getY(), this.getZ());
                rider.setPersistent();
                rider.startRiding(this);
                world.spawnEntity(rider);
            }
        }
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
}
