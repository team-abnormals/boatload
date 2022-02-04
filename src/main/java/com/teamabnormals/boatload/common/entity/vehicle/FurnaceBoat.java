package com.teamabnormals.boatload.common.entity.vehicle;

import com.teamabnormals.boatload.core.registry.BoatloadEntityTypes;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FurnaceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PlayMessages;

import javax.annotation.Nullable;

public class FurnaceBoat extends BoatloadBoat {
	private static final EntityDataAccessor<Integer> FUEL = SynchedEntityData.defineId(FurnaceBoat.class, EntityDataSerializers.INT);
	private static final Ingredient FUEL_ITEMS = Ingredient.of(Items.COAL, Items.CHARCOAL);

	public FurnaceBoat(EntityType<? extends Boat> entityType, Level worldIn) {
		super(entityType, worldIn);
	}

	public FurnaceBoat(Level worldIn, double x, double y, double z) {
		this(BoatloadEntityTypes.FURNACE_BOAT.get(), worldIn);
		this.setPos(x, y, z);
		this.setDeltaMovement(Vec3.ZERO);
		this.xo = x;
		this.yo = y;
		this.zo = z;
	}

	public FurnaceBoat(PlayMessages.SpawnEntity packet, Level worldIn) {
		super(BoatloadEntityTypes.FURNACE_BOAT.get(), worldIn);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(FUEL, 0);
	}

	@Override
	protected void dropBreakItems() {
		super.dropBreakItems();
		this.spawnAtLocation(Blocks.FURNACE);
	}

	@Override
	public void killBoat() {
		super.killBoat();
		this.spawnAtLocation(Blocks.FURNACE);
	}

	@Override
	public InteractionResult interact(Player player, InteractionHand hand) {
		if (player.isShiftKeyDown()) {
			ItemStack itemstack = player.getItemInHand(hand);
			if (FUEL_ITEMS.test(itemstack) && this.getFuel() + 3600 <= 32000) {
				if (!player.getAbilities().instabuild) {
					itemstack.shrink(1);
				}

				this.setFuel(this.getFuel() + 3600);
			}

			return InteractionResult.sidedSuccess(this.level.isClientSide);
		} else {
			return super.interact(player, hand);
		}
	}

	@Override
	public void tick() {
		super.tick();
		if (this.getFuel() > 0) {
			this.setFuel(this.getFuel() - 1);

			float f = (this.getYRot() - 90.0F) * ((float) Math.PI / 180F);
			float f1 = Mth.cos(f);
			float f2 = Mth.sin(f);

			if (this.level.isClientSide && this.random.nextInt(4) == 0) {
				this.level.addParticle(ParticleTypes.LARGE_SMOKE, this.getX() + (double) f1 * 0.5D, this.getY() + 1.0D, this.getZ() + (double) f2 * 0.5D, 0.0D, 0.0D, 0.0D);
			}

			if (this.random.nextInt(40) == 0) {
				this.level.playLocalSound(this.getX() + (double) f1 * 0.5D, this.getY(), this.getZ() + (double) f2 * 0.5D, SoundEvents.FURNACE_FIRE_CRACKLE, this.getSoundSource(), 1.0F, 1.0F, false);
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
				f += 0.04F;
			}

			if (this.inputDown) {
				f -= 0.021F;
			}

			if (this.getFuel() > 0) {
				f += 0.026F;
			}

			this.setDeltaMovement(this.getDeltaMovement().add(Mth.sin(-this.getYRot() * ((float) Math.PI / 180F)) * f, 0.0D, Mth.cos(this.getYRot() * ((float) Math.PI / 180F)) * f));
			this.setPaddleState(this.inputRight && !this.inputLeft || this.inputUp, this.inputLeft && !this.inputRight || this.inputUp);

			if (this.getFuel() > 0 && this.status == Boat.Status.IN_WATER) {
				float f1 = (this.getYRot() - 90.0F) * ((float) Math.PI / 180F);
				float f2 = Mth.cos(f1);
				float f3 = Mth.sin(f1);
				for (int i = 0; i < 10; ++i) {
					this.level.addParticle(ParticleTypes.SPLASH, this.getX() + (double) f2 * 0.8D + (this.random.nextDouble() - 0.5D), this.getY() + 0.2F, this.getZ() + (double) f3 * 0.8D + (this.random.nextDouble() - 0.5D), 0.0D, 0.05D, 0.0D);
				}
			}
		}
	}

	@Override
	protected void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("Fuel", this.getFuel());
	}

	@Override
	protected void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		this.setFuel(compound.getInt("Fuel"));
	}

	private void setFuel(@Nullable int fuel) {
		this.entityData.set(FUEL, fuel);
	}

	@Nullable
	public int getFuel() {
		return this.entityData.get(FUEL);
	}

	@Override
	public ItemStack getPickResult() {
		return new ItemStack(this.getBoatloadBoatType().furnaceBoat().get());
	}

	@Override
	public BlockState getDisplayTile() {
		return Blocks.FURNACE.defaultBlockState().setValue(FurnaceBlock.FACING, Direction.SOUTH).setValue(FurnaceBlock.LIT, this.getFuel() > 0);
	}

	@Override
	public void positionRider(Entity passenger) {
		if (this.hasPassenger(passenger)) {
			float f = passenger instanceof Animal ? 0.4F : 0.2F;
			float f1 = (float) ((this.isRemoved() ? (double) 0.01F : this.getPassengersRidingOffset()) + passenger.getMyRidingOffset());

			Vec3 vector3d = (new Vec3(f, 0.0D, 0.0D)).yRot(-this.getYRot() * ((float) Math.PI / 180F) - ((float) Math.PI / 2F));
			passenger.setPos(this.getX() + vector3d.x, this.getY() + (double) f1, this.getZ() + vector3d.z);
			this.setYRot(this.getYRot() + this.deltaRotation);
			passenger.setYHeadRot(passenger.getYHeadRot() + this.deltaRotation);
			this.clampRotation(passenger);
			if (passenger instanceof Animal) {
				int j = passenger.getId() % 2 == 0 ? 90 : 270;
				passenger.setYBodyRot(((Animal) passenger).yBodyRot + (float) j);
				passenger.setYHeadRot(passenger.getYHeadRot() + (float) j);
			}
		}
	}

	@Override
	protected boolean canAddPassenger(Entity passenger) {
		return !this.isVehicle() && !this.isEyeInFluid(FluidTags.WATER);
	}
}