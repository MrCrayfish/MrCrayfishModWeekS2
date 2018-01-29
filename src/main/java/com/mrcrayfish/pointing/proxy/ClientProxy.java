package com.mrcrayfish.pointing.proxy;

import com.mrcrayfish.pointing.client.KeyBinds;
import com.mrcrayfish.pointing.client.PointingEvents;
import net.minecraftforge.common.MinecraftForge;

/**
 * Author: MrCrayfish
 */
public class ClientProxy implements Proxy
{
    @Override
    public void preInit()
    {
        MinecraftForge.EVENT_BUS.register(new PointingEvents());
        KeyBinds.register();
    }
}
