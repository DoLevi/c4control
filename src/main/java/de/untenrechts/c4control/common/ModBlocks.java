package de.untenrechts.c4control.common;

import de.untenrechts.c4control.C4Control;
import de.untenrechts.c4control.block.BlockAtm;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlocks {

    public static final String BLOCK_ATM_NAME = "block_atm";
    @GameRegistry.ObjectHolder(C4Control.MOD_ID + ":" + BLOCK_ATM_NAME)
    public static BlockAtm blockAtm;

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        blockAtm.initModel();
    }
}
