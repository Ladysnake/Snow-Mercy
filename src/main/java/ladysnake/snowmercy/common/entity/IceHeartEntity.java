package ladysnake.snowmercy.common.entity;

import ladysnake.snowmercy.cca.SnowMercyComponents;
import ladysnake.snowmercy.common.init.*;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.LiteralText;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.LightType;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

import java.util.List;

import static net.minecraft.text.Style.EMPTY;

public class IceHeartEntity extends Entity {
    private static final TrackedData<Boolean> ACTIVE = DataTracker.registerData(IceHeartEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    public static final int SPAWN_RADIUS = 100;
    public static final int GIFT_SPAWN_RADIUS = 20;

    public IceHeartEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    @Override
    protected void initDataTracker() {
        this.dataTracker.startTracking(ACTIVE, false);
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
        if (nbt.contains("Active")) {
            this.setActive(nbt.getBoolean("Active"));
        }
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
        nbt.putBoolean("Active", this.isActive());
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
    public boolean doesRenderOnFire() {
        return false;
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if (!this.isActive() && !world.isClient) {
            this.playSound(SoundEvents.BLOCK_END_PORTAL_SPAWN, 10.0f, 2.0f);
            this.setActive(true);

            int wave = SnowMercyComponents.SNOWMERCY.get(world).getEventWave();

            world.getPlayers().forEach(serverPlayerEntity -> {
                MutableText waveText = new TranslatableText("info.snowmercy.wave_start", world.getRegistryKey().getValue().getPath()).append(wave+1 +": ");
                int i = 0;
                for (WaveSpawnEntry waveSpawnEntry : SnowMercyWaves.WAVES.get(wave)) {
                    if (i > 0) {
                        waveText.append(", ");
                    }
                    waveText.append(waveSpawnEntry.entityType.getName());
                    i++;
                }

                serverPlayerEntity.sendMessage(waveText.setStyle(EMPTY.withColor(Formatting.AQUA)), false);
                serverPlayerEntity.playSound(SoundEvents.BLOCK_END_PORTAL_SPAWN, SoundCategory.MASTER, 1.0f, 2.0f);
            });
        }

        return super.damage(source, amount);
    }

    @Override
    public boolean collides() {
        return true;
    }

    @Override
    public boolean isCollidable() {
        return true;
    }

    @Override
    public boolean isOnFire() {
        return false;
    }

    @Override
    public void tick() {
        super.tick();

        this.age++;

        if (world.getTime() % 65 == 0) {
            this.playSound(SnowMercySoundEvents.HEART_OF_ICE_AMBIENT, 1.0f, 1.0f);
        }

        if (this.isActive()) {
            this.world.addParticle(ParticleTypes.SNOWFLAKE, this.getX(), this.getY() + .25, this.getZ(), random.nextGaussian() / 10f, random.nextGaussian() / 10f, random.nextGaussian() / 10f);
            this.world.addParticle(ParticleTypes.END_ROD, this.getX(), this.getY() + .25, this.getZ(), random.nextGaussian() / 10f, random.nextGaussian() / 10f, random.nextGaussian() / 10f);

            if (!world.isClient && this.age % 5 == 0) {
                int wave = SnowMercyComponents.SNOWMERCY.get(this.world).getEventWave();

                // check if there are enemies left to spawn
                List<MobEntity> enemiesLeft = world.getEntitiesByClass(MobEntity.class, this.getBoundingBox().expand(100f, 30f, 100f), entity -> entity instanceof SnowMercyEnemy);
                SnowMercyWaves.WAVES.get(wave).removeIf(waveSpawnEntry -> waveSpawnEntry.count <= 0);
                if (!SnowMercyWaves.WAVES.get(wave).isEmpty() && enemiesLeft.size() < 50    ) {
                    int i = random.nextInt(SnowMercyWaves.WAVES.get(wave).size());

                    MobEntity enemy = SnowMercyWaves.WAVES.get(wave).get(i).entityType.create(this.world);

                    enemy.initialize((ServerWorldAccess) world, world.getLocalDifficulty(this.getBlockPos()), SpawnReason.MOB_SUMMONED, null, null);

                    var angle = Math.random() * Math.PI * 2;
                    float x = (float) (this.getX() + (Math.cos(angle) * SPAWN_RADIUS));
                    float z = (float) (this.getZ() + (Math.sin(angle) * SPAWN_RADIUS));

                    if (enemy instanceof IceballEntity) {
                        x = (float) (this.getX() + (Math.cos(angle) * (SPAWN_RADIUS / 5f)));
                        z = (float) (this.getZ() + (Math.sin(angle) * (SPAWN_RADIUS / 5f)));
                    }

                    BlockPos offsetPos = new BlockPos(x, this.getY(), z);
                    for (int groundOffset = -10; groundOffset < 10; groundOffset++) {
                        if ((world.getBlockState(offsetPos.add(0, groundOffset, 0)).isAir() || world.getBlockState(offsetPos.add(0, groundOffset, 0)).getBlock() == Blocks.SNOW) && world.getLightLevel(LightType.SKY, offsetPos.add(0, groundOffset, 0)) >= 15f && (world.getBlockState(offsetPos.add(0, groundOffset - 1, 0)).isSolidBlock(world, offsetPos.add(0, groundOffset - 1, 0)) || world.getBlockState(offsetPos.add(0, groundOffset - 1, 0)).getBlock() == Blocks.ICE)) {
                            enemy.setPosition(offsetPos.getX(), offsetPos.getY() + groundOffset, offsetPos.getZ());
                            enemy.setPersistent();
                            world.spawnEntity(enemy);
                            SnowMercyWaves.WAVES.get(wave).get(i).count--;
                            break;
                        }
                    }

                } else {
                    // if there are no ennemies left to spawn, check if all have been defeated
                    if (enemiesLeft.size() <= 10) {
                        for (int i = 0; i < wave + 1; i++) {
                            double r = GIFT_SPAWN_RADIUS * Math.sqrt(random.nextFloat());
                            double theta = random.nextFloat() * 2 * Math.PI;

                            GiftPackageEntity giftPackageEntity = new GiftPackageEntity(SnowMercyEntities.GIFT_PACKAGE, world);
                            giftPackageEntity.setPosition(this.getX() + r * Math.cos(theta), this.getY() + 60, this.getZ() + r * Math.sin(theta));
                            world.spawnEntity(giftPackageEntity);
                        }

                        SnowMercyComponents.SNOWMERCY.get(this.world).setEventWave(wave + 1);

                        world.getPlayers().forEach(serverPlayerEntity -> {
                            serverPlayerEntity.sendMessage(
                                    new TranslatableText("info.snowmercy.wave_cleared", world.getRegistryKey().getValue().getPath()).setStyle(EMPTY.withColor(Formatting.AQUA)), false);
                        });

//                        if (SnowMercyComponents.SNOWMERCY.get(world).getEventWave() >= 9) {
                            world.setBlockState(this.getBlockPos(), SnowMercyBlocks.FROZEN_LODESTONE.getDefaultState());
//                        }

                        this.discard();
                        this.playSound(SoundEvents.ENTITY_PLAYER_HURT_FREEZE, 1.0f, 1.2f);
                        this.playSound(SoundEvents.BLOCK_GLASS_BREAK, 1.0f, 1.2f);
                        ((ServerWorld) this.world).spawnParticles(new ItemStackParticleEffect(ParticleTypes.ITEM, new ItemStack(Items.PACKED_ICE, 1)), this.getX(), this.getY() + .25, this.getZ(), 200, 0, 0, 0, random.nextGaussian() / 5f);
                    } else {
                        for (MobEntity mobEntity : enemiesLeft) {
                            if (Math.sqrt(this.squaredDistanceTo(mobEntity)) > 80f && mobEntity.isAlive()) {
                                mobEntity.setGlowing(true);
                            } else {
                                mobEntity.setGlowing(false);
                            }
                        }
                    }
                }
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
