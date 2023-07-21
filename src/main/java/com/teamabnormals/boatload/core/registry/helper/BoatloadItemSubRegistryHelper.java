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

	public Pair<RegistryObject<Item>, RegistryObject<Item>> createBoatAndChestBoatItem(String wood, Block block, boolean raft) {
		ResourceLocation type = new ResourceLocation(this.parent.getModId() + ":" + wood);
		RegistryObject<Item> boat = this.deferredRegister.register(wood + "_" + (raft ? "raft" : "boat"), () -> new BlueprintBoatItem(false, type, createSimpleItemProperty(1)));
		RegistryObject<Item> chestBoat = this.deferredRegister.register(wood + "_chest_" + (raft ? "raft" : "boat"), () -> new BlueprintBoatItem(true, type, createSimpleItemProperty(1)));
		BlueprintBoatTypes.registerType(type, () -> { return boat.get(); } , () -> { return chestBoat.get(); } , () -> block, raft);
		return Pair.of(boat, chestBoat);
	}

	public RegistryObject<Item> createFurnaceBoat(BoatloadBoatType type) {
		return this.deferredRegister.register(type.registryName().getPath() + "_furnace_" + (type.raft() ? "raft" : "boat"), () -> new FurnaceBoatItem(type));
	}

	public RegistryObject<Item> createLargeBoat(BoatloadBoatType type) {
		return this.deferredRegister.register("large_" + type.registryName().getPath() + "_" + (type.raft() ? "raft" : "boat"), () -> new LargeBoatItem(type));
	}
}