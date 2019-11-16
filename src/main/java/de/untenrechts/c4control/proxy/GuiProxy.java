package de.untenrechts.c4control.proxy;

import de.untenrechts.c4control.block.BlockATMContainer;
import de.untenrechts.c4control.block.BlockATMContainerGui;
import de.untenrechts.c4control.block.BlockATMContainerTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiProxy implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int id, EntityPlayer player,
                                      World worldIn, int x, int y, int z) {
        TileEntity tileEntity = worldIn.getTileEntity(new BlockPos(x, y, z));
        if (tileEntity instanceof BlockATMContainerTileEntity) {
            return new BlockATMContainer(player.inventory, (BlockATMContainerTileEntity) tileEntity);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player,
                                      World worldIn, int x, int y, int z) {
        TileEntity tileEntity = worldIn.getTileEntity(new BlockPos(x, y, z));
        if (tileEntity instanceof BlockATMContainerTileEntity) {
            BlockATMContainerTileEntity blockTileEntity = (BlockATMContainerTileEntity) tileEntity;
            BlockATMContainer blockContainer = new BlockATMContainer(player.inventory, blockTileEntity);
            return new BlockATMContainerGui(blockTileEntity, blockContainer);
        }
        return null;
    }
}
