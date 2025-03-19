package com.teamabnormals.boatload.core.data.client;

import com.teamabnormals.blueprint.core.data.client.BlueprintLanguageProvider;
import com.teamabnormals.boatload.core.Boatload;
import com.teamabnormals.boatload.core.other.BoatloadUtil;
import com.teamabnormals.boatload.core.registry.BoatloadEntityTypes;
import com.teamabnormals.boatload.core.registry.BoatloadItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class BoatloadLanguageProvider extends BlueprintLanguageProvider {

	public BoatloadLanguageProvider(PackOutput output) {
		super(output, Boatload.MOD_ID);
	}

	@Override
	protected void addTranslations() {
		this.add(BoatloadEntityTypes.FURNACE_BOAT.get(), "Boat with Furnace");
		this.add(BoatloadEntityTypes.LARGE_BOAT.get(), "Large Boat");

		this.add(BoatloadItems.CRIMSON_BOAT.get());
		this.add(BoatloadItems.WARPED_BOAT.get());
		this.addChestBoat(BoatloadItems.CRIMSON_CHEST_BOAT.get());
		this.addChestBoat(BoatloadItems.WARPED_CHEST_BOAT.get());

		BoatloadUtil.getFurnaceBoats().forEach(this::addFurnaceBoat);
		BoatloadUtil.getLargeBoats().forEach(this::add);
	}

	private void addChestBoat(Item item) {
		ResourceLocation name = BuiltInRegistries.ITEM.getKey(item);
		this.add(item, format(name).replace("Chest Boat", "Boat with Chest"));
	}

	private void addFurnaceBoat(Item item) {
		ResourceLocation name = BuiltInRegistries.ITEM.getKey(item);
		this.add(item, format(name).replace("Furnace Boat", "Boat with Furnace"));
	}
}