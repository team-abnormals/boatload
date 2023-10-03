package com.teamabnormals.boatload.client.model;// Made with Blockbench 4.8.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


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

public class WideRaftModel extends ListModel<LargeBoat> {
	private final ModelPart leftPaddle;
	private final ModelPart rightPaddle;
	private final ImmutableList<ModelPart> parts;

	public WideRaftModel(ModelPart root) {
		this.leftPaddle = root.getChild("left_paddle");
		this.rightPaddle = root.getChild("right_paddle");
		this.parts = this.createPartsBuilder(root).build();
	}

	protected ImmutableList.Builder<ModelPart> createPartsBuilder(ModelPart modelPart) {
		ImmutableList.Builder<ModelPart> builder = new ImmutableList.Builder<>();
		builder.add(modelPart.getChild("bottom"), this.leftPaddle, this.rightPaddle);
		return builder;
	}

	public static void createChildren(PartDefinition partdefinition) {
		partdefinition.addOrReplaceChild("bottom", CubeListBuilder.create().texOffs(0, 0).addBox(-20.0F, -21.0F, -4.0F, 40.0F, 40.0F, 4.0F).texOffs(0, 0).addBox(-20.0F, -19.0F, -8.0F, 40.0F, 36.0F, 4.0F), PartPose.offsetAndRotation(0.0F, -2.0F, 1.0F, 1.5708F, 0.0F, 0.0F));
		partdefinition.addOrReplaceChild("left_paddle", CubeListBuilder.create().texOffs(0, 44).addBox(-1.0F, 0.0F, -5.0F, 2.0F, 2.0F, 18.0F).addBox(-1.001F, -3.0F, 8.0F, 1.0F, 6.0F, 7.0F), PartPose.offsetAndRotation(3.0F, -4.0F, 19.0F, 0.0F, 0.0F, 0.19634955F));
		partdefinition.addOrReplaceChild("right_paddle", CubeListBuilder.create().texOffs(40, 44).addBox(-1.0F, 0.0F, -5.0F, 2.0F, 2.0F, 18.0F).addBox(0.001F, -3.0F, 8.0F, 1.0F, 6.0F, 7.0F), PartPose.offsetAndRotation(3.0F, -4.0F, -19.0F, 0.0F, (float) Math.PI, 0.19634955F));
	}

	public static LayerDefinition createBodyModel() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		createChildren(partdefinition);
		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	public void setupAnim(LargeBoat p_249733_, float p_249202_, float p_252219_, float p_249366_, float p_249759_, float p_250286_) {
		animatePaddle(p_249733_, 0, this.leftPaddle, p_249202_);
		animatePaddle(p_249733_, 1, this.rightPaddle, p_249202_);
	}

	public ImmutableList<ModelPart> parts() {
		return this.parts;
	}

	private static void animatePaddle(LargeBoat boat, int p_249947_, ModelPart p_248943_, float p_251990_) {
		float f = boat.getRowingTime(p_249947_, p_251990_);
		p_248943_.xRot = Mth.clampedLerp((-(float) Math.PI / 3F), -0.2617994F, (Mth.sin(-f) + 1.0F) / 2.0F);
		p_248943_.yRot = Mth.clampedLerp((-(float) Math.PI / 4F), ((float) Math.PI / 4F), (Mth.sin(-f + 1.0F) + 1.0F) / 2.0F);
		if (p_249947_ == 1) {
			p_248943_.yRot = (float) Math.PI - p_248943_.yRot;
		}
	}
}