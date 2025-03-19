package com.teamabnormals.boatload.core.registry.helper;

import com.mojang.datafixers.util.Pair;
import com.teamabnormals.blueprint.common.item.BlueprintBoatItem;
import com.teamabnormals.blueprint.core.registry.BlueprintBoatTypes;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import com.teamabnormals.boatload.common.item.FurnaceBoatItem;
import com.teamabnormals.boatload.common.item.LargeBoatItem;
import com.teamabnormals.boatload.core.api.BoatloadBoatType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredItem;

public class BoatloadItemSubRegistryHelper extends ItemSubRegistryHelper {

	public BoatloadItemSubRegistryHelper(RegistryHelper parent) {
		super(parent);
	}

	public Pair<DeferredItem<Item>, DeferredItem<Item>> createBoatAndChestBoatItem(String wood, Block block) {
		ResourceLocation type = ResourceLocation.fromNamespaceAndPath(this.parent.getModId(), wood);
		DeferredItem<Item> boat = this.deferredRegister.register(wood + "_boat", () -> new BlueprintBoatItem(false, type, createSimpleItemProperty(1)));
		DeferredItem<Item> chestBoat = this.deferredRegister.register(wood + "_chest_boat", () -> new BlueprintBoatItem(true, type, createSimpleItemProperty(1)));
		BlueprintBoatTypes.registerType(type, boat, chestBoat, () -> block, false);
		return Pair.of(boat, chestBoat);
	}

	public DeferredItem<Item> createFurnaceBoat(String name, BoatloadBoatType type) {
		return this.deferredRegister.register(name, () -> new FurnaceBoatItem(type));
	}

	public DeferredItem<Item> createFurnaceBoat(BoatloadBoatType type) {
		return this.createFurnaceBoat(type.registryName().getPath() + "_furnace_boat", type);
	}

	public DeferredItem<Item> createLargeBoat(String name, BoatloadBoatType type) {
		return this.deferredRegister.register(name, () -> new LargeBoatItem(type));
	}

	public DeferredItem<Item> createLargeBoat(BoatloadBoatType type) {
		return this.createLargeBoat("large_" + type.registryName().getPath() + "_boat", type);
	}
}