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
import net.minecraftforge.registries.RegistryObject;

public class BoatloadItemSubRegistryHelper extends ItemSubRegistryHelper {

	public BoatloadItemSubRegistryHelper(RegistryHelper parent) {
		super(parent, parent.getItemSubHelper().getDeferredRegister());
	}

	public Pair<RegistryObject<Item>, RegistryObject<Item>> createBoatAndChestBoatItem(String wood, Block block) {
		ResourceLocation type = new ResourceLocation(this.parent.getModId(), wood);
		RegistryObject<Item> boat = this.deferredRegister.register(wood + "_boat", () -> new BlueprintBoatItem(false, type, createSimpleItemProperty(1)));
		RegistryObject<Item> chestBoat = this.deferredRegister.register(wood + "_chest_boat", () -> new BlueprintBoatItem(true, type, createSimpleItemProperty(1)));
		BlueprintBoatTypes.registerType(type, boat, chestBoat, () -> block, false);
		return Pair.of(boat, chestBoat);
	}

	public RegistryObject<Item> createFurnaceBoat(String name, BoatloadBoatType type) {
		return this.deferredRegister.register(name, () -> new FurnaceBoatItem(type));
	}

	public RegistryObject<Item> createFurnaceBoat(BoatloadBoatType type) {
		return this.createFurnaceBoat(type.registryName().getPath() + "_furnace_boat", type);
	}

	public RegistryObject<Item> createLargeBoat(String name, BoatloadBoatType type) {
		return this.deferredRegister.register(name, () -> new LargeBoatItem(type));
	}

	public RegistryObject<Item> createLargeBoat(BoatloadBoatType type) {
		return this.createLargeBoat("large_" + type.registryName().getPath() + "_boat", type);
	}
}