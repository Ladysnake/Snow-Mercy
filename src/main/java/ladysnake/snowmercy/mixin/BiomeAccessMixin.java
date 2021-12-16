package ladysnake.snowmercy.mixin;

import ladysnake.snowmercy.common.world.ExtendedBiome;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeAccess;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BiomeAccess.class)
public abstract class BiomeAccessMixin {
    @Unique
    private @Nullable
    World world;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void init(BiomeAccess.Storage storage, long seed, CallbackInfo ci) {
        this.world = storage instanceof World ? ((World)storage) : null;
    }

    @Inject(method = "getBiome(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/world/biome/Biome;", at = @At("RETURN"))
    private void getBiome(BlockPos pos, CallbackInfoReturnable<Biome> cir) {
        if (cir.getReturnValue() != null) {
            ((ExtendedBiome)(Object)cir.getReturnValue()).frostlegion_setWorld(this.world);
        }
    }
}