package de.untenrechts.c4control.bank;

import de.untenrechts.c4control.entrepreneur.IEntrepreneur;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class AtmTileEntity extends TileEntity {

    private static final String INVENTORY_NBT_KEY = "atm.inventory";

    public static final int SIZE = 1;
    private static final List<ResourceLocation> validItems = Collections.singletonList(
            Items.GOLD_INGOT.getRegistryName()
    );

    private ItemStackHandler itemStackHandler = new ItemStackHandler(SIZE) {
        @Override
        protected void onContentsChanged(int slot) {
            AtmTileEntity.this.markDirty();
        }

        @Override
        public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
            return validItems.contains(stack.getItem().getRegistryName());
        }
    };
    private IEntrepreneur occupier;

    public void setOccupier(IEntrepreneur occupier) {
        this.occupier = occupier;
    }

    public boolean canInteractWith(EntityPlayer playerIn) {
        if (!isInvalid() && playerIn.getDistanceSqToCenter(pos) <= 64d) {
            Optional<IEntrepreneur> entrepreneurOpt = IEntrepreneur.getAnyEntrepreneur(playerIn);
            return occupier == null
                    || (entrepreneurOpt.isPresent() && entrepreneurOpt.get().equals(occupier));
        }
        return false;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        if (compound.hasKey(INVENTORY_NBT_KEY)) {
            itemStackHandler.deserializeNBT((NBTTagCompound) compound.getTag(INVENTORY_NBT_KEY));
        }
    }

    @Nonnull
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setTag(INVENTORY_NBT_KEY, itemStackHandler.serializeNBT());
        return compound;
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY
                || super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(itemStackHandler);
        }
        return super.getCapability(capability, facing);
    }
}
