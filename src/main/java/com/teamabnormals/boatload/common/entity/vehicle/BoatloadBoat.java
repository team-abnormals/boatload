package com.teamabnormals.boatload.common.entity.vehicle;

import com.teamabnormals.blueprint.common.world.storage.tracking.IDataManager;
import com.teamabnormals.boatload.core.api.BoatloadBoatType;
import com.teamabnormals.boatload.core.other.BoatloadTrackedData;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.network.NetworkHooks;

public abstract class BoatloadBoat extends Boat {
	private static final EntityDataAccessor<String> BOAT_TYPE = SynchedEntityData.defineId(BoatloadBoat.class, EntityDataSerializers.STRING);

	public BoatloadBoat(EntityType<? extends Boat> entityType, Level worldIn) {
		super(entityType, worldIn);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(BOAT_TYPE, BoatloadBoatType.OAK.registryName().toString());
	}

	public void setBoatloadBoatType(BoatloadBoatType boatType) {
		this.entityData.set(BOAT_TYPE, boatType.registryName().toString());
	}

	public BoatloadBoatType getBoatloadBoatType() {
		return BoatloadBoatType.getType(new ResourceLocation(this.entityData.get(BOAT_TYPE)));
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
			this.setBoatloadBoatType(BoatloadBoatType.getType(new ResourceLocation(compound.getString("Type"))));
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
						this.discard();
						if (this.level().getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
							this.dropBreakItems();
						}
					}
				}

				this.fallDistance = 0.0F;
			} else if (!this.level().getFluidState(this.blockPosition().below()).is(FluidTags.WATER) && y < 0.0D) {
				this.fallDistance = (float) ((double) this.fallDistance - y);
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
	public void destroy(DamageSource source) {
		this.spawnAtLocation(this.getDropItem());
		this.spawnAtLocation(((IDataManager) this).getValue(BoatloadTrackedData.BANNER));
	}

	@Override
	public double getPassengersRidingOffset() {
		return this.getBoatloadBoatType().raft() ? 0.25D : -0.1D;
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}