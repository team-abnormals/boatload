package com.teamabnormals.boatload.core.data.client;

import com.teamabnormals.boatload.core.Boatload;
import com.teamabnormals.boatload.core.other.BoatloadUtil;
import com.teamabnormals.boatload.core.registry.BoatloadEntityTypes;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.LanguageProvider;
import org.apache.commons.lang3.text.WordUtils;

public class BoatloadLanguageProvider extends LanguageProvider {

	public BoatloadLanguageProvider(DataGenerator gen) {
		super(gen, Boatload.MOD_ID, "en_us");
	}

	@Override
	protected void addTranslations() {
		this.add(BoatloadEntityTypes.CHEST_BOAT.get(), "Boat with Chest");
		this.add(BoatloadEntityTypes.FURNACE_BOAT.get(), "Boat with Furnace");
		this.add(BoatloadEntityTypes.LARGE_BOAT.get(), "Large Boat");

		BoatloadUtil.getChestBoats().forEach(this::addChestBoat);
		BoatloadUtil.getFurnaceBoats().forEach(this::addFurnaceBoat);
		BoatloadUtil.getLargeBoats().forEach(this::add);
	}

	private void add(Item item) {
		if (item.getRegistryName() != null) this.add(item, format(item.getRegistryName()));
	}

	private void addChestBoat(Item item) {
		if (item.getRegistryName() != null) this.add(item, format(item.getRegistryName()).replace("Chest Boat", "Boat with Chest"));
	}

	private void addFurnaceBoat(Item item) {
		if (item.getRegistryName() != null) this.add(item, format(item.getRegistryName()).replace("Chest Boat", "Boat with Furnace"));
	}

	private String format(ResourceLocation registryName) {
		return WordUtils.capitalizeFully(registryName.getPath().replace("_", " "));
	}
}