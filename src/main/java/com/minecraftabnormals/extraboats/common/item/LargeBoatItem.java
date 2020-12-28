package com.minecraftabnormals.extraboats.common.item;

import com.minecraftabnormals.extraboats.common.entity.item.boat.EBBoatEntity;
import com.minecraftabnormals.extraboats.common.entity.item.boat.LargeBoatEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class LargeBoatItem extends EBBoatItem {
	public LargeBoatItem(EBBoatEntity.BoatType typeIn, Item.Properties properties) {
		super(typeIn, properties);
	}

	@Override
	protected EBBoatEntity getBoatEntity(World worldIn, RayTraceResult raytraceresult, ItemStack itemStack) {
		return new LargeBoatEntity(worldIn, raytraceresult.getHitVec().x, raytraceresult.getHitVec().y, raytraceresult.getHitVec().z);
	}

	@Override
	public int getBurnTime(ItemStack itemStack) {
		return 2400;
	}
}