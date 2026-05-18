package com.teamabnormals.boatload.core.other;

import com.mojang.serialization.Codec;
import net.minecraft.ChatFormatting;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;

import java.util.function.Consumer;

public final class ItemStackWrapper implements TooltipProvider {
	public static final ItemStackWrapper EMPTY = new ItemStackWrapper();
	public static final Codec<ItemStackWrapper> CODEC = ItemStack.CODEC.xmap(ItemStackWrapper::new, wrapper -> wrapper.item);
	public static final StreamCodec<RegistryFriendlyByteBuf, ItemStackWrapper> STREAM_CODEC = ItemStack.OPTIONAL_STREAM_CODEC.map(ItemStackWrapper::new, wrapper -> wrapper.item);

	private final ItemStack item;
	private final int hashCode;

	public ItemStackWrapper(ItemStack item) {
		this.item = item;
		this.hashCode = ItemStack.hashItemAndComponents(item);
	}

	private ItemStackWrapper() {
		this(ItemStack.EMPTY);
	}

	public ItemStack getItem() {
		return this.item;
	}

	@Override
	public int hashCode() {
		return this.hashCode;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		} else {
			return other instanceof ItemStackWrapper wrapper && ItemStack.matches(this.item, wrapper.item);
		}
	}

	@Override
	public void addToTooltip(Item.TooltipContext context, Consumer<Component> tooltipAdder, TooltipFlag tooltipFlag) {
		tooltipAdder.accept(this.item.getHoverName().plainCopy().withStyle(ChatFormatting.GRAY));
	}
}