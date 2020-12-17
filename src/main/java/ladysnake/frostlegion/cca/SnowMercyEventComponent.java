package ladysnake.frostlegion.cca;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.ServerTickingComponent;
import net.minecraft.nbt.CompoundTag;

public class SnowMercyEventComponent implements BooleanComponent, AutoSyncedComponent {
    private boolean isEventOngoing;

    @Override
    public boolean isEventOngoing() {
        return this.isEventOngoing;
    }

    public void setEventOngoing(boolean bool) {
        this.isEventOngoing = bool;
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
