package ladysnake.snowmercy.common.init;

import ladysnake.snowmercy.common.SnowMercy;
import ladysnake.snowmercy.common.item.FrozenCompassItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;

public class Items {
    public static Item FROZEN_COMPASS;

    public static void init() {
        FROZEN_COMPASS = registerItem(new FrozenCompassItem(new FabricItemSettings().maxCount(1).group(ItemGroup.MISC)), "frozen_compass");
    }

    public static Item registerItem(Item item, String name) {
        Registry.register(Registry.ITEM, SnowMercy.MODID + ":" + name, item);
        return item;
    }

}