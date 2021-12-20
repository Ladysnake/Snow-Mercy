package ladysnake.snowmercy.common.entity;

import ladysnake.snowmercy.mixin.FoxEntityInvoker;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.world.World;

public class TundrabidEntity extends FoxEntity {
    public TundrabidEntity(EntityType<? extends FoxEntity> entityType, World world) {
        super(entityType, world);

        ((FoxEntityInvoker) this).invokeSetType(Type.SNOW);
    }

    @Override
    public boolean canFreeze() {
        return false;
    }

    @Override
    public boolean isFreezing() {
        return false;
    }

    @Override
    protected void addPowderSnowSlowIfNeeded() {
    }

    @Override
    public boolean isPersistent() {
        return true;
    }

}
