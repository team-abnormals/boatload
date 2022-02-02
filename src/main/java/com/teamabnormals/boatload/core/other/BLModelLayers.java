package com.teamabnormals.boatload.core.other;

import com.teamabnormals.boatload.common.entity.vehicle.BLBoat.BLBoatType;
import com.teamabnormals.boatload.core.Boatload;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class BLModelLayers {

	public static ModelLayerLocation createBoatModelName(BLBoatType boatType) {
		return register("boat/" + boatType.getName(), "main");
	}

	public static ModelLayerLocation createLargeBoatModelName(BLBoatType boatType) {
		return register("large_boat/" + boatType.getName(), "main");
	}

	public static ModelLayerLocation register(String name, String layer) {
		return new ModelLayerLocation(new ResourceLocation(Boatload.MOD_ID, name), layer);
	}
}
