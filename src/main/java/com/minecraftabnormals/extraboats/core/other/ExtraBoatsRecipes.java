package com.minecraftabnormals.extraboats.core.other;

import com.minecraftabnormals.extraboats.common.item.crafting.ChestBoatRecipe;
import com.minecraftabnormals.extraboats.core.ExtraBoats;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ExtraBoatsRecipes {
	public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, ExtraBoats.MOD_ID);
	public static final RegistryObject<IRecipeSerializer<?>> CHEST_BOAT = RECIPE_SERIALIZERS.register("crafting_special_chestboat", () -> ChestBoatRecipe.SERIALIZER);
}