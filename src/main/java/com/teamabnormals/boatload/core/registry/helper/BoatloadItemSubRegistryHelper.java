package com.teamabnormals.boatload.core.registry.helper;

import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import com.teamabnormals.boatload.common.item.ChestBoatItem;
import com.teamabnormals.boatload.common.item.FurnaceBoatItem;
import com.teamabnormals.boatload.common.item.LargeBoatItem;
import com.teamabnormals.boatload.core.api.BoatloadBoatType;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

public class BoatloadItemSubRegistryHelper extends ItemSubRegistryHelper {

	public BoatloadItemSubRegistryHelper(RegistryHelper parent) {
		super(parent, parent.getItemSubHelper().getDeferredRegister());
	}

	public RegistryObject<Item> createChestBoat(BoatloadBoatType type) {
		return this.deferredRegister.register(type.getRegistryName().getPath() + "_chest_boat", () -> new ChestBoatItem(type));
	}

	public RegistryObject<Item> createFurnaceBoat(BoatloadBoatType type) {
		return this.deferredRegister.register(type.getRegistryName().getPath() + "_furnace_boat", () -> new FurnaceBoatItem(type));
	}

	public RegistryObject<Item> createLargeBoat(BoatloadBoatType type) {
		return this.deferredRegister.register("large_" + type.getRegistryName().getPath() + "_boat", () -> new LargeBoatItem(type));
	}
}