package com.teamabnormals.boatload.core.other;

import com.teamabnormals.boatload.core.api.BoatloadBoatType;
import com.teamabnormals.boatload.core.Boatload;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class BoatloadModelLayers {

	public static ModelLayerLocation createBoatModelName(BoatloadBoatType boatType) {
		return register("boat/" + boatType.getRegistryName().getPath(), "main");
	}

	public static ModelLayerLocation createLargeBoatModelName(BoatloadBoatType boatType) {
		return register("boat/" + boatType.getRegistryName().getPath() + "_large", "main");
	}

	public static ModelLayerLocation register(String name, String layer) {
		return new ModelLayerLocation(new ResourceLocation(Boatload.MOD_ID, name), layer);
	}
}
