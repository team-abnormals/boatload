package com.minecraftabnormals.extraboats.common.item.crafting;

import com.minecraftabnormals.extraboats.common.entity.item.boat.ExtraBoatsBoatEntity;
import com.minecraftabnormals.extraboats.common.item.ChestBoatItem;
import com.minecraftabnormals.extraboats.core.BoatHelper;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.SpecialRecipe;
import net.minecraft.item.crafting.SpecialRecipeSerializer;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.Tags;

import javax.annotation.Nonnull;

public class ChestBoatRecipe extends SpecialRecipe {
	public static final SpecialRecipeSerializer<ChestBoatRecipe> SERIALIZER = new SpecialRecipeSerializer<>(ChestBoatRecipe::new);

	public ChestBoatRecipe(ResourceLocation idIn) {
		super(idIn);
	}

	@Override
	public boolean matches(CraftingInventory inv, World worldIn) {
		ItemStack itemstack = ItemStack.EMPTY;
		ItemStack itemstack1 = ItemStack.EMPTY;

		for (int i = 0; i < inv.getContainerSize(); ++i) {
			ItemStack itemstack2 = inv.getItem(i);
			if (!itemstack2.isEmpty()) {
				if (itemstack2.getItem().is(ItemTags.BOATS)) {
					if (!itemstack.isEmpty()) {
						return false;
					}

					itemstack = itemstack2;
				} else if (itemstack2.getItem().is(Tags.Items.CHESTS_WOODEN) && !itemstack2.getItem().is(Tags.Items.CHESTS_TRAPPED)) {
					if (!itemstack1.isEmpty()) {
						return false;
					}

					itemstack1 = itemstack2;
				}
			}
		}

		return !itemstack.isEmpty() && !itemstack1.isEmpty();
	}

	@Nonnull
	@Override
	public ItemStack assemble(CraftingInventory inv) {
		Item item = Items.CHEST;
		ExtraBoatsBoatEntity.BoatType type = ExtraBoatsBoatEntity.BoatType.OAK;

		for (int i = 0; i < inv.getContainerSize(); ++i) {
			ItemStack itemstack2 = inv.getItem(i);
			if (!itemstack2.isEmpty()) {
				if (itemstack2.getItem().is(ItemTags.BOATS)) {
					type = BoatHelper.getType(itemstack2.getItem());
				} else if (itemstack2.getItem().is(Tags.Items.CHESTS_WOODEN) && !itemstack2.getItem().is(Tags.Items.CHESTS_TRAPPED)) {
					item = itemstack2.getItem();
				}
			}
		}

		ItemStack itemstack1 = new ItemStack(BoatHelper.getChestBoatItem(type));
		ChestBoatItem.setChest(itemstack1, item.getItem());

		return itemstack1;
	}

	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return width * height >= 2;
	}

	@Nonnull
	@Override
	public IRecipeSerializer<?> getSerializer() {
		return SERIALIZER;
	}
}