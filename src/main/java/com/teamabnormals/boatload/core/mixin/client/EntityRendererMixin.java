package com.teamabnormals.boatload.core.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.teamabnormals.blueprint.common.entity.BlueprintBoat;
import com.teamabnormals.blueprint.common.world.storage.tracking.IDataManager;
import com.teamabnormals.boatload.common.entity.vehicle.BoatloadBoat;
import com.teamabnormals.boatload.common.entity.vehicle.LargeBoat;
import com.teamabnormals.boatload.core.other.BoatloadTrackedData;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.BannerItem;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import org.joml.Quaternionf;
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
			matrixStackIn.mulPose(Axis.YP.rotationDegrees(180.0F - entityYaw));
			float f = (float) boat.getHurtTime() - partialTicks;
			float f1 = boat.getDamage() - partialTicks;
			if (f1 < 0.0F) {
				f1 = 0.0F;
			}

			if (f > 0.0F) {
				matrixStackIn.mulPose(Axis.XP.rotationDegrees(Mth.sin(f) * f * f1 / 10.0F * (float) boat.getHurtDir()));
			}

			float f2 = boat.getBubbleAngle(partialTicks);
			if (!Mth.equal(f2, 0.0F)) {
				matrixStackIn.mulPose((new Quaternionf()).setAngleAxis(boat.getBubbleAngle(partialTicks) * ((float)Math.PI / 180F), 1.0F, 0.0F, 1.0F));
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
				float xoffset = 0;
				float yoffset = 0;
				float zoffset = 0;
				float angle = 180.0F;
				if (isBoatRaft(boat)) {
					xoffset = 1.0F;
					yoffset = 2.0F;
					zoffset = 17.0F;
					angle = 0;
				}

				float f3 = boat instanceof LargeBoat ? 36.0F : 23.0F;
				matrixStackIn.pushPose();
				matrixStackIn.translate((0.5D - xoffset), (3.0F - yoffset) / 16.0F, (f3 - zoffset) / 16.0F);
				matrixStackIn.mulPose(Axis.YP.rotationDegrees(angle));
				IClientItemExtensions.of(banner).getCustomRenderer().renderByItem(banner, ItemDisplayContext.GROUND, matrixStackIn, bufferIn, i, OverlayTexture.NO_OVERLAY);
				matrixStackIn.popPose();
			}
			matrixStackIn.popPose();
		}
	}
	
	private boolean isBoatRaft(Boat boat) {
		if (boat instanceof BoatloadBoat) {
			if (((BoatloadBoat)boat).getBoatloadBoatType().raft()) {
				return true;
			}
		}
		else if (boat instanceof BlueprintBoat) {
			if (((BlueprintBoat)boat).getBoatType().isRaft()) {
				return true;
			}
		}
		else {
			if (((Boat)boat).getVariant() == Boat.Type.BAMBOO) {
				return true;
			}
		}
		return false;
	}
}