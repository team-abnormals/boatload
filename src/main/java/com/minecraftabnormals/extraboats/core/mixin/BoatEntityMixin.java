package com.minecraftabnormals.extraboats.core.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.minecraftabnormals.abnormals_core.common.world.storage.tracking.IDataManager;
import com.minecraftabnormals.extraboats.core.other.ExtraBoatsDataProcessors;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BannerItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

@Mixin(BoatEntity.class)
public abstract class BoatEntityMixin extends Entity {
	@Shadow
	protected BoatEntity.Status status;

	public BoatEntityMixin(EntityType<?> entityTypeIn, World worldIn) {
		super(entityTypeIn, worldIn);
	}

	@Shadow
	public abstract float getDamageTaken();

	public ItemStack getBanner() {
		return ((IDataManager) this).getValue(ExtraBoatsDataProcessors.BANNER);
	}

	public void setBanner(ItemStack itemStack) {
		((IDataManager) this).setValue(ExtraBoatsDataProcessors.BANNER, itemStack);
	}

	@Inject(method = "processInitialInteract", at = @At("HEAD"), cancellable = true)
	private void addBannerInteraction(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResultType> info) {
		ItemStack itemstack = player.getHeldItem(hand);

		if (this.getBanner().isEmpty() && itemstack.getItem() instanceof BannerItem) {
			if (!player.abilities.isCreativeMode) {
				player.getHeldItem(hand).shrink(1);
			}
			ItemStack itemstack1 = itemstack.copy();
			itemstack1.setCount(1);

			this.setBanner(itemstack1);
			if (!this.world.isRemote) {
				info.setReturnValue(ActionResultType.CONSUME);
			}
			else {
				info.setReturnValue(ActionResultType.SUCCESS);
			}
		}
	}

	@Inject(method = "attackEntityFrom", at = @At(value = "RETURN", ordinal = 1), cancellable = true)
	private void dropBannerWhenBroken(DamageSource source, float amount, CallbackInfoReturnable<Boolean> info) {
		boolean flag = source.getTrueSource() instanceof PlayerEntity && ((PlayerEntity) source.getTrueSource()).abilities.isCreativeMode;
		if (flag || this.getDamageTaken() > 40.0F) {
			if (!flag && this.world.getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
				this.entityDropItem(this.getBanner());
			}
		}
	}
}