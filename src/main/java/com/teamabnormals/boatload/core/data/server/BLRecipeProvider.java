package com.teamabnormals.boatload.core.data.server;

import com.teamabnormals.boatload.core.api.ExtraBoatType;
import com.teamabnormals.boatload.common.item.BLBoatItem;
import com.teamabnormals.boatload.core.other.BLUtil;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;

import java.util.function.Consumer;

public class BLRecipeProvider extends RecipeProvider {

	public BLRecipeProvider(DataGenerator generator) {
		super(generator);
	}

	@Override
	protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
		BLUtil.getFurnaceBoats().forEach(item -> furnaceBoatRecipe(consumer, item));
		BLUtil.getLargeBoats().forEach(item -> largeBoatRecipe(consumer, item));
	}

	private void furnaceBoatRecipe(Consumer<FinishedRecipe> consumer, BLBoatItem item) {
		ExtraBoatType type = item.getType();
		ConditionalRecipe.builder().addCondition(new ModLoadedCondition(type.getModID())).addRecipe(ShapedRecipeBuilder.shaped(item).define('F', Items.FURNACE).define('B', type.getBoat()).pattern("F").pattern("B").group("furnace_boat").unlockedBy(getHasName(type.getBoat()), has(type.getBoat()))::save).build(consumer, RecipeBuilder.getDefaultRecipeId(item));
	}

	private void largeBoatRecipe(Consumer<FinishedRecipe> consumer, BLBoatItem item) {
		ExtraBoatType type = item.getType();
		ConditionalRecipe.builder().addCondition(new ModLoadedCondition(type.getModID())).addRecipe(ShapedRecipeBuilder.shaped(item).define('#', type.getPlanks()).define('B', type.getBoat()).pattern("#B#").pattern("###").group("large_boat").unlockedBy(getHasName(type.getBoat()), has(type.getBoat()))::save).build(consumer, RecipeBuilder.getDefaultRecipeId(item));
	}

	private static String getHasName(ItemLike item) {
		return "has_" + getItemName(item);
	}

	private static String getItemName(ItemLike item) {
		return Registry.ITEM.getKey(item.asItem()).getPath();
	}
}