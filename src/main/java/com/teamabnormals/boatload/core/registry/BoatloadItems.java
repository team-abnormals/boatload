package com.teamabnormals.boatload.core.registry;

import com.mojang.datafixers.util.Pair;
import com.teamabnormals.boatload.core.Boatload;
import com.teamabnormals.boatload.core.api.BoatloadBoatType;
import com.teamabnormals.boatload.core.registry.helper.BoatloadItemSubRegistryHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class BoatloadItems {
	public static final BoatloadItemSubRegistryHelper HELPER = Boatload.REGISTRY_HELPER.getItemSubHelper();

	public static RegistryObject<Item> OAK_FURNACE_BOAT = HELPER.createFurnaceBoat(BoatloadBoatType.OAK);
	public static RegistryObject<Item> LARGE_OAK_BOAT = HELPER.createLargeBoat(BoatloadBoatType.OAK);

	public static RegistryObject<Item> SPRUCE_FURNACE_BOAT = HELPER.createFurnaceBoat(BoatloadBoatType.SPRUCE);
	public static RegistryObject<Item> LARGE_SPRUCE_BOAT = HELPER.createLargeBoat(BoatloadBoatType.SPRUCE);

	public static RegistryObject<Item> BIRCH_FURNACE_BOAT = HELPER.createFurnaceBoat(BoatloadBoatType.BIRCH);
	public static RegistryObject<Item> LARGE_BIRCH_BOAT = HELPER.createLargeBoat(BoatloadBoatType.BIRCH);

	public static RegistryObject<Item> JUNGLE_FURNACE_BOAT = HELPER.createFurnaceBoat(BoatloadBoatType.JUNGLE);
	public static RegistryObject<Item> LARGE_JUNGLE_BOAT = HELPER.createLargeBoat(BoatloadBoatType.JUNGLE);

	public static RegistryObject<Item> ACACIA_FURNACE_BOAT = HELPER.createFurnaceBoat(BoatloadBoatType.ACACIA);
	public static RegistryObject<Item> LARGE_ACACIA_BOAT = HELPER.createLargeBoat(BoatloadBoatType.ACACIA);

	public static RegistryObject<Item> DARK_OAK_FURNACE_BOAT = HELPER.createFurnaceBoat(BoatloadBoatType.DARK_OAK);
	public static RegistryObject<Item> LARGE_DARK_OAK_BOAT = HELPER.createLargeBoat(BoatloadBoatType.DARK_OAK);

	public static RegistryObject<Item> MANGROVE_FURNACE_BOAT = HELPER.createFurnaceBoat(BoatloadBoatType.MANGROVE);
	public static RegistryObject<Item> LARGE_MANGROVE_BOAT = HELPER.createLargeBoat(BoatloadBoatType.MANGROVE);

	public static Pair<RegistryObject<Item>, RegistryObject<Item>> CRIMSON_BOATS = HELPER.createBoatAndChestBoatItem("crimson", Blocks.CRIMSON_PLANKS);
	public static RegistryObject<Item> CRIMSON_BOAT = CRIMSON_BOATS.getFirst();
	public static RegistryObject<Item> CRIMSON_CHEST_BOAT = CRIMSON_BOATS.getSecond();
	public static RegistryObject<Item> CRIMSON_FURNACE_BOAT = HELPER.createFurnaceBoat(BoatloadBoatType.CRIMSON);
	public static RegistryObject<Item> LARGE_CRIMSON_BOAT = HELPER.createLargeBoat(BoatloadBoatType.CRIMSON);

	public static Pair<RegistryObject<Item>, RegistryObject<Item>> WARPED_BOATS = HELPER.createBoatAndChestBoatItem("warped", Blocks.WARPED_PLANKS);
	public static RegistryObject<Item> WARPED_BOAT = WARPED_BOATS.getFirst();
	public static RegistryObject<Item> WARPED_CHEST_BOAT = WARPED_BOATS.getSecond();
	public static RegistryObject<Item> WARPED_FURNACE_BOAT = HELPER.createFurnaceBoat(BoatloadBoatType.WARPED);
	public static RegistryObject<Item> LARGE_WARPED_BOAT = HELPER.createLargeBoat(BoatloadBoatType.WARPED);
}