package de.untenrechts.c4control.bank;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public class AtmContainer extends Container {

    private final AtmTileEntity atmTileEntity;

    public AtmContainer(IInventory playerInventory, AtmTileEntity atmTileEntity) {
        this.atmTileEntity = atmTileEntity;
        IItemHandler itemHandler = this.atmTileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
        this.addSlotToContainer(new SlotItemHandler(itemHandler, 0, 81, 24));

        forPlayerInventorySlots(playerInventory, this::addSlotToContainer);
    }

    @Override
    public boolean canInteractWith(@Nonnull EntityPlayer playerIn) {
        return atmTileEntity.canInteractWith(playerIn);
    }

    @Nonnull
    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index < AtmTileEntity.SIZE) {
                if (!this.mergeItemStack(itemstack1, AtmTileEntity.SIZE, this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 0, AtmTileEntity.SIZE, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }

    private static void forPlayerInventorySlots(IInventory inventory, Consumer<Slot> consumer) {
        for (int row = 0; row < 3; ++row) {
            for (int col  = 0; col < 9; ++col) {
                consumer.accept(new Slot(inventory, 10 + col + row * 9, 9 + col * 18, 70 + row * 18));
            }
        }

        for (int col = 0; col < 9; ++col) {
            consumer.accept(new Slot(inventory, col, 9 + col * 18, 70 + 58));
        }
    }

}
