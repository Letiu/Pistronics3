package letiu.pistronics.tab;

import letiu.pistronics.Pistronics;
import letiu.pistronics.item.PistronicsItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PistronicsTab extends CreativeTabs {

    public PistronicsTab(String translationKey) {
        super(Pistronics.MOD_ID + "." + translationKey);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ItemStack createIcon() {
        return new ItemStack(PistronicsItems.BOOK_OF_GEARS);
    }
}
