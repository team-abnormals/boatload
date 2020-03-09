package com.markus1002.extraboats.advancements.criterion;

import net.minecraft.advancements.CriteriaTriggers;

public class ModCriteriaTriggers
{
	public static RideLargeBoatTrigger RIDE_LARGE_BOAT;
	
	public static void initCriteriaTriggers()
	{
		RIDE_LARGE_BOAT = CriteriaTriggers.register(new RideLargeBoatTrigger());
	}
}