package com.minecraftabnormals.extraboats.core.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.minecraftabnormals.abnormals_core.common.world.storage.tracking.IDataManager;
import com.minecraftabnormals.extraboats.core.other.ExtraBoatsDataProcessors;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

@Mixin(Entity.class)
public abstract class EntityMixin {
	@Shadow
	private World world;

	@Shadow
	private boolean removed;

	@Inject(method = "onLivingFall", at = @At(value = "HEAD"), cancellable = true)
	private void dropBannerUponFalling(float distance, float damageMultiplier, CallbackInfoReturnable<Boolean> info) {
		if ((Object) this instanceof BoatEntity) {
			if (!this.world.isRemote && !this.removed) {
				if (this.world.getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
					((Entity)(Object) this).entityDropItem(((IDataManager)(Object) this).getValue(ExtraBoatsDataProcessors.BANNER));
				}
			}
		}
	}
}
