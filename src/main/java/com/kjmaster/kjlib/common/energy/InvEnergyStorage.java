package com.kjmaster.kjlib.common.energy;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public class InvEnergyStorage implements IEnergyStorage, ICapabilitySerializable<NBTBase> {

    private int capacity;
    private int maxReceive;
    private int maxExtract;
    private ItemStack container;
    private IItemEnergy itemEnergy;

    public InvEnergyStorage(int capacity, int maxReceive, int maxExtract, ItemStack stack, IItemEnergy itemEnergy) {
        this.capacity = capacity;
        this.maxReceive = maxReceive;
        this.maxExtract = maxExtract;
        this.container = stack;
        this.itemEnergy = itemEnergy;
    }

    @Override
    public NBTBase serializeNBT() {
        if (container.hasTagCompound()) {
            NBTTagCompound tagCompound = container.getTagCompound();
            tagCompound.setInteger("Energy", this.getEnergyStored());
        }
        return container.getTagCompound();
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        NBTTagCompound tagCompound = (NBTTagCompound) nbt;
        if (tagCompound != null && tagCompound.hasKey("Energy")) {
            int energy = tagCompound.getInteger("Energy");
            this.setEnergy(energy);
        }
    }

    public void setEnergy(int energy) {
        checkForEnergyTag();
        this.container.getTagCompound().setInteger("Energy", energy);
    }

    public void setCapacity(int capacity) {
        checkForEnergyTag();
        this.capacity = capacity;
        if (this.getEnergyStored() > capacity) {
            this.container.getTagCompound().setInteger("Energy", capacity);
        }
    }

    private void checkForEnergyTag() {
        if (this.container.hasTagCompound()) {
            NBTTagCompound tagCompound = this.container.getTagCompound();
            if (!tagCompound.hasKey("Energy")) {
                tagCompound.setInteger("Energy", 0);
            }
        } else {
            NBTTagCompound tagCompound = new NBTTagCompound();
            tagCompound.setInteger("Energy", 0);
            this.container.setTagCompound(tagCompound);
        }
    }

    public void setMaxTransfer(int maxTransfer) {
        this.setMaxReceive(maxTransfer);
        this.setMaxExtract(maxTransfer);
    }

    void setMaxReceive(int maxReceive) {
        this.maxReceive = maxReceive;
    }

    void setMaxExtract(int maxExtract) {
        this.maxExtract = maxExtract;
    }

    public int getMaxReceive() {
        return this.maxReceive;
    }

    public int getMaxExtract() {
        return this.maxExtract;
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        return itemEnergy.receiveEnergy(this.container, maxReceive, simulate);
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        return this.itemEnergy.extractEnergy(this.container, maxExtract, simulate);
    }

    @Override
    public int getEnergyStored() {
        checkForEnergyTag();
        return this.container.getTagCompound().getInteger("Energy");
    }

    @Override
    public int getMaxEnergyStored() {
        return this.capacity;
    }

    @Override
    public boolean canExtract() {
        return true;
    }

    @Override
    public boolean canReceive() {
        return false;
    }

    /* ICapabilityProvider */
    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing from) {

        return capability == CapabilityEnergy.ENERGY;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, final EnumFacing from) {

        if (!hasCapability(capability, from)) {
            return null;
        }
        return CapabilityEnergy.ENERGY.cast(this);
    }
}
