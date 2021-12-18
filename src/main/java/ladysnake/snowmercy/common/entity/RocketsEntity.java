package ladysnake.snowmercy.common.entity;

import ladysnake.snowmercy.common.entity.ai.goal.SalvoProjectileAttackGoal;
import ladysnake.snowmercy.mixin.FireworkRocketEntityInvoker;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.math.Quaternion;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3f;
import net.minecraft.world.World;

public class RocketsEntity extends WeaponizedSnowGolemEntity implements RangedAttackMob {
    public static final ItemStack FIREWORKS = new ItemStack(Items.FIREWORK_ROCKET, 1);

    public RocketsEntity(EntityType<RocketsEntity> entityType, World world) {
        super(entityType, world);

        FIREWORKS.getOrCreateSubNbt("Fireworks").putInt("Flight", 1);

        NbtList explosions = new NbtList();
        NbtCompound explosion = new NbtCompound();
        explosion.putBoolean("Flicker", false);
        explosion.putBoolean("Trail", true);
        explosion.putInt("Type", 0);
        explosion.putIntArray("Colors", new int[]{15790320});
        explosions.add(explosion);
        FIREWORKS.getOrCreateSubNbt("Fireworks").put("Explosions", explosions);

        ItemStack stackInHand = FIREWORKS.copy();
        this.equipStack(EquipmentSlot.MAINHAND, stackInHand);
        this.setEquipmentDropChance(EquipmentSlot.MAINHAND, 0.25F);
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(1, new SalvoProjectileAttackGoal(this, 1.25D, 120, 32f, 1, 3));
    }

    @Override
    public WeaponizedGolemType getGolemType() {
        return WeaponizedGolemType.ROCKETS;
    }

    public void attack(LivingEntity target, float pullProgress) {
        FireworkRocketEntity fireworkRocketEntity = new FireworkRocketEntity(world, FIREWORKS, this, this.getX(), this.getEyeY() - 0.15000000596046448D, this.getZ(), true);
        Vec3d vec3d = this.getOppositeRotationVector(1.0F);
        Quaternion quaternion = new Quaternion(new Vec3f(vec3d), 0f, true);
        Vec3d vec3d2 = this.getRotationVec(1.0F);
        Vec3f vector3f = new Vec3f(vec3d2);
        vector3f.rotate(quaternion);
        fireworkRocketEntity.setVelocity(vector3f.getX(), vector3f.getY(), vector3f.getZ(), 3f, 3f);
        world.spawnEntity(fireworkRocketEntity);
    }

    @Override
    protected void dropEquipment(DamageSource source, int lootingMultiplier, boolean allowDrops) {
        ItemStack droppedStack = this.getMainHandStack();
        droppedStack.setCount(random.nextInt(3 + lootingMultiplier));
        this.dropStack(droppedStack);
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if (this.isAlive() && (source == DamageSource.ON_FIRE || source == DamageSource.IN_FIRE)) {
            FireworkRocketEntity fireworkRocketEntity = new FireworkRocketEntity(world, FIREWORKS, this, this.getX() + random.nextGaussian() / 10f, this.getEyeY() + random.nextGaussian() / 10f, this.getZ() + random.nextGaussian() / 10f, true);
            fireworkRocketEntity.setVelocity(0, 0, 0, 0, 0);
            world.spawnEntity(fireworkRocketEntity);
            ((FireworkRocketEntityInvoker) fireworkRocketEntity).invokeExplodeAndRemove();
            this.setInvisible(true);
            this.kill();
        }

        return super.damage(source, amount);
    }
}
