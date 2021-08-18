package ladysnake.snowmercy.common.entity;

import ladysnake.snowmercy.common.world.PuffExplosion;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.network.packet.s2c.play.ExplosionS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

import java.util.Iterator;

public class ChillSnugglesEntity extends SnugglesEntity {
    public ChillSnugglesEntity(EntityType<ChillSnugglesEntity> entityType, World world) {
        super(entityType, world);
    }

    public void explode() {
        if (!this.getEntityWorld().isClient()) {
            this.discard();

            ServerWorld world = (ServerWorld) this.getEntityWorld();

            float power = 3.0f;
            Explosion.DestructionType destructionType;
            if (this.world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)) {
                destructionType = Explosion.DestructionType.DESTROY;
            } else {
                destructionType = Explosion.DestructionType.NONE;
            }

            Explosion explosion = new PuffExplosion(world, this, DamageSource.explosion(this), null, this.getX(), this.getY(), this.getZ(), power, 3f, destructionType, false);
            explosion.collectBlocksAndDamageEntities();
            explosion.affectWorld(false);

            for (int i = 0; i < 250; i++) {
                IcicleEntity entity = new IcicleEntity(world, this);
                entity.setPos(this.getX(), this.getY(), this.getZ());
                entity.updateTrackedPosition(this.getX(), this.getY()+0.5f, this.getZ());
                entity.setVelocity(random.nextGaussian(), random.nextGaussian(), random.nextGaussian());
                world.spawnEntity(entity);
            }

            Iterator<ServerPlayerEntity> var14 = world.getPlayers().iterator();
            if (destructionType == Explosion.DestructionType.NONE) {
                explosion.clearAffectedBlocks();
            }

            while (var14.hasNext()) {
                ServerPlayerEntity serverPlayerEntity = var14.next();
                if (serverPlayerEntity.squaredDistanceTo(this.getX(), this.getY(), this.getZ()) < 4096.0D) {
                    serverPlayerEntity.networkHandler.sendPacket(new ExplosionS2CPacket(this.getX(), this.getY(), this.getZ(), power, explosion.getAffectedBlocks(), explosion.getAffectedPlayers().get(serverPlayerEntity)));
                }
            }
        }
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_SNOW_GOLEM_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.BLOCK_GLASS_BREAK;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.BLOCK_GLASS_BREAK;
    }
}
