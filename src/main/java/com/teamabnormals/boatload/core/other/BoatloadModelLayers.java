package com.teamabnormals.boatload.core.other;

import com.teamabnormals.boatload.core.Boatload;
import com.teamabnormals.boatload.core.api.BoatloadBoatType;
import net.minecraft.client.model.geom.ModelLayerLocation;

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
}
