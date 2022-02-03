package com.teamabnormals.boatload.common.dispenser;

import com.teamabnormals.boatload.core.api.BoatloadBoatType;
import com.teamabnormals.boatload.common.entity.vehicle.FurnaceBoat;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;

public class FurnaceBoatDispenseItemBehavior extends DefaultDispenseItemBehavior {
	private final DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior();
	private final BoatloadBoatType type;

	public FurnaceBoatDispenseItemBehavior(BoatloadBoatType typeIn) {
		this.type = typeIn;
	}

	@Override
	public ItemStack execute(BlockSource source, ItemStack stack) {
		Direction direction = source.getBlockState().getValue(DispenserBlock.FACING);
		Level world = source.getLevel();
		double d0 = source.x() + (double) ((float) direction.getStepX() * 1.125F);
		double d1 = source.y() + (double) ((float) direction.getStepY() * 1.125F);
		double d2 = source.z() + (double) ((float) direction.getStepZ() * 1.125F);
		BlockPos blockpos = source.getPos().relative(direction);
		double d3;
		if (world.getFluidState(blockpos).is(FluidTags.WATER)) {
			d3 = 1.0D;
		} else {
			if (!world.getBlockState(blockpos).isAir() || !world.getFluidState(blockpos.below()).is(FluidTags.WATER)) {
				return this.defaultDispenseItemBehavior.dispense(source, stack);
			}

			d3 = 0.0D;
		}

		FurnaceBoat furnaceboatentity = new FurnaceBoat(world, d0, d1 + d3, d2);
		furnaceboatentity.setModBoatType(this.type);
		furnaceboatentity.setYRot(direction.toYRot());
		world.addFreshEntity(furnaceboatentity);
		stack.shrink(1);
		return stack;
	}

	@Override
	protected void playSound(BlockSource source) {
		source.getLevel().levelEvent(1000, source.getPos(), 0);
	}
}