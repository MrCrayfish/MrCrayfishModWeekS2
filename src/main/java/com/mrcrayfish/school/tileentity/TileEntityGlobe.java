package com.mrcrayfish.school.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Author: MrCrayfish
 */
public class TileEntityGlobe extends TileEntitySync implements ITickable
{
    private double currentSpeed;

    @SideOnly(Side.CLIENT)
    public float rotation;

    @SideOnly(Side.CLIENT)
    public float lastRotation;

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        if(compound.hasKey("speed", Constants.NBT.TAG_DOUBLE))
        {
            currentSpeed = compound.getDouble("speed");
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        return super.writeToNBT(compound);
    }

    @Override
    public NBTTagCompound writeSyncTag()
    {
        NBTTagCompound tag = new NBTTagCompound();
        tag.setDouble("speed", currentSpeed);
        return tag;
    }

    @Override
    public void update()
    {
        if(world.isRemote)
        {
            lastRotation = rotation;
            rotation -= 15F * currentSpeed;
        }
        currentSpeed *= 0.95;
    }

    public void spin()
    {
        currentSpeed = 1;
        sync();
    }
}
