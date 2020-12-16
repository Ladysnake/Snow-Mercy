package ladysnake.frostlegion.common.entity;

import ladysnake.frostlegion.common.init.EntityTypes;
import net.minecraft.enchantment.FrostWalkerEnchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.passive.SnowGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ShovelItem;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class EvilSnowGolemEntity extends SnowGolemEntity implements Monster {
    private static final TrackedData<Boolean> HEADLESS = DataTracker.registerData(EvilSnowGolemEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    public EvilSnowGolemEntity(EntityType<? extends EvilSnowGolemEntity> entityType, World world) {
        super(entityType, world);
    }

    public EvilSnowGolemEntity(EntityType<? extends EvilSnowGolemEntity> entityType, World world, double x, double y, double z) {
        super(entityType, world);
        this.updatePosition(x, y, z);
    }

    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(HEADLESS, false);
    }

    public boolean isHeadless() {
        return this.dataTracker.get(HEADLESS);
    }

    public void setHeadless(boolean headless) {
        this.dataTracker.set(HEADLESS, headless);
    }

    public static DefaultAttributeContainer.Builder createEntityAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 4.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 50);
    }

    @Override
    public void tick() {
        super.tick();

        FrostWalkerEnchantment.freezeWater(this, this.world, this.getBlockPos(), 0);
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if (!(source.getAttacker() instanceof EvilSnowGolemEntity)) {
            return super.damage(source, amount);
        } else {
            return false;
        }
    }

    @Override
    public boolean hurtByWater() {
        return false;
    }

    @Override
    public void onDeath(DamageSource source) {
        if (!(this instanceof SnowGolemHeadEntity)) {
            SnowGolemHeadEntity entity = new SnowGolemHeadEntity(world, EntityTypes.GOLEM_IDS.inverse().get(this.getType()), this.getX(), this.getY() + this.getEyeHeight(this.getPose()), this.getZ());
            if (source.getAttacker() instanceof ServerPlayerEntity) {
                PlayerEntity player = ((PlayerEntity) source.getAttacker());
                if (player.getMainHandStack().getItem() instanceof ShovelItem) {
                    this.world.playSound(null, this.getBlockPos(), SoundEvents.BLOCK_ANVIL_LAND, SoundCategory.NEUTRAL, 1.0f, 0.5f);
                    entity.setProperties(player, player.pitch, player.yaw, 0.0F, 1.5F, 1.0F);
                    world.spawnEntity(entity);
                    this.setHeadless(true);
                }
            }

            world.spawnEntity(entity);
        }
    }
}
