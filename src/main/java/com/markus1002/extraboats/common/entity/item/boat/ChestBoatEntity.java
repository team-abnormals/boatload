package com.markus1002.extraboats.common.entity.item.boat;

import com.markus1002.extraboats.core.BoatHelper;
import com.markus1002.extraboats.core.registry.ModEntities;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ChestBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.Item;
import net.minecraft.util.Direction;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class ChestBoatEntity extends ContainerBoatEntity
{
	public ChestBoatEntity(EntityType<? extends BoatEntity> entityType, World worldIn)
	{
		super(entityType, worldIn);
	}

	public ChestBoatEntity(World worldIn, double x, double y, double z)
	{
		this(ModEntities.CHEST_BOAT, worldIn);
		this.setPosition(x, y, z);
		this.setMotion(Vector3d.ZERO);
		this.prevPosX = x;
		this.prevPosY = y;
		this.prevPosZ = z;
	}

	public ChestBoatEntity(FMLPlayMessages.SpawnEntity packet, World worldIn)
	{
		super(ModEntities.CHEST_BOAT, worldIn);
	}
	
	public void killBoat()
	{
		super.killBoat();
		this.entityDropItem(Blocks.CHEST);
	}

	public int getSizeInventory()
	{
		return 27;
	}

	public Item getItemBoat()
	{
		return BoatHelper.getChestBoatItem(this.getModBoatType());
	}

	public BlockState getDisplayTile()
	{
		return Blocks.CHEST.getDefaultState().with(ChestBlock.FACING, Direction.NORTH);
	}

	public Container createContainer(int id, PlayerInventory playerInventoryIn)
	{
		return ChestContainer.createGeneric9X3(id, playerInventoryIn, this);
	}
}