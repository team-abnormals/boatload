package com.markus1002.extraboats.common.item;

import java.util.List;

import com.markus1002.extraboats.common.entity.item.boat.ChestBoatEntity;
import com.markus1002.extraboats.common.entity.item.boat.EBBoatEntity;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;

public class ChestBoatItem extends EBBoatItem
{
	public ChestBoatItem(EBBoatEntity.BoatType typeIn, Item.Properties properties)
	{
		super(typeIn, properties);
	}

	public static void setChest(ItemStack itemStack, Item chest)
	{
		CompoundNBT compoundnbt = itemStack.getOrCreateTag();
		compoundnbt.putString("Chest", ForgeRegistries.ITEMS.getKey(chest).toString());
	}

	private static Item getChest(ItemStack itemStack)
	{
		CompoundNBT compoundnbt = itemStack.getTag();

		if (compoundnbt != null)
		{
			if (compoundnbt.contains("Chest", 8))
			{
				return ForgeRegistries.ITEMS.getValue(new ResourceLocation(compoundnbt.getString("Chest")));
			}
		}

		return Items.CHEST;
	}

	@Override
	protected EBBoatEntity getBoatEntity(World worldIn, RayTraceResult raytraceresult, ItemStack itemStack)
	{
		ChestBoatEntity boatentity = new ChestBoatEntity(worldIn, raytraceresult.getHitVec().x, raytraceresult.getHitVec().y, raytraceresult.getHitVec().z);
		boatentity.setChest(new ItemStack(getChest(itemStack)));
		return boatentity;
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		Item item = getChest(stack);
        tooltip.add(((TranslationTextComponent) item.getName()).func_240699_a_(TextFormatting.GRAY));
        
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	@Override
	public int getBurnTime(ItemStack itemStack)
	{
		return 1500;
	}
}