package com.mrcrayfish.pointing.network.message;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Author: MrCrayfish
 */
public class MessageUpdate implements IMessage, IMessageHandler<MessageUpdate, IMessage>
{
    private int playerId;
    private boolean pointing;

    public MessageUpdate()
    {
    }

    public MessageUpdate(int playerId, boolean pointing)
    {
        this.playerId = playerId;
        this.pointing = pointing;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(playerId);
        buf.writeBoolean(pointing);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.playerId = buf.readInt();
        this.pointing = buf.readBoolean();
    }

    @Override
    public IMessage onMessage(MessageUpdate message, MessageContext ctx)
    {
        Entity entity = Minecraft.getMinecraft().world.getEntityByID(message.playerId);
        if(entity instanceof EntityPlayer)
        {
            entity.getEntityData().setBoolean("pointing", message.pointing);
            entity.ignoreFrustumCheck = message.pointing;
        }
        return null;
    }
}
