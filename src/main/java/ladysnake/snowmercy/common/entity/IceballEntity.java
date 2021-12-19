package ladysnake.snowmercy.common.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class IceballEntity extends SlimeEntity {
    public static final ItemStackParticleEffect PARTICLE = new ItemStackParticleEffect(ParticleTypes.ITEM, new ItemStack(Items.ICE));

    public IceballEntity(EntityType<? extends SlimeEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
        if (fallDistance >= 5f) {
            for (int i = 0; i < this.getSize() * this.getSize() * 10; i++) {
                IcicleEntity entity = new IcicleEntity(world, this);
                entity.setPos(this.getX(), this.getEyeY(), this.getZ());
                entity.updateTrackedPosition(this.getX(), this.getY() + 0.5f, this.getZ());
                entity.setVelocity(random.nextGaussian(), random.nextGaussian(), random.nextGaussian());
                world.spawnEntity(entity);
            }

            this.discard();
        }
        return false;
    }

    @Override
    public void tick() {
        super.tick();
        int i = this.getSize();

        if (this.onGround && this.getTarget() != null && Math.sqrt(this.getTarget().getBlockPos().getSquaredDistance(this.getBlockPos())) <= i * 5f) {
            for (int j = 0; j < i * 8; ++j) {
                float f = this.random.nextFloat() * ((float)Math.PI * 2);
                float g = this.random.nextFloat() * 0.5f + 0.5f;
                float h = MathHelper.sin(f) * (float)i * 0.5f * g;
                float k = MathHelper.cos(f) * (float)i * 0.5f * g;
                this.world.addParticle(this.getParticles(), this.getX() + (double)h, this.getY(), this.getZ() + (double)k, 0.0, 0.0, 0.0);
            }
            this.playSound(this.getSquishSound(), this.getSoundVolume(), ((this.random.nextFloat() - this.random.nextFloat()) * 0.2f + 1.0f) / 0.8f);
            this.targetStretch = -0.5f;

            this.setVelocity(getVelocity().getX(), Math.max(1.0f, this.getSize() * 0.5f), getVelocity().getZ());
        }
    }

    @Override
    protected ParticleEffect getParticles() {
        return PARTICLE;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.BLOCK_GLASS_BREAK;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_PLAYER_HURT_FREEZE;
    }

    @Override
    protected SoundEvent getJumpSound() {
        return SoundEvents.BLOCK_SNOW_BREAK;
    }

    @Override
    protected SoundEvent getSquishSound() {
        return SoundEvents.BLOCK_GLASS_BREAK;
    }


    @Override
    public boolean canFreeze() {
        return false;
    }

    @Override
    public boolean isFreezing() {
        return false;
    }

    @Override
    protected void addPowderSnowSlowIfNeeded() {
    }

    @Override
    public void remove(RemovalReason reason) {
        this.setRemoved(reason);
        if (reason == RemovalReason.KILLED) {
            this.emitGameEvent(GameEvent.ENTITY_KILLED);
        }
    }

}
