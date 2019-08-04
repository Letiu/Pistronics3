package letiu.pistronics.entity;

import letiu.pistronics.Pistronics;
import letiu.pistronics.handler.PacketHandler;
import letiu.pistronics.network.MessageBlockGroupSyncRequest;
import letiu.pistronics.piston.BlockData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BlockGroupEntity extends Entity {

    public static final ResourceLocation REGISTRY_NAME = new ResourceLocation(Pistronics.MOD_ID + ":block_group_entity");

    private List<BlockData> blockDataList;
    private boolean isSynced = true;

    public BlockGroupEntity(World worldIn) {
        super(worldIn);

        this.motionX = 0.0D;
        this.motionY = 0.0D;
        this.motionZ = 0.0D;
    }

    public BlockGroupEntity(World worldIn, double x, double y, double z) {
        this(worldIn);
        this.preventEntitySpawning = true;
        this.setSize(0.98F, 0.98F);
        this.setPosition(x, y, z);
        this.prevPosX = x;
        this.prevPosY = y;
        this.prevPosZ = z;
        this.setEntityBoundingBox(new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D).offset(x, y, z));
    }

    public BlockGroupEntity(World worldIn, double x, double y, double z, List<BlockData> blockDataList) {
        this(worldIn, x, y, z);
        this.blockDataList = blockDataList;
    }

    public void setSynced(boolean isSynced) {
        this.isSynced = isSynced;
    }

    @Override
    public void setEntityId(int id) {
        super.setEntityId(id);
        this.isSynced = false;
        System.out.println("SET-EntityID: " + this.getEntityId());
    }

    @SideOnly(Side.CLIENT)
    public World getWorldObj() {
        return this.world;
    }

    public List<BlockData> getBlockDataList() {
        return this.blockDataList;
    }

    @Override
    public void onUpdate() {

        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        this.move(MoverType.SELF, 0, 0.01D, 0);

        if (isSynced == false && this.getWorldObj().isRemote) {
            PacketHandler.INSTANCE.sendToServer(new MessageBlockGroupSyncRequest(this));
            this.isSynced = true; //TODO
        }
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    @Override
    protected void entityInit() {
        System.out.println("I-EntityID: " + this.getEntityId());
        System.out.println("I-EntityUUID: " + this.getUniqueID());
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {

        if (blockDataList == null) {
            this.blockDataList = new ArrayList<>();
        }

        NBTTagList nbtBlockDataList = compound.getTagList("blockDataList", Constants.NBT.TAG_COMPOUND);

        if (nbtBlockDataList != null) {
            for (Iterator<NBTBase> it = nbtBlockDataList.iterator(); it.hasNext(); ) {
                NBTTagCompound nbtBlockData = (NBTTagCompound) it.next();
                this.blockDataList.add(new BlockData(nbtBlockData));
            }
        }
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {

        NBTTagList nbtBlockDataList = new NBTTagList();
        for (BlockData blockData : blockDataList) {
            NBTTagCompound nbtBlockData = new NBTTagCompound();
            blockData.writeEntityToNBT(nbtBlockData);
            nbtBlockDataList.appendTag(nbtBlockData);
        }

        compound.setTag("blockDataList", nbtBlockDataList);
    }

}
