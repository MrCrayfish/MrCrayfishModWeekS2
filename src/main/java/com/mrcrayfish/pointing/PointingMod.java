package com.mrcrayfish.pointing;

import com.mrcrayfish.pointing.network.PacketHandler;
import com.mrcrayfish.pointing.proxy.Proxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Author: MrCrayfish
 */
@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION, acceptedMinecraftVersions = Reference.MOD_COMPATIBILITY)
public class PointingMod
{
    @SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.SERVER_PROXY)
    public static Proxy proxy;

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event)
    {
        proxy.preInit(event);

        PacketHandler.init();

        //SHow ball in first person
        //Render ball if player not in view
        //Make it so you cn hit entities
        //Fix laggy positioning
        //Smooth changed looking position
    }
}
