package com.teamabnormals.boatload.client.renderer.entity;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.datafixers.util.Pair;
import com.mojang.math.Axis;
import com.teamabnormals.boatload.client.model.LargeBoatModel;
import com.teamabnormals.boatload.client.model.LargeRaftModel;
import com.teamabnormals.boatload.common.entity.vehicle.LargeBoat;
import com.teamabnormals.boatload.core.Boatload;
import com.teamabnormals.boatload.core.api.BoatloadBoatType;
import com.teamabnormals.boatload.core.other.BoatloadModelLayers;

import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.WaterPatchModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Map;

import org.joml.Quaternionf;

@OnlyIn(Dist.CLIENT)
public class LargeBoatRenderer extends EntityRenderer<LargeBoat> {
	private final Map<BoatloadBoatType, Pair<ResourceLocation, ListModel<LargeBoat>>> boatResources;

	public LargeBoatRenderer(EntityRendererProvider.Context context) {
		super(context);
		this.shadowRadius = 1.2F;
		this.boatResources = BoatloadBoatType.values().stream().collect(ImmutableMap.toImmutableMap((temp) -> {
			return temp;
		}, (boattype) -> {
			return Pair.of(new ResourceLocation(Boatload.MOD_ID, getTextureLocation(boattype)), this.createLargeBoatModel(context, boattype));
		}));
	}

	private static String getTextureLocation(BoatloadBoatType boattype) {
		return "textures/entity/large_boat/" + boattype.registryName().getPath() + ".png";
	}
	
	private ListModel<LargeBoat> createLargeBoatModel(EntityRendererProvider.Context context, BoatloadBoatType boattype) {
		ModelLayerLocation modellayerlocation = BoatloadModelLayers.createLargeBoatModelName(boattype);
		ModelPart modelpart = context.bakeLayer(modellayerlocation);
		if (boattype.raft()) {
			return (ListModel<LargeBoat>) new LargeRaftModel(modelpart);
		} else {
			return (ListModel<LargeBoat>) new LargeBoatModel(modelpart);
		}
	}
	
	public void render(LargeBoat entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
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
			matrixStackIn.mulPose((new Quaternionf()).setAngleAxis(entityIn.getBubbleAngle(partialTicks) * ((float)Math.PI / 180F), 1.0F, 0.0F, 1.0F));
		}

		Pair<ResourceLocation, ListModel<LargeBoat>> pair = getModelWithLocation(entityIn);
		ResourceLocation boatLocation = pair.getFirst();
		ListModel<LargeBoat> listmodel = pair.getSecond();
		matrixStackIn.scale(-1.0F, -1.0F, 1.0F);
		matrixStackIn.mulPose(Axis.YP.rotationDegrees(90.0F));
		listmodel.setupAnim(entityIn, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F);
		VertexConsumer ivertexbuilder = bufferIn.getBuffer(listmodel.renderType(boatLocation));
		listmodel.renderToBuffer(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		if (!entityIn.isUnderWater()) {
			VertexConsumer vertexconsumer1 = bufferIn.getBuffer(RenderType.waterMask());
			if (listmodel instanceof WaterPatchModel) {
				WaterPatchModel waterpatchmodel = (WaterPatchModel) listmodel;
				waterpatchmodel.waterPatch().render(matrixStackIn, vertexconsumer1, packedLightIn, OverlayTexture.NO_OVERLAY);
			}
		}
		matrixStackIn.popPose();
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}

	@Override
	public ResourceLocation getTextureLocation(LargeBoat boat) {
		return getModelWithLocation(boat).getFirst();
	}

	public Pair<ResourceLocation, ListModel<LargeBoat>> getModelWithLocation(LargeBoat boat) {
		return this.boatResources.get(boat.getBoatloadBoatType());
	}
}