package com.markus1002.extraboats.client.renderer.entity;

import com.markus1002.extraboats.Reference;
import com.markus1002.extraboats.client.renderer.entity.model.LargeBoatModel;
import com.markus1002.extraboats.entity.item.boat.LargeBoatEntity;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
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
			Reference.location("textures/entity/large_boat/bamboo.png"),
			Reference.location("textures/entity/large_boat/poise.png"),
			Reference.location("textures/entity/large_boat/wisteria.png"),
			Reference.location("textures/entity/large_boat/se_willow.png"),
			Reference.location("textures/entity/large_boat/rosewood.png"),
			Reference.location("textures/entity/large_boat/aspen.png"),
			Reference.location("textures/entity/large_boat/kousa.png"),
			Reference.location("textures/entity/large_boat/yucca.png"),
			Reference.location("textures/entity/large_boat/maple.png")};
	protected final LargeBoatModel largeBoatModel = new LargeBoatModel();

	public LargeBoatRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn);
		this.shadowSize = 0.8F;
	}

	public void doRender(LargeBoatEntity entity, double x, double y, double z, float entityYaw, float partialTicks)
	{
		GlStateManager.pushMatrix();
		this.setupTranslation(x, y, z);
		this.setupRotation(entity, entityYaw, partialTicks);
		this.bindEntityTexture(entity);
		if (this.renderOutlines)
		{
			GlStateManager.enableColorMaterial();
			GlStateManager.setupSolidRenderingTextureCombine(this.getTeamColor(entity));
		}

		this.largeBoatModel.render(entity, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		if (this.renderOutlines)
		{
			GlStateManager.tearDownSolidRenderingTextureCombine();
			GlStateManager.disableColorMaterial();
		}

		GlStateManager.popMatrix();
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	public void setupRotation(LargeBoatEntity entityIn, float entityYaw, float partialTicks)
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

	public void setupTranslation(double x, double y, double z)
	{
		GlStateManager.translatef((float)x, (float)y + 0.375F, (float)z);
	}

	protected ResourceLocation getEntityTexture(LargeBoatEntity entity)
	{
		return LARGE_BOAT_TEXTURES[entity.getModBoatType().ordinal()];
	}

	public boolean isMultipass()
	{
		return true;
	}

	public void renderMultipass(LargeBoatEntity entityIn, double x, double y, double z, float entityYaw, float partialTicks)
	{
		GlStateManager.pushMatrix();
		this.setupTranslation(x, y, z);
		this.setupRotation(entityIn, entityYaw, partialTicks);
		this.bindEntityTexture(entityIn);
		this.largeBoatModel.renderMultipass(entityIn, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GlStateManager.popMatrix();
	}
}