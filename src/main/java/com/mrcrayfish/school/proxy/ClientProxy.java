package com.mrcrayfish.school.proxy;

import com.mrcrayfish.school.tileentity.TileEntityGlobe;
import com.mrcrayfish.school.tileentity.TileEntityWhiteboard;
import com.mrcrayfish.school.tileentity.render.GlobeRenderer;
import com.mrcrayfish.school.tileentity.render.WhiteboardRenderer;
import net.minecraftforge.fml.client.registry.ClientRegistry;

/**
 * Author: MrCrayfish
 */
public class ClientProxy implements Proxy
{
    @Override
    public void preInit()
    {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGlobe.class, new GlobeRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWhiteboard.class, new WhiteboardRenderer());
    }
}
