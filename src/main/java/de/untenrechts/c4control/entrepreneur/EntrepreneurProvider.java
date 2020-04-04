package de.untenrechts.c4control.entrepreneur;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

public class EntrepreneurProvider implements ICapabilitySerializable<NBTBase> {

    @CapabilityInject(IEntrepreneur.class)
    public static Capability<IEntrepreneur> entrepreneur = null;

    private final IEntrepreneur instance = entrepreneur.getDefaultInstance();

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return Objects.equals(capability, entrepreneur);
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return Objects.equals(capability, entrepreneur) ? entrepreneur.cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT() {
        return entrepreneur.getStorage().writeNBT(entrepreneur, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        entrepreneur.getStorage().readNBT(entrepreneur, this.instance, null, nbt);
    }
}
