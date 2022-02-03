package com.teamabnormals.boatload.core.api;

import com.google.common.collect.ImmutableList;
import com.teamabnormals.boatload.core.Boatload;
import com.teamabnormals.boatload.core.registry.BoatloadItems;
import it.unimi.dsi.fastutil.objects.ObjectArraySet;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.Set;
import java.util.function.Supplier;

public class BoatloadBoatType {
	private static final Set<BoatloadBoatType> BOAT_TYPES = new ObjectArraySet<>();

	public static final BoatloadBoatType OAK = register(create(new ResourceLocation(Boatload.MOD_ID, "oak"), () -> Items.OAK_PLANKS, () -> Items.OAK_BOAT, BoatloadItems.OAK_CHEST_BOAT, BoatloadItems.OAK_FURNACE_BOAT, BoatloadItems.LARGE_OAK_BOAT));
	public static final BoatloadBoatType BIRCH = register(create(new ResourceLocation(Boatload.MOD_ID, "birch"), () -> Items.BIRCH_PLANKS, () -> Items.BIRCH_BOAT, BoatloadItems.BIRCH_CHEST_BOAT, BoatloadItems.BIRCH_FURNACE_BOAT, BoatloadItems.LARGE_BIRCH_BOAT));
	public static final BoatloadBoatType SPRUCE = register(create(new ResourceLocation(Boatload.MOD_ID, "spruce"), () -> Items.SPRUCE_PLANKS, () -> Items.SPRUCE_BOAT, BoatloadItems.SPRUCE_CHEST_BOAT, BoatloadItems.SPRUCE_FURNACE_BOAT, BoatloadItems.LARGE_SPRUCE_BOAT));
	public static final BoatloadBoatType JUNGLE = register(create(new ResourceLocation(Boatload.MOD_ID, "jungle"), () -> Items.JUNGLE_PLANKS, () -> Items.JUNGLE_BOAT, BoatloadItems.JUNGLE_CHEST_BOAT, BoatloadItems.JUNGLE_FURNACE_BOAT, BoatloadItems.LARGE_JUNGLE_BOAT));
	public static final BoatloadBoatType ACACIA = register(create(new ResourceLocation(Boatload.MOD_ID, "acacia"), () -> Items.ACACIA_PLANKS, () -> Items.ACACIA_BOAT, BoatloadItems.ACACIA_CHEST_BOAT, BoatloadItems.ACACIA_FURNACE_BOAT, BoatloadItems.LARGE_ACACIA_BOAT));
	public static final BoatloadBoatType DARK_OAK = register(create(new ResourceLocation(Boatload.MOD_ID, "dark_oak"), () -> Items.DARK_OAK_PLANKS, () -> Items.DARK_OAK_BOAT, BoatloadItems.DARK_OAK_CHEST_BOAT, BoatloadItems.DARK_OAK_FURNACE_BOAT, BoatloadItems.LARGE_DARK_OAK_BOAT));

	private final ResourceLocation registryName;
	private final Supplier<Item> planks;
	private final Supplier<Item> boat;
	private final Supplier<Item> chestBoat;
	private final Supplier<Item> furnaceBoat;
	private final Supplier<Item> largeBoat;

	protected BoatloadBoatType(ResourceLocation registryName, Supplier<Item> planks, Supplier<Item> boat, Supplier<Item> chestBoat, Supplier<Item> furnaceBoat, Supplier<Item> largeBoat) {
		this.registryName = registryName;
		this.planks = planks;
		this.boat = boat;
		this.chestBoat = chestBoat;
		this.furnaceBoat = furnaceBoat;
		this.largeBoat = largeBoat;
	}

	public static ImmutableList<BoatloadBoatType> values() {
		return ImmutableList.copyOf(BOAT_TYPES);
	}

	public ResourceLocation getRegistryName() {
		return this.registryName;
	}

	public String toString() {
		return this.getRegistryName().toString();
	}

	public Supplier<Item> getPlanks() {
		return this.planks;
	}

	public Supplier<Item> getBoat() {
		return this.boat;
	}

	public Supplier<Item> getChestBoat() {
		return this.chestBoat;
	}

	public Supplier<Item> getFurnaceBoat() {
		return this.furnaceBoat;
	}

	public Supplier<Item> getLargeBoat() {
		return this.largeBoat;
	}

	public static BoatloadBoatType create(ResourceLocation registryName, Supplier<Item> planks, Supplier<Item> boat, Supplier<Item> chestBoat, Supplier<Item> furnaceBoat, Supplier<Item> largeBoat) {
		return new BoatloadBoatType(registryName, planks, boat, chestBoat, furnaceBoat, largeBoat);
	}

	public static synchronized BoatloadBoatType register(BoatloadBoatType type) {
		BOAT_TYPES.add(type);
		return type;
	}

	public static BoatloadBoatType getTypeFromString(String name) {
		for (BoatloadBoatType type : values()) {
			if (type.getRegistryName().toString().equals(name)) return type;
		}

		return OAK;
	}

	public static BoatloadBoatType getTypeFromBoat(Item boat) {
		for (BoatloadBoatType type : values()) {
			if (type.getBoat() == boat) return type;
		}

		return OAK;
	}
}