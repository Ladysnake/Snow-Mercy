package ladysnake.snowmercy.common.block;

import ladysnake.snowmercy.cca.SnowMercyComponents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.explosion.Explosion;

public class FrozenLodestoneBlock extends Block {
    public FrozenLodestoneBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBreak(world, pos, state, player);
        if (world instanceof ServerWorld) {
            SnowMercyComponents.SNOWMERCY.get(world).stopEvent((ServerWorld) world);
        }
    }

    @Override
    public void onBroken(WorldAccess world, BlockPos pos, BlockState state) {
        super.onBroken(world, pos, state);
        if (world instanceof ServerWorld) {
            SnowMercyComponents.SNOWMERCY.get(world).stopEvent((ServerWorld) world);
        }
    }

    @Override
    public void onDestroyedByExplosion(World world, BlockPos pos, Explosion explosion) {
        super.onDestroyedByExplosion(world, pos, explosion);
        if (world instanceof ServerWorld) {
            SnowMercyComponents.SNOWMERCY.get(world).stopEvent((ServerWorld) world);
        }
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        if (world instanceof ServerWorld) {
            SnowMercyComponents.SNOWMERCY.get(world).startEvent((ServerWorld) world);
        }
    }
}
