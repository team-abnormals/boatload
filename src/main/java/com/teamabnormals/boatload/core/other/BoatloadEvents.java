package com.teamabnormals.boatload.core.other;

import com.teamabnormals.blueprint.common.world.storage.tracking.IDataManager;
import com.teamabnormals.boatload.core.Boatload;
import com.teamabnormals.boatload.core.registry.BoatloadDataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent.EntityInteract;

import java.util.function.Consumer;

@EventBusSubscriber(modid = Boatload.MOD_ID)
public class BoatloadEvents {

	@SubscribeEvent
	public static void entityInteract(EntityInteract event) {
		Level level = event.getLevel();
		Player player = event.getEntity();
		Entity target = event.getTarget();
		ItemStack stack = event.getItemStack();

		ItemStack banner = ((IDataManager) target).getValue(BoatloadTrackedData.BANNER);
		if (stack.is(Tags.Items.TOOLS_SHEAR) && !banner.isEmpty()) {
			level.playSound(null, target, SoundEvents.SHEEP_SHEAR, SoundSource.PLAYERS, 1.0F, 1.0F);
			target.spawnAtLocation(banner, target.getBbHeight());
			((IDataManager) target).setValue(BoatloadTrackedData.BANNER, ItemStack.EMPTY);
			stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(event.getHand()));
			event.setCancellationResult(InteractionResult.sidedSuccess(level.isClientSide()));
			event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public static void onItemTooltip(ItemTooltipEvent event) {
		Consumer<Component> consumer = event.getToolTip()::add;
		event.getItemStack().addToTooltip(BoatloadDataComponents.CHEST, event.getContext(), consumer, event.getFlags());
		event.getItemStack().addToTooltip(BoatloadDataComponents.BANNER, event.getContext(), consumer, event.getFlags());
	}
}