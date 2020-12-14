package ladysnake.frostlegion.common.entity;

import ladysnake.frostlegion.common.init.EntityTypes;
import ladysnake.frostlegion.common.init.Items;
import ladysnake.frostlegion.common.network.Packets;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.Packet;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class ExplosiveOrnamentEntity extends ThrownItemEntity {
    public ExplosiveOrnamentEntity(EntityType<? extends ExplosiveOrnamentEntity> entityType, World world) {
        super(entityType, world);
    }

    public ExplosiveOrnamentEntity(World world, LivingEntity owner) {
        super(EntityTypes.EXPLOSIVE_ORNAMENT, owner, world);
    }

    public ExplosiveOrnamentEntity(World world, double x, double y, double z) {
        super(EntityTypes.EXPLOSIVE_ORNAMENT, x, y, z, world);
    }

    protected Item getDefaultItem() {
        return Items.EXPLOSIVE_ORNAMENT;
    }

    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.world.isClient) {
            this.world.createExplosion(this, hitResult.getPos().getX(), hitResult.getPos().getY(), hitResult.getPos().getZ(), 1.0f, Explosion.DestructionType.DESTROY);
            this.world.sendEntityStatus(this, (byte)3);
            this.remove();
        }
    }

    @Override
    public Packet<?> createSpawnPacket() {
        return Packets.newSpawnPacket(this);
    }
}
