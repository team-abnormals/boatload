package com.minecraftabnormals.extraboats.common.entity.item.boat;

import com.minecraftabnormals.abnormals_core.common.world.storage.tracking.IDataManager;
import com.minecraftabnormals.extraboats.core.BoatHelper;
import com.minecraftabnormals.extraboats.core.other.EBDataProcessors;
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
import net.minecraftforge.fml.network.NetworkHooks;

public abstract class ExtraBoatsBoatEntity extends BoatEntity {
	private static final DataParameter<Integer> BOAT_TYPE = EntityDataManager.defineId(ExtraBoatsBoatEntity.class, DataSerializers.INT);

	public ExtraBoatsBoatEntity(EntityType<? extends BoatEntity> entityType, World worldIn) {
		super(entityType, worldIn);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(BOAT_TYPE, ExtraBoatsBoatEntity.BoatType.OAK.ordinal());
	}

	public void setModBoatType(ExtraBoatsBoatEntity.BoatType boatType) {
		this.entityData.set(BOAT_TYPE, boatType.ordinal());
	}

	public ExtraBoatsBoatEntity.BoatType getModBoatType() {
		return ExtraBoatsBoatEntity.BoatType.byId(this.entityData.get(BOAT_TYPE));
	}

	@Override
	protected void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putString("Type", this.getModBoatType().getName());
	}

	@Override
	protected void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains("Type", 8)) {
			this.setModBoatType(ExtraBoatsBoatEntity.BoatType.getTypeFromString(compound.getString("Type")));
		}
	}

	@Override
	protected void checkFallDamage(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
		this.lastYd = this.getDeltaMovement().y;
		if (!this.isPassenger()) {
			if (onGroundIn) {
				if (this.fallDistance > 3.0F) {
					if (this.status != BoatEntity.Status.ON_LAND) {
						this.fallDistance = 0.0F;
						return;
					}

					this.causeFallDamage(this.fallDistance, 1.0F);
					if (!this.level.isClientSide && !this.removed) {
						this.remove();
						if (this.level.getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
							this.dropBreakItems();
						}
					}
				}

				this.fallDistance = 0.0F;
			} else if (!this.level.getFluidState(this.blockPosition().below()).is(FluidTags.WATER) && y < 0.0D) {
				this.fallDistance = (float) ((double) this.fallDistance - y);
			}
		}
	}

	protected void dropBreakItems() {
		for (int i = 0; i < 3; ++i) {
			this.spawnAtLocation(this.getPlanks());
		}

		for (int j = 0; j < 2; ++j) {
			this.spawnAtLocation(Items.STICK);
		}
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (this.isInvulnerableTo(source)) {
			return false;
		} else if (!this.level.isClientSide && !this.removed) {
			if (source instanceof IndirectEntityDamageSource && source.getEntity() != null && this.hasPassenger(source.getEntity())) {
				return false;
			} else {
				this.setHurtDir(-this.getHurtDir());
				this.setHurtTime(10);
				this.setDamage(this.getDamage() + amount * 10.0F);
				this.markHurt();
				boolean flag = source.getEntity() instanceof PlayerEntity && ((PlayerEntity) source.getEntity()).abilities.instabuild;
				if (flag || this.getDamage() > 40.0F) {
					if (!flag && this.level.getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
						this.killBoat();
					}

					this.remove();
				}

				return true;
			}
		} else {
			return true;
		}
	}

	public void killBoat() {
		this.spawnAtLocation(this.getItemDropBoat());
		this.spawnAtLocation(((IDataManager) this).getValue(EBDataProcessors.BANNER));
	}

	public BlockState getDisplayTile() {
		return Blocks.AIR.defaultBlockState();
	}

	public Item getItemDropBoat() {
		return this.getDropItem();
	}

	public Item getDropItem() {
		return BoatHelper.getBoatItem(this.getModBoatType());
	}

	@Override
	public IPacket<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	protected Block getPlanks() {
		return BoatHelper.getPlanks(this.getModBoatType());
	}

	public enum BoatType {
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

		SAKURA("sakura"),

		CRIMSON("crimson"),
		WARPED("warped");

		private final String name;

		BoatType(String nameIn) {
			this.name = nameIn;
		}

		public String getName() {
			return this.name;
		}

		public String toString() {
			return this.name;
		}

		public static ExtraBoatsBoatEntity.BoatType byId(int id) {
			ExtraBoatsBoatEntity.BoatType[] aboatentity$type = values();
			if (id < 0 || id >= aboatentity$type.length) {
				id = 0;
			}

			return aboatentity$type[id];
		}

		public static ExtraBoatsBoatEntity.BoatType getTypeFromString(String nameIn) {
			ExtraBoatsBoatEntity.BoatType[] aboatentity$type = values();

			for (int i = 0; i < aboatentity$type.length; ++i) {
				if (aboatentity$type[i].getName().equals(nameIn)) {
					return aboatentity$type[i];
				}
			}

			return aboatentity$type[0];
		}
	}
}