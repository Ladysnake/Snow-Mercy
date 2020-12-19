package ladysnake.snowmercy.common.entity;

import ladysnake.snowmercy.common.network.Packets;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.goal.ProjectileAttackGoal;
import net.minecraft.network.Packet;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class MortarsEntity extends WeaponizedSnowGolemEntity implements RangedAttackMob {
    public MortarsEntity(EntityType<MortarsEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(1, new ProjectileAttackGoal(this, 1.25D, 80, 50f));
    }

    public void attack(LivingEntity target, float pullProgress) {
        world.playSound(null, this.getBlockPos(), SoundEvents.ENTITY_FIREWORK_ROCKET_BLAST, SoundCategory.HOSTILE, 1.0f, 0.6f);
        for (int i = 0; i < 20; i++) {
            IcicleEntity entity = new IcicleEntity(world, this);
            entity.setPos(this.getX(), this.getY(), this.getZ());
            entity.updateTrackedPosition(this.getX(), this.getY()+0.5f, this.getZ());
            entity.setVelocity((target.getX()-this.getX())/50f+random.nextGaussian()/10f, 2f+random.nextGaussian()/10f, (target.getZ()-this.getZ())/50f+random.nextGaussian()/10f);
            world.spawnEntity(entity);
        }
    }

    @Override
    public Packet<?> createSpawnPacket() {
        return Packets.newSpawnPacket(this);
    }
}
