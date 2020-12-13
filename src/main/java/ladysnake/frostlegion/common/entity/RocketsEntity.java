package ladysnake.frostlegion.common.entity;

import ladysnake.frostlegion.common.entity.ai.goal.FollowAndBlowGoal;
import ladysnake.frostlegion.common.network.Packets;
import ladysnake.frostlegion.common.world.PuffExplosion;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.SnowGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntArrayTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.ExplosionS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Quaternion;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

import java.util.Iterator;

public class RocketsEntity extends EvilSnowGolemEntity {
    public static final ItemStack FIREWORKS = new ItemStack(Items.FIREWORK_ROCKET, 1);

    public RocketsEntity(EntityType<RocketsEntity> entityType, World world) {
        super(entityType, world);

        FIREWORKS.getOrCreateSubTag("Fireworks").putInt("Flight", 127);

        ListTag explosions = new ListTag();
        CompoundTag explosion = new CompoundTag();
        explosion.putBoolean("Flicker", true);
        explosion.putBoolean("Trail", true);
        explosion.putInt("Type", 0);
        explosion.putIntArray("Colors", new int[] {15790320});
        explosions.add(explosion);
        FIREWORKS.getOrCreateSubTag("Fireworks").put("Explosions", explosions);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new ProjectileAttackGoal(this, 1.25D, 50, 30f));
        this.targetSelector.add(2, new FollowTargetGoal(this, PlayerEntity.class, true));
        this.targetSelector.add(3, new FollowTargetGoal(this, SnowGolemEntity.class, 10, true, false, snowGolemEntity -> !(snowGolemEntity instanceof EvilSnowGolemEntity) && !(snowGolemEntity instanceof SnowblobEntity)));
        this.goalSelector.add(4, new WanderAroundFarGoal(this, 1.0D, 1.0000001E-5F));
        this.goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(5, new LookAroundGoal(this));
    }

    public void attack(LivingEntity target, float pullProgress) {
        FireworkRocketEntity fireworkRocketEntity = new FireworkRocketEntity(world, FIREWORKS, this, this.getX(), this.getEyeY() - 0.15000000596046448D, this.getZ(), true);
        Vec3d vec3d = this.getOppositeRotationVector(1.0F);
        Quaternion quaternion = new Quaternion(new Vector3f(vec3d), 0f, true);
        Vec3d vec3d2 = this.getRotationVec(1.0F);
        Vector3f vector3f = new Vector3f(vec3d2);
        vector3f.rotate(quaternion);
        ((ProjectileEntity)fireworkRocketEntity).setVelocity((double)vector3f.getX(), (double)vector3f.getY(), (double)vector3f.getZ(), 3f, 0f);
        world.spawnEntity((Entity)fireworkRocketEntity);
    }

    @Override
    public Packet<?> createSpawnPacket() {
        return Packets.newSpawnPacket(this);
    }
}
