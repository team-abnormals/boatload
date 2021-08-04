package com.minecraftabnormals.extraboats.client.renderer.entity;

import com.minecraftabnormals.extraboats.common.entity.item.boat.ExtraBoatsBoatEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.BoatModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModBoatRenderer<T extends ExtraBoatsBoatEntity> extends EntityRenderer<T> {
	private static final ResourceLocation[] BOAT_TEXTURES = new ResourceLocation[]{
			new ResourceLocation("textures/entity/boat/oak.png"),
			new ResourceLocation("textures/entity/boat/spruce.png"),
			new ResourceLocation("textures/entity/boat/birch.png"),
			new ResourceLocation("textures/entity/boat/jungle.png"),
			new ResourceLocation("textures/entity/boat/acacia.png"),
			new ResourceLocation("textures/entity/boat/dark_oak.png"),

			new ResourceLocation("biomesoplenty", "textures/entity/boat/cherry.png"),
			new ResourceLocation("biomesoplenty", "textures/entity/boat/dead.png"),
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

			new ResourceLocation("bamboo_blocks", "textures/entity/boat/bamboo.png"),

			new ResourceLocation("endergetic", "textures/entity/boat/poise.png"),

			new ResourceLocation("environmental", "textures/entity/boat/wisteria.png"),
			new ResourceLocation("environmental", "textures/entity/boat/willow.png"),
			new ResourceLocation("environmental", "textures/entity/boat/cherry.png"),

			new ResourceLocation("atmospheric", "textures/entity/boat/rosewood.png"),
			new ResourceLocation("atmospheric", "textures/entity/boat/morado.png"),
			new ResourceLocation("atmospheric", "textures/entity/boat/aspen.png"),
			new ResourceLocation("atmospheric", "textures/entity/boat/kousa.png"),
			new ResourceLocation("atmospheric", "textures/entity/boat/yucca.png"),
			new ResourceLocation("atmospheric", "textures/entity/boat/grimwood.png"),

			new ResourceLocation("autumnity", "textures/entity/boat/maple.png"),

			new ResourceLocation("enhanced_mushrooms", "textures/entity/boat/red_mushroom.png"),
			new ResourceLocation("enhanced_mushrooms", "textures/entity/boat/brown_mushroom.png"),
			new ResourceLocation("enhanced_mushrooms", "textures/entity/boat/glowshroom.png"),

			new ResourceLocation("hanami", "textures/entity/boat/sakura.png"),

			new ResourceLocation("nether_extension", "textures/entity/boat/crimson.png"),
			new ResourceLocation("nether_extension", "textures/entity/boat/warped.png")};

	protected final BoatModel modelBoat = new BoatModel();

	public ModBoatRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn);
		this.shadowRadius = 0.8F;
	}

	public void render(T entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
		matrixStackIn.pushPose();
		matrixStackIn.translate(0.0D, 0.375D, 0.0D);
		matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(180.0F - entityYaw));
		float f = (float) entityIn.getHurtTime() - partialTicks;
		float f1 = entityIn.getDamage() - partialTicks;
		if (f1 < 0.0F) {
			f1 = 0.0F;
		}

		if (f > 0.0F) {
			matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(MathHelper.sin(f) * f * f1 / 10.0F * (float) entityIn.getHurtDir()));
		}

		float f2 = entityIn.getBubbleAngle(partialTicks);
		if (!MathHelper.equal(f2, 0.0F)) {
			matrixStackIn.mulPose(new Quaternion(new Vector3f(1.0F, 0.0F, 1.0F), entityIn.getBubbleAngle(partialTicks), true));
		}

		BlockState blockstate = entityIn.getDisplayTile();
		if (blockstate.getRenderShape() != BlockRenderType.INVISIBLE) {
			matrixStackIn.pushPose();
			matrixStackIn.scale(0.75F, 0.75F, 0.75F);
			matrixStackIn.translate(0.5D, (double) (-3.0F / 16.0F), 1.1D);
			matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(180.0F));
			this.renderBlockState(entityIn, partialTicks, blockstate, matrixStackIn, bufferIn, packedLightIn);
			matrixStackIn.popPose();
		}

		matrixStackIn.scale(-1.0F, -1.0F, 1.0F);
		matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(90.0F));
		this.modelBoat.setupAnim(entityIn, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F);
		IVertexBuilder ivertexbuilder = bufferIn.getBuffer(this.modelBoat.renderType(this.getTextureLocation(entityIn)));
		this.modelBoat.renderToBuffer(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		if (!entityIn.isUnderWater()) {
			IVertexBuilder ivertexbuilder1 = bufferIn.getBuffer(RenderType.waterMask());
			this.modelBoat.waterPatch().render(matrixStackIn, ivertexbuilder1, packedLightIn, OverlayTexture.NO_OVERLAY);
		}
		matrixStackIn.popPose();
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}

	public ResourceLocation getTextureLocation(T entity) {
		return BOAT_TEXTURES[entity.getModBoatType().ordinal()];
	}

	protected void renderBlockState(T entityIn, float partialTicks, BlockState stateIn, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
		Minecraft.getInstance().getBlockRenderer().renderSingleBlock(stateIn, matrixStackIn, bufferIn, packedLightIn, OverlayTexture.NO_OVERLAY);
	}
}
