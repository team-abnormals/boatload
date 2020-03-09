package com.markus1002.extraboats.compatibility;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ObjectHolder;

public class BambooBlocks
{
	@ObjectHolder("bambooblocks:bamboo_planks")
	public static final Block BAMBOO_PLANKS = null;
	
	@ObjectHolder("bambooblocks:bamboo_boat")
	public static final Item BAMBOO_BOAT = null;
	
	public static boolean isInstalled()
	{
		return ModList.get() != null && ModList.get().getModContainerById("bambooblocks").isPresent();
	}
}