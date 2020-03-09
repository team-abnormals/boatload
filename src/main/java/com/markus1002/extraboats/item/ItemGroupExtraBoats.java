package com.markus1002.extraboats.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ItemGroupExtraBoats extends ItemGroup
{
	public ItemGroupExtraBoats(int index, String label)
	{
		super(index, label);
	}

	@Override
	public ItemStack createIcon()
	{
        return new ItemStack(ModItems.OAK_FURNACE_BOAT);
	}
}