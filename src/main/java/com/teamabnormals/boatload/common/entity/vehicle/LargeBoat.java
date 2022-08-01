package com.teamabnormals.boatload.common.entity.vehicle;

import java.util.List;

import com.teamabnormals.boatload.core.registry.BoatloadEntityTypes;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PlayMessages;

public class LargeBoat extends BoatloadBoat {
	public LargeBoat(EntityType<? extends Boat> entityType, Level worldIn) {
		super(entityType, worldIn);
	}

	public LargeBoat(Level worldIn, double x, double y, double z) {
		this(BoatloadEntityTypes.LARGE_BOAT.get(), worldIn);
		this.setPos(x, y, z);
		this.setDeltaMovement(Vec3.ZERO);
		this.xo = x;
		this.yo = y;
		this.zo = z;
	}

	public LargeBoat(PlayMessages.SpawnEntity packet, Level worldIn) {
		super(BoatloadEntityTypes.LARGE_BOAT.get(), worldIn);
	}

	@Override
	protected boolean canAddPassenger(Entity passenger) {
		if (this.getPassengers().size() >= this.getMaxPassengers() - 1 && this.isPassengerBig(passenger)) {
			return false;
		} else {
			return passenger.getBbWidth() < 1.8F && super.canAddPassenger(passenger);
		}
	}

	@Override
	protected int getMaxPassengers() {
		return this.getBigPassengers() == 1 ? 3 : getBigPassengers() == 2 ? 2 : 4;
	}

	private boolean isPassengerBig(Entity passenger) {
		return passenger.getBbWidth() >= 1.375F;
	}

	private int getBigPassengers() {
		int i = 0;
		for (Entity passenger : this.getPassengers()) {
			if (this.isPassengerBig(passenger))
				i += 1;
		}
		return i;
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

		List<Entity> list = this.level.getEntities(this, this.getBoundingBox().inflate(0.2F, -0.01F, 0.2F), EntitySelector.pushableBy(this));
		if (!list.isEmpty()) {
			boolean flag = !this.level.isClientSide && !(this.getControllingPassenger() instanceof Player);

			for (Entity entity : list) {
				if (!entity.hasPassenger(this)) {
					if (flag && this.getPassengers().size() < 4 && !entity.isPassenger() && entity.getBbWidth() < 1.375F && entity instanceof LivingEntity && !(entity instanceof WaterAnimal) && !(entity instanceof Player)) {
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
				this.deltaRotation -= 0.75F;
			}

			if (this.inputRight) {
				this.deltaRotation += 0.75F;
			}

			if (this.inputRight != this.inputLeft && !this.inputUp && !this.inputDown) {
				f += 0.005F;
			}

			this.setYRot(this.getYRot() + this.deltaRotation);
			if (this.inputUp) {
				f += 0.03F;
			}

			if (this.inputDown) {
				f -= 0.005F;
			}

			this.setDeltaMovement(this.getDeltaMovement().add(Mth.sin(-this.getYRot() * ((float) Math.PI / 180F)) * f, 0.0D, Mth.cos(this.getYRot() * ((float) Math.PI / 180F)) * f));
			this.setPaddleState(this.inputRight && !this.inputLeft || this.inputUp, this.inputLeft && !this.inputRight || this.inputUp);
		}
	}

	@Override
	public void positionRider(Entity passenger) {
		if (this.hasPassenger(passenger)) {
			float f = -0.2F;
			float f1 = (float) ((this.isRemoved() ? (double) 0.01F : this.getPassengersRidingOffset()) + passenger.getMyRidingOffset());

			if (this.getPassengers().size() == 2) {
				int i = this.getPassengers().indexOf(passenger);
				int j = this.getBigPassengers();

				if (j == 1) {
					f = 0.5F - i * 1.4F;

					if (this.isPassengerBig(this.getPassengers().get(0))) {
						f -= 0.2F;
					} else if (this.isPassengerBig(this.getPassengers().get(1))) {
						f += 0.2F;
					}
				} else if (j == 2) {
					f = 0.7F - i * 1.8F;
				} else {
					f = 0.4F - i * 1.2F;
				}
			} else if (this.getPassengers().size() == 3) {
				int i = this.getPassengers().indexOf(passenger);
				f = 0.8F - i * 1.0F;

				if (this.isPassengerBig(this.getPassengers().get(0))) {
					if (i == 1) {
						f -= 0.1F;
					} else if (i == 2) {
						f += 0.1F;
					}
				} else if (this.isPassengerBig(this.getPassengers().get(1))) {
					if (i == 1) {
						f -= 0.2F;
					} else if (i == 2) {
						f -= 0.4F;
					}
					f += 0.2F;
				} else if (this.isPassengerBig(this.getPassengers().get(2))) {
					if (i == 1) {
						f += 0.1F;
					} else if (i == 2) {
						f -= 0.1F;
					}
					f += 0.2F;
				}
			} else if (this.getPassengers().size() > 3) {
				int i = this.getPassengers().indexOf(passenger);
				f = 1.0F - i * 0.8F;
			}

			if (this.isPassengerBig(passenger)) {
				f += 0.1F;
			} else if (passenger instanceof Animal) {
				f += 0.2F;
			}

			Vec3 vector3d = (new Vec3(f, 0.0D, 0.0D)).yRot(-this.getYRot() * ((float) Math.PI / 180F) - ((float) Math.PI / 2F));
			passenger.setPos(this.getX() + vector3d.x, this.getY() + (double) f1, this.getZ() + vector3d.z);
			passenger.setYRot(passenger.getYRot() + this.deltaRotation);
			passenger.setYHeadRot(passenger.getYHeadRot() + this.deltaRotation);
			this.clampRotation(passenger);
			if (passenger instanceof Animal && !this.isPassengerBig(passenger) && this.getPassengers().size() > 1) {
				int j = passenger.getId() % 2 == 0 ? 90 : 270;
				passenger.setYBodyRot(((Animal) passenger).yBodyRot + (float) j);
				passenger.setYHeadRot(passenger.getYHeadRot() + (float) j);
			}
		}
	}

	@Override
	public Item getDropItem() {
		return this.getBoatloadBoatType().largeBoat().get();
	}
}