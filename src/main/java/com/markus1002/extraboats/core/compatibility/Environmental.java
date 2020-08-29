package com.markus1002.extraboats.core.compatibility;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ObjectHolder;

public class Environmental
{
	@ObjectHolder("environmental:wisteria_planks")
	public static final Block WISTERIA_PLANKS = null;
	
	@ObjectHolder("environmental:willow_planks")
	public static final Block WILLOW_PLANKS = null;
	
	@ObjectHolder("environmental:cherry_planks")
	public static final Block CHERRY_PLANKS = null;
	
	
	@ObjectHolder("environmental:wisteria_boat")
	public static final Item WISTERIA_BOAT = null;
	
	@ObjectHolder("environmental:willow_boat")
	public static final Item WILLOW_BOAT = null;
	
	@ObjectHolder("environmental:cherry_boat")
	public static final Item CHERRY_BOAT = null;
	
	public static boolean isInstalled()
	{
		return ModList.get() != null && ModList.get().getModContainerById("environmental").isPresent();
	}
}