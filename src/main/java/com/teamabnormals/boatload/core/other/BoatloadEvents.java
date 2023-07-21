package com.teamabnormals.boatload.core.other;

import com.teamabnormals.boatload.common.entity.vehicle.FurnaceBoat;
import com.teamabnormals.boatload.core.Boatload;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickItem;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Boatload.MOD_ID)
public class BoatloadEvents {

	@SubscribeEvent
	public static void onRightClickItem(RightClickItem event) {
		ItemStack itemstack = event.getItemStack();
		Player player = event.getEntity();

		if (player.getVehicle() instanceof FurnaceBoat && FurnaceBoat.FUEL_ITEMS.test(itemstack)) {
			FurnaceBoat boat = (FurnaceBoat) player.getVehicle();
			if (boat.getFuel() + 3600 <= 32000) {
				if (!player.getAbilities().instabuild) {
					itemstack.shrink(1);
				}

				boat.setFuel(boat.getFuel() + 3600);
				event.setCanceled(true);
				event.setCancellationResult(InteractionResult.sidedSuccess(player.level().isClientSide()));
			}
		}
	}
}