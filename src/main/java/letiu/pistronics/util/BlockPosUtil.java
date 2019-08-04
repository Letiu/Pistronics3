package letiu.pistronics.util;

import net.minecraft.util.math.BlockPos;

public class BlockPosUtil {

    public static BlockPos add(BlockPos blockPosA, BlockPos blockPosB) {
        return blockPosA.add(blockPosB.getX(), blockPosB.getY(), blockPosB.getZ());
    }
}
