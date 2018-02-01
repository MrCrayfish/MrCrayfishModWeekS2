package com.mrcrayfish.soccer.client.render;

import com.mrcrayfish.soccer.Reference;
import com.mrcrayfish.soccer.entity.EntitySoccerBall;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.util.glu.Sphere;

import javax.annotation.Nullable;

/**
 * Author: MrCrayfish
 */
public class RenderSoccerBall extends Render<EntitySoccerBall>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/entity/soccer_ball.png");
    private static final Sphere BALL = new Sphere();

    public RenderSoccerBall(RenderManager renderManager)
    {
        super(renderManager);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntitySoccerBall entity)
    {
        return null;
    }

    @Override
    public void doRender(EntitySoccerBall entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z);
        GlStateManager.translate(0, EntitySoccerBall.SIZE / 2, 0);
        float roll = (float) (entity.prevRoll + (entity.currentRoll - entity.prevRoll) * partialTicks);
        GlStateManager.rotate(-entityYaw, 0, 1, 0);
        GlStateManager.rotate(roll, 1, 0, 0);
        Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
        BALL.draw(EntitySoccerBall.SIZE / 2, 20, 20);
        BALL.setTextureFlag(true);
        GlStateManager.popMatrix();
    }
}
