package ladysnake.snowmercy.common;

import ladysnake.snowmercy.cca.SnowMercyComponents;
import ladysnake.snowmercy.common.command.SnowMercyCommand;
import ladysnake.snowmercy.common.entity.WeaponizedSnowGolemEntity;
import ladysnake.snowmercy.common.init.Blocks;
import ladysnake.snowmercy.common.init.EntityTypes;
import ladysnake.snowmercy.common.init.Items;
import ladysnake.snowmercy.common.utils.WorldUtils;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.Heightmap;

public class SnowMercy implements ModInitializer {
    public static final String MODID = "snowmercy";

    @Override
    public void onInitialize() {
        Blocks.init();
        Items.init();
        EntityTypes.init();

        CommandRegistrationCallback.EVENT.register((commandDispatcher, b) ->
                SnowMercyCommand.register(commandDispatcher)
        );

        ServerTickEvents.END_SERVER_TICK.register(server -> {
            server.getWorlds().forEach(world -> {
                if (SnowMercyComponents.SNOWMERCY.get(world).isEventOngoing() && world.random.nextInt(5 * 20) == 0) {
                    WorldUtils.getLoadedChunks(world).forEach(chunk -> {
                        ChunkPos pos = chunk.getPos();
                        if (world.getEntitiesByClass(WeaponizedSnowGolemEntity.class, new Box(pos.getStartPos(), pos.getStartPos().add(16, 256, 16)), e -> true).size() < 1) {
                            int randomX = world.random.nextInt(16);
                            int randomZ = world.random.nextInt(16);
                            ChunkPos chunkPos = chunk.getPos();

                            int yMax = world.getTopY(Heightmap.Type.MOTION_BLOCKING, chunkPos.getStartX() + randomX, chunkPos.getStartZ() + randomZ);
                            for (int y = 1; y <= yMax; y++) {
                                BlockPos spawnPos = new BlockPos(chunkPos.getStartX() + randomX, y, chunkPos.getStartZ() + randomZ);

                                MobEntity entity = EntityTypes.EVENT_SPAWN_CANDIDATES.get(world.getRandom().nextInt(EntityTypes.EVENT_SPAWN_CANDIDATES.size())).create(world);

                                if (entity != null && world.getBlockState(spawnPos).isAir() && world.getBlockState(spawnPos.add(0, -1, 0)).isSolidBlock(world, spawnPos.add(0, -1, 0))) {
                                    entity.setPos(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ());
                                    entity.updateTrackedPosition(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ());
                                    entity.updatePosition(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ());
                                    world.spawnEntity(entity);

                                    break;
                                }
                            }
                        }
                    });
                }
            });
        });
    }
}
