package ladysnake.snowmercy.common.item;

import com.google.common.collect.ImmutableSet;
import ladysnake.snowmercy.common.entity.BurningCoalEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

import java.util.List;
import java.util.function.Predicate;

import static net.minecraft.text.Style.EMPTY;

public class CoalBurnerItem extends RangedWeaponItem implements Vanishable {

    public CoalBurnerItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public Predicate<ItemStack> getHeldProjectiles() {
        return CROSSBOW_HELD_PROJECTILES;
    }

    @Override
    public Predicate<ItemStack> getProjectiles() {
        return BOW_PROJECTILES;
    }

    @Override
    public int getRange() {
        return 4;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if ((user.isCreative() || user.getInventory().containsAny(ImmutableSet.of(Items.COAL, Items.CHARCOAL, Items.BLAZE_POWDER))) && !user.isSubmergedInWater()) {
            user.setCurrentHand(hand);
            user.playSound(SoundEvents.ITEM_FIRECHARGE_USE, 1.0f, 0.5f);
            user.playSound(SoundEvents.BLOCK_FIRE_AMBIENT, 1.0f, 1.0f);
            return TypedActionResult.consume(itemStack);
        }
        user.playSound(SoundEvents.BLOCK_LEVER_CLICK, 1.0f, 0.8f);
        return TypedActionResult.fail(itemStack);


    }

    @Override
    public void usageTick(World world, LivingEntity entity, ItemStack stack, int remainingUseTicks) {
        if (entity instanceof PlayerEntity) {
            PlayerEntity user = (PlayerEntity) entity;
            if ((user.isCreative() || user.getInventory().containsAny(ImmutableSet.of(Items.COAL, Items.CHARCOAL, Items.BLAZE_POWDER))) && !user.isSubmergedInWater()) {
                boolean extraHot = false;

                stack.damage(1, user, p -> p.sendToolBreakStatus(user.getMainHandStack().equals(stack) ? Hand.MAIN_HAND : Hand.OFF_HAND));

                for (int i = 0; i < user.getInventory().size(); i++) {
                    ItemStack itemStack = user.getInventory().getStack(i);
                    if (itemStack.getItem() == Items.COAL || itemStack.getItem() == Items.CHARCOAL) {
                        if (!user.isCreative() && remainingUseTicks % 40 == 0 || remainingUseTicks == this.getMaxUseTime(stack)) {
                            itemStack.decrement(1);
                        }
                        break;
                    } else if (itemStack.getItem() == Items.BLAZE_POWDER) {
                        if (!user.isCreative() && remainingUseTicks % 10 == 0 || remainingUseTicks == this.getMaxUseTime(stack)) {
                            itemStack.decrement(1);
                        }
                        extraHot = true;
                        break;
                    }
                }

                if (!world.isClient) {
                    BurningCoalEntity burningCoalEntity = new BurningCoalEntity(world, user);
                    burningCoalEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0f, 1.5f, 1.0f);
                    burningCoalEntity.setExtraHot(extraHot);
                    world.spawnEntity(burningCoalEntity);
                }

                if (remainingUseTicks % 5 == 0) {
                    user.playSound(SoundEvents.ITEM_FIRECHARGE_USE, 1.0f, 0.5f);
                    user.playSound(SoundEvents.BLOCK_FIRE_AMBIENT, 1.0f, 1.0f);
                }
            }
        }
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 999999990;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.NONE;
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        if (Screen.hasShiftDown()) {
            tooltip.add(new TranslatableText("item.snowmercy.coal_burner.tooltip1").setStyle(EMPTY.withColor(Formatting.DARK_AQUA)));
            tooltip.add(new TranslatableText("item.snowmercy.coal_burner.tooltip2").setStyle(EMPTY.withColor(Formatting.DARK_AQUA).withFormatting(Formatting.ITALIC)));
            tooltip.add(new TranslatableText("item.snowmercy.coal_burner.tooltip3").setStyle(EMPTY.withColor(Formatting.DARK_AQUA).withFormatting(Formatting.ITALIC)));
        } else {
            tooltip.add(new TranslatableText("tip.snowmercy.sneak_tooltip").setStyle(EMPTY.withColor(Formatting.GRAY)));
        }
    }
}

