package com.markus1002.extraboats.client.renderer.entity;

import com.markus1002.extraboats.client.renderer.entity.model.LargeBoatModel;
import com.markus1002.extraboats.common.entity.item.boat.LargeBoatEntity;
import com.markus1002.extraboats.core.Reference;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LargeBoatRenderer extends EntityRenderer<LargeBoatEntity>
{
	private static final ResourceLocation[] LARGE_BOAT_TEXTURES = new ResourceLocation[]{
			Reference.location("textures/entity/large_boat/oak.png"),
			Reference.location("textures/entity/large_boat/spruce.png"),
			Reference.location("textures/entity/large_boat/birch.png"),
			Reference.location("textures/entity/large_boat/jungle.png"),
			Reference.location("textures/entity/large_boat/acacia.png"),
			Reference.location("textures/entity/large_boat/dark_oak.png"),
			Reference.location("textures/entity/large_boat/cherry.png"),
			Reference.location("textures/entity/large_boat/dead.png"),
			Reference.location("textures/entity/large_boat/ethereal.png"),
			Reference.location("textures/entity/large_boat/fir.png"),
			Reference.location("textures/entity/large_boat/hellbark.png"),
			Reference.location("textures/entity/large_boat/jacaranda.png"),
			Reference.location("textures/entity/large_boat/magic.png"),
			Reference.location("textures/entity/large_boat/mahogany.png"),
			Reference.location("textures/entity/large_boat/palm.png"),
			Reference.location("textures/entity/large_boat/redwood.png"),
			Reference.location("textures/entity/large_boat/umbran.png"),
			Reference.location("textures/entity/large_boat/willow.png"),
			Reference.location("textures/entity/large_boat/driftwood.png"),
			Reference.location("textures/entity/large_boat/river.png"),
			Reference.location("textures/entity/large_boat/bamboo.png"),
			Reference.location("textures/entity/large_boat/poise.png"),
			Reference.location("textures/entity/large_boat/wisteria.png"),
			Reference.location("textures/entity/large_boat/se_willow.png"),
			Reference.location("textures/entity/large_boat/rosewood.png"),
			Reference.location("textures/entity/large_boat/aspen.png"),
			Reference.location("textures/entity/large_boat/kousa.png"),
			Reference.location("textures/entity/large_boat/yucca.png"),
			Reference.location("textures/entity/large_boat/grimwood.png"),
			Reference.location("textures/entity/large_boat/maple.png"),
			Reference.location("textures/entity/large_boat/hive.png")};
	protected final LargeBoatModel modelLargeBoat = new LargeBoatModel();

	public LargeBoatRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn);
		this.shadowSize = 0.8F;
	}

	public void render(LargeBoatEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn)
	{
		matrixStackIn.push();
		matrixStackIn.translate(0.0D, 0.375D, 0.0D);
		matrixStackIn.rotate(Vector3f.YP.rotationDegrees(180.0F - entityYaw));
		float f = (float)entityIn.getTimeSinceHit() - partialTicks;
		float f1 = entityIn.getDamageTaken() - partialTicks;
		if (f1 < 0.0F) {
			f1 = 0.0F;
		}

		if (f > 0.0F)
		{
			matrixStackIn.rotate(Vector3f.XP.rotationDegrees(MathHelper.sin(f) * f * f1 / 10.0F * (float)entityIn.getForwardDirection()));
		}

		float f2 = entityIn.getRockingAngle(partialTicks);
		if (!MathHelper.epsilonEquals(f2, 0.0F))
		{
			matrixStackIn.rotate(new Quaternion(new Vector3f(1.0F, 0.0F, 1.0F), entityIn.getRockingAngle(partialTicks), true));
		}

		matrixStackIn.scale(-1.0F, -1.0F, 1.0F);
		matrixStackIn.rotate(Vector3f.YP.rotationDegrees(90.0F));
		this.modelLargeBoat.setRotationAngles(entityIn, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F);
		IVertexBuilder ivertexbuilder = bufferIn.getBuffer(this.modelLargeBoat.getRenderType(this.getEntityTexture(entityIn)));
		this.modelLargeBoat.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		IVertexBuilder ivertexbuilder1 = bufferIn.getBuffer(RenderType.getWaterMask());
		this.modelLargeBoat.func_228245_c_().render(matrixStackIn, ivertexbuilder1, packedLightIn, OverlayTexture.NO_OVERLAY);
		matrixStackIn.pop();
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}

	public ResourceLocation getEntityTexture(LargeBoatEntity entity)
	{
		return LARGE_BOAT_TEXTURES[entity.getModBoatType().ordinal()];
	}

	protected void renderBlockState(LargeBoatEntity entityIn, float partialTicks, BlockState stateIn, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn)
	{
		Minecraft.getInstance().getBlockRendererDispatcher().renderBlock(stateIn, matrixStackIn, bufferIn, packedLightIn, OverlayTexture.NO_OVERLAY);
	}
}