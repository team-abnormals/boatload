package com.teamabnormals.boatload.core.data.client;

import com.teamabnormals.boatload.core.Boatload;
import com.teamabnormals.boatload.core.other.BoatloadUtil;
import com.teamabnormals.boatload.core.registry.BoatloadEntityTypes;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.commons.lang3.text.WordUtils;

public class BoatloadLanguageProvider extends LanguageProvider {

	public BoatloadLanguageProvider(DataGenerator generator) {
		super(generator, Boatload.MOD_ID, "en_us");
	}

	@Override
	protected void addTranslations() {
		this.add(BoatloadEntityTypes.FURNACE_BOAT.get(), "Boat with Furnace");
		this.add(BoatloadEntityTypes.LARGE_BOAT.get(), "Large Boat");

		BoatloadUtil.getFurnaceBoats().forEach(this::addFurnaceBoat);
		BoatloadUtil.getLargeBoats().forEach(this::add);
	}

	private void add(Item item) {
		ResourceLocation name = ForgeRegistries.ITEMS.getKey(item);
		if (name != null) this.add(item, format(name));
	}

	private void addFurnaceBoat(Item item) {
		ResourceLocation name = ForgeRegistries.ITEMS.getKey(item);
		if (name != null)
			this.add(item, format(name).replace("Furnace Boat", "Boat with Furnace"));
	}

	private String format(ResourceLocation registryName) {
		return WordUtils.capitalizeFully(registryName.getPath().replace("_", " "));
	}
}