package com.teamabnormals.boatload.common.item;

import com.teamabnormals.boatload.common.entity.vehicle.BoatloadBoat;
import com.teamabnormals.boatload.common.entity.vehicle.FurnaceBoat;
import com.teamabnormals.boatload.core.api.BoatloadBoatType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class FurnaceBoatItem extends BoatloadBoatItem {

	public FurnaceBoatItem(BoatloadBoatType type) {
		super(type);
	}

	public FurnaceBoatItem(BoatloadBoatType type, Item.Properties properties) {
		super(type, properties);
	}

	@Override
	protected BoatloadBoat getBoatEntity(Level level, double x, double y, double z, ItemStack stack) {
		return new FurnaceBoat(level, x, y, z);
	}
}