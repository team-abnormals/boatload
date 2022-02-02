package com.teamabnormals.extraboats.core.data.client;

import com.teamabnormals.extraboats.core.ExtraBoats;
import com.teamabnormals.extraboats.core.other.EBUtil;
import com.teamabnormals.extraboats.core.registry.EBEntityTypes;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.LanguageProvider;
import org.apache.commons.lang3.text.WordUtils;

public class EBLanguageProvider extends LanguageProvider {

	public EBLanguageProvider(DataGenerator gen) {
		super(gen, ExtraBoats.MOD_ID, "en_us");
	}

	@Override
	protected void addTranslations() {
		this.add(EBEntityTypes.CHEST_BOAT.get(), "Boat with Chest");
		this.add(EBEntityTypes.FURNACE_BOAT.get(), "Boat with Furnace");
		this.add(EBEntityTypes.LARGE_BOAT.get(), "Large Boat");

		EBUtil.getChestBoats().forEach(this::addChestBoat);
		EBUtil.getFurnaceBoats().forEach(this::addFurnaceBoat);
		EBUtil.getLargeBoats().forEach(this::add);
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