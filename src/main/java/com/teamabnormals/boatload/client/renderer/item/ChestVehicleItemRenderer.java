package com.teamabnormals.boatload.client.renderer.item;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Transformation;
import com.teamabnormals.blueprint.client.model.DynamicItemModel;
import com.teamabnormals.boatload.core.Boatload;
import com.teamabnormals.boatload.core.other.ItemStackWrapper;
import com.teamabnormals.boatload.core.registry.BoatloadDataComponents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.*;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.client.resources.model.SimpleBakedModel;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.Holder.Reference;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.client.model.BakedModelWrapper;
import net.neoforged.neoforge.client.model.SimpleModelState;
import net.neoforged.neoforge.client.model.geometry.UnbakedGeometryHelper;
import org.joml.Vector3f;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EventBusSubscriber(modid = Boatload.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ChestVehicleItemRenderer extends BlockEntityWithoutLevelRenderer {
	private static final Map<ResourceLocation, BakedModel> OVERLAY_CACHE = new HashMap<>();
	public static final ChestVehicleItemRenderer MINECART = new ChestVehicleItemRenderer("chest_minecart");
	public static final ChestVehicleItemRenderer BOAT = new ChestVehicleItemRenderer("chest_boat");
	public static final ChestVehicleItemRenderer RAFT = new ChestVehicleItemRenderer("chest_raft");
	private final String text;

	public ChestVehicleItemRenderer(String text) {
		super(null, null);
		this.text = text;
	}

	@Override
	public void renderByItem(ItemStack stack, ItemDisplayContext context, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
		poseStack.pushPose();
		poseStack.translate(0.5F, 0.5F, 0.5F);
		poseStack.scale(-1.0F, 1.0F, -1.0F);

		ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
		ModelManager modelManager = itemRenderer.getItemModelShaper().getModelManager();

		ResourceLocation vehicleLocation = stack.getItemHolder().getKey().location();
		WrappedGeneratedItemModel model = (WrappedGeneratedItemModel) modelManager.getModel(ModelResourceLocation.inventory(vehicleLocation));
		itemRenderer.render(stack, ItemDisplayContext.FIXED, true, poseStack, buffer, packedLight, packedOverlay, model.getOriginal());

		ItemStackWrapper chest = stack.get(BoatloadDataComponents.CHEST);
		if (chest != null) {
			ResourceLocation chestLocation = chest.getItem().getItemHolder().getKey().location().withPrefix("item/" + this.text + "/");
			TextureAtlasSprite sprite = modelManager.getAtlas(TextureAtlas.LOCATION_BLOCKS).getSprite(chestLocation);
			if (!sprite.contents().name().equals(MissingTextureAtlasSprite.getLocation())) {
				poseStack.pushPose();
				poseStack.scale(-1.0F, 1.0F, -1.0F);
				itemRenderer.render(stack, ItemDisplayContext.FIXED, true, poseStack, buffer, packedLight, packedOverlay, createChestModel(sprite));
				poseStack.popPose();
			}
		}

		poseStack.popPose();
	}

	@SubscribeEvent
	public static void registerAdditional(ModelEvent.RegisterAdditional event) {
		for (String name : List.of("chest_minecart", "chest_boat", "chest_raft")) {
			DynamicItemModel.register(event, name);
		}
	}

	@SubscribeEvent
	public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
		event.registerItem(new ClientExtension(MINECART), Items.CHEST_MINECART);
		event.registerItem(new ClientExtension(RAFT), Items.BAMBOO_CHEST_RAFT);
		for (Holder<Item> item : chestBoats()) {
			event.registerItem(new ClientExtension(BOAT), item);
		}
	}

	@SubscribeEvent
	public static void onModelBake(ModelEvent.ModifyBakingResult event) {
		wrapModel(event, Items.CHEST_MINECART);
		wrapModel(event, Items.BAMBOO_CHEST_RAFT);
		for (Holder<Item> item : chestBoats()) {
			wrapModel(event, item.value());
		}
	}

	private static List<Reference<Item>> chestBoats() {
		return BuiltInRegistries.ITEM.holders().filter(holder -> holder.key().location().getPath().contains("chest_boat")).toList();
	}

	private static BakedModel createChestModel(TextureAtlasSprite sprite) {
		return OVERLAY_CACHE.computeIfAbsent(sprite.contents().name(), location -> bakeGeneratedModel(sprite));
	}

	private static BakedModel bakeGeneratedModel(TextureAtlasSprite sprite) {
		List<BlockElement> elements = List.of(new BlockElement(new Vector3f(0.0F, 0.0F, 7.5F), new Vector3f(16.0F, 16.0F, 8.5F), Map.of(Direction.SOUTH, new BlockElementFace(null, 0, "layer1", new BlockFaceUV(new float[]{0F, 0F, 16F, 16F}, 0))), null, true));
		List<BakedQuad> quads = UnbakedGeometryHelper.bakeElements(elements, material -> sprite, new SimpleModelState(Transformation.identity()));
		Map<Direction, List<BakedQuad>> culledFaces = new EnumMap<>(Direction.class);

		for (Direction direction : Direction.values()) {
			culledFaces.put(direction, List.of());
		}

		return new SimpleBakedModel(quads, culledFaces, false, false, false, sprite, ItemTransforms.NO_TRANSFORMS, ItemOverrides.EMPTY);
	}

	private static void wrapModel(ModelEvent.ModifyBakingResult event, Item item) {
		ModelResourceLocation id = ModelResourceLocation.inventory(item.builtInRegistryHolder().key().location());
		BakedModel original = event.getModels().get(id);
		if (original != null) {
			event.getModels().put(id, new WrappedGeneratedItemModel(original));
		}
	}

	private record ClientExtension(ChestVehicleItemRenderer renderer) implements IClientItemExtensions {
		@Override
		public BlockEntityWithoutLevelRenderer getCustomRenderer() {
			return renderer;
		}
	}

	public static class WrappedGeneratedItemModel extends BakedModelWrapper<BakedModel> {
		public WrappedGeneratedItemModel(BakedModel original) {
			super(original);
		}

		public BakedModel getOriginal() {
			return this.originalModel;
		}

		@Override
		public BakedModel applyTransform(ItemDisplayContext context, PoseStack poseStack, boolean leftHand) {
			this.originalModel.applyTransform(context, poseStack, leftHand);
			return this;
		}

		@Override
		public boolean isCustomRenderer() {
			return true;
		}
	}
}