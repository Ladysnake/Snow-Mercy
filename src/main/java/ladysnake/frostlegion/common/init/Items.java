package ladysnake.frostlegion.common.init;

import ladysnake.frostlegion.common.FrostLegion;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

public class Items {

    public static void init() {
    }

    public static Item registerItem(Item item, String name) {
        Registry.register(Registry.ITEM, FrostLegion.MODID + ":" + name, item);
        return item;
    }

}