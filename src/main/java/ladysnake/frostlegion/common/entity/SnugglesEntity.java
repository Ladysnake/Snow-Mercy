package ladysnake.frostlegion.common.entity;

import ladysnake.frostlegion.common.entity.ai.goal.FollowAndBlowGoal;
import ladysnake.frostlegion.common.network.Packets;
import ladysnake.frostlegion.common.world.PuffExplosion;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.SnowGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.ExplosionS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

import java.util.Iterator;

public class SnugglesEntity extends EvilSnowGolemEntity {
    public SnugglesEntity(EntityType<SnugglesEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.targetSelector.add(1, new RevengeGoal(this));
        this.goalSelector.add(2, new FollowAndBlowGoal(this, 1.0D, false));
        this.targetSelector.add(2, new FollowTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.add(3, new FollowTargetGoal<>(this, SnowGolemEntity.class, 10, true, false, snowGolemEntity -> !(snowGolemEntity instanceof EvilSnowGolemEntity)));
        this.goalSelector.add(4, new WanderAroundFarGoal(this, 1.0D, 1.0000001E-5F));
        this.goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(5, new LookAroundGoal(this));
    }

    public void explode() {
        if (!this.getEntityWorld().isClient()) {
            this.remove();

            ServerWorld world = (ServerWorld) this.getEntityWorld();

            float power = 3.0f;
            Explosion.DestructionType destructionType;
            if (this.world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)) {
                destructionType = Explosion.DestructionType.DESTROY;
            } else {
                destructionType = Explosion.DestructionType.NONE;
            }

            Explosion explosion = new PuffExplosion(world, this, DamageSource.explosion(this), null, this.getX(), this.getY(), this.getZ(), power, 10f, destructionType);
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

    @Override
    public Packet<?> createSpawnPacket() {
        return Packets.newSpawnPacket(this);
    }

}
