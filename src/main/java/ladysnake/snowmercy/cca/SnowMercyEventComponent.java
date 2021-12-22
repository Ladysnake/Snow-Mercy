package ladysnake.snowmercy.cca;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import ladysnake.snowmercy.common.init.SnowMercyWaves;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import static net.minecraft.text.Style.EMPTY;

public class SnowMercyEventComponent implements AutoSyncedComponent {
    private boolean isEventOngoing;
    private int eventWave;
    private World world;

    public int getEventWave() {
        return this.eventWave;
    }

    public void setEventWave(int wave) {
        if (wave <= 9 && wave >= 0) {
            this.eventWave = wave;
        } else {
            SnowMercyWaves.resetWaves();
            this.eventWave = 0;
        }
    }

    public boolean isEventOngoing() {
        return this.isEventOngoing;
    }

    public void startEvent(World world) {
        if (!this.isEventOngoing) {
            this.isEventOngoing = true;
            this.world = world;
            world.getPlayers().forEach(serverPlayerEntity -> {
                serverPlayerEntity.sendMessage(
                        new TranslatableText("info.snowmercy.start", world.getRegistryKey().getValue().getPath()).setStyle(EMPTY.withColor(Formatting.AQUA)), false);
                serverPlayerEntity.playSound(SoundEvents.BLOCK_END_PORTAL_SPAWN, SoundCategory.MASTER, 1.0f, 2.0f);
            });
        }
    }

    public void stopEvent(World world) {
        if (this.isEventOngoing) {
            this.isEventOngoing = false;
            this.world = world;
            world.getPlayers().forEach(serverPlayerEntity -> {
                serverPlayerEntity.sendMessage(
                        new TranslatableText("info.snowmercy.stop", world.getRegistryKey().getValue().getPath()).setStyle(EMPTY.withColor(Formatting.AQUA)), false);
            });
        }
    }

    @Override
    public void readFromNbt(NbtCompound compoundTag) {
        this.isEventOngoing = compoundTag.getBoolean("SnowMercyOngoing");
        this.eventWave = compoundTag.getInt("SnowMercyWave");
    }

    @Override
    public void writeToNbt(NbtCompound compoundTag) {
        compoundTag.putBoolean("SnowMercyOngoing", this.isEventOngoing);
        compoundTag.putInt("SnowMercyWave", this.eventWave);
    }
}
