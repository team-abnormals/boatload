package com.teamabnormals.boatload.common.entity.vehicle;

import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.wrapper.InvWrapper;

import javax.annotation.Nullable;

public abstract class AbstractContainerBoat extends BLBoat implements Container, MenuProvider {
	private NonNullList<ItemStack> boatContainerItems = NonNullList.withSize(36, ItemStack.EMPTY);
	private boolean dropContentsWhenDead = true;
	@Nullable
	private ResourceLocation lootTable;
	private long lootTableSeed;

	public AbstractContainerBoat(EntityType<? extends Boat> entityType, Level worldIn) {
		super(entityType, worldIn);
	}

	@Override
	protected void dropBreakItems() {
		super.dropBreakItems();
		if (!this.level.isClientSide && this.dropContentsWhenDead && this.level.getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
			Containers.dropContents(this.level, this, this);
		}
	}

	@Override
	public void killBoat() {
		super.killBoat();
		if (!this.level.isClientSide && this.dropContentsWhenDead && this.level.getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
			Containers.dropContents(this.level, this, this);
		}
	}

	@Override
	public boolean isEmpty() {
		for (ItemStack itemstack : this.boatContainerItems) {
			if (!itemstack.isEmpty()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public ItemStack getItem(int index) {
		this.unpackLootTable(null);
		return this.boatContainerItems.get(index);
	}

	@Override
	public ItemStack removeItem(int index, int count) {
		this.unpackLootTable(null);
		return ContainerHelper.removeItem(this.boatContainerItems, index, count);
	}

	@Override
	public ItemStack removeItemNoUpdate(int index) {
		this.unpackLootTable(null);
		ItemStack itemstack = this.boatContainerItems.get(index);
		if (itemstack.isEmpty()) {
			return ItemStack.EMPTY;
		} else {
			this.boatContainerItems.set(index, ItemStack.EMPTY);
			return itemstack;
		}
	}

	@Override
	public void setItem(int index, ItemStack stack) {
		this.unpackLootTable(null);
		this.boatContainerItems.set(index, stack);
		if (!stack.isEmpty() && stack.getCount() > this.getMaxStackSize()) {
			stack.setCount(this.getMaxStackSize());
		}
	}

	@Override
	public SlotAccess getSlot(final int slot) {
		return slot >= 0 && slot < this.getContainerSize() ? new SlotAccess() {
			public ItemStack get() {
				return AbstractContainerBoat.this.getItem(slot);
			}

			public boolean set(ItemStack stack) {
				AbstractContainerBoat.this.setItem(slot, stack);
				return true;
			}
		} : super.getSlot(slot);
	}

	@Override
	public void setChanged() {
	}

	@Override
	public boolean stillValid(Player player) {
		if (this.isRemoved()) {
			return false;
		} else {
			return !(player.distanceToSqr(this) > 64.0D);
		}
	}

	public void remove(Entity.RemovalReason reason) {
		if (!this.level.isClientSide && reason.shouldDestroy()) {
			Containers.dropContents(this.level, this, this);
		}

		super.remove(reason);
	}

	@Override
	protected void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		if (this.lootTable != null) {
			compound.putString("LootTable", this.lootTable.toString());
			if (this.lootTableSeed != 0L) {
				compound.putLong("LootTableSeed", this.lootTableSeed);
			}
		} else {
			ContainerHelper.saveAllItems(compound, this.boatContainerItems);
		}
	}

	@Override
	protected void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		this.boatContainerItems = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		if (compound.contains("LootTable", 8)) {
			this.lootTable = new ResourceLocation(compound.getString("LootTable"));
			this.lootTableSeed = compound.getLong("LootTableSeed");
		} else {
			ContainerHelper.loadAllItems(compound, this.boatContainerItems);
		}
	}

	@Override
	public InteractionResult interact(Player player, InteractionHand hand) {
		if (player.isShiftKeyDown()) {
			player.openMenu(this);
			return InteractionResult.sidedSuccess(this.level.isClientSide);
		} else {
			return super.interact(player, hand);
		}
	}

	public void unpackLootTable(@Nullable Player player) {
		if (this.lootTable != null && this.level.getServer() != null) {
			LootTable loottable = this.level.getServer().getLootTables().get(this.lootTable);
			this.lootTable = null;
			LootContext.Builder lootcontext$builder = (new LootContext.Builder((ServerLevel) this.level)).withParameter(LootContextParams.ORIGIN, this.position()).withOptionalRandomSeed(this.lootTableSeed);
			lootcontext$builder.withParameter(LootContextParams.KILLER_ENTITY, this);
			if (player != null) {
				lootcontext$builder.withLuck(player.getLuck()).withParameter(LootContextParams.THIS_ENTITY, player);
			}

			loottable.fill(this, lootcontext$builder.create(LootContextParamSets.CHEST));
		}
	}

	@Override
	public void clearContent() {
		this.unpackLootTable(null);
		this.boatContainerItems.clear();
	}

	public void setLootTable(ResourceLocation lootTableIn, long lootTableSeedIn) {
		this.lootTable = lootTableIn;
		this.lootTableSeed = lootTableSeedIn;
	}

	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int p_createMenu_1_, Inventory p_createMenu_2_, Player p_createMenu_3_) {
		if (this.lootTable != null && p_createMenu_3_.isSpectator()) {
			return null;
		} else {
			this.unpackLootTable(p_createMenu_2_.player);
			return this.createMenu(p_createMenu_1_, p_createMenu_2_);
		}
	}

	protected abstract AbstractContainerMenu createMenu(int id, Inventory playerInventoryIn);

	private LazyOptional<?> itemHandler = LazyOptional.of(() -> new InvWrapper(this));

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable net.minecraft.core.Direction facing) {
		if (this.isAlive() && capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return itemHandler.cast();
		return super.getCapability(capability, facing);
	}

	@Override
	public void invalidateCaps() {
		super.invalidateCaps();
		itemHandler.invalidate();
	}

	@Override
	public void reviveCaps() {
		super.reviveCaps();
		itemHandler = LazyOptional.of(() -> new InvWrapper(this));
	}

	public void dropContentsWhenDead(boolean value) {
		this.dropContentsWhenDead = value;
	}

	@Override
	public Item getItemDropBoat() {
		return this.getBLBoatType().getChestBoat();
	}

	@Override
	public void positionRider(Entity passenger) {
		if (this.hasPassenger(passenger)) {
			float f = passenger instanceof Animal ? 0.4F : 0.2F;
			float f1 = (float) ((this.isRemoved() ? (double) 0.01F : this.getPassengersRidingOffset()) + passenger.getMyRidingOffset());

			Vec3 vector3d = (new Vec3(f, 0.0D, 0.0D)).yRot(-this.getYRot() * ((float) Math.PI / 180F) - ((float) Math.PI / 2F));
			passenger.setPos(this.getX() + vector3d.x, this.getY() + (double) f1, this.getZ() + vector3d.z);
			passenger.setYRot(this.getYRot() + this.deltaRotation);
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