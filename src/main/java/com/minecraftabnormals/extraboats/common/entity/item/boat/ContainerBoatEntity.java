package com.minecraftabnormals.extraboats.common.entity.item.boat;

import com.minecraftabnormals.extraboats.core.BoatHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootParameters;
import net.minecraft.loot.LootTable;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;

public abstract class ContainerBoatEntity extends ExtraBoatsBoatEntity implements IInventory, INamedContainerProvider {
	private NonNullList<ItemStack> boatContainerItems = NonNullList.withSize(36, ItemStack.EMPTY);
	private boolean dropContentsWhenDead = true;
	@Nullable
	private ResourceLocation lootTable;
	private long lootTableSeed;

	public ContainerBoatEntity(EntityType<? extends BoatEntity> entityType, World worldIn) {
		super(entityType, worldIn);
	}

	@Override
	protected void dropBreakItems() {
		super.dropBreakItems();
		if (!this.level.isClientSide && this.dropContentsWhenDead && this.level.getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
			InventoryHelper.dropContents(this.level, this, this);
		}
	}

	@Override
	public void killBoat() {
		super.killBoat();
		if (!this.level.isClientSide && this.dropContentsWhenDead && this.level.getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
			InventoryHelper.dropContents(this.level, this, this);
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
		this.addLoot((PlayerEntity) null);
		return this.boatContainerItems.get(index);
	}

	@Override
	public ItemStack removeItem(int index, int count) {
		this.addLoot((PlayerEntity) null);
		return ItemStackHelper.removeItem(this.boatContainerItems, index, count);
	}

	@Override
	public ItemStack removeItemNoUpdate(int index) {
		this.addLoot((PlayerEntity) null);
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
		this.addLoot((PlayerEntity) null);
		this.boatContainerItems.set(index, stack);
		if (!stack.isEmpty() && stack.getCount() > this.getMaxStackSize()) {
			stack.setCount(this.getMaxStackSize());
		}
	}

	@Override
	public boolean setSlot(int inventorySlot, ItemStack itemStackIn) {
		if (inventorySlot >= 0 && inventorySlot < this.getContainerSize()) {
			this.setItem(inventorySlot, itemStackIn);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void setChanged() {
	}

	@Override
	public boolean stillValid(PlayerEntity player) {
		if (this.removed) {
			return false;
		} else {
			return !(player.distanceToSqr(this) > 64.0D);
		}
	}

	@Override
	public void remove(boolean keepData) {
		super.remove(keepData);
		if (!keepData) itemHandler.invalidate();
	}

	@Override
	protected void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		if (this.lootTable != null) {
			compound.putString("LootTable", this.lootTable.toString());
			if (this.lootTableSeed != 0L) {
				compound.putLong("LootTableSeed", this.lootTableSeed);
			}
		} else {
			ItemStackHelper.saveAllItems(compound, this.boatContainerItems);
		}
	}

	@Override
	protected void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		this.boatContainerItems = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		if (compound.contains("LootTable", 8)) {
			this.lootTable = new ResourceLocation(compound.getString("LootTable"));
			this.lootTableSeed = compound.getLong("LootTableSeed");
		} else {
			ItemStackHelper.loadAllItems(compound, this.boatContainerItems);
		}
	}

	@Override
	public ActionResultType interact(PlayerEntity player, Hand hand) {
		if (player.isShiftKeyDown()) {
			player.openMenu(this);
			return ActionResultType.sidedSuccess(this.level.isClientSide);
		} else {
			return super.interact(player, hand);
		}
	}

	public void addLoot(@Nullable PlayerEntity player) {
		if (this.lootTable != null && this.level.getServer() != null) {
			LootTable loottable = this.level.getServer().getLootTables().get(this.lootTable);
			this.lootTable = null;
			LootContext.Builder lootcontext$builder = (new LootContext.Builder((ServerWorld) this.level)).withParameter(LootParameters.ORIGIN, this.position()).withOptionalRandomSeed(this.lootTableSeed);
			lootcontext$builder.withParameter(LootParameters.KILLER_ENTITY, this);
			if (player != null) {
				lootcontext$builder.withLuck(player.getLuck()).withParameter(LootParameters.THIS_ENTITY, player);
			}

			loottable.fill(this, lootcontext$builder.create(LootParameterSets.CHEST));
		}
	}

	@Override
	public void clearContent() {
		this.addLoot((PlayerEntity) null);
		this.boatContainerItems.clear();
	}

	public void setLootTable(ResourceLocation lootTableIn, long lootTableSeedIn) {
		this.lootTable = lootTableIn;
		this.lootTableSeed = lootTableSeedIn;
	}

	@Nullable
	@Override
	public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
		if (this.lootTable != null && p_createMenu_3_.isSpectator()) {
			return null;
		} else {
			this.addLoot(p_createMenu_2_.player);
			return this.createContainer(p_createMenu_1_, p_createMenu_2_);
		}
	}

	protected abstract Container createContainer(int id, PlayerInventory playerInventoryIn);

	private net.minecraftforge.common.util.LazyOptional<?> itemHandler = net.minecraftforge.common.util.LazyOptional.of(() -> new net.minecraftforge.items.wrapper.InvWrapper(this));

	@Override
	public <T> net.minecraftforge.common.util.LazyOptional<T> getCapability(net.minecraftforge.common.capabilities.Capability<T> capability, @Nullable net.minecraft.util.Direction facing) {
		if (this.isAlive() && capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return itemHandler.cast();
		return super.getCapability(capability, facing);
	}

	public void dropContentsWhenDead(boolean value) {
		this.dropContentsWhenDead = value;
	}

	@Override
	public Item getItemDropBoat() {
		return BoatHelper.getBoatItem(this.getModBoatType());
	}

	@Override
	public void positionRider(Entity passenger) {
		if (this.hasPassenger(passenger)) {
			float f = passenger instanceof AnimalEntity ? 0.4F : 0.2F;
			float f1 = (float) ((this.removed ? (double) 0.01F : this.getPassengersRidingOffset()) + passenger.getMyRidingOffset());

			Vector3d vector3d = (new Vector3d((double) f, 0.0D, 0.0D)).yRot(-this.yRot * ((float) Math.PI / 180F) - ((float) Math.PI / 2F));
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