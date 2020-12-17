package ladysnake.frostlegion.common;

import ladysnake.frostlegion.cca.SnowMercyComponents;
import ladysnake.frostlegion.common.command.SnowMercyCommand;
import ladysnake.frostlegion.common.init.Blocks;
import ladysnake.frostlegion.common.init.EntityTypes;
import ladysnake.frostlegion.common.init.Items;
import ladysnake.frostlegion.common.utils.WorldUtils;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.SpawnSettings;

public class FrostLegion implements ModInitializer {
    public static final String MODID = "frostlegion";

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
                    if (world.getTimeOfDay() >= 13188) {
                        WorldUtils.getLoadedChunks(world).forEach(chunk -> {
                            ChunkPos pos = chunk.getPos();
                            if (world.getEntitiesByClass(HostileEntity.class, new Box(pos.getStartPos(), pos.getStartPos().add(16, 256, 16)), e -> true).size() < 3) {
                                int randomX = world.random.nextInt(16);
                                int randomZ = world.random.nextInt(16);
                                ChunkPos chunkPos = chunk.getPos();

                                int y = world.getTopY(Heightmap.Type.MOTION_BLOCKING, chunkPos.getStartX() + randomX, chunkPos.getStartZ() + randomZ);
                                BlockPos spawnPos = new BlockPos(chunkPos.getStartX() + randomX, y, chunkPos.getStartZ() + randomZ);

                                MobEntity entity = EntityTypes.EVENT_SPAWN_CANDIDATES.get(world.getRandom().nextInt(EntityTypes.EVENT_SPAWN_CANDIDATES.size())).create(world);

                                if (entity != null) {
                                    entity.setPos(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ());
                                    entity.updateTrackedPosition(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ());
                                    entity.updatePosition(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ());
                                    world.spawnEntity(entity);
                                }
                            }
                        });
                    }
                }
            });
        });
    }
}
