package ladysnake.frostlegion.common.entity;

import ladysnake.frostlegion.common.init.EntityTypes;
import ladysnake.frostlegion.common.network.Packets;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.Packet;
import net.minecraft.world.World;

public class SnowGolemHeadEntity extends EvilSnowGolemEntity {
    private static final TrackedData<Integer> GOLEM_TYPE = DataTracker.registerData(SnowGolemHeadEntity.class, TrackedDataHandlerRegistry.INTEGER);

    public SnowGolemHeadEntity(EntityType<? extends SnowGolemHeadEntity> entityType, World world) {
        super(entityType, world);
    }

    protected void initGoals() {
        this.goalSelector.add(1, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(2, new LookAroundGoal(this));
    }

    public SnowGolemHeadEntity(World world, int golemType, double x, double y, double z) {
        super(EntityTypes.SNOW_GOLEM_HEAD, world);
        this.updatePosition(x, y, z);
        this.setGolemType(golemType);
    }

    public SnowGolemHeadEntity(World world, int golemType) {
        super(EntityTypes.SNOW_GOLEM_HEAD, world);
        this.setGolemType(golemType);
    }

    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(GOLEM_TYPE, 0);
    }

    public int getGolemType() {
        return this.dataTracker.get(GOLEM_TYPE);
    }

    public void setGolemType(int golemType) {
        this.dataTracker.set(GOLEM_TYPE, golemType);
    }

    public static DefaultAttributeContainer.Builder createEntityAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 1.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.0D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 0.0D);
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public Packet<?> createSpawnPacket() {
        return Packets.newSpawnPacket(this);
    }
}
