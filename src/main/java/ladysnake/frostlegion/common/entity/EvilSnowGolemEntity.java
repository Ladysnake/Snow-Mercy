package ladysnake.frostlegion.common.entity;

import ladysnake.frostlegion.common.init.EntityTypes;
import net.minecraft.enchantment.FrostWalkerEnchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.FollowTargetGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.passive.SnowGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ShovelItem;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public abstract class EvilSnowGolemEntity extends SnowGolemEntity implements Monster {
    private static final TrackedData<Integer> HEAD = DataTracker.registerData(EvilSnowGolemEntity.class, TrackedDataHandlerRegistry.INTEGER);

    public EvilSnowGolemEntity(EntityType<? extends EvilSnowGolemEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.targetSelector.add(2, new FollowTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.add(3, new FollowTargetGoal<>(this, SnowGolemEntity.class, 10, true, false, snowGolemEntity -> !(snowGolemEntity instanceof EvilSnowGolemEntity)));
        this.goalSelector.add(4, new WanderAroundFarGoal(this, 1.0D, 1.0000001E-5F));
        this.goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(5, new LookAroundGoal(this));
    }

    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(HEAD, 1);
    }

    // HEAD STATUS
    // 0: Headless
    // 1: Hostile (default)
    // 2: Helps player
    public int getHead() {
        return this.dataTracker.get(HEAD);
    }

    public void setHead(int head) {
        this.dataTracker.set(HEAD, head);
    }

    public static DefaultAttributeContainer.Builder createEntityAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 4.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 50);
    }

    @Override
    public void tick() {
        super.tick();

        FrostWalkerEnchantment.freezeWater(this, this.world, this.getBlockPos(), 0);
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if (!(source.getAttacker() instanceof EvilSnowGolemEntity)) {
            if (this.getHead() == 1 && !(this instanceof SnowGolemHeadEntity) && source.getAttacker() instanceof ServerPlayerEntity) {
                SnowGolemHeadEntity entity = new SnowGolemHeadEntity(world, EntityTypes.GOLEM_IDS.inverse().get(this.getType()), this.getX(), this.getY() + this.getEyeHeight(this.getPose()), this.getZ());
                PlayerEntity player = ((PlayerEntity) source.getAttacker());
                if (player.getMainHandStack().getItem() instanceof ShovelItem) {
                    this.world.playSound(null, this.getBlockPos(), SoundEvents.BLOCK_ANVIL_LAND, SoundCategory.NEUTRAL, 1.0f, 0.5f);
                    this.world.playSound(player, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.PLAYERS, 1.0F, 1.0F);
                    entity.setProperties(player, player.pitch, player.yaw, 0.0F, amount, amount);
                    world.spawnEntity(entity);

                    double d = (double)(-MathHelper.sin(player.yaw * 0.017453292F));
                    double e = (double)MathHelper.cos(player.yaw * 0.017453292F);
                    ((ServerWorld)this.world).spawnParticles(ParticleTypes.SWEEP_ATTACK, player.getX() + d, player.getBodyY(0.5D), player.getZ() + e, 0, d, 0.0D, e, 0.0D);
                    player.spawnSweepAttackParticles();

                    this.setHead(0);
                }
            }

            return super.damage(source, amount);
        } else {
            return false;
        }
    }

    @Override
    public boolean hurtByWater() {
        return false;
    }

    @Override
    public void onDeath(DamageSource source) {
        super.onDeath(source);
    }

    @Override
    public boolean hasPumpkin() {
        return this.getHead() == 2;
    }
}
