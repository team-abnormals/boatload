package com.teamabnormals.boatload.core;

import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import com.teamabnormals.boatload.client.gui.screens.inventory.FurnaceBoatScreen;
import com.teamabnormals.boatload.client.model.FurnaceBoatModel;
import com.teamabnormals.boatload.client.model.FurnaceRaftModel;
import com.teamabnormals.boatload.client.model.LargeBoatModel;
import com.teamabnormals.boatload.client.model.WideRaftModel;
import com.teamabnormals.boatload.client.renderer.entity.FurnaceBoatRenderer;
import com.teamabnormals.boatload.client.renderer.entity.LargeBoatRenderer;
import com.teamabnormals.boatload.core.api.BoatloadBoatType;
import com.teamabnormals.boatload.core.data.client.BoatloadItemModelProvider;
import com.teamabnormals.boatload.core.data.client.BoatloadLanguageProvider;
import com.teamabnormals.boatload.core.data.server.BoatloadBlockTagsProvider;
import com.teamabnormals.boatload.core.data.server.BoatloadItemTagsProvider;
import com.teamabnormals.boatload.core.data.server.BoatloadRecipeProvider;
import com.teamabnormals.boatload.core.other.BoatloadModelLayers;
import com.teamabnormals.boatload.core.other.BoatloadTrackedData;
import com.teamabnormals.boatload.core.registry.BoatloadEntityTypes;
import com.teamabnormals.boatload.core.registry.BoatloadItems;
import com.teamabnormals.boatload.core.registry.BoatloadMenuTypes;
import com.teamabnormals.boatload.core.registry.helper.BoatloadItemSubRegistryHelper;
import net.minecraft.client.model.BoatModel;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@Mod(Boatload.MOD_ID)
public class Boatload {
	public static final String MOD_ID = "boatload";
	public static final RegistryHelper REGISTRY_HELPER = RegistryHelper.create(MOD_ID, helper -> helper.putSubHelper(Registries.ITEM, new BoatloadItemSubRegistryHelper(helper)));

	public Boatload(IEventBus bus, ModContainer container) {
		REGISTRY_HELPER.register(bus);
		BoatloadEntityTypes.ENTITY_TYPES.register(bus);
		BoatloadMenuTypes.MENU_TYPES.register(bus);

		bus.addListener(this::commonSetup);
		bus.addListener(this::clientSetup);
		bus.addListener(this::dataSetup);

		if (FMLEnvironment.dist == Dist.CLIENT) {
			bus.addListener(this::registerLayerDefinitions);
			bus.addListener(this::registerRenderers);
			bus.addListener(this::registerScreens);
			BoatloadItems.setupTabEditors();
		}
	}

	private void commonSetup(FMLCommonSetupEvent event) {
		BoatloadTrackedData.registerTrackedData();
	}

	private void clientSetup(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
		});
	}

	private void dataSetup(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput output = generator.getPackOutput();
		CompletableFuture<Provider> provider = event.getLookupProvider();
		ExistingFileHelper helper = event.getExistingFileHelper();

		boolean includeServer = event.includeServer();
		BlockTagsProvider blockTags = new BoatloadBlockTagsProvider(output, provider, helper);
		generator.addProvider(includeServer, blockTags);
		generator.addProvider(includeServer, new BoatloadItemTagsProvider(output, provider, blockTags.contentsGetter(), helper));
		generator.addProvider(includeServer, new BoatloadRecipeProvider(output, provider));

		boolean includeClient = event.includeClient();
		generator.addProvider(includeClient, new BoatloadItemModelProvider(output, helper));
		generator.addProvider(includeClient, new BoatloadLanguageProvider(output));
	}

	@OnlyIn(Dist.CLIENT)
	private void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		for (BoatloadBoatType boatType : BoatloadBoatType.values()) {
			event.registerLayerDefinition(BoatloadModelLayers.createBoatModelName(boatType), BoatModel::createBodyModel);
			event.registerLayerDefinition(BoatloadModelLayers.createFurnaceBoatModelName(boatType), boatType.raft() ? FurnaceRaftModel::createBodyModel : FurnaceBoatModel::createFurnaceBoatBodyModel);
			event.registerLayerDefinition(BoatloadModelLayers.createLargeBoatModelName(boatType), boatType.raft() ? WideRaftModel::createBodyModel : LargeBoatModel::createBodyModel);
		}
	}

	@OnlyIn(Dist.CLIENT)
	private void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(BoatloadEntityTypes.FURNACE_BOAT.get(), FurnaceBoatRenderer::new);
		event.registerEntityRenderer(BoatloadEntityTypes.LARGE_BOAT.get(), LargeBoatRenderer::new);
	}

	private void registerScreens(RegisterMenuScreensEvent event) {
		event.register(BoatloadMenuTypes.FURNACE_BOAT.get(), FurnaceBoatScreen::new);
	}

	public static ResourceLocation location(String path) {
		return ResourceLocation.fromNamespaceAndPath(Boatload.MOD_ID, path);
	}
}