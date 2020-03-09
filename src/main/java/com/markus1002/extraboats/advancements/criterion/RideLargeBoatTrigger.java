package com.markus1002.extraboats.advancements.criterion;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.minecraft.advancements.ICriterionTrigger;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.advancements.criterion.CriterionInstance;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;

public class RideLargeBoatTrigger implements ICriterionTrigger<RideLargeBoatTrigger.Instance>
{
	private static final ResourceLocation ID = new ResourceLocation("ride_large_boat");
	private final Map<PlayerAdvancements, RideLargeBoatTrigger.Listeners> field_215108_b = Maps.newHashMap();

	public ResourceLocation getId()
	{
		return ID;
	}

	public void addListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<RideLargeBoatTrigger.Instance> listener)
	{
		RideLargeBoatTrigger.Listeners ridelargeboattrigger$listeners = this.field_215108_b.get(playerAdvancementsIn);
		if (ridelargeboattrigger$listeners == null)
		{
			ridelargeboattrigger$listeners = new RideLargeBoatTrigger.Listeners(playerAdvancementsIn);
			this.field_215108_b.put(playerAdvancementsIn, ridelargeboattrigger$listeners);
		}

		ridelargeboattrigger$listeners.func_218009_a(listener);
	}

	public void removeListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<RideLargeBoatTrigger.Instance> listener)
	{
		RideLargeBoatTrigger.Listeners ridelargeboattrigger$listeners = this.field_215108_b.get(playerAdvancementsIn);
		if (ridelargeboattrigger$listeners != null)
		{
			ridelargeboattrigger$listeners.func_218006_b(listener);
			if (ridelargeboattrigger$listeners.func_218007_a())
			{
				this.field_215108_b.remove(playerAdvancementsIn);
			}
		}

	}

	public void removeAllListeners(PlayerAdvancements playerAdvancementsIn)
	{
		this.field_215108_b.remove(playerAdvancementsIn);
	}

	public RideLargeBoatTrigger.Instance deserializeInstance(JsonObject json, JsonDeserializationContext context)
	{
		EntityPredicate[] aentitypredicate = EntityPredicate.deserializeArray(json.get("entities"));
		return new RideLargeBoatTrigger.Instance(aentitypredicate);
	}

	public void func_215105_a(ServerPlayerEntity p_215105_1_, Collection<Entity> p_215105_2_, int p_215105_3_)
	{
		RideLargeBoatTrigger.Listeners ridelargeboattrigger$listeners = this.field_215108_b.get(p_215105_1_.getAdvancements());
		if (ridelargeboattrigger$listeners != null)
		{
			ridelargeboattrigger$listeners.func_218008_a(p_215105_1_, p_215105_2_, p_215105_3_);
		}

	}

	public static class Instance extends CriterionInstance
	{
		private final EntityPredicate[] field_215118_a;

		public Instance(EntityPredicate[] p_i50580_1_)
		{
			super(RideLargeBoatTrigger.ID);
			this.field_215118_a = p_i50580_1_;
		}

		public static RideLargeBoatTrigger.Instance func_215116_a(EntityPredicate.Builder... p_215116_0_)
		{
			EntityPredicate[] aentitypredicate = new EntityPredicate[p_215116_0_.length];

			for(int i = 0; i < p_215116_0_.length; ++i)
			{
				EntityPredicate.Builder entitypredicate$builder = p_215116_0_[i];
				aentitypredicate[i] = entitypredicate$builder.build();
			}

			return new RideLargeBoatTrigger.Instance(aentitypredicate);
		}

		public static RideLargeBoatTrigger.Instance func_215117_a(MinMaxBounds.IntBound p_215117_0_)
		{
			EntityPredicate[] aentitypredicate = new EntityPredicate[0];
			return new RideLargeBoatTrigger.Instance(aentitypredicate);
		}

		public boolean func_215115_a(ServerPlayerEntity p_215115_1_, Collection<Entity> p_215115_2_, int p_215115_3_)
		{
			if (this.field_215118_a.length > 0)
			{
				List<Entity> list = Lists.newArrayList(p_215115_2_);

				for(EntityPredicate entitypredicate : this.field_215118_a)
				{
					boolean flag = false;
					Iterator<Entity> iterator = list.iterator();

					while(iterator.hasNext())
					{
						Entity entity = iterator.next();
						if (entitypredicate.test(p_215115_1_, entity))
						{
							iterator.remove();
							flag = true;
							break;
						}
					}

					if (!flag)
					{
						return false;
					}
				}
			}

			return true;

			/*
			if (this.field_215119_b == MinMaxBounds.IntBound.UNBOUNDED) {
				return true;
			} else {
				Set<EntityType<?>> set = Sets.newHashSet();

				for(Entity entity1 : p_215115_2_) {
					set.add(entity1.getType());
				}

				return this.field_215119_b.test(set.size()) && this.field_215119_b.test(p_215115_3_);
			}
			 */
		}

		public JsonElement serialize()
		{
			JsonObject jsonobject = new JsonObject();
			jsonobject.add("entities", EntityPredicate.serializeArray(this.field_215118_a));
			return jsonobject;
		}
	}

	static class Listeners
	{
		private final PlayerAdvancements field_218010_a;
		private final Set<ICriterionTrigger.Listener<RideLargeBoatTrigger.Instance>> field_218011_b = Sets.newHashSet();

		public Listeners(PlayerAdvancements p_i50581_1_)
		{
			this.field_218010_a = p_i50581_1_;
		}

		public boolean func_218007_a()
		{
			return this.field_218011_b.isEmpty();
		}

		public void func_218009_a(ICriterionTrigger.Listener<RideLargeBoatTrigger.Instance> p_218009_1_)
		{
			this.field_218011_b.add(p_218009_1_);
		}

		public void func_218006_b(ICriterionTrigger.Listener<RideLargeBoatTrigger.Instance> p_218006_1_)
		{
			this.field_218011_b.remove(p_218006_1_);
		}

		public void func_218008_a(ServerPlayerEntity p_218008_1_, Collection<Entity> p_218008_2_, int p_218008_3_)
		{
			List<ICriterionTrigger.Listener<RideLargeBoatTrigger.Instance>> list = null;

			for(ICriterionTrigger.Listener<RideLargeBoatTrigger.Instance> listener : this.field_218011_b)
			{
				if (listener.getCriterionInstance().func_215115_a(p_218008_1_, p_218008_2_, p_218008_3_))
				{
					if (list == null)
					{
						list = Lists.newArrayList();
					}

					list.add(listener);
				}
			}

			if (list != null)
			{
				for(ICriterionTrigger.Listener<RideLargeBoatTrigger.Instance> listener1 : list)
				{
					listener1.grantCriterion(this.field_218010_a);
				}
			}
		}
	}
}