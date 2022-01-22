package ladysnake.snowmercy.common.block;

import net.fabricmc.fabric.api.dimension.v1.FabricDimensions;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;

public class FrozenLodestoneBlock extends Block {
    public FrozenLodestoneBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient && !player.hasVehicle() && !player.hasPassengers() && player.canUsePortals()) {
            ServerWorld spawnWorld = world.getServer().getWorld(((ServerPlayerEntity)player).getSpawnPointDimension());

            if (spawnWorld == null) {
                spawnWorld = world.getServer().getOverworld();
            }

            BlockPos spawn = ((ServerPlayerEntity) player).getSpawnPointPosition();
            if (spawn == null) {
                spawn = spawnWorld.getSpawnPos();
            }
            FabricDimensions.teleport(player, spawnWorld, new TeleportTarget(new Vec3d(spawn.getX(), spawn.getY(), spawn.getZ()), Vec3d.ZERO, player.getYaw(), 90));
        }

        return super.onUse(state, world, pos, player, hand, hit);
    }
}
