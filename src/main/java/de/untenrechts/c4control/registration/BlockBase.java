package de.untenrechts.c4control.registration;

import de.untenrechts.c4control.C4Control;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public abstract class BlockBase extends Block {

    protected String name;

    public BlockBase(Material material, String name, CreativeTabs tab) {
        super(material);

        this.name = name;

        setUnlocalizedName(name);
        setRegistryName(C4Control.MOD_ID, name);
        setCreativeTab(tab);
    }
}
