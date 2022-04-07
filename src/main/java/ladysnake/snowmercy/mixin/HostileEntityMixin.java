package ladysnake.snowmercy.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(HostileEntity.class)
public abstract class HostileEntityMixin extends LivingEntity {
    protected HostileEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "canSpawnInDark", at = @At("RETURN"), cancellable = true)
    private static void canSpawnInDark(EntityType<? extends HostileEntity> type, ServerWorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        if (world.getDimension().getCoordinateScale() == 2 && !world.getDimension().isNatural() && !world.getDimension().isUltrawarm() && world.getDimension().isBedWorking() && world.getDimension().hasRaids()) {
            callbackInfoReturnable.setReturnValue(false);
        }
    }
}
