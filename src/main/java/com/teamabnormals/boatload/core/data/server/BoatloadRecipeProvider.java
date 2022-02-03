package com.teamabnormals.boatload.core.data.server;

import com.teamabnormals.boatload.core.api.BoatloadBoatType;
import com.teamabnormals.boatload.common.item.BoatloadBoatItem;
import com.teamabnormals.boatload.core.other.BoatloadUtil;
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
		ConditionalRecipe.builder().addCondition(new ModLoadedCondition(type.getRegistryName().getNamespace())).addRecipe(ShapedRecipeBuilder.shaped(item).define('F', Items.FURNACE).define('B', type.getBoat().get()).pattern("F").pattern("B").group("furnace_boat").unlockedBy(getHasName(type.getBoat().get()), has(type.getBoat().get()))::save).build(consumer, RecipeBuilder.getDefaultRecipeId(item));
	}

	private void largeBoatRecipe(Consumer<FinishedRecipe> consumer, BoatloadBoatItem item) {
		BoatloadBoatType type = item.getType();
		ConditionalRecipe.builder().addCondition(new ModLoadedCondition(type.getRegistryName().getNamespace())).addRecipe(ShapedRecipeBuilder.shaped(item).define('#', type.getPlanks().get()).define('B', type.getBoat().get()).pattern("#B#").pattern("###").group("large_boat").unlockedBy(getHasName(type.getBoat().get()), has(type.getBoat().get()))::save).build(consumer, RecipeBuilder.getDefaultRecipeId(item));
	}

	private static String getHasName(ItemLike item) {
		return "has_" + getItemName(item);
	}

	private static String getItemName(ItemLike item) {
		return Registry.ITEM.getKey(item.asItem()).getPath();
	}
}