package ladysnake.snowmercy.common.item;

import ladysnake.snowmercy.cca.SnowMercyComponents;
import ladysnake.snowmercy.common.init.SnowMercyBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

import static net.minecraft.text.Style.EMPTY;

public class FrozenCompassItem extends Item {
    public FrozenCompassItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (context.getWorld().getBlockState(context.getBlockPos()).getBlock() == Blocks.LODESTONE) {
            if (SnowMercyComponents.SNOWMERCY.get(context.getWorld()).isEventOngoing()) {
                if (context.getPlayer() != null) {
                    context.getPlayer().sendMessage(new TranslatableText("info.snowmercy.ongoing"), true);
                }
                return ActionResult.FAIL;
            } else {
                context.getWorld().playSound(null, context.getBlockPos(), SoundEvents.ITEM_LODESTONE_COMPASS_LOCK, SoundCategory.PLAYERS, 1.0F, 1.0F);

                context.getWorld().setBlockState(context.getBlockPos(), Blocks.BLUE_ICE.getDefaultState());
                context.getWorld().breakBlock(context.getBlockPos(), false);
                context.getWorld().setBlockState(context.getBlockPos(), Blocks.SNOW_BLOCK.getDefaultState());
                context.getWorld().breakBlock(context.getBlockPos(), false);
                context.getWorld().setBlockState(context.getBlockPos(), SnowMercyBlocks.FROZEN_LODESTONE.getDefaultState());
                if (context.getPlayer() != null && !context.getPlayer().isCreative()) {
                    context.getStack().decrement(1);
                }

                if (context.getWorld() instanceof ServerWorld) {
                    SnowMercyComponents.SNOWMERCY.get(context.getWorld()).startEvent(context.getWorld());
                }

                return ActionResult.SUCCESS;
            }
        } else {
            return ActionResult.FAIL;
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(new TranslatableText("item.snowmercy.frozen_compass.tooltip").setStyle(EMPTY.withColor(Formatting.DARK_AQUA)));
    }
}
