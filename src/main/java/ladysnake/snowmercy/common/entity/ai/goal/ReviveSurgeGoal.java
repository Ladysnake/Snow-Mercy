package ladysnake.snowmercy.common.entity.ai.goal;

import ladysnake.snowmercy.common.entity.HeadmasterEntity;
import ladysnake.snowmercy.common.entity.SnowGolemHeadEntity;
import ladysnake.snowmercy.common.entity.WeaponizedSnowGolemEntity;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

public class ReviveSurgeGoal extends Goal {
    protected HeadmasterEntity headmaster;
    protected int ticksUntilSurge;
    protected Random random;

    public ReviveSurgeGoal(HeadmasterEntity headmasterEntity) {
        this.headmaster = headmasterEntity;
    }

    @Override
    public boolean canStart() {
        // if snowmen heads around
        return !headmaster.world.getEntitiesByClass(SnowGolemHeadEntity.class, this.headmaster.getBoundingBox().expand(16f), snowGolemHeadEntity -> snowGolemHeadEntity.isAlive() && snowGolemHeadEntity.isOnGround()).isEmpty();
    }

    @Override
    public void start() {
        super.start();

        random = new Random();
        this.ticksUntilSurge = 40;
    }

    @Override
    public boolean shouldContinue() {
        return super.shouldContinue();
    }

    @Override
    public void tick() {
        super.tick();

        if (ticksUntilSurge == 40) {
            headmaster.playSound(SoundEvents.BLOCK_RESPAWN_ANCHOR_CHARGE, 5.0f, 0.5f);
        } else if (ticksUntilSurge > 0) {
            ((ServerWorld) headmaster.world).spawnParticles(ParticleTypes.SOUL, headmaster.getX(), headmaster.getY() + .25f, headmaster.getZ(), 50, random.nextGaussian() * 16f, 0, random.nextGaussian() * 16f, 0f);
        } else {
            headmaster.playSound(SoundEvents.ITEM_TOTEM_USE, 5.0f, 1.5f);
            headmaster.playSound(SoundEvents.ENTITY_PLAYER_HURT_FREEZE, 5.0f, 1.0f);

            if (!headmaster.world.isClient) {
                for (SnowGolemHeadEntity snowmanHead : headmaster.world.getEntitiesByClass(SnowGolemHeadEntity.class, this.headmaster.getBoundingBox().expand(16f), LivingEntity::isAlive)) {
                    ((ServerWorld) headmaster.world).spawnParticles(new ItemStackParticleEffect(ParticleTypes.ITEM, new ItemStack(Blocks.SNOW_BLOCK)), snowmanHead.getX(), snowmanHead.getY(), snowmanHead.getZ(), 100, random.nextGaussian() / 3f, random.nextGaussian() + 1 / 3f, random.nextGaussian() / 3f, 0.2f);
                    ((ServerWorld) headmaster.world).spawnParticles(ParticleTypes.TOTEM_OF_UNDYING, snowmanHead.getX(), snowmanHead.getY(), snowmanHead.getZ(), 50, random.nextGaussian() / 3f, random.nextGaussian() + 1 / 3f, random.nextGaussian() / 3f, 0.2f);
                    snowmanHead.damage(DamageSource.GENERIC, 1.0f);
                    WeaponizedSnowGolemEntity golem = snowmanHead.getGolemType().getEntityType().create(headmaster.world);
                    BlockPos blockPos = snowmanHead.getBlockPos().add(0d, 0d, 0d);
                    assert golem != null;
                    golem.refreshPositionAndAngles((double) blockPos.getX() + 0.5D, (double) blockPos.getY() + 0.05D, (double) blockPos.getZ() + 0.5D, 0.0F, 0.0F);
                    headmaster.world.spawnEntity(golem);
                }

                for (LivingEntity livingEntity : headmaster.world.getEntitiesByClass(LivingEntity.class, this.headmaster.getBoundingBox().expand(16f), LivingEntity::canFreeze)) {
                    livingEntity.setFrozenTicks(1000);
                }
            }

            this.stop();
        }

        ticksUntilSurge--;
    }
}
