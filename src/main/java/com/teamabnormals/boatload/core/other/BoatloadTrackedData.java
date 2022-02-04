package com.teamabnormals.boatload.core.other;

import com.teamabnormals.blueprint.common.world.storage.tracking.DataProcessors;
import com.teamabnormals.blueprint.common.world.storage.tracking.TrackedData;
import com.teamabnormals.blueprint.common.world.storage.tracking.TrackedDataManager;
import com.teamabnormals.boatload.core.Boatload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class BoatloadTrackedData {
	public static final TrackedData<ItemStack> BANNER = TrackedData.Builder.create(DataProcessors.STACK, () -> ItemStack.EMPTY).enableSaving().build();

	public static void registerTrackedData() {
		TrackedDataManager.INSTANCE.registerData(new ResourceLocation(Boatload.MOD_ID, "banner"), BANNER);
	}
}