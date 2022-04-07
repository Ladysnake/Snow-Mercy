package ladysnake.snowmercy.common.item;

import ladysnake.snowmercy.common.init.SnowMercyEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Random;

public class SnowMercySummonerItem extends Item {

    public SnowMercySummonerItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ArrayList<EntityType<? extends MobEntity>> livingEntities = new ArrayList<>();
        livingEntities.add(SnowMercyEntities.TUNDRABID);
        livingEntities.add(SnowMercyEntities.ICEBALL);
        livingEntities.add(SnowMercyEntities.SNUGGLES);
        livingEntities.add(SnowMercyEntities.CHILL_SNUGGLES);
        livingEntities.add(SnowMercyEntities.ROCKETS);
        livingEntities.add(SnowMercyEntities.MORTARS);
        livingEntities.add(SnowMercyEntities.SAWMAN);
        livingEntities.add(SnowMercyEntities.BOOMBOX);

        MobEntity mobEntity = livingEntities.get(new Random().nextInt(livingEntities.size())).create(world);
        mobEntity.setPosition(user.getX(), user.getY(), user.getZ());

        float f = MathHelper.wrapDegrees(user.getYaw());
        float g = MathHelper.wrapDegrees(user.getPitch());
        float chunkPos = MathHelper.clamp(g, -90.0f, 90.0f);
        mobEntity.refreshPositionAndAngles(user.getX(), user.getY(), user.getZ(), f, chunkPos);
        mobEntity.setHeadYaw(f);
        mobEntity.setAiDisabled(true);


        world.spawnEntity(mobEntity);

        return TypedActionResult.success(user.getStackInHand(hand));
    }
}
