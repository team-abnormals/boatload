package com.minecraftabnormals.extraboats.common.entity.item.boat;

import com.minecraftabnormals.extraboats.core.BoatHelper;
import com.minecraftabnormals.extraboats.core.registry.EBEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FurnaceBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

import javax.annotation.Nullable;

public class FurnaceBoatEntity extends ExtraBoatsBoatEntity {
	private static final DataParameter<Integer> FUEL = EntityDataManager.defineId(FurnaceBoatEntity.class, DataSerializers.INT);
	private static final Ingredient FUEL_ITEMS = Ingredient.of(Items.COAL, Items.CHARCOAL);

	public FurnaceBoatEntity(EntityType<? extends BoatEntity> entityType, World worldIn) {
		super(entityType, worldIn);
	}

	public FurnaceBoatEntity(World worldIn, double x, double y, double z) {
		this(EBEntities.FURNACE_BOAT.get(), worldIn);
		this.setPos(x, y, z);
		this.setDeltaMovement(Vector3d.ZERO);
		this.xo = x;
		this.yo = y;
		this.zo = z;
	}

	public FurnaceBoatEntity(FMLPlayMessages.SpawnEntity packet, World worldIn) {
		super(EBEntities.FURNACE_BOAT.get(), worldIn);
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
	public ActionResultType interact(PlayerEntity player, Hand hand) {
		if (player.isShiftKeyDown()) {
			ItemStack itemstack = player.getItemInHand(hand);
			if (FUEL_ITEMS.test(itemstack) && this.getFuel() + 3600 <= 32000) {
				if (!player.abilities.instabuild) {
					itemstack.shrink(1);
				}

				this.setFuel(this.getFuel() + 3600);
			}

			return ActionResultType.sidedSuccess(this.level.isClientSide);
		} else {
			return super.interact(player, hand);
		}
	}

	@Override
	public void tick() {
		super.tick();
		if (this.getFuel() > 0) {
			this.setFuel(this.getFuel() - 1);

			float f = (this.yRot - 90.0F) * ((float) Math.PI / 180F);
			float f1 = MathHelper.cos(f);
			float f2 = MathHelper.sin(f);

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

			this.yRot += this.deltaRotation;
			if (this.inputUp) {
				f += 0.04F;
			}

			if (this.inputDown) {
				f -= 0.021F;
			}

			if (this.getFuel() > 0) {
				f += 0.026F;
			}

			this.setDeltaMovement(this.getDeltaMovement().add(MathHelper.sin(-this.yRot * ((float) Math.PI / 180F)) * f, 0.0D, MathHelper.cos(this.yRot * ((float) Math.PI / 180F)) * f));
			this.setPaddleState(this.inputRight && !this.inputLeft || this.inputUp, this.inputLeft && !this.inputRight || this.inputUp);

			if (this.getFuel() > 0 && this.status == BoatEntity.Status.IN_WATER) {
				float f1 = (this.yRot - 90.0F) * ((float) Math.PI / 180F);
				float f2 = MathHelper.cos(f1);
				float f3 = MathHelper.sin(f1);
				for (int i = 0; i < 10; ++i) {
					this.level.addParticle(ParticleTypes.SPLASH, this.getX() + (double) f2 * 0.8D + (this.random.nextDouble() - 0.5D), this.getY() + 0.2F, this.getZ() + (double) f3 * 0.8D + (this.random.nextDouble() - 0.5D), 0.0D, 0.05D, 0.0D);
				}
			}
		}
	}

	@Override
	protected void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("Fuel", this.getFuel());
	}

	@Override
	protected void readAdditionalSaveData(CompoundNBT compound) {
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
	public Item getDropItem() {
		return BoatHelper.getFurnaceBoatItem(this.getModBoatType());
	}

	@Override
	public Item getItemDropBoat() {
		return BoatHelper.getBoatItem(this.getModBoatType());
	}

	@Override
	public BlockState getDisplayTile() {
		return Blocks.FURNACE.defaultBlockState().setValue(FurnaceBlock.FACING, Direction.SOUTH).setValue(FurnaceBlock.LIT, Boolean.valueOf(this.getFuel() > 0));
	}

	@Override
	public void positionRider(Entity passenger) {
		if (this.hasPassenger(passenger)) {
			float f = passenger instanceof AnimalEntity ? 0.4F : 0.2F;
			float f1 = (float) ((this.removed ? (double) 0.01F : this.getPassengersRidingOffset()) + passenger.getMyRidingOffset());

			Vector3d vector3d = (new Vector3d(f, 0.0D, 0.0D)).yRot(-this.yRot * ((float) Math.PI / 180F) - ((float) Math.PI / 2F));
			passenger.setPos(this.getX() + vector3d.x, this.getY() + (double) f1, this.getZ() + vector3d.z);
			passenger.yRot += this.deltaRotation;
			passenger.setYHeadRot(passenger.getYHeadRot() + this.deltaRotation);
			this.clampRotation(passenger);
			if (passenger instanceof AnimalEntity) {
				int j = passenger.getId() % 2 == 0 ? 90 : 270;
				passenger.setYBodyRot(((AnimalEntity) passenger).yBodyRot + (float) j);
				passenger.setYHeadRot(passenger.getYHeadRot() + (float) j);
			}
		}
	}

	@Override
	protected boolean canAddPassenger(Entity passenger) {
		return !this.isVehicle() && !this.isEyeInFluid(FluidTags.WATER);
	}
}