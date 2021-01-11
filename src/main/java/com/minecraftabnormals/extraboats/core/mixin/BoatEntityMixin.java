package com.minecraftabnormals.extraboats.core.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.minecraftabnormals.abnormals_core.common.world.storage.tracking.IDataManager;
import com.minecraftabnormals.extraboats.core.ExtraBoats;

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
	public BoatEntityMixin(EntityType<?> entityTypeIn, World worldIn) {
		super(entityTypeIn, worldIn);
	}

	@Shadow
	float getDamageTaken() {
		return 0.0F;
	}

	@Inject(method = "writeAdditional", at = @At("TAIL"))
	private void writeBanner(CompoundNBT compound, CallbackInfo info) {
		if (!this.getBanner().isEmpty()) {
			compound.put("Banner", this.getBanner().write(new CompoundNBT()));
		}
	}

	@Inject(method = "readAdditional", at = @At("TAIL"))
	private void readBanner(CompoundNBT compound, CallbackInfo info) {
		CompoundNBT compoundnbt = compound.getCompound("Banner");
		this.setBanner(ItemStack.read(compoundnbt));
	}

	public ItemStack getBanner() {
		return ((IDataManager) this).getValue(ExtraBoats.BANNER);
	}

	public void setBanner(ItemStack itemStack) {
		((IDataManager) this).setValue(ExtraBoats.BANNER, itemStack);
	}

	@Inject(method = "processInitialInteract", at = @At("HEAD"), cancellable = true)
	private void addBanner(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResultType> cir) {
		ItemStack itemstack = player.getHeldItem(hand);

		if (this.getBanner().isEmpty() && itemstack.getItem() instanceof BannerItem) {
			if (!this.world.isRemote) {
				this.setBanner(itemstack);
				if (!player.abilities.isCreativeMode) {
					player.getHeldItem(hand).shrink(1);
				}

				cir.setReturnValue(ActionResultType.CONSUME);
			} else {
				cir.setReturnValue(ActionResultType.SUCCESS);
			}
		}
	}

	@Inject(method = "attackEntityFrom", at = @At(value = "RETURN", ordinal = 0), cancellable = true)
	private void dropBanner(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
		boolean flag = source.getTrueSource() instanceof PlayerEntity && ((PlayerEntity) source.getTrueSource()).abilities.isCreativeMode;
		if (flag || this.getDamageTaken() > 40.0F) {
			if (!flag && this.world.getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
				this.entityDropItem(this.getBanner());
			}
		}
	}
}