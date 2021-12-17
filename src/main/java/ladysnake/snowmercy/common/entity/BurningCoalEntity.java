package ladysnake.snowmercy.common.entity;

import ladysnake.snowmercy.common.init.SnowMercyEntities;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.thrown.ThrownEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

public class BurningCoalEntity extends ThrownEntity {
    public static final int MELT_RADIUS = 3;
    public static final int BURN_RADIUS = 1;
    private boolean isExtraHot = false;

    public BurningCoalEntity(EntityType<? extends BurningCoalEntity> entityType, World world) {
        super(entityType, world);
    }

    public BurningCoalEntity(World world, LivingEntity owner) {
        super(SnowMercyEntities.BURNING_COAL, owner.getX(), owner.getEyeY() - (double) 0.9f, owner.getZ(), world);
        this.setOwner(owner);
    }

    public BurningCoalEntity(World world, double x, double y, double z) {
        super(SnowMercyEntities.BURNING_COAL, x, y, z, world);
    }

    public void setExtraHot(boolean extraHot) {
        isExtraHot = extraHot;
    }

    public boolean isExtraHot() {
        return isExtraHot;
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
    public boolean isFireImmune() {
        return true;
    }

    @Override
    public void tick() {
        super.tick();

        this.setVelocity(this.getVelocity().multiply(0.9, 0.9, 0.9));

        if (this.isTouchingWaterOrRain() || this.isInsideWall() || (this.age > 20 && this.getVelocity().getX() <= 0.1f && this.getVelocity().getY() <= 0.1f && this.getVelocity().getZ() <= 0.1f)) {
            this.discard();
        }

        for (int i = 0; i < Math.sqrt(this.getVelocity().getX() * this.getVelocity().getX() + this.getVelocity().getY() * this.getVelocity().getY() + this.getVelocity().getZ() * this.getVelocity().getZ()) * 25; i++) {
            world.addParticle(ParticleTypes.FLAME, this.getX() + random.nextGaussian() / 5f, this.getY() + .5 + random.nextGaussian() / 5f, this.getZ() + random.nextGaussian() / 5f, 0, 0, 0);
        }

        // melt snow and ice
        for (int x = -MELT_RADIUS; x <= MELT_RADIUS; x++) {
            for (int y = -MELT_RADIUS; y <= MELT_RADIUS; y++) {
                for (int z = -MELT_RADIUS; z <= MELT_RADIUS; z++) {
                    if (Math.sqrt(this.getBlockPos().getSquaredDistance(this.getBlockPos().add(x, y, z))) <= MELT_RADIUS) {
                        if (this.isExtraHot()) {
                            if (random.nextInt(5) == 0) {
                                if (world.getBlockState(this.getBlockPos().add(x, y, z)).getBlock() == Blocks.AIR || world.getBlockState(this.getBlockPos().add(x, y, z)).getBlock() == Blocks.SNOW || world.getBlockState(this.getBlockPos().add(x, y, z)).getBlock() == Blocks.POWDER_SNOW || world.getBlockState(this.getBlockPos().add(x, y, z)).getBlock() == Blocks.SNOW_BLOCK) {
                                    world.setBlockState(this.getBlockPos().add(x, y, z), Blocks.FIRE.getDefaultState());
                                }
                                if (world.getBlockState(this.getBlockPos().add(x, y, z)).getBlock() == Blocks.ICE || world.getBlockState(this.getBlockPos().add(x, y, z)).getBlock() == Blocks.FROSTED_ICE || world.getBlockState(this.getBlockPos().add(x, y, z)).getBlock() == Blocks.PACKED_ICE || world.getBlockState(this.getBlockPos().add(x, y, z)).getBlock() == Blocks.BLUE_ICE) {
                                    world.setBlockState(this.getBlockPos().add(x, y, z), Blocks.WATER.getDefaultState());
                                }
                            }
                        } else {
                            if (world.getBlockState(this.getBlockPos().add(x, y, z)).getBlock() == Blocks.SNOW || world.getBlockState(this.getBlockPos().add(x, y, z)).getBlock() == Blocks.POWDER_SNOW) {
                                world.setBlockState(this.getBlockPos().add(x, y, z), Blocks.AIR.getDefaultState());
                            }
                            if (world.getBlockState(this.getBlockPos().add(x, y, z)).getBlock() == Blocks.ICE || world.getBlockState(this.getBlockPos().add(x, y, z)).getBlock() == Blocks.FROSTED_ICE) {
                                world.setBlockState(this.getBlockPos().add(x, y, z), Blocks.WATER.getDefaultState());
                            }
                        }
                    }
                }
            }
        }

        // burn entities in contact
        for (Entity entity : world.getOtherEntities(this.getOwner(), this.getBoundingBox().expand(BURN_RADIUS))) {
            entity.setOnFireFor((int) (10 * Math.sqrt(entity.getBlockPos().getSquaredDistance(this.getBlockPos()))));
            entity.damage(DamageSource.IN_FIRE, 2f);
        }

        // burn icicles
        for (Entity entity : world.getOtherEntities(this.getOwner(), this.getBoundingBox().expand(MELT_RADIUS))) {
            if (entity instanceof IcicleEntity && !world.isClient) {
                ((ServerWorld) world).spawnParticles(ParticleTypes.FALLING_WATER, entity.getX(), entity.getY(), entity.getZ(), 10, random.nextGaussian() / 5f, random.nextGaussian() / 5f, random.nextGaussian() / 5f, 0);
                entity.discard();
            }
        }
    }

    @Override
    public void pushAwayFrom(Entity entity) {
    }

    @Override
    public boolean isPushable() {
        return false;
    }
}
