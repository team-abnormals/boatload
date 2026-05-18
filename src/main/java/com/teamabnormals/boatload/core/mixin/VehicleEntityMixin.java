package com.teamabnormals.boatload.core.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.teamabnormals.blueprint.common.world.storage.tracking.IDataManager;
import com.teamabnormals.boatload.core.other.BoatloadTrackedData;
import com.teamabnormals.boatload.core.other.ItemStackWrapper;
import com.teamabnormals.boatload.core.registry.BoatloadDataComponents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.VehicleEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(VehicleEntity.class)
public abstract class VehicleEntityMixin extends Entity {

	public VehicleEntityMixin(EntityType<?> entityTypeIn, Level worldIn) {
		super(entityTypeIn, worldIn);
	}

	@Inject(method = "destroy(Lnet/minecraft/world/item/Item;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;set(Lnet/minecraft/core/component/DataComponentType;Ljava/lang/Object;)Ljava/lang/Object;", shift = At.Shift.AFTER))
	private void addExtraComponent(Item dropItem, CallbackInfo ci, @Local ItemStack stack) {
		ItemStack banner = ((IDataManager) this).getValue(BoatloadTrackedData.BANNER);
		if (!banner.isEmpty()) {
			stack.set(BoatloadDataComponents.BANNER.get(), new ItemStackWrapper(banner));
		}

		ItemStack chest = ((IDataManager) this).getValue(BoatloadTrackedData.CHEST);
		if (!chest.isEmpty()) {
			stack.set(BoatloadDataComponents.CHEST.get(), new ItemStackWrapper(chest));
		}
	}
}