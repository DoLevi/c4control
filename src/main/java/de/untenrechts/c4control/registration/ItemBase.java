package de.untenrechts.c4control.registration;

import de.untenrechts.c4control.C4Control;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item {

    protected String name;

    public ItemBase(String name, CreativeTabs tab) {
        this.name = name;

        setUnlocalizedName(name);
        setRegistryName(C4Control.MOD_ID, name);
        setCreativeTab(tab);
    }
}
