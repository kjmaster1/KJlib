package com.kjmaster.kjlib.common.energy;

import net.minecraft.item.ItemStack;

public interface IItemEnergy {

    int receiveEnergy(ItemStack container, int maxReceive, boolean simulate);

    int extractEnergy(ItemStack container, int maxExtract, boolean simulate);

    int getMaxEnergyStored(ItemStack container);
}
