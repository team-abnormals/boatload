package com.minecraftabnormals.extraboats.core.compatibility;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ObjectHolder;

public class TerraIncognita {
	@ObjectHolder("terraincognita:apple_planks")
	public static final Block APPLE_PLANKS = null;

	@ObjectHolder("terraincognita:hazel_planks")
	public static final Block HAZEL_PLANKS = null;

	@ObjectHolder("terraincognita:apple_boat")
	public static final Item APPLE_BOAT = null;

	@ObjectHolder("terraincognita:hazel_boat")
	public static final Item HAZEL_BOAT = null;

	public static boolean isInstalled() {
		return ModList.get() != null && ModList.get().getModContainerById("terraincognita").isPresent();
	}
}