package letiu.pistronics.handler;

import letiu.pistronics.Pistronics;
import letiu.pistronics.network.MessageBlockGroupSync;
import letiu.pistronics.network.MessageBlockGroupSyncRequest;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler {

    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Pistronics.MOD_ID);
    private static int id = 0;

    public static void init() {
        INSTANCE.registerMessage(MessageBlockGroupSync.MessageHandler.class, MessageBlockGroupSync.class, id++, Side.CLIENT);
        INSTANCE.registerMessage(MessageBlockGroupSyncRequest.MessageHandler.class, MessageBlockGroupSyncRequest.class, id++, Side.SERVER);
    }
}
