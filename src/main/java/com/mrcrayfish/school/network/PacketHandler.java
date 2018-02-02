package com.mrcrayfish.school.network;

import com.mrcrayfish.school.Reference;
import com.mrcrayfish.school.network.message.MessageWhiteboard;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler
{
	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID);

	public static void init()
	{
		INSTANCE.registerMessage(MessageWhiteboard.class, MessageWhiteboard.class, 0, Side.SERVER);
	}
}
