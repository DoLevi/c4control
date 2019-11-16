package de.untenrechts.c4control.common;

import de.untenrechts.c4control.C4Control;
import de.untenrechts.c4control.item.ItemMidasNote;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems {

    public static final String ITEM_MIDAS_NOTE_NAME = "item_midas_note";
    @GameRegistry.ObjectHolder(C4Control.MOD_ID + ":" + ITEM_MIDAS_NOTE_NAME)
    public static ItemMidasNote itemMidasNote;

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        itemMidasNote.initModel();
    }
}
