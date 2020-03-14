package com.markus1002.extraboats.entity;

import com.markus1002.extraboats.Reference;
import com.markus1002.extraboats.client.renderer.entity.LargeBoatRenderer;
import com.markus1002.extraboats.client.renderer.entity.ModBoatRenderer;
import com.markus1002.extraboats.entity.item.boat.ChestBoatEntity;
import com.markus1002.extraboats.entity.item.boat.FurnaceBoatEntity;
import com.markus1002.extraboats.entity.item.boat.LargeBoatEntity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntities
{
	public static final EntityType<ChestBoatEntity> CHEST_BOAT = EntityType.Builder.<ChestBoatEntity>create(ChestBoatEntity::new, EntityClassification.MISC)
			.size(1.375F, 0.5625F)
			.setCustomClientFactory((FMLPlayMessages.SpawnEntity spawnEntity, World worldIn) -> new ChestBoatEntity(spawnEntity, worldIn))
			.build(Reference.location("chest_boat").toString());

	public static final EntityType<FurnaceBoatEntity> FURNACE_BOAT = EntityType.Builder.<FurnaceBoatEntity>create(FurnaceBoatEntity::new, EntityClassification.MISC)
			.size(1.375F, 0.5625F)
			.setCustomClientFactory((FMLPlayMessages.SpawnEntity spawnEntity, World worldIn) -> new FurnaceBoatEntity(spawnEntity, worldIn))
			.build(Reference.location("furnace_boat").toString());

	public static final EntityType<LargeBoatEntity> LARGE_BOAT = EntityType.Builder.<LargeBoatEntity>create(LargeBoatEntity::new, EntityClassification.MISC)
			.size(2.4F, 0.5625F)
			.setCustomClientFactory((FMLPlayMessages.SpawnEntity spawnEntity, World worldIn) -> new LargeBoatEntity(spawnEntity, worldIn))
			.build(Reference.location("large_boat").toString());

	@SubscribeEvent
	public static void registerEntities(RegistryEvent.Register<EntityType<?>> event)
	{
		registerEntity(CHEST_BOAT, "chest_boat");

		registerEntity(FURNACE_BOAT, "furnace_boat");

		registerEntity(LARGE_BOAT, "large_boat");
	}

	private static void registerEntity(EntityType<?> entity, String name)
	{
		entity.setRegistryName(Reference.location(name));
		ForgeRegistries.ENTITIES.register(entity);
	}

	public static void setupEntitiesClient()
	{
        RenderingRegistry.registerEntityRenderingHandler((EntityType<? extends ChestBoatEntity>)CHEST_BOAT, ModBoatRenderer::new);
        
        RenderingRegistry.registerEntityRenderingHandler((EntityType<? extends FurnaceBoatEntity>)FURNACE_BOAT, ModBoatRenderer::new);
        
        RenderingRegistry.registerEntityRenderingHandler((EntityType<? extends LargeBoatEntity>)LARGE_BOAT, LargeBoatRenderer::new);
	}
}