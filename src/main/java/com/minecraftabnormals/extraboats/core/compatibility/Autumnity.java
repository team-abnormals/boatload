package com.minecraftabnormals.extraboats.core.compatibility;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ObjectHolder;

public class Autumnity {
	@ObjectHolder("autumnity:maple_planks")
	public static final Block MAPLE_PLANKS = null;

	@ObjectHolder("autumnity:maple_boat")
	public static final Item MAPLE_BOAT = null;

	public static boolean isInstalled() {
		return ModList.get() != null && ModList.get().getModContainerById("autumnity").isPresent();
	}
}