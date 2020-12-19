package ladysnake.snowmercy.common.entity;

import ladysnake.snowmercy.common.entity.ai.goal.WeaponizedSnowGolemFollowTargetGoal;
import ladysnake.snowmercy.common.init.EntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.FrostWalkerEnchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
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
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.entity.passive.SnowGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ShovelItem;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Difficulty;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

public abstract class WeaponizedSnowGolemEntity extends GolemEntity {
    private static final TrackedData<Integer> HEAD = DataTracker.registerData(WeaponizedSnowGolemEntity.class, TrackedDataHandlerRegistry.INTEGER);

    private int ageHeadless = 0;

    public WeaponizedSnowGolemEntity(EntityType<? extends WeaponizedSnowGolemEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        // hostile (1) goals = target weaponized snowmen with a pumpkin (2) + snow golems + players
        this.targetSelector.add(1, new WeaponizedSnowGolemFollowTargetGoal(this, WeaponizedSnowGolemEntity.class, 10, true, false, 1, livingEntity -> ((WeaponizedSnowGolemEntity) livingEntity).getHead() == 2));
        this.targetSelector.add(1, new WeaponizedSnowGolemFollowTargetGoal(this, SnowGolemEntity.class, 100, true, false, 1, livingEntity -> !(livingEntity instanceof WeaponizedSnowGolemEntity)));
        this.targetSelector.add(2, new WeaponizedSnowGolemFollowTargetGoal(this, PlayerEntity.class, true, 1));
        // friendly (2) goals = target weaponized snowmen with a normal face (1) + hostile entities
        this.targetSelector.add(1, new WeaponizedSnowGolemFollowTargetGoal(this, WeaponizedSnowGolemEntity.class, 10, true, false, 2, livingEntity -> ((WeaponizedSnowGolemEntity) livingEntity).getHead() == 1));
        this.targetSelector.add(2, new WeaponizedSnowGolemFollowTargetGoal(this, MobEntity.class, 10, true, false, 2, (livingEntity) -> livingEntity instanceof Monster));
        // common goals
        this.goalSelector.add(3, new WanderAroundFarGoal(this, 1.0D, 1.0000001E-5F));
        this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(4, new LookAroundGoal(this));
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
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 4.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 48);
    }

    @Override
    public void tick() {
        super.tick();
        this.updateDespawnCounter();

        if (this.getHead() == 0) {
            if (this.ageHeadless++ > SnowGolemHeadEntity.MAX_AGE) {
                this.kill();
            }
        }

        FrostWalkerEnchantment.freezeWater(this, this.world, this.getBlockPos(), 0);
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

            for(int l = 0; l < 4; ++l) {
                i = MathHelper.floor(this.getX() + (double)((float)(l % 2 * 2 - 1) * 0.25F));
                j = MathHelper.floor(this.getY());
                k = MathHelper.floor(this.getZ() + (double)((float)(l / 2 % 2 * 2 - 1) * 0.25F));
                BlockPos blockPos = new BlockPos(i, j, k);
                if (this.world.getBlockState(blockPos).isAir() && blockState.canPlaceAt(this.world, blockPos)) {
                    this.world.setBlockState(blockPos, blockState);
                }
            }
        }
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if (!(source.getAttacker() instanceof WeaponizedSnowGolemEntity)) {
            if (this.getHead() == 1 && !(this instanceof SnowGolemHeadEntity) && source.getAttacker() instanceof ServerPlayerEntity) {
                double eyeHeight = this.getY() + this.getEyeHeight(this.getPose(), this.getDimensions(this.getPose())) - 0.3f;
                SnowGolemHeadEntity entity = new SnowGolemHeadEntity(world, EntityTypes.GOLEM_IDS.inverse().get(this.getType()), this.getX(), eyeHeight, this.getZ());
                PlayerEntity player = ((PlayerEntity) source.getAttacker());

                if (player.getMainHandStack().getItem() instanceof ShovelItem && amount >= ((ShovelItem) player.getMainHandStack().getItem()).getAttackDamage() && random.nextInt(11) <= amount) {
                    this.world.playSound(null, this.getBlockPos(), SoundEvents.BLOCK_ANVIL_LAND, SoundCategory.NEUTRAL, 1.0f, 0.5f);
                    this.world.playSound(player, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.PLAYERS, 1.0F, 1.0F);
                    entity.setProperties(player, player.pitch, player.yaw, 0.0F, amount, amount);
                    world.spawnEntity(entity);

                    player.spawnSweepAttackParticles();
                    ((ServerWorld) this.world).spawnParticles(new ItemStackParticleEffect(ParticleTypes.ITEM, new ItemStack(Items.SNOW_BLOCK, 1)), this.getX(), eyeHeight, this.getZ(), 40, random.nextGaussian() / 20f, 0.2D + random.nextGaussian() / 20f, random.nextGaussian() / 20f, 0.1f);

                    this.setHead(0);
                    return super.damage(source, 0.0f);
                }
            }
        } else if (this.getHead() == ((WeaponizedSnowGolemEntity) source.getAttacker()).getHead()) {
            return false;
        }

        return super.damage(source, amount);
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
    protected ActionResult interactMob(PlayerEntity player, Hand hand) {
        if (this.getHead() == 0 && player.getStackInHand(hand).getItem() == Blocks.CARVED_PUMPKIN.asItem()) {
            player.getStackInHand(hand).decrement(1);
            this.setHead(2);
            return ActionResult.success(this.world.isClient);
        } else {
            return ActionResult.PASS;
        }
    }

    @Override
    public boolean cannotDespawn() {
        return super.cannotDespawn() && this.getHead() == 2;
    }

    protected void updateDespawnCounter() {
        float f = this.getBrightnessAtEyes();
        if (f > 0.5F) {
            this.despawnCounter += 2;
        }

    }

    protected boolean isDisallowedInPeaceful() {
        return this.getHead() != 2;
    }

    protected boolean canDropLootAndXp() {
        return true;
    }

    protected boolean shouldDropLoot() {
        return true;
    }

    public boolean isAngryAt(PlayerEntity player) {
        return this.getHead() == 1;
    }

    public boolean canImmediatelyDespawn(double distanceSquared) {
        return this.getHead() == 1;
    }

    public void checkDespawn() {
        if (this.world.getDifficulty() == Difficulty.PEACEFUL && this.isDisallowedInPeaceful()) {
            this.remove();
        } else if (!this.isPersistent() && !this.cannotDespawn()) {
            Entity entity = this.world.getClosestPlayer(this, -1.0D);
            if (entity != null) {
                double d = entity.squaredDistanceTo((Entity)this);
                int i = this.getType().getSpawnGroup().getImmediateDespawnRange();
                int j = i * i;
                if (d > (double)j && this.canImmediatelyDespawn(d)) {
                    this.remove();
                }

                int k = this.getType().getSpawnGroup().getDespawnStartRange();
                int l = k * k;
                if (this.despawnCounter > 600 && this.random.nextInt(800) == 0 && d > (double)l && this.canImmediatelyDespawn(d)) {
                    this.remove();
                } else if (d < (double)l) {
                    this.despawnCounter = 0;
                }
            }

        } else {
            this.despawnCounter = 0;
        }
    }
    
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_SNOW_GOLEM_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_SNOW_GOLEM_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_SNOW_GOLEM_DEATH;
    }
}
