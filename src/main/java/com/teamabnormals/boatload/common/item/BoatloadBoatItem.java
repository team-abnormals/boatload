package com.teamabnormals.boatload.common.item;

import com.teamabnormals.boatload.common.entity.vehicle.BoatloadBoat;
import com.teamabnormals.boatload.core.api.BoatloadBoatType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.function.Predicate;

public class BoatloadBoatItem extends Item {
	private static final Predicate<Entity> ENTITY_PREDICATE = EntitySelector.NO_SPECTATORS.and(Entity::isPickable);
	private final BoatloadBoatType type;

	public BoatloadBoatItem(BoatloadBoatType typeIn) {
		this(typeIn, new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_TRANSPORTATION));
	}

	public BoatloadBoatItem(BoatloadBoatType typeIn, Item.Properties properties) {
		super(properties);
		this.type = typeIn;
		DispenserBlock.registerBehavior(this, new BoatloadBoatDispenseItemBehavior());
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
		ItemStack itemstack = playerIn.getItemInHand(handIn);
		HitResult raytraceresult = getPlayerPOVHitResult(worldIn, playerIn, ClipContext.Fluid.ANY);
		if (raytraceresult.getType() == HitResult.Type.MISS) {
			return InteractionResultHolder.pass(itemstack);
		} else {
			Vec3 vector3d = playerIn.getViewVector(1.0F);
			List<Entity> list = worldIn.getEntities(playerIn, playerIn.getBoundingBox().expandTowards(vector3d.scale(5.0D)).inflate(1.0D), ENTITY_PREDICATE);
			if (!list.isEmpty()) {
				Vec3 vector3d1 = playerIn.getEyePosition(1.0F);

				for (Entity entity : list) {
					AABB axisalignedbb = entity.getBoundingBox().inflate(entity.getPickRadius());
					if (axisalignedbb.contains(vector3d1)) {
						return InteractionResultHolder.pass(itemstack);
					}
				}
			}

			if (raytraceresult.getType() == HitResult.Type.BLOCK) {
				BoatloadBoat boatentity = this.getBoatEntity(worldIn, raytraceresult, itemstack);
				boatentity.setBoatloadBoatType(this.type);
				boatentity.setYRot(playerIn.getYRot());
				if (!worldIn.noCollision(boatentity, boatentity.getBoundingBox().inflate(-0.1D))) {
					return InteractionResultHolder.fail(itemstack);
				} else {
					if (!worldIn.isClientSide) {
						worldIn.addFreshEntity(boatentity);
						if (!playerIn.getAbilities().instabuild) {
							itemstack.shrink(1);
						}
					}

					playerIn.awardStat(Stats.ITEM_USED.get(this));
					return InteractionResultHolder.success(itemstack);
				}
			} else {
				return InteractionResultHolder.pass(itemstack);
			}
		}
	}

	protected BoatloadBoat getBoatEntity(Level level, HitResult result, ItemStack stack) {
		return this.getBoatEntity(level, result.getLocation().x, result.getLocation().y, result.getLocation().z, stack);
	}

	protected BoatloadBoat getBoatEntity(Level level, double x, double y, double z, ItemStack stack) {
		return null;
	}

	public BoatloadBoatType getType() {
		return this.type;
	}

	public static class BoatloadBoatDispenseItemBehavior extends DefaultDispenseItemBehavior {
		private final DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior();

		public ItemStack execute(BlockSource source, ItemStack stack) {
			Direction direction = source.getBlockState().getValue(DispenserBlock.FACING);
			Level level = source.getLevel();
			double x = source.x() + (double) ((float) direction.getStepX() * 1.125f);
			double y = source.y() + (double) ((float) direction.getStepY() * 1.125f);
			double z = source.z() + (double) ((float) direction.getStepZ() * 1.125f);
			BlockPos pos = source.getPos().relative(direction);
			double adjustY;
			if (level.getFluidState(pos).is(FluidTags.WATER)) {
				adjustY = 1d;
			} else {
				if (!level.getBlockState(pos).isAir() || !level.getFluidState(pos.below()).is(FluidTags.WATER)) {
					return this.defaultDispenseItemBehavior.dispense(source, stack);
				}
				adjustY = 0d;
			}
			BoatloadBoatItem item = ((BoatloadBoatItem) stack.getItem());
			BoatloadBoat boat = item.getBoatEntity(level, x, y + adjustY, z, stack);
			boat.setBoatloadBoatType(item.type);
			boat.setYRot(direction.toYRot());
			level.addFreshEntity(boat);
			stack.shrink(1);
			return stack;
		}

		protected void playSound(BlockSource source) {
			source.getLevel().levelEvent(1000, source.getPos(), 0);
		}
	}
}