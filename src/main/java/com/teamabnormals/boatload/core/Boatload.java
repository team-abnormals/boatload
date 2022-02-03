package com.teamabnormals.boatload.core;

import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import com.teamabnormals.boatload.client.model.LargeBoatModel;
import com.teamabnormals.boatload.client.renderer.entity.BoatloadBoatRenderer;
import com.teamabnormals.boatload.client.renderer.entity.LargeBoatRenderer;
import com.teamabnormals.boatload.common.dispenser.ChestBoatDispenseItemBehavior;
import com.teamabnormals.boatload.common.dispenser.FurnaceBoatDispenseItemBehavior;
import com.teamabnormals.boatload.common.dispenser.LargeBoatDispenseItemBehavior;
import com.teamabnormals.boatload.common.item.BoatloadBoatItem;
import com.teamabnormals.boatload.common.item.ChestBoatItem;
import com.teamabnormals.boatload.common.item.FurnaceBoatItem;
import com.teamabnormals.boatload.core.api.BoatloadBoatType;
import com.teamabnormals.boatload.core.data.client.BoatloadItemModelProvider;
import com.teamabnormals.boatload.core.data.client.BoatloadLanguageProvider;
import com.teamabnormals.boatload.core.data.server.BoatloadRecipeProvider;
import com.teamabnormals.boatload.core.other.BoatloadDataProcessors;
import com.teamabnormals.boatload.core.other.BoatloadModelLayers;
import com.teamabnormals.boatload.core.other.BoatloadRecipeSerializers;
import com.teamabnormals.boatload.core.registry.BoatloadEntityTypes;
import com.teamabnormals.boatload.core.registry.helper.BoatloadItemSubRegistryHelper;
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

@Mod(Boatload.MOD_ID)
public class Boatload {
	public static final String MOD_ID = "boatload";
	public static final RegistryHelper REGISTRY_HELPER = RegistryHelper.create(MOD_ID, helper -> helper.putSubHelper(ForgeRegistries.ITEMS, new BoatloadItemSubRegistryHelper(helper)));

	public Boatload() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		MinecraftForge.EVENT_BUS.register(this);

		REGISTRY_HELPER.register(bus);
		BoatloadEntityTypes.ENTITIES.register(bus);
		BoatloadRecipeSerializers.RECIPE_SERIALIZERS.register(bus);

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
				BoatloadBoatItem boatItem = (BoatloadBoatItem) item.get();
				if (boatItem instanceof ChestBoatItem) {
					DispenserBlock.registerBehavior(boatItem, new ChestBoatDispenseItemBehavior(boatItem.getType()));
				} else if (boatItem instanceof FurnaceBoatItem) {
					DispenserBlock.registerBehavior(boatItem, new FurnaceBoatDispenseItemBehavior(boatItem.getType()));
				} else {
					DispenserBlock.registerBehavior(boatItem, new LargeBoatDispenseItemBehavior(boatItem.getType()));
				}
			}
			BoatloadDataProcessors.registerTrackedData();
		});
	}

	private void dataSetup(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		ExistingFileHelper fileHelper = event.getExistingFileHelper();

		if (event.includeServer()) {
			//generator.addProvider(new BoatloadRecipeProvider(generator));
		}

		if (event.includeClient()) {
			generator.addProvider(new BoatloadItemModelProvider(generator, fileHelper));
			generator.addProvider(new BoatloadLanguageProvider(generator));
		}
	}

	@OnlyIn(Dist.CLIENT)
	private void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		for (BoatloadBoatType boatType : BoatloadBoatType.values()) {
			event.registerLayerDefinition(BoatloadModelLayers.createBoatModelName(boatType), BoatModel::createBodyModel);
			event.registerLayerDefinition(BoatloadModelLayers.createLargeBoatModelName(boatType), LargeBoatModel::createBodyModel);
		}
	}

	@OnlyIn(Dist.CLIENT)
	private void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(BoatloadEntityTypes.CHEST_BOAT.get(), BoatloadBoatRenderer::new);
		event.registerEntityRenderer(BoatloadEntityTypes.FURNACE_BOAT.get(), BoatloadBoatRenderer::new);
		event.registerEntityRenderer(BoatloadEntityTypes.LARGE_BOAT.get(), LargeBoatRenderer::new);
	}
}