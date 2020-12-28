package com.minecraftabnormals.extraboats.common.dispenser;

import com.minecraftabnormals.extraboats.common.entity.item.boat.EBBoatEntity;
import com.minecraftabnormals.extraboats.common.entity.item.boat.LargeBoatEntity;
import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DispenseLargeBoatBehavior extends DefaultDispenseItemBehavior {
	private final DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior();
	private final EBBoatEntity.BoatType type;

	public DispenseLargeBoatBehavior(EBBoatEntity.BoatType typeIn) {
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

		LargeBoatEntity largeboatentity = new LargeBoatEntity(world, d0, d1 + d3, d2);
		largeboatentity.setModBoatType(this.type);
		largeboatentity.rotationYaw = direction.getHorizontalAngle();
		world.addEntity(largeboatentity);
		stack.shrink(1);
		return stack;
	}

	@Override
	protected void playDispenseSound(IBlockSource source) {
		source.getWorld().playEvent(1000, source.getBlockPos(), 0);
	}
}