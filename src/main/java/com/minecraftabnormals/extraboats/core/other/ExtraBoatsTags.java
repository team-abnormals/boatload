package com.minecraftabnormals.extraboats.core.other;

import com.minecraftabnormals.extraboats.core.ExtraBoats;

import net.minecraft.entity.EntityType;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ITag.INamedTag;

public class ExtraBoatsTags {
	public static final INamedTag<EntityType<?>> SITTING_MOBS = EntityTypeTags.getTagById(ExtraBoats.MOD_ID + ":sitting_mobs");
}