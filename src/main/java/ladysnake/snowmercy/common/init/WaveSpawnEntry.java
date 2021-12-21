package ladysnake.snowmercy.common.init;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;

public class WaveSpawnEntry {
    public EntityType<? extends MobEntity> entityType;
    public int count;
    public WaveSpawnEntry(EntityType<? extends MobEntity> entityType, int count) {
        this.entityType = entityType;
        this.count = count;
    }
}
