package com.mrcrayfish.pointing.network;

import com.mrcrayfish.pointing.Reference;
import com.mrcrayfish.pointing.network.message.MessagePoint;
import com.mrcrayfish.pointing.network.message.MessageUpdate;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Author: MrCrayfish
 */
public class PacketHandler
{
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID);

    public static void init()
    {
        INSTANCE.registerMessage(MessagePoint.class, MessagePoint.class, 0, Side.SERVER);
        INSTANCE.registerMessage(MessageUpdate.class, MessageUpdate.class, 1, Side.CLIENT);
    }
}
