package com.markus1002.extraboats.item;

import com.markus1002.extraboats.Reference;
import com.markus1002.extraboats.compatibility.Atmospheric;
import com.markus1002.extraboats.compatibility.Autumnity;
import com.markus1002.extraboats.compatibility.BambooBlocks;
import com.markus1002.extraboats.compatibility.BiomesOPlenty;
import com.markus1002.extraboats.compatibility.Bloomful;
import com.markus1002.extraboats.compatibility.EndergeticExpansion;
import com.markus1002.extraboats.compatibility.SwampExpansion;
import com.markus1002.extraboats.compatibility.UpgradeAquatic;
import com.markus1002.extraboats.entity.item.boat.ModBoatEntity;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItems
{
	public static final ItemGroupExtraBoats EXTRA_BOATS = new ItemGroupExtraBoats(ItemGroup.GROUPS.length, Reference.MOD_ID);

	public static final Item OAK_CHEST_BOAT = new ChestBoatItem(ModBoatEntity.BoatType.OAK, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	public static final Item OAK_FURNACE_BOAT = new FurnaceBoatItem(ModBoatEntity.BoatType.OAK, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	public static final Item LARGE_OAK_BOAT = new LargeBoatItem(ModBoatEntity.BoatType.OAK, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	
	public static final Item SPRUCE_CHEST_BOAT = new ChestBoatItem(ModBoatEntity.BoatType.SPRUCE, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	public static final Item SPRUCE_FURNACE_BOAT = new FurnaceBoatItem(ModBoatEntity.BoatType.SPRUCE, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	public static final Item LARGE_SPRUCE_BOAT = new LargeBoatItem(ModBoatEntity.BoatType.SPRUCE, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	
	public static final Item BIRCH_CHEST_BOAT = new ChestBoatItem(ModBoatEntity.BoatType.BIRCH, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	public static final Item BIRCH_FURNACE_BOAT = new FurnaceBoatItem(ModBoatEntity.BoatType.BIRCH, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	public static final Item LARGE_BIRCH_BOAT = new LargeBoatItem(ModBoatEntity.BoatType.BIRCH, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	
	public static final Item JUNGLE_CHEST_BOAT = new ChestBoatItem(ModBoatEntity.BoatType.JUNGLE, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	public static final Item JUNGLE_FURNACE_BOAT = new FurnaceBoatItem(ModBoatEntity.BoatType.JUNGLE, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	public static final Item LARGE_JUNGLE_BOAT = new LargeBoatItem(ModBoatEntity.BoatType.JUNGLE, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	
	public static final Item ACACIA_CHEST_BOAT = new ChestBoatItem(ModBoatEntity.BoatType.ACACIA, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	public static final Item ACACIA_FURNACE_BOAT = new FurnaceBoatItem(ModBoatEntity.BoatType.ACACIA, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	public static final Item LARGE_ACACIA_BOAT = new LargeBoatItem(ModBoatEntity.BoatType.ACACIA, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	
	public static final Item DARK_OAK_CHEST_BOAT = new ChestBoatItem(ModBoatEntity.BoatType.DARK_OAK, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	public static final Item DARK_OAK_FURNACE_BOAT = new FurnaceBoatItem(ModBoatEntity.BoatType.DARK_OAK, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	public static final Item LARGE_DARK_OAK_BOAT = new LargeBoatItem(ModBoatEntity.BoatType.DARK_OAK, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));

	// Biomes O' Plenty
	public static final Item CHERRY_CHEST_BOAT = new ChestBoatItem(ModBoatEntity.BoatType.CHERRY, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	public static final Item CHERRY_FURNACE_BOAT = new FurnaceBoatItem(ModBoatEntity.BoatType.CHERRY, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	public static final Item LARGE_CHERRY_BOAT = new LargeBoatItem(ModBoatEntity.BoatType.CHERRY, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	
	public static final Item DEAD_CHEST_BOAT = new ChestBoatItem(ModBoatEntity.BoatType.DEAD, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	public static final Item DEAD_FURNACE_BOAT = new FurnaceBoatItem(ModBoatEntity.BoatType.DEAD, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	public static final Item LARGE_DEAD_BOAT = new LargeBoatItem(ModBoatEntity.BoatType.DEAD, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	
	public static final Item ETHEREAL_CHEST_BOAT = new ChestBoatItem(ModBoatEntity.BoatType.ETHEREAL, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	public static final Item ETHEREAL_FURNACE_BOAT = new FurnaceBoatItem(ModBoatEntity.BoatType.ETHEREAL, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	public static final Item LARGE_ETHEREAL_BOAT = new LargeBoatItem(ModBoatEntity.BoatType.ETHEREAL, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	
	public static final Item FIR_CHEST_BOAT = new ChestBoatItem(ModBoatEntity.BoatType.FIR, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	public static final Item FIR_FURNACE_BOAT = new FurnaceBoatItem(ModBoatEntity.BoatType.FIR, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	public static final Item LARGE_FIR_BOAT = new LargeBoatItem(ModBoatEntity.BoatType.FIR, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	
	public static final Item HELLBARK_CHEST_BOAT = new ChestBoatItem(ModBoatEntity.BoatType.HELLBARK, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	public static final Item HELLBARK_FURNACE_BOAT = new FurnaceBoatItem(ModBoatEntity.BoatType.HELLBARK, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	public static final Item LARGE_HELLBARK_BOAT = new LargeBoatItem(ModBoatEntity.BoatType.HELLBARK, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	
	public static final Item JACARANDA_CHEST_BOAT = new ChestBoatItem(ModBoatEntity.BoatType.JACARANDA, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	public static final Item JACARANDA_FURNACE_BOAT = new FurnaceBoatItem(ModBoatEntity.BoatType.JACARANDA, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	public static final Item LARGE_JACARANDA_BOAT = new LargeBoatItem(ModBoatEntity.BoatType.JACARANDA, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	
	public static final Item MAGIC_CHEST_BOAT = new ChestBoatItem(ModBoatEntity.BoatType.MAGIC, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	public static final Item MAGIC_FURNACE_BOAT = new FurnaceBoatItem(ModBoatEntity.BoatType.MAGIC, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	public static final Item LARGE_MAGIC_BOAT = new LargeBoatItem(ModBoatEntity.BoatType.MAGIC, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	
	public static final Item MAHOGANY_CHEST_BOAT = new ChestBoatItem(ModBoatEntity.BoatType.MAHOGANY, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	public static final Item MAHOGANY_FURNACE_BOAT = new FurnaceBoatItem(ModBoatEntity.BoatType.MAHOGANY, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	public static final Item LARGE_MAHOGANY_BOAT = new LargeBoatItem(ModBoatEntity.BoatType.MAHOGANY, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	
	public static final Item PALM_CHEST_BOAT = new ChestBoatItem(ModBoatEntity.BoatType.PALM, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	public static final Item PALM_FURNACE_BOAT = new FurnaceBoatItem(ModBoatEntity.BoatType.PALM, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	public static final Item LARGE_PALM_BOAT = new LargeBoatItem(ModBoatEntity.BoatType.PALM, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	
	public static final Item REDWOOD_CHEST_BOAT = new ChestBoatItem(ModBoatEntity.BoatType.REDWOOD, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	public static final Item REDWOOD_FURNACE_BOAT = new FurnaceBoatItem(ModBoatEntity.BoatType.REDWOOD, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	public static final Item LARGE_REDWOOD_BOAT = new LargeBoatItem(ModBoatEntity.BoatType.REDWOOD, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	
	public static final Item UMBRAN_CHEST_BOAT = new ChestBoatItem(ModBoatEntity.BoatType.UMBRAN, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	public static final Item UMBRAN_FURNACE_BOAT = new FurnaceBoatItem(ModBoatEntity.BoatType.UMBRAN, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	public static final Item LARGE_UMBRAN_BOAT = new LargeBoatItem(ModBoatEntity.BoatType.UMBRAN, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	
	public static final Item WILLOW_CHEST_BOAT = new ChestBoatItem(ModBoatEntity.BoatType.WILLOW, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	public static final Item WILLOW_FURNACE_BOAT = new FurnaceBoatItem(ModBoatEntity.BoatType.WILLOW, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	public static final Item LARGE_WILLOW_BOAT = new LargeBoatItem(ModBoatEntity.BoatType.WILLOW, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));

	// Upgrade Aquatic
	public static final Item DRIFTWOOD_CHEST_BOAT = new ChestBoatItem(ModBoatEntity.BoatType.DRIFTWOOD, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	public static final Item DRIFTWOOD_FURNACE_BOAT = new FurnaceBoatItem(ModBoatEntity.BoatType.DRIFTWOOD, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	public static final Item LARGE_DRIFTWOOD_BOAT = new LargeBoatItem(ModBoatEntity.BoatType.DRIFTWOOD, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));

	// Bamboo Blocks
	public static final Item BAMBOO_CHEST_BOAT = new ChestBoatItem(ModBoatEntity.BoatType.BAMBOO, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	public static final Item BAMBOO_FURNACE_BOAT = new FurnaceBoatItem(ModBoatEntity.BoatType.BAMBOO, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	public static final Item LARGE_BAMBOO_BOAT = new LargeBoatItem(ModBoatEntity.BoatType.BAMBOO, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));

	// Endergetic Expansion
	public static final Item POISE_CHEST_BOAT = new ChestBoatItem(ModBoatEntity.BoatType.POISE, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	public static final Item POISE_FURNACE_BOAT = new FurnaceBoatItem(ModBoatEntity.BoatType.POISE, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	public static final Item LARGE_POISE_BOAT = new LargeBoatItem(ModBoatEntity.BoatType.POISE, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));

	// Bloomful
	public static final Item WISTERIA_CHEST_BOAT = new ChestBoatItem(ModBoatEntity.BoatType.WISTERIA, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	public static final Item WISTERIA_FURNACE_BOAT = new FurnaceBoatItem(ModBoatEntity.BoatType.WISTERIA, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	public static final Item LARGE_WISTERIA_BOAT = new LargeBoatItem(ModBoatEntity.BoatType.WISTERIA, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));

	// Swamp Expansion
	public static final Item SE_WILLOW_CHEST_BOAT = new ChestBoatItem(ModBoatEntity.BoatType.SE_WILLOW, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	public static final Item SE_WILLOW_FURNACE_BOAT = new FurnaceBoatItem(ModBoatEntity.BoatType.SE_WILLOW, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	public static final Item LARGE_SE_WILLOW_BOAT = new LargeBoatItem(ModBoatEntity.BoatType.SE_WILLOW, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));

	// Atmospheric
	public static final Item ROSEWOOD_CHEST_BOAT = new ChestBoatItem(ModBoatEntity.BoatType.ROSEWOOD, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	public static final Item ROSEWOOD_FURNACE_BOAT = new FurnaceBoatItem(ModBoatEntity.BoatType.ROSEWOOD, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	public static final Item LARGE_ROSEWOOD_BOAT = new LargeBoatItem(ModBoatEntity.BoatType.ROSEWOOD, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	
	public static final Item ASPEN_CHEST_BOAT = new ChestBoatItem(ModBoatEntity.BoatType.ASPEN, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	public static final Item ASPEN_FURNACE_BOAT = new FurnaceBoatItem(ModBoatEntity.BoatType.ASPEN, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	public static final Item LARGE_ASPEN_BOAT = new LargeBoatItem(ModBoatEntity.BoatType.ASPEN, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	
	public static final Item KOUSA_CHEST_BOAT = new ChestBoatItem(ModBoatEntity.BoatType.KOUSA, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	public static final Item KOUSA_FURNACE_BOAT = new FurnaceBoatItem(ModBoatEntity.BoatType.KOUSA, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	public static final Item LARGE_KOUSA_BOAT = new LargeBoatItem(ModBoatEntity.BoatType.KOUSA, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	
	public static final Item YUCCA_CHEST_BOAT = new ChestBoatItem(ModBoatEntity.BoatType.YUCCA, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	public static final Item YUCCA_FURNACE_BOAT = new FurnaceBoatItem(ModBoatEntity.BoatType.YUCCA, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	public static final Item LARGE_YUCCA_BOAT = new LargeBoatItem(ModBoatEntity.BoatType.YUCCA, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	
	// Autumnity
	public static final Item MAPLE_CHEST_BOAT = new ChestBoatItem(ModBoatEntity.BoatType.MAPLE, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	public static final Item MAPLE_FURNACE_BOAT = new FurnaceBoatItem(ModBoatEntity.BoatType.MAPLE, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));
	public static final Item LARGE_MAPLE_BOAT = new LargeBoatItem(ModBoatEntity.BoatType.MAPLE, (new Item.Properties()).maxStackSize(1).group(EXTRA_BOATS));

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event)
	{
		registerItem(OAK_CHEST_BOAT, "oak_chest_boat");
		registerItem(OAK_FURNACE_BOAT, "oak_furnace_boat");
		registerItem(LARGE_OAK_BOAT, "large_oak_boat");
		
		registerItem(SPRUCE_CHEST_BOAT, "spruce_chest_boat");
		registerItem(SPRUCE_FURNACE_BOAT, "spruce_furnace_boat");
		registerItem(LARGE_SPRUCE_BOAT, "large_spruce_boat");
		
		registerItem(BIRCH_CHEST_BOAT, "birch_chest_boat");
		registerItem(BIRCH_FURNACE_BOAT, "birch_furnace_boat");
		registerItem(LARGE_BIRCH_BOAT, "large_birch_boat");
		
		registerItem(JUNGLE_CHEST_BOAT, "jungle_chest_boat");
		registerItem(JUNGLE_FURNACE_BOAT, "jungle_furnace_boat");
		registerItem(LARGE_JUNGLE_BOAT, "large_jungle_boat");
		
		registerItem(ACACIA_CHEST_BOAT, "acacia_chest_boat");
		registerItem(ACACIA_FURNACE_BOAT, "acacia_furnace_boat");
		registerItem(LARGE_ACACIA_BOAT, "large_acacia_boat");
		
		registerItem(DARK_OAK_CHEST_BOAT, "dark_oak_chest_boat");
		registerItem(DARK_OAK_FURNACE_BOAT, "dark_oak_furnace_boat");
		registerItem(LARGE_DARK_OAK_BOAT, "large_dark_oak_boat");

		if (BiomesOPlenty.isInstalled())
		{
			registerItem(CHERRY_CHEST_BOAT, "cherry_chest_boat");
			registerItem(CHERRY_FURNACE_BOAT, "cherry_furnace_boat");
			registerItem(LARGE_CHERRY_BOAT, "large_cherry_boat");
			
			registerItem(DEAD_CHEST_BOAT, "dead_chest_boat");
			registerItem(DEAD_FURNACE_BOAT, "dead_furnace_boat");
			registerItem(LARGE_DEAD_BOAT, "large_dead_boat");
			
			registerItem(ETHEREAL_CHEST_BOAT, "ethereal_chest_boat");
			registerItem(ETHEREAL_FURNACE_BOAT, "ethereal_furnace_boat");
			registerItem(LARGE_ETHEREAL_BOAT, "large_ethereal_boat");
			
			registerItem(FIR_CHEST_BOAT, "fir_chest_boat");
			registerItem(FIR_FURNACE_BOAT, "fir_furnace_boat");
			registerItem(LARGE_FIR_BOAT, "large_fir_boat");
			
			registerItem(HELLBARK_CHEST_BOAT, "hellbark_chest_boat");
			registerItem(HELLBARK_FURNACE_BOAT, "hellbark_furnace_boat");
			registerItem(LARGE_HELLBARK_BOAT, "large_hellbark_boat");
			
			registerItem(JACARANDA_CHEST_BOAT, "jacaranda_chest_boat");
			registerItem(JACARANDA_FURNACE_BOAT, "jacaranda_furnace_boat");
			registerItem(LARGE_JACARANDA_BOAT, "large_jacaranda_boat");
			
			registerItem(MAGIC_CHEST_BOAT, "magic_chest_boat");
			registerItem(MAGIC_FURNACE_BOAT, "magic_furnace_boat");
			registerItem(LARGE_MAGIC_BOAT, "large_magic_boat");
			
			registerItem(MAHOGANY_CHEST_BOAT, "mahogany_chest_boat");
			registerItem(MAHOGANY_FURNACE_BOAT, "mahogany_furnace_boat");
			registerItem(LARGE_MAHOGANY_BOAT, "large_mahogany_boat");
			
			registerItem(PALM_CHEST_BOAT, "palm_chest_boat");
			registerItem(PALM_FURNACE_BOAT, "palm_furnace_boat");
			registerItem(LARGE_PALM_BOAT, "large_palm_boat");
			
			registerItem(REDWOOD_CHEST_BOAT, "redwood_chest_boat");
			registerItem(REDWOOD_FURNACE_BOAT, "redwood_furnace_boat");
			registerItem(LARGE_REDWOOD_BOAT, "large_redwood_boat");
			
			registerItem(UMBRAN_CHEST_BOAT, "umbran_chest_boat");
			registerItem(UMBRAN_FURNACE_BOAT, "umbran_furnace_boat");
			registerItem(LARGE_UMBRAN_BOAT, "large_umbran_boat");
			
			registerItem(WILLOW_CHEST_BOAT, "willow_chest_boat");
			registerItem(WILLOW_FURNACE_BOAT, "willow_furnace_boat");
			registerItem(LARGE_WILLOW_BOAT, "large_willow_boat");
		}

		if (UpgradeAquatic.isInstalled())
		{
			registerItem(DRIFTWOOD_CHEST_BOAT, "driftwood_chest_boat");
			registerItem(DRIFTWOOD_FURNACE_BOAT, "driftwood_furnace_boat");
			registerItem(LARGE_DRIFTWOOD_BOAT, "large_driftwood_boat");
		}

		if (BambooBlocks.isInstalled())
		{
			registerItem(BAMBOO_CHEST_BOAT, "bamboo_chest_boat");
			registerItem(BAMBOO_FURNACE_BOAT, "bamboo_furnace_boat");
			registerItem(LARGE_BAMBOO_BOAT, "large_bamboo_boat");
		}

		if (EndergeticExpansion.isInstalled())
		{
			registerItem(POISE_CHEST_BOAT, "poise_chest_boat");
			registerItem(POISE_FURNACE_BOAT, "poise_furnace_boat");
			registerItem(LARGE_POISE_BOAT, "large_poise_boat");
		}

		if (Bloomful.isInstalled())
		{
			registerItem(WISTERIA_CHEST_BOAT, "wisteria_chest_boat");
			registerItem(WISTERIA_FURNACE_BOAT, "wisteria_furnace_boat");
			registerItem(LARGE_WISTERIA_BOAT, "large_wisteria_boat");
		}

		if (SwampExpansion.isInstalled())
		{
			registerItem(SE_WILLOW_CHEST_BOAT, "se_willow_chest_boat");
			registerItem(SE_WILLOW_FURNACE_BOAT, "se_willow_furnace_boat");
			registerItem(LARGE_SE_WILLOW_BOAT, "large_se_willow_boat");
		}

		if (Atmospheric.isInstalled())
		{
			registerItem(ROSEWOOD_CHEST_BOAT, "rosewood_chest_boat");
			registerItem(ROSEWOOD_FURNACE_BOAT, "rosewood_furnace_boat");
			registerItem(LARGE_ROSEWOOD_BOAT, "large_rosewood_boat");
			
			registerItem(ASPEN_CHEST_BOAT, "aspen_chest_boat");
			registerItem(ASPEN_FURNACE_BOAT, "aspen_furnace_boat");
			registerItem(LARGE_ASPEN_BOAT, "large_aspen_boat");
			
			registerItem(KOUSA_CHEST_BOAT, "kousa_chest_boat");
			registerItem(KOUSA_FURNACE_BOAT, "kousa_furnace_boat");
			registerItem(LARGE_KOUSA_BOAT, "large_kousa_boat");
			
			registerItem(YUCCA_CHEST_BOAT, "yucca_chest_boat");
			registerItem(YUCCA_FURNACE_BOAT, "yucca_furnace_boat");
			registerItem(LARGE_YUCCA_BOAT, "large_yucca_boat");
		}
		
		if (Autumnity.isInstalled())
		{
			registerItem(MAPLE_CHEST_BOAT, "maple_chest_boat");
			registerItem(MAPLE_FURNACE_BOAT, "maple_furnace_boat");
			registerItem(LARGE_MAPLE_BOAT, "large_maple_boat");
		}
	}

	private static void registerItem(Item item, String name)
	{
		item.setRegistryName(Reference.location(name));
		ForgeRegistries.ITEMS.register(item);
	}
}