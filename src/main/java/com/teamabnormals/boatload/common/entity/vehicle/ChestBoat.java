package com.teamabnormals.boatload.common.entity.vehicle;

import com.teamabnormals.boatload.core.registry.BLEntityTypes;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PlayMessages;

public class ChestBoat extends AbstractContainerBoat {
	private static final EntityDataAccessor<ItemStack> CHEST = SynchedEntityData.defineId(ChestBoat.class, EntityDataSerializers.ITEM_STACK);

	public ChestBoat(EntityType<? extends Boat> entityType, Level worldIn) {
		super(entityType, worldIn);
	}

	public ChestBoat(Level worldIn, double x, double y, double z) {
		this(BLEntityTypes.CHEST_BOAT.get(), worldIn);
		this.setPos(x, y, z);
		this.setDeltaMovement(Vec3.ZERO);
		this.xo = x;
		this.yo = y;
		this.zo = z;
	}

	public ChestBoat(PlayMessages.SpawnEntity packet, Level worldIn) {
		super(BLEntityTypes.CHEST_BOAT.get(), worldIn);
	}

	@Override
	protected void defineSynchedData() {
		this.getEntityData().define(CHEST, ItemStack.EMPTY);
		super.defineSynchedData();
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);

		if (!this.getChest().isEmpty()) {
			compound.put("Chest", this.getChest().save(new CompoundTag()));
		}
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);

		CompoundTag compoundnbt = compound.getCompound("Chest");
		this.setChest(ItemStack.of(compoundnbt));
	}

	@Override
	protected void dropBreakItems() {
		super.dropBreakItems();
		this.spawnAtLocation(this.getChest());
	}

	@Override
	public void killBoat() {
		super.killBoat();
		this.spawnAtLocation(this.getChest());
	}

	@Override
	public int getContainerSize() {
		return 27;
	}

	@Override
	public Item getDropItem() {
		return this.getExtraBoatType().getChestBoat();
	}

	public ItemStack getChest() {
		return this.getEntityData().get(CHEST);
	}

	public void setChest(ItemStack itemStack) {
		this.getEntityData().set(CHEST, itemStack);
	}

	@Override
	public BlockState getDisplayTile() {
		Item item = this.getChest().getItem();
		if (this.getChest().getItem() instanceof BlockItem) {
			Block block = ((BlockItem) item).getBlock();

			if (block instanceof ChestBlock) {
				return block.defaultBlockState().setValue(ChestBlock.FACING, Direction.NORTH);
			}
		}

		return Blocks.CHEST.defaultBlockState().setValue(ChestBlock.FACING, Direction.NORTH);
	}

	@Override
	public AbstractContainerMenu createMenu(int id, Inventory playerInventoryIn) {
		return ChestMenu.threeRows(id, playerInventoryIn, this);
	}
}