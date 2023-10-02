package com.teamabnormals.boatload.core.data.server;

import com.teamabnormals.boatload.core.Boatload;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class BoatloadBlockTagsProvider extends BlockTagsProvider {

	public BoatloadBlockTagsProvider(PackOutput output, CompletableFuture<Provider> lookupProvider, ExistingFileHelper helper) {
		super(output, lookupProvider, Boatload.MOD_ID, helper);
	}

	@Override
	protected void addTags(Provider lookupProvider) {
	}
}