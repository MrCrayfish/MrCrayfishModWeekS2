package com.mrcrayfish.soccer.entity;

import com.mrcrayfish.soccer.init.ModSounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Author: MrCrayfish
 */
public class EntitySoccerBall extends Entity
{
    private static final DataParameter<Float> SPEED = EntityDataManager.createKey(EntityBoat.class, DataSerializers.FLOAT);

    public static final float SIZE = 0.75F;

    @SideOnly(Side.CLIENT)
    public double currentRoll;
    @SideOnly(Side.CLIENT)
    public double prevRoll;

    public EntitySoccerBall(World worldIn)
    {
        super(worldIn);
        this.setSize(SIZE, SIZE);
    }

    @Override
    public boolean canBeCollidedWith()
    {
        return true;
    }

    @Override
    public void onCollideWithPlayer(EntityPlayer entityIn)
    {
        if(!this.world.isRemote && getEntityBoundingBox().intersects(entityIn.getEntityBoundingBox()))
        {
            this.rotationYaw = entityIn.rotationYaw;
            this.motionX = entityIn.getLookVec().normalize().x * (entityIn.getAIMoveSpeed() * 10);
            this.motionZ = entityIn.getLookVec().normalize().z * (entityIn.getAIMoveSpeed() * 10);
            this.setSpeed(1);
            this.world.playSound(null, posX, posY, posZ, ModSounds.KICK_BALL, SoundCategory.BLOCKS, 1.0F, 1.0F);
        }
    }

    @Override
    public void onEntityUpdate()
    {
        super.onEntityUpdate();
        double currentSpeed = this.getSpeed();

        if(this.world.isRemote)
        {
            this.prevRoll = currentRoll;
            this.currentRoll += 90F * currentSpeed;
        }

        this.motionY -= 0.05;
        this.move(MoverType.SELF, motionX * currentSpeed, motionY, motionZ * currentSpeed);
        this.setSpeed(currentSpeed * 0.9);
    }

    @Override
    protected void entityInit()
    {
        this.dataManager.register(SPEED, 0F);
    }

    public void setSpeed(double speed)
    {
        this.dataManager.set(SPEED, (float) speed);
    }

    public double getSpeed()
    {
        return this.dataManager.get(SPEED).doubleValue();
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound)
    {

    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound)
    {

    }
}
