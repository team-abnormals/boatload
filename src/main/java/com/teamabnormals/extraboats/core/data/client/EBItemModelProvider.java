package com.teamabnormals.extraboats.core.data.client;

import com.teamabnormals.extraboats.core.ExtraBoats;
import com.teamabnormals.extraboats.core.other.EBUtil;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;


public class EBItemModelProvider extends ItemModelProvider {

	public EBItemModelProvider(DataGenerator generator, ExistingFileHelper fileHelper) {
		super(generator, ExtraBoats.MOD_ID, fileHelper);
	}

	@Override
	protected void registerModels() {
		EBUtil.getItems().forEach(this::generated);
	}

	private void generated(Item item) {
		withExistingParent(name(item), "item/generated").texture("layer0", new ResourceLocation(this.modid, "item/" + name(item)));
	}

	private String name(ItemLike itemLike) {
		return itemLike.asItem().getRegistryName().getPath();
	}
}