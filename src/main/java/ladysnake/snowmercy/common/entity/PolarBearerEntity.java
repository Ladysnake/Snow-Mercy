package ladysnake.snowmercy.common.entity;

import ladysnake.snowmercy.common.entity.ai.goal.GoToHeartGoal;
import ladysnake.snowmercy.common.init.SnowMercyEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.PolarBearEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class PolarBearerEntity extends PolarBearEntity implements SnowMercyEnemy {
    public PolarBearerEntity(EntityType<? extends PolarBearEntity> entityType, World world) {
        super(entityType, world);
        this.stepHeight = 1f;

    }

    public static DefaultAttributeContainer.Builder createPolarBearerAttributes() {
        return PolarBearEntity.createPolarBearAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25f).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 64.0f);
    }

    @Override
    protected void initGoals() {
        this.targetSelector.add(1, new GoToHeartGoal(this, 1.0f, false, 20));
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new AttackGoal());
        this.goalSelector.add(1, new PolarBearEscapeDangerGoal());
        this.goalSelector.add(4, new FollowParentGoal(this, 1.25));
        this.goalSelector.add(5, new WanderAroundGoal(this, 1.0));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 6.0f));
        this.goalSelector.add(7, new LookAroundGoal(this));
        this.targetSelector.add(1, new PolarBearRevengeGoal());
        this.targetSelector.add(3, new ActiveTargetGoal<PlayerEntity>(this, PlayerEntity.class, 10, true, false, this::shouldAngerAt));
        this.targetSelector.add(5, new UniversalAngerGoal<PolarBearEntity>(this, false));
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
    public boolean isPersistent() {
        return true;
    }

    @Override
    public boolean canFreeze() {
        return false;
    }

    @Override
    protected void addPowderSnowSlowIfNeeded() {
    }


    class AttackGoal
            extends MeleeAttackGoal {
        public AttackGoal() {
            super(PolarBearerEntity.this, 1.25, true);
        }

        @Override
        protected void attack(LivingEntity target, double squaredDistance) {
            double d = this.getSquaredMaxAttackDistance(target);
            if (squaredDistance <= d && this.isCooledDown()) {
                this.resetCooldown();
                this.mob.tryAttack(target);
                PolarBearerEntity.this.setWarning(false);
            } else if (squaredDistance <= d * 2.0) {
                if (this.isCooledDown()) {
                    PolarBearerEntity.this.setWarning(false);
                    this.resetCooldown();
                }
                if (this.getCooldown() <= 10) {
                    PolarBearerEntity.this.setWarning(true);
                    PolarBearerEntity.this.playWarningSound();
                }
            } else {
                this.resetCooldown();
                PolarBearerEntity.this.setWarning(false);
            }
        }

        @Override
        public void stop() {
            PolarBearerEntity.this.setWarning(false);
            super.stop();
        }

        @Override
        protected double getSquaredMaxAttackDistance(LivingEntity entity) {
            return 4.0f + entity.getWidth();
        }
    }

    class PolarBearEscapeDangerGoal
            extends EscapeDangerGoal {
        public PolarBearEscapeDangerGoal() {
            super(PolarBearerEntity.this, 2.0);
        }

        @Override
        public boolean canStart() {
            if (!PolarBearerEntity.this.isBaby() && !PolarBearerEntity.this.isOnFire()) {
                return false;
            }
            return super.canStart();
        }
    }

    class PolarBearRevengeGoal
            extends RevengeGoal {
        public PolarBearRevengeGoal() {
            super(PolarBearerEntity.this, new Class[0]);
        }

        @Override
        public void start() {
            super.start();
            if (PolarBearerEntity.this.isBaby()) {
                this.callSameTypeForRevenge();
                this.stop();
            }
        }

        @Override
        protected void setMobEntityTarget(MobEntity mob, LivingEntity target) {
            if (mob instanceof PolarBearEntity && !mob.isBaby()) {
                super.setMobEntityTarget(mob, target);
            }
        }
    }

}
