package ladysnake.snowmercy.common.init;

import ladysnake.snowmercy.common.SnowMercy;
import ladysnake.snowmercy.common.item.CoalBurnerItem;
import ladysnake.snowmercy.common.item.SkillotineItem;
import ladysnake.snowmercy.common.item.SledgeItem;
import ladysnake.snowmercy.common.item.SnowMercySummonerItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.registry.Registry;

public class SnowMercyItems {
    public static Item SKILLOTINE;
    public static Item COAL_BURNER;
    public static Item SLEDGE;
    public static Item SUMMONER;

    public static void init() {
        SKILLOTINE = registerItem(new SkillotineItem(ToolMaterials.DIAMOND, 3, -2.4f, new Item.Settings().group(ItemGroup.COMBAT)), "skillotine");
        COAL_BURNER = registerItem(new CoalBurnerItem(new Item.Settings().group(ItemGroup.COMBAT).maxCount(1).maxDamage(13000)), "coal_burner");
        SLEDGE = registerItem(new SledgeItem(new Item.Settings().group(ItemGroup.TRANSPORTATION).maxCount(1)), "hammersledge");

        SUMMONER = registerItem(new SnowMercySummonerItem(new Item.Settings().maxCount(1)), "summoner");
    }

    public static Item registerItem(Item item, String name) {
        Registry.register(Registry.ITEM, SnowMercy.MODID + ":" + name, item);
        return item;
    }
}