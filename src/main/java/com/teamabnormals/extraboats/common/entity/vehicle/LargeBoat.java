package com.teamabnormals.extraboats.common.entity.vehicle;

import com.teamabnormals.extraboats.core.registry.EBEntityTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.IllagerModel;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.tags.FluidTags;
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

import java.util.List;

public class LargeBoat extends EBBoat {
	public LargeBoat(EntityType<? extends Boat> entityType, Level worldIn) {
		super(entityType, worldIn);
	}

	public LargeBoat(Level worldIn, double x, double y, double z) {
		this(EBEntityTypes.LARGE_BOAT.get(), worldIn);
		this.setPos(x, y, z);
		this.setDeltaMovement(Vec3.ZERO);
		this.xo = x;
		this.yo = y;
		this.zo = z;
	}

	public LargeBoat(PlayMessages.SpawnEntity packet, Level worldIn) {
		super(EBEntityTypes.LARGE_BOAT.get(), worldIn);
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
				--this.deltaRotation;
			}

			if (this.inputRight) {
				++this.deltaRotation;
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
			float f = 0.0F;
			float f1 = (float) ((this.isRemoved() ? (double) 0.01F : this.getPassengersRidingOffset()) + passenger.getMyRidingOffset());

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

				boolean flag = false;
				Entity firstpassenger = this.getPassengers().get(0);
				if (Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(firstpassenger) instanceof LivingEntityRenderer livingEntityRenderer) {
					flag = livingEntityRenderer.getModel() instanceof HumanoidModel || livingEntityRenderer.getModel() instanceof IllagerModel;
				}


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

			if (passenger instanceof Animal) {
				f = (float) ((double) f + 0.1D);
			}

			Vec3 vector3d = (new Vec3(f, 0.0D, 0.0D)).yRot(-this.getYRot() * ((float) Math.PI / 180F) - ((float) Math.PI / 2F));
			passenger.setPos(this.getX() + vector3d.x, this.getY() + (double) f1, this.getZ() + vector3d.z);
			passenger.setYRot(this.getYRot() + this.deltaRotation);
			passenger.setYHeadRot(passenger.getYHeadRot() + this.deltaRotation);
			this.clampRotation(passenger);
			if (passenger instanceof Animal && this.getPassengers().size() > 1) {
				int j = passenger.getId() % 2 == 0 ? 90 : 270;
				passenger.setYBodyRot(((Animal) passenger).yBodyRot + (float) j);
				passenger.setYHeadRot(passenger.getYHeadRot() + (float) j);
			}
		}
	}

	@Override
	public Item getDropItem() {
		return this.getEBBoatType().getLargeBoat();
	}

	@Override
	protected boolean canAddPassenger(Entity passenger) {
		return this.getPassengers().size() < 4 && passenger.getBbWidth() < 1.375F && !this.isEyeInFluid(FluidTags.WATER);
	}
}