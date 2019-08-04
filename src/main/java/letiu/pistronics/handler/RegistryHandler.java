package letiu.pistronics.handler;

import letiu.pistronics.block.BlockStopper;
import letiu.pistronics.block.PistronicsBlocks;
import letiu.pistronics.item.ItemBookOfGears;
import letiu.pistronics.item.ItemDebug1;
import letiu.pistronics.item.ItemDebug2;
import letiu.pistronics.item.ItemDebug3;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class RegistryHandler {

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        final Block[] blocks = {
                new BlockStopper()
        };

        event.getRegistry().registerAll(blocks);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {

        final Item[] items = {
                new ItemDebug1(),
                new ItemDebug2(),
                new ItemDebug3(),
                new ItemBookOfGears()
        };

        final Item[] itemBlocks = {
                new ItemBlock(PistronicsBlocks.STOPPER).setRegistryName(PistronicsBlocks.STOPPER.getRegistryName())
        };

        event.getRegistry().registerAll(items);
        event.getRegistry().registerAll(itemBlocks);
    }
}
