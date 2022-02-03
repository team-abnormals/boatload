package com.teamabnormals.boatload.core.registry;

import com.teamabnormals.boatload.common.entity.vehicle.ChestBoat;
import com.teamabnormals.boatload.common.entity.vehicle.FurnaceBoat;
import com.teamabnormals.boatload.common.entity.vehicle.LargeBoat;
import com.teamabnormals.boatload.core.Boatload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class BoatloadEntityTypes {
	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Boatload.MOD_ID);

	public static RegistryObject<EntityType<ChestBoat>> CHEST_BOAT = ENTITIES.register("chest_boat", () -> EntityType.Builder.<ChestBoat>of(ChestBoat::new, MobCategory.MISC)
			.sized(1.375F, 0.5625F)
			.setCustomClientFactory(ChestBoat::new)
			.build(new ResourceLocation(Boatload.MOD_ID, "chest_boat").toString()));

	public static RegistryObject<EntityType<FurnaceBoat>> FURNACE_BOAT = ENTITIES.register("furnace_boat", () -> EntityType.Builder.<FurnaceBoat>of(FurnaceBoat::new, MobCategory.MISC)
			.sized(1.375F, 0.5625F)
			.setCustomClientFactory(FurnaceBoat::new)
			.build(new ResourceLocation(Boatload.MOD_ID, "furnace_boat").toString()));

	public static RegistryObject<EntityType<LargeBoat>> LARGE_BOAT = ENTITIES.register("large_boat", () -> EntityType.Builder.<LargeBoat>of(LargeBoat::new, MobCategory.MISC)
			.sized(2.5F, 0.5625F)
			.setCustomClientFactory(LargeBoat::new)
			.build(new ResourceLocation(Boatload.MOD_ID, "large_boat").toString()));
}