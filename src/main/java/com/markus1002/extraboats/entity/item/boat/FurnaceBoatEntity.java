package com.markus1002.extraboats.entity.item.boat;

import javax.annotation.Nullable;

import com.markus1002.extraboats.compatibility.Atmospheric;
import com.markus1002.extraboats.compatibility.Autumnity;
import com.markus1002.extraboats.compatibility.BambooBlocks;
import com.markus1002.extraboats.compatibility.BiomesOPlenty;
import com.markus1002.extraboats.compatibility.Bloomful;
import com.markus1002.extraboats.compatibility.EndergeticExpansion;
import com.markus1002.extraboats.compatibility.SwampExpansion;
import com.markus1002.extraboats.compatibility.UpgradeAquatic;
import com.markus1002.extraboats.entity.ModEntities;
import com.markus1002.extraboats.item.ModItems;

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
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class FurnaceBoatEntity extends ModBoatEntity
{
	private static final DataParameter<Integer> FUEL = EntityDataManager.createKey(FurnaceBoatEntity.class, DataSerializers.VARINT);
	private static final Ingredient FUEL_ITEMS = Ingredient.fromItems(Items.COAL, Items.CHARCOAL);

	public FurnaceBoatEntity(EntityType<? extends BoatEntity> entityType, World worldIn)
	{
		super(entityType, worldIn);
	}

	public FurnaceBoatEntity(World worldIn, double x, double y, double z)
	{
		this(ModEntities.FURNACE_BOAT, worldIn);
		this.setPosition(x, y, z);
		this.setMotion(Vec3d.ZERO);
		this.prevPosX = x;
		this.prevPosY = y;
		this.prevPosZ = z;
	}

	public FurnaceBoatEntity(FMLPlayMessages.SpawnEntity packet, World worldIn)
	{
		super(ModEntities.FURNACE_BOAT, worldIn);
	}

	protected void registerData()
	{
		super.registerData();
		this.dataManager.register(FUEL, 0);
	}

	public void killBoat()
	{
		super.killBoat();
		this.entityDropItem(Blocks.FURNACE);
	}

	public boolean processInitialInteract(PlayerEntity player, Hand hand)
	{
		if (player.isSneaking())
		{
			ItemStack itemstack = player.getHeldItem(hand);
			if (FUEL_ITEMS.test(itemstack) && this.getFuel() + 3600 <= 32000)
			{
				if (!player.abilities.isCreativeMode)
				{
					itemstack.shrink(1);
				}

				this.setFuel(this.getFuel() + 3600);
			}

			return true;
		}
		else
		{
			return super.processInitialInteract(player, hand);
		}
	}

	public void tick()
	{
		super.tick();
		if (this.getFuel() > 0)
		{
			this.setFuel(this.getFuel() - 1);
		}

		if (this.world.isRemote && this.getFuel() > 0 && this.rand.nextInt(4) == 0)
		{
			float f = (this.rotationYaw - 90.0F) * ((float)Math.PI / 180F);
			float f1 = MathHelper.cos(f);
			float f2 = MathHelper.sin(f);
			this.world.addParticle(ParticleTypes.LARGE_SMOKE, this.posX + (double)f1 * 0.5D, this.posY + 1.0D, this.posZ + (double)f2 * 0.5D, 0.0D, 0.0D, 0.0D);
		}
	}

	protected void controlBoat()
	{
		if (this.isBeingRidden())
		{
			float f = 0.0F;
			if (this.leftInputDown)
			{
				--this.deltaRotation;
			}

			if (this.rightInputDown)
			{
				++this.deltaRotation;
			}

			if (this.rightInputDown != this.leftInputDown && !this.forwardInputDown && !this.backInputDown)
			{
				f += 0.005F;
			}

			this.rotationYaw += this.deltaRotation;
			if (this.forwardInputDown)
			{
				f += 0.04F;
			}

			if (this.backInputDown)
			{
				f -= 0.021F;
			}

			if (this.getFuel() > 0)
			{
				f += 0.026F;
			}

			this.setMotion(this.getMotion().add((double)(MathHelper.sin(-this.rotationYaw * ((float)Math.PI / 180F)) * f), 0.0D, (double)(MathHelper.cos(this.rotationYaw * ((float)Math.PI / 180F)) * f)));
			this.setPaddleState(this.rightInputDown && !this.leftInputDown || this.forwardInputDown, this.leftInputDown && !this.rightInputDown || this.forwardInputDown);

			if (this.getFuel() > 0)
			{
				float f1 = (this.rotationYaw - 90.0F) * ((float)Math.PI / 180F);
				float f2 = MathHelper.cos(f1);
				float f3 = MathHelper.sin(f1);
				for(int i = 0; i < 10; ++i)
				{
					this.world.addParticle(ParticleTypes.SPLASH, this.posX + (double)f2 * 0.8D + (this.rand.nextDouble() - 0.5D), this.posY + 0.2F, this.posZ + (double)f3 * 0.8D + (this.rand.nextDouble()- 0.5D), 0.0D, 0.05D, 0.0D);
				}
			}
		}
	}

	protected void writeAdditional(CompoundNBT compound)
	{
		super.writeAdditional(compound);
		compound.putInt("Fuel", this.getFuel());
	}

	protected void readAdditional(CompoundNBT compound)
	{
		super.readAdditional(compound);
		this.setFuel(compound.getInt("Fuel"));
	}

	private void setFuel(@Nullable int fuel)
	{
		this.dataManager.set(FUEL, fuel);
	}

	@Nullable
	public int getFuel()
	{
		return this.dataManager.get(FUEL);
	}

	public Item getItemBoat()
	{
		switch(this.getModBoatType())
		{
		case OAK:
		default:
			return ModItems.OAK_FURNACE_BOAT;
		case SPRUCE:
			return ModItems.SPRUCE_FURNACE_BOAT;
		case BIRCH:
			return ModItems.BIRCH_FURNACE_BOAT;
		case JUNGLE:
			return ModItems.JUNGLE_FURNACE_BOAT;
		case ACACIA:
			return ModItems.ACACIA_FURNACE_BOAT;
		case DARK_OAK:
			return ModItems.DARK_OAK_FURNACE_BOAT;

		case CHERRY:
			return ModItems.CHERRY_FURNACE_BOAT;
		case DEAD:
			return ModItems.DEAD_FURNACE_BOAT;
		case ETHEREAL:
			return ModItems.ETHEREAL_FURNACE_BOAT;
		case FIR:
			return ModItems.FIR_FURNACE_BOAT;
		case HELLBARK:
			return ModItems.HELLBARK_FURNACE_BOAT;
		case JACARANDA:
			return ModItems.JACARANDA_FURNACE_BOAT;
		case MAGIC:
			return ModItems.MAGIC_FURNACE_BOAT;
		case MAHOGANY:
			return ModItems.MAHOGANY_FURNACE_BOAT;
		case PALM:
			return ModItems.PALM_FURNACE_BOAT;
		case REDWOOD:
			return ModItems.REDWOOD_FURNACE_BOAT;
		case UMBRAN:
			return ModItems.UMBRAN_FURNACE_BOAT;
		case WILLOW:
			return ModItems.WILLOW_FURNACE_BOAT;

		case DRIFTWOOD:
			return ModItems.DRIFTWOOD_FURNACE_BOAT;

		case BAMBOO:
			return ModItems.BAMBOO_FURNACE_BOAT;

		case POISE:
			return ModItems.POISE_FURNACE_BOAT;

		case WISTERIA:
			return ModItems.WISTERIA_FURNACE_BOAT;

		case SE_WILLOW:
			return ModItems.SE_WILLOW_FURNACE_BOAT;
			
		case ROSEWOOD:
			return ModItems.ROSEWOOD_FURNACE_BOAT;
		case ASPEN:
			return ModItems.ASPEN_FURNACE_BOAT;
		case KOUSA:
			return ModItems.KOUSA_FURNACE_BOAT;
		case YUCCA:
			return ModItems.YUCCA_FURNACE_BOAT;
			
		case MAPLE:
			return ModItems.MAPLE_FURNACE_BOAT;
		}
	}

	public Item getItemDropBoat()
	{
		switch(this.getModBoatType())
		{
		case OAK:
		default:
			return Items.OAK_BOAT;
		case SPRUCE:
			return Items.SPRUCE_BOAT;
		case BIRCH:
			return Items.BIRCH_BOAT;
		case JUNGLE:
			return Items.JUNGLE_BOAT;
		case ACACIA:
			return Items.ACACIA_BOAT;
		case DARK_OAK:
			return Items.DARK_OAK_BOAT;

		case CHERRY:
			return BiomesOPlenty.CHERRY_BOAT;
		case DEAD:
			return BiomesOPlenty.DEAD_BOAT;
		case ETHEREAL:
			return BiomesOPlenty.ETHEREAL_BOAT;
		case FIR:
			return BiomesOPlenty.FIR_BOAT;
		case HELLBARK:
			return BiomesOPlenty.HELLBARK_BOAT;
		case JACARANDA:
			return BiomesOPlenty.JACARANDA_BOAT;
		case MAGIC:
			return BiomesOPlenty.MAGIC_BOAT;
		case MAHOGANY:
			return BiomesOPlenty.MAHOGANY_BOAT;
		case PALM:
			return BiomesOPlenty.PALM_BOAT;
		case REDWOOD:
			return BiomesOPlenty.REDWOOD_BOAT;
		case UMBRAN:
			return BiomesOPlenty.UMBRAN_BOAT;
		case WILLOW:
			return BiomesOPlenty.WILLOW_BOAT;

		case DRIFTWOOD:
			return UpgradeAquatic.DRIFTWOOD_BOAT;

		case BAMBOO:
			return BambooBlocks.BAMBOO_BOAT;

		case POISE:
			return EndergeticExpansion.POISE_BOAT;

		case WISTERIA:
			return Bloomful.WISTERIA_BOAT;

		case SE_WILLOW:
			return SwampExpansion.WILLOW_BOAT;
			
		case ROSEWOOD:
			return Atmospheric.ROSEWOOD_BOAT;
		case ASPEN:
			return Atmospheric.ASPEN_BOAT;
		case KOUSA:
			return Atmospheric.KOUSA_BOAT;
		case YUCCA:
			return Atmospheric.YUCCA_BOAT;
			
		case MAPLE:
			return Autumnity.MAPLE_BOAT;
		}
	}

	public BlockState getDisplayTile()
	{
		return Blocks.FURNACE.getDefaultState().with(FurnaceBlock.FACING, Direction.SOUTH).with(FurnaceBlock.LIT, Boolean.valueOf(this.getFuel() > 0));
	}

	public void updatePassenger(Entity passenger)
	{
		if (this.isPassenger(passenger))
		{
			float f1 = (float)((this.removed ? (double)0.01F : this.getMountedYOffset()) + passenger.getYOffset());

			Vec3d vec3d = (new Vec3d((double)0.2F, 0.0D, 0.0D)).rotateYaw(-this.rotationYaw * ((float)Math.PI / 180F) - ((float)Math.PI / 2F));
			passenger.setPosition(this.posX + vec3d.x, this.posY + (double)f1, this.posZ + vec3d.z);
			passenger.rotationYaw += this.deltaRotation;
			passenger.setRotationYawHead(passenger.getRotationYawHead() + this.deltaRotation);
			this.applyYawToEntity(passenger);
			if (passenger instanceof AnimalEntity)
			{
				int j = passenger.getEntityId() % 2 == 0 ? 90 : 270;
				passenger.setRenderYawOffset(((AnimalEntity)passenger).renderYawOffset + (float)j);
				passenger.setRotationYawHead(passenger.getRotationYawHead() + (float)j);
			}
		}
	}

	protected boolean canFitPassenger(Entity passenger)
	{
		return !this.isBeingRidden() && !this.areEyesInFluid(FluidTags.WATER);
	}
}