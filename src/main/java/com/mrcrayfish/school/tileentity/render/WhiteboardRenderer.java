package com.mrcrayfish.school.tileentity.render;

import com.mrcrayfish.school.block.BlockFurniture;
import com.mrcrayfish.school.block.BlockWhiteboard;
import com.mrcrayfish.school.tileentity.TileEntityWhiteboard;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

import java.awt.*;

/**
 * Author: MrCrayfish
 */
public class WhiteboardRenderer extends TileEntitySpecialRenderer<TileEntityWhiteboard>
{
    @Override
    public void render(TileEntityWhiteboard te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
        if (te.getMessage() != null)
        {
            IBlockState state = getWorld().getBlockState(te.getPos());
            if (state.getBlock() instanceof BlockWhiteboard)
            {
                int rotation = state.getValue(BlockFurniture.FACING).getHorizontalIndex();
                GlStateManager.pushMatrix();
                {
                    GlStateManager.translate(x, y, z);
                    GlStateManager.translate(0.5, 0.5625F, 0.5);
                    GlStateManager.rotate(-90F * rotation, 0, 1, 0);
                    GlStateManager.rotate(180F, 0, 1, 0);
                    GlStateManager.translate(-0.375, 0, 0);
                    GlStateManager.translate(0, 0, -0.45);
                    GlStateManager.scale(1, -1, -1);
                    GlStateManager.scale(0.015625F, 0.015625F, 0.015625F);
                    int lines = getFontRenderer().listFormattedStringToWidth(te.getMessage(), 50).size();
                    GlStateManager.translate(0, -(lines * getFontRenderer().FONT_HEIGHT - 1) / 2, 0);
                    GlStateManager.enableAlpha();
                    GlStateManager.disableLighting();
                    drawSplitString(Minecraft.getMinecraft().fontRenderer, te.getMessage(), 0, 0, 50, Color.BLUE.getRGB());
                    GlStateManager.enableLighting();
                    GlStateManager.disableAlpha();
                }
                GlStateManager.popMatrix();
            }
        }
    }

    private void drawSplitString(FontRenderer renderer, String str, int x, int y, int wrapWidth, int textColor)
    {
        str = this.trimStringNewline(str);
        this.renderSplitStringCentered(renderer, str, x, y, wrapWidth, textColor);
    }

    private void renderSplitStringCentered(FontRenderer renderer, String str, int x, int y, int wrapWidth, int textColor)
    {
        java.util.List<String> lines = renderer.listFormattedStringToWidth(str, wrapWidth);
        for (int i = 0; i < lines.size() && i < 5; i++)
        {
            String line = lines.get(i);
            x = (wrapWidth + -renderer.getStringWidth(line)) / 2;
            renderer.drawString(line, x, y, textColor);
            y += renderer.FONT_HEIGHT;
        }
    }

    private String trimStringNewline(String text)
    {
        while (text != null && text.endsWith("\n"))
        {
            text = text.substring(0, text.length() - 1);
        }
        return text;
    }
}
