package com.mrcrayfish.school.block;

import com.mrcrayfish.school.Bounds;
import com.mrcrayfish.school.SeatUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;

/**
 * Author: MrCrayfish
 */
public class BlockChair extends BlockFurniture
{
    private static final AxisAlignedBB SELECTION_BOX = new Bounds(1, 0, 1, 15, 22, 15).toAABB();
    private static final AxisAlignedBB COLLISION_SEAT = new Bounds(2, 0, 2, 14, 10, 14).toAABB();
    private static final AxisAlignedBB[] COLLISION_BACK_REST = new Bounds(12, 10, 2, 14, 22, 14).getRotatedBounds();

    public BlockChair()
    {
        super(Material.WOOD);
        this.setUnlocalizedName("chair");
        this.setRegistryName("chair");
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return SELECTION_BOX;
    }

    @Override
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, Entity entityIn, boolean p_185477_7_)
    {
        EnumFacing facing = state.getValue(FACING);
        Block.addCollisionBoxToList(pos, entityBox, collidingBoxes, COLLISION_SEAT);
        Block.addCollisionBoxToList(pos, entityBox, collidingBoxes, COLLISION_BACK_REST[facing.getHorizontalIndex()]);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if(!worldIn.isRemote)
        {
            SeatUtil.createSeatAndSit(worldIn, pos, playerIn, 0.4);
        }
        return true;
    }
}
