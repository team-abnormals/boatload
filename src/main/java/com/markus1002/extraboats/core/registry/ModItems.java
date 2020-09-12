package com.markus1002.extraboats.core.registry;

import com.markus1002.extraboats.common.entity.item.boat.ModBoatEntity;
import com.markus1002.extraboats.common.item.ChestBoatItem;
import com.markus1002.extraboats.common.item.FurnaceBoatItem;
import com.markus1002.extraboats.common.item.LargeBoatItem;
import com.markus1002.extraboats.core.Reference;
import com.markus1002.extraboats.core.compatibility.Atmospheric;
import com.markus1002.extraboats.core.compatibility.Autumnity;
import com.markus1002.extraboats.core.compatibility.BambooBlocks;
import com.markus1002.extraboats.core.compatibility.BiomesOPlenty;
import com.markus1002.extraboats.core.compatibility.EndergeticExpansion;
import com.markus1002.extraboats.core.compatibility.EnhancedMushrooms;
import com.markus1002.extraboats.core.compatibility.Environmental;
import com.markus1002.extraboats.core.compatibility.Hanami;
import com.markus1002.extraboats.core.compatibility.UpgradeAquatic;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItems
{
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);
	
	public static RegistryObject<Item> OAK_CHEST_BOAT = createChestBoat("oak_chest_boat", ModBoatEntity.BoatType.OAK, true);
	public static RegistryObject<Item> OAK_FURNACE_BOAT = createFurnaceBoat("oak_furnace_boat", ModBoatEntity.BoatType.OAK, true);
	public static RegistryObject<Item> LARGE_OAK_BOAT = createLargeBoat("large_oak_boat", ModBoatEntity.BoatType.OAK, true);
	
	public static RegistryObject<Item> SPRUCE_CHEST_BOAT = createChestBoat("spruce_chest_boat", ModBoatEntity.BoatType.SPRUCE, true);
	public static RegistryObject<Item> SPRUCE_FURNACE_BOAT = createFurnaceBoat("spruce_furnace_boat", ModBoatEntity.BoatType.SPRUCE, true);
	public static RegistryObject<Item> LARGE_SPRUCE_BOAT = createLargeBoat("large_spruce_boat", ModBoatEntity.BoatType.SPRUCE, true);
	
	public static RegistryObject<Item> BIRCH_CHEST_BOAT = createChestBoat("birch_chest_boat", ModBoatEntity.BoatType.BIRCH, true);
	public static RegistryObject<Item> BIRCH_FURNACE_BOAT = createFurnaceBoat("birch_furnace_boat", ModBoatEntity.BoatType.BIRCH, true);
	public static RegistryObject<Item> LARGE_BIRCH_BOAT = createLargeBoat("large_birch_boat", ModBoatEntity.BoatType.BIRCH, true);
	
	public static RegistryObject<Item> JUNGLE_CHEST_BOAT = createChestBoat("jungle_chest_boat", ModBoatEntity.BoatType.JUNGLE, true);
	public static RegistryObject<Item> JUNGLE_FURNACE_BOAT = createFurnaceBoat("jungle_furnace_boat", ModBoatEntity.BoatType.JUNGLE, true);
	public static RegistryObject<Item> LARGE_JUNGLE_BOAT = createLargeBoat("large_jungle_boat", ModBoatEntity.BoatType.JUNGLE, true);
	
	public static RegistryObject<Item> ACACIA_CHEST_BOAT = createChestBoat("acacia_chest_boat", ModBoatEntity.BoatType.ACACIA, true);
	public static RegistryObject<Item> ACACIA_FURNACE_BOAT = createFurnaceBoat("acacia_furnace_boat", ModBoatEntity.BoatType.ACACIA, true);
	public static RegistryObject<Item> LARGE_ACACIA_BOAT = createLargeBoat("large_acacia_boat", ModBoatEntity.BoatType.ACACIA, true);
	
	public static RegistryObject<Item> DARK_OAK_CHEST_BOAT = createChestBoat("dark_oak_chest_boat", ModBoatEntity.BoatType.DARK_OAK, true);
	public static RegistryObject<Item> DARK_OAK_FURNACE_BOAT = createFurnaceBoat("dark_oak_furnace_boat", ModBoatEntity.BoatType.DARK_OAK, true);
	public static RegistryObject<Item> LARGE_DARK_OAK_BOAT = createLargeBoat("large_dark_oak_boat", ModBoatEntity.BoatType.DARK_OAK, true);

	// Biomes O' Plenty
	public static RegistryObject<Item> CHERRY_CHEST_BOAT = createChestBoat("cherry_chest_boat", ModBoatEntity.BoatType.CHERRY, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> CHERRY_FURNACE_BOAT = createFurnaceBoat("cherry_furnace_boat", ModBoatEntity.BoatType.CHERRY, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> LARGE_CHERRY_BOAT = createLargeBoat("large_cherry_boat", ModBoatEntity.BoatType.CHERRY, BiomesOPlenty.isInstalled());
	
	public static RegistryObject<Item> DEAD_CHEST_BOAT = createChestBoat("dead_chest_boat", ModBoatEntity.BoatType.DEAD, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> DEAD_FURNACE_BOAT = createFurnaceBoat("dead_furnace_boat", ModBoatEntity.BoatType.DEAD, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> LARGE_DEAD_BOAT = createLargeBoat("large_dead_boat", ModBoatEntity.BoatType.DEAD, BiomesOPlenty.isInstalled());
	
	public static RegistryObject<Item> FIR_CHEST_BOAT = createChestBoat("fir_chest_boat", ModBoatEntity.BoatType.FIR, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> FIR_FURNACE_BOAT = createFurnaceBoat("fir_furnace_boat", ModBoatEntity.BoatType.FIR, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> LARGE_FIR_BOAT = createLargeBoat("large_fir_boat", ModBoatEntity.BoatType.FIR, BiomesOPlenty.isInstalled());
	
	public static RegistryObject<Item> HELLBARK_CHEST_BOAT = createChestBoat("hellbark_chest_boat", ModBoatEntity.BoatType.HELLBARK, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> HELLBARK_FURNACE_BOAT = createFurnaceBoat("hellbark_furnace_boat", ModBoatEntity.BoatType.HELLBARK, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> LARGE_HELLBARK_BOAT = createLargeBoat("large_hellbark_boat", ModBoatEntity.BoatType.HELLBARK, BiomesOPlenty.isInstalled());
	
	public static RegistryObject<Item> JACARANDA_CHEST_BOAT = createChestBoat("jacaranda_chest_boat", ModBoatEntity.BoatType.JACARANDA, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> JACARANDA_FURNACE_BOAT = createFurnaceBoat("jacaranda_furnace_boat", ModBoatEntity.BoatType.JACARANDA, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> LARGE_JACARANDA_BOAT = createLargeBoat("large_jacaranda_boat", ModBoatEntity.BoatType.JACARANDA, BiomesOPlenty.isInstalled());
	
	public static RegistryObject<Item> MAGIC_CHEST_BOAT = createChestBoat("magic_chest_boat", ModBoatEntity.BoatType.MAGIC, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> MAGIC_FURNACE_BOAT = createFurnaceBoat("magic_furnace_boat", ModBoatEntity.BoatType.MAGIC, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> LARGE_MAGIC_BOAT = createLargeBoat("large_magic_boat", ModBoatEntity.BoatType.MAGIC, BiomesOPlenty.isInstalled());
	
	public static RegistryObject<Item> MAHOGANY_CHEST_BOAT = createChestBoat("mahogany_chest_boat", ModBoatEntity.BoatType.MAHOGANY, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> MAHOGANY_FURNACE_BOAT = createFurnaceBoat("mahogany_furnace_boat", ModBoatEntity.BoatType.MAHOGANY, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> LARGE_MAHOGANY_BOAT = createLargeBoat("large_mahogany_boat", ModBoatEntity.BoatType.MAHOGANY, BiomesOPlenty.isInstalled());
	
	public static RegistryObject<Item> PALM_CHEST_BOAT = createChestBoat("palm_chest_boat", ModBoatEntity.BoatType.PALM, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> PALM_FURNACE_BOAT = createFurnaceBoat("palm_furnace_boat", ModBoatEntity.BoatType.PALM, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> LARGE_PALM_BOAT = createLargeBoat("large_palm_boat", ModBoatEntity.BoatType.PALM, BiomesOPlenty.isInstalled());
	
	public static RegistryObject<Item> REDWOOD_CHEST_BOAT = createChestBoat("redwood_chest_boat", ModBoatEntity.BoatType.REDWOOD, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> REDWOOD_FURNACE_BOAT = createFurnaceBoat("redwood_furnace_boat", ModBoatEntity.BoatType.REDWOOD, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> LARGE_REDWOOD_BOAT = createLargeBoat("large_redwood_boat", ModBoatEntity.BoatType.REDWOOD, BiomesOPlenty.isInstalled());
	
	public static RegistryObject<Item> UMBRAN_CHEST_BOAT = createChestBoat("umbran_chest_boat", ModBoatEntity.BoatType.UMBRAN, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> UMBRAN_FURNACE_BOAT = createFurnaceBoat("umbran_furnace_boat", ModBoatEntity.BoatType.UMBRAN, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> LARGE_UMBRAN_BOAT = createLargeBoat("large_umbran_boat", ModBoatEntity.BoatType.UMBRAN, BiomesOPlenty.isInstalled());
	
	public static RegistryObject<Item> WILLOW_CHEST_BOAT = createChestBoat("willow_chest_boat", ModBoatEntity.BoatType.WILLOW, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> WILLOW_FURNACE_BOAT = createFurnaceBoat("willow_furnace_boat", ModBoatEntity.BoatType.WILLOW, BiomesOPlenty.isInstalled());
	public static RegistryObject<Item> LARGE_WILLOW_BOAT = createLargeBoat("large_willow_boat", ModBoatEntity.BoatType.WILLOW, BiomesOPlenty.isInstalled());

	// Upgrade Aquatic
	public static RegistryObject<Item> DRIFTWOOD_CHEST_BOAT = createChestBoat("driftwood_chest_boat", ModBoatEntity.BoatType.DRIFTWOOD, UpgradeAquatic.isInstalled());
	public static RegistryObject<Item> DRIFTWOOD_FURNACE_BOAT = createFurnaceBoat("driftwood_furnace_boat", ModBoatEntity.BoatType.DRIFTWOOD, UpgradeAquatic.isInstalled());
	public static RegistryObject<Item> LARGE_DRIFTWOOD_BOAT = createLargeBoat("large_driftwood_boat", ModBoatEntity.BoatType.DRIFTWOOD, UpgradeAquatic.isInstalled());
	
	public static RegistryObject<Item> RIVER_CHEST_BOAT = createChestBoat("river_chest_boat", ModBoatEntity.BoatType.RIVER, UpgradeAquatic.isInstalled());
	public static RegistryObject<Item> RIVER_FURNACE_BOAT = createFurnaceBoat("river_furnace_boat", ModBoatEntity.BoatType.RIVER, UpgradeAquatic.isInstalled());
	public static RegistryObject<Item> LARGE_RIVER_BOAT = createLargeBoat("large_river_boat", ModBoatEntity.BoatType.RIVER, UpgradeAquatic.isInstalled());

	// Bamboo Blocks
	public static RegistryObject<Item> BAMBOO_CHEST_BOAT = createChestBoat("bamboo_chest_boat", ModBoatEntity.BoatType.BAMBOO, BambooBlocks.isInstalled());
	public static RegistryObject<Item> BAMBOO_FURNACE_BOAT = createFurnaceBoat("bamboo_furnace_boat", ModBoatEntity.BoatType.BAMBOO, BambooBlocks.isInstalled());
	public static RegistryObject<Item> LARGE_BAMBOO_BOAT = createLargeBoat("large_bamboo_boat", ModBoatEntity.BoatType.BAMBOO, BambooBlocks.isInstalled());

	// Endergetic Expansion
	public static RegistryObject<Item> POISE_CHEST_BOAT = createChestBoat("poise_chest_boat", ModBoatEntity.BoatType.POISE, EndergeticExpansion.isInstalled());
	public static RegistryObject<Item> POISE_FURNACE_BOAT = createFurnaceBoat("poise_furnace_boat", ModBoatEntity.BoatType.POISE, EndergeticExpansion.isInstalled());
	public static RegistryObject<Item> LARGE_POISE_BOAT = createLargeBoat("large_poise_boat", ModBoatEntity.BoatType.POISE, EndergeticExpansion.isInstalled());

	// Environmental
	public static RegistryObject<Item> WISTERIA_CHEST_BOAT = createChestBoat("wisteria_chest_boat", ModBoatEntity.BoatType.WISTERIA, Environmental.isInstalled());
	public static RegistryObject<Item> WISTERIA_FURNACE_BOAT = createFurnaceBoat("wisteria_furnace_boat", ModBoatEntity.BoatType.WISTERIA, Environmental.isInstalled());
	public static RegistryObject<Item> LARGE_WISTERIA_BOAT = createLargeBoat("large_wisteria_boat", ModBoatEntity.BoatType.WISTERIA, Environmental.isInstalled());
	
	public static RegistryObject<Item> ENV_WILLOW_CHEST_BOAT = createChestBoat("env_willow_chest_boat", ModBoatEntity.BoatType.ENV_WILLOW, Environmental.isInstalled());
	public static RegistryObject<Item> ENV_WILLOW_FURNACE_BOAT = createFurnaceBoat("env_willow_furnace_boat", ModBoatEntity.BoatType.ENV_WILLOW, Environmental.isInstalled());
	public static RegistryObject<Item> LARGE_ENV_WILLOW_BOAT = createLargeBoat("large_env_willow_boat", ModBoatEntity.BoatType.ENV_WILLOW, Environmental.isInstalled());
	
	public static RegistryObject<Item> ENV_CHERRY_CHEST_BOAT = createChestBoat("env_cherry_chest_boat", ModBoatEntity.BoatType.ENV_CHERRY, Environmental.isInstalled());
	public static RegistryObject<Item> ENV_CHERRY_FURNACE_BOAT = createFurnaceBoat("env_cherry_furnace_boat", ModBoatEntity.BoatType.ENV_CHERRY, Environmental.isInstalled());
	public static RegistryObject<Item> LARGE_ENV_CHERRY_BOAT = createLargeBoat("large_env_cherry_boat", ModBoatEntity.BoatType.ENV_CHERRY, Environmental.isInstalled());

	// Atmospheric
	public static RegistryObject<Item> ROSEWOOD_CHEST_BOAT = createChestBoat("rosewood_chest_boat", ModBoatEntity.BoatType.ROSEWOOD, Atmospheric.isInstalled());
	public static RegistryObject<Item> ROSEWOOD_FURNACE_BOAT = createFurnaceBoat("rosewood_furnace_boat", ModBoatEntity.BoatType.ROSEWOOD, Atmospheric.isInstalled());
	public static RegistryObject<Item> LARGE_ROSEWOOD_BOAT = createLargeBoat("large_rosewood_boat", ModBoatEntity.BoatType.ROSEWOOD, Atmospheric.isInstalled());
	
	public static RegistryObject<Item> ASPEN_CHEST_BOAT = createChestBoat("aspen_chest_boat", ModBoatEntity.BoatType.ASPEN, Atmospheric.isInstalled());
	public static RegistryObject<Item> ASPEN_FURNACE_BOAT = createFurnaceBoat("aspen_furnace_boat", ModBoatEntity.BoatType.ASPEN, Atmospheric.isInstalled());
	public static RegistryObject<Item> LARGE_ASPEN_BOAT = createLargeBoat("large_aspen_boat", ModBoatEntity.BoatType.ASPEN, Atmospheric.isInstalled());
	
	public static RegistryObject<Item> KOUSA_CHEST_BOAT = createChestBoat("kousa_chest_boat", ModBoatEntity.BoatType.KOUSA, Atmospheric.isInstalled());
	public static RegistryObject<Item> KOUSA_FURNACE_BOAT = createFurnaceBoat("kousa_furnace_boat", ModBoatEntity.BoatType.KOUSA, Atmospheric.isInstalled());
	public static RegistryObject<Item> LARGE_KOUSA_BOAT = createLargeBoat("large_kousa_boat", ModBoatEntity.BoatType.KOUSA, Atmospheric.isInstalled());
	
	public static RegistryObject<Item> YUCCA_CHEST_BOAT = createChestBoat("yucca_chest_boat", ModBoatEntity.BoatType.YUCCA, Atmospheric.isInstalled());
	public static RegistryObject<Item> YUCCA_FURNACE_BOAT = createFurnaceBoat("yucca_furnace_boat", ModBoatEntity.BoatType.YUCCA, Atmospheric.isInstalled());
	public static RegistryObject<Item> LARGE_YUCCA_BOAT = createLargeBoat("large_yucca_boat", ModBoatEntity.BoatType.YUCCA, Atmospheric.isInstalled());
	
	public static RegistryObject<Item> GRIMWOOD_CHEST_BOAT = createChestBoat("grimwood_chest_boat", ModBoatEntity.BoatType.GRIMWOOD, Atmospheric.isInstalled());
	public static RegistryObject<Item> GRIMWOOD_FURNACE_BOAT = createFurnaceBoat("grimwood_furnace_boat", ModBoatEntity.BoatType.GRIMWOOD, Atmospheric.isInstalled());
	public static RegistryObject<Item> LARGE_GRIMWOOD_BOAT = createLargeBoat("large_grimwood_boat", ModBoatEntity.BoatType.GRIMWOOD, Atmospheric.isInstalled());
	
	// Autumnity
	public static RegistryObject<Item> MAPLE_CHEST_BOAT = createChestBoat("maple_chest_boat", ModBoatEntity.BoatType.MAPLE, Autumnity.isInstalled());
	public static RegistryObject<Item> MAPLE_FURNACE_BOAT = createFurnaceBoat("maple_furnace_boat", ModBoatEntity.BoatType.MAPLE, Autumnity.isInstalled());
	public static RegistryObject<Item> LARGE_MAPLE_BOAT = createLargeBoat("large_maple_boat", ModBoatEntity.BoatType.MAPLE, Autumnity.isInstalled());

	// Enhanced Mushrooms
	public static RegistryObject<Item> RED_MUSHROOM_CHEST_BOAT = createChestBoat("red_mushroom_chest_boat", ModBoatEntity.BoatType.RED_MUSHROOM, EnhancedMushrooms.isInstalled());
	public static RegistryObject<Item> RED_MUSHROOM_FURNACE_BOAT = createFurnaceBoat("red_mushroom_furnace_boat", ModBoatEntity.BoatType.RED_MUSHROOM, EnhancedMushrooms.isInstalled());
	public static RegistryObject<Item> LARGE_RED_MUSHROOM_BOAT = createLargeBoat("large_red_mushroom_boat", ModBoatEntity.BoatType.RED_MUSHROOM, EnhancedMushrooms.isInstalled());

	public static RegistryObject<Item> BROWN_MUSHROOM_CHEST_BOAT = createChestBoat("brown_mushroom_chest_boat", ModBoatEntity.BoatType.BROWN_MUSHROOM, EnhancedMushrooms.isInstalled());
	public static RegistryObject<Item> BROWN_MUSHROOM_FURNACE_BOAT = createFurnaceBoat("brown_mushroom_furnace_boat", ModBoatEntity.BoatType.BROWN_MUSHROOM, EnhancedMushrooms.isInstalled());
	public static RegistryObject<Item> LARGE_BROWN_MUSHROOM_BOAT = createLargeBoat("large_brown_mushroom_boat", ModBoatEntity.BoatType.BROWN_MUSHROOM, EnhancedMushrooms.isInstalled());

	public static RegistryObject<Item> GLOWSHROOM_CHEST_BOAT = createChestBoat("glowshroom_chest_boat", ModBoatEntity.BoatType.GLOWSHROOM, EnhancedMushrooms.isInstalled());
	public static RegistryObject<Item> GLOWSHROOM_FURNACE_BOAT = createFurnaceBoat("glowshroom_furnace_boat", ModBoatEntity.BoatType.GLOWSHROOM, EnhancedMushrooms.isInstalled());
	public static RegistryObject<Item> LARGE_GLOWSHROOM_BOAT = createLargeBoat("large_glowshroom_boat", ModBoatEntity.BoatType.GLOWSHROOM, EnhancedMushrooms.isInstalled());

	// Hanami
	public static RegistryObject<Item> SAKURA_CHEST_BOAT = createChestBoat("hanami_chest_boat", ModBoatEntity.BoatType.SAKURA, Hanami.isInstalled());
	public static RegistryObject<Item> SAKURA_FURNACE_BOAT = createFurnaceBoat("hanami_furnace_boat", ModBoatEntity.BoatType.SAKURA, Hanami.isInstalled());
	public static RegistryObject<Item> LARGE_SAKURA_BOAT = createLargeBoat("large_hanami_boat", ModBoatEntity.BoatType.SAKURA, Hanami.isInstalled());

	private static RegistryObject<Item> createChestBoat(String name, ModBoatEntity.BoatType type, boolean compat)
	{
		return ITEMS.register(name, () -> new ChestBoatItem(type, (new Item.Properties()).maxStackSize(1).group(compat ? ItemGroup.TRANSPORTATION : null)));
	}
	
	private static RegistryObject<Item> createFurnaceBoat(String name, ModBoatEntity.BoatType type, boolean compat)
	{
		return ITEMS.register(name, () -> new FurnaceBoatItem(type, (new Item.Properties()).maxStackSize(1).group(compat ? ItemGroup.TRANSPORTATION : null)));
	}
	
	private static RegistryObject<Item> createLargeBoat(String name, ModBoatEntity.BoatType type, boolean compat)
	{
		return ITEMS.register(name, () -> new LargeBoatItem(type, (new Item.Properties()).maxStackSize(1).group(compat ? ItemGroup.TRANSPORTATION : null)));
	}
}