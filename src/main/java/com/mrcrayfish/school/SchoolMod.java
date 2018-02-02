package com.mrcrayfish.school;

import com.mrcrayfish.school.entity.EntitySeat;
import com.mrcrayfish.school.gui.GuiHandler;
import com.mrcrayfish.school.init.ModBlocks;
import com.mrcrayfish.school.init.RegistrationHandler;
import com.mrcrayfish.school.network.PacketHandler;
import com.mrcrayfish.school.proxy.Proxy;
import com.mrcrayfish.school.tileentity.TileEntityGlobe;
import com.mrcrayfish.school.tileentity.TileEntityWhiteboard;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Author: MrCrayfish
 */
@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION, acceptedMinecraftVersions = Reference.MOD_COMPATIBILITY)
public class SchoolMod
{
    @Mod.Instance(Reference.MOD_ID)
    public static SchoolMod instance;

    @SidedProxy(clientSide = Reference.PROXY_CLIENT, serverSide = Reference.PROXY_SERVER)
    public static Proxy proxy;

    public static final CreativeTabs TAB_SCHOOL = new CreativeTabs("tabSchool")
    {
        @Override
        public ItemStack getTabIconItem()
        {
            return new ItemStack(ModBlocks.DESK);
        }
    };

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event)
    {
        RegistrationHandler.init();
        PacketHandler.init();

        GameRegistry.registerTileEntity(TileEntityGlobe.class, Reference.MOD_ID + ":globe");
        GameRegistry.registerTileEntity(TileEntityWhiteboard.class, Reference.MOD_ID + ":whiteboard");

        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, "seat"), EntitySeat.class, "seat", 0, this, 80, 1, false);
        proxy.preInit();
    }
}
