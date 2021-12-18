package ladysnake.snowmercy.common.init;

import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.EntityDamageSource;

public class SnowMercyDamageSources {
    public static DamageSource ramming(Entity attacker) {
        return new EntityDamageSource("ramming", attacker).setNeutral();
    }
}
