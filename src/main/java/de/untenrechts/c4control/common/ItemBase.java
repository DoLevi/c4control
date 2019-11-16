package de.untenrechts.c4control.common;

import de.untenrechts.c4control.proxy.CommonProxy;
import net.minecraft.item.Item;

public abstract class ItemBase extends Item {

    public ItemBase(String name) {
        setRegistryName(CommonProxy.asRegistryName(name));
        setUnlocalizedName(CommonProxy.asUnlocalizedName(name));
    }

}
