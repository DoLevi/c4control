package de.untenrechts.c4control.common;

import de.untenrechts.c4control.proxy.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public abstract class BlockBase extends Block {

    public BlockBase(Material material, String name) {
        super(material);
        setRegistryName(CommonProxy.asRegistryName(name));
        setUnlocalizedName(CommonProxy.asUnlocalizedName(name));
    }

}
