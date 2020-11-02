package com.minecraftabnormals.extraboats.common.item.crafting;

import com.minecraftabnormals.extraboats.core.Reference;

import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EBRecipes
{
	public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Reference.MOD_ID);
	public static final RegistryObject<IRecipeSerializer<?>> CHEST_BOAT = RECIPE_SERIALIZERS.register("crafting_special_chestboat", () -> ChestBoatRecipe.SERIALIZER);
}