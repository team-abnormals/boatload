package com.teamabnormals.boatload.core.registry;

import com.teamabnormals.boatload.common.inventory.FurnaceBoatMenu;
import com.teamabnormals.boatload.core.Boatload;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BoatloadMenuTypes {
	public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(Registries.MENU, Boatload.MOD_ID);

	public static final DeferredHolder<MenuType<?>, MenuType<FurnaceBoatMenu>> FURNACE_BOAT = MENU_TYPES.register("furnace_boat", () -> new MenuType<>(FurnaceBoatMenu::new, FeatureFlags.DEFAULT_FLAGS));
}