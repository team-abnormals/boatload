package com.markus1002.extraboats.client.renderer.entity;

import com.markus1002.extraboats.common.entity.item.boat.ModBoatEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.BoatModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModBoatRenderer<T extends ModBoatEntity> extends EntityRenderer<T>
{
	private static final ResourceLocation[] BOAT_TEXTURES = new ResourceLocation[]{
			new ResourceLocation("textures/entity/boat/oak.png"),
			new ResourceLocation("textures/entity/boat/spruce.png"),
			new ResourceLocation("textures/entity/boat/birch.png"),
			new ResourceLocation("textures/entity/boat/jungle.png"),
			new ResourceLocation("textures/entity/boat/acacia.png"),
			new ResourceLocation("textures/entity/boat/dark_oak.png"),
			new ResourceLocation("biomesoplenty", "textures/entity/boat/cherry.png"),
			new ResourceLocation("biomesoplenty", "textures/entity/boat/dead.png"),
			new ResourceLocation("biomesoplenty", "textures/entity/boat/ethereal.png"),
			new ResourceLocation("biomesoplenty", "textures/entity/boat/fir.png"),
			new ResourceLocation("biomesoplenty", "textures/entity/boat/hellbark.png"),
			new ResourceLocation("biomesoplenty", "textures/entity/boat/jacaranda.png"),
			new ResourceLocation("biomesoplenty", "textures/entity/boat/magic.png"),
			new ResourceLocation("biomesoplenty", "textures/entity/boat/mahogany.png"),
			new ResourceLocation("biomesoplenty", "textures/entity/boat/palm.png"),
			new ResourceLocation("biomesoplenty", "textures/entity/boat/redwood.png"),
			new ResourceLocation("biomesoplenty", "textures/entity/boat/umbran.png"),
			new ResourceLocation("biomesoplenty", "textures/entity/boat/willow.png"),
			new ResourceLocation("upgrade_aquatic", "textures/entity/boat/driftwood.png"),
			new ResourceLocation("upgrade_aquatic", "textures/entity/boat/river.png"),
			new ResourceLocation("bambooblocks", "textures/entity/boat/bamboo.png"),
			new ResourceLocation("endergetic", "textures/entity/boat/poise.png"),
			new ResourceLocation("bloomful", "textures/entity/boat/wisteria.png"),
			new ResourceLocation("swampexpansion", "textures/entity/boat/willow.png"),
			new ResourceLocation("atmospheric", "textures/entity/boat/rosewood.png"),
			new ResourceLocation("atmospheric", "textures/entity/boat/aspen.png"),
			new ResourceLocation("atmospheric", "textures/entity/boat/kousa.png"),
			new ResourceLocation("atmospheric", "textures/entity/boat/yucca.png"),
			new ResourceLocation("atmospheric", "textures/entity/boat/grimwood.png"),
			new ResourceLocation("autumnity", "textures/entity/boat/maple.png"),
			new ResourceLocation("buzzierbees", "textures/entity/boat/hive.png")};
	protected final BoatModel modelBoat = new BoatModel();

	public ModBoatRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn);
		this.shadowSize = 0.8F;
	}

	public void render(T entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn)
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

		BlockState blockstate = entityIn.getDisplayTile();
		if (blockstate.getRenderType() != BlockRenderType.INVISIBLE)
		{
			matrixStackIn.push();
			matrixStackIn.scale(0.75F, 0.75F, 0.75F);
			matrixStackIn.translate(0.5D, (double)(-3.0F / 16.0F), 1.1D);
			matrixStackIn.rotate(Vector3f.YP.rotationDegrees(180.0F));
			this.renderBlockState(entityIn, partialTicks, blockstate, matrixStackIn, bufferIn, packedLightIn);
			matrixStackIn.pop();
		}

		matrixStackIn.scale(-1.0F, -1.0F, 1.0F);
		matrixStackIn.rotate(Vector3f.YP.rotationDegrees(90.0F));
		this.modelBoat.setRotationAngles(entityIn, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F);
		IVertexBuilder ivertexbuilder = bufferIn.getBuffer(this.modelBoat.getRenderType(this.getEntityTexture(entityIn)));
		this.modelBoat.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		IVertexBuilder ivertexbuilder1 = bufferIn.getBuffer(RenderType.getWaterMask());
		this.modelBoat.func_228245_c_().render(matrixStackIn, ivertexbuilder1, packedLightIn, OverlayTexture.NO_OVERLAY);
		matrixStackIn.pop();
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}

	public ResourceLocation getEntityTexture(T entity)
	{
		return BOAT_TEXTURES[entity.getModBoatType().ordinal()];
	}

	protected void renderBlockState(T entityIn, float partialTicks, BlockState stateIn, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn)
	{
		Minecraft.getInstance().getBlockRendererDispatcher().renderBlock(stateIn, matrixStackIn, bufferIn, packedLightIn, OverlayTexture.NO_OVERLAY);
	}
}
