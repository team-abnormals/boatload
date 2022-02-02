package com.teamabnormals.extraboats.core.registry.helper;

import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import com.teamabnormals.extraboats.common.entity.vehicle.EBBoat.EBBoatType;
import com.teamabnormals.extraboats.common.item.ChestBoatItem;
import com.teamabnormals.extraboats.common.item.FurnaceBoatItem;
import com.teamabnormals.extraboats.common.item.LargeBoatItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

public class EBItemSubRegistryHelper extends ItemSubRegistryHelper {

	public EBItemSubRegistryHelper(RegistryHelper parent) {
		super(parent, parent.getItemSubHelper().getDeferredRegister());
	}

	public RegistryObject<Item> createChestBoat(EBBoatType type, String... modIds) {
		return this.deferredRegister.register(type.getName() + "_chest_boat", () -> new ChestBoatItem(type, (new Item.Properties()).stacksTo(1).tab(areModsLoaded(modIds) ? CreativeModeTab.TAB_TRANSPORTATION : null)));
	}

	public RegistryObject<Item> createFurnaceBoat(EBBoatType type, String... modIds) {
		return this.deferredRegister.register(type.getName() + "_furnace_boat", () -> new FurnaceBoatItem(type, (new Item.Properties()).stacksTo(1).tab(areModsLoaded(modIds) ? CreativeModeTab.TAB_TRANSPORTATION : null)));
	}

	public RegistryObject<Item> createLargeBoat(EBBoatType type, String... modIds) {
		return this.deferredRegister.register("large_" + type.getName() + "_boat", () -> new LargeBoatItem(type, (new Item.Properties()).stacksTo(1).tab(areModsLoaded(modIds) ? CreativeModeTab.TAB_TRANSPORTATION : null)));
	}
}