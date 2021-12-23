package ladysnake.snowmercy.common.init;

import java.util.ArrayList;

public class SnowMercyWaves {
    public static final ArrayList<ArrayList<WaveSpawnEntry>> WAVES = new ArrayList<>();

    public static void init() {
        WAVES.clear();
        for (int i = 1; i <= 10; i++) {
            WAVES.add(new ArrayList<>());
        }

        // 50 mobs
        WAVES.get(0).add(new WaveSpawnEntry(SnowMercyEntities.SAWMAN, 40));
        WAVES.get(0).add(new WaveSpawnEntry(SnowMercyEntities.TUNDRABID, 10));
        WAVES.get(0).add(new WaveSpawnEntry(SnowMercyEntities.ICEBALL, 5));

        // 75 mobs
        WAVES.get(1).add(new WaveSpawnEntry(SnowMercyEntities.SAWMAN, 40));
        WAVES.get(1).add(new WaveSpawnEntry(SnowMercyEntities.TUNDRABID, 15));
        WAVES.get(1).add(new WaveSpawnEntry(SnowMercyEntities.ROCKETS, 5));
        WAVES.get(1).add(new WaveSpawnEntry(SnowMercyEntities.MORTARS, 5));

        // 91 mobs
        WAVES.get(2).add(new WaveSpawnEntry(SnowMercyEntities.SAWMAN, 40));
        WAVES.get(2).add(new WaveSpawnEntry(SnowMercyEntities.TUNDRABID, 20));
        WAVES.get(2).add(new WaveSpawnEntry(SnowMercyEntities.ROCKETS, 10));
        WAVES.get(2).add(new WaveSpawnEntry(SnowMercyEntities.SNUGGLES, 10));
        WAVES.get(2).add(new WaveSpawnEntry(SnowMercyEntities.BOOMBOX, 1));
        WAVES.get(2).add(new WaveSpawnEntry(SnowMercyEntities.ICEBALL, 10));

        // 113 mobs
        WAVES.get(3).add(new WaveSpawnEntry(SnowMercyEntities.SAWMAN, 50));
        WAVES.get(3).add(new WaveSpawnEntry(SnowMercyEntities.ROCKETS, 25));
        WAVES.get(3).add(new WaveSpawnEntry(SnowMercyEntities.MORTARS, 25));
        WAVES.get(3).add(new WaveSpawnEntry(SnowMercyEntities.SNUGGLES, 10));
        WAVES.get(3).add(new WaveSpawnEntry(SnowMercyEntities.BOOMBOX, 2));

        // 1 mob, headmaster introduction
        WAVES.get(4).add(new WaveSpawnEntry(SnowMercyEntities.HEADMASTER, 1));

        // 148 mobs
        WAVES.get(5).add(new WaveSpawnEntry(SnowMercyEntities.SAWMAN, 50));
        WAVES.get(5).add(new WaveSpawnEntry(SnowMercyEntities.ROCKETS, 30));
        WAVES.get(5).add(new WaveSpawnEntry(SnowMercyEntities.MORTARS, 30));
        WAVES.get(5).add(new WaveSpawnEntry(SnowMercyEntities.SNUGGLES, 15));
        WAVES.get(5).add(new WaveSpawnEntry(SnowMercyEntities.CHILL_SNUGGLES, 5));
        WAVES.get(5).add(new WaveSpawnEntry(SnowMercyEntities.ICEBALL, 15));

        // 200 mobs
        WAVES.get(6).add(new WaveSpawnEntry(SnowMercyEntities.SAWMAN, 50));
        WAVES.get(6).add(new WaveSpawnEntry(SnowMercyEntities.TUNDRABID, 10));
        WAVES.get(6).add(new WaveSpawnEntry(SnowMercyEntities.ROCKETS, 30));
        WAVES.get(6).add(new WaveSpawnEntry(SnowMercyEntities.MORTARS, 45));
        WAVES.get(6).add(new WaveSpawnEntry(SnowMercyEntities.SNUGGLES, 20));
        WAVES.get(6).add(new WaveSpawnEntry(SnowMercyEntities.CHILL_SNUGGLES, 5));
        WAVES.get(6).add(new WaveSpawnEntry(SnowMercyEntities.ICEBALL, 20));
        WAVES.get(6).add(new WaveSpawnEntry(SnowMercyEntities.POLAR_BEARER, 15));
        WAVES.get(6).add(new WaveSpawnEntry(SnowMercyEntities.HEADMASTER, 2));

        // 150 mobs
        WAVES.get(7).add(new WaveSpawnEntry(SnowMercyEntities.CHILL_SNUGGLES, 50));
        WAVES.get(7).add(new WaveSpawnEntry(SnowMercyEntities.ICEBALL, 100));

        // 205 mobs
        WAVES.get(8).add(new WaveSpawnEntry(SnowMercyEntities.SAWMAN, 150));
        WAVES.get(8).add(new WaveSpawnEntry(SnowMercyEntities.HEADMASTER, 5));
        WAVES.get(8).add(new WaveSpawnEntry(SnowMercyEntities.SNUGGLES, 50));

        // grand finale, 390 mobs
        WAVES.get(9).add(new WaveSpawnEntry(SnowMercyEntities.SAWMAN, 100));
        WAVES.get(9).add(new WaveSpawnEntry(SnowMercyEntities.TUNDRABID, 30));
        WAVES.get(9).add(new WaveSpawnEntry(SnowMercyEntities.ROCKETS, 50));
        WAVES.get(9).add(new WaveSpawnEntry(SnowMercyEntities.MORTARS, 80));
        WAVES.get(9).add(new WaveSpawnEntry(SnowMercyEntities.SNUGGLES, 20));
        WAVES.get(9).add(new WaveSpawnEntry(SnowMercyEntities.CHILL_SNUGGLES, 20));
        WAVES.get(9).add(new WaveSpawnEntry(SnowMercyEntities.ICEBALL, 50));
        WAVES.get(9).add(new WaveSpawnEntry(SnowMercyEntities.POLAR_BEARER, 30));
        WAVES.get(9).add(new WaveSpawnEntry(SnowMercyEntities.HEADMASTER, 10));

    }
}
