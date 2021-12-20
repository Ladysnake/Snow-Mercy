package ladysnake.snowmercy.common.init;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;

public class WaveSpawnEntry {
    public WaveSpawnEntry(EntityType<? extends MobEntity> entityType, int count) {
        this.entityType = entityType;
        this.count = count;
    }

    public EntityType<? extends MobEntity> entityType;
    public int count;
}
