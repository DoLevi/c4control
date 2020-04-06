package de.untenrechts.c4control.bank;

import de.untenrechts.c4control.C4Control;
import de.untenrechts.c4control.entrepreneur.IEntrepreneur;
import de.untenrechts.c4control.proxies.GuiProxy;
import de.untenrechts.c4control.registration.BlockBase;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

public class BlockAtm extends BlockBase implements ITileEntityProvider {

    public BlockAtm(String name) {
        super(Material.IRON, name, CreativeTabs.MISC);
        setBlockUnbreakable();
        setLightLevel(6 / 16f);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote) {
            TileEntity requestedTileEntity = worldIn.getTileEntity(pos);
            if (requestedTileEntity instanceof AtmTileEntity) {
                AtmTileEntity atmTileEntity = (AtmTileEntity) requestedTileEntity;
                Optional<IEntrepreneur> entrepreneurOpt = IEntrepreneur.getAnyEntrepreneur(playerIn);

                entrepreneurOpt.ifPresent(atmTileEntity::setOccupier);
                playerIn.openGui(C4Control.MOD_ID, GuiProxy.ATM_GUI_ID, worldIn, pos.getX(), pos.getY(), pos.getZ());
                return true;
            }
            return false;
        }
        return true;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(@Nonnull World worldIn, int meta) {
        return new AtmTileEntity();
    }
}
