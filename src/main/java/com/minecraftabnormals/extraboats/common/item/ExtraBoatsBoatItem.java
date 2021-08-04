package com.minecraftabnormals.extraboats.common.item;

import com.minecraftabnormals.extraboats.common.entity.item.boat.ExtraBoatsBoatEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import java.util.List;
import java.util.function.Predicate;

public class ExtraBoatsBoatItem extends Item {
	private static final Predicate<Entity> ENTITY_PREDICATE = EntityPredicates.NO_SPECTATORS.and(Entity::isPickable);
	private final ExtraBoatsBoatEntity.BoatType type;

	public ExtraBoatsBoatItem(ExtraBoatsBoatEntity.BoatType typeIn, Item.Properties properties) {
		super(properties);
		this.type = typeIn;
	}

	@Override
	public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack itemstack = playerIn.getItemInHand(handIn);
		RayTraceResult raytraceresult = getPlayerPOVHitResult(worldIn, playerIn, RayTraceContext.FluidMode.ANY);
		if (raytraceresult.getType() == RayTraceResult.Type.MISS) {
			return ActionResult.pass(itemstack);
		} else {
			Vector3d vector3d = playerIn.getViewVector(1.0F);
			List<Entity> list = worldIn.getEntities(playerIn, playerIn.getBoundingBox().expandTowards(vector3d.scale(5.0D)).inflate(1.0D), ENTITY_PREDICATE);
			if (!list.isEmpty()) {
				Vector3d vector3d1 = playerIn.getEyePosition(1.0F);

				for (Entity entity : list) {
					AxisAlignedBB axisalignedbb = entity.getBoundingBox().inflate((double) entity.getPickRadius());
					if (axisalignedbb.contains(vector3d1)) {
						return ActionResult.pass(itemstack);
					}
				}
			}

			if (raytraceresult.getType() == RayTraceResult.Type.BLOCK) {
				ExtraBoatsBoatEntity boatentity = this.getBoatEntity(worldIn, raytraceresult, itemstack);
				boatentity.setModBoatType(this.type);
				boatentity.yRot = playerIn.yRot;
				if (!worldIn.noCollision(boatentity, boatentity.getBoundingBox().inflate(-0.1D))) {
					return ActionResult.fail(itemstack);
				} else {
					if (!worldIn.isClientSide) {
						worldIn.addFreshEntity(boatentity);
						if (!playerIn.abilities.instabuild) {
							itemstack.shrink(1);
						}
					}

					playerIn.awardStat(Stats.ITEM_USED.get(this));
					return ActionResult.success(itemstack);
				}
			} else {
				return ActionResult.pass(itemstack);
			}
		}
	}

	protected ExtraBoatsBoatEntity getBoatEntity(World worldIn, RayTraceResult raytraceresult, ItemStack itemStack) {
		return null;
	}

	public ExtraBoatsBoatEntity.BoatType getType() {
		return this.type;
	}
}