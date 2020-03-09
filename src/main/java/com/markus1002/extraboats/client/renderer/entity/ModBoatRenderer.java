package com.markus1002.extraboats.client.renderer.entity;

import com.markus1002.extraboats.entity.item.boat.ModBoatEntity;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.BoatModel;
import net.minecraft.client.renderer.texture.AtlasTexture;
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
			new ResourceLocation("upgrade_aquatic", "textures/entity/boat/driftwood_boat.png"),
			new ResourceLocation("bambooblocks", "textures/entity/boat/bamboo_boat.png"),
			new ResourceLocation("endergetic", "textures/entity/boat/poise_boat.png"),
			new ResourceLocation("bloomful", "textures/entity/boat/wisteria_boat.png"),
			new ResourceLocation("swampexpansion", "textures/entity/boat/willow_boat.png"),
			new ResourceLocation("atmospheric", "textures/entity/boat/rosewood_boat.png"),
			new ResourceLocation("atmospheric", "textures/entity/boat/aspen_boat.png"),
			new ResourceLocation("atmospheric", "textures/entity/boat/kousa_boat.png"),
			new ResourceLocation("atmospheric", "textures/entity/boat/yucca_boat.png"),
			new ResourceLocation("autumnity", "textures/entity/boat/maple.png")};
	protected final BoatModel field_76998_a = new BoatModel();

	public ModBoatRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn);
		this.shadowSize = 0.8F;
	}

	public void doRender(T entity, double x, double y, double z, float entityYaw, float partialTicks)
	{
		GlStateManager.pushMatrix();
		this.setupTranslation(x, y, z);
		this.setupRotation(entity, entityYaw, partialTicks);
		this.bindEntityTexture(entity);

		BlockState blockstate = entity.getDisplayTile();
		if (blockstate.getRenderType() != BlockRenderType.INVISIBLE)
		{
			GlStateManager.pushMatrix();
			this.bindTexture(AtlasTexture.LOCATION_BLOCKS_TEXTURE);
			GlStateManager.scalef(-0.75F, -0.75F, 0.75F);
			GlStateManager.rotatef(90.0F, 0.0F, 1.0F, 0.0F);
			GlStateManager.translatef(-1.1F, -3.0F / 16.0F, 0.5F);
			this.renderBoatContents(entity, partialTicks, blockstate);
			GlStateManager.popMatrix();
			GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
			this.bindEntityTexture(entity);
		}

		if (this.renderOutlines)
		{
			GlStateManager.enableColorMaterial();
			GlStateManager.setupSolidRenderingTextureCombine(this.getTeamColor(entity));
		}

		this.field_76998_a.render(entity, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		if (this.renderOutlines)
		{
			GlStateManager.tearDownSolidRenderingTextureCombine();
			GlStateManager.disableColorMaterial();
		}

		GlStateManager.popMatrix();
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	public void setupRotation(T entityIn, float entityYaw, float partialTicks)
	{
		GlStateManager.rotatef(180.0F - entityYaw, 0.0F, 1.0F, 0.0F);
		float f = (float)entityIn.getTimeSinceHit() - partialTicks;
		float f1 = entityIn.getDamageTaken() - partialTicks;
		if (f1 < 0.0F)
		{
			f1 = 0.0F;
		}

		if (f > 0.0F)
		{
			GlStateManager.rotatef(MathHelper.sin(f) * f * f1 / 10.0F * (float)entityIn.getForwardDirection(), 1.0F, 0.0F, 0.0F);
		}

		float f2 = entityIn.getRockingAngle(partialTicks);
		if (!MathHelper.epsilonEquals(f2, 0.0F))
		{
			GlStateManager.rotatef(entityIn.getRockingAngle(partialTicks), 1.0F, 0.0F, 1.0F);
		}

		GlStateManager.scalef(-1.0F, -1.0F, 1.0F);
	}

	protected void renderBoatContents(T boat, float partialTicks, BlockState contents)
	{
		GlStateManager.pushMatrix();
		Minecraft.getInstance().getBlockRendererDispatcher().renderBlockBrightness(contents, boat.getBrightness());
		GlStateManager.popMatrix();
	}

	public void setupTranslation(double x, double y, double z)
	{
		GlStateManager.translatef((float)x, (float)y + 0.375F, (float)z);
	}

	protected ResourceLocation getEntityTexture(T entity)
	{
		return BOAT_TEXTURES[entity.getModBoatType().ordinal()];
	}

	public boolean isMultipass()
	{
		return true;
	}

	public void renderMultipass(T entityIn, double x, double y, double z, float entityYaw, float partialTicks)
	{
		GlStateManager.pushMatrix();
		this.setupTranslation(x, y, z);
		this.setupRotation(entityIn, entityYaw, partialTicks);
		this.bindEntityTexture(entityIn);
		this.field_76998_a.renderMultipass(entityIn, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GlStateManager.popMatrix();
	}
}