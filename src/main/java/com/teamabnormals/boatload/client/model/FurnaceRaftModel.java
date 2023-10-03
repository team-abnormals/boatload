package com.teamabnormals.boatload.client.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.RaftModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FurnaceRaftModel extends RaftModel {

	public FurnaceRaftModel(ModelPart modelPart) {
		super(modelPart);
	}

	protected ImmutableList.Builder<ModelPart> createPartsBuilder(ModelPart modelPart) {
		ImmutableList.Builder<ModelPart> builder = super.createPartsBuilder(modelPart);
		builder.add(modelPart.getChild("furnace"));
		return builder;
	}

	public static LayerDefinition createBodyModel() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		RaftModel.createChildren(partdefinition);
		partdefinition.addOrReplaceChild("furnace", CubeListBuilder.create().texOffs(0, 59).addBox(0.0F, 0.0F, 0.0F, 12.0F, 12.0F, 12.0F), PartPose.offsetAndRotation(-2.0F, -14.0F, -6.0F, 0.0F, (-(float) Math.PI / 2F), 0.0F));
		return LayerDefinition.create(meshdefinition, 128, 128);
	}
}