package ladysnake.snowmercy.common.item;

import ladysnake.snowmercy.common.entity.SledgeEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.List;
import java.util.function.Predicate;

import static net.minecraft.text.Style.EMPTY;

public class SledgeItem extends Item {
    private static final Predicate<Entity> RIDERS = EntityPredicates.EXCEPT_SPECTATOR.and(Entity::collides);

    public SledgeItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        Object vec3d2;
        ItemStack itemStack = user.getStackInHand(hand);
        BlockHitResult hitResult = SledgeItem.raycast(world, user, RaycastContext.FluidHandling.ANY);
        if (((HitResult) hitResult).getType() == HitResult.Type.MISS) {
            return TypedActionResult.pass(itemStack);
        }
        Vec3d vec3d = user.getRotationVec(1.0f);
        double d = 5.0;
        List<Entity> list = world.getOtherEntities(user, user.getBoundingBox().stretch(vec3d.multiply(5.0)).expand(1.0), RIDERS);
        if (!list.isEmpty()) {
            vec3d2 = user.getEyePos();
            for (Entity entity : list) {
                Box box = entity.getBoundingBox().expand(entity.getTargetingMargin());
                if (!box.contains((Vec3d) vec3d2)) continue;
                return TypedActionResult.pass(itemStack);
            }
        }
        if (((HitResult) hitResult).getType() == HitResult.Type.BLOCK) {
            vec3d2 = new SledgeEntity(world, hitResult.getPos().x, hitResult.getPos().y, hitResult.getPos().z);
            ((Entity) vec3d2).setYaw(user.getYaw());
            if (!world.isSpaceEmpty((Entity) vec3d2, ((Entity) vec3d2).getBoundingBox())) {
                return TypedActionResult.fail(itemStack);
            }
            if (!world.isClient) {
                world.spawnEntity((Entity) vec3d2);
                world.emitGameEvent((Entity) user, GameEvent.ENTITY_PLACE, new BlockPos(hitResult.getPos()));
                if (!user.getAbilities().creativeMode) {
                    itemStack.decrement(1);
                }
            }
            user.incrementStat(Stats.USED.getOrCreateStat(this));
            return TypedActionResult.success(itemStack, world.isClient());
        }
        return TypedActionResult.pass(itemStack);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        if (Screen.hasShiftDown()) {
            tooltip.add(new TranslatableText("item.snowmercy.skillotine.tooltip").setStyle(EMPTY.withColor(Formatting.DARK_AQUA)));
        } else {
            tooltip.add(new TranslatableText("tip.snowmercy.sneak_tooltip").setStyle(EMPTY.withColor(Formatting.GRAY)));
        }
    }
}

