package de.untenrechts.c4control.block;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class BlockATMContainer extends Container {

    private BlockATMContainerTileEntity tileEntity;
    private final int slotsOffsetX = 9;
    private final int slotsOffsetY = 6;

    public BlockATMContainer(IInventory playerInventory, BlockATMContainerTileEntity tileEntity) {
        this.tileEntity = tileEntity;
        // TODO: 11/14/19 addOwnSlots
    }

    private void addOwnSlots() {
        Capability<IItemHandler> capability = CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
        // TODO: 11/14/19 Test different facings
        IItemHandler itemHandler = tileEntity.getCapability(capability, null);

        if (null != itemHandler) {
            for (int slotIndex = 0; slotIndex < itemHandler.getSlots(); ++slotIndex) {
                int x = slotsOffsetX + 18 * slotIndex;
                addSlotToContainer(new SlotItemHandler(itemHandler, slotIndex, x, slotsOffsetY));
            }
        }
        String error = String.format("IItemHandler for %s cannot be null.",
                capability.getName());
        throw new IllegalStateException(error);

    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return tileEntity.canInteractWith(playerIn);
    }
}
