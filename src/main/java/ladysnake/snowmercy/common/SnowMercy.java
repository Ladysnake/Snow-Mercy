package ladysnake.snowmercy.common;

import ladysnake.snowmercy.common.init.*;
import ladysnake.snowmercy.common.utils.RandomSpawnCollection;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.dimension.v1.FabricDimensions;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;
import software.bernie.geckolib3.GeckoLib;

import java.util.ArrayList;
import java.util.List;

public class SnowMercy implements ModInitializer {
    public static final String MODID = "snowmercy";
    public static final Identifier WINTER_MURDERLAND_ID = new Identifier(MODID, "winter_murderland");
    public static final RegistryKey<World> WINTER_MURDERLAND = RegistryKey.of(Registry.WORLD_KEY, SnowMercy.WINTER_MURDERLAND_ID);
    private static final RandomSpawnCollection<EntityType<? extends LivingEntity>> SPAWN_CANDIDATES = new RandomSpawnCollection<>();

    public static Identifier id(String path) {
        return new Identifier(MODID, path);
    }

    @Override
    public void onInitialize() {
        GeckoLib.initialize();

        SnowMercyBlocks.init();
        SnowMercyItems.init();
        SnowMercyEntities.init();
        SnowMercyFeatures.init();
        SnowMercySoundEvents.init();
        SnowMercyWaves.init();

        // spawn sculk catalysts around players
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            if (server.getTicks() % 20 == 0) {
                ServerWorld overworld = server.getOverworld();
                List<PlayerEntity> playersToTp = new ArrayList<>();

                for (ServerPlayerEntity player : overworld.getPlayers()) {
                    BlockPos playerPos = player.getBlockPos();
                    if (player.getFrozenTicks() > 60 && overworld.getBlockState(playerPos).getBlock() == Blocks.POWDER_SNOW
                            && overworld.getBlockState(playerPos.add(0, 1, 0)).getBlock() == Blocks.POWDER_SNOW
                            && overworld.getBlockState(playerPos.add(0, 2, 0)).getBlock() == Blocks.POWDER_SNOW) {
                        boolean cancel = false;

                        for (int x = -1; x <= 1 && !cancel; x += 2) {
                            for (int y = 0; y < 2 && !cancel; y++) {
                                for (int z = -1; z <= 1 && !cancel; z += 2) {
                                    if (overworld.getBlockState(playerPos.add(x, y, z)).getBlock() != Blocks.BLUE_ICE) {
                                        cancel = true;
                                    }
                                }
                            }
                        }

                        if (!cancel) {
                            playersToTp.add(player);
                        }
                    }
                }

                for (PlayerEntity player : playersToTp) {
                    if (!player.hasVehicle() && !player.hasPassengers() && player.canUsePortals()) {
                        ServerWorld winterMurderland = server.getWorld(WINTER_MURDERLAND);
                        if (winterMurderland == null) {
                            return;
                        }
                        player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 10 * 20, 5));
                        FabricDimensions.teleport(player, winterMurderland, new TeleportTarget(new Vec3d(player.getX(), 500, player.getZ()), Vec3d.ZERO, player.getYaw(), 90));
                    }
                }
            }
        });
    }
}
