package com.teamabnormals.boatload.core.data.client;

import com.teamabnormals.boatload.core.Boatload;
import com.teamabnormals.boatload.core.other.BLUtil;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;


public class BLItemModelProvider extends ItemModelProvider {

	public BLItemModelProvider(DataGenerator generator, ExistingFileHelper fileHelper) {
		super(generator, Boatload.MOD_ID, fileHelper);
	}

	@Override
	protected void registerModels() {
		BLUtil.getItems().forEach(this::generated);
	}

	private void generated(Item item) {
		withExistingParent(name(item), "item/generated").texture("layer0", new ResourceLocation(this.modid, "item/" + name(item)));
	}

	private String name(ItemLike itemLike) {
		return itemLike.asItem().getRegistryName().getPath();
	}
}