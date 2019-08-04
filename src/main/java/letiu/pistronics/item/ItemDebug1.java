package letiu.pistronics.item;

import letiu.pistronics.Pistronics;
import letiu.pistronics.block.PistronicsBlocks;
import letiu.pistronics.entity.BlockGroupEntity;
import letiu.pistronics.piston.BlockData;
import letiu.pistronics.util.BlockPosUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;

public class ItemDebug1 extends Item {

    public static final String REGISTRY_NAME = "debug_1";

    public ItemDebug1() {
        setTranslationKey(Pistronics.MOD_ID + "." + REGISTRY_NAME);
        setRegistryName(REGISTRY_NAME);
        setCreativeTab(Pistronics.PistronicsTab);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

        if (!worldIn.isRemote) {

            System.out.println("using Debug-Item-1");

            Minecraft.getMinecraft().player.sendChatMessage("using Debug-Item-1");

            ArrayList<BlockData> blockDataList = new ArrayList<>();

            for (int x = 0; x < 2; x++) {
                for (int z = 0; z < 2; z++) {
                    BlockPos offset = new BlockPos(x, 0, z);
                    blockDataList.add(new BlockData(worldIn.getBlockState(BlockPosUtil.add(pos, offset)), offset));
                }
            }

            BlockGroupEntity bg_entity = new BlockGroupEntity(worldIn, pos.getX(), pos.getY() + 2D, pos.getZ(), blockDataList);
            //bg_entity.setPosition(pos.getX(), pos.getY() + 1D, pos.getZ());
            worldIn.spawnEntity(bg_entity);
        }

        return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
    }
}
