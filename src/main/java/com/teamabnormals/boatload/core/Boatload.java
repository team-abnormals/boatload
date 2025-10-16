package com.teamabnormals.boatload.core;

import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import com.teamabnormals.boatload.core.data.client.BoatloadItemModelProvider;
import com.teamabnormals.boatload.core.data.client.BoatloadLanguageProvider;
import com.teamabnormals.boatload.core.data.server.BoatloadBlockTagsProvider;
import com.teamabnormals.boatload.core.data.server.BoatloadItemTagsProvider;
import com.teamabnormals.boatload.core.data.server.BoatloadRecipeProvider;
import com.teamabnormals.boatload.core.other.BoatloadTrackedData;
import com.teamabnormals.boatload.core.registry.BoatloadEntityTypes;
import com.teamabnormals.boatload.core.registry.BoatloadItems;
import com.teamabnormals.boatload.core.registry.BoatloadMenuTypes;
import com.teamabnormals.boatload.core.registry.helper.BoatloadItemSubRegistryHelper;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@Mod(Boatload.MOD_ID)
public class Boatload {
	public static final String MOD_ID = "boatload";
	public static final RegistryHelper REGISTRY_HELPER = RegistryHelper.create(MOD_ID, helper -> helper.putSubHelper(Registries.ITEM, new BoatloadItemSubRegistryHelper(helper)));

	public Boatload(IEventBus bus) {
		BoatloadTrackedData.registerTrackedData();

		BoatloadItems.ITEMS.register(bus);
		BoatloadEntityTypes.ENTITY_TYPES.register(bus);
		BoatloadMenuTypes.MENU_TYPES.register(bus);

		bus.addListener(this::commonSetup);
		bus.addListener(this::clientSetup);
		bus.addListener(this::dataSetup);
	}

	private void commonSetup(FMLCommonSetupEvent event) {
	}

	private void clientSetup(FMLClientSetupEvent event) {
		BoatloadItems.setupTabEditors();
	}

	private void dataSetup(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput output = generator.getPackOutput();
		CompletableFuture<Provider> provider = event.getLookupProvider();
		ExistingFileHelper helper = event.getExistingFileHelper();

		boolean server = event.includeServer();
		BlockTagsProvider blockTags = new BoatloadBlockTagsProvider(output, provider, helper);
		generator.addProvider(server, blockTags);
		generator.addProvider(server, new BoatloadItemTagsProvider(output, provider, blockTags.contentsGetter(), helper));
		generator.addProvider(server, new BoatloadRecipeProvider(output, provider));

		boolean client = event.includeClient();
		generator.addProvider(client, new BoatloadItemModelProvider(output, helper));
		generator.addProvider(client, new BoatloadLanguageProvider(output));
	}

	public static ResourceLocation location(String path) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
	}
}