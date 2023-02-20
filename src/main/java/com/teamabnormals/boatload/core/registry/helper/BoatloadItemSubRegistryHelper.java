package com.teamabnormals.boatload.core.registry.helper;

import com.mojang.datafixers.util.Pair;
import com.teamabnormals.blueprint.common.item.BlueprintBoatItem;
import com.teamabnormals.blueprint.core.registry.BoatTypeRegistry;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import com.teamabnormals.boatload.common.item.FurnaceBoatItem;
import com.teamabnormals.boatload.common.item.LargeBoatItem;
import com.teamabnormals.boatload.core.api.BoatloadBoatType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

public class BoatloadItemSubRegistryHelper extends ItemSubRegistryHelper {

	public BoatloadItemSubRegistryHelper(RegistryHelper parent) {
		super(parent, parent.getItemSubHelper().getDeferredRegister());
	}

	public Pair<RegistryObject<Item>, RegistryObject<Item>> createBoatAndChestBoatItem(String wood, Block block) {
		String type = this.parent.getModId() + ":" + wood;
		RegistryObject<Item> boat = this.deferredRegister.register(wood + "_boat", () -> new BlueprintBoatItem(false, type, createSimpleItemProperty(1, CreativeModeTab.TAB_TRANSPORTATION)));
		RegistryObject<Item> chestBoat = this.deferredRegister.register(wood + "_chest_boat", () -> new BlueprintBoatItem(true, type, createSimpleItemProperty(1, CreativeModeTab.TAB_TRANSPORTATION)));
		BoatTypeRegistry.registerBoat(type, boat, chestBoat, () -> block);
		return Pair.of(boat, chestBoat);
	}

	public RegistryObject<Item> createFurnaceBoat(BoatloadBoatType type) {
		return this.deferredRegister.register(type.registryName().getPath() + "_furnace_boat", () -> new FurnaceBoatItem(type));
	}

	public RegistryObject<Item> createLargeBoat(BoatloadBoatType type) {
		return this.deferredRegister.register("large_" + type.registryName().getPath() + "_boat", () -> new LargeBoatItem(type));
	}
}