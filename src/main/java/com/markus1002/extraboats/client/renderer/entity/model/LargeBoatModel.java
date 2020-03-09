package com.markus1002.extraboats.client.renderer.entity.model;

import com.markus1002.extraboats.entity.item.boat.LargeBoatEntity;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LargeBoatModel extends EntityModel<LargeBoatEntity>
{
   private final RendererModel[] field_78103_a = new RendererModel[5];
   private final RendererModel[] paddles = new RendererModel[2];
   private final RendererModel noWater;

   public LargeBoatModel()
   {
      this.field_78103_a[0] = (new RendererModel(this, 0, 0)).setTextureSize(128, 128);
      this.field_78103_a[1] = (new RendererModel(this, 0, 21)).setTextureSize(128, 128);
      this.field_78103_a[2] = (new RendererModel(this, 0, 29)).setTextureSize(128, 128);
      this.field_78103_a[3] = (new RendererModel(this, 0, 37)).setTextureSize(128, 128);
      this.field_78103_a[4] = (new RendererModel(this, 0, 45)).setTextureSize(128, 128);
      this.field_78103_a[0].addBox(-24.0F, -10.0F, -3.0F, 48, 18, 3, 0.0F);
      this.field_78103_a[0].setRotationPoint(0.0F, 3.0F, 1.0F);
      this.field_78103_a[1].addBox(-14.0F, -7.0F, -1.0F, 20, 6, 2, 0.0F);
      this.field_78103_a[1].setRotationPoint(-25.0F, 4.0F, 4.0F);
      this.field_78103_a[2].addBox(-8.0F, -7.0F, -1.0F, 18, 6, 2, 0.0F);
      this.field_78103_a[2].setRotationPoint(25.0F, 4.0F, 1.0F);
      this.field_78103_a[3].addBox(-24.0F, -7.0F, -1.0F, 48, 6, 2, 0.0F);
      this.field_78103_a[3].setRotationPoint(0.0F, 4.0F, -10.0F);
      this.field_78103_a[4].addBox(-24.0F, -7.0F, -1.0F, 48, 6, 2, 0.0F);
      this.field_78103_a[4].setRotationPoint(0.0F, 4.0F, 10.0F);
      this.field_78103_a[0].rotateAngleX = ((float)Math.PI / 2F);
      this.field_78103_a[1].rotateAngleY = ((float)Math.PI * 1.5F);
      this.field_78103_a[2].rotateAngleY = ((float)Math.PI / 2F);
      this.field_78103_a[3].rotateAngleY = (float)Math.PI;
      this.paddles[0] = this.makePaddle(true);
      this.paddles[0].setRotationPoint(3.0F, -5.0F, 10.0F);
      this.paddles[1] = this.makePaddle(false);
      this.paddles[1].setRotationPoint(3.0F, -5.0F, -10.0F);
      this.paddles[1].rotateAngleY = (float)Math.PI;
      this.paddles[0].rotateAngleZ = 0.19634955F;
      this.paddles[1].rotateAngleZ = 0.19634955F;
      this.noWater = (new RendererModel(this, 0, 0)).setTextureSize(128, 128);
      this.noWater.addBox(-24.0F, -10.0F, -3.0F, 48, 18, 3, 0.0F);
      this.noWater.setRotationPoint(0.0F, -3.0F, 1.0F);
      this.noWater.rotateAngleX = ((float)Math.PI / 2F);
   }

   public void render(LargeBoatEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
   {
      GlStateManager.rotatef(90.0F, 0.0F, 1.0F, 0.0F);
      this.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);

      for(int i = 0; i < 5; ++i)
      {
         this.field_78103_a[i].render(scale);
      }

      this.renderPaddle(entityIn, 0, scale, limbSwing);
      this.renderPaddle(entityIn, 1, scale, limbSwing);
   }

   public void renderMultipass(Entity entityIn, float partialTicks, float p_187054_3_, float p_187054_4_, float p_187054_5_, float p_187054_6_, float scale)
   {
      GlStateManager.rotatef(90.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.colorMask(false, false, false, false);
      this.noWater.render(scale);
      GlStateManager.colorMask(true, true, true, true);
   }

   protected RendererModel makePaddle(boolean p_187056_1_)
   {
      RendererModel renderermodel = (new RendererModel(this, 0, p_187056_1_ ? 53 : 73)).setTextureSize(128, 128);
      renderermodel.addBox(-1.0F, 0.0F, -5.0F, 2, 2, 18);
      renderermodel.addBox(p_187056_1_ ? -1.001F : 0.001F, -3.0F, 8.0F, 1, 6, 7);
      return renderermodel;
   }

   protected void renderPaddle(LargeBoatEntity boat, int paddle, float scale, float limbSwing)
   {
      float f = boat.getRowingTime(paddle, limbSwing);
      RendererModel renderermodel = this.paddles[paddle];
      renderermodel.rotateAngleX = (float)MathHelper.clampedLerp((double)(-(float)Math.PI / 3F), (double)-0.2617994F, (double)((MathHelper.sin(-f) + 1.0F) / 2.0F));
      renderermodel.rotateAngleY = (float)MathHelper.clampedLerp((double)(-(float)Math.PI / 4F), (double)((float)Math.PI / 4F), (double)((MathHelper.sin(-f + 1.0F) + 1.0F) / 2.0F));
      if (paddle == 1)
      {
         renderermodel.rotateAngleY = (float)Math.PI - renderermodel.rotateAngleY;
      }

      renderermodel.render(scale);
   }
}