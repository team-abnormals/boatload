package com.markus1002.extraboats.common.entity.item.boat;

import java.util.List;

import com.markus1002.extraboats.core.BoatHelper;
import com.markus1002.extraboats.core.ModTags;
import com.markus1002.extraboats.core.registry.ModEntities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
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
		this(ModEntities.LARGE_BOAT.get(), worldIn);
		this.setPosition(x, y, z);
		this.setMotion(Vector3d.ZERO);
		this.prevPosX = x;
		this.prevPosY = y;
		this.prevPosZ = z;
	}

	public LargeBoatEntity(FMLPlayMessages.SpawnEntity packet, World worldIn)
	{
		super(ModEntities.LARGE_BOAT.get(), worldIn);
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
			}
			else if (this.getPassengers().size() == 3)
			{
				int i = this.getPassengers().indexOf(passenger);
				if (i == 0)
				{
					f = 0.8F;
				}
				else if (i == 1)
				{
					f = -0.1F;
				}
				else
				{
					f = -1.0F;
				}
			}
			else if (this.getPassengers().size() > 3)
			{
				int i = this.getPassengers().indexOf(passenger);

				Entity firstpassenger = this.getPassengers().get(0);
				boolean flag = firstpassenger.getType().isContained(ModTags.SITTING_MOBS) || firstpassenger instanceof PlayerEntity;

				if (i == 0)
				{
					f = flag ? 0.875F : 1.0F;
				}
				else
				{
					if (i == 1)
					{
						f = 0.2F;
					}
					else if (i == 2)
					{
						f = -0.55F;
					}
					else
					{
						f = -1.3F;
					}

					f += flag ? 0.0F : 0.05F;
				}
			}

			if (passenger instanceof AnimalEntity)
			{
				f = (float)((double)f + 0.1D);
			}

			Vector3d vector3d = (new Vector3d((double)f, 0.0D, 0.0D)).rotateYaw(-this.rotationYaw * ((float)Math.PI / 180F) - ((float)Math.PI / 2F));
			passenger.setPosition(this.getPosX() + vector3d.x, this.getPosY() + (double)f1, this.getPosZ() + vector3d.z);
			passenger.rotationYaw += this.deltaRotation;
			passenger.setRotationYawHead(passenger.getRotationYawHead() + this.deltaRotation);
			this.applyYawToEntity(passenger);
			if (passenger instanceof AnimalEntity && this.getPassengers().size() > 1)
			{
				int j = passenger.getEntityId() % 2 == 0 ? 90 : 270;
				passenger.setRenderYawOffset(((AnimalEntity)passenger).renderYawOffset + (float)j);
				passenger.setRotationYawHead(passenger.getRotationYawHead() + (float)j);
			}
		}
	}

	public Item getItemBoat()
	{
		return BoatHelper.getLargeBoatItem(this.getModBoatType());
	}

	protected boolean canFitPassenger(Entity passenger)
	{
		return this.getPassengers().size() < 4 && passenger.getWidth() < 1.375F && !this.areEyesInFluid(FluidTags.WATER);
	}
}