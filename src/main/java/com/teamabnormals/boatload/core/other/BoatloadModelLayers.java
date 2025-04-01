package com.teamabnormals.boatload.core.other;

import com.teamabnormals.boatload.client.model.FurnaceBoatModel;
import com.teamabnormals.boatload.client.model.FurnaceRaftModel;
import com.teamabnormals.boatload.client.model.LargeBoatModel;
import com.teamabnormals.boatload.client.model.WideRaftModel;
import com.teamabnormals.boatload.client.renderer.entity.FurnaceBoatRenderer;
import com.teamabnormals.boatload.client.renderer.entity.LargeBoatRenderer;
import com.teamabnormals.boatload.core.Boatload;
import com.teamabnormals.boatload.core.api.BoatloadBoatType;
import com.teamabnormals.boatload.core.registry.BoatloadEntityTypes;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent.RegisterLayerDefinitions;
import net.neoforged.neoforge.client.event.EntityRenderersEvent.RegisterRenderers;

@EventBusSubscriber(modid = Boatload.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class BoatloadModelLayers {

	public static ModelLayerLocation createBoatModelName(BoatloadBoatType boatType) {
		return register("boat/" + boatType.registryName().getPath(), "main");
	}

	public static ModelLayerLocation createFurnaceBoatModelName(BoatloadBoatType boatType) {
		return register("furnace_boat/" + boatType.registryName().getPath(), "main");
	}

	public static ModelLayerLocation createLargeBoatModelName(BoatloadBoatType boatType) {
		return register("boat/" + boatType.registryName().getPath() + "_large", "main");
	}

	public static ModelLayerLocation register(String name, String layer) {
		return new ModelLayerLocation(Boatload.location(name), layer);
	}

	@SubscribeEvent
	public static void registerLayerDefinitions(RegisterLayerDefinitions event) {
		for (BoatloadBoatType boatType : BoatloadBoatType.values()) {
			event.registerLayerDefinition(createBoatModelName(boatType), BoatModel::createBodyModel);
			event.registerLayerDefinition(createFurnaceBoatModelName(boatType), boatType.raft() ? FurnaceRaftModel::createBodyModel : FurnaceBoatModel::createFurnaceBoatBodyModel);
			event.registerLayerDefinition(createLargeBoatModelName(boatType), boatType.raft() ? WideRaftModel::createBodyModel : LargeBoatModel::createBodyModel);
		}
	}

	@SubscribeEvent
	public static void registerRenderers(RegisterRenderers event) {
		event.registerEntityRenderer(BoatloadEntityTypes.FURNACE_BOAT.get(), FurnaceBoatRenderer::new);
		event.registerEntityRenderer(BoatloadEntityTypes.LARGE_BOAT.get(), LargeBoatRenderer::new);
	}
}
