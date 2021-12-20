package ladysnake.snowmercy.common.init;

import net.minecraft.item.ItemStack;

public class LootSpawnEntry {
    public LootSpawnEntry(ItemStack itemStack, int count) {
        this.itemStack = itemStack;
        this.count = count;
    }

    public ItemStack itemStack;
    public int count;
}
