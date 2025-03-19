package com.teamabnormals.boatload.core.mixin;

import com.teamabnormals.blueprint.common.world.storage.tracking.IDataManager;
import com.teamabnormals.boatload.core.other.BoatloadTrackedData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.VehicleEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
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

	@Inject(method = "destroy(Lnet/minecraft/world/item/Item;)V", at = @At("TAIL"))
	private void dropBannerWhenBroken(Item dropItem, CallbackInfo ci) {
		if (((Object) this) instanceof Boat && this.level().getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
			ItemStack stack = ((IDataManager) this).getValue(BoatloadTrackedData.BANNER);
			if (!stack.isEmpty()) {
				this.spawnAtLocation(stack);
			}
		}
	}
}