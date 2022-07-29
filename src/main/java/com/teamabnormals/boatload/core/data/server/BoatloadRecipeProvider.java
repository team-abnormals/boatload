package com.teamabnormals.boatload.core.data.server;

import com.teamabnormals.boatload.common.item.BoatloadBoatItem;
import com.teamabnormals.boatload.core.api.BoatloadBoatType;
import com.teamabnormals.boatload.core.other.BoatloadUtil;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Consumer;

public class BoatloadRecipeProvider extends RecipeProvider {

	public BoatloadRecipeProvider(DataGenerator generator) {
		super(generator);
	}

	@Override
	protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
		BoatloadUtil.getFurnaceBoats().forEach(item -> furnaceBoatRecipe(consumer, item));
		BoatloadUtil.getLargeBoats().forEach(item -> largeBoatRecipe(consumer, item));
	}

	private void furnaceBoatRecipe(Consumer<FinishedRecipe> consumer, BoatloadBoatItem item) {
		BoatloadBoatType type = item.getType();
		ShapelessRecipeBuilder.shapeless(item).requires(Blocks.FURNACE).requires(type.boat().get()).group("furnace_boat").unlockedBy("has_boat", has(ItemTags.BOATS)).save(consumer);
	}

	private void largeBoatRecipe(Consumer<FinishedRecipe> consumer, BoatloadBoatItem item) {
		BoatloadBoatType type = item.getType();
		ShapedRecipeBuilder.shaped(item).define('#', type.planks().get()).define('B', type.boat().get()).pattern("#B#").pattern("###").group("large_boat").unlockedBy("has_boat", has(ItemTags.BOATS)).save(consumer);
	}
}