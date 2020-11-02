package com.minecraftabnormals.extraboats.common.entity.item.boat;

import com.minecraftabnormals.extraboats.core.BoatHelper;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IndirectEntityDamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;;

public abstract class EBBoatEntity extends BoatEntity
{
	private static final DataParameter<Integer> BOAT_TYPE = EntityDataManager.createKey(EBBoatEntity.class, DataSerializers.VARINT);

	public EBBoatEntity(EntityType<? extends BoatEntity> entityType, World worldIn)
	{
		super(entityType, worldIn);
	}

	@Override
	protected void registerData()
	{
		super.registerData();
		this.dataManager.register(BOAT_TYPE, EBBoatEntity.BoatType.OAK.ordinal());
	}

	public void setModBoatType(EBBoatEntity.BoatType boatType)
	{
		this.dataManager.set(BOAT_TYPE, boatType.ordinal());
	}

	public EBBoatEntity.BoatType getModBoatType()
	{
		return EBBoatEntity.BoatType.byId(this.dataManager.get(BOAT_TYPE));
	}

	@Override
	protected void writeAdditional(CompoundNBT compound)
	{
		compound.putString("Type", this.getModBoatType().getName());
	}

	@Override
	protected void readAdditional(CompoundNBT compound)
	{
		if (compound.contains("Type", 8))
		{
			this.setModBoatType(EBBoatEntity.BoatType.getTypeFromString(compound.getString("Type")));
		}
	}

	@Override
	protected void updateFallState(double y, boolean onGroundIn, BlockState state, BlockPos pos)
	{
		this.lastYd = this.getMotion().y;
		if (!this.isPassenger())
		{
			if (onGroundIn)
			{
				if (this.fallDistance > 3.0F)
				{
					if (this.status != BoatEntity.Status.ON_LAND)
					{
						this.fallDistance = 0.0F;
						return;
					}

					this.onLivingFall(this.fallDistance, 1.0F);
					if (!this.world.isRemote && !this.removed)
					{
						this.remove();
						if (this.world.getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS))
						{
							this.dropBreakItems();
						}
					}
				}

				this.fallDistance = 0.0F;
			}
			else if (!this.world.getFluidState(this.func_233580_cy_().down()).isTagged(FluidTags.WATER) && y < 0.0D)
			{
				this.fallDistance = (float)((double)this.fallDistance - y);
			}
		}
	}

	protected void dropBreakItems()
	{
		for(int i = 0; i < 3; ++i)
		{
			this.entityDropItem(this.getPlanks());
		}

		for(int j = 0; j < 2; ++j)
		{
			this.entityDropItem(Items.STICK);
		}

		this.entityDropItem(this.getDisplayTile().getBlock());
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount)
	{
		if (this.isInvulnerableTo(source))
		{
			return false;
		}
		else if (!this.world.isRemote && !this.removed)
		{
			if (source instanceof IndirectEntityDamageSource && source.getTrueSource() != null && this.isPassenger(source.getTrueSource()))
			{
				return false;
			}
			else
			{
				this.setForwardDirection(-this.getForwardDirection());
				this.setTimeSinceHit(10);
				this.setDamageTaken(this.getDamageTaken() + amount * 10.0F);
				this.markVelocityChanged();
				boolean flag = source.getTrueSource() instanceof PlayerEntity && ((PlayerEntity)source.getTrueSource()).abilities.isCreativeMode;
				if (flag || this.getDamageTaken() > 40.0F)
				{
					if (!flag && this.world.getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS))
					{
						this.killBoat();
					}

					this.remove();
				}

				return true;
			}
		}
		else
		{
			return true;
		}
	}

	public void killBoat()
	{
		this.entityDropItem(this.getItemDropBoat());
	}

	public BlockState getDisplayTile()
	{
		return Blocks.AIR.getDefaultState();
	}

	public Item getItemDropBoat()
	{
		return this.getItemBoat();
	}

	public Item getItemBoat()
	{
		return BoatHelper.getBoatItem(this.getModBoatType());
	}

	@Override
	public IPacket<?> createSpawnPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	protected Block getPlanks()
	{
		return BoatHelper.getPlanks(this.getModBoatType());
	}

	public static enum BoatType
	{
		OAK("oak"),
		SPRUCE("spruce"),
		BIRCH("birch"),
		JUNGLE("jungle"),
		ACACIA("acacia"),
		DARK_OAK("dark_oak"),

		CHERRY("cherry"),
		DEAD("dead"),
		FIR("fir"),
		HELLBARK("hellbark"),
		JACARANDA("jacaranda"),
		MAGIC("magic"),
		MAHOGANY("mahogany"),
		PALM("palm"),
		REDWOOD("redwood"),
		UMBRAN("umbran"),
		WILLOW("willow"),

		DRIFTWOOD("driftwood"),
		RIVER("river"),

		BAMBOO("bamboo"),

		POISE("poise"),

		WISTERIA("wisteria"),
		ENV_WILLOW("env_willow"),
		ENV_CHERRY("env_cherry"),

		ROSEWOOD("rosewood"),
		MORADO("morado"),
		ASPEN("aspen"),
		KOUSA("kousa"),
		YUCCA("yucca"),
		GRIMWOOD("grimwood"),

		MAPLE("maple"),

		RED_MUSHROOM("red_mushroom"),
		BROWN_MUSHROOM("brown_mushroom"),
		GLOWSHROOM("glowshroom"),

		SAKURA("sakura");

		private final String name;

		private BoatType(String nameIn) 
		{
			this.name = nameIn;
		}

		public String getName()
		{
			return this.name;
		}

		public String toString()
		{
			return this.name;
		}

		public static EBBoatEntity.BoatType byId(int id)
		{
			EBBoatEntity.BoatType[] aboatentity$type = values();
			if (id < 0 || id >= aboatentity$type.length)
			{
				id = 0;
			}

			return aboatentity$type[id];
		}

		public static EBBoatEntity.BoatType getTypeFromString(String nameIn)
		{
			EBBoatEntity.BoatType[] aboatentity$type = values();

			for(int i = 0; i < aboatentity$type.length; ++i)
			{
				if (aboatentity$type[i].getName().equals(nameIn))
				{
					return aboatentity$type[i];
				}
			}

			return aboatentity$type[0];
		}
	}
}