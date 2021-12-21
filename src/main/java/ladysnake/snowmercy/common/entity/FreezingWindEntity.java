package ladysnake.snowmercy.common.entity;

import ladysnake.snowmercy.common.init.SnowMercyEntities;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;

public class FreezingWindEntity extends ThrownEntity {
    public static final int SNOW_RADIUS = 3;
    public static final int FREEZE_RADIUS = 1;

    public FreezingWindEntity(EntityType<? extends FreezingWindEntity> entityType, World world) {
        super(entityType, world);
    }

    public FreezingWindEntity(World world, LivingEntity owner) {
        super(SnowMercyEntities.FREEZING_WIND, owner.getX(), owner.getEyeY() - (double) 0.9f, owner.getZ(), world);
        this.setOwner(owner);
    }

    public FreezingWindEntity(World world, double x, double y, double z) {
        super(SnowMercyEntities.FREEZING_WIND, x, y, z, world);
    }

    @Override
    protected void initDataTracker() {
    }

    @Override
    public boolean hasNoGravity() {
        return true;
    }

    @Override
    public boolean collidesWith(Entity other) {
        return false;
    }

    @Override
    public boolean collides() {
        return false;
    }

    @Override
    public void tick() {
        super.tick();

        this.setVelocity(this.getVelocity().multiply(0.9, 0.9, 0.9));

        if (this.isTouchingWaterOrRain() || this.isInsideWall() || (this.age > 20 && this.getVelocity().getX() <= 0.1f && this.getVelocity().getY() <= 0.1f && this.getVelocity().getZ() <= 0.1f)) {
            this.discard();
        }

        for (int i = 0; i < Math.sqrt(this.getVelocity().getX() * this.getVelocity().getX() + this.getVelocity().getY() * this.getVelocity().getY() + this.getVelocity().getZ() * this.getVelocity().getZ()) * 25; i++) {
            world.addParticle(ParticleTypes.SNOWFLAKE, this.getX() + random.nextGaussian() / 5f, this.getY() + .5 + random.nextGaussian() / 5f, this.getZ() + random.nextGaussian() / 5f, 0, 0, 0);
        }

        if (this.isOnFire()) {
            this.discard();
        }

        // make snow and ice
        for (int x = -SNOW_RADIUS; x <= SNOW_RADIUS; x++) {
            for (int y = -SNOW_RADIUS; y <= SNOW_RADIUS; y++) {
                for (int z = -SNOW_RADIUS; z <= SNOW_RADIUS; z++) {
                    if (Math.sqrt(this.getBlockPos().getSquaredDistance(this.getBlockPos().add(x, y, z))) <= SNOW_RADIUS) {

                        if (world.getBlockState(this.getBlockPos().add(x, y + 1, z)).getBlock() == Blocks.AIR && world.getBlockState(this.getBlockPos().add(x, y, z)).isSolidBlock(world, this.getBlockPos().add(x, y, z))) {
                            world.setBlockState(this.getBlockPos().add(x, y + 1, z), Blocks.SNOW.getDefaultState());
                        }
                        if (world.getBlockState(this.getBlockPos().add(x, y, z)).getBlock() == Blocks.WATER) {
                            world.setBlockState(this.getBlockPos().add(x, y, z), Blocks.ICE.getDefaultState());
                        }
                    }
                }
            }
        }

        // freeze entities in contact
        for (Entity entity : world.getOtherEntities(this, this.getBoundingBox().expand(FREEZE_RADIUS), entity -> entity instanceof LivingEntity && entity != this.getOwner())) {
            entity.setFrozenTicks(entity.getFrozenTicks() + (int) (10 * Math.sqrt(entity.getBlockPos().getSquaredDistance(this.getBlockPos()))));
            entity.setFireTicks(0);
        }
    }

    @Override
    public void pushAwayFrom(Entity entity) {
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean doesRenderOnFire() {
        return false;
    }
}
