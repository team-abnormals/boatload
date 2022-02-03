package com.teamabnormals.boatload.client.renderer.entity;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.datafixers.util.Pair;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import com.teamabnormals.boatload.common.entity.vehicle.BoatloadBoat;
import com.teamabnormals.boatload.core.api.BoatloadBoatType;
import com.teamabnormals.boatload.core.other.BoatloadModelLayers;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class BoatloadBoatRenderer extends EntityRenderer<BoatloadBoat> {
	private final Map<BoatloadBoatType, Pair<ResourceLocation, BoatModel>> boatResources;

	public BoatloadBoatRenderer(EntityRendererProvider.Context context) {
		super(context);
		this.shadowRadius = 0.8F;
		this.boatResources = BoatloadBoatType.values().stream().collect(ImmutableMap.toImmutableMap((type) -> type, (boatType) -> Pair.of(new ResourceLocation(boatType.registryName().getNamespace(), "textures/entity/boat/" + boatType.registryName().getPath() + ".png"), new BoatModel(context.bakeLayer(BoatloadModelLayers.createBoatModelName(boatType))))));
	}

	public void render(BoatloadBoat entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
		matrixStackIn.pushPose();
		matrixStackIn.translate(0.0D, 0.375D, 0.0D);
		matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(180.0F - entityYaw));
		float f = (float) entityIn.getHurtTime() - partialTicks;
		float f1 = entityIn.getDamage() - partialTicks;
		if (f1 < 0.0F) {
			f1 = 0.0F;
		}

		if (f > 0.0F) {
			matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(Mth.sin(f) * f * f1 / 10.0F * (float) entityIn.getHurtDir()));
		}

		float f2 = entityIn.getBubbleAngle(partialTicks);
		if (!Mth.equal(f2, 0.0F)) {
			matrixStackIn.mulPose(new Quaternion(new Vector3f(1.0F, 0.0F, 1.0F), entityIn.getBubbleAngle(partialTicks), true));
		}

		BlockState blockstate = entityIn.getDisplayTile();
		if (blockstate.getRenderShape() != RenderShape.INVISIBLE) {
			matrixStackIn.pushPose();
			matrixStackIn.scale(0.75F, 0.75F, 0.75F);
			matrixStackIn.translate(0.5D, -3.0F / 16.0F, 1.1D);
			matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(180.0F));
			Minecraft.getInstance().getBlockRenderer().renderSingleBlock(blockstate, matrixStackIn, bufferIn, packedLightIn, OverlayTexture.NO_OVERLAY);
			matrixStackIn.popPose();
		}

		Pair<ResourceLocation, BoatModel> pair = getModelWithLocation(entityIn);
		ResourceLocation boatLocation = pair.getFirst();
		BoatModel boatModel = pair.getSecond();
		matrixStackIn.scale(-1.0F, -1.0F, 1.0F);
		matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(90.0F));
		boatModel.setupAnim(entityIn, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F);
		VertexConsumer ivertexbuilder = bufferIn.getBuffer(boatModel.renderType(boatLocation));
		boatModel.renderToBuffer(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		if (!entityIn.isUnderWater()) {
			VertexConsumer ivertexbuilder1 = bufferIn.getBuffer(RenderType.waterMask());
			boatModel.waterPatch().render(matrixStackIn, ivertexbuilder1, packedLightIn, OverlayTexture.NO_OVERLAY);
		}
		matrixStackIn.popPose();
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}

	@Override
	public ResourceLocation getTextureLocation(BoatloadBoat boat) {
		return getModelWithLocation(boat).getFirst();
	}

	public Pair<ResourceLocation, BoatModel> getModelWithLocation(BoatloadBoat boat) {
		return this.boatResources.get(boat.getExtraBoatType());
	}
}
