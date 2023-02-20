package com.teamabnormals.boatload.core.data.server;

import com.teamabnormals.boatload.core.Boatload;
import com.teamabnormals.boatload.core.other.BoatloadItemTags;
import com.teamabnormals.boatload.core.other.BoatloadUtil;
import com.teamabnormals.boatload.core.registry.BoatloadItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BoatloadItemTagsProvider extends ItemTagsProvider {

	public BoatloadItemTagsProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, new BlockTagsProvider(generator, Boatload.MOD_ID, existingFileHelper), Boatload.MOD_ID, existingFileHelper);
	}

	@Override
	protected void addTags() {
		this.tag(ItemTags.BOATS).add(BoatloadItems.CRIMSON_BOAT.get(), BoatloadItems.WARPED_BOAT.get()).addTag(BoatloadItemTags.FURNACE_BOATS).addTag(BoatloadItemTags.LARGE_BOATS);
		this.tag(ItemTags.CHEST_BOATS).add(BoatloadItems.CRIMSON_CHEST_BOAT.get(), BoatloadItems.WARPED_CHEST_BOAT.get());
		BoatloadUtil.getFurnaceBoats().forEach(boat -> this.tag(BoatloadItemTags.FURNACE_BOATS).add(boat));
		BoatloadUtil.getLargeBoats().forEach(boat -> this.tag(BoatloadItemTags.LARGE_BOATS).add(boat));
	}
}