package com.minecraftabnormals.extraboats.core.registry;

import com.minecraftabnormals.extraboats.client.renderer.entity.LargeBoatRenderer;
import com.minecraftabnormals.extraboats.client.renderer.entity.ModBoatRenderer;
import com.minecraftabnormals.extraboats.common.entity.item.boat.ChestBoatEntity;
import com.minecraftabnormals.extraboats.common.entity.item.boat.FurnaceBoatEntity;
import com.minecraftabnormals.extraboats.common.entity.item.boat.LargeBoatEntity;
import com.minecraftabnormals.extraboats.core.ExtraBoats;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class EBEntities {
	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, ExtraBoats.MOD_ID);

	public static RegistryObject<EntityType<ChestBoatEntity>> CHEST_BOAT = ENTITIES.register("chest_boat", () -> EntityType.Builder.<ChestBoatEntity>of(ChestBoatEntity::new, EntityClassification.MISC)
			.sized(1.375F, 0.5625F)
			.setCustomClientFactory(ChestBoatEntity::new)
			.build(new ResourceLocation(ExtraBoats.MOD_ID, "chest_boat").toString()));

	public static RegistryObject<EntityType<FurnaceBoatEntity>> FURNACE_BOAT = ENTITIES.register("furnace_boat", () -> EntityType.Builder.<FurnaceBoatEntity>of(FurnaceBoatEntity::new, EntityClassification.MISC)
			.sized(1.375F, 0.5625F)
			.setCustomClientFactory(FurnaceBoatEntity::new)
			.build(new ResourceLocation(ExtraBoats.MOD_ID, "furnace_boat").toString()));

	public static RegistryObject<EntityType<LargeBoatEntity>> LARGE_BOAT = ENTITIES.register("large_boat", () -> EntityType.Builder.<LargeBoatEntity>of(LargeBoatEntity::new, EntityClassification.MISC)
			.sized(2.5F, 0.5625F)
			.setCustomClientFactory(LargeBoatEntity::new)
			.build(new ResourceLocation(ExtraBoats.MOD_ID, "large_boat").toString()));

	public static void registerRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(CHEST_BOAT.get(), ModBoatRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(FURNACE_BOAT.get(), ModBoatRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(LARGE_BOAT.get(), LargeBoatRenderer::new);
	}
}