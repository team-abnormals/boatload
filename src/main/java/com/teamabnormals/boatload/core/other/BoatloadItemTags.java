package com.teamabnormals.boatload.core.other;

import com.teamabnormals.blueprint.core.util.TagUtil;
import com.teamabnormals.boatload.core.Boatload;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class BoatloadItemTags {
	public static final TagKey<Item> FURNACE_BOATS = itemTag("furnace_boats");
	public static final TagKey<Item> LARGE_BOATS = itemTag("large_boats");

	private static TagKey<Item> itemTag(String name) {
		return TagUtil.itemTag(Boatload.MOD_ID, name);
	}
}