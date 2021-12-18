package ladysnake.snowmercy.common.entity;

import ladysnake.snowmercy.common.init.SnowMercyEntities;
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
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class SnowGolemHeadEntity extends WeaponizedSnowGolemEntity {
    public static final int MAX_AGE = 600;

    private static final TrackedData<WeaponizedGolemType> GOLEM_TYPE = DataTracker.registerData(SnowGolemHeadEntity.class, WeaponizedGolemType.TRACKED_DATA_HANDLER);

    public SnowGolemHeadEntity(EntityType<? extends SnowGolemHeadEntity> entityType, World world) {
        super(entityType, world);
    }

    public SnowGolemHeadEntity(World world, WeaponizedGolemType golemType, double x, double y, double z) {
        super(SnowMercyEntities.SNOW_GOLEM_HEAD, world);
        this.updatePosition(x, y, z);
        this.setGolemType(golemType);
    }

    public static DefaultAttributeContainer.Builder createEntityAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 1.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.0D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 0.0D);
    }

    protected void initGoals() {
        this.goalSelector.add(1, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(2, new LookAroundGoal(this));
    }

    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(GOLEM_TYPE, WeaponizedGolemType.DEFAULT);
    }

    public WeaponizedGolemType getGolemType() {
        return this.dataTracker.get(GOLEM_TYPE);
    }

    public void setGolemType(WeaponizedGolemType golemType) {
        this.dataTracker.set(GOLEM_TYPE, golemType);
    }

    public void writeCustomDataToNbt(NbtCompound tag) {
        super.writeCustomDataToNbt(tag);

        tag.putString("golemType", this.getGolemType().getId().toString());
    }

    public void readCustomDataFromNbt(NbtCompound tag) {
        super.readCustomDataFromNbt(tag);

        if (tag.contains("golemType", NbtElement.STRING_TYPE)) {
            this.setGolemType(WeaponizedGolemType.byId(Identifier.tryParse(tag.getString("golemType"))));
        } else if (tag.contains("golemType", NbtElement.INT_TYPE)) {
            // migrate from old versions
            this.setGolemType(WeaponizedGolemType.values()[tag.getInt("golemType") % WeaponizedGolemType.values().length]);
        }
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if (!this.world.isClient()) {
            ((ServerWorld) world).spawnParticles(new ItemStackParticleEffect(ParticleTypes.ITEM, new ItemStack(Items.SNOW_BLOCK, 1)), this.getX(), this.getY() + 0.4f, this.getZ(), 40, 0f, 0f, 0f, 0.1f);
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
            WeaponizedSnowGolemEntity golem = this.getGolemType().getEntityType().create(this.world);
            BlockPos blockPos = this.getBlockPos().add(0d, -2d, 0d);
            assert golem != null;
            golem.refreshPositionAndAngles((double) blockPos.getX() + 0.5D, (double) blockPos.getY() + 0.05D, (double) blockPos.getZ() + 0.5D, 0.0F, 0.0F);
            world.spawnEntity(golem);
        }

        if (this.age >= MAX_AGE) {
            this.damage(DamageSource.GENERIC, 1.0f);
        }
    }

    @Override
    public void onPlayerCollision(PlayerEntity player) {
        super.onPlayerCollision(player);

        double speed = Math.sqrt(this.getVelocity().getX() * this.getVelocity().getX() + this.getVelocity().getY() * this.getVelocity().getY() + this.getVelocity().getZ() * this.getVelocity().getZ());
        System.out.println(speed);

        if (speed > 0.5f) {
            player.damage(DamageSource.mobProjectile(this, this), (float) speed);
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
        Vec3d vec3d = (new Vec3d(x, y, z)).normalize().add(this.random.nextGaussian() * 0.007499999832361937D * (double) divergence, this.random.nextGaussian() * 0.007499999832361937D * (double) divergence, this.random.nextGaussian() * 0.007499999832361937D * (double) divergence).multiply(speed);
        this.setVelocity(vec3d);
        float f = MathHelper.sqrt((float) (vec3d.x * vec3d.x + vec3d.z * vec3d.z));
        this.setYaw((float) (MathHelper.atan2(vec3d.x, vec3d.z) * 57.2957763671875D));
        this.setPitch((float) (MathHelper.atan2(vec3d.y, f) * 57.2957763671875D));
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
