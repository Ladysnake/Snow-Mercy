package ladysnake.snowmercy.common.structure;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import ladysnake.snowmercy.common.SnowMercy;
import net.minecraft.block.BlockState;
import net.minecraft.structure.*;
import net.minecraft.structure.pool.EmptyPoolElement;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.*;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.VerticalBlockSample;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;
import net.minecraft.world.gen.random.ChunkRandom;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Random;

public class IcepostFeature extends StructureFeature<DefaultFeatureConfig> {

    public IcepostFeature(Codec<DefaultFeatureConfig> codec) {
        super(codec, IcepostFeature::createPiecesGenerator, PostPlacementProcessor.EMPTY);
    }

    private static Optional<StructurePiecesGenerator<DefaultFeatureConfig>> createPiecesGenerator(StructureGeneratorFactory.Context<DefaultFeatureConfig> context) {
        // Turns the chunk coordinates into actual coordinates we can use. (Gets center of that chunk)
        ChunkPos chunkPos = context.chunkPos();
        int x = chunkPos.x * 16;
        int z = chunkPos.z * 16;

        if (!(context.isBiomeValid(Heightmap.Type.WORLD_SURFACE) && context.world().getBottomY() == -80 && context.world().getHeight() == 304)) {
            return Optional.empty();
        }

        BlockPos.Mutable centerPos = new BlockPos.Mutable(x, 0, z);

        StructurePoolFeatureConfig structureSettingsAndStartPool = new StructurePoolFeatureConfig(
                () -> context.registryManager().get(Registry.STRUCTURE_POOL_KEY).get(new Identifier(SnowMercy.MODID, "icepost")), 1
        );

        return Optional.of((structurePieces, ctx) -> {
            ChunkRandom chunkRandom = ctx.random();
            StructurePoolElement spawnedStructure = structureSettingsAndStartPool.getStartPool().get().getRandomElement(chunkRandom);

            if (spawnedStructure != EmptyPoolElement.INSTANCE) {
                BlockRotation rotation = Util.getRandom(BlockRotation.values(), chunkRandom);
                BlockPos startPos = chunkPos.getStartPos();
                StructureManager structureManager = context.structureManager();
                PoolStructurePiece piece = new PoolStructurePiece(
                        structureManager,
                        spawnedStructure,
                        startPos,
                        spawnedStructure.getGroundLevelDelta(),
                        rotation,
                        spawnedStructure.getBoundingBox(structureManager, startPos, rotation)
                );
                BlockBox boundingBox = piece.getBoundingBox();
                OptionalInt floorY = getFloorHeight(chunkRandom, context.chunkGenerator(), boundingBox, context.world());

                if (floorY.isEmpty()) return;

                int lowering = boundingBox.getMinY() + 2 + piece.getGroundLevelDelta();
                piece.translate(0, floorY.getAsInt() - lowering, 0);
                structurePieces.addPiece(piece);

                // Since by default, the start piece of a structure spawns with its corner at centerPos
                // and will randomly rotate around that corner, we will center the piece on centerPos instead.
                // This is so that our structure's start piece is now centered on the water check done in shouldStartAt.
                // Whatever the offset done to center the start piece, that offset is applied to all other pieces
                // so the entire structure is shifted properly to the new spot.
                Vec3i structureCenter = structurePieces.toList().pieces().get(0).getBoundingBox().getCenter();
                int xOffset = centerPos.getX() - structureCenter.getX();
                int zOffset = centerPos.getZ() - structureCenter.getZ();
                for (StructurePiece structurePiece : structurePieces.toList().pieces()) {
                    structurePiece.translate(xOffset, 0, zOffset);
                }
            }
        });
    }

    /**
     * Stolen from {@link net.minecraft.world.gen.feature.RuinedPortalFeature}
     */
    static OptionalInt getFloorHeight(Random random, ChunkGenerator chunkGenerator, BlockBox box, HeightLimitView world) {
        int maxY = MathHelper.nextBetween(random, 60, 100);

        List<BlockPos> corners = ImmutableList.of(new BlockPos(box.getMinX(), 0, box.getMinZ()), new BlockPos(box.getMaxX(), 0, box.getMinZ()), new BlockPos(box.getMinX(), 0, box.getMaxZ()), new BlockPos(box.getMaxX(), 0, box.getMaxZ()));
        List<VerticalBlockSample> cornerColumns = corners.stream().map(blockPos -> chunkGenerator.getColumnSample(blockPos.getX(), blockPos.getZ(), world)).toList();
        Heightmap.Type heightmapType = Heightmap.Type.WORLD_SURFACE_WG;

        int y;
        for (y = maxY; y > 15; --y) {
            int validCorners = 0;

            for (VerticalBlockSample cornerColumn : cornerColumns) {
                BlockState blockState = cornerColumn.getState(y);
                if (heightmapType.getBlockPredicate().test(blockState)) {
                    ++validCorners;
                }
            }

            if (validCorners >= 3) {
                validCorners = 0;

                for (VerticalBlockSample cornerColumn : cornerColumns) {
                    BlockState blockState = cornerColumn.getState(y + box.getBlockCountY() - 1);
                    if (blockState.isAir()) {
                        ++validCorners;
                        if (validCorners == 2) {
                            return OptionalInt.of(y + 1);
                        }
                    }
                }
            }
        }

        return OptionalInt.empty();
    }

    @Override
    public PostPlacementProcessor getPostProcessor() {
        return super.getPostProcessor();
    }
}
