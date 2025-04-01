package com.teamabnormals.boatload.core.registry;

import com.teamabnormals.blueprint.core.util.registry.EntitySubRegistryHelper;
import com.teamabnormals.boatload.common.entity.vehicle.FurnaceBoat;
import com.teamabnormals.boatload.common.entity.vehicle.LargeBoat;
import com.teamabnormals.boatload.core.Boatload;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;

public class BoatloadEntityTypes {
	public static final EntitySubRegistryHelper ENTITY_TYPES = Boatload.REGISTRY_HELPER.getEntitySubHelper();

	public static DeferredHolder<EntityType<?>, EntityType<FurnaceBoat>> FURNACE_BOAT = ENTITY_TYPES.createEntity("furnace_boat", FurnaceBoat::new, MobCategory.MISC, builder -> builder
			.sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));

	public static DeferredHolder<EntityType<?>, EntityType<LargeBoat>> LARGE_BOAT = ENTITY_TYPES.createEntity("large_boat", LargeBoat::new, MobCategory.MISC, builder -> builder
			.sized(2.5F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
}