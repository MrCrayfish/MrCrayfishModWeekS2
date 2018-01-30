package com.mrcrayfish.clock;

import com.mrcrayfish.clock.init.ModBlocks;
import com.mrcrayfish.clock.init.RegistrationHandler;
import com.mrcrayfish.clock.proxy.Proxy;
import com.mrcrayfish.clock.tileentity.TileEntityDigitalClock;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Author: MrCrayfish
 */
@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION, acceptedMinecraftVersions = Reference.MOD_COMPATIBILITY)
public class ClockMod
{
    @SidedProxy(clientSide = Reference.PROXY_CLIENT, serverSide = Reference.PROXY_SERVER)
    public static Proxy proxy;

    public static final CreativeTabs TAB_CLOCK = new CreativeTabs("tabClock")
    {
        @Override
        public ItemStack getTabIconItem()
        {
            return new ItemStack(ModBlocks.DIGITAL_CLOCK);
        }
    };

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        RegistrationHandler.init();
        GameRegistry.registerTileEntity(TileEntityDigitalClock.class, Reference.MOD_ID + ":digital_clock");
        proxy.preInit();
    }
}
