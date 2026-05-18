package com.teamabnormals.boatload.core.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.teamabnormals.blueprint.common.world.storage.tracking.IDataManager;
import com.teamabnormals.boatload.core.other.BoatloadTrackedData;
import com.teamabnormals.boatload.core.other.BoatloadUtil;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.MinecartChest;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public abstract class EntityRendererMixin<T extends Entity> {

	@Inject(method = "render", at = @At("HEAD"))
	private void renderBanner(T entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight, CallbackInfo info) {
		ItemStack banner = ((IDataManager) entity).getValue(BoatloadTrackedData.BANNER);
		if (entity instanceof Boat boat && !banner.isEmpty()) {
			BoatloadUtil.renderBanner(boat, entityYaw, partialTicks, poseStack, buffer, packedLight);
		}

		if (entity instanceof AbstractMinecart minecart && !banner.isEmpty()) {
			BoatloadUtil.renderBanner(minecart, entityYaw, partialTicks, poseStack, buffer, packedLight);
		}
	}
}