package com.teamabnormals.boatload.common.entity.vehicle;

import com.teamabnormals.boatload.core.registry.BoatloadEntityTypes;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.monster.hoglin.HoglinBase;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PlayMessages;

import java.util.List;

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
		if (this.getPassengers().size() >= this.getMaxPassengers() - 1 && isPassengerBig(passenger)) {
			return false;
		} else {
			return passenger.getBbWidth() < 1.8F && super.canAddPassenger(passenger);
		}
	}

	@Override
	protected int getMaxPassengers() {
		return this.getBigPassengers() == 1 ? 3 : getBigPassengers() == 2 ? 2 : 4;
	}

	private static boolean isPassengerBig(Entity passenger) {
		return passenger.getBbWidth() >= 1.375F;
	}

	private int getBigPassengers() {
		int i = 0;
		for (Entity passenger : this.getPassengers()) {
			if (isPassengerBig(passenger))
				i += 1;
		}
		return i;
	}

	@Override
	protected void dropBreakItems() {
		super.dropBreakItems();
		super.dropBreakItems();
	}

	@Override
	public void tick() {
		super.tick();

		List<Entity> list = this.level().getEntities(this, this.getBoundingBox().inflate(0.2F, -0.01F, 0.2F), EntitySelector.pushableBy(this));
		if (!list.isEmpty()) {
			boolean flag = !this.level().isClientSide && !(this.getControllingPassenger() instanceof Player);

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

	public static boolean isAnimalEsque(Entity passenger) {
		return passenger instanceof Animal || passenger instanceof HoglinBase || (isPassengerBig(passenger) && passenger instanceof Spider);
	}

	@Override
	public void positionRider(Entity passenger, Entity.MoveFunction function) {
		if (this.hasPassenger(passenger)) {
			float x = -0.2F;
			float z = 0.0F;
			int index = this.getPassengers().indexOf(passenger);
			int bigPassengers = this.getBigPassengers();
			boolean raft = this.getBoatloadBoatType().raft();

			boolean rotate = false;
			if (raft) {
				if (this.getPassengers().size() == 2) {
					Entity otherPassenger = this.getPassengers().get(1 - index);
					if (bigPassengers == 1 || (bigPassengers == 2 && (!isAnimalEsque(passenger) || !isAnimalEsque(otherPassenger)))) {
						rotate = isAnimalEsque(passenger);
						if (index == 0) {
							x += 0.7F;
						} else {
							x -= 0.5F;
						}
					} else {
						if (index == 0) {
							z += 0.6F;
						} else {
							z -= 0.6F;
						}
					}
				} else if (this.getPassengers().size() == 3) {
					if (bigPassengers == 1) {
						int bigIndex = 0;
						for (int i = 0; i < this.getPassengers().size(); i++) {
							if (isPassengerBig(this.getPassengers().get(i))) {
								bigIndex = i;
								break;
							}
						}
						if (isPassengerBig(passenger)) {
							rotate = isAnimalEsque(passenger);
							x -= 0.5F;
						} else {
							x += 0.7F;
							if (bigIndex == 0 && index == 1 || bigIndex > 0 && index == 0) {
								z += 0.6F;
							} else {
								z -= 0.6F;
							}
						}
					} else {
						if (index == 0) {
							x += 0.6F;
							rotate = isAnimalEsque(passenger);
						} else {
							x -= 0.5F;
							if (index == 1) {
								z += 0.6F;
							} else {
								z -= 0.6F;
							}
						}
					}
				} else if (this.getPassengers().size() > 3) {
					double mod = isAnimalEsque(passenger) ? 0.2F : 0.F;
					x += (index < 2 ? 1 : -1) * (0.5F + mod);
					z += (index % 2 == 0 ? 1 : -1) * 0.5F;
				}

				if (isAnimalEsque(passenger)) {
					x += 0.2F;
				}
			} else {
				if (this.getPassengers().size() == 2) {
					if (bigPassengers == 1) {
						x = 0.5F - index * 1.4F;

						if (isPassengerBig(this.getPassengers().get(0))) {
							x -= 0.2F;
						} else if (isPassengerBig(this.getPassengers().get(1))) {
							x += 0.2F;
						}
					} else if (bigPassengers == 2) {
						x = 0.7F - index * 1.8F;
					} else {
						x = 0.4F - index * 1.2F;
					}
				} else if (this.getPassengers().size() == 3) {
					int i = this.getPassengers().indexOf(passenger);
					x = 0.8F - i * 1.0F;

					if (isPassengerBig(this.getPassengers().get(0))) {
						if (i == 1) {
							x -= 0.1F;
						} else if (i == 2) {
							x += 0.1F;
						}
					} else if (isPassengerBig(this.getPassengers().get(1))) {
						if (i == 1) {
							x -= 0.2F;
						} else if (i == 2) {
							x -= 0.4F;
						}
						x += 0.2F;
					} else if (isPassengerBig(this.getPassengers().get(2))) {
						if (i == 1) {
							x += 0.1F;
						} else if (i == 2) {
							x -= 0.1F;
						}
						x += 0.2F;
					}
				} else if (this.getPassengers().size() > 3) {
					int i = this.getPassengers().indexOf(passenger);
					x = 1.0F - i * 0.8F;
				}

				if (isPassengerBig(passenger)) {
					x += 0.1F;
				} else if (isAnimalEsque(passenger)) {
					x += 0.2F;
				}
			}

			float f1 = (float) ((this.isRemoved() ? (double) 0.01F : this.getPassengersRidingOffset()) + passenger.getMyRidingOffset());
			Vec3 vector3d = (new Vec3(x, 0.0D, z)).yRot(-this.getYRot() * ((float) Math.PI / 180F) - ((float) Math.PI / 2F));
			function.accept(passenger, this.getX() + vector3d.x, this.getY() + (double) f1, this.getZ() + vector3d.z);
			passenger.setYRot(passenger.getYRot() + this.deltaRotation);
			passenger.setYHeadRot(passenger.getYHeadRot() + this.deltaRotation);
			this.clampRotation(passenger);
			if (passenger instanceof LivingEntity living && (rotate || (isAnimalEsque(passenger) && !isPassengerBig(passenger) && this.getPassengers().size() > 1 && !raft))) {
				int j = passenger.getId() % 2 == 0 ? 90 : 270;
				passenger.setYBodyRot(living.yBodyRot + (float) j);
				passenger.setYHeadRot(passenger.getYHeadRot() + (float) j);
			}
		}
	}

	@Override
	public Item getDropItem() {
		return this.getBoatloadBoatType().largeBoat().get();
	}
}