package com.markus1002.extraboats.entity.item.boat;

import java.util.List;

import com.markus1002.extraboats.advancements.criterion.ModCriteriaTriggers;
import com.markus1002.extraboats.entity.ModEntities;
import com.markus1002.extraboats.item.ModItems;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class LargeBoatEntity extends ModBoatEntity
{
	public LargeBoatEntity(EntityType<? extends BoatEntity> entityType, World worldIn)
	{
		super(entityType, worldIn);
	}

	public LargeBoatEntity(World worldIn, double x, double y, double z)
	{
		this(ModEntities.LARGE_BOAT, worldIn);
		this.setPosition(x, y, z);
		this.setMotion(Vec3d.ZERO);
		this.prevPosX = x;
		this.prevPosY = y;
		this.prevPosZ = z;
	}

	public LargeBoatEntity(FMLPlayMessages.SpawnEntity packet, World worldIn)
	{
		super(ModEntities.LARGE_BOAT, worldIn);
	}

	protected void dropBreakItems()
	{
		super.dropBreakItems();
		for(int i = 0; i < 3; ++i)
		{
			this.entityDropItem(this.getPlanks());
		}
		
		for(int j = 0; j < 2; ++j)
		{
			this.entityDropItem(Items.STICK);
		}
	}
	
	public void tick()
	{
		super.tick();

		List<Entity> list = this.world.getEntitiesInAABBexcluding(this, this.getBoundingBox().grow((double)0.2F, (double)-0.01F, (double)0.2F), EntityPredicates.pushableBy(this));
		if (!list.isEmpty())
		{
			boolean flag = !this.world.isRemote && !(this.getControllingPassenger() instanceof PlayerEntity);

			for(int j = 0; j < list.size(); ++j)
			{
				Entity entity = list.get(j);
				if (!entity.isPassenger(this))
				{
					if (flag && this.getPassengers().size() < 4 && !entity.isPassenger() && entity.getWidth() < 1.375F && entity instanceof LivingEntity && !(entity instanceof WaterMobEntity) && !(entity instanceof PlayerEntity))
					{
						entity.startRiding(this);
					}
					else
					{
						this.applyEntityCollision(entity);
					}
				}
			}
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
				f += 0.03F;
			}

			if (this.backInputDown)
			{
				f -= 0.005F;
			}

			this.setMotion(this.getMotion().add((double)(MathHelper.sin(-this.rotationYaw * ((float)Math.PI / 180F)) * f), 0.0D, (double)(MathHelper.cos(this.rotationYaw * ((float)Math.PI / 180F)) * f)));
			this.setPaddleState(this.rightInputDown && !this.leftInputDown || this.forwardInputDown, this.leftInputDown && !this.rightInputDown || this.forwardInputDown);
		}
	}
	
	public void updatePassenger(Entity passenger)
	{
		if (this.isPassenger(passenger))
		{
			float f = 0.0F;
			float f1 = (float)((this.removed ? (double)0.01F : this.getMountedYOffset()) + passenger.getYOffset());
			if (this.getPassengers().size() == 2)
			{
				int i = this.getPassengers().indexOf(passenger);
				if (i == 0)
				{
					f = 0.4F;
				}
				else
				{
					f = -0.8F;
				}

				if (passenger instanceof AnimalEntity) {
					f = (float)((double)f + 0.2D);
				}
			}
			else if (this.getPassengers().size() == 3)
			{
				int i = this.getPassengers().indexOf(passenger);
				if (i == 0)
				{
					f = 0.7F;
				}
				else if (i == 1)
				{
					f = -0.2F;
				}
				else
				{
					f = -1.1F;
				}

				if (passenger instanceof AnimalEntity)
				{
					f = (float)((double)f + 0.2D);
				}
			}
			else if (this.getPassengers().size() > 3)
			{
				int i = this.getPassengers().indexOf(passenger);
				if (i == 0)
				{
					f = 1.025F;
				}
				else if (i == 1)
				{
					f = 0.275F;
				}
				else if (i == 2)
				{
					f = -0.475F;
				}
				else
				{
					f = -1.225F;
				}

				if (passenger instanceof AnimalEntity)
				{
					f = (float)((double)f + 0.1D);
				}
			}

			Vec3d vec3d = (new Vec3d((double)f, 0.0D, 0.0D)).rotateYaw(-this.rotationYaw * ((float)Math.PI / 180F) - ((float)Math.PI / 2F));
			passenger.setPosition(this.getPosX() + vec3d.x, this.getPosY() + (double)f1, this.getPosZ() + vec3d.z);
			passenger.rotationYaw += this.deltaRotation;
			passenger.setRotationYawHead(passenger.getRotationYawHead() + this.deltaRotation);
			this.applyYawToEntity(passenger);
			if (passenger instanceof AnimalEntity && this.getPassengers().size() > 1)
			{
				int j = passenger.getEntityId() % 2 == 0 ? 90 : 270;
				passenger.setRenderYawOffset(((AnimalEntity)passenger).renderYawOffset + (float)j);
				passenger.setRotationYawHead(passenger.getRotationYawHead() + (float)j);
			}
			else if (passenger instanceof ServerPlayerEntity)
			{
                ModCriteriaTriggers.RIDE_LARGE_BOAT.func_215105_a((ServerPlayerEntity)passenger, this.getPassengers(), this.getPassengers().size());
			}
		}
	}

	public Item getItemBoat()
	{
		switch(this.getModBoatType())
		{
		case OAK:
		default:
			return ModItems.LARGE_OAK_BOAT;
		case SPRUCE:
			return ModItems.LARGE_SPRUCE_BOAT;
		case BIRCH:
			return ModItems.LARGE_BIRCH_BOAT;
		case JUNGLE:
			return ModItems.LARGE_JUNGLE_BOAT;
		case ACACIA:
			return ModItems.LARGE_ACACIA_BOAT;
		case DARK_OAK:
			return ModItems.LARGE_DARK_OAK_BOAT;
			
		case CHERRY:
			return ModItems.LARGE_CHERRY_BOAT;
		case DEAD:
			return ModItems.LARGE_DEAD_BOAT;
		case ETHEREAL:
			return ModItems.LARGE_ETHEREAL_BOAT;
		case FIR:
			return ModItems.LARGE_FIR_BOAT;
		case HELLBARK:
			return ModItems.LARGE_HELLBARK_BOAT;
		case JACARANDA:
			return ModItems.LARGE_JACARANDA_BOAT;
		case MAGIC:
			return ModItems.LARGE_MAGIC_BOAT;
		case MAHOGANY:
			return ModItems.LARGE_MAHOGANY_BOAT;
		case PALM:
			return ModItems.LARGE_PALM_BOAT;
		case REDWOOD:
			return ModItems.LARGE_REDWOOD_BOAT;
		case UMBRAN:
			return ModItems.LARGE_UMBRAN_BOAT;
		case WILLOW:
			return ModItems.LARGE_WILLOW_BOAT;
			
		case DRIFTWOOD:
			return ModItems.LARGE_DRIFTWOOD_BOAT;
			
		case BAMBOO:
			return ModItems.LARGE_BAMBOO_BOAT;
			
		case POISE:
			return ModItems.LARGE_POISE_BOAT;
			
		case WISTERIA:
			return ModItems.LARGE_WISTERIA_BOAT;
			
		case SE_WILLOW:
			return ModItems.LARGE_SE_WILLOW_BOAT;
			
		case ROSEWOOD:
			return ModItems.LARGE_ROSEWOOD_BOAT;
		case ASPEN:
			return ModItems.LARGE_ASPEN_BOAT;
		case KOUSA:
			return ModItems.LARGE_KOUSA_BOAT;
		case YUCCA:
			return ModItems.LARGE_YUCCA_BOAT;
			
		case MAPLE:
			return ModItems.LARGE_MAPLE_BOAT;
			
		case HIVE:
			return ModItems.LARGE_HIVE_BOAT;
		}
	}

	protected boolean canFitPassenger(Entity passenger)
	{
		return this.getPassengers().size() < 4 && passenger.getWidth() < 1.375F && !this.areEyesInFluid(FluidTags.WATER);
	}
}