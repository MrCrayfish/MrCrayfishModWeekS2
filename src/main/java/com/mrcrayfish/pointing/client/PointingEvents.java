package com.mrcrayfish.pointing.client;

import com.mrcrayfish.pointing.Config;
import com.mrcrayfish.pointing.client.model.ModelPlayerOverride;
import com.mrcrayfish.pointing.network.PacketHandler;
import com.mrcrayfish.pointing.network.message.MessagePoint;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.util.glu.Sphere;

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

    private static final Sphere SPHERE = new Sphere();

    @SubscribeEvent
    public void onPlayerPreRender(RenderLivingEvent.Pre event)
    {
        if(!Config.isBallEnabled())
            return;

        Entity entityIn = event.getEntity();
        if(entityIn instanceof EntityPlayer && entityIn.getEntityData().getBoolean("pointing"))
        {
            RayTraceResult rayTraceResult = entityIn.rayTrace(50.0, event.getPartialRenderTick());
            if(rayTraceResult != null)
            {
                double prevPointX = entityIn.getEntityData().getDouble("prevPointX");
                double prevPointY = entityIn.getEntityData().getDouble("prevPointY");
                double prevPointZ = entityIn.getEntityData().getDouble("prevPointZ");

                GlStateManager.pushMatrix();
                {
                    Entity viewEntity = Minecraft.getMinecraft().getRenderViewEntity();
                    double viewEntityX = viewEntity.prevPosX + (viewEntity.posX - viewEntity.prevPosX) * event.getPartialRenderTick();
                    double viewEntityY = viewEntity.prevPosY + (viewEntity.posY - viewEntity.prevPosY) * event.getPartialRenderTick();
                    double viewEntityZ = viewEntity.prevPosZ + (viewEntity.posZ - viewEntity.prevPosZ) * event.getPartialRenderTick();

                    double pointX = prevPointX + (rayTraceResult.hitVec.x - prevPointX) * event.getPartialRenderTick();
                    double pointY = prevPointY + (rayTraceResult.hitVec.y - prevPointY) * event.getPartialRenderTick();
                    double pointZ = prevPointZ + (rayTraceResult.hitVec.z - prevPointZ) * event.getPartialRenderTick();

                    //double lookingPointX = (pointX - viewEntityX)

                    GlStateManager.disableAlpha();
                    GlStateManager.disableBlend();
                    GlStateManager.translate(pointX - viewEntityX, pointY - viewEntityY, pointZ - viewEntityZ);
                    GlStateManager.disableLighting();
                    GlStateManager.disableTexture2D();
                    GlStateManager.color(Config.getBallColorRed() / 255F, Config.getBallColorGreen() / 255F, Config.getBallColorBlue() / 255F);
                    SPHERE.draw(0.25F, 20, 20);

                    GlStateManager.enableLighting();
                    GlStateManager.enableAlpha();
                    GlStateManager.enableBlend();

                    GlStateManager.color(1.0F, 1.0F, 1.0F);
                    RenderManager manager = Minecraft.getMinecraft().getRenderManager();
                    float viewX = manager.playerViewY;
                    float viewY = manager.playerViewX;
                    boolean thirdPerson = manager.options.thirdPersonView == 2;
                    EntityRenderer.drawNameplate(Minecraft.getMinecraft().fontRenderer, ((EntityPlayer) entityIn).getDisplayNameString(), 0, 0.5F, 0, 0, viewX, viewY, thirdPerson, entityIn.isSneaking());

                    GlStateManager.enableTexture2D();
                }
                GlStateManager.popMatrix();

                entityIn.getEntityData().setDouble("prevPointX", rayTraceResult.hitVec.x);
                entityIn.getEntityData().setDouble("prevPointY", rayTraceResult.hitVec.y);
                entityIn.getEntityData().setDouble("prevPointZ", rayTraceResult.hitVec.z);
            }
        }
    }
}
