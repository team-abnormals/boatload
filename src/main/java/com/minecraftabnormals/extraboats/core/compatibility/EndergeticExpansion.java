package com.minecraftabnormals.extraboats.core.compatibility;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ObjectHolder;

public class EndergeticExpansion {
	@ObjectHolder("endergetic:poise_planks")
	public static final Block POISE_PLANKS = null;

	@ObjectHolder("endergetic:poise_boat")
	public static final Item POISE_BOAT = null;

	public static boolean isInstalled() {
		return ModList.get() != null && ModList.get().getModContainerById("endergetic").isPresent();
	}
}