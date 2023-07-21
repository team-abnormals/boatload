package com.teamabnormals.boatload.core;

import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import com.teamabnormals.boatload.client.model.FurnaceBoatModel;
import com.teamabnormals.boatload.client.model.LargeBoatModel;
import com.teamabnormals.boatload.client.renderer.entity.FurnaceBoatRenderer;
import com.teamabnormals.boatload.client.renderer.entity.LargeBoatRenderer;
import com.teamabnormals.boatload.common.dispenser.FurnaceBoatDispenseItemBehavior;
import com.teamabnormals.boatload.common.dispenser.LargeBoatDispenseItemBehavior;
import com.teamabnormals.boatload.core.api.BoatloadBoatType;
import com.teamabnormals.boatload.core.data.client.BoatloadItemModelProvider;
import com.teamabnormals.boatload.core.data.client.BoatloadLanguageProvider;
import com.teamabnormals.boatload.core.data.server.BoatloadItemTagsProvider;
import com.teamabnormals.boatload.core.data.server.BoatloadRecipeProvider;
import com.teamabnormals.boatload.core.other.BoatloadModelLayers;
import com.teamabnormals.boatload.core.other.BoatloadTrackedData;
import com.teamabnormals.boatload.core.other.BoatloadUtil;
import com.teamabnormals.boatload.core.registry.BoatloadEntityTypes;
import com.teamabnormals.boatload.core.registry.helper.BoatloadItemSubRegistryHelper;
import net.minecraft.client.model.BoatModel;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;

@Mod(Boatload.MOD_ID)
public class Boatload {
	public static final String MOD_ID = "boatload";
	public static final RegistryHelper REGISTRY_HELPER = RegistryHelper.create(MOD_ID, helper -> helper.putSubHelper(ForgeRegistries.ITEMS, new BoatloadItemSubRegistryHelper(helper)));

	public Boatload() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		MinecraftForge.EVENT_BUS.register(this);

		REGISTRY_HELPER.register(bus);
		BoatloadEntityTypes.ENTITY_TYPES.register(bus);

		bus.addListener(this::commonSetup);
		bus.addListener(this::dataSetup);

		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
			bus.addListener(this::registerLayerDefinitions);
			bus.addListener(this::registerRenderers);
		});
	}

	private void commonSetup(FMLCommonSetupEvent event) {
		BoatloadTrackedData.registerTrackedData();
		event.enqueueWork(() -> {
			BoatloadUtil.getFurnaceBoats().forEach(item -> DispenserBlock.registerBehavior(item, new FurnaceBoatDispenseItemBehavior(item.getType())));
			BoatloadUtil.getLargeBoats().forEach(item -> DispenserBlock.registerBehavior(item, new LargeBoatDispenseItemBehavior(item.getType())));
		});
	}

	private void dataSetup(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
		ExistingFileHelper helper = event.getExistingFileHelper();

		boolean includeServer = event.includeServer();
		//generator.addProvider(includeServer, new BoatloadItemTagsProvider(output, helper));
		generator.addProvider(includeServer, new BoatloadRecipeProvider(output));

		boolean includeClient = event.includeClient();
		generator.addProvider(includeClient, new BoatloadItemModelProvider(output, helper));
		generator.addProvider(includeClient, new BoatloadLanguageProvider(output));
	}

	@OnlyIn(Dist.CLIENT)
	private void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		for (BoatloadBoatType boatType : BoatloadBoatType.values()) {
			event.registerLayerDefinition(BoatloadModelLayers.createBoatModelName(boatType), () -> BoatModel.createBodyModel());
			event.registerLayerDefinition(BoatloadModelLayers.createFurnaceBoatModelName(boatType), FurnaceBoatModel::createFurnaceBoatBodyModel);
			event.registerLayerDefinition(BoatloadModelLayers.createLargeBoatModelName(boatType), LargeBoatModel::createBodyModel);
		}
	}

	@OnlyIn(Dist.CLIENT)
	private void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(BoatloadEntityTypes.FURNACE_BOAT.get(), FurnaceBoatRenderer::new);
		event.registerEntityRenderer(BoatloadEntityTypes.LARGE_BOAT.get(), LargeBoatRenderer::new);
	}
}