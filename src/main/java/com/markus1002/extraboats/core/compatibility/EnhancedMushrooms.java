package com.markus1002.extraboats.core.compatibility;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ObjectHolder;

public class EnhancedMushrooms {
    @ObjectHolder("enhanced_mushrooms:red_mushroom_planks")
    public static final Block RED_MUSHROOM_PLANKS = null;

    @ObjectHolder("enhanced_mushrooms:brown_mushroom_planks")
    public static final Block BROWN_MUSHROOM_PLANKS = null;

    @ObjectHolder("enhanced_mushrooms:glowshroom_planks")
    public static final Block GLOWSHROOM_PLANKS = null;


    @ObjectHolder("enhanced_mushrooms:red_mushroom_boat")
    public static final Item RED_MUSHROOM_BOAT = null;

    @ObjectHolder("enhanced_mushrooms:brown_mushroom_boat")
    public static final Item BROWN_MUSHROOM_BOAT = null;

    @ObjectHolder("enhanced_mushrooms:glowshroom_boat")
    public static final Item GLOWSHROOM_BOAT = null;

    public static boolean isInstalled()
    {
        return ModList.get() != null && ModList.get().getModContainerById("enhanced_mushrooms").isPresent();
    }

    public static boolean isInstalledWQuark()
    {
        return isInstalled() && ModList.get().getModContainerById("quark").isPresent();
    }
}
