package com.teamabnormals.boatload.core.other;

import com.teamabnormals.blueprint.common.world.storage.tracking.TrackedData;
import com.teamabnormals.blueprint.common.world.storage.tracking.TrackedDataManager;
import com.teamabnormals.boatload.core.Boatload;
import net.minecraft.world.item.ItemStack;

public class BoatloadTrackedData {
	public static final TrackedData<ItemStack> BANNER = TrackedData.Builder.create(ItemStack.STREAM_CODEC, () -> ItemStack.EMPTY).enableSaving(ItemStack.CODEC.fieldOf("id").fieldOf("count").fieldOf("components")).build();

	public static void registerTrackedData() {
		TrackedDataManager.INSTANCE.registerData(Boatload.location("banner"), BANNER);
	}
}