package ladysnake.snowmercy.client.sound;

import ladysnake.snowmercy.common.SnowMercy;
import ladysnake.snowmercy.common.entity.IceboomboxEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.sound.MovingSoundInstance;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;

@Environment(value = EnvType.CLIENT)
public class BoomboxMovingSoundInstance extends MovingSoundInstance {
    private final IceboomboxEntity iceboombox;
    private float distance = 0.0f;

    public BoomboxMovingSoundInstance(IceboomboxEntity iceboombox) {
        super(SnowMercy.JINGLE_BELLS, SoundCategory.HOSTILE);

        this.iceboombox = iceboombox;
        this.repeat = true;
        this.repeatDelay = 0;
        this.volume = 1.5f;
        this.x = (float) iceboombox.getX();
        this.y = (float) iceboombox.getY();
        this.z = (float) iceboombox.getZ();
    }

    @Override
    public boolean canPlay() {
        return !this.iceboombox.isSilent();
    }

    @Override
    public boolean shouldAlwaysPlay() {
        return true;
    }

    @Override
    public void tick() {
        if (this.iceboombox.isRemoved()) {
            this.setDone();
            return;
        }
        this.x = (float) this.iceboombox.getX();
        this.y = (float) this.iceboombox.getY();
        this.z = (float) this.iceboombox.getZ();
        this.distance = MathHelper.clamp(this.distance + 0.0025f, 0.0f, 1.0f);
    }
}

