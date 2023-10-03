package com.teamabnormals.boatload.core.other;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.teamabnormals.blueprint.common.entity.BlueprintBoat;
import com.teamabnormals.blueprint.common.entity.BlueprintChestBoat;
import com.teamabnormals.blueprint.common.world.storage.tracking.IDataManager;
import com.teamabnormals.blueprint.core.registry.BlueprintBoatTypes.BlueprintBoatType;
import com.teamabnormals.boatload.common.entity.vehicle.BoatloadBoat;
import com.teamabnormals.boatload.common.entity.vehicle.LargeBoat;
import com.teamabnormals.boatload.common.item.FurnaceBoatItem;
import com.teamabnormals.boatload.common.item.LargeBoatItem;
import com.teamabnormals.boatload.core.Boatload;
import com.teamabnormals.boatload.core.api.BoatloadBoatType;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.BannerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.joml.Quaternionf;

import java.util.List;
import java.util.stream.Collectors;

public class BoatloadUtil {

	public static List<Item> getItems() {
		List<Item> items = Lists.newArrayList();
		Boatload.REGISTRY_HELPER.getItemSubHelper().getDeferredRegister().getEntries().forEach(registryObject -> items.add(registryObject.get()));
		return items;
	}

	public static List<FurnaceBoatItem> getFurnaceBoats() {
		return (List<FurnaceBoatItem>) (List<?>) getItems().stream().filter(item -> item instanceof FurnaceBoatItem).collect(Collectors.toList());
	}

	public static List<LargeBoatItem> getLargeBoats() {
		return (List<LargeBoatItem>) (List<?>) getItems().stream().filter(item -> item instanceof LargeBoatItem).collect(Collectors.toList());
	}

	public static boolean isNetherBoat(Entity entity) {
		if (entity instanceof BoatloadBoat boat) {
			return boat.getBoatloadBoatType().fireproof();
		}

		BlueprintBoatType typeData = null;
		if (entity instanceof BlueprintBoat boat) {
			typeData = boat.getBoatType();
		} else if (entity instanceof BlueprintChestBoat boat) {
			typeData = boat.getBoatType();
		}

		return typeData != null && BoatloadBoatType.getType(typeData.getName()).fireproof();
	}

	public static void renderBanner(Boat boat, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLightIn) {
		poseStack.pushPose();
		poseStack.translate(0.0D, 0.375D, 0.0D);
		poseStack.mulPose(Axis.YP.rotationDegrees(180.0F - entityYaw));
		float f = (float) boat.getHurtTime() - partialTicks;
		float f1 = boat.getDamage() - partialTicks;
		if (f1 < 0.0F) {
			f1 = 0.0F;
		}

		if (f > 0.0F) {
			poseStack.mulPose(Axis.XP.rotationDegrees(Mth.sin(f) * f * f1 / 10.0F * (float) boat.getHurtDir()));
		}

		float f2 = boat.getBubbleAngle(partialTicks);
		if (!Mth.equal(f2, 0.0F)) {
			poseStack.mulPose((new Quaternionf()).setAngleAxis(boat.getBubbleAngle(partialTicks) * ((float) Math.PI / 180F), 1.0F, 0.0F, 1.0F));
		}

		ItemStack banner = ((IDataManager) boat).getValue(BoatloadTrackedData.BANNER);
		if (banner != null && banner.getItem() instanceof BannerItem) {
			Level level = boat.getCommandSenderWorld();
			int i;
			if (level != null) {
				i = LevelRenderer.getLightColor(level, boat.blockPosition());
			} else {
				i = 15728880;
			}

			boolean raft = boat.getVariant() == Boat.Type.BAMBOO || boat instanceof BoatloadBoat boatloadBoat && boatloadBoat.getBoatloadBoatType().raft() || boat instanceof BlueprintBoat blueprintBoat && blueprintBoat.getBoatType().isRaft();
			float f3 = boat instanceof LargeBoat ? (raft ? 27.0F : 36.0F) : (raft ? 21.0F : 23.0F);
			poseStack.pushPose();
			poseStack.translate(0.5D, (raft ? 2.0F : 3.0F) / 16.0F, f3 / 16.0F);
			poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
			IClientItemExtensions.of(banner).getCustomRenderer().renderByItem(banner, ItemDisplayContext.GROUND, poseStack, buffer, i, OverlayTexture.NO_OVERLAY);
			poseStack.popPose();
		}
		poseStack.popPose();
	}
}
