package de.untenrechts.c4control.entrepreneur;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class EntrepreneurStorage implements Capability.IStorage<IEntrepreneur> {

    @Nullable
    @Override
    public NBTBase writeNBT(Capability<IEntrepreneur> capability,
                            IEntrepreneur instance, EnumFacing side) {
        instance.ensureConsistentState();

        NBTTagCompound tag = new NBTTagCompound();
        tag.setBoolean("entrepreneur", instance.isEntrepreneur());
        if (instance.isEntrepreneur()) {
            tag.setFloat("accountBalance", instance.getAccountBalance());
            tag.setFloat("chargeMultiplier", instance.getChargeMultiplier());
            tag.setFloat("activeChargeValue", instance.getActiveChargeValue());
        }
        return tag;
    }

    @Override
    public void readNBT(Capability<IEntrepreneur> capability, IEntrepreneur instance, EnumFacing side, NBTBase nbt) {
        NBTTagCompound tag = (NBTTagCompound) nbt;
        instance.setEntrepreneur(tag.getBoolean("entrepreneur"));
        if (instance.isEntrepreneur()) {
            instance.setAccountBalance(tag.getFloat("accountBalance"));
            instance.setChargeMultiplier(tag.getFloat("chargeMultiplier"));
            instance.setActiveChargeValue(tag.getFloat("activeChargeValue"));
        }

        instance.ensureConsistentState();
    }

}
