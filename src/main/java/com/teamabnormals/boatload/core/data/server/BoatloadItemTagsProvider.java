package com.teamabnormals.boatload.core.data.server;

import com.teamabnormals.blueprint.core.other.tags.BlueprintItemTags;
import com.teamabnormals.boatload.core.Boatload;
import com.teamabnormals.boatload.core.other.BoatloadUtil;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.data.ExistingFileHelper;

import static com.teamabnormals.boatload.core.registry.BoatloadItems.*;

public class BoatloadItemTagsProvider extends ItemTagsProvider {

	//TODO: Update this code for 1.20.1.
	public BoatloadItemTagsProvider(PackOutput generator, ExistingFileHelper existingFileHelper) {
		super(generator, null, null, Boatload.MOD_ID, existingFileHelper);
		//super(generator, new BlockTagsProvider(generator, Boatload.MOD_ID, existingFileHelper), Boatload.MOD_ID, existingFileHelper);
	}

	@Override
	protected void addTags(Provider provider) {
		this.tag(ItemTags.NON_FLAMMABLE_WOOD).add(CRIMSON_BOAT.get(), CRIMSON_CHEST_BOAT.get(), CRIMSON_FURNACE_BOAT.get(), LARGE_CRIMSON_BOAT.get(), WARPED_BOAT.get(), WARPED_CHEST_BOAT.get(), WARPED_FURNACE_BOAT.get(), LARGE_WARPED_BOAT.get());
		this.tag(ItemTags.BOATS).add(CRIMSON_BOAT.get(), WARPED_BOAT.get()).addTag(BlueprintItemTags.FURNACE_BOATS).addTag(BlueprintItemTags.LARGE_BOATS);
		this.tag(ItemTags.CHEST_BOATS).add(CRIMSON_CHEST_BOAT.get(), WARPED_CHEST_BOAT.get());
		BoatloadUtil.getFurnaceBoats().forEach(boat -> this.tag(BlueprintItemTags.FURNACE_BOATS).add(boat));
		BoatloadUtil.getLargeBoats().forEach(boat -> this.tag(BlueprintItemTags.LARGE_BOATS).add(boat));
	}
}