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
	private static final DataParameter<ItemStack> CHEST = EntityDataManager.createKey(ChestBoatEntity.class, DataSerializers.ITEMSTACK);

	public ChestBoatEntity(EntityType<? extends BoatEntity> entityType, World worldIn) {
		super(entityType, worldIn);
	}

	public ChestBoatEntity(World worldIn, double x, double y, double z) {
		this(EBEntities.CHEST_BOAT.get(), worldIn);
		this.setPosition(x, y, z);
		this.setMotion(Vector3d.ZERO);
		this.prevPosX = x;
		this.prevPosY = y;
		this.prevPosZ = z;
	}

	public ChestBoatEntity(FMLPlayMessages.SpawnEntity packet, World worldIn) {
		super(EBEntities.CHEST_BOAT.get(), worldIn);
	}

	@Override
	protected void registerData() {
		this.getDataManager().register(CHEST, ItemStack.EMPTY);
		super.registerData();
	}

	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);

		if (!this.getChest().isEmpty()) {
			compound.put("Chest", this.getChest().write(new CompoundNBT()));
		}
	}

	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);

		CompoundNBT compoundnbt = compound.getCompound("Chest");
		this.setChest(ItemStack.read(compoundnbt));
	}

	@Override
	public void killBoat() {
		super.killBoat();
		this.entityDropItem(this.getChest());
	}

	@Override
	public int getSizeInventory() {
		return 27;
	}

	@Override
	public Item getItemBoat() {
		return BoatHelper.getChestBoatItem(this.getModBoatType());
	}

	public ItemStack getChest() {
		return this.getDataManager().get(CHEST);
	}

	public void setChest(ItemStack itemStack) {
		this.getDataManager().set(CHEST, itemStack);
	}

	@Override
	public BlockState getDisplayTile() {
		Item item = this.getChest().getItem();
		if (this.getChest().getItem() instanceof BlockItem) {
			Block block = ((BlockItem) item).getBlock();

			if (block instanceof ChestBlock) {
				return block.getDefaultState().with(ChestBlock.FACING, Direction.NORTH);
			}
		}

		return Blocks.CHEST.getDefaultState().with(ChestBlock.FACING, Direction.NORTH);
	}

	@Override
	public Container createContainer(int id, PlayerInventory playerInventoryIn) {
		return ChestContainer.createGeneric9X3(id, playerInventoryIn, this);
	}
}