package ladysnake.snowmercy.mixin;

import net.minecraft.entity.projectile.FireworkRocketEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(FireworkRocketEntity.class)
public interface FireworkRocketEntityInvoker {
    @Invoker("explodeAndRemove")
    public void invokeExplodeAndRemove();
}
