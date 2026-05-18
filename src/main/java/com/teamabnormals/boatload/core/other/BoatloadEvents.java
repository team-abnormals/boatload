package com.teamabnormals.boatload.core.other;

import com.teamabnormals.boatload.core.Boatload;
import com.teamabnormals.boatload.core.registry.BoatloadDataComponents;
import net.minecraft.network.chat.Component;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

import java.util.function.Consumer;

@EventBusSubscriber(modid = Boatload.MOD_ID)
public class BoatloadEvents {

	@SubscribeEvent
	public static void onItemTooltip(ItemTooltipEvent event) {
		Consumer<Component> consumer = event.getToolTip()::add;
		event.getItemStack().addToTooltip(BoatloadDataComponents.BANNER, event.getContext(), consumer, event.getFlags());
		event.getItemStack().addToTooltip(BoatloadDataComponents.CHEST, event.getContext(), consumer, event.getFlags());
	}
}