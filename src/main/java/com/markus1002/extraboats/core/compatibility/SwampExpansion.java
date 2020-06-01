package com.markus1002.extraboats.core.compatibility;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ObjectHolder;

public class SwampExpansion
{
	@ObjectHolder("swampexpansion:willow_planks")
	public static final Block WILLOW_PLANKS = null;
	
	@ObjectHolder("swampexpansion:willow_boat")
	public static final Item WILLOW_BOAT = null;
	
	public static boolean isInstalled()
	{
		return ModList.get() != null && ModList.get().getModContainerById("swampexpansion").isPresent();
	}
}