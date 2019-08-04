package letiu.pistronics.network;

import io.netty.buffer.ByteBuf;
import letiu.pistronics.entity.BlockGroupEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageBlockGroupSync implements IMessage {

    private int entityID;
    private NBTTagCompound compound;

    public MessageBlockGroupSync() {}

    public MessageBlockGroupSync(BlockGroupEntity entity) {
        this.entityID = entity.getEntityId();
        this.compound = entity.writeToNBT(new NBTTagCompound());
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.entityID = buf.readInt();
        this.compound = ByteBufUtils.readTag(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(entityID);
        ByteBufUtils.writeTag(buf, compound);
    }

    public static class MessageHandler implements IMessageHandler<MessageBlockGroupSync, IMessage> {

        @Override
        public IMessage onMessage(MessageBlockGroupSync message, MessageContext ctx) {

            if (ctx.side.isClient()) {
                Entity entity = Minecraft.getMinecraft().world.getEntityByID(message.entityID);
                if (entity != null && entity instanceof BlockGroupEntity && message.compound != null) {

                    BlockGroupEntity bg_entity = (BlockGroupEntity) entity;
                    bg_entity.readFromNBT(message.compound);
                    bg_entity.setSynced(true);
                }
                else {
                    // TODO: Handle this
                    System.out.println("Could not find Entity for syncing");
                }
            }

            return null;
        }
    }
}
