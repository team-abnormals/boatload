package com.minecraftabnormals.extraboats.core.compatibility;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ObjectHolder;

public class Atmospheric
{
	@ObjectHolder("atmospheric:rosewood_planks")
	public static final Block ROSEWOOD_PLANKS = null;
	
	@ObjectHolder("atmospheric:morado_planks")
	public static final Block MORADO_PLANKS = null;
	
	@ObjectHolder("atmospheric:aspen_planks")
	public static final Block ASPEN_PLANKS = null;
	
	@ObjectHolder("atmospheric:kousa_planks")
	public static final Block KOUSA_PLANKS = null;
	
	@ObjectHolder("atmospheric:yucca_planks")
	public static final Block YUCCA_PLANKS = null;
	
	@ObjectHolder("atmospheric:grimwood_planks")
	public static final Block GRIMWOOD_PLANKS = null;
	

	@ObjectHolder("atmospheric:rosewood_boat")
	public static final Item ROSEWOOD_BOAT = null;
	
	@ObjectHolder("atmospheric:morado_boat")
	public static final Item MORADO_BOAT = null;
	
	@ObjectHolder("atmospheric:aspen_boat")
	public static final Item ASPEN_BOAT = null;
	
	@ObjectHolder("atmospheric:kousa_boat")
	public static final Item KOUSA_BOAT = null;
	
	@ObjectHolder("atmospheric:yucca_boat")
	public static final Item YUCCA_BOAT = null;
	
	@ObjectHolder("atmospheric:grimwood_boat")
	public static final Item GRIMWOOD_BOAT = null;

	public static boolean isInstalled()
	{
		return ModList.get() != null && ModList.get().getModContainerById("atmospheric").isPresent();
	}
}