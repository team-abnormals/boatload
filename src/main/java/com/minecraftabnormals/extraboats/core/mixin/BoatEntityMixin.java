package com.minecraftabnormals.extraboats.core.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.minecraftabnormals.abnormals_core.common.world.storage.tracking.IDataManager;
import com.minecraftabnormals.extraboats.common.entity.item.boat.ChestBoatEntity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BannerItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

@Mixin(BoatEntity.class)
public abstract class BoatEntityMixin extends Entity {
	private static final DataParameter<ItemStack> BANNER = EntityDataManager.createKey(ChestBoatEntity.class, DataSerializers.ITEMSTACK);

	public BoatEntityMixin(EntityType<?> entityTypeIn, World worldIn) {
		super(entityTypeIn, worldIn);
	}

	@Inject(method = "writeAdditional", at = @At("TAIL"))
	private void writeBanner(CompoundNBT compound) {
		if (!this.getBanner().isEmpty()) {
			compound.put("Banner", this.getBanner().write(new CompoundNBT()));
		}
	}

	@Inject(method = "readAdditional", at = @At("TAIL"))
	private void readBanner(CompoundNBT compound) {
		CompoundNBT compoundnbt = compound.getCompound("Banner");
		this.setBanner(ItemStack.read(compoundnbt));
	}

	public ItemStack getBanner() {
		return this.getDataManager().get(BANNER);
	}

	public void setBanner(ItemStack itemStack) {
		this.getDataManager().set(BANNER, itemStack);
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
}