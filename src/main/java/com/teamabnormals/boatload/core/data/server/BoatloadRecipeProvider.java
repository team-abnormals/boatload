package com.teamabnormals.boatload.core.data.server;

import com.teamabnormals.boatload.common.item.BoatloadBoatItem;
import com.teamabnormals.boatload.core.other.BoatloadUtil;
import com.teamabnormals.boatload.core.registry.BoatloadItems;
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
		woodenBoat(consumer, BoatloadItems.CRIMSON_BOAT.get(), Blocks.CRIMSON_PLANKS);
		woodenBoat(consumer, BoatloadItems.WARPED_BOAT.get(), Blocks.WARPED_PLANKS);
		chestBoat(consumer, BoatloadItems.CRIMSON_CHEST_BOAT.get(), BoatloadItems.CRIMSON_BOAT.get());
		chestBoat(consumer, BoatloadItems.WARPED_CHEST_BOAT.get(), BoatloadItems.WARPED_BOAT.get());
		BoatloadUtil.getFurnaceBoats().forEach(item -> furnaceBoat(consumer, item));
		BoatloadUtil.getLargeBoats().forEach(item -> largeBoat(consumer, item));
	}

	private void furnaceBoat(Consumer<FinishedRecipe> consumer, BoatloadBoatItem item) {
		ShapelessRecipeBuilder.shapeless(item).requires(Blocks.FURNACE).requires(item.getType().boat().get()).group("furnace_boat").unlockedBy("has_boat", has(ItemTags.BOATS)).save(consumer);
	}

	private void largeBoat(Consumer<FinishedRecipe> consumer, BoatloadBoatItem item) {
		ShapedRecipeBuilder.shaped(item).define('#', item.getType().planks().get()).define('B', item.getType().boat().get()).pattern("#B#").pattern("###").group("large_boat").unlockedBy("has_boat", has(ItemTags.BOATS)).save(consumer);
	}
}