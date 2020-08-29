package com.markus1002.extraboats.core;

import com.markus1002.extraboats.common.entity.item.boat.ModBoatEntity;
import com.markus1002.extraboats.core.compatibility.Atmospheric;
import com.markus1002.extraboats.core.compatibility.Autumnity;
import com.markus1002.extraboats.core.compatibility.BambooBlocks;
import com.markus1002.extraboats.core.compatibility.BiomesOPlenty;
import com.markus1002.extraboats.core.compatibility.EndergeticExpansion;
import com.markus1002.extraboats.core.compatibility.EnhancedMushrooms;
import com.markus1002.extraboats.core.compatibility.Environmental;
import com.markus1002.extraboats.core.compatibility.Hanami;
import com.markus1002.extraboats.core.compatibility.UpgradeAquatic;
import com.markus1002.extraboats.core.registry.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

public class BoatHelper
{
	public static Block getPlanks(ModBoatEntity.BoatType type)
	{
		switch(type)
		{
		case OAK:
		default:
			return Blocks.OAK_PLANKS;
		case SPRUCE:
			return Blocks.SPRUCE_PLANKS;
		case BIRCH:
			return Blocks.BIRCH_PLANKS;
		case JUNGLE:
			return Blocks.JUNGLE_PLANKS;
		case ACACIA:
			return Blocks.ACACIA_PLANKS;
		case DARK_OAK:
			return Blocks.DARK_OAK_PLANKS;

		case CHERRY:
			return BiomesOPlenty.CHERRY_PLANKS;
		case DEAD:
			return BiomesOPlenty.DEAD_PLANKS;
		case FIR:
			return BiomesOPlenty.FIR_PLANKS;
		case HELLBARK:
			return BiomesOPlenty.HELLBARK_PLANKS;
		case JACARANDA:
			return BiomesOPlenty.JACARANDA_PLANKS;
		case MAGIC:
			return BiomesOPlenty.MAGIC_PLANKS;
		case MAHOGANY:
			return BiomesOPlenty.MAHOGANY_PLANKS;
		case PALM:
			return BiomesOPlenty.PALM_PLANKS;
		case REDWOOD:
			return BiomesOPlenty.REDWOOD_PLANKS;
		case UMBRAN:
			return BiomesOPlenty.UMBRAN_PLANKS;
		case WILLOW:
			return BiomesOPlenty.WILLOW_PLANKS;
			
		case DRIFTWOOD:
			return UpgradeAquatic.DRIFTWOOD_PLANKS;
		case RIVER:
			return UpgradeAquatic.RIVER_PLANKS;
			
		case BAMBOO:
			return BambooBlocks.BAMBOO_PLANKS;
			
		case POISE:
			return EndergeticExpansion.POISE_PLANKS;
			
		case WISTERIA:
			return Environmental.WISTERIA_PLANKS;
		case ENV_WILLOW:
			return Environmental.WILLOW_PLANKS;
		case ENV_CHERRY:
			return Environmental.CHERRY_PLANKS;
			
		case ROSEWOOD:
			return Atmospheric.ROSEWOOD_PLANKS;
		case ASPEN:
			return Atmospheric.ASPEN_PLANKS;
		case KOUSA:
			return Atmospheric.KOUSA_PLANKS;
		case YUCCA:
			return Atmospheric.YUCCA_PLANKS;
		case GRIMWOOD:
			return Atmospheric.GRIMWOOD_PLANKS;
			
		case MAPLE:
			return Autumnity.MAPLE_PLANKS;

		case RED_MUSHROOM:
			return EnhancedMushrooms.RED_MUSHROOM_PLANKS;
		case BROWN_MUSHROOM:
			return EnhancedMushrooms.BROWN_MUSHROOM_PLANKS;
		case GLOWSHROOM:
			return EnhancedMushrooms.GLOWSHROOM_PLANKS;

		case SAKURA:
			return Hanami.SAKURA_PLANKS;
		}
	}
	
	public static Item getBoatItem(ModBoatEntity.BoatType type)
	{
		switch(type)
		{
		case OAK:
		default:
			return Items.OAK_BOAT;
		case SPRUCE:
			return Items.SPRUCE_BOAT;
		case BIRCH:
			return Items.BIRCH_BOAT;
		case JUNGLE:
			return Items.JUNGLE_BOAT;
		case ACACIA:
			return Items.ACACIA_BOAT;
		case DARK_OAK:
			return Items.DARK_OAK_BOAT;

		case CHERRY:
			return BiomesOPlenty.CHERRY_BOAT;
		case DEAD:
			return BiomesOPlenty.DEAD_BOAT;
		case FIR:
			return BiomesOPlenty.FIR_BOAT;
		case HELLBARK:
			return BiomesOPlenty.HELLBARK_BOAT;
		case JACARANDA:
			return BiomesOPlenty.JACARANDA_BOAT;
		case MAGIC:
			return BiomesOPlenty.MAGIC_BOAT;
		case MAHOGANY:
			return BiomesOPlenty.MAHOGANY_BOAT;
		case PALM:
			return BiomesOPlenty.PALM_BOAT;
		case REDWOOD:
			return BiomesOPlenty.REDWOOD_BOAT;
		case UMBRAN:
			return BiomesOPlenty.UMBRAN_BOAT;
		case WILLOW:
			return BiomesOPlenty.WILLOW_BOAT;
			
		case DRIFTWOOD:
			return UpgradeAquatic.DRIFTWOOD_BOAT;
		case RIVER:
			return UpgradeAquatic.RIVER_BOAT;
			
		case BAMBOO:
			return BambooBlocks.BAMBOO_BOAT;
			
		case POISE:
			return EndergeticExpansion.POISE_BOAT;
			
		case WISTERIA:
			return Environmental.WISTERIA_BOAT;
		case ENV_WILLOW:
			return Environmental.WILLOW_BOAT;
		case ENV_CHERRY:
			return Environmental.CHERRY_BOAT;
			
		case ROSEWOOD:
			return Atmospheric.ROSEWOOD_BOAT;
		case ASPEN:
			return Atmospheric.ASPEN_BOAT;
		case KOUSA:
			return Atmospheric.KOUSA_BOAT;
		case YUCCA:
			return Atmospheric.YUCCA_BOAT;
		case GRIMWOOD:
			return Atmospheric.GRIMWOOD_BOAT;
			
		case MAPLE:
			return Autumnity.MAPLE_BOAT;

		case RED_MUSHROOM:
			return EnhancedMushrooms.RED_MUSHROOM_BOAT;
		case BROWN_MUSHROOM:
			return EnhancedMushrooms.BROWN_MUSHROOM_BOAT;
		case GLOWSHROOM:
			return EnhancedMushrooms.GLOWSHROOM_BOAT;

		case SAKURA:
			return Hanami.SAKURA_BOAT;
		}
	}
	
	public static Item getChestBoatItem(ModBoatEntity.BoatType type)
	{
		switch(type)
		{
		case OAK:
		default:
			return ModItems.OAK_CHEST_BOAT;
		case SPRUCE:
			return ModItems.SPRUCE_CHEST_BOAT;
		case BIRCH:
			return ModItems.BIRCH_CHEST_BOAT;
		case JUNGLE:
			return ModItems.JUNGLE_CHEST_BOAT;
		case ACACIA:
			return ModItems.ACACIA_CHEST_BOAT;
		case DARK_OAK:
			return ModItems.DARK_OAK_CHEST_BOAT;
			
		case CHERRY:
			return ModItems.CHERRY_CHEST_BOAT;
		case DEAD:
			return ModItems.DEAD_CHEST_BOAT;
		case FIR:
			return ModItems.FIR_CHEST_BOAT;
		case HELLBARK:
			return ModItems.HELLBARK_CHEST_BOAT;
		case JACARANDA:
			return ModItems.JACARANDA_CHEST_BOAT;
		case MAGIC:
			return ModItems.MAGIC_CHEST_BOAT;
		case MAHOGANY:
			return ModItems.MAHOGANY_CHEST_BOAT;
		case PALM:
			return ModItems.PALM_CHEST_BOAT;
		case REDWOOD:
			return ModItems.REDWOOD_CHEST_BOAT;
		case UMBRAN:
			return ModItems.UMBRAN_CHEST_BOAT;
		case WILLOW:
			return ModItems.WILLOW_CHEST_BOAT;
			
		case DRIFTWOOD:
			return ModItems.DRIFTWOOD_CHEST_BOAT;
		case RIVER:
			return ModItems.RIVER_CHEST_BOAT;
			
		case BAMBOO:
			return ModItems.BAMBOO_CHEST_BOAT;
			
		case POISE:
			return ModItems.POISE_CHEST_BOAT;
			
		case WISTERIA:
			return ModItems.WISTERIA_CHEST_BOAT;
		case ENV_WILLOW:
			return ModItems.ENV_WILLOW_CHEST_BOAT;
		case ENV_CHERRY:
			return ModItems.ENV_CHERRY_CHEST_BOAT;
			
		case ROSEWOOD:
			return ModItems.ROSEWOOD_CHEST_BOAT;
		case ASPEN:
			return ModItems.ASPEN_CHEST_BOAT;
		case KOUSA:
			return ModItems.KOUSA_CHEST_BOAT;
		case YUCCA:
			return ModItems.YUCCA_CHEST_BOAT;
		case GRIMWOOD:
			return ModItems.GRIMWOOD_CHEST_BOAT;
			
		case MAPLE:
			return ModItems.MAPLE_CHEST_BOAT;

		case RED_MUSHROOM:
			return ModItems.RED_MUSHROOM_CHEST_BOAT;
		case BROWN_MUSHROOM:
			return ModItems.BROWN_MUSHROOM_CHEST_BOAT;
		case GLOWSHROOM:
			return ModItems.GLOWSHROOM_CHEST_BOAT;

		case SAKURA:
			return ModItems.SAKURA_CHEST_BOAT;
		}
	}
	
	public static Item getFurnaceBoatItem(ModBoatEntity.BoatType type)
	{
		switch(type)
		{
		case OAK:
		default:
			return ModItems.OAK_FURNACE_BOAT;
		case SPRUCE:
			return ModItems.SPRUCE_FURNACE_BOAT;
		case BIRCH:
			return ModItems.BIRCH_FURNACE_BOAT;
		case JUNGLE:
			return ModItems.JUNGLE_FURNACE_BOAT;
		case ACACIA:
			return ModItems.ACACIA_FURNACE_BOAT;
		case DARK_OAK:
			return ModItems.DARK_OAK_FURNACE_BOAT;

		case CHERRY:
			return ModItems.CHERRY_FURNACE_BOAT;
		case DEAD:
			return ModItems.DEAD_FURNACE_BOAT;
		case FIR:
			return ModItems.FIR_FURNACE_BOAT;
		case HELLBARK:
			return ModItems.HELLBARK_FURNACE_BOAT;
		case JACARANDA:
			return ModItems.JACARANDA_FURNACE_BOAT;
		case MAGIC:
			return ModItems.MAGIC_FURNACE_BOAT;
		case MAHOGANY:
			return ModItems.MAHOGANY_FURNACE_BOAT;
		case PALM:
			return ModItems.PALM_FURNACE_BOAT;
		case REDWOOD:
			return ModItems.REDWOOD_FURNACE_BOAT;
		case UMBRAN:
			return ModItems.UMBRAN_FURNACE_BOAT;
		case WILLOW:
			return ModItems.WILLOW_FURNACE_BOAT;

		case DRIFTWOOD:
			return ModItems.DRIFTWOOD_FURNACE_BOAT;
		case RIVER:
			return ModItems.RIVER_FURNACE_BOAT;

		case BAMBOO:
			return ModItems.BAMBOO_FURNACE_BOAT;

		case POISE:
			return ModItems.POISE_FURNACE_BOAT;

		case WISTERIA:
			return ModItems.WISTERIA_FURNACE_BOAT;
		case ENV_WILLOW:
			return ModItems.ENV_WILLOW_FURNACE_BOAT;
		case ENV_CHERRY:
			return ModItems.ENV_CHERRY_FURNACE_BOAT;
			
		case ROSEWOOD:
			return ModItems.ROSEWOOD_FURNACE_BOAT;
		case ASPEN:
			return ModItems.ASPEN_FURNACE_BOAT;
		case KOUSA:
			return ModItems.KOUSA_FURNACE_BOAT;
		case YUCCA:
			return ModItems.YUCCA_FURNACE_BOAT;
		case GRIMWOOD:
			return ModItems.GRIMWOOD_FURNACE_BOAT;
			
		case MAPLE:
			return ModItems.MAPLE_FURNACE_BOAT;

		case RED_MUSHROOM:
			return ModItems.RED_MUSHROOM_FURNACE_BOAT;
		case BROWN_MUSHROOM:
			return ModItems.BROWN_MUSHROOM_FURNACE_BOAT;
		case GLOWSHROOM:
			return ModItems.GLOWSHROOM_FURNACE_BOAT;

		case SAKURA:
			return ModItems.SAKURA_FURNACE_BOAT;
		}
	}
	
	public static Item getLargeBoatItem(ModBoatEntity.BoatType type)
	{
		switch(type)
		{
		case OAK:
		default:
			return ModItems.LARGE_OAK_BOAT;
		case SPRUCE:
			return ModItems.LARGE_SPRUCE_BOAT;
		case BIRCH:
			return ModItems.LARGE_BIRCH_BOAT;
		case JUNGLE:
			return ModItems.LARGE_JUNGLE_BOAT;
		case ACACIA:
			return ModItems.LARGE_ACACIA_BOAT;
		case DARK_OAK:
			return ModItems.LARGE_DARK_OAK_BOAT;
			
		case CHERRY:
			return ModItems.LARGE_CHERRY_BOAT;
		case DEAD:
			return ModItems.LARGE_DEAD_BOAT;
		case FIR:
			return ModItems.LARGE_FIR_BOAT;
		case HELLBARK:
			return ModItems.LARGE_HELLBARK_BOAT;
		case JACARANDA:
			return ModItems.LARGE_JACARANDA_BOAT;
		case MAGIC:
			return ModItems.LARGE_MAGIC_BOAT;
		case MAHOGANY:
			return ModItems.LARGE_MAHOGANY_BOAT;
		case PALM:
			return ModItems.LARGE_PALM_BOAT;
		case REDWOOD:
			return ModItems.LARGE_REDWOOD_BOAT;
		case UMBRAN:
			return ModItems.LARGE_UMBRAN_BOAT;
		case WILLOW:
			return ModItems.LARGE_WILLOW_BOAT;
			
		case DRIFTWOOD:
			return ModItems.LARGE_DRIFTWOOD_BOAT;
		case RIVER:
			return ModItems.LARGE_RIVER_BOAT;
			
		case BAMBOO:
			return ModItems.LARGE_BAMBOO_BOAT;
			
		case POISE:
			return ModItems.LARGE_POISE_BOAT;
			
		case WISTERIA:
			return ModItems.LARGE_WISTERIA_BOAT;
		case ENV_WILLOW:
			return ModItems.LARGE_ENV_WILLOW_BOAT;
		case ENV_CHERRY:
			return ModItems.LARGE_ENV_CHERRY_BOAT;
			
		case ROSEWOOD:
			return ModItems.LARGE_ROSEWOOD_BOAT;
		case ASPEN:
			return ModItems.LARGE_ASPEN_BOAT;
		case KOUSA:
			return ModItems.LARGE_KOUSA_BOAT;
		case YUCCA:
			return ModItems.LARGE_YUCCA_BOAT;
		case GRIMWOOD:
			return ModItems.LARGE_GRIMWOOD_BOAT;
			
		case MAPLE:
			return ModItems.LARGE_MAPLE_BOAT;

		case RED_MUSHROOM:
			return ModItems.LARGE_RED_MUSHROOM_BOAT;
		case BROWN_MUSHROOM:
			return ModItems.LARGE_BROWN_MUSHROOM_BOAT;
		case GLOWSHROOM:
			return ModItems.LARGE_GLOWSHROOM_BOAT;

		case SAKURA:
			return ModItems.LARGE_SAKURA_BOAT;
		}
	}
}