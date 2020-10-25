package com.markus1002.extraboats.common.item;

import com.markus1002.extraboats.common.entity.item.boat.FurnaceBoatEntity;
import com.markus1002.extraboats.common.entity.item.boat.EBBoatEntity;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class FurnaceBoatItem extends EBBoatItem
{
	public FurnaceBoatItem(EBBoatEntity.BoatType typeIn, Item.Properties properties)
	{
		super(typeIn, properties);
	}

	@Override
	protected EBBoatEntity getBoatEntity(World worldIn, RayTraceResult raytraceresult, ItemStack itemStack)
	{
		return new FurnaceBoatEntity(worldIn, raytraceresult.getHitVec().x, raytraceresult.getHitVec().y, raytraceresult.getHitVec().z);
	}
	
	@Override
    public int getBurnTime(ItemStack itemStack)
    {
        return 1200;
    }
}