package ladysnake.snowmercy.mixin;

import ladysnake.snowmercy.common.entity.SnowMercyEnemy;
import net.minecraft.entity.Entity;
import net.minecraft.scoreboard.AbstractTeam;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow
    @Nullable
    public abstract AbstractTeam getScoreboardTeam();

    @Inject(method = "getTeamColorValue", at = @At("RETURN"), cancellable = true)
    public void getTeamColorValue(CallbackInfoReturnable<Integer> callbackInfoReturnable) {
        if (this instanceof SnowMercyEnemy) {
            callbackInfoReturnable.setReturnValue(0xFF0000);
        }
    }

}
