package ladysnake.snowmercy.cca;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import static net.minecraft.text.Style.EMPTY;

public class SnowMercyEventComponent implements AutoSyncedComponent {
    private boolean isEventOngoing;
    private World world;

    public boolean isEventOngoing() {
        return this.isEventOngoing;
    }

    public void startEvent(World world) {
        if (!this.isEventOngoing) {
            this.isEventOngoing = true;
            this.world = world;
            world.getPlayers().forEach(serverPlayerEntity -> serverPlayerEntity.sendMessage(
                    new TranslatableText("info.snowmercy.start", world.getRegistryKey().getValue().getPath()).setStyle(EMPTY.withColor(Formatting.AQUA)), false)
            );
        }
    }

    public void stopEvent(World world) {
        if (this.isEventOngoing) {
            this.isEventOngoing = false;
            this.world = world;
            world.getPlayers().forEach(serverPlayerEntity -> serverPlayerEntity.sendMessage(
                    new TranslatableText("info.snowmercy.stop", world.getRegistryKey().getValue().getPath()).setStyle(EMPTY.withColor(Formatting.AQUA)), false)
            );
        }
    }

    @Override
    public void readFromNbt(CompoundTag compoundTag) {
        this.isEventOngoing = compoundTag.getBoolean("SnowMercy");
    }

    @Override
    public void writeToNbt(CompoundTag compoundTag) {
        compoundTag.putBoolean("SnowMercy", this.isEventOngoing);
    }
}
