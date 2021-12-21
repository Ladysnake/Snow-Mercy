package ladysnake.snowmercy.common.init;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SnowMercySoundEvents {
    public static SoundEvent JINGLE_BELLS = new SoundEvent(new Identifier("snowmercy:music_disc.jingle_bells"));
    public static SoundEvent HEART_OF_ICE_AMBIENT = new SoundEvent(new Identifier("snowmercy:heart_of_ice_ambient"));

    public static void init() {
        Registry.register(Registry.SOUND_EVENT, JINGLE_BELLS.getId(), JINGLE_BELLS);
        Registry.register(Registry.SOUND_EVENT, HEART_OF_ICE_AMBIENT.getId(), HEART_OF_ICE_AMBIENT);

    }
}