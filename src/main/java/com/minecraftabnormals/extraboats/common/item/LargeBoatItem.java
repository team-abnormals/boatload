package com.minecraftabnormals.extraboats.common.item;

import com.minecraftabnormals.extraboats.common.entity.item.boat.ExtraBoatsBoatEntity;
import com.minecraftabnormals.extraboats.common.entity.item.boat.LargeBoatEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class LargeBoatItem extends ExtraBoatsBoatItem {
	public LargeBoatItem(ExtraBoatsBoatEntity.BoatType typeIn, Item.Properties properties) {
		super(typeIn, properties);
	}

	@Override
	protected ExtraBoatsBoatEntity getBoatEntity(World worldIn, RayTraceResult raytraceresult, ItemStack itemStack) {
		return new LargeBoatEntity(worldIn, raytraceresult.getLocation().x, raytraceresult.getLocation().y, raytraceresult.getLocation().z);
	}

	@Override
	public int getBurnTime(ItemStack itemStack) {
		return 2400;
	}
}