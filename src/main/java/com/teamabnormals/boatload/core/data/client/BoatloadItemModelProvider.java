package com.teamabnormals.boatload.core.data.client;

import com.teamabnormals.boatload.core.Boatload;
import com.teamabnormals.boatload.core.other.BoatloadUtil;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;


public class BoatloadItemModelProvider extends ItemModelProvider {

	public BoatloadItemModelProvider(PackOutput generator, ExistingFileHelper existingFileHelper) {
		super(generator, Boatload.MOD_ID, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		BoatloadUtil.getItems().forEach(this::generated);
	}

	private void generated(Item item) {
		withExistingParent(name(item), "item/generated").texture("layer0", new ResourceLocation(this.modid, "item/" + name(item)));
	}

	private String name(ItemLike itemLike) {
		return ForgeRegistries.ITEMS.getKey(itemLike.asItem()).getPath();
	}
}