package com.teamabnormals.extraboats.core.other;

import com.teamabnormals.extraboats.common.item.crafting.ChestBoatRecipe;
import com.teamabnormals.extraboats.core.ExtraBoats;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EBRecipes {
	public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, ExtraBoats.MOD_ID);

	public static final RegistryObject<RecipeSerializer<?>> CHEST_BOAT = RECIPE_SERIALIZERS.register("crafting_special_chestboat", () -> ChestBoatRecipe.SERIALIZER);
}