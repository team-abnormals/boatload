package com.teamabnormals.boatload.core.registry;

import com.teamabnormals.boatload.common.entity.vehicle.BLBoat.BLBoatType;
import com.teamabnormals.boatload.core.Boatload;
import com.teamabnormals.boatload.core.registry.helper.BLItemSubRegistryHelper;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class BLItems {
	public static final BLItemSubRegistryHelper HELPER = Boatload.REGISTRY_HELPER.getItemSubHelper();

	public static RegistryObject<Item> OAK_CHEST_BOAT = HELPER.createChestBoat(BLBoatType.OAK);
	public static RegistryObject<Item> OAK_FURNACE_BOAT = HELPER.createFurnaceBoat(BLBoatType.OAK);
	public static RegistryObject<Item> LARGE_OAK_BOAT = HELPER.createLargeBoat(BLBoatType.OAK);

	public static RegistryObject<Item> SPRUCE_CHEST_BOAT = HELPER.createChestBoat(BLBoatType.SPRUCE);
	public static RegistryObject<Item> SPRUCE_FURNACE_BOAT = HELPER.createFurnaceBoat(BLBoatType.SPRUCE);
	public static RegistryObject<Item> LARGE_SPRUCE_BOAT = HELPER.createLargeBoat(BLBoatType.SPRUCE);

	public static RegistryObject<Item> BIRCH_CHEST_BOAT = HELPER.createChestBoat(BLBoatType.BIRCH);
	public static RegistryObject<Item> BIRCH_FURNACE_BOAT = HELPER.createFurnaceBoat(BLBoatType.BIRCH);
	public static RegistryObject<Item> LARGE_BIRCH_BOAT = HELPER.createLargeBoat(BLBoatType.BIRCH);

	public static RegistryObject<Item> JUNGLE_CHEST_BOAT = HELPER.createChestBoat(BLBoatType.JUNGLE);
	public static RegistryObject<Item> JUNGLE_FURNACE_BOAT = HELPER.createFurnaceBoat(BLBoatType.JUNGLE);
	public static RegistryObject<Item> LARGE_JUNGLE_BOAT = HELPER.createLargeBoat(BLBoatType.JUNGLE);

	public static RegistryObject<Item> ACACIA_CHEST_BOAT = HELPER.createChestBoat(BLBoatType.ACACIA);
	public static RegistryObject<Item> ACACIA_FURNACE_BOAT = HELPER.createFurnaceBoat(BLBoatType.ACACIA);
	public static RegistryObject<Item> LARGE_ACACIA_BOAT = HELPER.createLargeBoat(BLBoatType.ACACIA);

	public static RegistryObject<Item> DARK_OAK_CHEST_BOAT = HELPER.createChestBoat(BLBoatType.DARK_OAK);
	public static RegistryObject<Item> DARK_OAK_FURNACE_BOAT = HELPER.createFurnaceBoat(BLBoatType.DARK_OAK);
	public static RegistryObject<Item> LARGE_DARK_OAK_BOAT = HELPER.createLargeBoat(BLBoatType.DARK_OAK);

	// Upgrade Aquatic
	public static RegistryObject<Item> DRIFTWOOD_CHEST_BOAT = HELPER.createChestBoat(BLBoatType.DRIFTWOOD, "upgrade_aquatic");
	public static RegistryObject<Item> DRIFTWOOD_FURNACE_BOAT = HELPER.createFurnaceBoat(BLBoatType.DRIFTWOOD, "upgrade_aquatic");
	public static RegistryObject<Item> LARGE_DRIFTWOOD_BOAT = HELPER.createLargeBoat(BLBoatType.DRIFTWOOD, "upgrade_aquatic");

	public static RegistryObject<Item> RIVER_CHEST_BOAT = HELPER.createChestBoat(BLBoatType.RIVER, "upgrade_aquatic");
	public static RegistryObject<Item> RIVER_FURNACE_BOAT = HELPER.createFurnaceBoat(BLBoatType.RIVER, "upgrade_aquatic");
	public static RegistryObject<Item> LARGE_RIVER_BOAT = HELPER.createLargeBoat(BLBoatType.RIVER, "upgrade_aquatic");

	// Bamboo Blocks
	public static RegistryObject<Item> BAMBOO_CHEST_BOAT = HELPER.createChestBoat(BLBoatType.BAMBOO, "bamboo_blocks");
	public static RegistryObject<Item> BAMBOO_FURNACE_BOAT = HELPER.createFurnaceBoat(BLBoatType.BAMBOO, "bamboo_blocks");
	public static RegistryObject<Item> LARGE_BAMBOO_BOAT = HELPER.createLargeBoat(BLBoatType.BAMBOO, "bamboo_blocks");

	// Endergetic Expansion
	public static RegistryObject<Item> POISE_CHEST_BOAT = HELPER.createChestBoat(BLBoatType.POISE, "endergetic");
	public static RegistryObject<Item> POISE_FURNACE_BOAT = HELPER.createFurnaceBoat(BLBoatType.POISE, "endergetic");
	public static RegistryObject<Item> LARGE_POISE_BOAT = HELPER.createLargeBoat(BLBoatType.POISE, "endergetic");

	// Environmental
	public static RegistryObject<Item> WISTERIA_CHEST_BOAT = HELPER.createChestBoat(BLBoatType.WISTERIA, "environmental");
	public static RegistryObject<Item> WISTERIA_FURNACE_BOAT = HELPER.createFurnaceBoat(BLBoatType.WISTERIA, "environmental");
	public static RegistryObject<Item> LARGE_WISTERIA_BOAT = HELPER.createLargeBoat(BLBoatType.WISTERIA, "environmental");

	public static RegistryObject<Item> ENV_WILLOW_CHEST_BOAT = HELPER.createChestBoat(BLBoatType.WILLOW, "environmental");
	public static RegistryObject<Item> ENV_WILLOW_FURNACE_BOAT = HELPER.createFurnaceBoat(BLBoatType.WILLOW, "environmental");
	public static RegistryObject<Item> LARGE_ENV_WILLOW_BOAT = HELPER.createLargeBoat(BLBoatType.WILLOW, "environmental");

	public static RegistryObject<Item> ENV_CHERRY_CHEST_BOAT = HELPER.createChestBoat(BLBoatType.CHERRY, "environmental");
	public static RegistryObject<Item> ENV_CHERRY_FURNACE_BOAT = HELPER.createFurnaceBoat(BLBoatType.CHERRY, "environmental");
	public static RegistryObject<Item> LARGE_ENV_CHERRY_BOAT = HELPER.createLargeBoat(BLBoatType.CHERRY, "environmental");

	// Atmospheric
	public static RegistryObject<Item> ROSEWOOD_CHEST_BOAT = HELPER.createChestBoat(BLBoatType.ROSEWOOD, "atmospheric");
	public static RegistryObject<Item> ROSEWOOD_FURNACE_BOAT = HELPER.createFurnaceBoat(BLBoatType.ROSEWOOD, "atmospheric");
	public static RegistryObject<Item> LARGE_ROSEWOOD_BOAT = HELPER.createLargeBoat(BLBoatType.ROSEWOOD, "atmospheric");

	public static RegistryObject<Item> MORADO_CHEST_BOAT = HELPER.createChestBoat(BLBoatType.MORADO, "atmospheric");
	public static RegistryObject<Item> MORADO_FURNACE_BOAT = HELPER.createFurnaceBoat(BLBoatType.MORADO, "atmospheric");
	public static RegistryObject<Item> LARGE_MORADO_BOAT = HELPER.createLargeBoat(BLBoatType.MORADO, "atmospheric");

	public static RegistryObject<Item> ASPEN_CHEST_BOAT = HELPER.createChestBoat(BLBoatType.ASPEN, "atmospheric");
	public static RegistryObject<Item> ASPEN_FURNACE_BOAT = HELPER.createFurnaceBoat(BLBoatType.ASPEN, "atmospheric");
	public static RegistryObject<Item> LARGE_ASPEN_BOAT = HELPER.createLargeBoat(BLBoatType.ASPEN, "atmospheric");

	public static RegistryObject<Item> KOUSA_CHEST_BOAT = HELPER.createChestBoat(BLBoatType.KOUSA, "atmospheric");
	public static RegistryObject<Item> KOUSA_FURNACE_BOAT = HELPER.createFurnaceBoat(BLBoatType.KOUSA, "atmospheric");
	public static RegistryObject<Item> LARGE_KOUSA_BOAT = HELPER.createLargeBoat(BLBoatType.KOUSA, "atmospheric");

	public static RegistryObject<Item> YUCCA_CHEST_BOAT = HELPER.createChestBoat(BLBoatType.YUCCA, "atmospheric");
	public static RegistryObject<Item> YUCCA_FURNACE_BOAT = HELPER.createFurnaceBoat(BLBoatType.YUCCA, "atmospheric");
	public static RegistryObject<Item> LARGE_YUCCA_BOAT = HELPER.createLargeBoat(BLBoatType.YUCCA, "atmospheric");

	public static RegistryObject<Item> GRIMWOOD_CHEST_BOAT = HELPER.createChestBoat(BLBoatType.GRIMWOOD, "atmospheric");
	public static RegistryObject<Item> GRIMWOOD_FURNACE_BOAT = HELPER.createFurnaceBoat(BLBoatType.GRIMWOOD, "atmospheric");
	public static RegistryObject<Item> LARGE_GRIMWOOD_BOAT = HELPER.createLargeBoat(BLBoatType.GRIMWOOD, "atmospheric");

	// Autumnity
	public static RegistryObject<Item> MAPLE_CHEST_BOAT = HELPER.createChestBoat(BLBoatType.MAPLE, "autumnity");
	public static RegistryObject<Item> MAPLE_FURNACE_BOAT = HELPER.createFurnaceBoat(BLBoatType.MAPLE, "autumnity");
	public static RegistryObject<Item> LARGE_MAPLE_BOAT = HELPER.createLargeBoat(BLBoatType.MAPLE, "autumnity");

	// Nether Extension
	public static RegistryObject<Item> CRIMSON_CHEST_BOAT = HELPER.createChestBoat(BLBoatType.CRIMSON, "nether_extension");
	public static RegistryObject<Item> CRIMSON_FURNACE_BOAT = HELPER.createFurnaceBoat(BLBoatType.CRIMSON, "nether_extension");
	public static RegistryObject<Item> LARGE_CRIMSON_BOAT = HELPER.createLargeBoat(BLBoatType.CRIMSON, "nether_extension");

	public static RegistryObject<Item> WARPED_CHEST_BOAT = HELPER.createChestBoat(BLBoatType.WARPED, "nether_extension");
	public static RegistryObject<Item> WARPED_FURNACE_BOAT = HELPER.createFurnaceBoat(BLBoatType.WARPED, "nether_extension");
	public static RegistryObject<Item> LARGE_WARPED_BOAT = HELPER.createLargeBoat(BLBoatType.WARPED, "nether_extension");
}