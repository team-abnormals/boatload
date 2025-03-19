package com.teamabnormals.boatload.common.entity.vehicle;

import com.teamabnormals.boatload.common.inventory.FurnaceBoatMenu;
import com.teamabnormals.boatload.core.registry.BoatloadEntityTypes;
import net.minecraft.core.NonNullList;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.*;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.HasCustomInventoryScreen;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class FurnaceBoat extends BoatloadBoat implements HasCustomInventoryScreen, Container, MenuProvider {
	private static final EntityDataAccessor<Integer> BURN_TIME = SynchedEntityData.defineId(FurnaceBoat.class, EntityDataSerializers.INT);
	private int burnDuration;
	private NonNullList<ItemStack> itemStacks = NonNullList.withSize(1, ItemStack.EMPTY);
	private final ContainerData dataAccess = new ContainerData() {
		public int get(int index) {
			return switch (index) {
				case 0 -> FurnaceBoat.this.getBurnTime();
				case 1 -> FurnaceBoat.this.burnDuration;
				default -> 0;
			};
		}

		public void set(int index, int value) {
			switch (index) {
				case 0:
					FurnaceBoat.this.setBurnTime(value);
					break;
				case 1:
					FurnaceBoat.this.burnDuration = value;
					break;
			}
		}

		public int getCount() {
			return 2;
		}
	};

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

	@Override
	protected float getSinglePassengerXOffset() {
		return 0.15F;
	}

	@Override
	protected int getMaxPassengers() {
		return 1;
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(BURN_TIME, 0);
	}

	@Override
	protected void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("BurnTime", this.getBurnTime());
		compound.putInt("BurnDuration", this.burnDuration);
		ContainerHelper.saveAllItems(compound, this.itemStacks, this.registryAccess());
	}

	@Override
	protected void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		this.setBurnTime(compound.getInt("BurnTime"));
		this.burnDuration = compound.getInt("BurnDuration");
		ContainerHelper.loadAllItems(compound, this.itemStacks, this.registryAccess());
	}

	public void setBurnTime(int time) {
		this.entityData.set(BURN_TIME, time);
	}

	public int getBurnTime() {
		return this.entityData.get(BURN_TIME);
	}

	public boolean isLit() {
		return this.getBurnTime() > 0;
	}

	@Override
	public InteractionResult interact(Player player, InteractionHand hand) {
		if (this.canAddPassenger(player) && !player.isSecondaryUseActive()) {
			return super.interact(player, hand);
		} else {
			player.openMenu(this);
			this.gameEvent(GameEvent.CONTAINER_OPEN, player);
			return InteractionResult.sidedSuccess(player.level().isClientSide);
		}
	}

	private int getBurnDuration(ItemStack stack) {
		if (stack.isEmpty())
			return 0;
		else
			return stack.getBurnTime(RecipeType.SMELTING);
	}

	@Override
	public void tick() {
		super.tick();

		if (!this.level().isClientSide) {
			if (this.isLit())
				this.setBurnTime(this.getBurnTime() - 1);

			ItemStack itemstack = this.itemStacks.get(0);
			if (!this.isLit() && !itemstack.isEmpty()) {
				this.setBurnTime(this.getBurnDuration(itemstack));
				this.burnDuration = this.getBurnTime();
				if (this.isLit()) {
					if (itemstack.hasCraftingRemainingItem())
						this.itemStacks.set(0, itemstack.getCraftingRemainingItem());
					else {
						itemstack.shrink(1);
						if (itemstack.isEmpty())
							this.itemStacks.set(0, itemstack.getCraftingRemainingItem());
					}
				}
			}
		} else if (this.isLit()) {
			float f = (this.getYRot() - 90.0F) * ((float) Math.PI / 180F);
			float f1 = Mth.cos(f);
			float f2 = Mth.sin(f);

			if (this.level().isClientSide && this.random.nextInt(4) == 0)
				this.level().addParticle(ParticleTypes.LARGE_SMOKE, this.getX() + (double) f1 * 0.5D, this.getY() + 1.0D, this.getZ() + (double) f2 * 0.5D, 0.0D, 0.0D, 0.0D);

			if (this.random.nextInt(40) == 0)
				this.level().playLocalSound(this.getX() + (double) f1 * 0.5D, this.getY(), this.getZ() + (double) f2 * 0.5D, SoundEvents.FURNACE_FIRE_CRACKLE, this.getSoundSource(), 1.0F, 1.0F, false);
		}
	}

	@Override
	protected void controlBoat() {
		if (this.isVehicle()) {
			float f = 0.0F;
			if (this.inputLeft)
				--this.deltaRotation;

			if (this.inputRight)
				++this.deltaRotation;

			if (this.inputRight != this.inputLeft && !this.inputUp && !this.inputDown)
				f += 0.005F;

			this.setYRot(this.getYRot() + this.deltaRotation);
			if (this.inputUp) {
				f += 0.04F;
				if (this.isLit())
					f += 0.026F;
			}

			if (this.inputDown) {
				f -= 0.005F;
				if (this.isLit())
					f -= 0.01F;
			}

			this.setDeltaMovement(this.getDeltaMovement().add(Mth.sin(-this.getYRot() * ((float) Math.PI / 180F)) * f, 0.0D, Mth.cos(this.getYRot() * ((float) Math.PI / 180F)) * f));
			this.setPaddleState(this.inputRight && !this.inputLeft || this.inputUp, this.inputLeft && !this.inputRight || this.inputUp);

			if (this.isLit() && this.status == Boat.Status.IN_WATER) {
				float f1 = (this.getYRot() - 90.0F) * ((float) Math.PI / 180F);
				float f2 = Mth.cos(f1);
				float f3 = Mth.sin(f1);
				for (int i = 0; i < 10; ++i)
					this.level().addParticle(ParticleTypes.SPLASH, this.getX() + (double) f2 * 0.8D + (this.random.nextDouble() - 0.5D), this.getY() + 0.2F, this.getZ() + (double) f3 * 0.8D + (this.random.nextDouble() - 0.5D), 0.0D, 0.05D, 0.0D);
			}
		}
	}

	@Override
	public void destroy(DamageSource damageSource) {
		super.destroy(damageSource);
		if (this.level().getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS))
			Containers.dropContents(this.level(), this, this);
	}

	@Override
	public void remove(Entity.RemovalReason reason) {
		if (!this.level().isClientSide && reason.shouldDestroy())
			Containers.dropContents(this.level(), this, this);
		super.remove(reason);
	}

	@Override
	public Item getDropItem() {
		return this.getBoatloadBoatType().furnaceBoat().get();
	}

	@Override
	public ItemStack getPickResult() {
		return new ItemStack(this.getBoatloadBoatType().furnaceBoat().get());
	}

	@Override
	public int getContainerSize() {
		return 1;
	}

	@Override
	public boolean isEmpty() {
		for (ItemStack itemstack : this.itemStacks) {
			if (!itemstack.isEmpty())
				return false;
		}
		return true;
	}

	@Override
	public ItemStack getItem(int index) {
		return this.itemStacks.get(index);
	}

	@Override
	public ItemStack removeItem(int index, int p_18943_) {
		return ContainerHelper.removeItem(this.itemStacks, index, p_18943_);
	}

	@Override
	public ItemStack removeItemNoUpdate(int index) {
		ItemStack itemstack = this.itemStacks.get(index);
		if (itemstack.isEmpty()) {
			return ItemStack.EMPTY;
		} else {
			this.itemStacks.set(index, ItemStack.EMPTY);
			return itemstack;
		}
	}

	@Override
	public void setItem(int index, ItemStack stack) {
		this.itemStacks.set(index, stack);
		if (!stack.isEmpty() && stack.getCount() > this.getMaxStackSize())
			stack.setCount(this.getMaxStackSize());
	}

	@Override
	public void setChanged() {
	}

	@Override
	public boolean stillValid(Player player) {
		return !this.isRemoved() && this.position().closerThan(player.position(), 8.0D);
	}

	@Override
	public void clearContent() {
		this.itemStacks.clear();
	}

	@Override
	public void openCustomInventoryScreen(Player player) {
		player.openMenu(this);
		if (!player.level().isClientSide)
			this.gameEvent(GameEvent.CONTAINER_OPEN, player);
	}

	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int windowId, Inventory inventory, Player player) {
		return new FurnaceBoatMenu(windowId, inventory, this, this.dataAccess);
	}
}