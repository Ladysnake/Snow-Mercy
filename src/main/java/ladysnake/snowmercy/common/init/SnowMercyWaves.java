package ladysnake.snowmercy.common.init;

import java.util.ArrayList;

public class SnowMercyWaves {
    public static final ArrayList<ArrayList<WaveSpawnEntry>> WAVES = new ArrayList<>();

    public static void init() {
        WAVES.clear();
        for (int i = 1; i <= 10; i++) {
            WAVES.add(new ArrayList<>());
        }

        WAVES.get(0).add(new WaveSpawnEntry(SnowMercyEntities.SAWMAN, 20));
        WAVES.get(0).add(new WaveSpawnEntry(SnowMercyEntities.TUNDRABID, 5));

        WAVES.get(1).add(new WaveSpawnEntry(SnowMercyEntities.SAWMAN, 25));
        WAVES.get(1).add(new WaveSpawnEntry(SnowMercyEntities.TUNDRABID, 10));
        WAVES.get(1).add(new WaveSpawnEntry(SnowMercyEntities.ROCKETS, 5));
        WAVES.get(1).add(new WaveSpawnEntry(SnowMercyEntities.SNUGGLES, 5));
        WAVES.get(1).add(new WaveSpawnEntry(SnowMercyEntities.BOOMBOX, 3));

        WAVES.get(2).add(new WaveSpawnEntry(SnowMercyEntities.SAWMAN, 30));
        WAVES.get(2).add(new WaveSpawnEntry(SnowMercyEntities.TUNDRABID, 20));
        WAVES.get(2).add(new WaveSpawnEntry(SnowMercyEntities.ROCKETS, 10));
        WAVES.get(2).add(new WaveSpawnEntry(SnowMercyEntities.SNUGGLES, 10));
        WAVES.get(2).add(new WaveSpawnEntry(SnowMercyEntities.ICEBALL, 5));
        WAVES.get(2).add(new WaveSpawnEntry(SnowMercyEntities.BOOMBOX, 3));

        WAVES.get(3).add(new WaveSpawnEntry(SnowMercyEntities.SAWMAN, 20));
        WAVES.get(3).add(new WaveSpawnEntry(SnowMercyEntities.TUNDRABID, 10));
        WAVES.get(3).add(new WaveSpawnEntry(SnowMercyEntities.ROCKETS, 10));
        WAVES.get(3).add(new WaveSpawnEntry(SnowMercyEntities.MORTARS, 10));
        WAVES.get(3).add(new WaveSpawnEntry(SnowMercyEntities.SNUGGLES, 15));
        WAVES.get(3).add(new WaveSpawnEntry(SnowMercyEntities.ICEBALL, 10));
        WAVES.get(3).add(new WaveSpawnEntry(SnowMercyEntities.CHILL_SNUGGLES, 5));
        WAVES.get(3).add(new WaveSpawnEntry(SnowMercyEntities.BOOMBOX, 5));

        WAVES.get(4).add(new WaveSpawnEntry(SnowMercyEntities.HEADMASTER, 3));

        WAVES.get(5).add(new WaveSpawnEntry(SnowMercyEntities.SAWMAN, 40));
        WAVES.get(5).add(new WaveSpawnEntry(SnowMercyEntities.TUNDRABID, 20));
        WAVES.get(5).add(new WaveSpawnEntry(SnowMercyEntities.ROCKETS, 25));
        WAVES.get(5).add(new WaveSpawnEntry(SnowMercyEntities.MORTARS, 25));
        WAVES.get(5).add(new WaveSpawnEntry(SnowMercyEntities.SNUGGLES, 20));
        WAVES.get(5).add(new WaveSpawnEntry(SnowMercyEntities.ICEBALL, 20));
        WAVES.get(5).add(new WaveSpawnEntry(SnowMercyEntities.CHILL_SNUGGLES, 10));
        WAVES.get(5).add(new WaveSpawnEntry(SnowMercyEntities.BOOMBOX, 10));
        WAVES.get(5).add(new WaveSpawnEntry(SnowMercyEntities.HEADMASTER, 5));

        WAVES.get(6).add(new WaveSpawnEntry(SnowMercyEntities.TUNDRABID, 100));
        WAVES.get(6).add(new WaveSpawnEntry(SnowMercyEntities.POLAR_BEARER, 5));

        WAVES.get(7).add(new WaveSpawnEntry(SnowMercyEntities.SAWMAN, 60));
        WAVES.get(7).add(new WaveSpawnEntry(SnowMercyEntities.TUNDRABID, 30));
        WAVES.get(7).add(new WaveSpawnEntry(SnowMercyEntities.ROCKETS, 30));
        WAVES.get(7).add(new WaveSpawnEntry(SnowMercyEntities.MORTARS, 30));
        WAVES.get(7).add(new WaveSpawnEntry(SnowMercyEntities.SNUGGLES, 30));
        WAVES.get(7).add(new WaveSpawnEntry(SnowMercyEntities.ICEBALL, 20));
        WAVES.get(7).add(new WaveSpawnEntry(SnowMercyEntities.CHILL_SNUGGLES, 10));
        WAVES.get(7).add(new WaveSpawnEntry(SnowMercyEntities.BOOMBOX, 10));
        WAVES.get(7).add(new WaveSpawnEntry(SnowMercyEntities.HEADMASTER, 10));

        WAVES.get(8).add(new WaveSpawnEntry(SnowMercyEntities.CHILL_SNUGGLES, 50));
        WAVES.get(8).add(new WaveSpawnEntry(SnowMercyEntities.ICEBALL, 50));

        WAVES.get(9).add(new WaveSpawnEntry(SnowMercyEntities.SAWMAN, 100));
        WAVES.get(9).add(new WaveSpawnEntry(SnowMercyEntities.ROCKETS, 75));
        WAVES.get(9).add(new WaveSpawnEntry(SnowMercyEntities.MORTARS, 75));
        WAVES.get(9).add(new WaveSpawnEntry(SnowMercyEntities.SNUGGLES, 50));
        WAVES.get(9).add(new WaveSpawnEntry(SnowMercyEntities.CHILL_SNUGGLES, 25));
        WAVES.get(9).add(new WaveSpawnEntry(SnowMercyEntities.BOOMBOX, 25));
        WAVES.get(9).add(new WaveSpawnEntry(SnowMercyEntities.HEADMASTER, 20));
        WAVES.get(9).add(new WaveSpawnEntry(SnowMercyEntities.POLAR_BEARER, 25));
        WAVES.get(9).add(new WaveSpawnEntry(SnowMercyEntities.TUNDRABID, 50));
        WAVES.get(9).add(new WaveSpawnEntry(SnowMercyEntities.ICEBALL, 25));

    }
}
