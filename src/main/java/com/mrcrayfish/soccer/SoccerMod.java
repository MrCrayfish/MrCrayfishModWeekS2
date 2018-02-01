package com.mrcrayfish.soccer;

import com.mrcrayfish.soccer.entity.EntitySoccerBall;
import com.mrcrayfish.soccer.init.RegistrationHandler;
import com.mrcrayfish.soccer.proxy.Proxy;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import java.awt.*;

/**
 * Author: MrCrayfish
 */
@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION, acceptedMinecraftVersions = Reference.MOD_COMPATIBILITY)
public class SoccerMod
{
    @SidedProxy(clientSide = Reference.PROXY_CLIENT, serverSide = Reference.PROXY_SERVER)
    public static Proxy proxy;

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event)
    {
        RegistrationHandler.init();

        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, "soccer_ball"), EntitySoccerBall.class, "soccer_ball", 0, this, 64, 1, true, Color.WHITE.getRGB(), Color.BLACK.getRGB());

        proxy.preInit();
    }
}
