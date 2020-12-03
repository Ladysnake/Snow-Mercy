package ladysnake.frostlegion.common.block;

import ladysnake.frostlegion.common.world.CustomExplosion;
import net.minecraft.block.BlockState;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class SnowMineBlock extends PressurePlateBlock {
    public SnowMineBlock(Settings settings) {
        super(ActivationRule.EVERYTHING, settings);
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (!world.isClient()) {
            world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 4.0F, Explosion.DestructionType.BREAK);
        }
    }
}
