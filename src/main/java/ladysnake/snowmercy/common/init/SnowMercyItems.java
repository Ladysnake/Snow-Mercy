package ladysnake.snowmercy.common.init;

import ladysnake.snowmercy.common.SnowMercy;
import ladysnake.snowmercy.common.item.CoalBurnerItem;
import ladysnake.snowmercy.common.item.FrozenCompassItem;
import ladysnake.snowmercy.common.item.SkillotineItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.registry.Registry;

public class SnowMercyItems {
    public static Item FROZEN_COMPASS;
    public static Item SKILLOTINE;
    public static Item COAL_BURNER;

    public static void init() {
        FROZEN_COMPASS = registerItem(new FrozenCompassItem(new FabricItemSettings().maxCount(1).group(ItemGroup.MISC)), "frozen_compass");
        SKILLOTINE = registerItem(new SkillotineItem(ToolMaterials.DIAMOND, 3, -2.4f, new Item.Settings().group(ItemGroup.COMBAT)), "skillotine");
        COAL_BURNER = registerItem(new CoalBurnerItem(new Item.Settings().group(ItemGroup.COMBAT).maxCount(1).maxDamage(640)), "coal_burner");
    }

    public static Item registerItem(Item item, String name) {
        Registry.register(Registry.ITEM, SnowMercy.MODID + ":" + name, item);
        return item;
    }
}