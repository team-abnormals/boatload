package com.teamabnormals.boatload.common.inventory;

import com.teamabnormals.boatload.core.registry.BoatloadMenuTypes;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;

public class FurnaceBoatMenu extends AbstractContainerMenu {
	private final Container container;
	private final ContainerData data;

	public FurnaceBoatMenu(int windowId, Inventory inventory) {
		this(windowId, inventory, new SimpleContainer(1), new SimpleContainerData(2));
	}

	public FurnaceBoatMenu(int windowId, Inventory inventory, Container container, ContainerData data) {
		super(BoatloadMenuTypes.FURNACE_BOAT.get(), windowId);
		checkContainerSize(container, 1);
		checkContainerDataCount(data, 2);
		this.container = container;
		this.data = data;
		this.addSlot(new FurnaceBoatSlot(this, container, 0, 80, 35));

		for (int i = 0; i < 3; ++i)
			for (int j = 0; j < 9; ++j)
				this.addSlot(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));

		for (int i = 0; i < 9; ++i)
			this.addSlot(new Slot(inventory, i, 8 + i * 18, 142));

		this.addDataSlots(data);
	}

	@Override
	public boolean stillValid(Player player) {
		return this.container.stillValid(player);
	}

	@Override
	public ItemStack quickMoveStack(Player player, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);
		if (slot != null && slot.hasItem()) {
			ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			if (index > 0) {
				if (this.isFuel(itemstack1)) {
					if (!this.moveItemStackTo(itemstack1, 0, 1, false)) {
						return ItemStack.EMPTY;
					}
				} else if (index < 28) {
					if (!this.moveItemStackTo(itemstack1, 28, 37, false)) {
						return ItemStack.EMPTY;
					}
				} else if (index >= 30 && index < 39 && !this.moveItemStackTo(itemstack1, 1, 28, false)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.moveItemStackTo(itemstack1, 1, 37, false)) {
				return ItemStack.EMPTY;
			}

			if (itemstack1.isEmpty())
				slot.setByPlayer(ItemStack.EMPTY);
			else
				slot.setChanged();

			if (itemstack1.getCount() == itemstack.getCount())
				return ItemStack.EMPTY;

			slot.onTake(player, itemstack1);
		}

		return itemstack;
	}

	public int getLitProgress() {
		int i = this.data.get(1);
		if (i == 0)
			i = 200;

		return this.data.get(0) * 13 / i;
	}

	public boolean isLit() {
		return this.data.get(0) > 0;
	}

	protected boolean isFuel(ItemStack stack) {
		return stack.getBurnTime(RecipeType.SMELTING) > 0;
	}
}