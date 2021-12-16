package ladysnake.snowmercy.common.entity;

import ladysnake.snowmercy.common.entity.ai.goal.FollowAndBlowGoal;
import ladysnake.snowmercy.common.world.PuffExplosion;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.s2c.play.ExplosionS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
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

    @Override
    public WeaponizedGolemType getGolemType() {
        return WeaponizedGolemType.SNUGGLES;
    }

    public void explode() {
        if (!this.getEntityWorld().isClient()) {
            this.discard();

            ServerWorld world = (ServerWorld) this.getEntityWorld();

            float power = 3.0f;
            Explosion.DestructionType destructionType = Explosion.DestructionType.NONE;

            Explosion explosion = new PuffExplosion(world, this, DamageSource.explosion(this), null, this.getX(), this.getY(), this.getZ(), power, 3f, destructionType, false);
            explosion.collectBlocksAndDamageEntities();
            explosion.affectWorld(false);

            for (int i = 0; i < 250; i++) {
                FallingBlockEntity entity = new FallingBlockEntity(world, this.getX(), this.getY() + 0.5, this.getZ(), Blocks.POWDER_SNOW.getDefaultState());
                entity.timeFalling = 1;
                entity.dropItem = false;
                entity.setPos(this.getX(), this.getY(), this.getZ());
                entity.updateTrackedPosition(this.getX(), this.getY() + 0.5f, this.getZ());
                entity.setVelocity(random.nextGaussian(), random.nextGaussian(), random.nextGaussian());
                world.spawnEntity(entity);

            }

            Iterator<ServerPlayerEntity> var14 = world.getPlayers().iterator();
            if (destructionType == Explosion.DestructionType.NONE) {
                explosion.clearAffectedBlocks();
            }

            while (var14.hasNext()) {
                ServerPlayerEntity serverPlayerEntity = var14.next();
                if (serverPlayerEntity.squaredDistanceTo(this.getX(), this.getY(), this.getZ()) < 4096.0D) {
                    serverPlayerEntity.networkHandler.sendPacket(new ExplosionS2CPacket(this.getX(), this.getY(), this.getZ(), power, explosion.getAffectedBlocks(), explosion.getAffectedPlayers().get(serverPlayerEntity)));
                }
            }
        }
    }
}
