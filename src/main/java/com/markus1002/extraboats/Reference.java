package com.markus1002.extraboats;

import net.minecraft.util.ResourceLocation;

public class Reference
{
	public static final String MOD_ID = "extraboats";
	
	public static ResourceLocation location(String name)
	{
		return new ResourceLocation(MOD_ID, name);
	}
}