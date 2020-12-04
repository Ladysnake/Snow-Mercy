package ladysnake.frostlegion.common.entity;

import ladysnake.frostlegion.common.network.Packets;
import ladysnake.frostlegion.common.world.PuffExplosion;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.SnowGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.ExplosionS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

import java.util.Iterator;

public class SnowMineEntity extends SnowGolemEntity {
    public SnowMineEntity(EntityType<SnowMineEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(3, new WanderAroundFarGoal(this, 1.0D, 1.0000001E-5F));
        this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(4, new LookAroundGoal(this));
        this.goalSelector.add(2, new MeleeAttackGoal(this, 1.0D, false));
        this.targetSelector.add(1, new FollowTargetGoal(this, PlayerEntity.class, 10, true, false, (livingEntity) -> {
            return livingEntity instanceof PlayerEntity;
        }));
    }

    public static DefaultAttributeContainer.Builder createSnowGolemAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 4.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 32);
    }

    @Override
    public Packet<?> createSpawnPacket() {
        return Packets.newSpawnPacket(this);
    }

    @Override
    public void onPlayerCollision(PlayerEntity player) {
        explode();
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        boolean ret = super.damage(source, amount);
        if (!this.isDead()) {
            explode();
        }
        return ret;
    }

    public void explode() {
        if (!this.getEntityWorld().isClient()) {
            this.remove();

            ServerWorld world = (ServerWorld) this.getEntityWorld();

            float power = 3.0f;
            Explosion.DestructionType destructionType = Explosion.DestructionType.DESTROY;

            Explosion explosion = new PuffExplosion(world, this, DamageSource.explosion(this), null, this.getX(), this.getY(), this.getZ(), power, destructionType);
            explosion.collectBlocksAndDamageEntities();
            explosion.affectWorld(false);

            Iterator var14 = world.getPlayers().iterator();
            if (destructionType == Explosion.DestructionType.NONE) {
                explosion.clearAffectedBlocks();
            }

            while (var14.hasNext()) {
                ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity) var14.next();
                if (serverPlayerEntity.squaredDistanceTo(this.getX(), this.getY(), this.getZ()) < 4096.0D) {
                    serverPlayerEntity.networkHandler.sendPacket(new ExplosionS2CPacket(this.getX(), this.getY(), this.getZ(), power, explosion.getAffectedBlocks(), (Vec3d) explosion.getAffectedPlayers().get(serverPlayerEntity)));
                }
            }
        }
    }
}
