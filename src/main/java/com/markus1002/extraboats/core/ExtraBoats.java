package com.markus1002.extraboats.core;

import com.markus1002.extraboats.common.dispenser.DispenseChestBoatBehavior;
import com.markus1002.extraboats.common.dispenser.DispenseFurnaceBoatBehavior;
import com.markus1002.extraboats.common.dispenser.DispenseLargeBoatBehavior;
import com.markus1002.extraboats.common.entity.item.boat.ModBoatEntity;
import com.markus1002.extraboats.core.compatibility.*;
import com.markus1002.extraboats.core.registry.ModEntities;
import com.markus1002.extraboats.core.registry.ModItems;

import net.minecraft.block.DispenserBlock;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("extraboats")
public class ExtraBoats
{
	public ExtraBoats()
	{
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::clientSetup);
        
		MinecraftForge.EVENT_BUS.register(this);
	}

	private void setup(final FMLCommonSetupEvent event)
	{
		DispenserBlock.registerDispenseBehavior(ModItems.OAK_CHEST_BOAT, new DispenseChestBoatBehavior(ModBoatEntity.BoatType.OAK));
		DispenserBlock.registerDispenseBehavior(ModItems.OAK_FURNACE_BOAT, new DispenseFurnaceBoatBehavior(ModBoatEntity.BoatType.OAK));
		DispenserBlock.registerDispenseBehavior(ModItems.LARGE_OAK_BOAT, new DispenseLargeBoatBehavior(ModBoatEntity.BoatType.OAK));
		
		DispenserBlock.registerDispenseBehavior(ModItems.SPRUCE_CHEST_BOAT, new DispenseChestBoatBehavior(ModBoatEntity.BoatType.SPRUCE));
		DispenserBlock.registerDispenseBehavior(ModItems.SPRUCE_FURNACE_BOAT, new DispenseFurnaceBoatBehavior(ModBoatEntity.BoatType.SPRUCE));
		DispenserBlock.registerDispenseBehavior(ModItems.LARGE_SPRUCE_BOAT, new DispenseLargeBoatBehavior(ModBoatEntity.BoatType.SPRUCE));
		
		DispenserBlock.registerDispenseBehavior(ModItems.BIRCH_CHEST_BOAT, new DispenseChestBoatBehavior(ModBoatEntity.BoatType.BIRCH));
		DispenserBlock.registerDispenseBehavior(ModItems.BIRCH_FURNACE_BOAT, new DispenseFurnaceBoatBehavior(ModBoatEntity.BoatType.BIRCH));
		DispenserBlock.registerDispenseBehavior(ModItems.LARGE_BIRCH_BOAT, new DispenseLargeBoatBehavior(ModBoatEntity.BoatType.BIRCH));
		
		DispenserBlock.registerDispenseBehavior(ModItems.JUNGLE_CHEST_BOAT, new DispenseChestBoatBehavior(ModBoatEntity.BoatType.JUNGLE));
		DispenserBlock.registerDispenseBehavior(ModItems.JUNGLE_FURNACE_BOAT, new DispenseFurnaceBoatBehavior(ModBoatEntity.BoatType.JUNGLE));
		DispenserBlock.registerDispenseBehavior(ModItems.LARGE_JUNGLE_BOAT, new DispenseLargeBoatBehavior(ModBoatEntity.BoatType.JUNGLE));
		
		DispenserBlock.registerDispenseBehavior(ModItems.DARK_OAK_CHEST_BOAT, new DispenseChestBoatBehavior(ModBoatEntity.BoatType.DARK_OAK));
		DispenserBlock.registerDispenseBehavior(ModItems.DARK_OAK_FURNACE_BOAT, new DispenseFurnaceBoatBehavior(ModBoatEntity.BoatType.DARK_OAK));
		DispenserBlock.registerDispenseBehavior(ModItems.LARGE_DARK_OAK_BOAT, new DispenseLargeBoatBehavior(ModBoatEntity.BoatType.DARK_OAK));
		
		DispenserBlock.registerDispenseBehavior(ModItems.ACACIA_CHEST_BOAT, new DispenseChestBoatBehavior(ModBoatEntity.BoatType.ACACIA));
		DispenserBlock.registerDispenseBehavior(ModItems.ACACIA_FURNACE_BOAT, new DispenseFurnaceBoatBehavior(ModBoatEntity.BoatType.ACACIA));
		DispenserBlock.registerDispenseBehavior(ModItems.LARGE_ACACIA_BOAT, new DispenseLargeBoatBehavior(ModBoatEntity.BoatType.ACACIA));

		if (BiomesOPlenty.isInstalled())
		{
			DispenserBlock.registerDispenseBehavior(ModItems.CHERRY_CHEST_BOAT, new DispenseChestBoatBehavior(ModBoatEntity.BoatType.CHERRY));
			DispenserBlock.registerDispenseBehavior(ModItems.CHERRY_FURNACE_BOAT, new DispenseFurnaceBoatBehavior(ModBoatEntity.BoatType.CHERRY));
			DispenserBlock.registerDispenseBehavior(ModItems.LARGE_CHERRY_BOAT, new DispenseLargeBoatBehavior(ModBoatEntity.BoatType.CHERRY));
			
			DispenserBlock.registerDispenseBehavior(ModItems.DEAD_CHEST_BOAT, new DispenseChestBoatBehavior(ModBoatEntity.BoatType.DEAD));
			DispenserBlock.registerDispenseBehavior(ModItems.DEAD_FURNACE_BOAT, new DispenseFurnaceBoatBehavior(ModBoatEntity.BoatType.DEAD));
			DispenserBlock.registerDispenseBehavior(ModItems.LARGE_DEAD_BOAT, new DispenseLargeBoatBehavior(ModBoatEntity.BoatType.DEAD));
			
			DispenserBlock.registerDispenseBehavior(ModItems.FIR_CHEST_BOAT, new DispenseChestBoatBehavior(ModBoatEntity.BoatType.FIR));
			DispenserBlock.registerDispenseBehavior(ModItems.FIR_FURNACE_BOAT, new DispenseFurnaceBoatBehavior(ModBoatEntity.BoatType.FIR));
			DispenserBlock.registerDispenseBehavior(ModItems.LARGE_FIR_BOAT, new DispenseLargeBoatBehavior(ModBoatEntity.BoatType.FIR));
			
			DispenserBlock.registerDispenseBehavior(ModItems.HELLBARK_CHEST_BOAT, new DispenseChestBoatBehavior(ModBoatEntity.BoatType.HELLBARK));
			DispenserBlock.registerDispenseBehavior(ModItems.HELLBARK_FURNACE_BOAT, new DispenseFurnaceBoatBehavior(ModBoatEntity.BoatType.HELLBARK));
			DispenserBlock.registerDispenseBehavior(ModItems.LARGE_HELLBARK_BOAT, new DispenseLargeBoatBehavior(ModBoatEntity.BoatType.HELLBARK));
			
			DispenserBlock.registerDispenseBehavior(ModItems.JACARANDA_CHEST_BOAT, new DispenseChestBoatBehavior(ModBoatEntity.BoatType.JACARANDA));
			DispenserBlock.registerDispenseBehavior(ModItems.JACARANDA_FURNACE_BOAT, new DispenseFurnaceBoatBehavior(ModBoatEntity.BoatType.JACARANDA));
			DispenserBlock.registerDispenseBehavior(ModItems.LARGE_JACARANDA_BOAT, new DispenseLargeBoatBehavior(ModBoatEntity.BoatType.JACARANDA));
			
			DispenserBlock.registerDispenseBehavior(ModItems.MAGIC_CHEST_BOAT, new DispenseChestBoatBehavior(ModBoatEntity.BoatType.MAGIC));
			DispenserBlock.registerDispenseBehavior(ModItems.MAGIC_FURNACE_BOAT, new DispenseFurnaceBoatBehavior(ModBoatEntity.BoatType.MAGIC));
			DispenserBlock.registerDispenseBehavior(ModItems.LARGE_MAGIC_BOAT, new DispenseLargeBoatBehavior(ModBoatEntity.BoatType.MAGIC));
			
			DispenserBlock.registerDispenseBehavior(ModItems.MAHOGANY_CHEST_BOAT, new DispenseChestBoatBehavior(ModBoatEntity.BoatType.MAHOGANY));
			DispenserBlock.registerDispenseBehavior(ModItems.MAHOGANY_FURNACE_BOAT, new DispenseFurnaceBoatBehavior(ModBoatEntity.BoatType.MAHOGANY));
			DispenserBlock.registerDispenseBehavior(ModItems.LARGE_MAHOGANY_BOAT, new DispenseLargeBoatBehavior(ModBoatEntity.BoatType.MAHOGANY));
			
			DispenserBlock.registerDispenseBehavior(ModItems.PALM_CHEST_BOAT, new DispenseChestBoatBehavior(ModBoatEntity.BoatType.PALM));
			DispenserBlock.registerDispenseBehavior(ModItems.PALM_FURNACE_BOAT, new DispenseFurnaceBoatBehavior(ModBoatEntity.BoatType.PALM));
			DispenserBlock.registerDispenseBehavior(ModItems.LARGE_PALM_BOAT, new DispenseLargeBoatBehavior(ModBoatEntity.BoatType.PALM));
			
			DispenserBlock.registerDispenseBehavior(ModItems.REDWOOD_CHEST_BOAT, new DispenseChestBoatBehavior(ModBoatEntity.BoatType.REDWOOD));
			DispenserBlock.registerDispenseBehavior(ModItems.REDWOOD_FURNACE_BOAT, new DispenseFurnaceBoatBehavior(ModBoatEntity.BoatType.REDWOOD));
			DispenserBlock.registerDispenseBehavior(ModItems.LARGE_REDWOOD_BOAT, new DispenseLargeBoatBehavior(ModBoatEntity.BoatType.REDWOOD));
			
			DispenserBlock.registerDispenseBehavior(ModItems.UMBRAN_CHEST_BOAT, new DispenseChestBoatBehavior(ModBoatEntity.BoatType.UMBRAN));
			DispenserBlock.registerDispenseBehavior(ModItems.UMBRAN_FURNACE_BOAT, new DispenseFurnaceBoatBehavior(ModBoatEntity.BoatType.UMBRAN));
			DispenserBlock.registerDispenseBehavior(ModItems.LARGE_UMBRAN_BOAT, new DispenseLargeBoatBehavior(ModBoatEntity.BoatType.UMBRAN));
			
			DispenserBlock.registerDispenseBehavior(ModItems.WILLOW_CHEST_BOAT, new DispenseChestBoatBehavior(ModBoatEntity.BoatType.WILLOW));
			DispenserBlock.registerDispenseBehavior(ModItems.WILLOW_FURNACE_BOAT, new DispenseFurnaceBoatBehavior(ModBoatEntity.BoatType.WILLOW));
			DispenserBlock.registerDispenseBehavior(ModItems.LARGE_WILLOW_BOAT, new DispenseLargeBoatBehavior(ModBoatEntity.BoatType.WILLOW));
		}
		
		if (UpgradeAquatic.isInstalled())
		{
			DispenserBlock.registerDispenseBehavior(ModItems.DRIFTWOOD_CHEST_BOAT, new DispenseChestBoatBehavior(ModBoatEntity.BoatType.DRIFTWOOD));
			DispenserBlock.registerDispenseBehavior(ModItems.DRIFTWOOD_FURNACE_BOAT, new DispenseFurnaceBoatBehavior(ModBoatEntity.BoatType.DRIFTWOOD));
			DispenserBlock.registerDispenseBehavior(ModItems.LARGE_DRIFTWOOD_BOAT, new DispenseLargeBoatBehavior(ModBoatEntity.BoatType.DRIFTWOOD));
		}
		
		if (BambooBlocks.isInstalled())
		{
			DispenserBlock.registerDispenseBehavior(ModItems.BAMBOO_CHEST_BOAT, new DispenseChestBoatBehavior(ModBoatEntity.BoatType.BAMBOO));
			DispenserBlock.registerDispenseBehavior(ModItems.BAMBOO_FURNACE_BOAT, new DispenseFurnaceBoatBehavior(ModBoatEntity.BoatType.BAMBOO));
			DispenserBlock.registerDispenseBehavior(ModItems.LARGE_BAMBOO_BOAT, new DispenseLargeBoatBehavior(ModBoatEntity.BoatType.BAMBOO));
		}
		
		if (EndergeticExpansion.isInstalled())
		{
			DispenserBlock.registerDispenseBehavior(ModItems.POISE_CHEST_BOAT, new DispenseChestBoatBehavior(ModBoatEntity.BoatType.POISE));
			DispenserBlock.registerDispenseBehavior(ModItems.POISE_FURNACE_BOAT, new DispenseFurnaceBoatBehavior(ModBoatEntity.BoatType.POISE));
			DispenserBlock.registerDispenseBehavior(ModItems.LARGE_POISE_BOAT, new DispenseLargeBoatBehavior(ModBoatEntity.BoatType.POISE));
		}
		
		if (Environmental.isInstalled())
		{
			DispenserBlock.registerDispenseBehavior(ModItems.WISTERIA_CHEST_BOAT, new DispenseChestBoatBehavior(ModBoatEntity.BoatType.WISTERIA));
			DispenserBlock.registerDispenseBehavior(ModItems.WISTERIA_FURNACE_BOAT, new DispenseFurnaceBoatBehavior(ModBoatEntity.BoatType.WISTERIA));
			DispenserBlock.registerDispenseBehavior(ModItems.LARGE_WISTERIA_BOAT, new DispenseLargeBoatBehavior(ModBoatEntity.BoatType.WISTERIA));
			
			DispenserBlock.registerDispenseBehavior(ModItems.ENV_WILLOW_CHEST_BOAT, new DispenseChestBoatBehavior(ModBoatEntity.BoatType.ENV_WILLOW));
			DispenserBlock.registerDispenseBehavior(ModItems.ENV_WILLOW_FURNACE_BOAT, new DispenseFurnaceBoatBehavior(ModBoatEntity.BoatType.ENV_WILLOW));
			DispenserBlock.registerDispenseBehavior(ModItems.LARGE_ENV_WILLOW_BOAT, new DispenseLargeBoatBehavior(ModBoatEntity.BoatType.ENV_WILLOW));
			
			DispenserBlock.registerDispenseBehavior(ModItems.ENV_CHERRY_CHEST_BOAT, new DispenseChestBoatBehavior(ModBoatEntity.BoatType.ENV_CHERRY));
			DispenserBlock.registerDispenseBehavior(ModItems.ENV_CHERRY_FURNACE_BOAT, new DispenseFurnaceBoatBehavior(ModBoatEntity.BoatType.ENV_CHERRY));
			DispenserBlock.registerDispenseBehavior(ModItems.LARGE_ENV_CHERRY_BOAT, new DispenseLargeBoatBehavior(ModBoatEntity.BoatType.ENV_CHERRY));
		}
		
		if (Atmospheric.isInstalled())
		{
			DispenserBlock.registerDispenseBehavior(ModItems.ROSEWOOD_CHEST_BOAT, new DispenseChestBoatBehavior(ModBoatEntity.BoatType.ROSEWOOD));
			DispenserBlock.registerDispenseBehavior(ModItems.ROSEWOOD_FURNACE_BOAT, new DispenseFurnaceBoatBehavior(ModBoatEntity.BoatType.ROSEWOOD));
			DispenserBlock.registerDispenseBehavior(ModItems.LARGE_ROSEWOOD_BOAT, new DispenseLargeBoatBehavior(ModBoatEntity.BoatType.ROSEWOOD));
			
			DispenserBlock.registerDispenseBehavior(ModItems.ASPEN_CHEST_BOAT, new DispenseChestBoatBehavior(ModBoatEntity.BoatType.ASPEN));
			DispenserBlock.registerDispenseBehavior(ModItems.ASPEN_FURNACE_BOAT, new DispenseFurnaceBoatBehavior(ModBoatEntity.BoatType.ASPEN));
			DispenserBlock.registerDispenseBehavior(ModItems.LARGE_ASPEN_BOAT, new DispenseLargeBoatBehavior(ModBoatEntity.BoatType.ASPEN));
			
			DispenserBlock.registerDispenseBehavior(ModItems.KOUSA_CHEST_BOAT, new DispenseChestBoatBehavior(ModBoatEntity.BoatType.KOUSA));
			DispenserBlock.registerDispenseBehavior(ModItems.KOUSA_FURNACE_BOAT, new DispenseFurnaceBoatBehavior(ModBoatEntity.BoatType.KOUSA));
			DispenserBlock.registerDispenseBehavior(ModItems.LARGE_KOUSA_BOAT, new DispenseLargeBoatBehavior(ModBoatEntity.BoatType.KOUSA));
			
			DispenserBlock.registerDispenseBehavior(ModItems.YUCCA_CHEST_BOAT, new DispenseChestBoatBehavior(ModBoatEntity.BoatType.YUCCA));
			DispenserBlock.registerDispenseBehavior(ModItems.YUCCA_FURNACE_BOAT, new DispenseFurnaceBoatBehavior(ModBoatEntity.BoatType.YUCCA));
			DispenserBlock.registerDispenseBehavior(ModItems.LARGE_YUCCA_BOAT, new DispenseLargeBoatBehavior(ModBoatEntity.BoatType.YUCCA));
			
			DispenserBlock.registerDispenseBehavior(ModItems.GRIMWOOD_CHEST_BOAT, new DispenseChestBoatBehavior(ModBoatEntity.BoatType.GRIMWOOD));
			DispenserBlock.registerDispenseBehavior(ModItems.GRIMWOOD_FURNACE_BOAT, new DispenseFurnaceBoatBehavior(ModBoatEntity.BoatType.GRIMWOOD));
			DispenserBlock.registerDispenseBehavior(ModItems.LARGE_GRIMWOOD_BOAT, new DispenseLargeBoatBehavior(ModBoatEntity.BoatType.GRIMWOOD));
		}
		
		if (Autumnity.isInstalled())
		{
			DispenserBlock.registerDispenseBehavior(ModItems.MAPLE_CHEST_BOAT, new DispenseChestBoatBehavior(ModBoatEntity.BoatType.MAPLE));
			DispenserBlock.registerDispenseBehavior(ModItems.MAPLE_FURNACE_BOAT, new DispenseFurnaceBoatBehavior(ModBoatEntity.BoatType.MAPLE));
			DispenserBlock.registerDispenseBehavior(ModItems.LARGE_MAPLE_BOAT, new DispenseLargeBoatBehavior(ModBoatEntity.BoatType.MAPLE));
		}

		if (EnhancedMushrooms.isInstalled())
		{
			DispenserBlock.registerDispenseBehavior(ModItems.RED_MUSHROOM_CHEST_BOAT, new DispenseChestBoatBehavior(ModBoatEntity.BoatType.RED_MUSHROOM));
			DispenserBlock.registerDispenseBehavior(ModItems.RED_MUSHROOM_FURNACE_BOAT, new DispenseFurnaceBoatBehavior(ModBoatEntity.BoatType.RED_MUSHROOM));
			DispenserBlock.registerDispenseBehavior(ModItems.LARGE_RED_MUSHROOM_BOAT, new DispenseLargeBoatBehavior(ModBoatEntity.BoatType.RED_MUSHROOM));

			DispenserBlock.registerDispenseBehavior(ModItems.BROWN_MUSHROOM_CHEST_BOAT, new DispenseChestBoatBehavior(ModBoatEntity.BoatType.BROWN_MUSHROOM));
			DispenserBlock.registerDispenseBehavior(ModItems.BROWN_MUSHROOM_FURNACE_BOAT, new DispenseFurnaceBoatBehavior(ModBoatEntity.BoatType.BROWN_MUSHROOM));
			DispenserBlock.registerDispenseBehavior(ModItems.LARGE_BROWN_MUSHROOM_BOAT, new DispenseLargeBoatBehavior(ModBoatEntity.BoatType.BROWN_MUSHROOM));
		}

		if (EnhancedMushrooms.isInstalledWQuark())
		{
			DispenserBlock.registerDispenseBehavior(ModItems.GLOWSHROOM_CHEST_BOAT, new DispenseChestBoatBehavior(ModBoatEntity.BoatType.GLOWSHROOM));
			DispenserBlock.registerDispenseBehavior(ModItems.GLOWSHROOM_FURNACE_BOAT, new DispenseFurnaceBoatBehavior(ModBoatEntity.BoatType.GLOWSHROOM));
			DispenserBlock.registerDispenseBehavior(ModItems.LARGE_GLOWSHROOM_BOAT, new DispenseLargeBoatBehavior(ModBoatEntity.BoatType.GLOWSHROOM));
		}

		if (Hanami.isInstalled())
		{
			DispenserBlock.registerDispenseBehavior(ModItems.SAKURA_CHEST_BOAT, new DispenseChestBoatBehavior(ModBoatEntity.BoatType.SAKURA));
			DispenserBlock.registerDispenseBehavior(ModItems.SAKURA_FURNACE_BOAT, new DispenseFurnaceBoatBehavior(ModBoatEntity.BoatType.SAKURA));
			DispenserBlock.registerDispenseBehavior(ModItems.LARGE_SAKURA_BOAT, new DispenseLargeBoatBehavior(ModBoatEntity.BoatType.SAKURA));
		}
	}

	private void clientSetup(final FMLClientSetupEvent event)
	{
		ModEntities.setupEntitiesClient();
	}
}