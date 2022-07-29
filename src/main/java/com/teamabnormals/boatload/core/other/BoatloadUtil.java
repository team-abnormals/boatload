package com.teamabnormals.boatload.core.other;

import com.google.common.collect.Lists;
import com.teamabnormals.boatload.common.item.BoatloadBoatItem;
import com.teamabnormals.boatload.common.item.FurnaceBoatItem;
import com.teamabnormals.boatload.common.item.LargeBoatItem;
import com.teamabnormals.boatload.core.Boatload;

import java.util.List;
import java.util.stream.Collectors;

public class BoatloadUtil {

	public static List<BoatloadBoatItem> getItems() {
		List<BoatloadBoatItem> items = Lists.newArrayList();
		Boatload.REGISTRY_HELPER.getItemSubHelper().getDeferredRegister().getEntries().forEach(registryObject -> items.add((BoatloadBoatItem) registryObject.get()));
		return items;
	}

	public static List<BoatloadBoatItem> getFurnaceBoats() {
		return getItems().stream().filter(item -> item instanceof FurnaceBoatItem).collect(Collectors.toList());
	}

	public static List<BoatloadBoatItem> getLargeBoats() {
		return getItems().stream().filter(item -> item instanceof LargeBoatItem).collect(Collectors.toList());
	}
}
