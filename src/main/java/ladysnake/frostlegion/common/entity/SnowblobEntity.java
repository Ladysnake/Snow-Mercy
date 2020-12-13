package ladysnake.frostlegion.common.entity;

import ladysnake.frostlegion.common.entity.ai.goal.MergeGoal;
import ladysnake.frostlegion.common.init.EntityTypes;
import ladysnake.frostlegion.common.network.Packets;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.FollowTargetGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.SnowGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.Packet;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class SnowblobEntity extends SnowGolemEntity implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);

    public SnowblobEntity(EntityType<SnowblobEntity> entityType, World world) {
        super(entityType, world);
        this.ignoreCameraFrustum = true;
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(3, new WanderAroundFarGoal(this, 1.0D, 1.0000001E-5F));
        this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(4, new LookAroundGoal(this));
        this.goalSelector.add(2, new MergeGoal(this, 1.0D, false));
        this.targetSelector.add(1, new FollowTargetGoal(this, EvilSnowGolemEntity.class, 10, true, false, (livingEntity) -> {
            return livingEntity instanceof EvilSnowGolemEntity;
        }));
    }

    public static DefaultAttributeContainer.Builder createEntityAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 1.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 0).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 32);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.world.getBlockState(this.getBlockPos().add(0, -0.5, 0)).getBlock() == Blocks.SNOW_BLOCK
        && this.world.getBlockState(this.getBlockPos().add(0, -1.5, 0)).getBlock() == Blocks.SNOW_BLOCK) {
            this.remove();
            this.world.breakBlock(this.getBlockPos().add(0, -0.5, 0), false);
            this.world.breakBlock(this.getBlockPos().add(0, -1.5, 0), false);

            EvilSnowGolemEntity snowGolemEntity = EntityTypes.SNOWGGLER.create(world);
            BlockPos blockPos = this.getBlockPos().add(0, -2, 0);
            snowGolemEntity.refreshPositionAndAngles((double)blockPos.getX() + 0.5D, (double)blockPos.getY() + 0.05D, (double)blockPos.getZ() + 0.5D, 0.0F, 0.0F);
            this.world.spawnEntity(snowGolemEntity);
        }
    }

    @Override
    public Packet<?> createSpawnPacket() {
        return Packets.newSpawnPacket(this);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (this.prevX != this.getX() || this.prevZ != this.getZ()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.snowblob.roll", true));
            return PlayState.CONTINUE;
        } else {
            return PlayState.STOP;
        }
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}
