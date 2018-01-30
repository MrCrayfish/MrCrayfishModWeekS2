package com.mrcrayfish.clock.init;

import com.mrcrayfish.clock.block.BlockDigitalClock;
import com.mrcrayfish.clock.item.ItemColored;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;


public class ModBlocks
{
	public static final Block DIGITAL_CLOCK;

	static
	{
		DIGITAL_CLOCK = new BlockDigitalClock();
	}

	public static void register()
	{
		registerBlock(DIGITAL_CLOCK, new ItemColored(DIGITAL_CLOCK));
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