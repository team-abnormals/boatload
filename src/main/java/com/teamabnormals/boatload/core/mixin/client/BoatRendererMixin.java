package com.teamabnormals.boatload.core.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.teamabnormals.boatload.core.other.BoatloadUtil;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.world.entity.vehicle.Boat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BoatRenderer.class)
public abstract class BoatRendererMixin {

	@Inject(method = "render(Lnet/minecraft/world/entity/vehicle/Boat;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At(value = "INVOKE", shift = Shift.AFTER, by = 2, target = "Lnet/minecraft/world/entity/vehicle/Boat;getBubbleAngle(F)F", ordinal = 0), cancellable = true)
	private void render(Boat entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight, CallbackInfo ci) {
		BoatloadUtil.renderChest(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
	}
}
