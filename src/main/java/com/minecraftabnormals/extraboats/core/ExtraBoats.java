package com.minecraftabnormals.extraboats.core;

import com.minecraftabnormals.extraboats.common.dispenser.DispenseChestBoatBehavior;
import com.minecraftabnormals.extraboats.common.dispenser.DispenseFurnaceBoatBehavior;
import com.minecraftabnormals.extraboats.common.dispenser.DispenseLargeBoatBehavior;
import com.minecraftabnormals.extraboats.common.item.ChestBoatItem;
import com.minecraftabnormals.extraboats.common.item.ExtraBoatsBoatItem;
import com.minecraftabnormals.extraboats.common.item.FurnaceBoatItem;
import com.minecraftabnormals.extraboats.core.other.ExtraBoatsDataProcessors;
import com.minecraftabnormals.extraboats.core.other.ExtraBoatsRecipes;
import com.minecraftabnormals.extraboats.core.registry.ExtraBoatsEntities;
import com.minecraftabnormals.extraboats.core.registry.ExtraBoatsItems;

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
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

		modEventBus.addListener(this::setup);
		modEventBus.addListener(this::clientSetup);

		ExtraBoatsEntities.ENTITIES.register(modEventBus);
		ExtraBoatsItems.ITEMS.register(modEventBus);
		ExtraBoatsRecipes.RECIPE_SERIALIZERS.register(modEventBus);

		MinecraftForge.EVENT_BUS.register(this);
	}

	private void setup(final FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			for (RegistryObject<Item> item : ExtraBoatsItems.ITEMS.getEntries()) {
				ExtraBoatsBoatItem boatitem = (ExtraBoatsBoatItem) item.get();
				if (boatitem instanceof ChestBoatItem) {
					DispenserBlock.registerDispenseBehavior(boatitem, new DispenseChestBoatBehavior(boatitem.getType()));
				} else if (boatitem instanceof FurnaceBoatItem) {
					DispenserBlock.registerDispenseBehavior(boatitem, new DispenseFurnaceBoatBehavior(boatitem.getType()));
				} else {
					DispenserBlock.registerDispenseBehavior(boatitem, new DispenseLargeBoatBehavior(boatitem.getType()));
				}
			}
			ExtraBoatsDataProcessors.registerTrackedData();
		});
	}

	private void clientSetup(final FMLClientSetupEvent event) {
		ExtraBoatsEntities.setupEntitiesClient();
	}
}