package ladysnake.snowmercy.mixin;

import net.minecraft.entity.passive.FoxEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(FoxEntity.class)
public interface FoxEntityInvoker {
    @Invoker("setType")
    public void invokeSetType(FoxEntity.Type type);
}
