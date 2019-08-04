package letiu.pistronics.block;

import letiu.pistronics.Pistronics;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockStopper extends Block {

    public static final String REGISTRY_NAME = "stopper";

    public BlockStopper() {
        super(Material.ROCK);
        setTranslationKey(Pistronics.MOD_ID + "." + REGISTRY_NAME);
        setRegistryName(REGISTRY_NAME);
        setCreativeTab(Pistronics.PistronicsTab);
        setSoundType(SoundType.STONE);
    }

}
