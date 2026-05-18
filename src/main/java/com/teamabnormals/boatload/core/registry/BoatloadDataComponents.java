package com.teamabnormals.boatload.core.registry;

import com.teamabnormals.boatload.core.Boatload;
import com.teamabnormals.boatload.core.other.ItemStackWrapper;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponentType.Builder;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.UnaryOperator;

public class BoatloadDataComponents {
	public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENTS = DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, Boatload.MOD_ID);

	public static final DeferredHolder<DataComponentType<?>, DataComponentType<ItemStackWrapper>> BANNER = register("banner", builder -> builder.persistent(ItemStackWrapper.CODEC).networkSynchronized(ItemStackWrapper.STREAM_CODEC));
	public static final DeferredHolder<DataComponentType<?>, DataComponentType<ItemStackWrapper>> CHEST = register("chest", builder -> builder.persistent(ItemStackWrapper.CODEC).networkSynchronized(ItemStackWrapper.STREAM_CODEC));

	private static <T> DeferredHolder<DataComponentType<?>, DataComponentType<T>> register(String name, UnaryOperator<Builder<T>> builder) {
		return DATA_COMPONENTS.register(name, () -> builder.apply(DataComponentType.builder()).build());
	}
}