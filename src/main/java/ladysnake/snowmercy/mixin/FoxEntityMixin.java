package ladysnake.snowmercy.mixin;

import ladysnake.snowmercy.common.entity.TundrabidEntity;
import ladysnake.snowmercy.common.entity.WeaponizedSnowGolemEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.UUID;

@Mixin(FoxEntity.class)
public abstract class FoxEntityMixin extends AnimalEntity {
    protected FoxEntityMixin(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "tick", at = @At("TAIL"))
    public void tick(CallbackInfo callbackInfo) {
        if ((Object) this instanceof TundrabidEntity) {
            this.world.addParticle(ParticleTypes.SNOWFLAKE, this.getX() - (double) (this.getWidth() + 1.0F) * 0.5D * (double) MathHelper.sin(this.bodyYaw * 0.017453292F), this.getEyeY() - 0.10000000149011612D, this.getZ() + (double) (this.getWidth() + 1.0F) * 0.5D * (double) MathHelper.cos(this.bodyYaw * 0.017453292F), 0, -0.01D, 0);
        }
    }

    @Inject(method = "initGoals", at = @At("HEAD"))
    protected void initGoals(CallbackInfo callbackInfo) {
        if ((Object) this instanceof TundrabidEntity) {
            this.targetSelector.add(4, new ActiveTargetGoal<>(this, PlayerEntity.class, 10, false, false, Entity::isAttackable));
            this.targetSelector.add(4, new ActiveTargetGoal<>(this, WeaponizedSnowGolemEntity.class, 10, false, false, livingEntity -> livingEntity instanceof WeaponizedSnowGolemEntity && ((WeaponizedSnowGolemEntity) livingEntity).getHead() != 1));
        }
    }

    @Inject(method = "canTrust", at = @At("RETURN"), cancellable = true)
    void canTrust(UUID uuid, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        if ((Object) this instanceof TundrabidEntity) {
            callbackInfoReturnable.setReturnValue(true);
        }
    }

    @Inject(method = "isAggressive", at = @At("RETURN"), cancellable = true)
    void isAggressive(CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        if ((Object) this instanceof TundrabidEntity) {
            callbackInfoReturnable.setReturnValue(true);
        }
    }

    @Inject(method = "isSleeping", at = @At("RETURN"), cancellable = true)
    public void isSleeping(CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        if ((Object) this instanceof TundrabidEntity) {
            callbackInfoReturnable.setReturnValue(false);
        }
    }

    @Inject(method = "getFoxType", at = @At("RETURN"), cancellable = true)
    public void getFoxType(CallbackInfoReturnable<FoxEntity.Type> callbackInfoReturnable) {
        if ((Object) this instanceof TundrabidEntity) {
            callbackInfoReturnable.setReturnValue(FoxEntity.Type.SNOW);
        }
    }

}