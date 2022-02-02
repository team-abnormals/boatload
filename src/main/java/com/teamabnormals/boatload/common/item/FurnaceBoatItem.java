package com.teamabnormals.boatload.common.item;

import com.teamabnormals.boatload.common.entity.vehicle.BLBoat;
import com.teamabnormals.boatload.common.entity.vehicle.BLBoat.BLBoatType;
import com.teamabnormals.boatload.common.entity.vehicle.FurnaceBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

public class FurnaceBoatItem extends BLBoatItem {

	public FurnaceBoatItem(BLBoatType typeIn, Item.Properties properties) {
		super(typeIn, properties);
	}

	@Override
	protected BLBoat getBoatEntity(Level worldIn, HitResult raytraceresult, ItemStack itemStack) {
		return new FurnaceBoat(worldIn, raytraceresult.getLocation().x, raytraceresult.getLocation().y, raytraceresult.getLocation().z);
	}

	@Override
	public int getBurnTime(ItemStack itemStack, RecipeType<?> recipeType) {
		return 1200;
	}
}