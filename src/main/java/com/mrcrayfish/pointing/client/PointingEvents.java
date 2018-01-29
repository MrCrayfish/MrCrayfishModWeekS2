package com.mrcrayfish.pointing.client;

import com.mrcrayfish.pointing.client.model.ModelPlayerOverride;
import com.mrcrayfish.pointing.network.PacketHandler;
import com.mrcrayfish.pointing.network.message.MessagePoint;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

import java.util.Map;

/**
 * Author: MrCrayfish
 */
public class PointingEvents
{
    private static boolean setupPlayerRender = false;

    @SubscribeEvent
    public void onRenderHand(RenderPlayerEvent.Pre event)
    {
        if(!setupPlayerRender)
        {
            Map<String, RenderPlayer> skinMap = ObfuscationReflectionHelper.getPrivateValue(RenderManager.class, Minecraft.getMinecraft().getRenderManager(), "field_178636_l");
            if(skinMap != null)
            {
                ObfuscationReflectionHelper.setPrivateValue(RenderLivingBase.class, skinMap.get("default"), new ModelPlayerOverride(0.0F, false), "field_77045_g");
                ObfuscationReflectionHelper.setPrivateValue(RenderLivingBase.class, skinMap.get("slim"), new ModelPlayerOverride(0.0F, true), "field_77045_g");
            }
            setupPlayerRender = true;
        }
    }

    private static boolean pointing = false;

    @SubscribeEvent
    public void onKeyTyped(InputEvent.KeyInputEvent event)
    {
        if(KeyBinds.KEY_POINT.isKeyDown())
        {
            if(!pointing)
            {
                PacketHandler.INSTANCE.sendToServer(new MessagePoint(true));
                pointing = true;
            }
        }
        else
        {
            PacketHandler.INSTANCE.sendToServer(new MessagePoint(false));
            pointing = false;
        }
    }
}
