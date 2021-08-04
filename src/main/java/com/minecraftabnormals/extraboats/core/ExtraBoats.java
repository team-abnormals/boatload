package com.minecraftabnormals.extraboats.core;

import com.minecraftabnormals.extraboats.common.dispenser.DispenseChestBoatBehavior;
import com.minecraftabnormals.extraboats.common.dispenser.DispenseFurnaceBoatBehavior;
import com.minecraftabnormals.extraboats.common.dispenser.DispenseLargeBoatBehavior;
import com.minecraftabnormals.extraboats.common.item.ChestBoatItem;
import com.minecraftabnormals.extraboats.common.item.ExtraBoatsBoatItem;
import com.minecraftabnormals.extraboats.common.item.FurnaceBoatItem;
import com.minecraftabnormals.extraboats.core.other.EBDataProcessors;
import com.minecraftabnormals.extraboats.core.other.EBRecipes;
import com.minecraftabnormals.extraboats.core.registry.EBEntities;
import com.minecraftabnormals.extraboats.core.registry.EBItems;
import net.minecraft.block.DispenserBlock;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ExtraBoats.MOD_ID)
public class ExtraBoats {
	public static final String MOD_ID = "extraboats";

	public ExtraBoats() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		MinecraftForge.EVENT_BUS.register(this);

		EBEntities.ENTITIES.register(bus);
		EBItems.ITEMS.register(bus);
		EBRecipes.RECIPE_SERIALIZERS.register(bus);

		bus.addListener(this::commonSetup);
		bus.addListener(this::clientSetup);
	}

	private void commonSetup(final FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			for (RegistryObject<Item> item : EBItems.ITEMS.getEntries()) {
				ExtraBoatsBoatItem boatItem = (ExtraBoatsBoatItem) item.get();
				if (boatItem instanceof ChestBoatItem) {
					DispenserBlock.registerBehavior(boatItem, new DispenseChestBoatBehavior(boatItem.getType()));
				} else if (boatItem instanceof FurnaceBoatItem) {
					DispenserBlock.registerBehavior(boatItem, new DispenseFurnaceBoatBehavior(boatItem.getType()));
				} else {
					DispenserBlock.registerBehavior(boatItem, new DispenseLargeBoatBehavior(boatItem.getType()));
				}
			}
			EBDataProcessors.registerTrackedData();
		});
	}

	private void clientSetup(final FMLClientSetupEvent event) {
		EBEntities.registerRenderers();
	}
}