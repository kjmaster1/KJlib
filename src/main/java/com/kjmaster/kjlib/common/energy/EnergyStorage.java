package com.kjmaster.kjlib.common.energy;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.energy.IEnergyStorage;

public class EnergyStorage implements IEnergyStorage {

    private int energy;
    private int capacity;
    private int maxReceive;
    private int maxExtract;

    public EnergyStorage(int capacity, int maxReceive, int maxExtract) {
        this.capacity = capacity;
        this.maxReceive = maxReceive;
        this.maxExtract = maxExtract;
    }

    public void readFromNBT(NBTTagCompound nbt) {
        this.energy = nbt.getInteger("Energy");

        if (energy > capacity)
            energy = capacity;
    }

    public void writeToNBT(NBTTagCompound nbt) {
        if (energy < 0)
            energy = 0;
        nbt.setInteger("Energy", energy);
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
        if (energy > capacity)
            energy = capacity;
    }

    public void setMaxTransfer(int maxTransfer) {
        setMaxReceive(maxTransfer);
        setMaxExtract(maxTransfer);
    }

    void setMaxReceive(int maxReceive) {
        this.maxReceive = maxReceive;
    }

    void setMaxExtract(int maxExtract) {
        this.maxExtract = maxExtract;
    }

    public int getMaxReceive() {
        return maxReceive;
    }

    public int getMaxExtract() {
        return maxExtract;
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        int energyReceived = Math.min(capacity - energy, Math.min(this.maxReceive, maxReceive));

        if (!simulate)
            energy += energyReceived;
        return energyReceived;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        int energyExtracted = Math.min(energy, Math.min(this.maxExtract, maxExtract));

        if (!simulate)
            energy -= energyExtracted;
        return energyExtracted;
    }

    @Override
    public int getEnergyStored() {
        return energy;
    }

    @Override
    public int getMaxEnergyStored() {
        return capacity;
    }

    @Override
    public boolean canExtract() {
        return this.maxExtract > 0 && this.energy != 0;
    }

    @Override
    public boolean canReceive() {
        return this.maxReceive > 0 && this.energy != this.capacity;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
        if (this.energy < 0) {
            this.energy = 0;
        }
        if (this.energy > this.capacity) {
            this.energy = this.capacity;
        }
    }
}
