package com.markus1002.extraboats.entity.item.boat;

import com.markus1002.extraboats.compatibility.Atmospheric;
import com.markus1002.extraboats.compatibility.Autumnity;
import com.markus1002.extraboats.compatibility.BambooBlocks;
import com.markus1002.extraboats.compatibility.BiomesOPlenty;
import com.markus1002.extraboats.compatibility.Bloomful;
import com.markus1002.extraboats.compatibility.BuzzierBees;
import com.markus1002.extraboats.compatibility.EndergeticExpansion;
import com.markus1002.extraboats.compatibility.SwampExpansion;
import com.markus1002.extraboats.compatibility.UpgradeAquatic;

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

public abstract class ModBoatEntity extends BoatEntity
{
	private static final DataParameter<Integer> BOAT_TYPE = EntityDataManager.createKey(ModBoatEntity.class, DataSerializers.VARINT);

	public ModBoatEntity(EntityType<? extends BoatEntity> entityType, World worldIn)
	{
		super(entityType, worldIn);
	}

	protected void registerData()
	{
		super.registerData();
		this.dataManager.register(BOAT_TYPE, ModBoatEntity.BoatType.OAK.ordinal());
	}

	public void setModBoatType(ModBoatEntity.BoatType boatType)
	{
		this.dataManager.set(BOAT_TYPE, boatType.ordinal());
	}

	public ModBoatEntity.BoatType getModBoatType()
	{
		return ModBoatEntity.BoatType.byId(this.dataManager.get(BOAT_TYPE));
	}

	protected void writeAdditional(CompoundNBT compound)
	{
		compound.putString("Type", this.getModBoatType().getName());
	}

	protected void readAdditional(CompoundNBT compound)
	{
		if (compound.contains("Type", 8))
		{
			this.setModBoatType(ModBoatEntity.BoatType.getTypeFromString(compound.getString("Type")));
		}
	}

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
			else if (!this.world.getFluidState((new BlockPos(this)).down()).isTagged(FluidTags.WATER) && y < 0.0D)
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
		case RIVER:
			return UpgradeAquatic.RIVER_BOAT;
			
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
			
		case HIVE:
			return BuzzierBees.HIVE_BOAT;
		}
	}

	public IPacket<?> createSpawnPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	protected Block getPlanks()
	{
		switch(this.getModBoatType())
		{
		case OAK:
		default:
			return Blocks.OAK_PLANKS;
		case SPRUCE:
			return Blocks.SPRUCE_PLANKS;
		case BIRCH:
			return Blocks.BIRCH_PLANKS;
		case JUNGLE:
			return Blocks.JUNGLE_PLANKS;
		case ACACIA:
			return Blocks.ACACIA_PLANKS;
		case DARK_OAK:
			return Blocks.DARK_OAK_PLANKS;

		case CHERRY:
			return BiomesOPlenty.CHERRY_PLANKS;
		case DEAD:
			return BiomesOPlenty.DEAD_PLANKS;
		case ETHEREAL:
			return BiomesOPlenty.ETHEREAL_PLANKS;
		case FIR:
			return BiomesOPlenty.FIR_PLANKS;
		case HELLBARK:
			return BiomesOPlenty.HELLBARK_PLANKS;
		case JACARANDA:
			return BiomesOPlenty.JACARANDA_PLANKS;
		case MAGIC:
			return BiomesOPlenty.MAGIC_PLANKS;
		case MAHOGANY:
			return BiomesOPlenty.MAHOGANY_PLANKS;
		case PALM:
			return BiomesOPlenty.PALM_PLANKS;
		case REDWOOD:
			return BiomesOPlenty.REDWOOD_PLANKS;
		case UMBRAN:
			return BiomesOPlenty.UMBRAN_PLANKS;
		case WILLOW:
			return BiomesOPlenty.WILLOW_PLANKS;
			
		case DRIFTWOOD:
			return UpgradeAquatic.DRIFTWOOD_PLANKS;
		case RIVER:
			return UpgradeAquatic.RIVER_PLANKS;
			
		case BAMBOO:
			return BambooBlocks.BAMBOO_PLANKS;
			
		case POISE:
			return EndergeticExpansion.POISE_PLANKS;
			
		case WISTERIA:
			return Bloomful.WISTERIA_PLANKS;
			
		case SE_WILLOW:
			return SwampExpansion.WILLOW_PLANKS;
			
		case ROSEWOOD:
			return Atmospheric.ROSEWOOD_PLANKS;
		case ASPEN:
			return Atmospheric.ASPEN_PLANKS;
		case KOUSA:
			return Atmospheric.KOUSA_PLANKS;
		case YUCCA:
			return Atmospheric.YUCCA_PLANKS;
			
		case MAPLE:
			return Autumnity.MAPLE_PLANKS;
			
		case HIVE:
			return BuzzierBees.HIVE_PLANKS;
		}
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
		ETHEREAL("ethereal"),
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
		
		SE_WILLOW("se_willow"),
		
		ROSEWOOD("rosewood"),
		ASPEN("aspen"),
		KOUSA("kousa"),
		YUCCA("yucca"),
		
		MAPLE("maple"),
	
		HIVE("hive");

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

		public static ModBoatEntity.BoatType byId(int id)
		{
			ModBoatEntity.BoatType[] aboatentity$type = values();
			if (id < 0 || id >= aboatentity$type.length)
			{
				id = 0;
			}

			return aboatentity$type[id];
		}

		public static ModBoatEntity.BoatType getTypeFromString(String nameIn)
		{
			ModBoatEntity.BoatType[] aboatentity$type = values();

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