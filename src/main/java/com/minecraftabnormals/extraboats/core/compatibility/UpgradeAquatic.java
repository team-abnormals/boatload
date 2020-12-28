package com.minecraftabnormals.extraboats.core.compatibility;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ObjectHolder;

public class UpgradeAquatic {
	@ObjectHolder("upgrade_aquatic:driftwood_planks")
	public static final Block DRIFTWOOD_PLANKS = null;

	@ObjectHolder("upgrade_aquatic:river_planks")
	public static final Block RIVER_PLANKS = null;

	@ObjectHolder("upgrade_aquatic:driftwood_boat")
	public static final Item DRIFTWOOD_BOAT = null;

	@ObjectHolder("upgrade_aquatic:river_boat")
	public static final Item RIVER_BOAT = null;

	public static boolean isInstalled() {
		return ModList.get() != null && ModList.get().getModContainerById("upgrade_aquatic").isPresent();
	}
}