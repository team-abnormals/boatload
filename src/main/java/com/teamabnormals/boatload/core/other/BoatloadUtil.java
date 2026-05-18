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
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.BannerItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BannerBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BannerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
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

	public static boolean shouldShowOriginalChest() {
		return false;
	}

	public static void renderChest(Boat boat, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
		ItemStack stack = ((IDataManager) boat).getValue(BoatloadTrackedData.CHEST);
		if (stack.getItem() instanceof BlockItem block) {
			BlockState state = block.getBlock().defaultBlockState();
			if (state.getRenderShape() != RenderShape.INVISIBLE) {
				poseStack.pushPose();
				float f = 12.0F / 14.0F;
				poseStack.scale(f, f, f);
				poseStack.translate(0.5D, (boat.getVariant().isRaft() ? 2.45D : -3.5D) / 16.0F, 1.0D + f * 0.1D - 0.00238D);
				poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
				Minecraft.getInstance().getBlockRenderer().renderSingleBlock(state, poseStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
				poseStack.popPose();
			}
		}
	}

	public static void renderBanner(Boat boat, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
		poseStack.pushPose();
		poseStack.translate(0.0D, 0.375D, 0.0D);
		poseStack.mulPose(Axis.YP.rotationDegrees(180.0F - entityYaw));
		float f = (float) boat.getHurtTime() - partialTick;
		float f1 = boat.getDamage() - partialTick;
		if (f1 < 0.0F) {
			f1 = 0.0F;
		}

		if (f > 0.0F) {
			poseStack.mulPose(Axis.XP.rotationDegrees(Mth.sin(f) * f * f1 / 10.0F * (float) boat.getHurtDir()));
		}

		float f2 = boat.getBubbleAngle(partialTick);
		if (!Mth.equal(f2, 0.0F)) {
			poseStack.mulPose((new Quaternionf()).setAngleAxis(boat.getBubbleAngle(partialTick) * ((float) Math.PI / 180F), 1.0F, 0.0F, 1.0F));
		}

		ItemStack banner = ((IDataManager) boat).getValue(BoatloadTrackedData.BANNER);
		if (banner != null && !banner.isEmpty() && banner.getItem() instanceof BannerItem) {
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

			BannerBlockEntity blockEntity = new BannerBlockEntity(BlockPos.ZERO, BannerBlock.byItem(banner.getItem()).defaultBlockState());
			blockEntity.setLevel(level);
			blockEntity.fromItem(banner, blockEntity.getBaseColor());
			BlockEntityRenderDispatcher dispatcher = Minecraft.getInstance().getBlockEntityRenderDispatcher();
			BlockEntityRenderer<BannerBlockEntity> renderer = dispatcher.getRenderer(blockEntity);
			if (renderer != null) {
				renderer.render(blockEntity, partialTick, poseStack, buffer, i, OverlayTexture.NO_OVERLAY);
			}

			poseStack.popPose();
		}
		poseStack.popPose();
	}

	public static void renderBanner(AbstractMinecart minecart, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
		poseStack.pushPose();
		long i = (long) minecart.getId() * 493286711L;
		i = i * i * 4392167121L + i * 98761L;
		float f = (((float) (i >> 16 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
		float f1 = (((float) (i >> 20 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
		float f2 = (((float) (i >> 24 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
		poseStack.translate(f, f1, f2);
		double d0 = Mth.lerp(partialTick, minecart.xOld, minecart.getX());
		double d1 = Mth.lerp(partialTick, minecart.yOld, minecart.getY());
		double d2 = Mth.lerp(partialTick, minecart.zOld, minecart.getZ());
		double d3 = 0.3F;
		Vec3 vec3 = minecart.getPos(d0, d1, d2);
		float f3 = Mth.lerp(partialTick, minecart.xRotO, minecart.getXRot());
		if (vec3 != null) {
			Vec3 vec31 = minecart.getPosOffs(d0, d1, d2, d3);
			Vec3 vec32 = minecart.getPosOffs(d0, d1, d2, -d3);
			if (vec31 == null) {
				vec31 = vec3;
			}

			if (vec32 == null) {
				vec32 = vec3;
			}

			poseStack.translate(vec3.x - d0, (vec31.y + vec32.y) / 2.0 - d1, vec3.z - d2);
			Vec3 vec33 = vec32.add(-vec31.x, -vec31.y, -vec31.z);
			if (vec33.length() != 0.0) {
				vec33 = vec33.normalize();
				entityYaw = (float) (Math.atan2(vec33.z, vec33.x) * 180.0 / Math.PI);
				f3 = (float) (Math.atan(vec33.y) * 73.0);
			}
		}

		poseStack.translate(0.0F, 0.375F, 0.0F);
		poseStack.mulPose(Axis.YP.rotationDegrees(180.0F - entityYaw));
		poseStack.mulPose(Axis.ZP.rotationDegrees(-f3));
		float f5 = (float) minecart.getHurtTime() - partialTick;
		float f6 = minecart.getDamage() - partialTick;
		if (f6 < 0.0F) {
			f6 = 0.0F;
		}

		if (f5 > 0.0F) {
			poseStack.mulPose(Axis.XP.rotationDegrees(Mth.sin(f5) * f5 * f6 / 10.0F * (float) minecart.getHurtDir()));
		}

		ItemStack banner = ((IDataManager) minecart).getValue(BoatloadTrackedData.BANNER);
		if (banner != null && !banner.isEmpty() && banner.getItem() instanceof BannerItem) {
			Level level = minecart.getCommandSenderWorld();
			int color;
			if (level != null) {
				color = LevelRenderer.getLightColor(level, minecart.blockPosition());
			} else {
				color = 15728880;
			}

			poseStack.pushPose();
			poseStack.translate(0.5D, 5.0F / 16.0F, 17.0F / 16.0F);
			poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));

			BannerBlockEntity blockEntity = new BannerBlockEntity(BlockPos.ZERO, BannerBlock.byItem(banner.getItem()).defaultBlockState());
			blockEntity.setLevel(level);
			blockEntity.fromItem(banner, blockEntity.getBaseColor());
			BlockEntityRenderDispatcher dispatcher = Minecraft.getInstance().getBlockEntityRenderDispatcher();
			BlockEntityRenderer<BannerBlockEntity> renderer = dispatcher.getRenderer(blockEntity);
			if (renderer != null) {
				renderer.render(blockEntity, partialTick, poseStack, buffer, color, OverlayTexture.NO_OVERLAY);
			}

			poseStack.popPose();
		}
		poseStack.popPose();
	}
}
