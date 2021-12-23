package ladysnake.snowmercy.common.init;

import ladysnake.snowmercy.common.SnowMercy;
import net.minecraft.client.sound.Sound;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

public class SnowMercyBiomeKeys {
    public static final RegistryKey<Biome> NORTHERN_SNOWY_PLAINS = RegistryKey.of(Registry.BIOME_KEY, new Identifier(SnowMercy.MODID, "northern_snowy_plains"));
    public static final RegistryKey<Biome> NORTHERN_SNOWY_TAIGA = RegistryKey.of(Registry.BIOME_KEY, new Identifier(SnowMercy.MODID, "northern_snowy_taiga"));
    public static final RegistryKey<Biome> NORTHERN_SNOWY_SLOPES = RegistryKey.of(Registry.BIOME_KEY, new Identifier(SnowMercy.MODID, "northern_snowy_slopes"));
    public static final RegistryKey<Biome> NORTHERN_GROVE = RegistryKey.of(Registry.BIOME_KEY, new Identifier(SnowMercy.MODID, "northern_grove"));
    public static final RegistryKey<Biome> NORTHERN_ICE_SPIKES = RegistryKey.of(Registry.BIOME_KEY, new Identifier(SnowMercy.MODID, "northern_ice_spikes"));
    public static final RegistryKey<Biome> NORTHERN_FROZEN_PEAKS = RegistryKey.of(Registry.BIOME_KEY, new Identifier(SnowMercy.MODID, "northern_frozen_peaks"));
}
