package com.teamabnormals.boatload.core.mixin;

import com.teamabnormals.blueprint.common.world.storage.tracking.IDataManager;
import com.teamabnormals.boatload.core.other.BoatloadTrackedData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {

	@Inject(method = "causeFallDamage", at = @At(value = "HEAD"), cancellable = true)
	private void dropBannerUponFalling(float distance, float damageMultiplier, DamageSource source, CallbackInfoReturnable<Boolean> info) {
		if ((Object) this instanceof Boat boat) {
			if (!boat.level.isClientSide && !((Entity) (Object) this).isRemoved()) {
				if (boat.level.getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
					((Entity) (Object) this).spawnAtLocation(((IDataManager) this).getValue(BoatloadTrackedData.BANNER));
				}
			}
		}
	}
}
