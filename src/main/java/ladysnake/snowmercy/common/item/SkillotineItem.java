package ladysnake.snowmercy.common.item;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

import static net.minecraft.text.Style.EMPTY;

public class SkillotineItem extends ShovelItem {
    public SkillotineItem(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
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
