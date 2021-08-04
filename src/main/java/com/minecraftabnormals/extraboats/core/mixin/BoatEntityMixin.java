package com.minecraftabnormals.extraboats.core.mixin;

import com.minecraftabnormals.abnormals_core.common.world.storage.tracking.IDataManager;
import com.minecraftabnormals.extraboats.core.other.EBDataProcessors;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BannerItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BoatEntity.class)
public abstract class BoatEntityMixin extends Entity {
	@Shadow
	protected BoatEntity.Status status;

	public BoatEntityMixin(EntityType<?> entityTypeIn, World worldIn) {
		super(entityTypeIn, worldIn);
	}

	@Shadow
	public abstract float getDamage();

	public ItemStack getBanner() {
		return ((IDataManager) this).getValue(EBDataProcessors.BANNER);
	}

	public void setBanner(ItemStack itemStack) {
		((IDataManager) this).setValue(EBDataProcessors.BANNER, itemStack);
	}

	@Inject(method = "interact", at = @At("HEAD"), cancellable = true)
	private void addBannerInteraction(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResultType> info) {
		ItemStack itemstack = player.getItemInHand(hand);

		if (this.getBanner().isEmpty() && itemstack.getItem() instanceof BannerItem) {
			if (!player.abilities.instabuild) {
				player.getItemInHand(hand).shrink(1);
			}
			ItemStack itemstack1 = itemstack.copy();
			itemstack1.setCount(1);

			this.setBanner(itemstack1);
			info.setReturnValue(ActionResultType.sidedSuccess(level.isClientSide()));
		}
	}

	@Inject(method = "hurt", at = @At(value = "RETURN", ordinal = 1), cancellable = true)
	private void dropBannerWhenBroken(DamageSource source, float amount, CallbackInfoReturnable<Boolean> info) {
		boolean flag = source.getEntity() instanceof PlayerEntity && ((PlayerEntity) source.getEntity()).abilities.instabuild;
		if (flag || this.getDamage() > 40.0F) {
			if (!flag && this.level.getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
				this.spawnAtLocation(this.getBanner());
			}
		}
	}
}