package com.markus1002.extraboats.compatibility;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ObjectHolder;

public class BuzzierBees
{
	@ObjectHolder("buzzierbees:hive_planks")
	public static final Block HIVE_PLANKS = null;

	@ObjectHolder("buzzierbees:hive_boat")
	public static final Item HIVE_BOAT = null;

	public static boolean isInstalled()
	{
		return ModList.get() != null && ModList.get().getModContainerById("buzzierbees").isPresent();
	}
}
