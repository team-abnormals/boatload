package com.teamabnormals.boatload.core.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import com.teamabnormals.blueprint.common.world.storage.tracking.IDataManager;
import com.teamabnormals.boatload.common.entity.vehicle.LargeBoat;
import com.teamabnormals.boatload.core.other.BoatloadTrackedData;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.BannerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public abstract class EntityRendererMixin<T extends Entity> {

	@Inject(method = "render", at = @At("HEAD"))
	private void renderBanner(T entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, CallbackInfo info) {
		if (entityIn instanceof Boat boat && !((IDataManager) entityIn).getValue(BoatloadTrackedData.BANNER).isEmpty()) {
			matrixStackIn.pushPose();
			matrixStackIn.translate(0.0D, 0.375D, 0.0D);
			matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(180.0F - entityYaw));
			float f = (float) boat.getHurtTime() - partialTicks;
			float f1 = boat.getDamage() - partialTicks;
			if (f1 < 0.0F) {
				f1 = 0.0F;
			}

			if (f > 0.0F) {
				matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(Mth.sin(f) * f * f1 / 10.0F * (float) boat.getHurtDir()));
			}

			float f2 = boat.getBubbleAngle(partialTicks);
			if (!Mth.equal(f2, 0.0F)) {
				matrixStackIn.mulPose(new Quaternion(new Vector3f(1.0F, 0.0F, 1.0F), boat.getBubbleAngle(partialTicks), true));
			}

			ItemStack banner = ((IDataManager) entityIn).getValue(BoatloadTrackedData.BANNER);
			if (banner != null && banner.getItem() instanceof BannerItem) {
				Level level = boat.getCommandSenderWorld();
				int i;
				if (level != null) {
					i = LevelRenderer.getLightColor(level, boat.blockPosition());
				} else {
					i = 15728880;
				}

				if (boat instanceof LargeBoat) {
					matrixStackIn.pushPose();
					matrixStackIn.scale(1.05F, 1.05F, 1.05F);
					matrixStackIn.translate(0.5D, 3.0F / 16.0F, 33.0F / 16.0F);
					matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(180.0F));
					IClientItemExtensions.of(banner).getCustomRenderer().renderByItem(banner, ItemTransforms.TransformType.GROUND, matrixStackIn, bufferIn, i, OverlayTexture.NO_OVERLAY);
					matrixStackIn.popPose();
				} else {
					matrixStackIn.pushPose();
					matrixStackIn.translate(0.5D, 3.0F / 16.0F, 23.0F / 16.0F);
					matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(180.0F));
					IClientItemExtensions.of(banner).getCustomRenderer().renderByItem(banner, ItemTransforms.TransformType.GROUND, matrixStackIn, bufferIn, i, OverlayTexture.NO_OVERLAY);
					matrixStackIn.popPose();
				}
			}
			matrixStackIn.popPose();
		}
	}
}