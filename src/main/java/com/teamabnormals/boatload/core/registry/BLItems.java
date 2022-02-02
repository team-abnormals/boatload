package com.teamabnormals.boatload.core.registry;

import com.teamabnormals.boatload.core.api.ExtraBoatType;
import com.teamabnormals.boatload.core.Boatload;
import com.teamabnormals.boatload.core.registry.helper.BLItemSubRegistryHelper;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class BLItems {
	public static final BLItemSubRegistryHelper HELPER = Boatload.REGISTRY_HELPER.getItemSubHelper();

	public static RegistryObject<Item> OAK_CHEST_BOAT = HELPER.createChestBoat(ExtraBoatType.OAK);
	public static RegistryObject<Item> OAK_FURNACE_BOAT = HELPER.createFurnaceBoat(ExtraBoatType.OAK);
	public static RegistryObject<Item> LARGE_OAK_BOAT = HELPER.createLargeBoat(ExtraBoatType.OAK);

	public static RegistryObject<Item> SPRUCE_CHEST_BOAT = HELPER.createChestBoat(ExtraBoatType.SPRUCE);
	public static RegistryObject<Item> SPRUCE_FURNACE_BOAT = HELPER.createFurnaceBoat(ExtraBoatType.SPRUCE);
	public static RegistryObject<Item> LARGE_SPRUCE_BOAT = HELPER.createLargeBoat(ExtraBoatType.SPRUCE);

	public static RegistryObject<Item> BIRCH_CHEST_BOAT = HELPER.createChestBoat(ExtraBoatType.BIRCH);
	public static RegistryObject<Item> BIRCH_FURNACE_BOAT = HELPER.createFurnaceBoat(ExtraBoatType.BIRCH);
	public static RegistryObject<Item> LARGE_BIRCH_BOAT = HELPER.createLargeBoat(ExtraBoatType.BIRCH);

	public static RegistryObject<Item> JUNGLE_CHEST_BOAT = HELPER.createChestBoat(ExtraBoatType.JUNGLE);
	public static RegistryObject<Item> JUNGLE_FURNACE_BOAT = HELPER.createFurnaceBoat(ExtraBoatType.JUNGLE);
	public static RegistryObject<Item> LARGE_JUNGLE_BOAT = HELPER.createLargeBoat(ExtraBoatType.JUNGLE);

	public static RegistryObject<Item> ACACIA_CHEST_BOAT = HELPER.createChestBoat(ExtraBoatType.ACACIA);
	public static RegistryObject<Item> ACACIA_FURNACE_BOAT = HELPER.createFurnaceBoat(ExtraBoatType.ACACIA);
	public static RegistryObject<Item> LARGE_ACACIA_BOAT = HELPER.createLargeBoat(ExtraBoatType.ACACIA);

	public static RegistryObject<Item> DARK_OAK_CHEST_BOAT = HELPER.createChestBoat(ExtraBoatType.DARK_OAK);
	public static RegistryObject<Item> DARK_OAK_FURNACE_BOAT = HELPER.createFurnaceBoat(ExtraBoatType.DARK_OAK);
	public static RegistryObject<Item> LARGE_DARK_OAK_BOAT = HELPER.createLargeBoat(ExtraBoatType.DARK_OAK);

	// Upgrade Aquatic
	public static RegistryObject<Item> DRIFTWOOD_CHEST_BOAT = HELPER.createChestBoat(ExtraBoatType.DRIFTWOOD, "upgrade_aquatic");
	public static RegistryObject<Item> DRIFTWOOD_FURNACE_BOAT = HELPER.createFurnaceBoat(ExtraBoatType.DRIFTWOOD, "upgrade_aquatic");
	public static RegistryObject<Item> LARGE_DRIFTWOOD_BOAT = HELPER.createLargeBoat(ExtraBoatType.DRIFTWOOD, "upgrade_aquatic");

	public static RegistryObject<Item> RIVER_CHEST_BOAT = HELPER.createChestBoat(ExtraBoatType.RIVER, "upgrade_aquatic");
	public static RegistryObject<Item> RIVER_FURNACE_BOAT = HELPER.createFurnaceBoat(ExtraBoatType.RIVER, "upgrade_aquatic");
	public static RegistryObject<Item> LARGE_RIVER_BOAT = HELPER.createLargeBoat(ExtraBoatType.RIVER, "upgrade_aquatic");

	// Bamboo Blocks
	public static RegistryObject<Item> BAMBOO_CHEST_BOAT = HELPER.createChestBoat(ExtraBoatType.BAMBOO, "bamboo_blocks");
	public static RegistryObject<Item> BAMBOO_FURNACE_BOAT = HELPER.createFurnaceBoat(ExtraBoatType.BAMBOO, "bamboo_blocks");
	public static RegistryObject<Item> LARGE_BAMBOO_BOAT = HELPER.createLargeBoat(ExtraBoatType.BAMBOO, "bamboo_blocks");

	// Endergetic Expansion
	public static RegistryObject<Item> POISE_CHEST_BOAT = HELPER.createChestBoat(ExtraBoatType.POISE, "endergetic");
	public static RegistryObject<Item> POISE_FURNACE_BOAT = HELPER.createFurnaceBoat(ExtraBoatType.POISE, "endergetic");
	public static RegistryObject<Item> LARGE_POISE_BOAT = HELPER.createLargeBoat(ExtraBoatType.POISE, "endergetic");

	// Environmental
	public static RegistryObject<Item> WISTERIA_CHEST_BOAT = HELPER.createChestBoat(ExtraBoatType.WISTERIA, "environmental");
	public static RegistryObject<Item> WISTERIA_FURNACE_BOAT = HELPER.createFurnaceBoat(ExtraBoatType.WISTERIA, "environmental");
	public static RegistryObject<Item> LARGE_WISTERIA_BOAT = HELPER.createLargeBoat(ExtraBoatType.WISTERIA, "environmental");

	public static RegistryObject<Item> ENV_WILLOW_CHEST_BOAT = HELPER.createChestBoat(ExtraBoatType.WILLOW, "environmental");
	public static RegistryObject<Item> ENV_WILLOW_FURNACE_BOAT = HELPER.createFurnaceBoat(ExtraBoatType.WILLOW, "environmental");
	public static RegistryObject<Item> LARGE_ENV_WILLOW_BOAT = HELPER.createLargeBoat(ExtraBoatType.WILLOW, "environmental");

	public static RegistryObject<Item> ENV_CHERRY_CHEST_BOAT = HELPER.createChestBoat(ExtraBoatType.CHERRY, "environmental");
	public static RegistryObject<Item> ENV_CHERRY_FURNACE_BOAT = HELPER.createFurnaceBoat(ExtraBoatType.CHERRY, "environmental");
	public static RegistryObject<Item> LARGE_ENV_CHERRY_BOAT = HELPER.createLargeBoat(ExtraBoatType.CHERRY, "environmental");

	// Atmospheric
	public static RegistryObject<Item> ROSEWOOD_CHEST_BOAT = HELPER.createChestBoat(ExtraBoatType.ROSEWOOD, "atmospheric");
	public static RegistryObject<Item> ROSEWOOD_FURNACE_BOAT = HELPER.createFurnaceBoat(ExtraBoatType.ROSEWOOD, "atmospheric");
	public static RegistryObject<Item> LARGE_ROSEWOOD_BOAT = HELPER.createLargeBoat(ExtraBoatType.ROSEWOOD, "atmospheric");

	public static RegistryObject<Item> MORADO_CHEST_BOAT = HELPER.createChestBoat(ExtraBoatType.MORADO, "atmospheric");
	public static RegistryObject<Item> MORADO_FURNACE_BOAT = HELPER.createFurnaceBoat(ExtraBoatType.MORADO, "atmospheric");
	public static RegistryObject<Item> LARGE_MORADO_BOAT = HELPER.createLargeBoat(ExtraBoatType.MORADO, "atmospheric");

	public static RegistryObject<Item> ASPEN_CHEST_BOAT = HELPER.createChestBoat(ExtraBoatType.ASPEN, "atmospheric");
	public static RegistryObject<Item> ASPEN_FURNACE_BOAT = HELPER.createFurnaceBoat(ExtraBoatType.ASPEN, "atmospheric");
	public static RegistryObject<Item> LARGE_ASPEN_BOAT = HELPER.createLargeBoat(ExtraBoatType.ASPEN, "atmospheric");

	public static RegistryObject<Item> KOUSA_CHEST_BOAT = HELPER.createChestBoat(ExtraBoatType.KOUSA, "atmospheric");
	public static RegistryObject<Item> KOUSA_FURNACE_BOAT = HELPER.createFurnaceBoat(ExtraBoatType.KOUSA, "atmospheric");
	public static RegistryObject<Item> LARGE_KOUSA_BOAT = HELPER.createLargeBoat(ExtraBoatType.KOUSA, "atmospheric");

	public static RegistryObject<Item> YUCCA_CHEST_BOAT = HELPER.createChestBoat(ExtraBoatType.YUCCA, "atmospheric");
	public static RegistryObject<Item> YUCCA_FURNACE_BOAT = HELPER.createFurnaceBoat(ExtraBoatType.YUCCA, "atmospheric");
	public static RegistryObject<Item> LARGE_YUCCA_BOAT = HELPER.createLargeBoat(ExtraBoatType.YUCCA, "atmospheric");

	public static RegistryObject<Item> GRIMWOOD_CHEST_BOAT = HELPER.createChestBoat(ExtraBoatType.GRIMWOOD, "atmospheric");
	public static RegistryObject<Item> GRIMWOOD_FURNACE_BOAT = HELPER.createFurnaceBoat(ExtraBoatType.GRIMWOOD, "atmospheric");
	public static RegistryObject<Item> LARGE_GRIMWOOD_BOAT = HELPER.createLargeBoat(ExtraBoatType.GRIMWOOD, "atmospheric");

	// Autumnity
	public static RegistryObject<Item> MAPLE_CHEST_BOAT = HELPER.createChestBoat(ExtraBoatType.MAPLE, "autumnity");
	public static RegistryObject<Item> MAPLE_FURNACE_BOAT = HELPER.createFurnaceBoat(ExtraBoatType.MAPLE, "autumnity");
	public static RegistryObject<Item> LARGE_MAPLE_BOAT = HELPER.createLargeBoat(ExtraBoatType.MAPLE, "autumnity");

	// Nether Extension
	public static RegistryObject<Item> CRIMSON_CHEST_BOAT = HELPER.createChestBoat(ExtraBoatType.CRIMSON, "nether_extension");
	public static RegistryObject<Item> CRIMSON_FURNACE_BOAT = HELPER.createFurnaceBoat(ExtraBoatType.CRIMSON, "nether_extension");
	public static RegistryObject<Item> LARGE_CRIMSON_BOAT = HELPER.createLargeBoat(ExtraBoatType.CRIMSON, "nether_extension");

	public static RegistryObject<Item> WARPED_CHEST_BOAT = HELPER.createChestBoat(ExtraBoatType.WARPED, "nether_extension");
	public static RegistryObject<Item> WARPED_FURNACE_BOAT = HELPER.createFurnaceBoat(ExtraBoatType.WARPED, "nether_extension");
	public static RegistryObject<Item> LARGE_WARPED_BOAT = HELPER.createLargeBoat(ExtraBoatType.WARPED, "nether_extension");
}