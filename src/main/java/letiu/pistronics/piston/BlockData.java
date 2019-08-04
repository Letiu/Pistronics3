package letiu.pistronics.piston;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.math.BlockPos;

public class BlockData {

    private IBlockState blockState;
    private BlockPos blockPos;

    public BlockData(IBlockState blockState, BlockPos blockPos) {
        this.blockState = blockState;
        this.blockPos = blockPos;
    }

    public BlockData(NBTTagCompound compound) {
        this.readEntityFromNBT(compound);
    }

    public IBlockState getBlockState() {
        return this.blockState;
    }

    public BlockPos getBlockPos() {
        return this.blockPos;
    }

    public void readEntityFromNBT(NBTTagCompound compound) {
        NBTTagCompound nbtBlockState = compound.getCompoundTag("blockState");
        if (nbtBlockState != null) {
            blockState = NBTUtil.readBlockState(nbtBlockState);
        }

        NBTTagCompound nbtBlockPos = compound.getCompoundTag("blockPos");
        if (nbtBlockPos != null) {
            blockPos = NBTUtil.getPosFromTag(nbtBlockPos);
        }
    }

    public void writeEntityToNBT(NBTTagCompound compound) {
        NBTTagCompound nbtBlockState = new NBTTagCompound();
        NBTUtil.writeBlockState(nbtBlockState, blockState);
        compound.setTag("blockState", nbtBlockState);

        NBTTagCompound nbtBlockPos = NBTUtil.createPosTag(blockPos);
        compound.setTag("blockPos", nbtBlockPos);
    }
}
