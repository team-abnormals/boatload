package com.teamabnormals.boatload.core;

import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import com.teamabnormals.boatload.client.model.LargeBoatModel;
import com.teamabnormals.boatload.client.renderer.entity.BLBoatRenderer;
import com.teamabnormals.boatload.client.renderer.entity.LargeBoatRenderer;
import com.teamabnormals.boatload.common.dispenser.DispenseChestBoatBehavior;
import com.teamabnormals.boatload.common.dispenser.DispenseFurnaceBoatBehavior;
import com.teamabnormals.boatload.common.dispenser.DispenseLargeBoatBehavior;
import com.teamabnormals.boatload.common.item.BLBoatItem;
import com.teamabnormals.boatload.common.item.ChestBoatItem;
import com.teamabnormals.boatload.common.item.FurnaceBoatItem;
import com.teamabnormals.boatload.core.api.ExtraBoatType;
import com.teamabnormals.boatload.core.data.client.BLItemModelProvider;
import com.teamabnormals.boatload.core.data.client.BLLanguageProvider;
import com.teamabnormals.boatload.core.other.BLDataProcessors;
import com.teamabnormals.boatload.core.other.BLModelLayers;
import com.teamabnormals.boatload.core.other.BLRecipes;
import com.teamabnormals.boatload.core.registry.BLEntityTypes;
import com.teamabnormals.boatload.core.registry.helper.BLItemSubRegistryHelper;
import net.minecraft.client.model.BoatModel;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.stream.Collectors;

@Mod(Boatload.MOD_ID)
public class Boatload {
	public static final String MOD_ID = "boatload";
	public static final RegistryHelper REGISTRY_HELPER = RegistryHelper.create(MOD_ID, helper -> helper.putSubHelper(ForgeRegistries.ITEMS, new BLItemSubRegistryHelper(helper)));

	public Boatload() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		MinecraftForge.EVENT_BUS.register(this);

		REGISTRY_HELPER.register(bus);
		BLEntityTypes.ENTITIES.register(bus);
		BLRecipes.RECIPE_SERIALIZERS.register(bus);

		bus.addListener(this::commonSetup);
		bus.addListener(this::dataSetup);

		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
			bus.addListener(this::registerLayerDefinitions);
			bus.addListener(this::registerRenderers);
		});
	}

	private void commonSetup(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			for (RegistryObject<Item> item : REGISTRY_HELPER.getItemSubHelper().getDeferredRegister().getEntries()) {
				BLBoatItem boatItem = (BLBoatItem) item.get();
				if (boatItem instanceof ChestBoatItem) {
					DispenserBlock.registerBehavior(boatItem, new DispenseChestBoatBehavior(boatItem.getType()));
				} else if (boatItem instanceof FurnaceBoatItem) {
					DispenserBlock.registerBehavior(boatItem, new DispenseFurnaceBoatBehavior(boatItem.getType()));
				} else {
					DispenserBlock.registerBehavior(boatItem, new DispenseLargeBoatBehavior(boatItem.getType()));
				}
			}
			BLDataProcessors.registerTrackedData();
		});
	}

	private void dataSetup(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		ExistingFileHelper fileHelper = event.getExistingFileHelper();

		if (event.includeServer()) {
			//generator.addProvider(new EBRecipeProvider(generator));
		}

		if (event.includeClient()) {
			generator.addProvider(new BLItemModelProvider(generator, fileHelper));
			generator.addProvider(new BLLanguageProvider(generator));
		}
	}

	@OnlyIn(Dist.CLIENT)
	private void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		for (ExtraBoatType boatType : ExtraBoatType.values().collect(Collectors.toList())) {
			event.registerLayerDefinition(BLModelLayers.createBoatModelName(boatType), BoatModel::createBodyModel);
			event.registerLayerDefinition(BLModelLayers.createLargeBoatModelName(boatType), LargeBoatModel::createBodyModel);
		}
	}

	@OnlyIn(Dist.CLIENT)
	private void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(BLEntityTypes.CHEST_BOAT.get(), BLBoatRenderer::new);
		event.registerEntityRenderer(BLEntityTypes.FURNACE_BOAT.get(), BLBoatRenderer::new);
		event.registerEntityRenderer(BLEntityTypes.LARGE_BOAT.get(), LargeBoatRenderer::new);
	}
}