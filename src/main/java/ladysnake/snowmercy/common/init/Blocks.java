package ladysnake.snowmercy.common.init;

import ladysnake.snowmercy.common.SnowMercy;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;

public class Blocks {

    public static void init() {
    }

    private static Block registerBlock(Block block, String name, ItemGroup itemGroup) {
        Registry.register(Registry.BLOCK, SnowMercy.MODID + ":" + name, block);

        if (itemGroup != null) {
            BlockItem item = new BlockItem(block, new Item.Settings().group(itemGroup));
            item.appendBlocks(Item.BLOCK_ITEMS, item);
            Items.registerItem(item, name);
        }

        return block;
    }

}
