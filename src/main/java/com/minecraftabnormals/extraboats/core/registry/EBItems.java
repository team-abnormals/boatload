package com.minecraftabnormals.extraboats.core.registry;

import com.minecraftabnormals.extraboats.common.entity.item.boat.EBBoatEntity;
import com.minecraftabnormals.extraboats.common.item.ChestBoatItem;
import com.minecraftabnormals.extraboats.common.item.FurnaceBoatItem;
import com.minecraftabnormals.extraboats.common.item.LargeBoatItem;
import com.minecraftabnormals.extraboats.core.ExtraBoats;
import com.minecraftabnormals.extraboats.core.compatibility.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class EBItems {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ExtraBoats.MOD_ID);

	public static RegistryObject<Item> OAK_CHEST_BOAT = createChestBoat("oak", EBBoatEntity.BoatType.OAK, true);
	public static RegistryObject<Item> OAK_FURNACE_BOAT = createFurnaceBoat("oak", EBBoatEntity.BoatType.OAK, true);
	public static RegistryObject<Item> LARGE_OAK_BOAT = createLargeBoat("oak", EBBoatEntity.BoatType.OAK, true);

	public static RegistryObject<Item> SPRUCE_CHEST_BOAT = createChestBoat("spruce", EBBoatEntity.BoatType.SPRUCE, true);
	public static RegistryObject<Item> SPRUCE_FURNACE_BOAT = createFurnaceBoat("spruce", EBBoatEntity.BoatType.SPRUCE, true);
	public static RegistryObject<Item> LARGE_SPRUCE_BOAT = createLargeBoat("spruce", EBBoatEntity.BoatType.SPRUCE, true);

	public static RegistryObject<Item> BIRCH_CHEST_BOAT = createChestBoat("birch", EBBoatEntity.BoatType.BIRCH, true);
	public static RegistryObject<Item> BIRCH_FURNACE_BOAT = createFurnaceBoat("birch", EBBoatEntity.BoatType.BIRCH, true);
	public static RegistryObject<Item> LARGE_BIRCH_BOAT = createLargeBoat("birch", EBBoatEntity.BoatType.BIRCH, true);

	public static RegistryObject<Item> JUNGLE_CHEST_BOAT = createChestBoat("jungle", EBBoatEntity.BoatType.JUNGLE, true);
	public static RegistryObject<Item> JUNGLE_FURNACE_BOAT = createFurnaceBoat("jungle", EBBoatEntity.BoatType.JUNGLE, true);
	public static RegistryObject<Item> LARGE_JUNGLE_BOAT = createLargeBoat("jungle", EBBoatEntity.BoatType.JUNGLE, true);

	public static RegistryObject<Item> ACACIA_CHEST_BOAT = createChestBoat("acacia", EBBoatEntity.BoatType.ACACIA, true);
	public static RegistryObject<Item> ACACIA_FURNACE_BOAT = createFurnaceBoat("acacia", EBBoatEntity.BoatType.ACACIA, true);
	public static RegistryObject<Item> LARGE_ACACIA_BOAT = createLargeBoat("acacia", EBBoatEntity.BoatType.ACACIA, true);

	public static RegistryObject<Item> DARK_OAK_CHEST_BOAT = createChestBoat("dark_oak", EBBoatEntity.BoatType.DARK_OAK, true);
	public static RegistryObject<Item> DARK_OAK_FURNACE_BOAT = createFurnaceBoat("dark_oak", EBBoatEntity.BoatType.DARK_OAK, true);
	public static RegistryObject<Item> LARGE_DARK_OAK_BOAT = createLargeBoat("dark_oak", EBBoatEntity.BoatType.DARK_OAK, true);

	// Biomes O' Plenty
	public static RegistryObject<Item> CHERRY_CHEST_BOAT = createChestBoat("cherry", EBBoatEntity.BoatType.CHERRY, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> CHERRY_FURNACE_BOAT = createFurnaceBoat("cherry", EBBoatEntity.BoatType.CHERRY, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> LARGE_CHERRY_BOAT = createLargeBoat("cherry", EBBoatEntity.BoatType.CHERRY, BiomesOPlenty.isInstalled());

	public static RegistryObject<Item> DEAD_CHEST_BOAT = createChestBoat("dead", EBBoatEntity.BoatType.DEAD, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> DEAD_FURNACE_BOAT = createFurnaceBoat("dead", EBBoatEntity.BoatType.DEAD, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> LARGE_DEAD_BOAT = createLargeBoat("dead", EBBoatEntity.BoatType.DEAD, BiomesOPlenty.isInstalled());

	public static RegistryObject<Item> FIR_CHEST_BOAT = createChestBoat("fir", EBBoatEntity.BoatType.FIR, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> FIR_FURNACE_BOAT = createFurnaceBoat("fir", EBBoatEntity.BoatType.FIR, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> LARGE_FIR_BOAT = createLargeBoat("fir", EBBoatEntity.BoatType.FIR, BiomesOPlenty.isInstalled());

	public static RegistryObject<Item> HELLBARK_CHEST_BOAT = createChestBoat("hellbark", EBBoatEntity.BoatType.HELLBARK, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> HELLBARK_FURNACE_BOAT = createFurnaceBoat("hellbark", EBBoatEntity.BoatType.HELLBARK, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> LARGE_HELLBARK_BOAT = createLargeBoat("hellbark", EBBoatEntity.BoatType.HELLBARK, BiomesOPlenty.isInstalled());

	public static RegistryObject<Item> JACARANDA_CHEST_BOAT = createChestBoat("jacaranda", EBBoatEntity.BoatType.JACARANDA, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> JACARANDA_FURNACE_BOAT = createFurnaceBoat("jacaranda", EBBoatEntity.BoatType.JACARANDA, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> LARGE_JACARANDA_BOAT = createLargeBoat("jacaranda", EBBoatEntity.BoatType.JACARANDA, BiomesOPlenty.isInstalled());

	public static RegistryObject<Item> MAGIC_CHEST_BOAT = createChestBoat("magic", EBBoatEntity.BoatType.MAGIC, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> MAGIC_FURNACE_BOAT = createFurnaceBoat("magic", EBBoatEntity.BoatType.MAGIC, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> LARGE_MAGIC_BOAT = createLargeBoat("magic", EBBoatEntity.BoatType.MAGIC, BiomesOPlenty.isInstalled());

	public static RegistryObject<Item> MAHOGANY_CHEST_BOAT = createChestBoat("mahogany", EBBoatEntity.BoatType.MAHOGANY, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> MAHOGANY_FURNACE_BOAT = createFurnaceBoat("mahogany", EBBoatEntity.BoatType.MAHOGANY, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> LARGE_MAHOGANY_BOAT = createLargeBoat("mahogany", EBBoatEntity.BoatType.MAHOGANY, BiomesOPlenty.isInstalled());

	public static RegistryObject<Item> PALM_CHEST_BOAT = createChestBoat("palm", EBBoatEntity.BoatType.PALM, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> PALM_FURNACE_BOAT = createFurnaceBoat("palm", EBBoatEntity.BoatType.PALM, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> LARGE_PALM_BOAT = createLargeBoat("palm", EBBoatEntity.BoatType.PALM, BiomesOPlenty.isInstalled());

	public static RegistryObject<Item> REDWOOD_CHEST_BOAT = createChestBoat("redwood", EBBoatEntity.BoatType.REDWOOD, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> REDWOOD_FURNACE_BOAT = createFurnaceBoat("redwood", EBBoatEntity.BoatType.REDWOOD, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> LARGE_REDWOOD_BOAT = createLargeBoat("redwood", EBBoatEntity.BoatType.REDWOOD, BiomesOPlenty.isInstalled());

	public static RegistryObject<Item> UMBRAN_CHEST_BOAT = createChestBoat("umbran", EBBoatEntity.BoatType.UMBRAN, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> UMBRAN_FURNACE_BOAT = createFurnaceBoat("umbran", EBBoatEntity.BoatType.UMBRAN, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> LARGE_UMBRAN_BOAT = createLargeBoat("umbran", EBBoatEntity.BoatType.UMBRAN, BiomesOPlenty.isInstalled());

	public static RegistryObject<Item> WILLOW_CHEST_BOAT = createChestBoat("willow", EBBoatEntity.BoatType.WILLOW, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> WILLOW_FURNACE_BOAT = createFurnaceBoat("willow", EBBoatEntity.BoatType.WILLOW, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> LARGE_WILLOW_BOAT = createLargeBoat("willow", EBBoatEntity.BoatType.WILLOW, BiomesOPlenty.isInstalled());

	// Upgrade Aquatic
	public static RegistryObject<Item> DRIFTWOOD_CHEST_BOAT = createChestBoat("driftwood", EBBoatEntity.BoatType.DRIFTWOOD, UpgradeAquatic.isInstalled());
	public static RegistryObject<Item> DRIFTWOOD_FURNACE_BOAT = createFurnaceBoat("driftwood", EBBoatEntity.BoatType.DRIFTWOOD, UpgradeAquatic.isInstalled());
	public static RegistryObject<Item> LARGE_DRIFTWOOD_BOAT = createLargeBoat("driftwood", EBBoatEntity.BoatType.DRIFTWOOD, UpgradeAquatic.isInstalled());

	public static RegistryObject<Item> RIVER_CHEST_BOAT = createChestBoat("river", EBBoatEntity.BoatType.RIVER, UpgradeAquatic.isInstalled());
	public static RegistryObject<Item> RIVER_FURNACE_BOAT = createFurnaceBoat("river", EBBoatEntity.BoatType.RIVER, UpgradeAquatic.isInstalled());
	public static RegistryObject<Item> LARGE_RIVER_BOAT = createLargeBoat("river", EBBoatEntity.BoatType.RIVER, UpgradeAquatic.isInstalled());

	// Bamboo Blocks
	public static RegistryObject<Item> BAMBOO_CHEST_BOAT = createChestBoat("bamboo", EBBoatEntity.BoatType.BAMBOO, BambooBlocks.isInstalled());
	public static RegistryObject<Item> BAMBOO_FURNACE_BOAT = createFurnaceBoat("bamboo", EBBoatEntity.BoatType.BAMBOO, BambooBlocks.isInstalled());
	public static RegistryObject<Item> LARGE_BAMBOO_BOAT = createLargeBoat("bamboo", EBBoatEntity.BoatType.BAMBOO, BambooBlocks.isInstalled());

	// Endergetic Expansion
	public static RegistryObject<Item> POISE_CHEST_BOAT = createChestBoat("poise", EBBoatEntity.BoatType.POISE, EndergeticExpansion.isInstalled());
	public static RegistryObject<Item> POISE_FURNACE_BOAT = createFurnaceBoat("poise", EBBoatEntity.BoatType.POISE, EndergeticExpansion.isInstalled());
	public static RegistryObject<Item> LARGE_POISE_BOAT = createLargeBoat("poise", EBBoatEntity.BoatType.POISE, EndergeticExpansion.isInstalled());

	// Environmental
	public static RegistryObject<Item> WISTERIA_CHEST_BOAT = createChestBoat("wisteria", EBBoatEntity.BoatType.WISTERIA, Environmental.isInstalled());
	public static RegistryObject<Item> WISTERIA_FURNACE_BOAT = createFurnaceBoat("wisteria", EBBoatEntity.BoatType.WISTERIA, Environmental.isInstalled());
	public static RegistryObject<Item> LARGE_WISTERIA_BOAT = createLargeBoat("wisteria", EBBoatEntity.BoatType.WISTERIA, Environmental.isInstalled());

	public static RegistryObject<Item> ENV_WILLOW_CHEST_BOAT = createChestBoat("env_willow", EBBoatEntity.BoatType.ENV_WILLOW, Environmental.isInstalled());
	public static RegistryObject<Item> ENV_WILLOW_FURNACE_BOAT = createFurnaceBoat("env_willow", EBBoatEntity.BoatType.ENV_WILLOW, Environmental.isInstalled());
	public static RegistryObject<Item> LARGE_ENV_WILLOW_BOAT = createLargeBoat("env_willow", EBBoatEntity.BoatType.ENV_WILLOW, Environmental.isInstalled());

	public static RegistryObject<Item> ENV_CHERRY_CHEST_BOAT = createChestBoat("env_cherry", EBBoatEntity.BoatType.ENV_CHERRY, Environmental.isInstalled());
	public static RegistryObject<Item> ENV_CHERRY_FURNACE_BOAT = createFurnaceBoat("env_cherry", EBBoatEntity.BoatType.ENV_CHERRY, Environmental.isInstalled());
	public static RegistryObject<Item> LARGE_ENV_CHERRY_BOAT = createLargeBoat("env_cherry", EBBoatEntity.BoatType.ENV_CHERRY, Environmental.isInstalled());

	// Atmospheric
	public static RegistryObject<Item> ROSEWOOD_CHEST_BOAT = createChestBoat("rosewood", EBBoatEntity.BoatType.ROSEWOOD, Atmospheric.isInstalled());
	public static RegistryObject<Item> ROSEWOOD_FURNACE_BOAT = createFurnaceBoat("rosewood", EBBoatEntity.BoatType.ROSEWOOD, Atmospheric.isInstalled());
	public static RegistryObject<Item> LARGE_ROSEWOOD_BOAT = createLargeBoat("rosewood", EBBoatEntity.BoatType.ROSEWOOD, Atmospheric.isInstalled());

	public static RegistryObject<Item> MORADO_CHEST_BOAT = createChestBoat("morado", EBBoatEntity.BoatType.MORADO, Atmospheric.isInstalled());
	public static RegistryObject<Item> MORADO_FURNACE_BOAT = createFurnaceBoat("morado", EBBoatEntity.BoatType.MORADO, Atmospheric.isInstalled());
	public static RegistryObject<Item> LARGE_MORADO_BOAT = createLargeBoat("morado", EBBoatEntity.BoatType.MORADO, Atmospheric.isInstalled());

	public static RegistryObject<Item> ASPEN_CHEST_BOAT = createChestBoat("aspen", EBBoatEntity.BoatType.ASPEN, Atmospheric.isInstalled());
	public static RegistryObject<Item> ASPEN_FURNACE_BOAT = createFurnaceBoat("aspen", EBBoatEntity.BoatType.ASPEN, Atmospheric.isInstalled());
	public static RegistryObject<Item> LARGE_ASPEN_BOAT = createLargeBoat("aspen", EBBoatEntity.BoatType.ASPEN, Atmospheric.isInstalled());

	public static RegistryObject<Item> KOUSA_CHEST_BOAT = createChestBoat("kousa", EBBoatEntity.BoatType.KOUSA, Atmospheric.isInstalled());
	public static RegistryObject<Item> KOUSA_FURNACE_BOAT = createFurnaceBoat("kousa", EBBoatEntity.BoatType.KOUSA, Atmospheric.isInstalled());
	public static RegistryObject<Item> LARGE_KOUSA_BOAT = createLargeBoat("kousa", EBBoatEntity.BoatType.KOUSA, Atmospheric.isInstalled());

	public static RegistryObject<Item> YUCCA_CHEST_BOAT = createChestBoat("yucca", EBBoatEntity.BoatType.YUCCA, Atmospheric.isInstalled());
	public static RegistryObject<Item> YUCCA_FURNACE_BOAT = createFurnaceBoat("yucca", EBBoatEntity.BoatType.YUCCA, Atmospheric.isInstalled());
	public static RegistryObject<Item> LARGE_YUCCA_BOAT = createLargeBoat("yucca", EBBoatEntity.BoatType.YUCCA, Atmospheric.isInstalled());

	public static RegistryObject<Item> GRIMWOOD_CHEST_BOAT = createChestBoat("grimwood", EBBoatEntity.BoatType.GRIMWOOD, Atmospheric.isInstalled());
	public static RegistryObject<Item> GRIMWOOD_FURNACE_BOAT = createFurnaceBoat("grimwood", EBBoatEntity.BoatType.GRIMWOOD, Atmospheric.isInstalled());
	public static RegistryObject<Item> LARGE_GRIMWOOD_BOAT = createLargeBoat("grimwood", EBBoatEntity.BoatType.GRIMWOOD, Atmospheric.isInstalled());

	// Autumnity
	public static RegistryObject<Item> MAPLE_CHEST_BOAT = createChestBoat("maple", EBBoatEntity.BoatType.MAPLE, Autumnity.isInstalled());
	public static RegistryObject<Item> MAPLE_FURNACE_BOAT = createFurnaceBoat("maple", EBBoatEntity.BoatType.MAPLE, Autumnity.isInstalled());
	public static RegistryObject<Item> LARGE_MAPLE_BOAT = createLargeBoat("maple", EBBoatEntity.BoatType.MAPLE, Autumnity.isInstalled());

	// Enhanced Mushrooms
	public static RegistryObject<Item> RED_MUSHROOM_CHEST_BOAT = createChestBoat("red_mushroom", EBBoatEntity.BoatType.RED_MUSHROOM, EnhancedMushrooms.isInstalled());
	public static RegistryObject<Item> RED_MUSHROOM_FURNACE_BOAT = createFurnaceBoat("red_mushroom", EBBoatEntity.BoatType.RED_MUSHROOM, EnhancedMushrooms.isInstalled());
	public static RegistryObject<Item> LARGE_RED_MUSHROOM_BOAT = createLargeBoat("red_mushroom", EBBoatEntity.BoatType.RED_MUSHROOM, EnhancedMushrooms.isInstalled());

	public static RegistryObject<Item> BROWN_MUSHROOM_CHEST_BOAT = createChestBoat("brown_mushroom", EBBoatEntity.BoatType.BROWN_MUSHROOM, EnhancedMushrooms.isInstalled());
	public static RegistryObject<Item> BROWN_MUSHROOM_FURNACE_BOAT = createFurnaceBoat("brown_mushroom", EBBoatEntity.BoatType.BROWN_MUSHROOM, EnhancedMushrooms.isInstalled());
	public static RegistryObject<Item> LARGE_BROWN_MUSHROOM_BOAT = createLargeBoat("brown_mushroom", EBBoatEntity.BoatType.BROWN_MUSHROOM, EnhancedMushrooms.isInstalled());

	public static RegistryObject<Item> GLOWSHROOM_CHEST_BOAT = createChestBoat("glowshroom", EBBoatEntity.BoatType.GLOWSHROOM, EnhancedMushrooms.isInstalled());
	public static RegistryObject<Item> GLOWSHROOM_FURNACE_BOAT = createFurnaceBoat("glowshroom", EBBoatEntity.BoatType.GLOWSHROOM, EnhancedMushrooms.isInstalled());
	public static RegistryObject<Item> LARGE_GLOWSHROOM_BOAT = createLargeBoat("glowshroom", EBBoatEntity.BoatType.GLOWSHROOM, EnhancedMushrooms.isInstalled());

	// Hanami
	public static RegistryObject<Item> SAKURA_CHEST_BOAT = createChestBoat("sakura", EBBoatEntity.BoatType.SAKURA, Hanami.isInstalled());
	public static RegistryObject<Item> SAKURA_FURNACE_BOAT = createFurnaceBoat("sakura", EBBoatEntity.BoatType.SAKURA, Hanami.isInstalled());
	public static RegistryObject<Item> LARGE_SAKURA_BOAT = createLargeBoat("sakura", EBBoatEntity.BoatType.SAKURA, Hanami.isInstalled());

	private static RegistryObject<Item> createChestBoat(String name, EBBoatEntity.BoatType type, boolean compat) {
		return ITEMS.register(name + "_chest_boat", () -> new ChestBoatItem(type, (new Item.Properties()).maxStackSize(1).group(compat ? ItemGroup.TRANSPORTATION : null)));
	}

	private static RegistryObject<Item> createFurnaceBoat(String name, EBBoatEntity.BoatType type, boolean compat) {
		return ITEMS.register(name + "_furnace_boat", () -> new FurnaceBoatItem(type, (new Item.Properties()).maxStackSize(1).group(compat ? ItemGroup.TRANSPORTATION : null)));
	}

	private static RegistryObject<Item> createLargeBoat(String name, EBBoatEntity.BoatType type, boolean compat) {
		return ITEMS.register("large_" + name + "_boat", () -> new LargeBoatItem(type, (new Item.Properties()).maxStackSize(1).group(compat ? ItemGroup.TRANSPORTATION : null)));
	}
}