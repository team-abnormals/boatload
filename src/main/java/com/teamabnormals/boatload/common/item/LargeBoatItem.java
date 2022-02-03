package com.teamabnormals.boatload.common.item;

import com.teamabnormals.boatload.common.entity.vehicle.BoatloadBoat;
import com.teamabnormals.boatload.common.entity.vehicle.LargeBoat;
import com.teamabnormals.boatload.core.api.BoatloadBoatType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

public class LargeBoatItem extends BoatloadBoatItem {

	public LargeBoatItem(BoatloadBoatType typeIn) {
		super(typeIn);
	}

	public LargeBoatItem(BoatloadBoatType typeIn, Item.Properties properties) {
		super(typeIn, properties);
	}

	@Override
	protected BoatloadBoat getBoatEntity(Level worldIn, HitResult result, ItemStack itemStack) {
		return new LargeBoat(worldIn, result.getLocation().x, result.getLocation().y, result.getLocation().z);
	}

	@Override
	public int getBurnTime(ItemStack itemStack, RecipeType<?> recipeType) {
		return 2400;
	}
}