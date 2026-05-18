package com.teamabnormals.boatload.core.mixin.client;

import com.google.common.collect.ImmutableList.Builder;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.teamabnormals.boatload.core.other.BoatloadUtil;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.ChestRaftModel;
import net.minecraft.client.model.geom.ModelPart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = {ChestBoatModel.class, ChestRaftModel.class})
public abstract class ChestBoatModelMixin {

	@WrapWithCondition(method = "createPartsBuilder", at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableList$Builder;add(Ljava/lang/Object;)Lcom/google/common/collect/ImmutableList$Builder;"))
	private boolean skipChestParts(Builder<ModelPart> builder, Object part) {
		return BoatloadUtil.shouldShowOriginalChest();
	}
}
