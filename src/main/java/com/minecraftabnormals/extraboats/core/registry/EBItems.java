package com.minecraftabnormals.extraboats.core.registry;

import com.minecraftabnormals.extraboats.common.entity.item.boat.ExtraBoatsBoatEntity;
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

	public static RegistryObject<Item> OAK_CHEST_BOAT = createChestBoat("oak", ExtraBoatsBoatEntity.BoatType.OAK, true);
	public static RegistryObject<Item> OAK_FURNACE_BOAT = createFurnaceBoat("oak", ExtraBoatsBoatEntity.BoatType.OAK, true);
	public static RegistryObject<Item> LARGE_OAK_BOAT = createLargeBoat("oak", ExtraBoatsBoatEntity.BoatType.OAK, true);

	public static RegistryObject<Item> SPRUCE_CHEST_BOAT = createChestBoat("spruce", ExtraBoatsBoatEntity.BoatType.SPRUCE, true);
	public static RegistryObject<Item> SPRUCE_FURNACE_BOAT = createFurnaceBoat("spruce", ExtraBoatsBoatEntity.BoatType.SPRUCE, true);
	public static RegistryObject<Item> LARGE_SPRUCE_BOAT = createLargeBoat("spruce", ExtraBoatsBoatEntity.BoatType.SPRUCE, true);

	public static RegistryObject<Item> BIRCH_CHEST_BOAT = createChestBoat("birch", ExtraBoatsBoatEntity.BoatType.BIRCH, true);
	public static RegistryObject<Item> BIRCH_FURNACE_BOAT = createFurnaceBoat("birch", ExtraBoatsBoatEntity.BoatType.BIRCH, true);
	public static RegistryObject<Item> LARGE_BIRCH_BOAT = createLargeBoat("birch", ExtraBoatsBoatEntity.BoatType.BIRCH, true);

	public static RegistryObject<Item> JUNGLE_CHEST_BOAT = createChestBoat("jungle", ExtraBoatsBoatEntity.BoatType.JUNGLE, true);
	public static RegistryObject<Item> JUNGLE_FURNACE_BOAT = createFurnaceBoat("jungle", ExtraBoatsBoatEntity.BoatType.JUNGLE, true);
	public static RegistryObject<Item> LARGE_JUNGLE_BOAT = createLargeBoat("jungle", ExtraBoatsBoatEntity.BoatType.JUNGLE, true);

	public static RegistryObject<Item> ACACIA_CHEST_BOAT = createChestBoat("acacia", ExtraBoatsBoatEntity.BoatType.ACACIA, true);
	public static RegistryObject<Item> ACACIA_FURNACE_BOAT = createFurnaceBoat("acacia", ExtraBoatsBoatEntity.BoatType.ACACIA, true);
	public static RegistryObject<Item> LARGE_ACACIA_BOAT = createLargeBoat("acacia", ExtraBoatsBoatEntity.BoatType.ACACIA, true);

	public static RegistryObject<Item> DARK_OAK_CHEST_BOAT = createChestBoat("dark_oak", ExtraBoatsBoatEntity.BoatType.DARK_OAK, true);
	public static RegistryObject<Item> DARK_OAK_FURNACE_BOAT = createFurnaceBoat("dark_oak", ExtraBoatsBoatEntity.BoatType.DARK_OAK, true);
	public static RegistryObject<Item> LARGE_DARK_OAK_BOAT = createLargeBoat("dark_oak", ExtraBoatsBoatEntity.BoatType.DARK_OAK, true);

	// Biomes O' Plenty
	public static RegistryObject<Item> CHERRY_CHEST_BOAT = createChestBoat("cherry", ExtraBoatsBoatEntity.BoatType.CHERRY, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> CHERRY_FURNACE_BOAT = createFurnaceBoat("cherry", ExtraBoatsBoatEntity.BoatType.CHERRY, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> LARGE_CHERRY_BOAT = createLargeBoat("cherry", ExtraBoatsBoatEntity.BoatType.CHERRY, BiomesOPlenty.isInstalled());

	public static RegistryObject<Item> DEAD_CHEST_BOAT = createChestBoat("dead", ExtraBoatsBoatEntity.BoatType.DEAD, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> DEAD_FURNACE_BOAT = createFurnaceBoat("dead", ExtraBoatsBoatEntity.BoatType.DEAD, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> LARGE_DEAD_BOAT = createLargeBoat("dead", ExtraBoatsBoatEntity.BoatType.DEAD, BiomesOPlenty.isInstalled());

	public static RegistryObject<Item> FIR_CHEST_BOAT = createChestBoat("fir", ExtraBoatsBoatEntity.BoatType.FIR, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> FIR_FURNACE_BOAT = createFurnaceBoat("fir", ExtraBoatsBoatEntity.BoatType.FIR, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> LARGE_FIR_BOAT = createLargeBoat("fir", ExtraBoatsBoatEntity.BoatType.FIR, BiomesOPlenty.isInstalled());

	public static RegistryObject<Item> HELLBARK_CHEST_BOAT = createChestBoat("hellbark", ExtraBoatsBoatEntity.BoatType.HELLBARK, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> HELLBARK_FURNACE_BOAT = createFurnaceBoat("hellbark", ExtraBoatsBoatEntity.BoatType.HELLBARK, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> LARGE_HELLBARK_BOAT = createLargeBoat("hellbark", ExtraBoatsBoatEntity.BoatType.HELLBARK, BiomesOPlenty.isInstalled());

	public static RegistryObject<Item> JACARANDA_CHEST_BOAT = createChestBoat("jacaranda", ExtraBoatsBoatEntity.BoatType.JACARANDA, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> JACARANDA_FURNACE_BOAT = createFurnaceBoat("jacaranda", ExtraBoatsBoatEntity.BoatType.JACARANDA, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> LARGE_JACARANDA_BOAT = createLargeBoat("jacaranda", ExtraBoatsBoatEntity.BoatType.JACARANDA, BiomesOPlenty.isInstalled());

	public static RegistryObject<Item> MAGIC_CHEST_BOAT = createChestBoat("magic", ExtraBoatsBoatEntity.BoatType.MAGIC, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> MAGIC_FURNACE_BOAT = createFurnaceBoat("magic", ExtraBoatsBoatEntity.BoatType.MAGIC, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> LARGE_MAGIC_BOAT = createLargeBoat("magic", ExtraBoatsBoatEntity.BoatType.MAGIC, BiomesOPlenty.isInstalled());

	public static RegistryObject<Item> MAHOGANY_CHEST_BOAT = createChestBoat("mahogany", ExtraBoatsBoatEntity.BoatType.MAHOGANY, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> MAHOGANY_FURNACE_BOAT = createFurnaceBoat("mahogany", ExtraBoatsBoatEntity.BoatType.MAHOGANY, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> LARGE_MAHOGANY_BOAT = createLargeBoat("mahogany", ExtraBoatsBoatEntity.BoatType.MAHOGANY, BiomesOPlenty.isInstalled());

	public static RegistryObject<Item> PALM_CHEST_BOAT = createChestBoat("palm", ExtraBoatsBoatEntity.BoatType.PALM, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> PALM_FURNACE_BOAT = createFurnaceBoat("palm", ExtraBoatsBoatEntity.BoatType.PALM, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> LARGE_PALM_BOAT = createLargeBoat("palm", ExtraBoatsBoatEntity.BoatType.PALM, BiomesOPlenty.isInstalled());

	public static RegistryObject<Item> REDWOOD_CHEST_BOAT = createChestBoat("redwood", ExtraBoatsBoatEntity.BoatType.REDWOOD, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> REDWOOD_FURNACE_BOAT = createFurnaceBoat("redwood", ExtraBoatsBoatEntity.BoatType.REDWOOD, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> LARGE_REDWOOD_BOAT = createLargeBoat("redwood", ExtraBoatsBoatEntity.BoatType.REDWOOD, BiomesOPlenty.isInstalled());

	public static RegistryObject<Item> UMBRAN_CHEST_BOAT = createChestBoat("umbran", ExtraBoatsBoatEntity.BoatType.UMBRAN, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> UMBRAN_FURNACE_BOAT = createFurnaceBoat("umbran", ExtraBoatsBoatEntity.BoatType.UMBRAN, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> LARGE_UMBRAN_BOAT = createLargeBoat("umbran", ExtraBoatsBoatEntity.BoatType.UMBRAN, BiomesOPlenty.isInstalled());

	public static RegistryObject<Item> WILLOW_CHEST_BOAT = createChestBoat("willow", ExtraBoatsBoatEntity.BoatType.WILLOW, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> WILLOW_FURNACE_BOAT = createFurnaceBoat("willow", ExtraBoatsBoatEntity.BoatType.WILLOW, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> LARGE_WILLOW_BOAT = createLargeBoat("willow", ExtraBoatsBoatEntity.BoatType.WILLOW, BiomesOPlenty.isInstalled());

	// Upgrade Aquatic
	public static RegistryObject<Item> DRIFTWOOD_CHEST_BOAT = createChestBoat("driftwood", ExtraBoatsBoatEntity.BoatType.DRIFTWOOD, UpgradeAquatic.isInstalled());
	public static RegistryObject<Item> DRIFTWOOD_FURNACE_BOAT = createFurnaceBoat("driftwood", ExtraBoatsBoatEntity.BoatType.DRIFTWOOD, UpgradeAquatic.isInstalled());
	public static RegistryObject<Item> LARGE_DRIFTWOOD_BOAT = createLargeBoat("driftwood", ExtraBoatsBoatEntity.BoatType.DRIFTWOOD, UpgradeAquatic.isInstalled());

	public static RegistryObject<Item> RIVER_CHEST_BOAT = createChestBoat("river", ExtraBoatsBoatEntity.BoatType.RIVER, UpgradeAquatic.isInstalled());
	public static RegistryObject<Item> RIVER_FURNACE_BOAT = createFurnaceBoat("river", ExtraBoatsBoatEntity.BoatType.RIVER, UpgradeAquatic.isInstalled());
	public static RegistryObject<Item> LARGE_RIVER_BOAT = createLargeBoat("river", ExtraBoatsBoatEntity.BoatType.RIVER, UpgradeAquatic.isInstalled());

	// Bamboo Blocks
	public static RegistryObject<Item> BAMBOO_CHEST_BOAT = createChestBoat("bamboo", ExtraBoatsBoatEntity.BoatType.BAMBOO, BambooBlocks.isInstalled());
	public static RegistryObject<Item> BAMBOO_FURNACE_BOAT = createFurnaceBoat("bamboo", ExtraBoatsBoatEntity.BoatType.BAMBOO, BambooBlocks.isInstalled());
	public static RegistryObject<Item> LARGE_BAMBOO_BOAT = createLargeBoat("bamboo", ExtraBoatsBoatEntity.BoatType.BAMBOO, BambooBlocks.isInstalled());

	// Endergetic Expansion
	public static RegistryObject<Item> POISE_CHEST_BOAT = createChestBoat("poise", ExtraBoatsBoatEntity.BoatType.POISE, EndergeticExpansion.isInstalled());
	public static RegistryObject<Item> POISE_FURNACE_BOAT = createFurnaceBoat("poise", ExtraBoatsBoatEntity.BoatType.POISE, EndergeticExpansion.isInstalled());
	public static RegistryObject<Item> LARGE_POISE_BOAT = createLargeBoat("poise", ExtraBoatsBoatEntity.BoatType.POISE, EndergeticExpansion.isInstalled());

	// Environmental
	public static RegistryObject<Item> WISTERIA_CHEST_BOAT = createChestBoat("wisteria", ExtraBoatsBoatEntity.BoatType.WISTERIA, Environmental.isInstalled());
	public static RegistryObject<Item> WISTERIA_FURNACE_BOAT = createFurnaceBoat("wisteria", ExtraBoatsBoatEntity.BoatType.WISTERIA, Environmental.isInstalled());
	public static RegistryObject<Item> LARGE_WISTERIA_BOAT = createLargeBoat("wisteria", ExtraBoatsBoatEntity.BoatType.WISTERIA, Environmental.isInstalled());

	public static RegistryObject<Item> ENV_WILLOW_CHEST_BOAT = createChestBoat("env_willow", ExtraBoatsBoatEntity.BoatType.ENV_WILLOW, Environmental.isInstalled());
	public static RegistryObject<Item> ENV_WILLOW_FURNACE_BOAT = createFurnaceBoat("env_willow", ExtraBoatsBoatEntity.BoatType.ENV_WILLOW, Environmental.isInstalled());
	public static RegistryObject<Item> LARGE_ENV_WILLOW_BOAT = createLargeBoat("env_willow", ExtraBoatsBoatEntity.BoatType.ENV_WILLOW, Environmental.isInstalled());

	public static RegistryObject<Item> ENV_CHERRY_CHEST_BOAT = createChestBoat("env_cherry", ExtraBoatsBoatEntity.BoatType.ENV_CHERRY, Environmental.isInstalled());
	public static RegistryObject<Item> ENV_CHERRY_FURNACE_BOAT = createFurnaceBoat("env_cherry", ExtraBoatsBoatEntity.BoatType.ENV_CHERRY, Environmental.isInstalled());
	public static RegistryObject<Item> LARGE_ENV_CHERRY_BOAT = createLargeBoat("env_cherry", ExtraBoatsBoatEntity.BoatType.ENV_CHERRY, Environmental.isInstalled());

	// Atmospheric
	public static RegistryObject<Item> ROSEWOOD_CHEST_BOAT = createChestBoat("rosewood", ExtraBoatsBoatEntity.BoatType.ROSEWOOD, Atmospheric.isInstalled());
	public static RegistryObject<Item> ROSEWOOD_FURNACE_BOAT = createFurnaceBoat("rosewood", ExtraBoatsBoatEntity.BoatType.ROSEWOOD, Atmospheric.isInstalled());
	public static RegistryObject<Item> LARGE_ROSEWOOD_BOAT = createLargeBoat("rosewood", ExtraBoatsBoatEntity.BoatType.ROSEWOOD, Atmospheric.isInstalled());

	public static RegistryObject<Item> MORADO_CHEST_BOAT = createChestBoat("morado", ExtraBoatsBoatEntity.BoatType.MORADO, Atmospheric.isInstalled());
	public static RegistryObject<Item> MORADO_FURNACE_BOAT = createFurnaceBoat("morado", ExtraBoatsBoatEntity.BoatType.MORADO, Atmospheric.isInstalled());
	public static RegistryObject<Item> LARGE_MORADO_BOAT = createLargeBoat("morado", ExtraBoatsBoatEntity.BoatType.MORADO, Atmospheric.isInstalled());

	public static RegistryObject<Item> ASPEN_CHEST_BOAT = createChestBoat("aspen", ExtraBoatsBoatEntity.BoatType.ASPEN, Atmospheric.isInstalled());
	public static RegistryObject<Item> ASPEN_FURNACE_BOAT = createFurnaceBoat("aspen", ExtraBoatsBoatEntity.BoatType.ASPEN, Atmospheric.isInstalled());
	public static RegistryObject<Item> LARGE_ASPEN_BOAT = createLargeBoat("aspen", ExtraBoatsBoatEntity.BoatType.ASPEN, Atmospheric.isInstalled());

	public static RegistryObject<Item> KOUSA_CHEST_BOAT = createChestBoat("kousa", ExtraBoatsBoatEntity.BoatType.KOUSA, Atmospheric.isInstalled());
	public static RegistryObject<Item> KOUSA_FURNACE_BOAT = createFurnaceBoat("kousa", ExtraBoatsBoatEntity.BoatType.KOUSA, Atmospheric.isInstalled());
	public static RegistryObject<Item> LARGE_KOUSA_BOAT = createLargeBoat("kousa", ExtraBoatsBoatEntity.BoatType.KOUSA, Atmospheric.isInstalled());

	public static RegistryObject<Item> YUCCA_CHEST_BOAT = createChestBoat("yucca", ExtraBoatsBoatEntity.BoatType.YUCCA, Atmospheric.isInstalled());
	public static RegistryObject<Item> YUCCA_FURNACE_BOAT = createFurnaceBoat("yucca", ExtraBoatsBoatEntity.BoatType.YUCCA, Atmospheric.isInstalled());
	public static RegistryObject<Item> LARGE_YUCCA_BOAT = createLargeBoat("yucca", ExtraBoatsBoatEntity.BoatType.YUCCA, Atmospheric.isInstalled());

	public static RegistryObject<Item> GRIMWOOD_CHEST_BOAT = createChestBoat("grimwood", ExtraBoatsBoatEntity.BoatType.GRIMWOOD, Atmospheric.isInstalled());
	public static RegistryObject<Item> GRIMWOOD_FURNACE_BOAT = createFurnaceBoat("grimwood", ExtraBoatsBoatEntity.BoatType.GRIMWOOD, Atmospheric.isInstalled());
	public static RegistryObject<Item> LARGE_GRIMWOOD_BOAT = createLargeBoat("grimwood", ExtraBoatsBoatEntity.BoatType.GRIMWOOD, Atmospheric.isInstalled());

	// Autumnity
	public static RegistryObject<Item> MAPLE_CHEST_BOAT = createChestBoat("maple", ExtraBoatsBoatEntity.BoatType.MAPLE, Autumnity.isInstalled());
	public static RegistryObject<Item> MAPLE_FURNACE_BOAT = createFurnaceBoat("maple", ExtraBoatsBoatEntity.BoatType.MAPLE, Autumnity.isInstalled());
	public static RegistryObject<Item> LARGE_MAPLE_BOAT = createLargeBoat("maple", ExtraBoatsBoatEntity.BoatType.MAPLE, Autumnity.isInstalled());

	// Enhanced Mushrooms
	public static RegistryObject<Item> RED_MUSHROOM_CHEST_BOAT = createChestBoat("red_mushroom", ExtraBoatsBoatEntity.BoatType.RED_MUSHROOM, EnhancedMushrooms.isInstalled());
	public static RegistryObject<Item> RED_MUSHROOM_FURNACE_BOAT = createFurnaceBoat("red_mushroom", ExtraBoatsBoatEntity.BoatType.RED_MUSHROOM, EnhancedMushrooms.isInstalled());
	public static RegistryObject<Item> LARGE_RED_MUSHROOM_BOAT = createLargeBoat("red_mushroom", ExtraBoatsBoatEntity.BoatType.RED_MUSHROOM, EnhancedMushrooms.isInstalled());

	public static RegistryObject<Item> BROWN_MUSHROOM_CHEST_BOAT = createChestBoat("brown_mushroom", ExtraBoatsBoatEntity.BoatType.BROWN_MUSHROOM, EnhancedMushrooms.isInstalled());
	public static RegistryObject<Item> BROWN_MUSHROOM_FURNACE_BOAT = createFurnaceBoat("brown_mushroom", ExtraBoatsBoatEntity.BoatType.BROWN_MUSHROOM, EnhancedMushrooms.isInstalled());
	public static RegistryObject<Item> LARGE_BROWN_MUSHROOM_BOAT = createLargeBoat("brown_mushroom", ExtraBoatsBoatEntity.BoatType.BROWN_MUSHROOM, EnhancedMushrooms.isInstalled());

	public static RegistryObject<Item> GLOWSHROOM_CHEST_BOAT = createChestBoat("glowshroom", ExtraBoatsBoatEntity.BoatType.GLOWSHROOM, EnhancedMushrooms.isInstalled());
	public static RegistryObject<Item> GLOWSHROOM_FURNACE_BOAT = createFurnaceBoat("glowshroom", ExtraBoatsBoatEntity.BoatType.GLOWSHROOM, EnhancedMushrooms.isInstalled());
	public static RegistryObject<Item> LARGE_GLOWSHROOM_BOAT = createLargeBoat("glowshroom", ExtraBoatsBoatEntity.BoatType.GLOWSHROOM, EnhancedMushrooms.isInstalled());

	// Hanami
	public static RegistryObject<Item> SAKURA_CHEST_BOAT = createChestBoat("sakura", ExtraBoatsBoatEntity.BoatType.SAKURA, Hanami.isInstalled());
	public static RegistryObject<Item> SAKURA_FURNACE_BOAT = createFurnaceBoat("sakura", ExtraBoatsBoatEntity.BoatType.SAKURA, Hanami.isInstalled());
	public static RegistryObject<Item> LARGE_SAKURA_BOAT = createLargeBoat("sakura", ExtraBoatsBoatEntity.BoatType.SAKURA, Hanami.isInstalled());
	
	// Nether Extension
	public static RegistryObject<Item> CRIMSON_CHEST_BOAT = createChestBoat("crimson", ExtraBoatsBoatEntity.BoatType.CRIMSON, NetherExtension.isInstalled());
	public static RegistryObject<Item> CRIMSON_FURNACE_BOAT = createFurnaceBoat("crimson", ExtraBoatsBoatEntity.BoatType.CRIMSON, NetherExtension.isInstalled());
	public static RegistryObject<Item> LARGE_CRIMSON_BOAT = createLargeBoat("crimson", ExtraBoatsBoatEntity.BoatType.CRIMSON, NetherExtension.isInstalled());
	
	public static RegistryObject<Item> WARPED_CHEST_BOAT = createChestBoat("warped", ExtraBoatsBoatEntity.BoatType.WARPED, NetherExtension.isInstalled());
	public static RegistryObject<Item> WARPED_FURNACE_BOAT = createFurnaceBoat("warped", ExtraBoatsBoatEntity.BoatType.WARPED, NetherExtension.isInstalled());
	public static RegistryObject<Item> LARGE_WARPED_BOAT = createLargeBoat("warped", ExtraBoatsBoatEntity.BoatType.WARPED, NetherExtension.isInstalled());

	private static RegistryObject<Item> createChestBoat(String name, ExtraBoatsBoatEntity.BoatType type, boolean compat) {
		return ITEMS.register(name + "_chest_boat", () -> new ChestBoatItem(type, (new Item.Properties()).stacksTo(1).tab(compat ? ItemGroup.TAB_TRANSPORTATION : null)));
	}

	private static RegistryObject<Item> createFurnaceBoat(String name, ExtraBoatsBoatEntity.BoatType type, boolean compat) {
		return ITEMS.register(name + "_furnace_boat", () -> new FurnaceBoatItem(type, (new Item.Properties()).stacksTo(1).tab(compat ? ItemGroup.TAB_TRANSPORTATION : null)));
	}

	private static RegistryObject<Item> createLargeBoat(String name, ExtraBoatsBoatEntity.BoatType type, boolean compat) {
		return ITEMS.register("large_" + name + "_boat", () -> new LargeBoatItem(type, (new Item.Properties()).stacksTo(1).tab(compat ? ItemGroup.TAB_TRANSPORTATION : null)));
	}
}