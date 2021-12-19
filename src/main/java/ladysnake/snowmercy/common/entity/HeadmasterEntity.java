package ladysnake.snowmercy.common.entity;

import ladysnake.snowmercy.common.entity.ai.goal.DeployHeadsGoal;
import ladysnake.snowmercy.common.entity.ai.goal.HeadmasterMinigunAttackGoal;
import ladysnake.snowmercy.common.entity.ai.goal.ReviveSurgeGoal;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.*;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class HeadmasterEntity extends HostileEntity implements IAnimatable {
    private static final TrackedData<Boolean> SHOOTING = DataTracker.registerData(HeadmasterEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Integer> TURRET_TRANSITION = DataTracker.registerData(HeadmasterEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Boolean> TURRET_MODE = DataTracker.registerData(HeadmasterEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private AnimationFactory factory = new AnimationFactory(this);

    public HeadmasterEntity(EntityType<? extends HeadmasterEntity> entityType, World world) {
        super(entityType, world);
        this.stepHeight = 1f;
    }

    public static DefaultAttributeContainer.Builder createHeadmasterAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_ATTACK_DAMAGE).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 64.0f).add(EntityAttributes.GENERIC_MAX_HEALTH, 100f).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2f).add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 1.0f);
    }

    @Override
    protected void initGoals() {
        // common goals
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true, entity -> this.isTurret()));
        this.goalSelector.add(2, new HeadmasterMinigunAttackGoal(this, 2.0D, 40, 8f, 20, 0));

        this.goalSelector.add(2, new DeployHeadsGoal(this));
        this.goalSelector.add(3, new ReviveSurgeGoal(this));
        this.goalSelector.add(3, new WanderAroundFarGoal(this, 1.0D, 1.0000001E-5F));
        this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(4, new LookAroundGoal(this));
    }

    protected void initDataTracker() {
        super.initDataTracker();

        this.dataTracker.startTracking(SHOOTING, false);
        this.dataTracker.startTracking(TURRET_MODE, false);
        this.dataTracker.startTracking(TURRET_TRANSITION, 100);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (this.isTurret()) {
            if (this.getTurretTransitionning() > 0) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.headmaster.transition", false));
                this.setTurretTransitionning(this.getTurretTransitionning() - 1);
            } else {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.headmaster.turret", true));
            }
        } else {
            if (isShooting()) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.headmaster.sendhead", true));
            } else {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.headmaster.standard", true));
            }
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void tick() {
        super.tick();

        // switch to turret mod at a quarter of health
        if (this.getHealth() <= this.getMaxHealth() / 3 && !this.isTurret()) {
            this.setTurret(true);
        }

        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                BlockPos pos = this.getBlockPos().add(x, 0, z);
                if (this.world.getBlockState(pos).getBlock() == Blocks.AIR && this.world.getBlockState(pos.add(0, -1, 0)).isSolidBlock(world, pos.add(0, -1, 0))) {
                    world.setBlockState(pos, Blocks.SNOW.getDefaultState());
                }
                if (this.world.getBlockState(pos).getBlock() == Blocks.WATER) {
                    world.setBlockState(pos, Blocks.FROSTED_ICE.getDefaultState());
                }
                if (this.world.getBlockState(pos).getBlock() == Blocks.POWDER_SNOW) {
                    world.breakBlock(pos, false);
                }
            }
        }
    }

    @Override
    public void tickMovement() {
        super.tickMovement();

        if (!this.world.isClient) {
            int i;
            int j;
            int k;

            if (!this.world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)) {
                return;
            }

            BlockState blockState = Blocks.SNOW.getDefaultState();

            for (int l = 0; l < 4; ++l) {
                i = MathHelper.floor(this.getX() + (double) ((float) (l % 2 * 2 - 1) * 0.25F));
                j = MathHelper.floor(this.getY());
                k = MathHelper.floor(this.getZ() + (double) ((float) (l / 2 % 2 * 2 - 1) * 0.25F));
                BlockPos blockPos = new BlockPos(i, j, k);
                if (this.world.getBlockState(blockPos).isAir() && blockState.canPlaceAt(this.world, blockPos)) {
                    this.world.setBlockState(blockPos, blockState);
                }
            }
        }
    }

    public void attack(LivingEntity target, float pullProgress) {
        FreezingWindEntity entity = new FreezingWindEntity(this.world, this);
        entity.setPos(this.getX(), this.getY()+2f, this.getZ());

        Vec3d vec3d = this.getOppositeRotationVector(1.0F);
        Quaternion quaternion = new Quaternion(new Vec3f(vec3d), 0f, true);
        Vec3d vec3d2 = this.getRotationVec(1.0F);
        Vec3f vector3f = new Vec3f(vec3d2);
        vector3f.rotate(quaternion);
        entity.setVelocity(vector3f.getX(), vector3f.getY(), vector3f.getZ(), 3f, 5f);
        world.spawnEntity(entity);
        this.world.playSound(null, this.getBlockPos(), SoundEvents.ENTITY_PLAYER_HURT_FREEZE, SoundCategory.HOSTILE, 1.0f, 1.0f);
        this.world.playSound(null, this.getBlockPos(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.HOSTILE, 1.0f, 1.5f);
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public boolean isInvulnerableTo(DamageSource damageSource) {
        return super.isInvulnerableTo(damageSource) || damageSource == DamageSource.CACTUS || damageSource == DamageSource.DROWN || damageSource == DamageSource.FREEZE || damageSource == DamageSource.FALL || damageSource == DamageSource.CRAMMING;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_IRON_GOLEM_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_IRON_GOLEM_DEATH;
    }

    public boolean isShooting() {
        return this.dataTracker.get(SHOOTING);
    }

    public void setShooting(boolean shooting) {
        this.dataTracker.set(SHOOTING, shooting);
    }

    public boolean isTurret() {
        return this.dataTracker.get(TURRET_MODE);
    }

    public void setTurret(boolean turret) {
        this.dataTracker.set(TURRET_MODE, turret);
    }

    public int getTurretTransitionning() {
        return this.dataTracker.get(TURRET_TRANSITION);
    }

    public void setTurretTransitionning(int transitionning) {
        this.dataTracker.set(TURRET_TRANSITION, transitionning);
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
