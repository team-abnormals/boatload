package com.minecraftabnormals.extraboats.common.item;

import com.minecraftabnormals.extraboats.common.entity.item.boat.ExtraBoatsBoatEntity;
import com.minecraftabnormals.extraboats.common.entity.item.boat.FurnaceBoatEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class FurnaceBoatItem extends EBBoatItem {
	public FurnaceBoatItem(ExtraBoatsBoatEntity.BoatType typeIn, Item.Properties properties) {
		super(typeIn, properties);
	}

	@Override
	protected ExtraBoatsBoatEntity getBoatEntity(World worldIn, RayTraceResult raytraceresult, ItemStack itemStack) {
		return new FurnaceBoatEntity(worldIn, raytraceresult.getHitVec().x, raytraceresult.getHitVec().y, raytraceresult.getHitVec().z);
	}

	@Override
	public int getBurnTime(ItemStack itemStack) {
		return 1200;
	}
}