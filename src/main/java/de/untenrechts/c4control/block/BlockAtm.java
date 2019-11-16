package de.untenrechts.c4control.block;

import de.untenrechts.c4control.C4Control;
import de.untenrechts.c4control.common.BlockBase;
import de.untenrechts.c4control.common.IClientModel;
import de.untenrechts.c4control.common.ModBlocks;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Optional;

public class BlockAtm extends BlockBase implements ITileEntityProvider, IClientModel {

    public static final int GUI_ID = 1;
    private static final Material material = Material.IRON;
    private static final SoundType soundType = SoundType.METAL;
    private static final int lightLevel = 4;

    public BlockAtm() {
        super(material, ModBlocks.BLOCK_ATM_NAME);

        setBlockUnbreakable();
        setSoundType(soundType);
        setLightLevel(lightLevel / 16f);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ResourceLocation registryName = getRegistryName();
        if (registryName == null) {
            throw new IllegalStateException("registryName must not be null!");
        }
        ModelResourceLocation mrl = new ModelResourceLocation(registryName, "inventory");
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, mrl);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new BlockATMContainerTileEntity();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state,
                                    EntityPlayer playerIn, EnumHand hand, EnumFacing facing,
                                    float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote) {
            return true;
        }
        TileEntity te = worldIn.getTileEntity(pos);
        if (te instanceof BlockATMContainerTileEntity) {
            int x = pos.getX();
            int y = pos.getY();
            int z = pos.getZ();
            playerIn.openGui(C4Control.instance, GUI_ID, worldIn, x, y, z);
            return true;
        }
        return false;
    }
}
