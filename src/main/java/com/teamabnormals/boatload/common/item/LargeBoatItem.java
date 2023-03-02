package com.teamabnormals.boatload.common.item;

import com.teamabnormals.blueprint.core.util.item.filling.TargetedItemCategoryFiller;
import com.teamabnormals.boatload.common.entity.vehicle.BoatloadBoat;
import com.teamabnormals.boatload.common.entity.vehicle.LargeBoat;
import com.teamabnormals.boatload.core.api.BoatloadBoatType;
import net.minecraft.core.NonNullList;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

public class LargeBoatItem extends BoatloadBoatItem {
	private final TargetedItemCategoryFiller FILLER = new TargetedItemCategoryFiller(() -> this.getType().furnaceBoat().get());

	public LargeBoatItem(BoatloadBoatType type) {
		super(type);
	}

	public LargeBoatItem(BoatloadBoatType type, Item.Properties properties) {
		super(type, properties);
	}

	@Override
	protected BoatloadBoat getBoatEntity(Level level, HitResult result, ItemStack stack) {
		return new LargeBoat(level, result.getLocation().x, result.getLocation().y, result.getLocation().z);
	}

	@Override
	public int getBurnTime(ItemStack stack, RecipeType<?> recipeType) {
		return !stack.is(ItemTags.NON_FLAMMABLE_WOOD) ? 2400 : super.getBurnTime(stack, recipeType);
	}

	@Override
	public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> items) {
		FILLER.fillItem(this, group, items);
	}
}