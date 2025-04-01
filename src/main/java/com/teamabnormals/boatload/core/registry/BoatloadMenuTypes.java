package com.teamabnormals.boatload.core.registry;

import com.teamabnormals.boatload.client.gui.screens.inventory.FurnaceBoatScreen;
import com.teamabnormals.boatload.common.inventory.FurnaceBoatMenu;
import com.teamabnormals.boatload.core.Boatload;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@EventBusSubscriber(modid = Boatload.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class BoatloadMenuTypes {
	public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(Registries.MENU, Boatload.MOD_ID);

	public static final DeferredHolder<MenuType<?>, MenuType<FurnaceBoatMenu>> FURNACE_BOAT = MENU_TYPES.register("furnace_boat", () -> new MenuType<>(FurnaceBoatMenu::new, FeatureFlags.DEFAULT_FLAGS));

	@SubscribeEvent
	public static void registerScreens(RegisterMenuScreensEvent event) {
		event.register(BoatloadMenuTypes.FURNACE_BOAT.get(), FurnaceBoatScreen::new);
	}
}