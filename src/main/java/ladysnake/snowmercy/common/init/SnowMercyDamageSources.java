package ladysnake.snowmercy.common.init;

import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.EntityDamageSource;
import net.minecraft.entity.damage.ProjectileDamageSource;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import org.jetbrains.annotations.Nullable;

public class SnowMercyDamageSources {
    public static DamageSource ramming(Entity attacker) {
        return new EntityDamageSource("ramming", attacker).setNeutral();
    }

    public static DamageSource icicle(PersistentProjectileEntity projectile, @Nullable Entity attacker) {
        return (new ProjectileDamageSource("icicle", projectile, attacker)).setProjectile();
    }

}
