package com.mrcrayfish.school.tileentity.render;

import com.mrcrayfish.school.Reference;
import com.mrcrayfish.school.tileentity.TileEntityGlobe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.util.glu.Sphere;

/**
 * Author: MrCrayfish
 */
public class GlobeRenderer extends TileEntitySpecialRenderer<TileEntityGlobe>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/blocks/globe.png");

    private static final Sphere BALL = new Sphere();

    static
    {
        BALL.setTextureFlag(true);
    }

    @Override
    public void render(TileEntityGlobe te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z);
        GlStateManager.translate(0.5, 0.39, 0.5);
        GlStateManager.rotate(90F, 1, 0, 0);
        float rotation = te.lastRotation + (te.rotation - te.lastRotation) * partialTicks;
        GlStateManager.rotate(rotation, 0, 0, 1);
        Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
        BALL.draw(0.275F, 40, 40);
        GlStateManager.popMatrix();
    }
}
