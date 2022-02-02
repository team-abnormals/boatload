package com.teamabnormals.extraboats.core.data.server;

import com.teamabnormals.extraboats.common.entity.vehicle.EBBoat.EBBoatType;
import com.teamabnormals.extraboats.common.item.EBBoatItem;
import com.teamabnormals.extraboats.core.other.EBUtil;
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

public class EBRecipeProvider extends RecipeProvider {

	public EBRecipeProvider(DataGenerator generator) {
		super(generator);
	}

	@Override
	protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
		EBUtil.getFurnaceBoats().forEach(item -> furnaceBoatRecipe(consumer, item));
		EBUtil.getLargeBoats().forEach(item -> largeBoatRecipe(consumer, item));
	}

	private void furnaceBoatRecipe(Consumer<FinishedRecipe> consumer, EBBoatItem item) {
		EBBoatType type = item.getType();
		ConditionalRecipe.builder().addCondition(new ModLoadedCondition(type.getModID())).addRecipe(ShapedRecipeBuilder.shaped(item).define('F', Items.FURNACE).define('B', type.getBoat()).pattern("F").pattern("B").group("furnace_boat").unlockedBy(getHasName(type.getBoat()), has(type.getBoat()))::save).build(consumer, RecipeBuilder.getDefaultRecipeId(item));
	}

	private void largeBoatRecipe(Consumer<FinishedRecipe> consumer, EBBoatItem item) {
		EBBoatType type = item.getType();
		ConditionalRecipe.builder().addCondition(new ModLoadedCondition(type.getModID())).addRecipe(ShapedRecipeBuilder.shaped(item).define('#', type.getPlanks()).define('B', type.getBoat()).pattern("#B#").pattern("###").group("large_boat").unlockedBy(getHasName(type.getBoat()), has(type.getBoat()))::save).build(consumer, RecipeBuilder.getDefaultRecipeId(item));
	}

	private static String getHasName(ItemLike item) {
		return "has_" + getItemName(item);
	}

	private static String getItemName(ItemLike item) {
		return Registry.ITEM.getKey(item.asItem()).getPath();
	}
}