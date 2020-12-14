package ladysnake.frostlegion.common.entity;

import ladysnake.frostlegion.common.init.EntityTypes;
import ladysnake.frostlegion.common.network.Packets;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class IcicleEntity extends PersistentProjectileEntity {
    public IcicleEntity(EntityType<? extends IcicleEntity> entityType, World world) {
        super(entityType, world);
        this.setSound(SoundEvents.BLOCK_GLASS_BREAK);
    }

    public IcicleEntity(World world, double x, double y, double z) {
        this(EntityTypes.ICICLE, world);
        this.setPos(x, y, z);
    }

    public IcicleEntity(World world, LivingEntity owner) {
        super(EntityTypes.ICICLE, world);
        this.setOwner(owner);
    }

    @Override
    protected ItemStack asItemStack() {
        return null;
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
    }

    @Override
    protected SoundEvent getHitSound() {
        return SoundEvents.BLOCK_GLASS_BREAK;
    }

    @Override
    public void tick() {
        super.tick();

        if (this.inGround) {
            this.remove();
        }
    }

    @Override
    public Packet<?> createSpawnPacket() {
        return Packets.newSpawnPacket(this);
    }
}
