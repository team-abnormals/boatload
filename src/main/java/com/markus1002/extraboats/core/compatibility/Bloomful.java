package com.markus1002.extraboats.core.compatibility;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ObjectHolder;

public class Bloomful
{
	@ObjectHolder("bloomful:wisteria_planks")
	public static final Block WISTERIA_PLANKS = null;
	
	@ObjectHolder("bloomful:wisteria_boat")
	public static final Item WISTERIA_BOAT = null;
	
	public static boolean isInstalled()
	{
		return ModList.get() != null && ModList.get().getModContainerById("bloomful").isPresent();
	}
}