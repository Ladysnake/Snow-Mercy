package ladysnake.snowmercy.mixin;

import ladysnake.snowmercy.common.init.SnowMercyItems;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntityRenderer.class)
public class PlayerEntityRendererMixin {
    @Inject(method = "getArmPose", at = @At("RETURN"), cancellable = true)
    private static void getArmPose(AbstractClientPlayerEntity player, Hand hand, CallbackInfoReturnable<BipedEntityModel.ArmPose> callbackInfoReturnable) {
        ItemStack itemStack = player.getStackInHand(hand);

        if (itemStack.getItem() == SnowMercyItems.COAL_BURNER) {
            callbackInfoReturnable.setReturnValue(BipedEntityModel.ArmPose.BOW_AND_ARROW);
        }
    }
}