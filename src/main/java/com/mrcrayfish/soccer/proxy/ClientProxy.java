package com.mrcrayfish.soccer.proxy;

import com.mrcrayfish.soccer.client.render.RenderSoccerBall;
import com.mrcrayfish.soccer.entity.EntitySoccerBall;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

/**
 * Author: MrCrayfish
 */
public class ClientProxy implements Proxy
{
    @Override
    public void preInit()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntitySoccerBall.class, RenderSoccerBall::new);
    }
}
