package com.teamabnormals.boatload.common.entity.vehicle;

import com.teamabnormals.boatload.core.api.BoatloadBoatType;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public abstract class BoatloadBoat extends Boat {
	private static final EntityDataAccessor<String> BOAT_TYPE = SynchedEntityData.defineId(BoatloadBoat.class, EntityDataSerializers.STRING);

	public BoatloadBoat(EntityType<? extends Boat> entityType, Level worldIn) {
		super(entityType, worldIn);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(BOAT_TYPE, BoatloadBoatType.OAK.registryName().toString());
	}

	public void setBoatloadBoatType(BoatloadBoatType boatType) {
		this.entityData.set(BOAT_TYPE, boatType.registryName().toString());
	}

	public BoatloadBoatType getBoatloadBoatType() {
		return BoatloadBoatType.getType(ResourceLocation.parse(this.entityData.get(BOAT_TYPE)));
	}

	@Override
	protected void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putString("Type", this.getBoatloadBoatType().registryName().toString());
	}

	@Override
	protected void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains("Type", 8)) {
			this.setBoatloadBoatType(BoatloadBoatType.getType(ResourceLocation.parse(compound.getString("Type"))));
		}
	}

	@Override
	protected void checkFallDamage(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
		this.lastYd = this.getDeltaMovement().y;
		if (!this.isPassenger()) {
			if (onGroundIn) {
				if (this.fallDistance > 3.0F) {
					if (this.status != Boat.Status.ON_LAND) {
						this.fallDistance = 0.0F;
						return;
					}

					this.causeFallDamage(this.fallDistance, 1.0F, this.damageSources().fall());
					if (!this.level().isClientSide && !this.isRemoved()) {
						this.kill();
						if (this.level().getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
							this.dropBreakItems();
						}
					}
				}

				this.resetFallDistance();
			} else if (!this.canBoatInFluid(this.level().getFluidState(this.blockPosition().below())) && y < 0.0D) {
				this.fallDistance -= (float) y;
			}
		}
	}

	protected void dropBreakItems() {
		for (int i = 0; i < 3; ++i) {
			this.spawnAtLocation(this.getBoatloadBoatType().planks().get());
		}

		for (int j = 0; j < 2; ++j) {
			this.spawnAtLocation(Items.STICK);
		}
	}

	@Override
	public Item getDropItem() {
		return this.getBoatloadBoatType().boat().get();
	}

	@Override
	protected Vec3 getPassengerAttachmentPoint(Entity entity, EntityDimensions dimensions, float partialTick) {
		float f = this.getSinglePassengerXOffset();
		if (this.getPassengers().size() > 1) {
			int i = this.getPassengers().indexOf(entity);
			if (i == 0) {
				f = 0.2F;
			} else {
				f = -0.6F;
			}

			if (entity instanceof Animal) {
				f += 0.2F;
			}
		}

		return new Vec3(0.0, this.getBoatloadBoatType().raft() ? (double) (dimensions.height() * 0.8888889F) : (double) (dimensions.height() / 3.0F), f).yRot(-this.getYRot() * (float) (Math.PI / 180.0));
	}
}