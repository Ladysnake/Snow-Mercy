package ladysnake.snowmercy.common.entity;

import ladysnake.snowmercy.common.init.EntityTypes;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Objects;

public class SnowGolemHeadEntity extends WeaponizedSnowGolemEntity {
    public static final int MAX_AGE = 600;

    private static final TrackedData<Integer> GOLEM_TYPE = DataTracker.registerData(SnowGolemHeadEntity.class, TrackedDataHandlerRegistry.INTEGER);

    public SnowGolemHeadEntity(EntityType<? extends SnowGolemHeadEntity> entityType, World world) {
        super(entityType, world);
    }

    protected void initGoals() {
        this.goalSelector.add(1, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(2, new LookAroundGoal(this));
    }

    public SnowGolemHeadEntity(World world, int golemType, double x, double y, double z) {
        super(EntityTypes.SNOW_GOLEM_HEAD, world);
        this.updatePosition(x, y, z);
        this.setGolemType(golemType);
    }

    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(GOLEM_TYPE, 0);
    }

    public int getGolemType() {
        return this.dataTracker.get(GOLEM_TYPE);
    }

    public void setGolemType(int golemType) {
        this.dataTracker.set(GOLEM_TYPE, golemType);
    }

    public static DefaultAttributeContainer.Builder createEntityAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 1.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.0D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 0.0D);
    }

    public void writeCustomDataToNbt(NbtCompound tag) {
        super.writeCustomDataToNbt(tag);

        tag.putInt("golemType", this.getGolemType());
    }

    public void readCustomDataFromNbt(NbtCompound tag) {
        super.readCustomDataFromNbt(tag);

        this.setGolemType(tag.getInt("golemType"));
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if (!this.world.isClient()) {
            ((ServerWorld) world).spawnParticles(new ItemStackParticleEffect(ParticleTypes.ITEM, new ItemStack(Items.SNOW_BLOCK, 1)), this.getX(), this.getY()+0.4f, this.getZ(), 40, 0f, 0f, 0f, 0.1f);
        }
        this.world.playSound(null, this.getBlockPos(), SoundEvents.ENTITY_SNOW_GOLEM_DEATH, SoundCategory.NEUTRAL, 1.0f, 1.0f);
        this.discard();

        return true;
    }

    @Override
    public void tick() {
        super.tick();

        if (this.world.getBlockState(this.getBlockPos().add(0d, -1d, 0d)).getBlock() == Blocks.SNOW_BLOCK
        && this.world.getBlockState(this.getBlockPos().add(0d, -2d, 0d)).getBlock() == Blocks.SNOW_BLOCK) {
            this.damage(DamageSource.GENERIC, 1.0f);
            this.world.breakBlock(this.getBlockPos().add(0d, -1d, 0d), false);
            this.world.breakBlock(this.getBlockPos().add(0d, -2d, 0d), false);
            WeaponizedSnowGolemEntity golemEntity = Objects.requireNonNull(EntityTypes.GOLEM_IDS.get(this.getGolemType())).create(this.world);
            BlockPos blockPos = this.getBlockPos().add(0d, -2d, 0d);
            assert golemEntity != null;
            golemEntity.refreshPositionAndAngles((double)blockPos.getX() + 0.5D, (double)blockPos.getY() + 0.05D, (double)blockPos.getZ() + 0.5D, 0.0F, 0.0F);
            world.spawnEntity(golemEntity);
        }

        if (this.age >= MAX_AGE) {
            this.damage(DamageSource.GENERIC, 1.0f);
        }
    }

    public void setProperties(Entity user, float pitch, float yaw, float roll, float modifierZ, float modifierXYZ) {
        float f = -MathHelper.sin(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
        float g = -MathHelper.sin((pitch + roll) * 0.017453292F);
        float h = MathHelper.cos(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
        this.setVelocity(f, g, h, modifierZ, modifierXYZ);
        Vec3d vec3d = user.getVelocity();
        this.setVelocity(this.getVelocity().add(vec3d.x, user.isOnGround() ? 0.0D : vec3d.y, vec3d.z));
    }

    public void setVelocity(double x, double y, double z, float speed, float divergence) {
        Vec3d vec3d = (new Vec3d(x, y, z)).normalize().add(this.random.nextGaussian() * 0.007499999832361937D * (double)divergence, this.random.nextGaussian() * 0.007499999832361937D * (double)divergence, this.random.nextGaussian() * 0.007499999832361937D * (double)divergence).multiply(speed);
        this.setVelocity(vec3d);
        float f = MathHelper.sqrt((float) (vec3d.x * vec3d.x + vec3d.z * vec3d.z));
        this.setYaw((float)(MathHelper.atan2(vec3d.x, vec3d.z) * 57.2957763671875D));
        this.setPitch((float)(MathHelper.atan2(vec3d.y, f) * 57.2957763671875D));
        this.prevYaw = this.getYaw();
        this.prevPitch = this.getPitch();
    }

    @Override
    public boolean hurtByWater() {
        return true;
    }

    @Override
    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return 0.5f;
    }

    @Override
    public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
        return false;
    }
}
