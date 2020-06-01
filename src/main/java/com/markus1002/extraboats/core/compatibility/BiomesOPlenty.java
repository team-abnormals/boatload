package com.markus1002.extraboats.core.compatibility;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ObjectHolder;

public class BiomesOPlenty
{
	@ObjectHolder("biomesoplenty:cherry_planks")
	public static final Block CHERRY_PLANKS = null;
	
	@ObjectHolder("biomesoplenty:dead_planks")
	public static final Block DEAD_PLANKS = null;
	
	@ObjectHolder("biomesoplenty:ethereal_planks")
	public static final Block ETHEREAL_PLANKS = null;
	
	@ObjectHolder("biomesoplenty:fir_planks")
	public static final Block FIR_PLANKS = null;
	
	@ObjectHolder("biomesoplenty:hellbark_planks")
	public static final Block HELLBARK_PLANKS = null;
	
	@ObjectHolder("biomesoplenty:jacaranda_planks")
	public static final Block JACARANDA_PLANKS = null;
	
	@ObjectHolder("biomesoplenty:magic_planks")
	public static final Block MAGIC_PLANKS = null;
	
	@ObjectHolder("biomesoplenty:mahogany_planks")
	public static final Block MAHOGANY_PLANKS = null;
	
	@ObjectHolder("biomesoplenty:palm_planks")
	public static final Block PALM_PLANKS = null;
	
	@ObjectHolder("biomesoplenty:redwood_planks")
	public static final Block REDWOOD_PLANKS = null;
	
	@ObjectHolder("biomesoplenty:umbran_planks")
	public static final Block UMBRAN_PLANKS = null;
	
	@ObjectHolder("biomesoplenty:willow_planks")
	public static final Block WILLOW_PLANKS = null;
	
	
	@ObjectHolder("biomesoplenty:cherry_boat")
	public static final Item CHERRY_BOAT = null;
	
	@ObjectHolder("biomesoplenty:dead_boat")
	public static final Item DEAD_BOAT = null;
	
	@ObjectHolder("biomesoplenty:ethereal_boat")
	public static final Item ETHEREAL_BOAT = null;
	
	@ObjectHolder("biomesoplenty:fir_boat")
	public static final Item FIR_BOAT = null;
	
	@ObjectHolder("biomesoplenty:hellbark_boat")
	public static final Item HELLBARK_BOAT = null;
	
	@ObjectHolder("biomesoplenty:jacaranda_boat")
	public static final Item JACARANDA_BOAT = null;
	
	@ObjectHolder("biomesoplenty:magic_boat")
	public static final Item MAGIC_BOAT = null;
	
	@ObjectHolder("biomesoplenty:mahogany_boat")
	public static final Item MAHOGANY_BOAT = null;
	
	@ObjectHolder("biomesoplenty:palm_boat")
	public static final Item PALM_BOAT = null;
	
	@ObjectHolder("biomesoplenty:redwood_boat")
	public static final Item REDWOOD_BOAT = null;
	
	@ObjectHolder("biomesoplenty:umbran_boat")
	public static final Item UMBRAN_BOAT = null;
	
	@ObjectHolder("biomesoplenty:willow_boat")
	public static final Item WILLOW_BOAT = null;
	
	public static boolean isInstalled()
	{
		return ModList.get() != null && ModList.get().getModContainerById("biomesoplenty").isPresent();
	}
}