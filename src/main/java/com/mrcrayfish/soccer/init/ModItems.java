package com.mrcrayfish.soccer.init;

import com.mrcrayfish.soccer.item.ItemSoccerBall;
import net.minecraft.item.Item;

/**
 * Author: MrCrayfish
 */
public class ModItems
{
    public static final Item SOCCER_BALL;

    static
    {
        SOCCER_BALL = new ItemSoccerBall();
    }

    static void register()
    {
        RegistrationHandler.Items.add(SOCCER_BALL);
    }
}
