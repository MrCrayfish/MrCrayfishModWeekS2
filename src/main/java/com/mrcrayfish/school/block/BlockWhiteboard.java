package com.mrcrayfish.school.block;

import com.mrcrayfish.school.Bounds;
import com.mrcrayfish.school.SchoolMod;
import com.mrcrayfish.school.gui.GuiWhiteboard;
import com.mrcrayfish.school.tileentity.TileEntityWhiteboard;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Author: MrCrayfish
 */
public class BlockWhiteboard extends BlockFurniture
{
    private static final AxisAlignedBB[] SELECTION_BOX = new Bounds(14, 0, 0, 16, 16, 16).getRotatedBounds();
    private static final AxisAlignedBB[] COLLISION = new Bounds(15, 0, 0, 16, 16, 16).getRotatedBounds();

    public BlockWhiteboard()
    {
        super(Material.WOOD);
        this.setUnlocalizedName("whiteboard");
        this.setRegistryName("whiteboard");
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return SELECTION_BOX[state.getValue(FACING).getHorizontalIndex()];
    }

    @Override
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, Entity entityIn, boolean p_185477_7_)
    {
        Block.addCollisionBoxToList(pos, entityBox, collidingBoxes, COLLISION[state.getValue(FACING).getHorizontalIndex()]);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        if(placer instanceof EntityPlayer)
        {
            ((EntityPlayer) placer).openGui(SchoolMod.instance, GuiWhiteboard.ID, worldIn, pos.getX(), pos.getY(), pos.getZ());
        }
    }

    @Override
    public boolean hasTileEntity(IBlockState state)
    {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state)
    {
        return new TileEntityWhiteboard();
    }
}
