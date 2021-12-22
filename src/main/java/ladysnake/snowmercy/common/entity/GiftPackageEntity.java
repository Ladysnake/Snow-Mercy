package ladysnake.snowmercy.common.entity;

import ladysnake.snowmercy.common.init.SnowMercyGifts;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.Random;

public class GiftPackageEntity extends Entity {
    private static final TrackedData<Boolean> PARACHUTE = DataTracker.registerData(GiftPackageEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    public GiftPackageEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    @Override
    protected void initDataTracker() {
        this.dataTracker.startTracking(PARACHUTE, true);
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
        if (nbt.contains("Parachute")) {
            this.setParachute(nbt.getBoolean("Parachute"));
        }
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
        nbt.putBoolean("Parachute", this.hasParachute());
    }

    @Override
    public boolean collides() {
        return true;
    }

    @Override
    public boolean isCollidable() {
        return true;
    }

    public boolean hasParachute() {
        return this.dataTracker.get(PARACHUTE);
    }

    public void setParachute(boolean parachute) {
        this.dataTracker.set(PARACHUTE, parachute);
    }

    @Override
    public Packet<?> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.getYaw() == 0) {
            this.setYaw(new Random().nextFloat()*360f);
        }

        if (world.isClient && !this.hasParachute()) {
            world.addParticle(ParticleTypes.END_ROD, this.getX()+random.nextGaussian()/5f, this.getY()+random.nextFloat()/1.5f, this.getZ()+random.nextGaussian()/5f, 0, 0, 0);
        }

        if (!world.isClient) {
            if (this.isOnGround()) {
                this.setParachute(false);
            }

            if (this.hasParachute() && !isOnGround()) {
                this.setVelocity(0, -0.2, 0);
            }
            if (!this.hasParachute() && !isOnGround()) {
                this.setVelocity(0, this.getVelocity().getY()-0.05, 0);
            }
        }

        this.move(MovementType.SELF, this.getVelocity());
    }

    @Override
    public boolean isInvulnerable() {
        return true;
    }

    @Override
    public ActionResult interact(PlayerEntity player, Hand hand) {
        this.playSound(SoundEvents.ENTITY_FIREWORK_ROCKET_BLAST, 1.0f, 1.5f);
        for (int i = 0; i < 50; i++) {
            world.addParticle(ParticleTypes.END_ROD, this.getX(), this.getY()+.25, this.getZ(), random.nextGaussian(), random.nextFloat(), random.nextGaussian());
            world.addParticle(ParticleTypes.FIREWORK, this.getX(), this.getY()+.25, this.getZ(), random.nextGaussian(), random.nextFloat(), random.nextGaussian());
        }

        for (int i = 0; i < 3; i++) {
            ItemEntity giftItem = new ItemEntity(world, this.getX(), this.getY(), this.getZ(), SnowMercyGifts.getRandomGift(), random.nextGaussian()/10f, 0.3f, random.nextGaussian()/10f);
            world.spawnEntity(giftItem);
        }

        this.discard();

        return ActionResult.SUCCESS;
    }
}
