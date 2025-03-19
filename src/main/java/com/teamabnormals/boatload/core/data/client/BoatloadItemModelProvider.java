package com.teamabnormals.boatload.core.data.client;

import com.teamabnormals.blueprint.core.data.client.BlueprintItemModelProvider;
import com.teamabnormals.boatload.core.Boatload;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;


public class BoatloadItemModelProvider extends BlueprintItemModelProvider {

	public BoatloadItemModelProvider(PackOutput output, ExistingFileHelper helper) {
		super(output, Boatload.MOD_ID, helper);
	}

	@Override
	protected void registerModels() {
		Boatload.REGISTRY_HELPER.getItemSubHelper().getDeferredRegister().getEntries().forEach(this::generatedItem);
	}
}