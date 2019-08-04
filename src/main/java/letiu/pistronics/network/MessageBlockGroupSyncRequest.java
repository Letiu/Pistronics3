package letiu.pistronics.network;

import io.netty.buffer.ByteBuf;
import letiu.pistronics.entity.BlockGroupEntity;
import letiu.pistronics.handler.PacketHandler;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageBlockGroupSyncRequest implements IMessage {

    private int entityID;

    public MessageBlockGroupSyncRequest() {}

    public MessageBlockGroupSyncRequest(BlockGroupEntity entity) {
        this.entityID = entity.getEntityId();
        System.out.println("R-EntityID: " + this.entityID);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.entityID = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(entityID);
    }

    public static class MessageHandler implements IMessageHandler<MessageBlockGroupSyncRequest, IMessage> {

        @Override
        public IMessage onMessage(MessageBlockGroupSyncRequest message, MessageContext ctx) {

            if (ctx.side.isServer()) {

                Entity entity = ctx.getServerHandler().player.world.getEntityByID(message.entityID);

                if (entity != null && entity instanceof BlockGroupEntity) {
                    BlockGroupEntity bg_entity = (BlockGroupEntity) entity;
                    PacketHandler.INSTANCE.sendTo(new MessageBlockGroupSync(bg_entity), ctx.getServerHandler().player);
                }
            }

            return null;
        }
    }
}
