package com.mrcrayfish.school.block;

import com.mrcrayfish.school.Bounds;
import com.mrcrayfish.school.tileentity.TileEntityGlobe;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Author: MrCrayfish
 */
public class BlockGlobe extends BlockFurniture
{
    private static final AxisAlignedBB SELECTION_BOX = new Bounds(2, 0, 2, 14, 14, 14).toAABB();
    private static final AxisAlignedBB COLLISION_BOX = new Bounds(3, 0, 3, 13, 13, 13).toAABB();

    public BlockGlobe()
    {
        super(Material.WOOD);
        this.setUnlocalizedName("globe");
        this.setRegistryName("globe");
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return SELECTION_BOX;
    }

    @Override
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, Entity entityIn, boolean p_185477_7_)
    {
        Block.addCollisionBoxToList(pos, entityBox, collidingBoxes, COLLISION_BOX);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if(tileEntity instanceof TileEntityGlobe)
        {
            ((TileEntityGlobe) tileEntity).spin();
        }
        return true;
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
        return new TileEntityGlobe();
    }
}
