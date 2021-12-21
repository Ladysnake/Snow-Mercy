package ladysnake.snowmercy.common.init;

import net.minecraft.item.ItemStack;

public class LootSpawnEntry {
    public ItemStack itemStack;
    public int count;
    public LootSpawnEntry(ItemStack itemStack, int count) {
        this.itemStack = itemStack;
        this.count = count;
    }
}
