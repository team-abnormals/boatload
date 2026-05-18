package com.teamabnormals.boatload.core.mixin;

import com.teamabnormals.boatload.core.other.ItemStackWrapper;
import com.teamabnormals.boatload.core.registry.BoatloadDataComponents;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import net.neoforged.neoforge.common.Tags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ShapelessRecipe.class)
public class ShapelessRecipeMixin {

	@Inject(method = "assemble(Lnet/minecraft/world/item/crafting/CraftingInput;Lnet/minecraft/core/HolderLookup$Provider;)Lnet/minecraft/world/item/ItemStack;", at = @At("RETURN"), cancellable = true)
	private void assemble(CraftingInput input, Provider registries, CallbackInfoReturnable<ItemStack> cir) {
		ItemStack result = cir.getReturnValue();
		if (result.is(ItemTags.CHEST_BOATS) || result.getItem() == Items.CHEST_MINECART) {
			for (int i = 0; i < input.size(); i++) {
				ItemStack inputStack = input.getItem(i);
				if (inputStack.is(Tags.Items.CHESTS_WOODEN)) {
					result.set(BoatloadDataComponents.CHEST, new ItemStackWrapper(inputStack.copy()));
					cir.setReturnValue(result);
					break;
				}
			}
		}
	}
}