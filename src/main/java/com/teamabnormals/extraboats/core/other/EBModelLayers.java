package com.teamabnormals.extraboats.core.other;

import com.teamabnormals.extraboats.common.entity.vehicle.EBBoat.EBBoatType;
import com.teamabnormals.extraboats.core.ExtraBoats;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class EBModelLayers {

	public static ModelLayerLocation createBoatModelName(EBBoatType boatType) {
		return register("boat/" + boatType.getName(), "main");
	}

	public static ModelLayerLocation createLargeBoatModelName(EBBoatType boatType) {
		return register("large_boat/" + boatType.getName(), "main");
	}

	public static ModelLayerLocation register(String name, String layer) {
		return new ModelLayerLocation(new ResourceLocation(ExtraBoats.MOD_ID, name), layer);
	}
}
