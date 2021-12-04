package com.minecraftabnormals.extraboats.client.renderer.entity.model;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import com.minecraftabnormals.extraboats.common.entity.item.boat.LargeBoatEntity;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Arrays;

@OnlyIn(Dist.CLIENT)
public class LargeBoatModel extends SegmentedModel<LargeBoatEntity> {
	private final ModelRenderer[] paddles = new ModelRenderer[2];
	private final ModelRenderer noWater;
	private final ImmutableList<ModelRenderer> parts;

	public LargeBoatModel() {
		ModelRenderer[] amodelrenderer = new ModelRenderer[]{(new ModelRenderer(this, 0, 0)).setTexSize(128, 128), (new ModelRenderer(this, 0, 21)).setTexSize(128, 128), (new ModelRenderer(this, 0, 29)).setTexSize(128, 128), (new ModelRenderer(this, 0, 37)).setTexSize(128, 128), (new ModelRenderer(this, 0, 45)).setTexSize(128, 128)};
		amodelrenderer[0].addBox(-24.0F, -10.0F, -3.0F, 48.0F, 18.0F, 3.0F, 0.0F);
		amodelrenderer[0].setPos(0.0F, 3.0F, 1.0F);
		amodelrenderer[1].addBox(-14.0F, -7.0F, -1.0F, 20.0F, 6.0F, 2.0F, 0.0F);
		amodelrenderer[1].setPos(-25.0F, 4.0F, 4.0F);
		amodelrenderer[2].addBox(-8.0F, -7.0F, -1.0F, 18.0F, 6.0F, 2.0F, 0.0F);
		amodelrenderer[2].setPos(25.0F, 4.0F, 1.0F);
		amodelrenderer[3].addBox(-24.0F, -7.0F, -1.0F, 48.0F, 6.0F, 2.0F, 0.0F);
		amodelrenderer[3].setPos(0.0F, 4.0F, -10.0F);
		amodelrenderer[4].addBox(-24.0F, -7.0F, -1.0F, 48.0F, 6.0F, 2.0F, 0.0F);
		amodelrenderer[4].setPos(0.0F, 4.0F, 10.0F);
		amodelrenderer[0].xRot = ((float) Math.PI / 2F);
		amodelrenderer[1].yRot = ((float) Math.PI * 1.5F);
		amodelrenderer[2].yRot = ((float) Math.PI / 2F);
		amodelrenderer[3].yRot = (float) Math.PI;
		this.paddles[0] = this.makePaddle(true);
		this.paddles[0].setPos(3.0F, -5.0F, 10.0F);
		this.paddles[1] = this.makePaddle(false);
		this.paddles[1].setPos(3.0F, -5.0F, -10.0F);
		this.paddles[1].yRot = (float) Math.PI;
		this.paddles[0].zRot = 0.19634955F;
		this.paddles[1].zRot = 0.19634955F;
		this.noWater = (new ModelRenderer(this, 0, 0)).setTexSize(128, 128);
		this.noWater.addBox(-24.0F, -10.0F, -3.0F, 48.0F, 18.0F, 3.0F, 0.0F);
		this.noWater.setPos(0.0F, -3.0F, 1.0F);
		this.noWater.xRot = ((float) Math.PI / 2F);
		Builder<ModelRenderer> builder = ImmutableList.builder();
		builder.addAll(Arrays.asList(amodelrenderer));
		builder.addAll(Arrays.asList(this.paddles));
		this.parts = builder.build();
	}

	public void setupAnim(LargeBoatEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.animatePaddle(entityIn, 0, limbSwing);
		this.animatePaddle(entityIn, 1, limbSwing);
	}

	public ImmutableList<ModelRenderer> parts() {
		return this.parts;
	}

	public ModelRenderer waterPatch() {
		return this.noWater;
	}

	protected ModelRenderer makePaddle(boolean p_187056_1_) {
		ModelRenderer modelrenderer = (new ModelRenderer(this, 0, p_187056_1_ ? 53 : 73)).setTexSize(128, 128);
		modelrenderer.addBox(-1.0F, 0.0F, -5.0F, 2.0F, 2.0F, 18.0F);
		modelrenderer.addBox(p_187056_1_ ? -1.001F : 0.001F, -3.0F, 8.0F, 1.0F, 6.0F, 7.0F);
		return modelrenderer;
	}

	protected void animatePaddle(LargeBoatEntity p_228244_1_, int p_228244_2_, float p_228244_3_) {
		float f = p_228244_1_.getRowingTime(p_228244_2_, p_228244_3_);
		ModelRenderer modelrenderer = this.paddles[p_228244_2_];
		modelrenderer.xRot = (float) MathHelper.clampedLerp(-(float) Math.PI / 3F, -0.2617994F, (MathHelper.sin(-f) + 1.0F) / 2.0F);
		modelrenderer.yRot = (float) MathHelper.clampedLerp(-(float) Math.PI / 4F, (float) Math.PI / 4F, (MathHelper.sin(-f + 1.0F) + 1.0F) / 2.0F);
		if (p_228244_2_ == 1) {
			modelrenderer.yRot = (float) Math.PI - modelrenderer.yRot;
		}

	}
}