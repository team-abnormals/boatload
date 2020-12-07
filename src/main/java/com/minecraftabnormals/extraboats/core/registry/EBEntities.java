package com.minecraftabnormals.extraboats.core.registry;

import com.minecraftabnormals.extraboats.client.renderer.entity.LargeBoatRenderer;
import com.minecraftabnormals.extraboats.client.renderer.entity.ModBoatRenderer;
import com.minecraftabnormals.extraboats.common.entity.item.boat.ChestBoatEntity;
import com.minecraftabnormals.extraboats.common.entity.item.boat.FurnaceBoatEntity;
import com.minecraftabnormals.extraboats.common.entity.item.boat.LargeBoatEntity;
import com.minecraftabnormals.extraboats.core.Reference;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class EBEntities
{
	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Reference.MOD_ID);
	
	public static RegistryObject<EntityType<ChestBoatEntity>> CHEST_BOAT = ENTITIES.register("chest_boat", () -> EntityType.Builder.<ChestBoatEntity>create(ChestBoatEntity::new, EntityClassification.MISC)
			.size(1.375F, 0.5625F)
			.setCustomClientFactory(ChestBoatEntity::new)
			.build(Reference.location("chest_boat").toString()));

	public static RegistryObject<EntityType<FurnaceBoatEntity>> FURNACE_BOAT = ENTITIES.register("furnace_boat", () -> EntityType.Builder.<FurnaceBoatEntity>create(FurnaceBoatEntity::new, EntityClassification.MISC)
			.size(1.375F, 0.5625F)
			.setCustomClientFactory(FurnaceBoatEntity::new)
			.build(Reference.location("furnace_boat").toString()));

	public static RegistryObject<EntityType<LargeBoatEntity>> LARGE_BOAT = ENTITIES.register("large_boat", () -> EntityType.Builder.<LargeBoatEntity>create(LargeBoatEntity::new, EntityClassification.MISC)
			.size(2.5F, 0.5625F)
			.setCustomClientFactory(LargeBoatEntity::new)
			.build(Reference.location("large_boat").toString()));

	public static void setupEntitiesClient()
	{
        RenderingRegistry.registerEntityRenderingHandler(CHEST_BOAT.get(), ModBoatRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(FURNACE_BOAT.get(), ModBoatRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(LARGE_BOAT.get(), LargeBoatRenderer::new);
	}
}