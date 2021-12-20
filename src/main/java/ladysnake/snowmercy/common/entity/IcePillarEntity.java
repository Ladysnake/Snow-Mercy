package ladysnake.snowmercy.common.entity;

import ladysnake.snowmercy.cca.SnowMercyComponents;
import ladysnake.snowmercy.common.init.SnowMercyWaves;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

import java.util.List;

public class IcePillarEntity extends Entity {
    private static final TrackedData<Boolean> ACTIVE = DataTracker.registerData(IcePillarEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    public IcePillarEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    @Override
    protected void initDataTracker() {
        this.dataTracker.startTracking(ACTIVE, false);
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {

    }

    @Override
    public Packet<?> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }

    @Override
    public boolean hasNoGravity() {
        return true;
    }

    @Override
    public void tick() {
        super.tick();

        this.age++;

        this.setYaw(this.getYaw() + 1);

        if (!SnowMercyComponents.SNOWMERCY.get(this.world).isEventOngoing()) {
            this.setActive(false);
        }

        if (this.isActive()) {
            this.world.addParticle(ParticleTypes.SNOWFLAKE, this.getX() + random.nextGaussian() / 3f, this.getY() + random.nextFloat() * 6f, this.getZ() + random.nextGaussian() / 3f, 0, 0, 0);

            if (!world.isClient && this.age % 5 == 0) {
                int wave = SnowMercyComponents.SNOWMERCY.get(this.world).getEventWave();

                // check if there are enemies left to spawn
                SnowMercyWaves.WAVES.get(wave).removeIf(waveSpawnEntry -> waveSpawnEntry.count <= 0);
                if (!SnowMercyWaves.WAVES.get(wave).isEmpty()) {
                    int i = random.nextInt(SnowMercyWaves.WAVES.get(wave).size());

                    MobEntity ennemy = SnowMercyWaves.WAVES.get(wave).get(i).entityType.create(this.world);
                    SnowMercyWaves.WAVES.get(wave).get(i).count--;

                    ennemy.initialize((ServerWorldAccess) world, world.getLocalDifficulty(this.getBlockPos()), SpawnReason.JOCKEY, null, null);

                    BlockPos offsetPos = this.getBlockPos().add(this.getX(), this.getY(), this.getZ());
                    for (int y = -10; y < 10; y++) {
                        if (world.getBlockState(offsetPos.add(0, y, 0)).isAir()) {
                            ennemy.setPosition(offsetPos.getX(), offsetPos.getY()+y, offsetPos.getZ());
                            ennemy.setPersistent();
                            world.spawnEntity(ennemy);
                            break;
                        }
                    }
                } else {
                    // if there are no ennemies left to spawn, check if all have been defeated
                    if (world.getEntitiesByClass(MobEntity.class, this.getBoundingBox().expand(100f, 30f, 100f), entity -> entity instanceof WeaponizedSnowGolemEntity || entity instanceof HeadmasterEntity || entity instanceof PolarBearerEntity || entity instanceof TundrabidEntity || entity instanceof IceballEntity).isEmpty()) {
                        SnowMercyComponents.SNOWMERCY.get(this.world).stopEvent(world);
                        SnowMercyComponents.SNOWMERCY.get(this.world).setEventWave(wave + 1);

                        this.setInvisible(true);
                        this.playSound(SoundEvents.BLOCK_GLASS_BREAK, 1.0f, 0.5f);
                        ((ServerWorld) this.world).spawnParticles(new ItemStackParticleEffect(ParticleTypes.ITEM, new ItemStack(Items.PACKED_ICE, 1)), this.getX(), this.getY(), this.getZ(), 200, random.nextGaussian() / 3f, this.getY() + random.nextFloat() * 6f, this.getZ() + random.nextGaussian() / 3f, random.nextGaussian() / 10f);
                    }
                }
            }
        } else if (!SnowMercyComponents.SNOWMERCY.get(this.world).isEventOngoing() && !this.isInvisible()) {
            List<SnowballEntity> snowballs = this.world.getEntitiesByClass(SnowballEntity.class, this.getBoundingBox(), snowballEntity -> true);
            if (!snowballs.isEmpty()) {
                for (SnowballEntity snowball : snowballs) {
                    snowball.discard();
                }

                this.setActive(true);
                SnowMercyComponents.SNOWMERCY.get(this.world).startEvent(this.world);
            }
        }
    }

    public boolean isActive() {
        return this.dataTracker.get(ACTIVE);
    }

    public void setActive(boolean active) {
        this.dataTracker.set(ACTIVE, active);
    }

}
