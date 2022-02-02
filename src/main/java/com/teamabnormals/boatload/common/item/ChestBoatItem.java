package com.teamabnormals.boatload.common.item;

import com.teamabnormals.boatload.common.entity.vehicle.BLBoat;
import com.teamabnormals.boatload.common.entity.vehicle.BLBoat.BLBoatType;
import com.teamabnormals.boatload.common.entity.vehicle.ChestBoat;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

public class ChestBoatItem extends BLBoatItem {

	public ChestBoatItem(BLBoatType typeIn, Item.Properties properties) {
		super(typeIn, properties);
	}

	public static void setChest(ItemStack itemStack, Item chest) {
		CompoundTag compoundnbt = itemStack.getOrCreateTag();
		compoundnbt.putString("Chest", ForgeRegistries.ITEMS.getKey(chest).toString());
	}

	private static Item getChest(ItemStack itemStack) {
		CompoundTag compoundnbt = itemStack.getTag();

		if (compoundnbt != null) {
			if (compoundnbt.contains("Chest", 8)) {
				return ForgeRegistries.ITEMS.getValue(new ResourceLocation(compoundnbt.getString("Chest")));
			}
		}

		return Items.CHEST;
	}

	@Override
	protected BLBoat getBoatEntity(Level worldIn, HitResult raytraceresult, ItemStack itemStack) {
		ChestBoat boatentity = new ChestBoat(worldIn, raytraceresult.getLocation().x, raytraceresult.getLocation().y, raytraceresult.getLocation().z);
		boatentity.setChest(new ItemStack(getChest(itemStack)));
		return boatentity;
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		Item item = getChest(stack);

		if (item != Items.CHEST) {
			tooltip.add(((TranslatableComponent) item.getDescription()).withStyle(ChatFormatting.GRAY));
		}

		super.appendHoverText(stack, worldIn, tooltip, flagIn);
	}

	@Override
	public int getBurnTime(ItemStack itemStack, RecipeType<?> recipeType) {
		return 1500;
	}
}