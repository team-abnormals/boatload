package com.teamabnormals.extraboats.common.item.crafting;

import com.teamabnormals.extraboats.common.entity.vehicle.EBBoat.EBBoatType;
import com.teamabnormals.extraboats.common.item.ChestBoatItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleRecipeSerializer;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.Tags;

import javax.annotation.Nonnull;

public class ChestBoatRecipe extends CustomRecipe {
	public static final SimpleRecipeSerializer<ChestBoatRecipe> SERIALIZER = new SimpleRecipeSerializer<>(ChestBoatRecipe::new);

	public ChestBoatRecipe(ResourceLocation idIn) {
		super(idIn);
	}

	@Override
	public boolean matches(CraftingContainer inv, Level worldIn) {
		ItemStack itemstack = ItemStack.EMPTY;
		ItemStack itemstack1 = ItemStack.EMPTY;

		for (int i = 0; i < inv.getContainerSize(); ++i) {
			ItemStack itemstack2 = inv.getItem(i);
			if (!itemstack2.isEmpty()) {
				if (itemstack2.is(ItemTags.BOATS)) {
					if (!itemstack.isEmpty()) {
						return false;
					}

					itemstack = itemstack2;
				} else if (itemstack2.is(Tags.Items.CHESTS_WOODEN) && !itemstack2.is(Tags.Items.CHESTS_TRAPPED)) {
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
	public ItemStack assemble(CraftingContainer inv) {
		Item item = Items.CHEST;
		EBBoatType type = EBBoatType.OAK;

		for (int i = 0; i < inv.getContainerSize(); ++i) {
			ItemStack itemstack2 = inv.getItem(i);
			if (!itemstack2.isEmpty()) {
				if (itemstack2.is(ItemTags.BOATS)) {
					type = EBBoatType.getTypeFromBoat(itemstack2.getItem());
				} else if (itemstack2.is(Tags.Items.CHESTS_WOODEN) && !itemstack2.is(Tags.Items.CHESTS_TRAPPED)) {
					item = itemstack2.getItem();
				}
			}
		}

		ItemStack itemstack1 = new ItemStack(type.getChestBoat());
		ChestBoatItem.setChest(itemstack1, item);

		return itemstack1;
	}

	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return width * height >= 2;
	}

	@Nonnull
	@Override
	public RecipeSerializer<?> getSerializer() {
		return SERIALIZER;
	}
}