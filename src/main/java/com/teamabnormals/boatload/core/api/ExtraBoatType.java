package com.teamabnormals.boatload.core.api;

import com.teamabnormals.boatload.core.registry.BLItems;
import it.unimi.dsi.fastutil.objects.ObjectArraySet;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExtraBoatType {
	private static final Set<ExtraBoatType> BOAT_TYPES = new ObjectArraySet<>();

	public static final ExtraBoatType OAK = register(create("oak"));
	public static final ExtraBoatType SPRUCE = register(create("spruce"));
	public static final ExtraBoatType BIRCH = register(create("birch"));
	public static final ExtraBoatType JUNGLE = register(create("jungle"));
	public static final ExtraBoatType ACACIA = register(create("acacia"));
	public static final ExtraBoatType DARK_OAK = register(create("dark_oak"));
	public static final ExtraBoatType ROSEWOOD = register(create("atmospheric", "rosewood"));
	public static final ExtraBoatType MORADO = register(create("atmospheric", "morado"));
	public static final ExtraBoatType ASPEN = register(create("atmospheric", "aspen"));
	public static final ExtraBoatType KOUSA = register(create("atmospheric", "kousa"));
	public static final ExtraBoatType YUCCA = register(create("atmospheric", "yucca"));
	public static final ExtraBoatType GRIMWOOD = register(create("atmospheric", "grimwood"));
	public static final ExtraBoatType MAPLE = register(create("autumnity", "maple"));
	public static final ExtraBoatType BAMBOO = register(create("bamboo_blocks", "bamboo"));
	public static final ExtraBoatType POISE = register(create("endergetic", "poise"));
	public static final ExtraBoatType WISTERIA = register(create("environmental", "wisteria"));
	public static final ExtraBoatType WILLOW = register(create("environmental", "willow"));
	public static final ExtraBoatType CHERRY = register(create("environmental", "cherry"));
	public static final ExtraBoatType CRIMSON = register(create("nether_extension", "crimson", "crimson_planks"));
	public static final ExtraBoatType WARPED = register(create("nether_extension", "warped", "warped_planks"));
	public static final ExtraBoatType DRIFTWOOD = register(create("upgrade_aquatic", "driftwood"));
	public static final ExtraBoatType RIVER = register(create("upgrade_aquatic", "river"));

	private final ResourceLocation id;
	private final ResourceLocation planks;

	protected ExtraBoatType(ResourceLocation id, String planks) {
		this.id = id;
		this.planks = new ResourceLocation(planks);
	}

	public static ExtraBoatType create(String name) {
		return create("minecraft", name, name + "_planks");
	}

	public static ExtraBoatType create(String modid, String name) {
		return create(modid, name, modid + ":" + name + "_planks");
	}

	public static ExtraBoatType create(String modid, String name, String planks) {
		return new ExtraBoatType(new ResourceLocation(modid, name), planks);
	}

	public static ExtraBoatType register(ExtraBoatType type) {
		BOAT_TYPES.add(type);
		return type;
	}

	public static Stream<ExtraBoatType> values() {
		return BOAT_TYPES.stream();
	}

	public ResourceLocation getRegistryName() {
		return this.id;
	}

	public String getName() {
		return this.id.getPath();
	}

	public String getModID() {
		return this.id.getNamespace();
	}

	public String toString() {
		return this.getRegistryName().toString();
	}

	public Item getPlanks() {
		Item item = ForgeRegistries.ITEMS.getValue(this.planks);
		return item == null ? Items.OAK_PLANKS : item;
	}

	public Item getBoat() {
		Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(this.getModID(), this.getName() + "_boat"));
		return item == null ? Items.OAK_BOAT : item;
	}

	public Item getChestBoat() {
		Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(this.getModID(), this.getName() + "_chest_boat"));
		return item == null ? BLItems.OAK_CHEST_BOAT.get() : item;
	}

	public Item getFurnaceBoat() {
		Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(this.getModID(), this.getName() + "_furnace_boat"));
		return item == null ? BLItems.OAK_FURNACE_BOAT.get() : item;
	}

	public Item getLargeBoat() {
		Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(this.getModID(), "large_" + this.getName() + "_boat"));
		return item == null ? BLItems.LARGE_OAK_BOAT.get() : item;
	}

	public static ExtraBoatType getTypeFromString(String name) {
		for (ExtraBoatType type : values().collect(Collectors.toList())) {
			if (type.toString().equals(name))
				return type;
		}

		return OAK;
	}

	public static ExtraBoatType getTypeFromBoat(Item boat) {
		for (ExtraBoatType type : values().collect(Collectors.toList())) {
			if (type.getBoat() == boat)
				return type;
		}

		return OAK;
	}
}