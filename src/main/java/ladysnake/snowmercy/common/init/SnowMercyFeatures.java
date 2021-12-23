package ladysnake.snowmercy.common.init;

import ladysnake.snowmercy.common.SnowMercy;
import ladysnake.snowmercy.common.structure.IcepostFeature;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.ArrayList;
import java.util.List;

public final class SnowMercyFeatures {
    public static final List<RegistryKey<Biome>> PILLAR_BIOMES = new ArrayList<>();
    public static final StructureFeature<DefaultFeatureConfig> ICEPOST = new IcepostFeature(DefaultFeatureConfig.CODEC);
    public static final ConfiguredStructureFeature<?, ?> CONFIGURED_ICEPOST = ICEPOST.configure(DefaultFeatureConfig.INSTANCE);

    public static void init() {
        PILLAR_BIOMES.add(BiomeKeys.SNOWY_PLAINS);
        PILLAR_BIOMES.add(BiomeKeys.ICE_SPIKES);
        PILLAR_BIOMES.add(BiomeKeys.SNOWY_TAIGA);
        PILLAR_BIOMES.add(BiomeKeys.GROVE);
        PILLAR_BIOMES.add(BiomeKeys.SNOWY_SLOPES);
        PILLAR_BIOMES.add(BiomeKeys.FROZEN_PEAKS);

        FabricStructureBuilder.create(new Identifier(SnowMercy.MODID, "icepost"), ICEPOST)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(new StructureConfig(20, 5, 556312282))
                .register();
        Registry.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, new Identifier(SnowMercy.MODID, "configured_icepost"), CONFIGURED_ICEPOST);
        BiomeModifications.create(new Identifier(SnowMercy.MODID, "icepost_addition"))
                .add(
                        ModificationPhase.ADDITIONS,
                        BiomeSelectors.includeByKey(PILLAR_BIOMES),
                        context -> context.getGenerationSettings().addBuiltInStructure(CONFIGURED_ICEPOST)
                );
    }
}
