package com.minecraftabnormals.extraboats.core;

import net.minecraft.entity.EntityType;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ITag.INamedTag;

public class ModTags
{
	public static final INamedTag<EntityType<?>> SITTING_MOBS = EntityTypeTags.getTagById(Reference.MOD_ID + ":sitting_mobs");
}