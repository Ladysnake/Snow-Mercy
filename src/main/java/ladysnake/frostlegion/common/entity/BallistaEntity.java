package ladysnake.frostlegion.common.entity;

import ladysnake.frostlegion.common.entity.ai.goal.BallistaProjectileAttackGoal;
import ladysnake.frostlegion.common.entity.ai.goal.FollowAndBlowGoal;
import ladysnake.frostlegion.common.network.Packets;
import ladysnake.frostlegion.common.world.PuffExplosion;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.ZombifiedPiglinEntity;
import net.minecraft.entity.passive.SnowGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.ExplosionS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

import java.util.Iterator;

public class BallistaEntity extends EvilSnowGolemEntity {
    public BallistaEntity(EntityType<BallistaEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.targetSelector.add(1, new RevengeGoal(this, new Class[0]));
        this.goalSelector.add(2, new BallistaProjectileAttackGoal(this, 1.25D, 10.0F));
        this.targetSelector.add(2, new FollowTargetGoal(this, PlayerEntity.class, true));
        this.targetSelector.add(3, new FollowTargetGoal(this, SnowGolemEntity.class, 10, true, false, snowGolemEntity -> !(snowGolemEntity instanceof EvilSnowGolemEntity) && !(snowGolemEntity instanceof SnowblobEntity)));
        this.goalSelector.add(4, new WanderAroundFarGoal(this, 1.0D, 1.0000001E-5F));
        this.goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(5, new LookAroundGoal(this));
    }

    public void attack(LivingEntity target, float pullProgress) {
        SnowballEntity snowballEntity = new SnowballEntity(this.world, this);
        double d = target.getEyeY() - 1.100000023841858D;
        double e = target.getX() - this.getX();
        double f = d - snowballEntity.getY();
        double g = target.getZ() - this.getZ();
        float h = MathHelper.sqrt(e * e + g * g) * 0.2F;
        snowballEntity.setVelocity(e, f + (double)h, g, 1.6F, 12.0F);
        this.playSound(SoundEvents.ENTITY_SNOW_GOLEM_SHOOT, 1.0F, 0.4F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.world.spawnEntity(snowballEntity);
    }

    @Override
    public Packet<?> createSpawnPacket() {
        return Packets.newSpawnPacket(this);
    }

}
