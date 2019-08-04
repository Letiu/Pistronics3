package letiu.pistronics.handler;

import letiu.pistronics.block.PistronicsBlocks;
import letiu.pistronics.item.PistronicsItems;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ModelRegistryHandler {

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        registerModel(PistronicsItems.DEBUG_1);
        registerModel(PistronicsItems.DEBUG_2);
        registerModel(PistronicsItems.DEBUG_3);
        registerModel(PistronicsItems.BOOK_OF_GEARS);


        registerModel(Item.getItemFromBlock(PistronicsBlocks.STOPPER));
    }

    private static void registerModel(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
}
