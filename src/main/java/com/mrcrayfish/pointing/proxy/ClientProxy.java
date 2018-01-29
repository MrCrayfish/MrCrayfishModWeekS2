package com.mrcrayfish.pointing.proxy;

import com.mrcrayfish.pointing.Config;
import com.mrcrayfish.pointing.client.KeyBinds;
import com.mrcrayfish.pointing.client.PointingEvents;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Author: MrCrayfish
 */
public class ClientProxy implements Proxy
{
    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        Config.load(event.getSuggestedConfigurationFile());
        MinecraftForge.EVENT_BUS.register(new PointingEvents());
        KeyBinds.register();
    }
}
