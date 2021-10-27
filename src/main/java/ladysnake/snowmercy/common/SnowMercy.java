package ladysnake.snowmercy.common;

import ladysnake.snowmercy.cca.SnowMercyComponents;
import ladysnake.snowmercy.common.command.SnowMercyCommand;
import ladysnake.snowmercy.common.entity.WeaponizedSnowGolemEntity;
import ladysnake.snowmercy.common.init.SnowMercyBlocks;
import ladysnake.snowmercy.common.init.SnowMercyEntities;
import ladysnake.snowmercy.common.init.SnowMercyItems;
import ladysnake.snowmercy.common.utils.RandomSpawnCollection;
import ladysnake.snowmercy.common.utils.WorldUtils;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.LightType;

public class SnowMercy implements ModInitializer {
    public static final String MODID = "snowmercy";
    
    public static Identifier id(String path) {
        return new Identifier(MODID, path);
    }

    private static final RandomSpawnCollection<EntityType<? extends LivingEntity>> SPAWN_CANDIDATES = new RandomSpawnCollection<>();

    @Override
    public void onInitialize() {
        SnowMercyBlocks.init();
        SnowMercyItems.init();
        SnowMercyEntities.init();

        CommandRegistrationCallback.EVENT.register((commandDispatcher, b) ->
                SnowMercyCommand.register(commandDispatcher)
        );

        SPAWN_CANDIDATES
                .add(25, SnowMercyEntities.SAWMAN)
                .add(20, SnowMercyEntities.MORTARS)
                .add(20, SnowMercyEntities.ROCKETS)
                .add(5, SnowMercyEntities.SNUGGLES)
                .add(1, SnowMercyEntities.CHILL_SNUGGLES);

        ServerTickEvents.END_SERVER_TICK.register(server -> server.getWorlds().forEach(world -> {
            if (SnowMercyComponents.SNOWMERCY.get(world).isEventOngoing() && world.random.nextInt(5 * 20) == 0) {
                WorldUtils.getLoadedChunks(world).forEach(chunk -> {
                    ChunkPos pos = chunk.getPos();
                    if (world.getEntitiesByClass(WeaponizedSnowGolemEntity.class, new Box(pos.getStartPos(), pos.getStartPos().add(16, 256, 16)), e -> true).size() < 1) {
                        int randomX = world.random.nextInt(16);
                        int randomZ = world.random.nextInt(16);
                        ChunkPos chunkPos = chunk.getPos();

                        int yMax = world.getTopY(Heightmap.Type.MOTION_BLOCKING, chunkPos.getStartX() + randomX, chunkPos.getStartZ() + randomZ);
                        int y = 1;

                        if (world.random.nextBoolean()) {
                            y = yMax;
                        }

                        for (; y <= yMax; y++) {
                            BlockPos spawnPos = new BlockPos(chunkPos.getStartX() + randomX, y, chunkPos.getStartZ() + randomZ);

                            LivingEntity entity = SPAWN_CANDIDATES.next(world.getRandom()).create(world);

                            if (entity != null && !world.getBlockState(spawnPos).isSolidBlock(world, spawnPos) && world.getBlockState(spawnPos.add(0, -1, 0)).isSolidBlock(world, spawnPos.add(0, -1, 0)) && world.getLightLevel(LightType.BLOCK, spawnPos) <= 5) {
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
        }));
    }
}
