package com.minecraftabnormals.extraboats.common.entity.item.boat;

import com.minecraftabnormals.extraboats.core.BoatHelper;
import com.minecraftabnormals.extraboats.core.registry.EBEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ChestBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.Direction;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class ChestBoatEntity extends ContainerBoatEntity {
	private static final DataParameter<ItemStack> CHEST = EntityDataManager.defineId(ChestBoatEntity.class, DataSerializers.ITEM_STACK);

	public ChestBoatEntity(EntityType<? extends BoatEntity> entityType, World worldIn) {
		super(entityType, worldIn);
	}

	public ChestBoatEntity(World worldIn, double x, double y, double z) {
		this(EBEntities.CHEST_BOAT.get(), worldIn);
		this.setPos(x, y, z);
		this.setDeltaMovement(Vector3d.ZERO);
		this.xo = x;
		this.yo = y;
		this.zo = z;
	}

	public ChestBoatEntity(FMLPlayMessages.SpawnEntity packet, World worldIn) {
		super(EBEntities.CHEST_BOAT.get(), worldIn);
	}

	@Override
	protected void defineSynchedData() {
		this.getEntityData().define(CHEST, ItemStack.EMPTY);
		super.defineSynchedData();
	}

	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);

		if (!this.getChest().isEmpty()) {
			compound.put("Chest", this.getChest().save(new CompoundNBT()));
		}
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);

		CompoundNBT compoundnbt = compound.getCompound("Chest");
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
		return BoatHelper.getChestBoatItem(this.getModBoatType());
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
	public Container createContainer(int id, PlayerInventory playerInventoryIn) {
		return ChestContainer.threeRows(id, playerInventoryIn, this);
	}
}