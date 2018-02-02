package com.mrcrayfish.school.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;

/**
 * Author: MrCrayfish
 */
public class TileEntityWhiteboard extends TileEntitySync
{
    private String message = null;

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
        sync();
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        if(compound.hasKey("message", Constants.NBT.TAG_STRING))
        {
            message = compound.getString("message");
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        if(message != null)
        {
            compound.setString("message", message);
        }
        return super.writeToNBT(compound);
    }

    @Override
    public NBTTagCompound writeSyncTag()
    {
        NBTTagCompound tag = new NBTTagCompound();
        if(message != null)
        {
            tag.setString("message", message);
        }
        return tag;
    }
}
