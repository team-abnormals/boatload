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
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;

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

	protected static void chestBoat(Consumer<FinishedRecipe> consumer, ItemLike chestBoat, ItemLike boat) {
		ShapelessRecipeBuilder.shapeless(chestBoat).requires(Tags.Items.CHESTS_WOODEN).requires(boat).group("chest_boat").unlockedBy("has_boat", has(ItemTags.BOATS)).save(consumer);
	}

	private static void furnaceBoat(Consumer<FinishedRecipe> consumer, BoatloadBoatItem furnaceBoat) {
		ShapelessRecipeBuilder.shapeless(furnaceBoat).requires(Blocks.FURNACE).requires(furnaceBoat.getType().boat().get()).group("furnace_boat").unlockedBy("has_boat", has(ItemTags.BOATS)).save(consumer);
	}

	private static void largeBoat(Consumer<FinishedRecipe> consumer, BoatloadBoatItem largeBoat) {
		ShapedRecipeBuilder.shaped(largeBoat).define('#', largeBoat.getType().planks().get()).define('B', largeBoat.getType().boat().get()).pattern("#B#").pattern("###").group("large_boat").unlockedBy("has_boat", has(ItemTags.BOATS)).save(consumer);
	}
}