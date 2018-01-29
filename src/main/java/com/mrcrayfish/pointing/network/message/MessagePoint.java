package com.mrcrayfish.pointing.network.message;

import com.mrcrayfish.pointing.network.PacketHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Author: MrCrayfish
 */
public class MessagePoint implements IMessage, IMessageHandler<MessagePoint, IMessage>
{
    private boolean pointing;

    public MessagePoint() {}

    public MessagePoint(boolean pointing)
    {
        this.pointing = pointing;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeBoolean(pointing);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.pointing = buf.readBoolean();
    }

    @Override
    public IMessage onMessage(MessagePoint message, MessageContext ctx)
    {
        EntityPlayer player = ctx.getServerHandler().player;
        PacketHandler.INSTANCE.sendToAll(new MessageUpdate(player.getEntityId(), message.pointing));
        return null;
    }
}
