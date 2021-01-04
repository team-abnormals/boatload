package com.minecraftabnormals.extraboats.core.compatibility;

import net.minecraft.item.Item;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ObjectHolder;

public class NetherExtension {
	@ObjectHolder("nether_extension:crimson_boat")
	public static final Item CRIMSON_BOAT = null;

	@ObjectHolder("nether_extension:warped_boat")
	public static final Item WARPED_BOAT = null;

	public static boolean isInstalled() {
		return ModList.get() != null && ModList.get().getModContainerById("nether_extension").isPresent();
	}
}