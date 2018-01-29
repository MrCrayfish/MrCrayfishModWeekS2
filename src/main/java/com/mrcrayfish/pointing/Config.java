package com.mrcrayfish.pointing;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

/**
 * Author: MrCrayfish
 */
public class Config
{
    private static Configuration config;

    private static final String CATEGORY_GENERAL = "general";
    private static boolean ballEnabled;
    private static int ballColorRed;
    private static int ballColorGreen;
    private static int ballColorBlue;

    public static void load(File file)
    {
        config = new Configuration(file);
        init();
    }

    private static void init()
    {
        ballEnabled = config.getBoolean("ballEnabled", CATEGORY_GENERAL, true, "Whether or not the pointing ball is shown");
        ballColorRed = santizeColor(config.get(CATEGORY_GENERAL, "ballColorRed", 182, "The red color of the pointing ball").getInt());
        ballColorGreen = santizeColor(config.get(CATEGORY_GENERAL, "ballColorGreen", 255, "The green color of the pointing ball").getInt());
        ballColorBlue = santizeColor(config.get(CATEGORY_GENERAL, "ballColorBlue", 103, "The blue color of the pointing ball").getInt());

        config.save();
    }

    private static int santizeColor(int color)
    {
        return Math.max(0, Math.min(255, color));
    }

    public static boolean isBallEnabled()
    {
        return ballEnabled;
    }

    public static int getBallColorRed()
    {
        return ballColorRed;
    }

    public static int getBallColorGreen()
    {
        return ballColorGreen;
    }

    public static int getBallColorBlue()
    {
        return ballColorBlue;
    }
}
