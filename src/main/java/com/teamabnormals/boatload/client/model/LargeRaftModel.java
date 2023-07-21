package com.teamabnormals.boatload.client.model;

import com.google.common.collect.ImmutableList;
import com.teamabnormals.boatload.common.entity.vehicle.LargeBoat;

import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

public class LargeRaftModel extends ListModel<LargeBoat>
{
	private final ModelPart leftPaddle;
	private final ModelPart rightPaddle;
	private final ImmutableList<ModelPart> parts;

	public LargeRaftModel(ModelPart root) {
		this.leftPaddle = root.getChild("left_paddle");
		this.rightPaddle = root.getChild("right_paddle");
		this.parts = ImmutableList.of(root.getChild("bottom"), this.leftPaddle, this.rightPaddle);
	}

	public static LayerDefinition createBodyModel() {
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition root = mesh.getRoot();
		root.addOrReplaceChild("bottom", CubeListBuilder.create().texOffs(0, 0).addBox(-27.0F, -11.0F, -4.0F, 54.0F, 20.0F, 4.0F).texOffs(0, 0).addBox(-27.0F, -9.0F, -8.0F, 54.0F, 16.0F, 4.0F), PartPose.offsetAndRotation(0.0F, -2.0F, 1.0F, ((float) Math.PI / 2F), 0.0F, 0.0F));
		root.addOrReplaceChild("left_paddle", CubeListBuilder.create().texOffs(0, 56).addBox(-1.0F, 0.0F, -5.0F, 2.0F, 2.0F, 18.0F).addBox(-1.001F, -3.0F, 8.0F, 1.0F, 6.0F, 7.0F), PartPose.offsetAndRotation(3.0F, -4.0F, 9.0F, 0.0F, 0.0F, 0.19634955F));
		root.addOrReplaceChild("right_paddle", CubeListBuilder.create().texOffs(0, 76).addBox(-1.0F, 0.0F, -5.0F, 2.0F, 2.0F, 18.0F).addBox(0.001F, -3.0F, 8.0F, 1.0F, 6.0F, 7.0F), PartPose.offsetAndRotation(3.0F, -4.0F, -9.0F, 0.0F, (float) Math.PI, 0.19634955F));
		root.addOrReplaceChild("water_patch", CubeListBuilder.create().texOffs(0, 0).addBox(-27.0F, -11.5F, -3.0F, 54.0F, 21.0F, 3.0F), PartPose.offsetAndRotation(0.0F, -3.0F, 1.0F, ((float) Math.PI / 2F), 0.0F, 0.0F));
		return LayerDefinition.create(mesh, 128, 128);
	}
	
	@Override
	public void setupAnim(LargeBoat boat, float p_102270_, float p_102271_, float p_102272_, float p_102273_, float p_102274_) {
		animatePaddle(boat, 0, this.leftPaddle, p_102270_);
		animatePaddle(boat, 1, this.rightPaddle, p_102270_);
	}
	
	@Override
	public Iterable<ModelPart> parts() {
		return this.parts;
	}

	private static void animatePaddle(LargeBoat boat, int p_170466_, ModelPart p_170467_, float p_170468_) {
		float f = boat.getRowingTime(p_170466_, p_170468_);
		p_170467_.xRot = Mth.clampedLerp((-(float) Math.PI / 3F), -0.2617994F, (Mth.sin(-f) + 1.0F) / 2.0F);
		p_170467_.yRot = Mth.clampedLerp((-(float) Math.PI / 4F), ((float) Math.PI / 4F), (Mth.sin(-f + 1.0F) + 1.0F) / 2.0F);
		if (p_170466_ == 1) {
			p_170467_.yRot = (float) Math.PI - p_170467_.yRot;
		}

	}
}
