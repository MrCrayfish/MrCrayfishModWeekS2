package com.mrcrayfish.school.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

/**
 * Author: MrCrayfish
 */
public class BlockDesk extends BlockFurniture
{
    public BlockDesk()
    {
        super(Material.WOOD);
        this.setUnlocalizedName("desk");
        this.setRegistryName("desk");
    }
}
