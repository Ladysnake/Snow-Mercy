package ladysnake.snowmercy.common.init;

import ladysnake.snowmercy.common.SnowMercy;
import ladysnake.snowmercy.common.block.FrozenLodestoneBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.registry.Registry;

public class Blocks {
    public static Block FROZEN_LODESTONE;

    public static void init() {
        FROZEN_LODESTONE = registerBlock(new FrozenLodestoneBlock(FabricBlockSettings.of(Material.REPAIR_STATION).requiresTool().strength(3.5F).sounds(BlockSoundGroup.GLASS)), "frozen_lodestone", null);
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
