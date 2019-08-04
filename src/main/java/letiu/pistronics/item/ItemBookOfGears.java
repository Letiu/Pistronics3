package letiu.pistronics.item;

import letiu.pistronics.Pistronics;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemBookOfGears extends Item {

    public static final String REGISTRY_NAME = "book_of_gears";

    public ItemBookOfGears() {
        setTranslationKey(Pistronics.MOD_ID + "." + REGISTRY_NAME);
        setRegistryName(REGISTRY_NAME);
        setCreativeTab(Pistronics.PistronicsTab);
    }
}
