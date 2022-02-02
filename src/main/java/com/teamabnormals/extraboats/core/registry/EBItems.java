package com.teamabnormals.extraboats.core.registry;

import com.teamabnormals.extraboats.common.entity.vehicle.EBBoat.EBBoatType;
import com.teamabnormals.extraboats.core.ExtraBoats;
import com.teamabnormals.extraboats.core.registry.helper.EBItemSubRegistryHelper;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class EBItems {
	public static final EBItemSubRegistryHelper HELPER = ExtraBoats.REGISTRY_HELPER.getItemSubHelper();

	public static RegistryObject<Item> OAK_CHEST_BOAT = HELPER.createChestBoat(EBBoatType.OAK);
	public static RegistryObject<Item> OAK_FURNACE_BOAT = HELPER.createFurnaceBoat(EBBoatType.OAK);
	public static RegistryObject<Item> LARGE_OAK_BOAT = HELPER.createLargeBoat(EBBoatType.OAK);

	public static RegistryObject<Item> SPRUCE_CHEST_BOAT = HELPER.createChestBoat(EBBoatType.SPRUCE);
	public static RegistryObject<Item> SPRUCE_FURNACE_BOAT = HELPER.createFurnaceBoat(EBBoatType.SPRUCE);
	public static RegistryObject<Item> LARGE_SPRUCE_BOAT = HELPER.createLargeBoat(EBBoatType.SPRUCE);

	public static RegistryObject<Item> BIRCH_CHEST_BOAT = HELPER.createChestBoat(EBBoatType.BIRCH);
	public static RegistryObject<Item> BIRCH_FURNACE_BOAT = HELPER.createFurnaceBoat(EBBoatType.BIRCH);
	public static RegistryObject<Item> LARGE_BIRCH_BOAT = HELPER.createLargeBoat(EBBoatType.BIRCH);

	public static RegistryObject<Item> JUNGLE_CHEST_BOAT = HELPER.createChestBoat(EBBoatType.JUNGLE);
	public static RegistryObject<Item> JUNGLE_FURNACE_BOAT = HELPER.createFurnaceBoat(EBBoatType.JUNGLE);
	public static RegistryObject<Item> LARGE_JUNGLE_BOAT = HELPER.createLargeBoat(EBBoatType.JUNGLE);

	public static RegistryObject<Item> ACACIA_CHEST_BOAT = HELPER.createChestBoat(EBBoatType.ACACIA);
	public static RegistryObject<Item> ACACIA_FURNACE_BOAT = HELPER.createFurnaceBoat(EBBoatType.ACACIA);
	public static RegistryObject<Item> LARGE_ACACIA_BOAT = HELPER.createLargeBoat(EBBoatType.ACACIA);

	public static RegistryObject<Item> DARK_OAK_CHEST_BOAT = HELPER.createChestBoat(EBBoatType.DARK_OAK);
	public static RegistryObject<Item> DARK_OAK_FURNACE_BOAT = HELPER.createFurnaceBoat(EBBoatType.DARK_OAK);
	public static RegistryObject<Item> LARGE_DARK_OAK_BOAT = HELPER.createLargeBoat(EBBoatType.DARK_OAK);

	// Upgrade Aquatic
	public static RegistryObject<Item> DRIFTWOOD_CHEST_BOAT = HELPER.createChestBoat(EBBoatType.DRIFTWOOD, "upgrade_aquatic");
	public static RegistryObject<Item> DRIFTWOOD_FURNACE_BOAT = HELPER.createFurnaceBoat(EBBoatType.DRIFTWOOD, "upgrade_aquatic");
	public static RegistryObject<Item> LARGE_DRIFTWOOD_BOAT = HELPER.createLargeBoat(EBBoatType.DRIFTWOOD, "upgrade_aquatic");

	public static RegistryObject<Item> RIVER_CHEST_BOAT = HELPER.createChestBoat(EBBoatType.RIVER, "upgrade_aquatic");
	public static RegistryObject<Item> RIVER_FURNACE_BOAT = HELPER.createFurnaceBoat(EBBoatType.RIVER, "upgrade_aquatic");
	public static RegistryObject<Item> LARGE_RIVER_BOAT = HELPER.createLargeBoat(EBBoatType.RIVER, "upgrade_aquatic");

	// Bamboo Blocks
	public static RegistryObject<Item> BAMBOO_CHEST_BOAT = HELPER.createChestBoat(EBBoatType.BAMBOO, "bamboo_blocks");
	public static RegistryObject<Item> BAMBOO_FURNACE_BOAT = HELPER.createFurnaceBoat(EBBoatType.BAMBOO, "bamboo_blocks");
	public static RegistryObject<Item> LARGE_BAMBOO_BOAT = HELPER.createLargeBoat(EBBoatType.BAMBOO, "bamboo_blocks");

	// Endergetic Expansion
	public static RegistryObject<Item> POISE_CHEST_BOAT = HELPER.createChestBoat(EBBoatType.POISE, "endergetic");
	public static RegistryObject<Item> POISE_FURNACE_BOAT = HELPER.createFurnaceBoat(EBBoatType.POISE, "endergetic");
	public static RegistryObject<Item> LARGE_POISE_BOAT = HELPER.createLargeBoat(EBBoatType.POISE, "endergetic");

	// Environmental
	public static RegistryObject<Item> WISTERIA_CHEST_BOAT = HELPER.createChestBoat(EBBoatType.WISTERIA, "environmental");
	public static RegistryObject<Item> WISTERIA_FURNACE_BOAT = HELPER.createFurnaceBoat(EBBoatType.WISTERIA, "environmental");
	public static RegistryObject<Item> LARGE_WISTERIA_BOAT = HELPER.createLargeBoat(EBBoatType.WISTERIA, "environmental");

	public static RegistryObject<Item> ENV_WILLOW_CHEST_BOAT = HELPER.createChestBoat(EBBoatType.WILLOW, "environmental");
	public static RegistryObject<Item> ENV_WILLOW_FURNACE_BOAT = HELPER.createFurnaceBoat(EBBoatType.WILLOW, "environmental");
	public static RegistryObject<Item> LARGE_ENV_WILLOW_BOAT = HELPER.createLargeBoat(EBBoatType.WILLOW, "environmental");

	public static RegistryObject<Item> ENV_CHERRY_CHEST_BOAT = HELPER.createChestBoat(EBBoatType.CHERRY, "environmental");
	public static RegistryObject<Item> ENV_CHERRY_FURNACE_BOAT = HELPER.createFurnaceBoat(EBBoatType.CHERRY, "environmental");
	public static RegistryObject<Item> LARGE_ENV_CHERRY_BOAT = HELPER.createLargeBoat(EBBoatType.CHERRY, "environmental");

	// Atmospheric
	public static RegistryObject<Item> ROSEWOOD_CHEST_BOAT = HELPER.createChestBoat(EBBoatType.ROSEWOOD, "atmospheric");
	public static RegistryObject<Item> ROSEWOOD_FURNACE_BOAT = HELPER.createFurnaceBoat(EBBoatType.ROSEWOOD, "atmospheric");
	public static RegistryObject<Item> LARGE_ROSEWOOD_BOAT = HELPER.createLargeBoat(EBBoatType.ROSEWOOD, "atmospheric");

	public static RegistryObject<Item> MORADO_CHEST_BOAT = HELPER.createChestBoat(EBBoatType.MORADO, "atmospheric");
	public static RegistryObject<Item> MORADO_FURNACE_BOAT = HELPER.createFurnaceBoat(EBBoatType.MORADO, "atmospheric");
	public static RegistryObject<Item> LARGE_MORADO_BOAT = HELPER.createLargeBoat(EBBoatType.MORADO, "atmospheric");

	public static RegistryObject<Item> ASPEN_CHEST_BOAT = HELPER.createChestBoat(EBBoatType.ASPEN, "atmospheric");
	public static RegistryObject<Item> ASPEN_FURNACE_BOAT = HELPER.createFurnaceBoat(EBBoatType.ASPEN, "atmospheric");
	public static RegistryObject<Item> LARGE_ASPEN_BOAT = HELPER.createLargeBoat(EBBoatType.ASPEN, "atmospheric");

	public static RegistryObject<Item> KOUSA_CHEST_BOAT = HELPER.createChestBoat(EBBoatType.KOUSA, "atmospheric");
	public static RegistryObject<Item> KOUSA_FURNACE_BOAT = HELPER.createFurnaceBoat(EBBoatType.KOUSA, "atmospheric");
	public static RegistryObject<Item> LARGE_KOUSA_BOAT = HELPER.createLargeBoat(EBBoatType.KOUSA, "atmospheric");

	public static RegistryObject<Item> YUCCA_CHEST_BOAT = HELPER.createChestBoat(EBBoatType.YUCCA, "atmospheric");
	public static RegistryObject<Item> YUCCA_FURNACE_BOAT = HELPER.createFurnaceBoat(EBBoatType.YUCCA, "atmospheric");
	public static RegistryObject<Item> LARGE_YUCCA_BOAT = HELPER.createLargeBoat(EBBoatType.YUCCA, "atmospheric");

	public static RegistryObject<Item> GRIMWOOD_CHEST_BOAT = HELPER.createChestBoat(EBBoatType.GRIMWOOD, "atmospheric");
	public static RegistryObject<Item> GRIMWOOD_FURNACE_BOAT = HELPER.createFurnaceBoat(EBBoatType.GRIMWOOD, "atmospheric");
	public static RegistryObject<Item> LARGE_GRIMWOOD_BOAT = HELPER.createLargeBoat(EBBoatType.GRIMWOOD, "atmospheric");

	// Autumnity
	public static RegistryObject<Item> MAPLE_CHEST_BOAT = HELPER.createChestBoat(EBBoatType.MAPLE, "autumnity");
	public static RegistryObject<Item> MAPLE_FURNACE_BOAT = HELPER.createFurnaceBoat(EBBoatType.MAPLE, "autumnity");
	public static RegistryObject<Item> LARGE_MAPLE_BOAT = HELPER.createLargeBoat(EBBoatType.MAPLE, "autumnity");

	// Nether Extension
	public static RegistryObject<Item> CRIMSON_CHEST_BOAT = HELPER.createChestBoat(EBBoatType.CRIMSON, "nether_extension");
	public static RegistryObject<Item> CRIMSON_FURNACE_BOAT = HELPER.createFurnaceBoat(EBBoatType.CRIMSON, "nether_extension");
	public static RegistryObject<Item> LARGE_CRIMSON_BOAT = HELPER.createLargeBoat(EBBoatType.CRIMSON, "nether_extension");

	public static RegistryObject<Item> WARPED_CHEST_BOAT = HELPER.createChestBoat(EBBoatType.WARPED, "nether_extension");
	public static RegistryObject<Item> WARPED_FURNACE_BOAT = HELPER.createFurnaceBoat(EBBoatType.WARPED, "nether_extension");
	public static RegistryObject<Item> LARGE_WARPED_BOAT = HELPER.createLargeBoat(EBBoatType.WARPED, "nether_extension");
}