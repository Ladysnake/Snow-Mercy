package ladysnake.snowmercy.mixin;

import ladysnake.snowmercy.cca.SnowMercyComponents;
import ladysnake.snowmercy.common.world.ExtendedBiome;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Biome.class)
public class FrozenTemperatureMixin implements ExtendedBiome {
    @Unique
    private World world;

    @Inject(at = @At(value = "RETURN"), method = "getTemperature()F", cancellable = true)
    public void getTemperature(CallbackInfoReturnable<Float> cir) {
        if (this.world != null && SnowMercyComponents.SNOWMERCY.get(this.world).isEventOngoing()) {
            cir.setReturnValue(-10f);
        }
    }

    @Inject(at = @At(value = "RETURN"), method = "getPrecipitation", cancellable = true)
    public void getPrecipitation(CallbackInfoReturnable<Biome.Precipitation> cir) {
        if (this.world != null && SnowMercyComponents.SNOWMERCY.get(this.world).isEventOngoing() && cir.getReturnValue() == Biome.Precipitation.RAIN) {
            cir.setReturnValue(Biome.Precipitation.SNOW);
        }
    }

    @Override
    public void frostlegion_setWorld(@Nullable World world) {
        this.world = world;
    }
}
