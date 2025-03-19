package com.teamabnormals.boatload.core.data.server;

import com.teamabnormals.boatload.core.Boatload;
import com.teamabnormals.boatload.core.api.BoatloadBoatType;
import com.teamabnormals.boatload.core.other.BoatloadUtil;
import com.teamabnormals.boatload.core.registry.BoatloadItems;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;

import java.util.concurrent.CompletableFuture;

public class BoatloadRecipeProvider extends RecipeProvider {
	public static final ModLoadedCondition BOATLOADED = new ModLoadedCondition(Boatload.MOD_ID);

	public BoatloadRecipeProvider(PackOutput output, CompletableFuture<Provider> provider) {
		super(output, provider);
	}

	@Override
	protected void buildRecipes(RecipeOutput consumer) {
		woodenBoat(consumer, BoatloadItems.CRIMSON_BOAT.get(), Blocks.CRIMSON_PLANKS);
		woodenBoat(consumer, BoatloadItems.WARPED_BOAT.get(), Blocks.WARPED_PLANKS);
		chestBoat(consumer, BoatloadItems.CRIMSON_CHEST_BOAT.get(), BoatloadItems.CRIMSON_BOAT.get());
		chestBoat(consumer, BoatloadItems.WARPED_CHEST_BOAT.get(), BoatloadItems.WARPED_BOAT.get());

		BoatloadUtil.getFurnaceBoats().forEach(boat -> furnaceBoat(consumer, boat, boat.getType().boat().get()));
		BoatloadUtil.getLargeBoats().forEach(boat -> largeBoat(consumer, boat, boat.getType().boat().get(), boat.getType().planks().get()));
	}

	public static void boatRecipes(RecipeOutput consumer, BoatloadBoatType boatType) {
		boatRecipes(consumer, boatType.boat().get(), boatType.chestBoat().get(), boatType.furnaceBoat().get(), boatType.largeBoat().get(), boatType.planks().get());
	}

	public static void boatRecipes(RecipeOutput consumer, ItemLike boat, ItemLike chestBoat, ItemLike furnaceBoat, ItemLike largeBoat, ItemLike planks) {
		woodenBoat(consumer, boat, planks);
		chestBoat(consumer, chestBoat, boat);
		furnaceBoatBuilder(furnaceBoat, boat).save(consumer.withConditions(BOATLOADED));
		largeBoatBuilder(largeBoat, boat, planks).save(consumer.withConditions(BOATLOADED));
	}

	public static void chestBoat(RecipeOutput consumer, ItemLike chestBoat, ItemLike boat) {
		ShapelessRecipeBuilder.shapeless(RecipeCategory.TRANSPORTATION, chestBoat).requires(Tags.Items.CHESTS_WOODEN).requires(boat).group("chest_boat").unlockedBy("has_boat", has(ItemTags.BOATS)).save(consumer);
	}

	public static void furnaceBoat(RecipeOutput consumer, ItemLike furnaceBoat, ItemLike boat) {
		furnaceBoatBuilder(furnaceBoat, boat).save(consumer);
	}

	public static ShapelessRecipeBuilder furnaceBoatBuilder(ItemLike furnaceBoat, ItemLike boat) {
		return ShapelessRecipeBuilder.shapeless(RecipeCategory.TRANSPORTATION, furnaceBoat).requires(Blocks.FURNACE).requires(boat).group("furnace_boat").unlockedBy("has_boat", has(ItemTags.BOATS));
	}

	public static void largeBoat(RecipeOutput consumer, ItemLike largeBoat, ItemLike boat, ItemLike planks) {
		largeBoatBuilder(largeBoat, boat, planks).save(consumer);
	}

	public static ShapedRecipeBuilder largeBoatBuilder(ItemLike largeBoat, ItemLike boat, ItemLike planks) {
		return ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, largeBoat).define('#', planks).define('B', boat).pattern("#B#").pattern("###").group("large_boat").unlockedBy("has_boat", has(ItemTags.BOATS));
	}
}