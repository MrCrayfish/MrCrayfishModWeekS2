package com.mrcrayfish.clock.proxy;

import com.mrcrayfish.clock.tileentity.TileEntityDigitalClock;
import com.mrcrayfish.clock.tileentity.render.DigitalClockRenderer;
import net.minecraftforge.fml.client.registry.ClientRegistry;

/**
 * Author: MrCrayfish
 */
public class ClientProxy implements Proxy
{
    @Override
    public void preInit()
    {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDigitalClock.class, new DigitalClockRenderer());
    }
}
