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

public class ItemDebug3 extends Item {

    public static final String REGISTRY_NAME = "debug_3";

    public ItemDebug3() {
        setTranslationKey(Pistronics.MOD_ID + "." + REGISTRY_NAME);
        setRegistryName(REGISTRY_NAME);
        setCreativeTab(Pistronics.PistronicsTab);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

        if (!worldIn.isRemote) {

        }

        return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
    }
}
