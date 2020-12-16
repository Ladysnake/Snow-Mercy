package ladysnake.frostlegion.common.entity;

import ladysnake.frostlegion.common.network.Packets;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.SnowGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.Packet;
import net.minecraft.world.World;

public class SawmanEntity extends EvilSnowGolemEntity {
    public SawmanEntity(EntityType<SawmanEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.targetSelector.add(1, new RevengeGoal(this));
        this.goalSelector.add(2, new MeleeAttackGoal(this, 1.0D, false));
    }

    @Override
    public Packet<?> createSpawnPacket() {
        return Packets.newSpawnPacket(this);
    }

}
