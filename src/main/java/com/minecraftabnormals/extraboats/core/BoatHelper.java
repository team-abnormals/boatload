package com.minecraftabnormals.extraboats.core;

import com.minecraftabnormals.extraboats.common.entity.item.boat.ExtraBoatsBoatEntity;
import com.minecraftabnormals.extraboats.core.compatibility.Atmospheric;
import com.minecraftabnormals.extraboats.core.compatibility.Autumnity;
import com.minecraftabnormals.extraboats.core.compatibility.BambooBlocks;
import com.minecraftabnormals.extraboats.core.compatibility.BiomesOPlenty;
import com.minecraftabnormals.extraboats.core.compatibility.EndergeticExpansion;
import com.minecraftabnormals.extraboats.core.compatibility.EnhancedMushrooms;
import com.minecraftabnormals.extraboats.core.compatibility.Environmental;
import com.minecraftabnormals.extraboats.core.compatibility.Hanami;
import com.minecraftabnormals.extraboats.core.compatibility.NetherExtension;
import com.minecraftabnormals.extraboats.core.compatibility.UpgradeAquatic;
import com.minecraftabnormals.extraboats.core.registry.ExtraBoatsItems;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

public class BoatHelper {
	public static Block getPlanks(ExtraBoatsBoatEntity.BoatType type) {
		switch (type) {
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
			case MORADO:
				return Atmospheric.MORADO_PLANKS;
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

			case CRIMSON:
				return Blocks.CRIMSON_PLANKS;
			case WARPED:
				return Blocks.WARPED_PLANKS;
		}
	}

	public static Item getBoatItem(ExtraBoatsBoatEntity.BoatType type) {
		switch (type) {
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
			case MORADO:
				return Atmospheric.MORADO_BOAT;
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

			case CRIMSON:
				return NetherExtension.CRIMSON_BOAT;
			case WARPED:
				return NetherExtension.WARPED_BOAT;
		}
	}

	public static Item getChestBoatItem(ExtraBoatsBoatEntity.BoatType type) {
		switch (type) {
			case OAK:
			default:
				return ExtraBoatsItems.OAK_CHEST_BOAT.get();
			case SPRUCE:
				return ExtraBoatsItems.SPRUCE_CHEST_BOAT.get();
			case BIRCH:
				return ExtraBoatsItems.BIRCH_CHEST_BOAT.get();
			case JUNGLE:
				return ExtraBoatsItems.JUNGLE_CHEST_BOAT.get();
			case ACACIA:
				return ExtraBoatsItems.ACACIA_CHEST_BOAT.get();
			case DARK_OAK:
				return ExtraBoatsItems.DARK_OAK_CHEST_BOAT.get();

			case CHERRY:
				return ExtraBoatsItems.CHERRY_CHEST_BOAT.get();
			case DEAD:
				return ExtraBoatsItems.DEAD_CHEST_BOAT.get();
			case FIR:
				return ExtraBoatsItems.FIR_CHEST_BOAT.get();
			case HELLBARK:
				return ExtraBoatsItems.HELLBARK_CHEST_BOAT.get();
			case JACARANDA:
				return ExtraBoatsItems.JACARANDA_CHEST_BOAT.get();
			case MAGIC:
				return ExtraBoatsItems.MAGIC_CHEST_BOAT.get();
			case MAHOGANY:
				return ExtraBoatsItems.MAHOGANY_CHEST_BOAT.get();
			case PALM:
				return ExtraBoatsItems.PALM_CHEST_BOAT.get();
			case REDWOOD:
				return ExtraBoatsItems.REDWOOD_CHEST_BOAT.get();
			case UMBRAN:
				return ExtraBoatsItems.UMBRAN_CHEST_BOAT.get();
			case WILLOW:
				return ExtraBoatsItems.WILLOW_CHEST_BOAT.get();

			case DRIFTWOOD:
				return ExtraBoatsItems.DRIFTWOOD_CHEST_BOAT.get();
			case RIVER:
				return ExtraBoatsItems.RIVER_CHEST_BOAT.get();

			case BAMBOO:
				return ExtraBoatsItems.BAMBOO_CHEST_BOAT.get();

			case POISE:
				return ExtraBoatsItems.POISE_CHEST_BOAT.get();

			case WISTERIA:
				return ExtraBoatsItems.WISTERIA_CHEST_BOAT.get();
			case ENV_WILLOW:
				return ExtraBoatsItems.ENV_WILLOW_CHEST_BOAT.get();
			case ENV_CHERRY:
				return ExtraBoatsItems.ENV_CHERRY_CHEST_BOAT.get();

			case ROSEWOOD:
				return ExtraBoatsItems.ROSEWOOD_CHEST_BOAT.get();
			case MORADO:
				return ExtraBoatsItems.MORADO_CHEST_BOAT.get();
			case ASPEN:
				return ExtraBoatsItems.ASPEN_CHEST_BOAT.get();
			case KOUSA:
				return ExtraBoatsItems.KOUSA_CHEST_BOAT.get();
			case YUCCA:
				return ExtraBoatsItems.YUCCA_CHEST_BOAT.get();
			case GRIMWOOD:
				return ExtraBoatsItems.GRIMWOOD_CHEST_BOAT.get();

			case MAPLE:
				return ExtraBoatsItems.MAPLE_CHEST_BOAT.get();

			case RED_MUSHROOM:
				return ExtraBoatsItems.RED_MUSHROOM_CHEST_BOAT.get();
			case BROWN_MUSHROOM:
				return ExtraBoatsItems.BROWN_MUSHROOM_CHEST_BOAT.get();
			case GLOWSHROOM:
				return ExtraBoatsItems.GLOWSHROOM_CHEST_BOAT.get();

			case SAKURA:
				return ExtraBoatsItems.SAKURA_CHEST_BOAT.get();

			case CRIMSON:
				return ExtraBoatsItems.CRIMSON_CHEST_BOAT.get();
			case WARPED:
				return ExtraBoatsItems.WARPED_CHEST_BOAT.get();
		}
	}

	public static Item getFurnaceBoatItem(ExtraBoatsBoatEntity.BoatType type) {
		switch (type) {
			case OAK:
			default:
				return ExtraBoatsItems.OAK_FURNACE_BOAT.get();
			case SPRUCE:
				return ExtraBoatsItems.SPRUCE_FURNACE_BOAT.get();
			case BIRCH:
				return ExtraBoatsItems.BIRCH_FURNACE_BOAT.get();
			case JUNGLE:
				return ExtraBoatsItems.JUNGLE_FURNACE_BOAT.get();
			case ACACIA:
				return ExtraBoatsItems.ACACIA_FURNACE_BOAT.get();
			case DARK_OAK:
				return ExtraBoatsItems.DARK_OAK_FURNACE_BOAT.get();

			case CHERRY:
				return ExtraBoatsItems.CHERRY_FURNACE_BOAT.get();
			case DEAD:
				return ExtraBoatsItems.DEAD_FURNACE_BOAT.get();
			case FIR:
				return ExtraBoatsItems.FIR_FURNACE_BOAT.get();
			case HELLBARK:
				return ExtraBoatsItems.HELLBARK_FURNACE_BOAT.get();
			case JACARANDA:
				return ExtraBoatsItems.JACARANDA_FURNACE_BOAT.get();
			case MAGIC:
				return ExtraBoatsItems.MAGIC_FURNACE_BOAT.get();
			case MAHOGANY:
				return ExtraBoatsItems.MAHOGANY_FURNACE_BOAT.get();
			case PALM:
				return ExtraBoatsItems.PALM_FURNACE_BOAT.get();
			case REDWOOD:
				return ExtraBoatsItems.REDWOOD_FURNACE_BOAT.get();
			case UMBRAN:
				return ExtraBoatsItems.UMBRAN_FURNACE_BOAT.get();
			case WILLOW:
				return ExtraBoatsItems.WILLOW_FURNACE_BOAT.get();

			case DRIFTWOOD:
				return ExtraBoatsItems.DRIFTWOOD_FURNACE_BOAT.get();
			case RIVER:
				return ExtraBoatsItems.RIVER_FURNACE_BOAT.get();

			case BAMBOO:
				return ExtraBoatsItems.BAMBOO_FURNACE_BOAT.get();

			case POISE:
				return ExtraBoatsItems.POISE_FURNACE_BOAT.get();

			case WISTERIA:
				return ExtraBoatsItems.WISTERIA_FURNACE_BOAT.get();
			case ENV_WILLOW:
				return ExtraBoatsItems.ENV_WILLOW_FURNACE_BOAT.get();
			case ENV_CHERRY:
				return ExtraBoatsItems.ENV_CHERRY_FURNACE_BOAT.get();

			case ROSEWOOD:
				return ExtraBoatsItems.ROSEWOOD_FURNACE_BOAT.get();
			case MORADO:
				return ExtraBoatsItems.MORADO_FURNACE_BOAT.get();
			case ASPEN:
				return ExtraBoatsItems.ASPEN_FURNACE_BOAT.get();
			case KOUSA:
				return ExtraBoatsItems.KOUSA_FURNACE_BOAT.get();
			case YUCCA:
				return ExtraBoatsItems.YUCCA_FURNACE_BOAT.get();
			case GRIMWOOD:
				return ExtraBoatsItems.GRIMWOOD_FURNACE_BOAT.get();

			case MAPLE:
				return ExtraBoatsItems.MAPLE_FURNACE_BOAT.get();

			case RED_MUSHROOM:
				return ExtraBoatsItems.RED_MUSHROOM_FURNACE_BOAT.get();
			case BROWN_MUSHROOM:
				return ExtraBoatsItems.BROWN_MUSHROOM_FURNACE_BOAT.get();
			case GLOWSHROOM:
				return ExtraBoatsItems.GLOWSHROOM_FURNACE_BOAT.get();

			case SAKURA:
				return ExtraBoatsItems.SAKURA_FURNACE_BOAT.get();

			case CRIMSON:
				return ExtraBoatsItems.CRIMSON_FURNACE_BOAT.get();
			case WARPED:
				return ExtraBoatsItems.WARPED_FURNACE_BOAT.get();
		}
	}

	public static Item getLargeBoatItem(ExtraBoatsBoatEntity.BoatType type) {
		switch (type) {
			case OAK:
			default:
				return ExtraBoatsItems.LARGE_OAK_BOAT.get();
			case SPRUCE:
				return ExtraBoatsItems.LARGE_SPRUCE_BOAT.get();
			case BIRCH:
				return ExtraBoatsItems.LARGE_BIRCH_BOAT.get();
			case JUNGLE:
				return ExtraBoatsItems.LARGE_JUNGLE_BOAT.get();
			case ACACIA:
				return ExtraBoatsItems.LARGE_ACACIA_BOAT.get();
			case DARK_OAK:
				return ExtraBoatsItems.LARGE_DARK_OAK_BOAT.get();

			case CHERRY:
				return ExtraBoatsItems.LARGE_CHERRY_BOAT.get();
			case DEAD:
				return ExtraBoatsItems.LARGE_DEAD_BOAT.get();
			case FIR:
				return ExtraBoatsItems.LARGE_FIR_BOAT.get();
			case HELLBARK:
				return ExtraBoatsItems.LARGE_HELLBARK_BOAT.get();
			case JACARANDA:
				return ExtraBoatsItems.LARGE_JACARANDA_BOAT.get();
			case MAGIC:
				return ExtraBoatsItems.LARGE_MAGIC_BOAT.get();
			case MAHOGANY:
				return ExtraBoatsItems.LARGE_MAHOGANY_BOAT.get();
			case PALM:
				return ExtraBoatsItems.LARGE_PALM_BOAT.get();
			case REDWOOD:
				return ExtraBoatsItems.LARGE_REDWOOD_BOAT.get();
			case UMBRAN:
				return ExtraBoatsItems.LARGE_UMBRAN_BOAT.get();
			case WILLOW:
				return ExtraBoatsItems.LARGE_WILLOW_BOAT.get();

			case DRIFTWOOD:
				return ExtraBoatsItems.LARGE_DRIFTWOOD_BOAT.get();
			case RIVER:
				return ExtraBoatsItems.LARGE_RIVER_BOAT.get();

			case BAMBOO:
				return ExtraBoatsItems.LARGE_BAMBOO_BOAT.get();

			case POISE:
				return ExtraBoatsItems.LARGE_POISE_BOAT.get();

			case WISTERIA:
				return ExtraBoatsItems.LARGE_WISTERIA_BOAT.get();
			case ENV_WILLOW:
				return ExtraBoatsItems.LARGE_ENV_WILLOW_BOAT.get();
			case ENV_CHERRY:
				return ExtraBoatsItems.LARGE_ENV_CHERRY_BOAT.get();

			case ROSEWOOD:
				return ExtraBoatsItems.LARGE_ROSEWOOD_BOAT.get();
			case MORADO:
				return ExtraBoatsItems.LARGE_MORADO_BOAT.get();
			case ASPEN:
				return ExtraBoatsItems.LARGE_ASPEN_BOAT.get();
			case KOUSA:
				return ExtraBoatsItems.LARGE_KOUSA_BOAT.get();
			case YUCCA:
				return ExtraBoatsItems.LARGE_YUCCA_BOAT.get();
			case GRIMWOOD:
				return ExtraBoatsItems.LARGE_GRIMWOOD_BOAT.get();

			case MAPLE:
				return ExtraBoatsItems.LARGE_MAPLE_BOAT.get();

			case RED_MUSHROOM:
				return ExtraBoatsItems.LARGE_RED_MUSHROOM_BOAT.get();
			case BROWN_MUSHROOM:
				return ExtraBoatsItems.LARGE_BROWN_MUSHROOM_BOAT.get();
			case GLOWSHROOM:
				return ExtraBoatsItems.LARGE_GLOWSHROOM_BOAT.get();

			case SAKURA:
				return ExtraBoatsItems.LARGE_SAKURA_BOAT.get();

			case CRIMSON:
				return ExtraBoatsItems.LARGE_CRIMSON_BOAT.get();
			case WARPED:
				return ExtraBoatsItems.LARGE_WARPED_BOAT.get();
		}
	}

	public static ExtraBoatsBoatEntity.BoatType getType(Item item) {
		if (item == Items.OAK_BOAT)
			return ExtraBoatsBoatEntity.BoatType.OAK;
		else if (item == Items.SPRUCE_BOAT)
			return ExtraBoatsBoatEntity.BoatType.SPRUCE;
		else if (item == Items.BIRCH_BOAT)
			return ExtraBoatsBoatEntity.BoatType.BIRCH;
		else if (item == Items.JUNGLE_BOAT)
			return ExtraBoatsBoatEntity.BoatType.JUNGLE;
		else if (item == Items.ACACIA_BOAT)
			return ExtraBoatsBoatEntity.BoatType.ACACIA;
		else if (item == Items.DARK_OAK_BOAT)
			return ExtraBoatsBoatEntity.BoatType.DARK_OAK;

		else if (item == Items.OAK_BOAT)
			return ExtraBoatsBoatEntity.BoatType.OAK;
		else if (item == Items.SPRUCE_BOAT)
			return ExtraBoatsBoatEntity.BoatType.SPRUCE;
		else if (item == Items.BIRCH_BOAT)
			return ExtraBoatsBoatEntity.BoatType.BIRCH;
		else if (item == Items.JUNGLE_BOAT)
			return ExtraBoatsBoatEntity.BoatType.JUNGLE;
		else if (item == Items.ACACIA_BOAT)
			return ExtraBoatsBoatEntity.BoatType.ACACIA;
		else if (item == Items.DARK_OAK_BOAT)
			return ExtraBoatsBoatEntity.BoatType.DARK_OAK;

		else if (item == BiomesOPlenty.CHERRY_BOAT)
			return ExtraBoatsBoatEntity.BoatType.CHERRY;
		else if (item == BiomesOPlenty.DEAD_BOAT)
			return ExtraBoatsBoatEntity.BoatType.DEAD;
		else if (item == BiomesOPlenty.FIR_BOAT)
			return ExtraBoatsBoatEntity.BoatType.FIR;
		else if (item == BiomesOPlenty.HELLBARK_BOAT)
			return ExtraBoatsBoatEntity.BoatType.HELLBARK;
		else if (item == BiomesOPlenty.JACARANDA_BOAT)
			return ExtraBoatsBoatEntity.BoatType.JACARANDA;
		else if (item == BiomesOPlenty.MAGIC_BOAT)
			return ExtraBoatsBoatEntity.BoatType.MAGIC;
		else if (item == BiomesOPlenty.MAHOGANY_BOAT)
			return ExtraBoatsBoatEntity.BoatType.MAHOGANY;
		else if (item == BiomesOPlenty.PALM_BOAT)
			return ExtraBoatsBoatEntity.BoatType.PALM;
		else if (item == BiomesOPlenty.REDWOOD_BOAT)
			return ExtraBoatsBoatEntity.BoatType.REDWOOD;
		else if (item == BiomesOPlenty.UMBRAN_BOAT)
			return ExtraBoatsBoatEntity.BoatType.UMBRAN;
		else if (item == BiomesOPlenty.WILLOW_BOAT)
			return ExtraBoatsBoatEntity.BoatType.WILLOW;

		else if (item == UpgradeAquatic.DRIFTWOOD_BOAT)
			return ExtraBoatsBoatEntity.BoatType.DRIFTWOOD;
		else if (item == UpgradeAquatic.RIVER_BOAT)
			return ExtraBoatsBoatEntity.BoatType.RIVER;

		else if (item == BambooBlocks.BAMBOO_BOAT)
			return ExtraBoatsBoatEntity.BoatType.BAMBOO;

		else if (item == EndergeticExpansion.POISE_BOAT)
			return ExtraBoatsBoatEntity.BoatType.POISE;

		else if (item == Environmental.WISTERIA_BOAT)
			return ExtraBoatsBoatEntity.BoatType.WISTERIA;
		else if (item == Environmental.WILLOW_BOAT)
			return ExtraBoatsBoatEntity.BoatType.ENV_WILLOW;
		else if (item == Environmental.CHERRY_BOAT)
			return ExtraBoatsBoatEntity.BoatType.ENV_CHERRY;

		else if (item == Atmospheric.ROSEWOOD_BOAT)
			return ExtraBoatsBoatEntity.BoatType.ROSEWOOD;
		else if (item == Atmospheric.MORADO_BOAT)
			return ExtraBoatsBoatEntity.BoatType.MORADO;
		else if (item == Atmospheric.ASPEN_BOAT)
			return ExtraBoatsBoatEntity.BoatType.ASPEN;
		else if (item == Atmospheric.KOUSA_BOAT)
			return ExtraBoatsBoatEntity.BoatType.KOUSA;
		else if (item == Atmospheric.YUCCA_BOAT)
			return ExtraBoatsBoatEntity.BoatType.YUCCA;
		else if (item == Atmospheric.GRIMWOOD_BOAT)
			return ExtraBoatsBoatEntity.BoatType.GRIMWOOD;

		else if (item == Autumnity.MAPLE_BOAT)
			return ExtraBoatsBoatEntity.BoatType.MAPLE;

		else if (item == EnhancedMushrooms.RED_MUSHROOM_BOAT)
			return ExtraBoatsBoatEntity.BoatType.RED_MUSHROOM;
		else if (item == EnhancedMushrooms.BROWN_MUSHROOM_BOAT)
			return ExtraBoatsBoatEntity.BoatType.BROWN_MUSHROOM;
		else if (item == EnhancedMushrooms.GLOWSHROOM_BOAT)
			return ExtraBoatsBoatEntity.BoatType.GLOWSHROOM;

		else if (item == Hanami.SAKURA_BOAT)
			return ExtraBoatsBoatEntity.BoatType.SAKURA;
		
		else if (item == NetherExtension.CRIMSON_BOAT)
			return ExtraBoatsBoatEntity.BoatType.CRIMSON;
		else
			return ExtraBoatsBoatEntity.BoatType.WARPED;
	}
}