package com.minecraftabnormals.extraboats.core.mixin;

import com.minecraftabnormals.abnormals_core.common.world.storage.tracking.IDataManager;
import com.minecraftabnormals.extraboats.core.other.EBDataProcessors;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {
	@Shadow
	private World level;

	@Shadow
	private boolean removed;

	@Inject(method = "causeFallDamage", at = @At(value = "HEAD"), cancellable = true)
	private void dropBannerUponFalling(float distance, float damageMultiplier, CallbackInfoReturnable<Boolean> info) {
		if ((Object) this instanceof BoatEntity) {
			if (!this.level.isClientSide && !this.removed) {
				if (this.level.getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
					((Entity)(Object) this).spawnAtLocation(((IDataManager) this).getValue(EBDataProcessors.BANNER));
				}
			}
		}
	}
}
