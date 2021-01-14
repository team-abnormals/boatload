package com.minecraftabnormals.extraboats.common.dispenser;

import com.minecraftabnormals.extraboats.common.entity.item.boat.ExtraBoatsBoatEntity;
import com.minecraftabnormals.extraboats.common.entity.item.boat.FurnaceBoatEntity;
import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DispenseFurnaceBoatBehavior extends DefaultDispenseItemBehavior {
	private final DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior();
	private final ExtraBoatsBoatEntity.BoatType type;

	public DispenseFurnaceBoatBehavior(ExtraBoatsBoatEntity.BoatType typeIn) {
		this.type = typeIn;
	}

	@Override
	public ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
		Direction direction = source.getBlockState().get(DispenserBlock.FACING);
		World world = source.getWorld();
		double d0 = source.getX() + (double) ((float) direction.getXOffset() * 1.125F);
		double d1 = source.getY() + (double) ((float) direction.getYOffset() * 1.125F);
		double d2 = source.getZ() + (double) ((float) direction.getZOffset() * 1.125F);
		BlockPos blockpos = source.getBlockPos().offset(direction);
		double d3;
		if (world.getFluidState(blockpos).isTagged(FluidTags.WATER)) {
			d3 = 1.0D;
		} else {
			if (!world.getBlockState(blockpos).isAir() || !world.getFluidState(blockpos.down()).isTagged(FluidTags.WATER)) {
				return this.defaultDispenseItemBehavior.dispense(source, stack);
			}

			d3 = 0.0D;
		}

		FurnaceBoatEntity furnaceboatentity = new FurnaceBoatEntity(world, d0, d1 + d3, d2);
		furnaceboatentity.setModBoatType(this.type);
		furnaceboatentity.rotationYaw = direction.getHorizontalAngle();
		world.addEntity(furnaceboatentity);
		stack.shrink(1);
		return stack;
	}

	@Override
	protected void playDispenseSound(IBlockSource source) {
		source.getWorld().playEvent(1000, source.getBlockPos(), 0);
	}
}