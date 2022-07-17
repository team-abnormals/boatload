package com.teamabnormals.boatload.core.mixin;

import com.teamabnormals.blueprint.common.world.storage.tracking.IDataManager;
import com.teamabnormals.boatload.core.other.BoatloadTrackedData;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.BannerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Boat.class)
public abstract class BoatMixin extends Entity {

	public BoatMixin(EntityType<?> entityTypeIn, Level worldIn) {
		super(entityTypeIn, worldIn);
	}

	public ItemStack getBanner() {
		return ((IDataManager) this).getValue(BoatloadTrackedData.BANNER);
	}

	public void setBanner(ItemStack itemStack) {
		((IDataManager) this).setValue(BoatloadTrackedData.BANNER, itemStack);
	}

	@Inject(method = "interact", at = @At("HEAD"), cancellable = true)
	private void addBannerInteraction(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> info) {
		ItemStack itemstack = player.getItemInHand(hand);

		if (this.getBanner().isEmpty() && itemstack.getItem() instanceof BannerItem) {
			ItemStack itemstack1 = itemstack.copy();
			itemstack1.setCount(1);
			if (!player.getAbilities().instabuild) {
				player.getItemInHand(hand).shrink(1);
			}

			this.setBanner(itemstack1);
			info.setReturnValue(InteractionResult.sidedSuccess(this.level.isClientSide()));
		}
	}

	@Inject(method = "hurt", at = @At(value = "RETURN", ordinal = 1), cancellable = true)
	private void dropBannerWhenBroken(DamageSource source, float amount, CallbackInfoReturnable<Boolean> info) {
		boolean flag = source.getEntity() instanceof Player && ((Player) source.getEntity()).getAbilities().instabuild;
		if (flag || ((Boat) (Object) this).getDamage() > 40.0F) {
			if (!flag && this.level.getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
				this.spawnAtLocation(this.getBanner());
			}
		}
	}
}