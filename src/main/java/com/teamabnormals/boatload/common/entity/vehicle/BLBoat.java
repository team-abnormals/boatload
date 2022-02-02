package com.teamabnormals.boatload.common.entity.vehicle;

import com.teamabnormals.blueprint.common.world.storage.tracking.IDataManager;
import com.teamabnormals.boatload.core.other.BLDataProcessors;
import com.teamabnormals.boatload.core.registry.BLItems;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.registries.ForgeRegistries;

public abstract class BLBoat extends Boat {
	private static final EntityDataAccessor<Integer> BOAT_TYPE = SynchedEntityData.defineId(BLBoat.class, EntityDataSerializers.INT);

	public BLBoat(EntityType<? extends Boat> entityType, Level worldIn) {
		super(entityType, worldIn);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(BOAT_TYPE, BLBoatType.OAK.ordinal());
	}

	public void setModBoatType(BLBoatType boatType) {
		this.entityData.set(BOAT_TYPE, boatType.ordinal());
	}

	public BLBoatType getBLBoatType() {
		return BLBoatType.byId(this.entityData.get(BOAT_TYPE));
	}

	@Override
	protected void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putString("Type", this.getBLBoatType().getName());
	}

	@Override
	protected void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains("Type", 8)) {
			this.setModBoatType(BLBoatType.getTypeFromString(compound.getString("Type")));
		}
	}

	@Override
	protected void checkFallDamage(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
		this.lastYd = this.getDeltaMovement().y;
		if (!this.isPassenger()) {
			if (onGroundIn) {
				if (this.fallDistance > 3.0F) {
					if (this.status != Boat.Status.ON_LAND) {
						this.fallDistance = 0.0F;
						return;
					}

					this.causeFallDamage(this.fallDistance, 1.0F, DamageSource.FALL);
					if (!this.level.isClientSide && !this.isRemoved()) {
						this.discard();
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
		} else if (!this.level.isClientSide && !this.isRemoved()) {
			if (source instanceof IndirectEntityDamageSource && source.getEntity() != null && this.hasPassenger(source.getEntity())) {
				return false;
			} else {
				this.setHurtDir(-this.getHurtDir());
				this.setHurtTime(10);
				this.setDamage(this.getDamage() + amount * 10.0F);
				this.markHurt();
				boolean flag = source.getEntity() instanceof Player && ((Player) source.getEntity()).getAbilities().instabuild;
				if (flag || this.getDamage() > 40.0F) {
					if (!flag && this.level.getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
						this.killBoat();
					}

					this.discard();
				}

				return true;
			}
		} else {
			return true;
		}
	}

	public void killBoat() {
		this.spawnAtLocation(this.getItemDropBoat());
		this.spawnAtLocation(((IDataManager) this).getValue(BLDataProcessors.BANNER));
	}

	public BlockState getDisplayTile() {
		return Blocks.AIR.defaultBlockState();
	}

	public Item getItemDropBoat() {
		return this.getDropItem();
	}

	public Item getDropItem() {
		return this.getBLBoatType().getBoat();
	}

	@Override
	public Packet<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	protected Item getPlanks() {
		return this.getBLBoatType().getPlanks();
	}

	public enum BLBoatType {
		OAK("oak"),
		SPRUCE("spruce"),
		BIRCH("birch"),
		JUNGLE("jungle"),
		ACACIA("acacia"),
		DARK_OAK("dark_oak"),
		ROSEWOOD("atmospheric", "rosewood"),
		MORADO("atmospheric", "morado"),
		ASPEN("atmospheric", "aspen"),
		KOUSA("atmospheric", "kousa"),
		YUCCA("atmospheric", "yucca"),
		GRIMWOOD("atmospheric", "grimwood"),
		MAPLE("autumnity", "maple"),
		BAMBOO("bamboo_blocks", "bamboo"),
		POISE("endergetic", "poise"),
		WISTERIA("environmental", "wisteria"),
		WILLOW("environmental", "willow"),
		CHERRY("environmental", "cherry"),
		CRIMSON("nether_extension", "crimson", "crimson_planks"),
		WARPED("nether_extension", "warped", "warped_planks"),
		DRIFTWOOD("upgrade_aquatic", "driftwood"),
		RIVER("upgrade_aquatic", "river");

		private final String name;
		private final String modid;
		private final ResourceLocation planks;

		BLBoatType(String name) {
			this("minecraft", name, name + "_planks");
		}

		BLBoatType(String modid, String name) {
			this(modid, name, modid + ":" + name + "_planks");
		}

		BLBoatType(String modid, String name, String planks) {
			this.modid = modid;
			this.name = name;
			this.planks = new ResourceLocation(planks);
		}

		public String getName() {
			return this.name;
		}

		public String getModID() {
			return this.modid;
		}

		public Item getPlanks() {
			Item item = ForgeRegistries.ITEMS.getValue(this.planks);
			return item == null ? Items.OAK_PLANKS : item;
		}

		public Item getBoat() {
			Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(this.modid, this.name + "_boat"));
			return item == null ? Items.OAK_BOAT : item;
		}

		public Item getChestBoat() {
			Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(this.modid, this.name + "_chest_boat"));
			return item == null ? BLItems.OAK_CHEST_BOAT.get() : item;
		}

		public Item getFurnaceBoat() {
			Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(this.modid, this.name + "_furnace_boat"));
			return item == null ? BLItems.OAK_FURNACE_BOAT.get() : item;
		}

		public Item getLargeBoat() {
			Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(this.modid, "large_" + this.name + "_boat"));
			return item == null ? BLItems.LARGE_OAK_BOAT.get() : item;
		}

		public String toString() {
			return this.name;
		}

		public static BLBoatType byId(int id) {
			BLBoatType[] aboatentity$type = values();
			if (id < 0 || id >= aboatentity$type.length) {
				id = 0;
			}

			return aboatentity$type[id];
		}

		public static BLBoatType getTypeFromString(String nameIn) {
			BLBoatType[] aboatentity$type = values();
			for (BLBoatType boatType : aboatentity$type) {
				if (boatType.getName().equals(nameIn)) {
					return boatType;
				}
			}

			return aboatentity$type[0];
		}

		public static BLBoatType getTypeFromBoat(Item boat) {
			BLBoatType[] values = values();
			for (BLBoatType boatType : values) {
				if (boatType.getBoat() == boat) {
					return boatType;
				}
			}

			return values[0];
		}
	}
}