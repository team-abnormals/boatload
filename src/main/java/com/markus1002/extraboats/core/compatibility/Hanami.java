package com.markus1002.extraboats.core.compatibility;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ObjectHolder;

public class Hanami {
    @ObjectHolder("hanami:sakura_planks")
    public static final Block SAKURA_PLANKS = null;


    @ObjectHolder("hanami:sakura_boat")
    public static final Item SAKURA_BOAT = null;

    public static boolean isInstalled()
    {
        return ModList.get() != null && ModList.get().getModContainerById("hanami").isPresent();
    }
}
