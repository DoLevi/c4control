package de.untenrechts.c4control.block;

import de.untenrechts.c4control.common.ContainerTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.items.ItemStackHandler;

public class BlockATMContainerTileEntity extends ContainerTileEntity {

    private static final int SIZE = 9;

    private final double maxInteractionDistance = 8D;

    private ItemStackHandler itemStackHandler = new ItemStackHandler(SIZE) {
        @Override
        protected void onContentsChanged(int slot) {
            BlockATMContainerTileEntity.this.markDirty();
        }
    };

    public boolean canInteractWith(EntityPlayer playerIn) {
        // TODO: 11/14/19 Find out why we add 0.5
        BlockPos roundedPos = pos.add(0.5D, 0.5D, 0.5D);
        return !isInvalid() && playerIn.getDistanceSq(roundedPos) <= maxInteractionDistance;
    }
}
