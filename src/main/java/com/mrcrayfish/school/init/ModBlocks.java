package com.mrcrayfish.school.init;

import com.mrcrayfish.school.block.BlockChair;
import com.mrcrayfish.school.block.BlockDesk;
import com.mrcrayfish.school.block.BlockGlobe;
import com.mrcrayfish.school.block.BlockWhiteboard;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

/**
 * Author: MrCrayfish
 */
public class ModBlocks
{
    public static final Block CHAIR;
    public static final Block DESK;
    public static final Block WHITEBOARD;
    public static final Block GLOBE;

    static
    {
        CHAIR = new BlockChair();
        DESK = new BlockDesk();
        WHITEBOARD = new BlockWhiteboard();
        GLOBE = new BlockGlobe();
    }

    public static void register()
    {
        registerBlock(CHAIR);
        registerBlock(DESK);
        registerBlock(WHITEBOARD);
        registerBlock(GLOBE);
    }

    private static void registerBlock(Block block)
    {
        registerBlock(block, new ItemBlock(block));
    }

    private static void registerBlock(Block block, ItemBlock item)
    {
        if(block.getRegistryName() == null)
            throw new IllegalArgumentException("A block being registered does not have a registry name and could be successfully registered.");

        RegistrationHandler.Blocks.add(block);
        item.setRegistryName(block.getRegistryName());
        RegistrationHandler.Items.add(item);
    }
}
