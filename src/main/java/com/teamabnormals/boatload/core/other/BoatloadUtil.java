package com.teamabnormals.boatload.core.other;

import com.google.common.collect.Lists;
import com.teamabnormals.blueprint.common.entity.BlueprintBoat;
import com.teamabnormals.blueprint.common.entity.BlueprintChestBoat;
import com.teamabnormals.blueprint.core.registry.BoatTypeRegistry;
import com.teamabnormals.boatload.common.entity.vehicle.BoatloadBoat;
import com.teamabnormals.boatload.common.item.FurnaceBoatItem;
import com.teamabnormals.boatload.common.item.LargeBoatItem;
import com.teamabnormals.boatload.core.Boatload;
import com.teamabnormals.boatload.core.api.BoatloadBoatType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;

import java.util.List;
import java.util.stream.Collectors;

public class BoatloadUtil {

	public static List<Item> getItems() {
		List<Item> items = Lists.newArrayList();
		Boatload.REGISTRY_HELPER.getItemSubHelper().getDeferredRegister().getEntries().forEach(registryObject -> items.add(registryObject.get()));
		return items;
	}

	public static List<FurnaceBoatItem> getFurnaceBoats() {
		return (List<FurnaceBoatItem>) (List<?>) getItems().stream().filter(item -> item instanceof FurnaceBoatItem).collect(Collectors.toList());
	}

	public static List<LargeBoatItem> getLargeBoats() {
		return (List<LargeBoatItem>) (List<?>) getItems().stream().filter(item -> item instanceof LargeBoatItem).collect(Collectors.toList());
	}

	public static boolean isNetherBoat(Entity entity) {
		ResourceLocation type = null;
		if (entity instanceof BlueprintBoat boat) {
			type = new ResourceLocation(BoatTypeRegistry.getNameForData(boat.getBoatTypeData()));
			type = new ResourceLocation(type.getPath());
		} else if (entity instanceof BlueprintChestBoat boat) {
			type = new ResourceLocation(BoatTypeRegistry.getNameForData(boat.getBoatTypeData()));
			type = new ResourceLocation(type.getPath());
		} else if (entity instanceof BoatloadBoat boat) {
			type = boat.getBoatloadBoatType().registryName();
		}

		return type != null && (type.equals(BoatloadBoatType.CRIMSON.registryName()) || type.equals(BoatloadBoatType.WARPED.registryName()));
	}
}
