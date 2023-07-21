package com.teamabnormals.boatload.core.registry;

import com.mojang.datafixers.util.Pair;
import com.teamabnormals.boatload.core.Boatload;
import com.teamabnormals.boatload.core.api.BoatloadBoatType;
import com.teamabnormals.boatload.core.registry.helper.BoatloadItemSubRegistryHelper;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.util.MutableHashedLinkedMap;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
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

	public static RegistryObject<Item> CHERRY_FURNACE_BOAT = HELPER.createFurnaceBoat(BoatloadBoatType.CHERRY);
	public static RegistryObject<Item> LARGE_CHERRY_BOAT = HELPER.createLargeBoat(BoatloadBoatType.CHERRY);
	
	public static RegistryObject<Item> BAMBOO_FURNACE_RAFT = HELPER.createFurnaceBoat(BoatloadBoatType.BAMBOO);
	public static RegistryObject<Item> LARGE_BAMBOO_RAFT = HELPER.createLargeBoat(BoatloadBoatType.BAMBOO);

	public static Pair<RegistryObject<Item>, RegistryObject<Item>> CRIMSON_BOATS = HELPER.createBoatAndChestBoatItem("crimson", Blocks.CRIMSON_PLANKS, false);
	public static RegistryObject<Item> CRIMSON_BOAT = CRIMSON_BOATS.getFirst();
	public static RegistryObject<Item> CRIMSON_CHEST_BOAT = CRIMSON_BOATS.getSecond();
	public static RegistryObject<Item> CRIMSON_FURNACE_BOAT = HELPER.createFurnaceBoat(BoatloadBoatType.CRIMSON);
	public static RegistryObject<Item> LARGE_CRIMSON_BOAT = HELPER.createLargeBoat(BoatloadBoatType.CRIMSON);

	public static Pair<RegistryObject<Item>, RegistryObject<Item>> WARPED_BOATS = HELPER.createBoatAndChestBoatItem("warped", Blocks.WARPED_PLANKS, false);
	public static RegistryObject<Item> WARPED_BOAT = WARPED_BOATS.getFirst();
	public static RegistryObject<Item> WARPED_CHEST_BOAT = WARPED_BOATS.getSecond();
	public static RegistryObject<Item> WARPED_FURNACE_BOAT = HELPER.createFurnaceBoat(BoatloadBoatType.WARPED);
	public static RegistryObject<Item> LARGE_WARPED_BOAT = HELPER.createLargeBoat(BoatloadBoatType.WARPED);
	
	@SubscribeEvent
	public static void addItemsToTabs(BuildCreativeModeTabContentsEvent event) {
		MutableHashedLinkedMap<ItemStack, CreativeModeTab.TabVisibility> map = event.getEntries();
		if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
			expandVanillaBoatItems(map, Items.OAK_CHEST_BOAT, OAK_FURNACE_BOAT.get(), LARGE_OAK_BOAT.get());
			expandVanillaBoatItems(map, Items.SPRUCE_CHEST_BOAT, SPRUCE_FURNACE_BOAT.get(), LARGE_SPRUCE_BOAT.get());
			expandVanillaBoatItems(map, Items.BIRCH_CHEST_BOAT, BIRCH_FURNACE_BOAT.get(), LARGE_BIRCH_BOAT.get());
			expandVanillaBoatItems(map, Items.JUNGLE_CHEST_BOAT, JUNGLE_FURNACE_BOAT.get(), LARGE_JUNGLE_BOAT.get());
			expandVanillaBoatItems(map, Items.ACACIA_CHEST_BOAT, ACACIA_FURNACE_BOAT.get(), LARGE_ACACIA_BOAT.get());
			expandVanillaBoatItems(map, Items.DARK_OAK_CHEST_BOAT, DARK_OAK_FURNACE_BOAT.get(), LARGE_DARK_OAK_BOAT.get());
			expandVanillaBoatItems(map, Items.MANGROVE_CHEST_BOAT, MANGROVE_FURNACE_BOAT.get(), LARGE_MANGROVE_BOAT.get());
			expandVanillaBoatItems(map, Items.CHERRY_CHEST_BOAT, CHERRY_FURNACE_BOAT.get(), LARGE_CHERRY_BOAT.get());
			expandVanillaBoatItems(map, Items.BAMBOO_CHEST_RAFT, BAMBOO_FURNACE_RAFT.get(), LARGE_BAMBOO_RAFT.get());
			addBoatItems(map, LARGE_BAMBOO_RAFT.get(), CRIMSON_BOAT.get(), CRIMSON_CHEST_BOAT.get(), CRIMSON_FURNACE_BOAT.get(), LARGE_CRIMSON_BOAT.get());
			addBoatItems(map, LARGE_CRIMSON_BOAT.get(), WARPED_BOAT.get(), WARPED_CHEST_BOAT.get(), WARPED_FURNACE_BOAT.get(), LARGE_WARPED_BOAT.get());
		}
	}
	
	public static void expandVanillaBoatItems(MutableHashedLinkedMap<ItemStack, CreativeModeTab.TabVisibility> map, Item originalItem, Item furnaceBoatItem, Item largeBoatItem) {
		map.putAfter(originalItem.getDefaultInstance(), furnaceBoatItem.getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
		map.putAfter(furnaceBoatItem.getDefaultInstance(), largeBoatItem.getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	}
	
	public static void addBoatItems(MutableHashedLinkedMap<ItemStack, CreativeModeTab.TabVisibility> map, Item appendAfterItem, Item baseBoatItem, Item chestBoatItem, Item furnaceBoatItem, Item largeBoatItem) {
		map.putAfter(appendAfterItem.getDefaultInstance(), baseBoatItem.getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
		map.putAfter(baseBoatItem.getDefaultInstance(), chestBoatItem.getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
		map.putAfter(chestBoatItem.getDefaultInstance(), furnaceBoatItem.getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
		map.putAfter(furnaceBoatItem.getDefaultInstance(), largeBoatItem.getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	}
}