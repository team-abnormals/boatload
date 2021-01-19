package com.minecraftabnormals.extraboats.core.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.minecraftabnormals.abnormals_core.common.world.storage.tracking.IDataManager;
import com.minecraftabnormals.extraboats.common.entity.item.boat.LargeBoatEntity;
import com.minecraftabnormals.extraboats.core.other.ExtraBoatsDataProcessors;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.item.BannerItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;

@Mixin(EntityRenderer.class)
public abstract class EntityRendererMixin<T extends Entity> {

	@Inject(method = "render", at = @At("HEAD"))
	private void renderBanner(T entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, CallbackInfo info) {
		if (entityIn instanceof BoatEntity && !((IDataManager) entityIn).getValue(ExtraBoatsDataProcessors.BANNER).isEmpty()) {
			BoatEntity boatentity = (BoatEntity) entityIn;
			matrixStackIn.push();
			matrixStackIn.translate(0.0D, 0.375D, 0.0D);
			matrixStackIn.rotate(Vector3f.YP.rotationDegrees(180.0F - entityYaw));
			float f = (float) boatentity.getTimeSinceHit() - partialTicks;
			float f1 = boatentity.getDamageTaken() - partialTicks;
			if (f1 < 0.0F) {
				f1 = 0.0F;
			}

			if (f > 0.0F) {
				matrixStackIn.rotate(Vector3f.XP.rotationDegrees(MathHelper.sin(f) * f * f1 / 10.0F * (float) boatentity.getForwardDirection()));
			}

			float f2 = boatentity.getRockingAngle(partialTicks);
			if (!MathHelper.epsilonEquals(f2, 0.0F)) {
				matrixStackIn.rotate(new Quaternion(new Vector3f(1.0F, 0.0F, 1.0F), boatentity.getRockingAngle(partialTicks), true));
			}

			ItemStack banner = ((IDataManager) entityIn).getValue(ExtraBoatsDataProcessors.BANNER);
			if (banner != null && banner.getItem() instanceof BannerItem) {
				World world = boatentity.getEntityWorld();
				int i;
				if (world != null) {
					i = WorldRenderer.getCombinedLight(world, boatentity.getPosition());
				} else {
					i = 15728880;
				}

				if (boatentity instanceof LargeBoatEntity) {
					matrixStackIn.push();
					matrixStackIn.scale(1.05F, 1.05F, 1.05F);
					matrixStackIn.translate(0.5D, (double) (3.0F / 16.0F), (double) (33.0F / 16.0F));
					matrixStackIn.rotate(Vector3f.YP.rotationDegrees(180.0F));
					banner.getItem().getItemStackTileEntityRenderer().func_239207_a_(banner, ItemCameraTransforms.TransformType.GROUND, matrixStackIn, bufferIn, i, OverlayTexture.NO_OVERLAY);
					matrixStackIn.pop();
				}
				else {
					matrixStackIn.push();
					matrixStackIn.translate(0.5D, (double) (3.0F / 16.0F), (double) (23.0F / 16.0F));
					matrixStackIn.rotate(Vector3f.YP.rotationDegrees(180.0F));
					banner.getItem().getItemStackTileEntityRenderer().func_239207_a_(banner, ItemCameraTransforms.TransformType.GROUND, matrixStackIn, bufferIn, i, OverlayTexture.NO_OVERLAY);
					matrixStackIn.pop();
				}
			}
			matrixStackIn.pop();
		}
	}
}