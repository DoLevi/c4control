package de.untenrechts.c4control.proxies;

import de.untenrechts.c4control.bank.AtmContainer;
import de.untenrechts.c4control.bank.AtmGui;
import de.untenrechts.c4control.bank.AtmTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

public class GuiProxy implements IGuiHandler {

    public static final int ATM_GUI_ID = 1;

    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof AtmTileEntity) {
            return new AtmContainer(player.inventory, (AtmTileEntity) te);
        }
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof AtmTileEntity) {
            AtmTileEntity containerTileEntity = (AtmTileEntity) te;
            return new AtmGui(new AtmContainer(player.inventory, containerTileEntity));
        }
        return null;
    }
}
