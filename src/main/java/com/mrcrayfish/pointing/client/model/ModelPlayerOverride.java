package com.mrcrayfish.pointing.client.model;

import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.RayTraceResult;

/**
 * Author: MrCrayfish
 */
public class ModelPlayerOverride extends ModelPlayer
{
    public ModelPlayerOverride(float modelSize, boolean smallArmsIn)
    {
        super(modelSize, smallArmsIn);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
        if(entityIn.getEntityData().getBoolean("pointing"))
        {
            copyModelAngles(this.bipedHead, this.bipedRightArm);
            this.bipedRightArm.rotateAngleX += Math.toRadians(-90.0);

            //RayTraceResult rayTraceResult = entityIn.rayTrace(50.0, 0F);
        }
    }

    public static void copyModelAngles(ModelRenderer source, ModelRenderer dest)
    {
        dest.rotateAngleX = source.rotateAngleX;
        dest.rotateAngleY = source.rotateAngleY;
        dest.rotateAngleZ = source.rotateAngleZ;
    }
}
