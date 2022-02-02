package com.teamabnormals.boatload.core.data.client;

import com.teamabnormals.boatload.core.Boatload;
import com.teamabnormals.boatload.core.other.BLUtil;
import com.teamabnormals.boatload.core.registry.BLEntityTypes;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.LanguageProvider;
import org.apache.commons.lang3.text.WordUtils;

public class BLLanguageProvider extends LanguageProvider {

	public BLLanguageProvider(DataGenerator gen) {
		super(gen, Boatload.MOD_ID, "en_us");
	}

	@Override
	protected void addTranslations() {
		this.add(BLEntityTypes.CHEST_BOAT.get(), "Boat with Chest");
		this.add(BLEntityTypes.FURNACE_BOAT.get(), "Boat with Furnace");
		this.add(BLEntityTypes.LARGE_BOAT.get(), "Large Boat");

		BLUtil.getChestBoats().forEach(this::addChestBoat);
		BLUtil.getFurnaceBoats().forEach(this::addFurnaceBoat);
		BLUtil.getLargeBoats().forEach(this::add);
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