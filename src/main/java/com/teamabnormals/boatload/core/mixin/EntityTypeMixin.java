package com.teamabnormals.boatload.core.mixin;

import com.teamabnormals.blueprint.common.world.storage.tracking.IDataManager;
import com.teamabnormals.boatload.core.other.BoatloadTrackedData;
import com.teamabnormals.boatload.core.registry.BoatloadDataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.MinecartChest;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Consumer;

@Mixin(EntityType.class)
public abstract class EntityTypeMixin {

	@Inject(method = "createDefaultStackConfig", at = @At(value = "HEAD"), cancellable = true)
	private static <T extends Entity> void createDefaultStackConfig(ServerLevel serverLevel, ItemStack stack, Player player, CallbackInfoReturnable<Consumer<T>> cir) {
		Consumer<T> consumer = entity -> {
			if (stack.has(BoatloadDataComponents.CHEST)) {
				ItemStack chestStack = stack.get(BoatloadDataComponents.CHEST).getItem();
				((IDataManager) entity).setValue(BoatloadTrackedData.CHEST, chestStack);
				if (entity instanceof MinecartChest minecart && chestStack.getItem() instanceof BlockItem blockItem) {
					minecart.setDisplayBlockState(blockItem.getBlock().defaultBlockState());
					minecart.setDisplayOffset(8);
				}
			}

			if (stack.has(BoatloadDataComponents.BANNER)) {
				((IDataManager) entity).setValue(BoatloadTrackedData.BANNER, stack.get(BoatloadDataComponents.BANNER).getItem());
			}
		};
		if (cir.getReturnValue() != null) {
			cir.setReturnValue(cir.getReturnValue().andThen(consumer));
		} else {
			cir.setReturnValue(consumer);
		}
	}
}
