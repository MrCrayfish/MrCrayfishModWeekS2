package com.mrcrayfish.pointing.client;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

/**
 * Author: MrCrayfish
 */
public class KeyBinds
{
    public static final KeyBinding KEY_POINT = new KeyBinding("key.point", Keyboard.KEY_P, "key.categories.point");

    public static void register()
    {
        ClientRegistry.registerKeyBinding(KEY_POINT);
    }
}
