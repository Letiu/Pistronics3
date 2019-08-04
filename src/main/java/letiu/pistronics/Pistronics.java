package letiu.pistronics;

import letiu.pistronics.proxy.CommonProxy;
import letiu.pistronics.tab.PistronicsTab;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.Logger;

@Mod(
        modid = Pistronics.MOD_ID,
        name = Pistronics.MOD_NAME,
        version = Pistronics.VERSION,
        acceptedMinecraftVersions = Pistronics.MC_VERSION
)
public class Pistronics {

    public static final String MOD_ID = "pistronics";
    public static final String MOD_NAME = "Pistronics 3";
    public static final String VERSION = "3.0.0";
    public static final String MC_VERSION = "[1.12.2]";

    public static Logger logger;

    public static final String CLIENT = "letiu.pistronics.proxy.ClientProxy";
    public static final String SERVER = "letiu.pistronics.proxy.ServerProxy";

    @SidedProxy(clientSide = Pistronics.CLIENT, serverSide = Pistronics.SERVER)
    public static CommonProxy proxy;

    public static final CreativeTabs PistronicsTab = new PistronicsTab("tabPistronics");

    @Mod.Instance(MOD_ID)
    public static Pistronics INSTANCE;

    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
}
