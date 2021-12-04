package com.minecraftabnormals.extraboats.common.entity.item.boat;

import com.minecraftabnormals.extraboats.core.BoatHelper;
import com.minecraftabnormals.extraboats.core.other.EBTags;
import com.minecraftabnormals.extraboats.core.registry.EBEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

import java.util.List;

public class LargeBoatEntity extends ExtraBoatsBoatEntity {
	public LargeBoatEntity(EntityType<? extends BoatEntity> entityType, World worldIn) {
		super(entityType, worldIn);
	}

	public LargeBoatEntity(World worldIn, double x, double y, double z) {
		this(EBEntities.LARGE_BOAT.get(), worldIn);
		this.setPos(x, y, z);
		this.setDeltaMovement(Vector3d.ZERO);
		this.xo = x;
		this.yo = y;
		this.zo = z;
	}

	public LargeBoatEntity(FMLPlayMessages.SpawnEntity packet, World worldIn) {
		super(EBEntities.LARGE_BOAT.get(), worldIn);
	}

	@Override
	protected void dropBreakItems() {
		super.dropBreakItems();
		for (int i = 0; i < 3; ++i) {
			this.spawnAtLocation(this.getPlanks());
		}

		for (int j = 0; j < 2; ++j) {
			this.spawnAtLocation(Items.STICK);
		}
	}

	@Override
	public void tick() {
		super.tick();

		List<Entity> list = this.level.getEntities(this, this.getBoundingBox().inflate(0.2F, -0.01F, 0.2F), EntityPredicates.pushableBy(this));
		if (!list.isEmpty()) {
			boolean flag = !this.level.isClientSide && !(this.getControllingPassenger() instanceof PlayerEntity);

			for (int j = 0; j < list.size(); ++j) {
				Entity entity = list.get(j);
				if (!entity.hasPassenger(this)) {
					if (flag && this.getPassengers().size() < 4 && !entity.isPassenger() && entity.getBbWidth() < 1.375F && entity instanceof LivingEntity && !(entity instanceof WaterMobEntity) && !(entity instanceof PlayerEntity)) {
						entity.startRiding(this);
					} else {
						this.push(entity);
					}
				}
			}
		}
	}

	@Override
	protected void controlBoat() {
		if (this.isVehicle()) {
			float f = 0.0F;
			if (this.inputLeft) {
				--this.deltaRotation;
			}

			if (this.inputRight) {
				++this.deltaRotation;
			}

			if (this.inputRight != this.inputLeft && !this.inputUp && !this.inputDown) {
				f += 0.005F;
			}

			this.yRot += this.deltaRotation;
			if (this.inputUp) {
				f += 0.03F;
			}

			if (this.inputDown) {
				f -= 0.005F;
			}

			this.setDeltaMovement(this.getDeltaMovement().add(MathHelper.sin(-this.yRot * ((float) Math.PI / 180F)) * f, 0.0D, MathHelper.cos(this.yRot * ((float) Math.PI / 180F)) * f));
			this.setPaddleState(this.inputRight && !this.inputLeft || this.inputUp, this.inputLeft && !this.inputRight || this.inputUp);
		}
	}

	@Override
	public void positionRider(Entity passenger) {
		if (this.hasPassenger(passenger)) {
			float f = 0.0F;
			float f1 = (float) ((this.removed ? (double) 0.01F : this.getPassengersRidingOffset()) + passenger.getMyRidingOffset());

			if (this.getPassengers().size() == 2) {
				int i = this.getPassengers().indexOf(passenger);
				if (i == 0) {
					f = 0.4F;
				} else {
					f = -0.8F;
				}
			} else if (this.getPassengers().size() == 3) {
				int i = this.getPassengers().indexOf(passenger);
				if (i == 0) {
					f = 0.8F;
				} else if (i == 1) {
					f = -0.1F;
				} else {
					f = -1.0F;
				}
			} else if (this.getPassengers().size() > 3) {
				int i = this.getPassengers().indexOf(passenger);

				Entity firstpassenger = this.getPassengers().get(0);
				boolean flag = firstpassenger.getType().is(EBTags.SITTING_MOBS) || firstpassenger instanceof PlayerEntity;

				if (i == 0) {
					f = flag ? 0.875F : 1.0F;
				} else {
					if (i == 1) {
						f = 0.2F;
					} else if (i == 2) {
						f = -0.55F;
					} else {
						f = -1.3F;
					}

					f += flag ? 0.0F : 0.05F;
				}
			}

			if (passenger instanceof AnimalEntity) {
				f = (float) ((double) f + 0.1D);
			}

			Vector3d vector3d = (new Vector3d(f, 0.0D, 0.0D)).yRot(-this.yRot * ((float) Math.PI / 180F) - ((float) Math.PI / 2F));
			passenger.setPos(this.getX() + vector3d.x, this.getY() + (double) f1, this.getZ() + vector3d.z);
			passenger.yRot += this.deltaRotation;
			passenger.setYHeadRot(passenger.getYHeadRot() + this.deltaRotation);
			this.clampRotation(passenger);
			if (passenger instanceof AnimalEntity && this.getPassengers().size() > 1) {
				int j = passenger.getId() % 2 == 0 ? 90 : 270;
				passenger.setYBodyRot(((AnimalEntity) passenger).yBodyRot + (float) j);
				passenger.setYHeadRot(passenger.getYHeadRot() + (float) j);
			}
		}
	}

	@Override
	public Item getDropItem() {
		return BoatHelper.getLargeBoatItem(this.getModBoatType());
	}

	@Override
	protected boolean canAddPassenger(Entity passenger) {
		return this.getPassengers().size() < 4 && passenger.getBbWidth() < 1.375F && !this.isEyeInFluid(FluidTags.WATER);
	}
}