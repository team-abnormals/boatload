package com.markus1002.extraboats.common.item;

import com.markus1002.extraboats.common.entity.item.boat.ChestBoatEntity;
import com.markus1002.extraboats.common.entity.item.boat.ModBoatEntity;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class ChestBoatItem extends ModBoatItem
{
	public ChestBoatItem(ModBoatEntity.BoatType typeIn, Item.Properties properties)
	{
		super(typeIn, properties);
	}

	protected ModBoatEntity getBoatEntity(World worldIn, RayTraceResult raytraceresult)
	{
		return new ChestBoatEntity(worldIn, raytraceresult.getHitVec().x, raytraceresult.getHitVec().y, raytraceresult.getHitVec().z);
	}

	public int getBurnTime(ItemStack itemStack)
	{
		return 1500;
	}
}