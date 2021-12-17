package ladysnake.snowmercy.mixin;

import ladysnake.snowmercy.common.entity.SledgeEntity;
import ladysnake.snowmercy.common.entity.WeaponizedSnowGolemEntity;
import net.minecraft.block.PowderSnowBlock;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PowderSnowBlock.class)
public class PowderSnowBlockMixin {
    @Inject(method = "canWalkOnPowderSnow", at = @At("RETURN"), cancellable = true)
    private static void canWalkOnPowderSnow(Entity entity, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        if (entity instanceof SledgeEntity || entity instanceof WeaponizedSnowGolemEntity) {
            callbackInfoReturnable.setReturnValue(true);
        }
    }
}