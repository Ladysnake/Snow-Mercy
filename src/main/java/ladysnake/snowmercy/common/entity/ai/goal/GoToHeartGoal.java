package ladysnake.snowmercy.common.entity.ai.goal;

import ladysnake.snowmercy.common.entity.IceHeartEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;

import java.util.EnumSet;
import java.util.List;

public class GoToHeartGoal extends Goal {
    private final double speed;
    private final boolean pauseWhenMobIdle;
    protected MobEntity mob;
    private Path path;
    private double targetX;
    private double targetY;
    private double targetZ;
    private int updateCountdownTicks;
    private int field_24667;
    private long lastUpdateTime;
    private final int radius;
    private IceHeartEntity targetHeart;

    public GoToHeartGoal(MobEntity mob, double speed, boolean pauseWhenMobIdle, int radius) {
        this.mob = mob;
        this.speed = speed;
        this.pauseWhenMobIdle = pauseWhenMobIdle;
        this.setControls(EnumSet.of(Control.MOVE, Control.LOOK));
        this.radius = radius;
    }

    public boolean canStart() {
        if (!(this.mob.getTarget() instanceof PlayerEntity)) {
            long l = this.mob.world.getTime();
            if (l - this.lastUpdateTime < 20L) {
                return false;
            } else {
                this.lastUpdateTime = l;
                List<IceHeartEntity> activeHearts = this.mob.world.getEntitiesByClass(IceHeartEntity.class, this.mob.getBoundingBox().expand(150f, 50f, 150f), IceHeartEntity::isActive);
                if (!activeHearts.isEmpty()) {
                    IceHeartEntity heartEntity = activeHearts.get(0);

                    if (!heartEntity.isAlive()) {
                        return false;
                    } else {
                        this.path = this.mob.getNavigation().findPathTo(heartEntity, 0);
                        if (this.path != null) {
                            return true;
                        } else {
                            return this.mob.squaredDistanceTo(heartEntity.getX(), heartEntity.getY(), heartEntity.getZ()) >= radius;
                        }
                    }
                } else {
                    return false;
                }
            }
        } else return false;
    }

    public boolean shouldContinue() {
        return !(this.mob.getTarget() instanceof PlayerEntity);
    }

    public void start() {
        this.mob.getNavigation().startMovingAlong(this.path, this.speed);
        this.updateCountdownTicks = 0;
        this.field_24667 = 0;
        List<IceHeartEntity> activeHearts = this.mob.world.getEntitiesByClass(IceHeartEntity.class, this.mob.getBoundingBox().expand(100f, 50f, 100f), IceHeartEntity::isActive);
        if (!activeHearts.isEmpty()) {
            this.targetHeart = activeHearts.get(0);
        } else {
            this.stop();
        }
    }

    public void stop() {
        this.mob.getNavigation().stop();
    }

    public void tick() {
        if (this.targetHeart != null && targetHeart.isAlive() && targetHeart.isActive()) {
            if (Math.sqrt(this.mob.getBlockPos().getSquaredDistance(targetHeart.getBlockPos())) <= radius) {
                this.stop();
            }

            this.mob.getLookControl().lookAt(targetHeart, 30.0F, 30.0F);

            assert targetHeart != null;
            double d = this.mob.squaredDistanceTo(targetHeart.getX(), targetHeart.getY(), targetHeart.getZ());
            this.updateCountdownTicks = Math.max(this.updateCountdownTicks - 1, 0);
            if (this.updateCountdownTicks <= 0 && (this.targetX == 0.0D && this.targetY == 0.0D && this.targetZ == 0.0D || targetHeart.squaredDistanceTo(this.targetX, this.targetY, this.targetZ) >= 1.0D || this.mob.getRandom().nextFloat() < 0.05F)) {
                this.targetX = targetHeart.getX();
                this.targetY = targetHeart.getY();
                this.targetZ = targetHeart.getZ();
                this.updateCountdownTicks = 4 + this.mob.getRandom().nextInt(7);
                if (d > 1024.0D) {
                    this.updateCountdownTicks += 10;
                } else if (d > 256.0D) {
                    this.updateCountdownTicks += 5;
                }

                if (!this.mob.getNavigation().startMovingTo(targetHeart, this.speed)) {
                    this.updateCountdownTicks += 15;
                }
            }

            this.field_24667 = Math.max(this.field_24667 - 1, 0);
        } else {
            this.stop();
        }
    }
}
