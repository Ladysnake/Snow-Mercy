package ladysnake.snowmercy.common.entity;

import ladysnake.snowmercy.common.entity.ai.goal.FollowAndBlowGoal;
import ladysnake.snowmercy.common.network.Packets;
import ladysnake.snowmercy.common.world.PuffExplosion;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.ExplosionS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

import java.util.Iterator;

public class SnugglesEntity extends WeaponizedSnowGolemEntity {
    public static final ItemStack TNT = new ItemStack(Items.TNT, 1);

    public SnugglesEntity(EntityType<? extends SnugglesEntity> entityType, World world) {
        super(entityType, world);

        ItemStack equippedStack = TNT.copy();
        this.equipStack(EquipmentSlot.CHEST, equippedStack);
        this.setEquipmentDropChance(EquipmentSlot.CHEST, 0.20F);
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(1, new FollowAndBlowGoal(this, 1.0D, false));
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        boolean ret = super.damage(source, amount);
        if (!this.isDead() && ret && amount == 0) {
            explode();
        }
        return ret;
    }

    public void explode() {
        if (!this.getEntityWorld().isClient()) {
            this.remove();

            ServerWorld world = (ServerWorld) this.getEntityWorld();

            float power = 3.0f;
            Explosion.DestructionType destructionType;
            if (this.world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)) {
                destructionType = Explosion.DestructionType.DESTROY;
            } else {
                destructionType = Explosion.DestructionType.NONE;
            }

            Explosion explosion = new PuffExplosion(world, this, DamageSource.explosion(this), null, this.getX(), this.getY(), this.getZ(), power, 3f, destructionType, true);
            explosion.collectBlocksAndDamageEntities();
            explosion.affectWorld(false);

            Iterator var14 = world.getPlayers().iterator();
            if (destructionType == Explosion.DestructionType.NONE) {
                explosion.clearAffectedBlocks();
            }

            while (var14.hasNext()) {
                ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity) var14.next();
                if (serverPlayerEntity.squaredDistanceTo(this.getX(), this.getY(), this.getZ()) < 4096.0D) {
                    serverPlayerEntity.networkHandler.sendPacket(new ExplosionS2CPacket(this.getX(), this.getY(), this.getZ(), power, explosion.getAffectedBlocks(), (Vec3d) explosion.getAffectedPlayers().get(serverPlayerEntity)));
                }
            }
        }
    }

    @Override
    public Packet<?> createSpawnPacket() {
        return Packets.newSpawnPacket(this);
    }

}
