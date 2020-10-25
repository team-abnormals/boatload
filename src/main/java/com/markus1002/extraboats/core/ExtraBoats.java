package com.markus1002.extraboats.core;

import com.markus1002.extraboats.common.dispenser.DispenseChestBoatBehavior;
import com.markus1002.extraboats.common.dispenser.DispenseFurnaceBoatBehavior;
import com.markus1002.extraboats.common.dispenser.DispenseLargeBoatBehavior;
import com.markus1002.extraboats.common.item.ChestBoatItem;
import com.markus1002.extraboats.common.item.EBBoatItem;
import com.markus1002.extraboats.common.item.FurnaceBoatItem;
import com.markus1002.extraboats.common.item.crafting.EBRecipes;
import com.markus1002.extraboats.core.registry.EBEntities;
import com.markus1002.extraboats.core.registry.EBItems;

import net.minecraft.block.DispenserBlock;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
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
        
        EBEntities.ENTITIES.register(modEventBus);
        EBItems.ITEMS.register(modEventBus);
		EBRecipes.RECIPE_SERIALIZERS.register(modEventBus);
        
		MinecraftForge.EVENT_BUS.register(this);
	}

	private void setup(final FMLCommonSetupEvent event)
	{
		for (RegistryObject<Item> item : EBItems.ITEMS.getEntries())
		{
			EBBoatItem boatitem = (EBBoatItem) item.get();
			if (boatitem instanceof ChestBoatItem)
			{
				DispenserBlock.registerDispenseBehavior(boatitem, new DispenseChestBoatBehavior(boatitem.getType()));
			}
			else if (boatitem instanceof FurnaceBoatItem)
			{
				DispenserBlock.registerDispenseBehavior(boatitem, new DispenseFurnaceBoatBehavior(boatitem.getType()));
			}
			else
			{
				DispenserBlock.registerDispenseBehavior(boatitem, new DispenseLargeBoatBehavior(boatitem.getType()));
			}
		}
	}

	private void clientSetup(final FMLClientSetupEvent event)
	{
		EBEntities.setupEntitiesClient();
	}
}