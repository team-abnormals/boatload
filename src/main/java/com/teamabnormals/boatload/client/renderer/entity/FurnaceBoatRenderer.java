package com.teamabnormals.boatload.client.renderer.entity;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.datafixers.util.Pair;
import com.mojang.math.Axis;
import com.teamabnormals.boatload.client.model.FurnaceBoatModel;
import com.teamabnormals.boatload.common.entity.vehicle.FurnaceBoat;
import com.teamabnormals.boatload.core.api.BoatloadBoatType;
import com.teamabnormals.boatload.core.other.BoatloadModelLayers;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Quaternionf;

import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class FurnaceBoatRenderer extends EntityRenderer<FurnaceBoat> {
	private final Map<BoatloadBoatType, Pair<ResourceLocation, FurnaceBoatModel>> boatResources;

	public FurnaceBoatRenderer(EntityRendererProvider.Context context) {
		super(context);
		this.shadowRadius = 0.8F;
		this.boatResources = BoatloadBoatType.values().stream().collect(ImmutableMap.toImmutableMap((type) -> type, (boatType) -> Pair.of(new ResourceLocation(boatType.registryName().getNamespace(), "textures/entity/furnace_boat/" + boatType.registryName().getPath()), new FurnaceBoatModel(context.bakeLayer(BoatloadModelLayers.createFurnaceBoatModelName(boatType))))));
	}

	@Override
	public void render(FurnaceBoat entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
		matrixStackIn.pushPose();
		matrixStackIn.translate(0.0D, 0.375D, 0.0D);
		matrixStackIn.mulPose(Axis.YP.rotationDegrees(180.0F - entityYaw));
		float f = (float) entityIn.getHurtTime() - partialTicks;
		float f1 = entityIn.getDamage() - partialTicks;
		if (f1 < 0.0F) {
			f1 = 0.0F;
		}

		if (f > 0.0F) {
			matrixStackIn.mulPose(Axis.XP.rotationDegrees(Mth.sin(f) * f * f1 / 10.0F * (float) entityIn.getHurtDir()));
		}

		float f2 = entityIn.getBubbleAngle(partialTicks);
		if (!Mth.equal(f2, 0.0F)) {
			matrixStackIn.mulPose((new Quaternionf()).setAngleAxis(entityIn.getBubbleAngle(partialTicks) * ((float) Math.PI / 180F), 1.0F, 0.0F, 1.0F));
		}

		Pair<ResourceLocation, FurnaceBoatModel> pair = getModelWithLocation(entityIn);
		ResourceLocation boatLocation = this.getTextureLocation(entityIn);
		BoatModel boatModel = pair.getSecond();
		matrixStackIn.scale(-1.0F, -1.0F, 1.0F);
		matrixStackIn.mulPose(Axis.YP.rotationDegrees(90.0F));
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
	public ResourceLocation getTextureLocation(FurnaceBoat boat) {
		String name = this.getModelWithLocation(boat).getFirst().toString();
		if (boat.getFuel() > 0)
			name += "_on";
		return new ResourceLocation(name + ".png");
	}

	public Pair<ResourceLocation, FurnaceBoatModel> getModelWithLocation(FurnaceBoat boat) {
		return this.boatResources.get(boat.getBoatloadBoatType());
	}
}
