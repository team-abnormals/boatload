package com.markus1002.extraboats.entity.item.boat;

import com.markus1002.extraboats.entity.ModEntities;
import com.markus1002.extraboats.item.ModItems;

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
import net.minecraft.util.math.Vec3d;
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
		this.setMotion(Vec3d.ZERO);
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
		switch(this.getModBoatType())
		{
		case OAK:
		default:
			return ModItems.OAK_CHEST_BOAT;
		case SPRUCE:
			return ModItems.SPRUCE_CHEST_BOAT;
		case BIRCH:
			return ModItems.BIRCH_CHEST_BOAT;
		case JUNGLE:
			return ModItems.JUNGLE_CHEST_BOAT;
		case ACACIA:
			return ModItems.ACACIA_CHEST_BOAT;
		case DARK_OAK:
			return ModItems.DARK_OAK_CHEST_BOAT;
			
		case CHERRY:
			return ModItems.CHERRY_CHEST_BOAT;
		case DEAD:
			return ModItems.DEAD_CHEST_BOAT;
		case ETHEREAL:
			return ModItems.ETHEREAL_CHEST_BOAT;
		case FIR:
			return ModItems.FIR_CHEST_BOAT;
		case HELLBARK:
			return ModItems.HELLBARK_CHEST_BOAT;
		case JACARANDA:
			return ModItems.JACARANDA_CHEST_BOAT;
		case MAGIC:
			return ModItems.MAGIC_CHEST_BOAT;
		case MAHOGANY:
			return ModItems.MAHOGANY_CHEST_BOAT;
		case PALM:
			return ModItems.PALM_CHEST_BOAT;
		case REDWOOD:
			return ModItems.REDWOOD_CHEST_BOAT;
		case UMBRAN:
			return ModItems.UMBRAN_CHEST_BOAT;
		case WILLOW:
			return ModItems.WILLOW_CHEST_BOAT;
			
		case DRIFTWOOD:
			return ModItems.DRIFTWOOD_CHEST_BOAT;
			
		case BAMBOO:
			return ModItems.BAMBOO_CHEST_BOAT;
			
		case POISE:
			return ModItems.POISE_CHEST_BOAT;
			
		case WISTERIA:
			return ModItems.WISTERIA_CHEST_BOAT;
			
		case SE_WILLOW:
			return ModItems.SE_WILLOW_CHEST_BOAT;
			
		case ROSEWOOD:
			return ModItems.ROSEWOOD_CHEST_BOAT;
		case ASPEN:
			return ModItems.ASPEN_CHEST_BOAT;
		case KOUSA:
			return ModItems.KOUSA_CHEST_BOAT;
		case YUCCA:
			return ModItems.YUCCA_CHEST_BOAT;
			
		case MAPLE:
			return ModItems.MAPLE_CHEST_BOAT;
		}
	}

	public BlockState getDisplayTile()
	{
		return Blocks.CHEST.getDefaultState().with(ChestBlock.FACING, Direction.NORTH);
	}

	public Container func_213968_a(int p_213968_1_, PlayerInventory p_213968_2_)
	{
		return ChestContainer.createGeneric9X3(p_213968_1_, p_213968_2_, this);
	}
}