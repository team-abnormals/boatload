package com.teamabnormals.boatload.core.registry;

import com.teamabnormals.boatload.common.entity.vehicle.FurnaceBoat;
import com.teamabnormals.boatload.common.entity.vehicle.LargeBoat;
import com.teamabnormals.boatload.core.Boatload;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BoatloadEntityTypes {
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, Boatload.MOD_ID);

	public static DeferredHolder<EntityType<?>, EntityType<FurnaceBoat>> FURNACE_BOAT = ENTITY_TYPES.register("furnace_boat", () -> EntityType.Builder.<FurnaceBoat>of(FurnaceBoat::new, MobCategory.MISC)
			.sized(1.375F, 0.5625F)
			.eyeHeight(0.5625F).clientTrackingRange(10)
			.build(Boatload.location("furnace_boat").toString()));

	public static DeferredHolder<EntityType<?>, EntityType<LargeBoat>> LARGE_BOAT = ENTITY_TYPES.register("large_boat", () -> EntityType.Builder.<LargeBoat>of(LargeBoat::new, MobCategory.MISC)
			.sized(2.5F, 0.5625F)
			.eyeHeight(0.5625F).clientTrackingRange(10)
			.build(Boatload.location("large_boat").toString()));
}