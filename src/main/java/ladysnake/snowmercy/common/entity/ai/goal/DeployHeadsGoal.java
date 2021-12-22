package ladysnake.snowmercy.common.entity.ai.goal;

import ladysnake.snowmercy.common.entity.HeadmasterEntity;
import ladysnake.snowmercy.common.entity.SnowGolemHeadEntity;
import ladysnake.snowmercy.common.entity.WeaponizedGolemType;
import ladysnake.snowmercy.common.entity.WeaponizedSnowGolemEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;

import java.util.Random;

public class DeployHeadsGoal extends Goal {
    protected HeadmasterEntity headmaster;
    protected int amount;
    protected Random random;
    protected int timer;

    public DeployHeadsGoal(HeadmasterEntity headmasterEntity) {
        this.headmaster = headmasterEntity;
    }

    @Override
    public boolean canStart() {
        // if not enough hostile snowmen around (<=10)
        return headmaster.world.getEntitiesByClass(WeaponizedSnowGolemEntity.class, this.headmaster.getBoundingBox().expand(20f), weaponizedSnowGolemEntity -> weaponizedSnowGolemEntity.isAlive() && weaponizedSnowGolemEntity.getHead() == 1).size() <= 10;
    }

    @Override
    public void start() {
        super.start();
        headmaster.setShooting(true);

        random = new Random();
        this.amount = 5 + random.nextInt(11); // between 5 and 15 heads
    }

    @Override
    public void stop() {
        super.stop();
        headmaster.setShooting(false);

    }

    @Override
    public boolean shouldContinue() {
        return super.shouldContinue() && this.amount > 0;
    }

    @Override
    public void tick() {
        super.tick();

        if (!headmaster.world.isClient) {
            if (timer++ % 10 == 0) {
                for (int i = 0; i < 1 + random.nextInt(3); i++) {
                    int chosenHead = random.nextInt(72);
                    WeaponizedGolemType golemType = WeaponizedGolemType.SAWMAN;

                    if (chosenHead >= 25 && chosenHead < 45) {
                        golemType = WeaponizedGolemType.MORTARS;
                    } else if (chosenHead >= 45 && chosenHead < 65) {
                        golemType = WeaponizedGolemType.ROCKETS;
                    } else if (chosenHead >= 65 && chosenHead < 70) {
                        golemType = WeaponizedGolemType.SNUGGLES;
                    } else if (chosenHead == 70) {
                        golemType = WeaponizedGolemType.CHILL_SNUGGLES;
                    } else if (chosenHead == 71) {
                        golemType = WeaponizedGolemType.BOOMBOX;
                    }

                    SnowGolemHeadEntity entity = new SnowGolemHeadEntity(headmaster.world, golemType, headmaster.getX(), headmaster.getY() + 3, headmaster.getZ());
                    entity.setPos(headmaster.getX(), headmaster.getY() + 3, headmaster.getZ());
                    entity.updateTrackedPosition(headmaster.getX(), headmaster.getY() + 3, headmaster.getZ());
                    entity.setVelocity(random.nextGaussian() / 2f, 3f + random.nextGaussian() / 10f, random.nextGaussian() / 2f);
                    headmaster.world.spawnEntity(entity);
                    this.headmaster.world.playSound(null, this.headmaster.getBlockPos(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.HOSTILE, 5.0f, 1.5f);
                    ((ServerWorld) headmaster.world).spawnParticles(ParticleTypes.CLOUD, headmaster.getX(), headmaster.getY() + 4, headmaster.getZ(), 50, random.nextGaussian() / 10f, random.nextGaussian() / 10f, random.nextGaussian() / 10f, 0.1f);

                    amount--;
                }
            }
        }
    }
}
