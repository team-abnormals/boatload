package com.minecraftabnormals.extraboats.core;

import com.minecraftabnormals.extraboats.common.entity.item.boat.EBBoatEntity;
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
import com.minecraftabnormals.extraboats.core.registry.EBItems;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

public class BoatHelper {
	public static Block getPlanks(EBBoatEntity.BoatType type) {
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

	public static Item getBoatItem(EBBoatEntity.BoatType type) {
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

	public static Item getChestBoatItem(EBBoatEntity.BoatType type) {
		switch (type) {
			case OAK:
			default:
				return EBItems.OAK_CHEST_BOAT.get();
			case SPRUCE:
				return EBItems.SPRUCE_CHEST_BOAT.get();
			case BIRCH:
				return EBItems.BIRCH_CHEST_BOAT.get();
			case JUNGLE:
				return EBItems.JUNGLE_CHEST_BOAT.get();
			case ACACIA:
				return EBItems.ACACIA_CHEST_BOAT.get();
			case DARK_OAK:
				return EBItems.DARK_OAK_CHEST_BOAT.get();

			case CHERRY:
				return EBItems.CHERRY_CHEST_BOAT.get();
			case DEAD:
				return EBItems.DEAD_CHEST_BOAT.get();
			case FIR:
				return EBItems.FIR_CHEST_BOAT.get();
			case HELLBARK:
				return EBItems.HELLBARK_CHEST_BOAT.get();
			case JACARANDA:
				return EBItems.JACARANDA_CHEST_BOAT.get();
			case MAGIC:
				return EBItems.MAGIC_CHEST_BOAT.get();
			case MAHOGANY:
				return EBItems.MAHOGANY_CHEST_BOAT.get();
			case PALM:
				return EBItems.PALM_CHEST_BOAT.get();
			case REDWOOD:
				return EBItems.REDWOOD_CHEST_BOAT.get();
			case UMBRAN:
				return EBItems.UMBRAN_CHEST_BOAT.get();
			case WILLOW:
				return EBItems.WILLOW_CHEST_BOAT.get();

			case DRIFTWOOD:
				return EBItems.DRIFTWOOD_CHEST_BOAT.get();
			case RIVER:
				return EBItems.RIVER_CHEST_BOAT.get();

			case BAMBOO:
				return EBItems.BAMBOO_CHEST_BOAT.get();

			case POISE:
				return EBItems.POISE_CHEST_BOAT.get();

			case WISTERIA:
				return EBItems.WISTERIA_CHEST_BOAT.get();
			case ENV_WILLOW:
				return EBItems.ENV_WILLOW_CHEST_BOAT.get();
			case ENV_CHERRY:
				return EBItems.ENV_CHERRY_CHEST_BOAT.get();

			case ROSEWOOD:
				return EBItems.ROSEWOOD_CHEST_BOAT.get();
			case MORADO:
				return EBItems.MORADO_CHEST_BOAT.get();
			case ASPEN:
				return EBItems.ASPEN_CHEST_BOAT.get();
			case KOUSA:
				return EBItems.KOUSA_CHEST_BOAT.get();
			case YUCCA:
				return EBItems.YUCCA_CHEST_BOAT.get();
			case GRIMWOOD:
				return EBItems.GRIMWOOD_CHEST_BOAT.get();

			case MAPLE:
				return EBItems.MAPLE_CHEST_BOAT.get();

			case RED_MUSHROOM:
				return EBItems.RED_MUSHROOM_CHEST_BOAT.get();
			case BROWN_MUSHROOM:
				return EBItems.BROWN_MUSHROOM_CHEST_BOAT.get();
			case GLOWSHROOM:
				return EBItems.GLOWSHROOM_CHEST_BOAT.get();

			case SAKURA:
				return EBItems.SAKURA_CHEST_BOAT.get();

			case CRIMSON:
				return EBItems.CRIMSON_CHEST_BOAT.get();
			case WARPED:
				return EBItems.WARPED_CHEST_BOAT.get();
		}
	}

	public static Item getFurnaceBoatItem(EBBoatEntity.BoatType type) {
		switch (type) {
			case OAK:
			default:
				return EBItems.OAK_FURNACE_BOAT.get();
			case SPRUCE:
				return EBItems.SPRUCE_FURNACE_BOAT.get();
			case BIRCH:
				return EBItems.BIRCH_FURNACE_BOAT.get();
			case JUNGLE:
				return EBItems.JUNGLE_FURNACE_BOAT.get();
			case ACACIA:
				return EBItems.ACACIA_FURNACE_BOAT.get();
			case DARK_OAK:
				return EBItems.DARK_OAK_FURNACE_BOAT.get();

			case CHERRY:
				return EBItems.CHERRY_FURNACE_BOAT.get();
			case DEAD:
				return EBItems.DEAD_FURNACE_BOAT.get();
			case FIR:
				return EBItems.FIR_FURNACE_BOAT.get();
			case HELLBARK:
				return EBItems.HELLBARK_FURNACE_BOAT.get();
			case JACARANDA:
				return EBItems.JACARANDA_FURNACE_BOAT.get();
			case MAGIC:
				return EBItems.MAGIC_FURNACE_BOAT.get();
			case MAHOGANY:
				return EBItems.MAHOGANY_FURNACE_BOAT.get();
			case PALM:
				return EBItems.PALM_FURNACE_BOAT.get();
			case REDWOOD:
				return EBItems.REDWOOD_FURNACE_BOAT.get();
			case UMBRAN:
				return EBItems.UMBRAN_FURNACE_BOAT.get();
			case WILLOW:
				return EBItems.WILLOW_FURNACE_BOAT.get();

			case DRIFTWOOD:
				return EBItems.DRIFTWOOD_FURNACE_BOAT.get();
			case RIVER:
				return EBItems.RIVER_FURNACE_BOAT.get();

			case BAMBOO:
				return EBItems.BAMBOO_FURNACE_BOAT.get();

			case POISE:
				return EBItems.POISE_FURNACE_BOAT.get();

			case WISTERIA:
				return EBItems.WISTERIA_FURNACE_BOAT.get();
			case ENV_WILLOW:
				return EBItems.ENV_WILLOW_FURNACE_BOAT.get();
			case ENV_CHERRY:
				return EBItems.ENV_CHERRY_FURNACE_BOAT.get();

			case ROSEWOOD:
				return EBItems.ROSEWOOD_FURNACE_BOAT.get();
			case MORADO:
				return EBItems.MORADO_FURNACE_BOAT.get();
			case ASPEN:
				return EBItems.ASPEN_FURNACE_BOAT.get();
			case KOUSA:
				return EBItems.KOUSA_FURNACE_BOAT.get();
			case YUCCA:
				return EBItems.YUCCA_FURNACE_BOAT.get();
			case GRIMWOOD:
				return EBItems.GRIMWOOD_FURNACE_BOAT.get();

			case MAPLE:
				return EBItems.MAPLE_FURNACE_BOAT.get();

			case RED_MUSHROOM:
				return EBItems.RED_MUSHROOM_FURNACE_BOAT.get();
			case BROWN_MUSHROOM:
				return EBItems.BROWN_MUSHROOM_FURNACE_BOAT.get();
			case GLOWSHROOM:
				return EBItems.GLOWSHROOM_FURNACE_BOAT.get();

			case SAKURA:
				return EBItems.SAKURA_FURNACE_BOAT.get();

			case CRIMSON:
				return EBItems.CRIMSON_FURNACE_BOAT.get();
			case WARPED:
				return EBItems.WARPED_FURNACE_BOAT.get();
		}
	}

	public static Item getLargeBoatItem(EBBoatEntity.BoatType type) {
		switch (type) {
			case OAK:
			default:
				return EBItems.LARGE_OAK_BOAT.get();
			case SPRUCE:
				return EBItems.LARGE_SPRUCE_BOAT.get();
			case BIRCH:
				return EBItems.LARGE_BIRCH_BOAT.get();
			case JUNGLE:
				return EBItems.LARGE_JUNGLE_BOAT.get();
			case ACACIA:
				return EBItems.LARGE_ACACIA_BOAT.get();
			case DARK_OAK:
				return EBItems.LARGE_DARK_OAK_BOAT.get();

			case CHERRY:
				return EBItems.LARGE_CHERRY_BOAT.get();
			case DEAD:
				return EBItems.LARGE_DEAD_BOAT.get();
			case FIR:
				return EBItems.LARGE_FIR_BOAT.get();
			case HELLBARK:
				return EBItems.LARGE_HELLBARK_BOAT.get();
			case JACARANDA:
				return EBItems.LARGE_JACARANDA_BOAT.get();
			case MAGIC:
				return EBItems.LARGE_MAGIC_BOAT.get();
			case MAHOGANY:
				return EBItems.LARGE_MAHOGANY_BOAT.get();
			case PALM:
				return EBItems.LARGE_PALM_BOAT.get();
			case REDWOOD:
				return EBItems.LARGE_REDWOOD_BOAT.get();
			case UMBRAN:
				return EBItems.LARGE_UMBRAN_BOAT.get();
			case WILLOW:
				return EBItems.LARGE_WILLOW_BOAT.get();

			case DRIFTWOOD:
				return EBItems.LARGE_DRIFTWOOD_BOAT.get();
			case RIVER:
				return EBItems.LARGE_RIVER_BOAT.get();

			case BAMBOO:
				return EBItems.LARGE_BAMBOO_BOAT.get();

			case POISE:
				return EBItems.LARGE_POISE_BOAT.get();

			case WISTERIA:
				return EBItems.LARGE_WISTERIA_BOAT.get();
			case ENV_WILLOW:
				return EBItems.LARGE_ENV_WILLOW_BOAT.get();
			case ENV_CHERRY:
				return EBItems.LARGE_ENV_CHERRY_BOAT.get();

			case ROSEWOOD:
				return EBItems.LARGE_ROSEWOOD_BOAT.get();
			case MORADO:
				return EBItems.LARGE_MORADO_BOAT.get();
			case ASPEN:
				return EBItems.LARGE_ASPEN_BOAT.get();
			case KOUSA:
				return EBItems.LARGE_KOUSA_BOAT.get();
			case YUCCA:
				return EBItems.LARGE_YUCCA_BOAT.get();
			case GRIMWOOD:
				return EBItems.LARGE_GRIMWOOD_BOAT.get();

			case MAPLE:
				return EBItems.LARGE_MAPLE_BOAT.get();

			case RED_MUSHROOM:
				return EBItems.LARGE_RED_MUSHROOM_BOAT.get();
			case BROWN_MUSHROOM:
				return EBItems.LARGE_BROWN_MUSHROOM_BOAT.get();
			case GLOWSHROOM:
				return EBItems.LARGE_GLOWSHROOM_BOAT.get();

			case SAKURA:
				return EBItems.LARGE_SAKURA_BOAT.get();

			case CRIMSON:
				return EBItems.LARGE_CRIMSON_BOAT.get();
			case WARPED:
				return EBItems.LARGE_WARPED_BOAT.get();
		}
	}

	public static EBBoatEntity.BoatType getType(Item item) {
		if (item == Items.OAK_BOAT)
			return EBBoatEntity.BoatType.OAK;
		else if (item == Items.SPRUCE_BOAT)
			return EBBoatEntity.BoatType.SPRUCE;
		else if (item == Items.BIRCH_BOAT)
			return EBBoatEntity.BoatType.BIRCH;
		else if (item == Items.JUNGLE_BOAT)
			return EBBoatEntity.BoatType.JUNGLE;
		else if (item == Items.ACACIA_BOAT)
			return EBBoatEntity.BoatType.ACACIA;
		else if (item == Items.DARK_OAK_BOAT)
			return EBBoatEntity.BoatType.DARK_OAK;

		else if (item == Items.OAK_BOAT)
			return EBBoatEntity.BoatType.OAK;
		else if (item == Items.SPRUCE_BOAT)
			return EBBoatEntity.BoatType.SPRUCE;
		else if (item == Items.BIRCH_BOAT)
			return EBBoatEntity.BoatType.BIRCH;
		else if (item == Items.JUNGLE_BOAT)
			return EBBoatEntity.BoatType.JUNGLE;
		else if (item == Items.ACACIA_BOAT)
			return EBBoatEntity.BoatType.ACACIA;
		else if (item == Items.DARK_OAK_BOAT)
			return EBBoatEntity.BoatType.DARK_OAK;

		else if (item == BiomesOPlenty.CHERRY_BOAT)
			return EBBoatEntity.BoatType.CHERRY;
		else if (item == BiomesOPlenty.DEAD_BOAT)
			return EBBoatEntity.BoatType.DEAD;
		else if (item == BiomesOPlenty.FIR_BOAT)
			return EBBoatEntity.BoatType.FIR;
		else if (item == BiomesOPlenty.HELLBARK_BOAT)
			return EBBoatEntity.BoatType.HELLBARK;
		else if (item == BiomesOPlenty.JACARANDA_BOAT)
			return EBBoatEntity.BoatType.JACARANDA;
		else if (item == BiomesOPlenty.MAGIC_BOAT)
			return EBBoatEntity.BoatType.MAGIC;
		else if (item == BiomesOPlenty.MAHOGANY_BOAT)
			return EBBoatEntity.BoatType.MAHOGANY;
		else if (item == BiomesOPlenty.PALM_BOAT)
			return EBBoatEntity.BoatType.PALM;
		else if (item == BiomesOPlenty.REDWOOD_BOAT)
			return EBBoatEntity.BoatType.REDWOOD;
		else if (item == BiomesOPlenty.UMBRAN_BOAT)
			return EBBoatEntity.BoatType.UMBRAN;
		else if (item == BiomesOPlenty.WILLOW_BOAT)
			return EBBoatEntity.BoatType.WILLOW;

		else if (item == UpgradeAquatic.DRIFTWOOD_BOAT)
			return EBBoatEntity.BoatType.DRIFTWOOD;
		else if (item == UpgradeAquatic.RIVER_BOAT)
			return EBBoatEntity.BoatType.RIVER;

		else if (item == BambooBlocks.BAMBOO_BOAT)
			return EBBoatEntity.BoatType.BAMBOO;

		else if (item == EndergeticExpansion.POISE_BOAT)
			return EBBoatEntity.BoatType.POISE;

		else if (item == Environmental.WISTERIA_BOAT)
			return EBBoatEntity.BoatType.WISTERIA;
		else if (item == Environmental.WILLOW_BOAT)
			return EBBoatEntity.BoatType.ENV_WILLOW;
		else if (item == Environmental.CHERRY_BOAT)
			return EBBoatEntity.BoatType.ENV_CHERRY;

		else if (item == Atmospheric.ROSEWOOD_BOAT)
			return EBBoatEntity.BoatType.ROSEWOOD;
		else if (item == Atmospheric.MORADO_BOAT)
			return EBBoatEntity.BoatType.MORADO;
		else if (item == Atmospheric.ASPEN_BOAT)
			return EBBoatEntity.BoatType.ASPEN;
		else if (item == Atmospheric.KOUSA_BOAT)
			return EBBoatEntity.BoatType.KOUSA;
		else if (item == Atmospheric.YUCCA_BOAT)
			return EBBoatEntity.BoatType.YUCCA;
		else if (item == Atmospheric.GRIMWOOD_BOAT)
			return EBBoatEntity.BoatType.GRIMWOOD;

		else if (item == Autumnity.MAPLE_BOAT)
			return EBBoatEntity.BoatType.MAPLE;

		else if (item == EnhancedMushrooms.RED_MUSHROOM_BOAT)
			return EBBoatEntity.BoatType.RED_MUSHROOM;
		else if (item == EnhancedMushrooms.BROWN_MUSHROOM_BOAT)
			return EBBoatEntity.BoatType.BROWN_MUSHROOM;
		else if (item == EnhancedMushrooms.GLOWSHROOM_BOAT)
			return EBBoatEntity.BoatType.GLOWSHROOM;

		else if (item == Hanami.SAKURA_BOAT)
			return EBBoatEntity.BoatType.SAKURA;
		
		else if (item == NetherExtension.CRIMSON_BOAT)
			return EBBoatEntity.BoatType.CRIMSON;
		else
			return EBBoatEntity.BoatType.WARPED;
	}
}