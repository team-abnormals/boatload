package com.minecraftabnormals.extraboats.core.other;

import com.minecraftabnormals.abnormals_core.common.world.storage.tracking.DataProcessors;
import com.minecraftabnormals.abnormals_core.common.world.storage.tracking.TrackedData;
import com.minecraftabnormals.abnormals_core.common.world.storage.tracking.TrackedDataManager;
import com.minecraftabnormals.extraboats.core.ExtraBoats;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ExtraBoatsDataProcessors {
	public static final TrackedData<ItemStack> BANNER = TrackedData.Builder.create(DataProcessors.STACK, () -> ItemStack.EMPTY).enableSaving().build();

	public static void registerTrackedData() {
		TrackedDataManager.INSTANCE.registerData(new ResourceLocation(ExtraBoats.MOD_ID, "banner"), BANNER);
	}
}