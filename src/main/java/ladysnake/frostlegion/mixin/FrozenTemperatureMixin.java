package ladysnake.frostlegion.mixin;

import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Biome.class)
public class FrozenTemperatureMixin {
    @Inject(at = @At(value = "RETURN"), method = "getTemperature()F", cancellable = true)
    public void getTemperature(CallbackInfoReturnable<Float> cir) {
        cir.setReturnValue(-10f);
    }

    @Inject(at = @At(value = "RETURN"), method = "getPrecipitation", cancellable = true)
    public void getPrecipitation(CallbackInfoReturnable<Biome.Precipitation> cir) {
        cir.setReturnValue(Biome.Precipitation.SNOW);
    }
}
