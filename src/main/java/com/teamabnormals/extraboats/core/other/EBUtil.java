package com.teamabnormals.extraboats.core.other;

import com.google.common.collect.Lists;
import com.teamabnormals.extraboats.common.item.ChestBoatItem;
import com.teamabnormals.extraboats.common.item.EBBoatItem;
import com.teamabnormals.extraboats.common.item.FurnaceBoatItem;
import com.teamabnormals.extraboats.common.item.LargeBoatItem;
import com.teamabnormals.extraboats.core.ExtraBoats;

import java.util.List;
import java.util.stream.Collectors;

public class EBUtil {

	public static List<EBBoatItem> getItems() {
		List<EBBoatItem> items = Lists.newArrayList();
		ExtraBoats.REGISTRY_HELPER.getItemSubHelper().getDeferredRegister().getEntries().forEach(registryObject -> items.add((EBBoatItem) registryObject.get()));
		return items;
	}

	public static List<EBBoatItem> getChestBoats() {
		return getItems().stream().filter(item -> item instanceof ChestBoatItem).collect(Collectors.toList());
	}

	public static List<EBBoatItem> getFurnaceBoats() {
		return getItems().stream().filter(item -> item instanceof FurnaceBoatItem).collect(Collectors.toList());
	}

	public static List<EBBoatItem> getLargeBoats() {
		return getItems().stream().filter(item -> item instanceof LargeBoatItem).collect(Collectors.toList());
	}
}
