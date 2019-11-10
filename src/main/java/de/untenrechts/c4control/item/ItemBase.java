package de.untenrechts.c4control.item;

import de.untenrechts.c4control.C4Control;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import javax.annotation.ParametersAreNonnullByDefault;

public class ItemBase extends Item {

    protected final String name;

    public ItemBase(String name) {
        this.name = name;
        setUnlocalizedName(this.name);
        setRegistryName(this.name);
    }

    public void registerItemModel() {
        C4Control.proxy.registerItemRenderer(this, 0, name);
    }

    @Override
    @MethodsReturnNonnullByDefault
    @ParametersAreNonnullByDefault
    public ItemBase setCreativeTab(CreativeTabs tabs) {
        super.setCreativeTab(tabs);
        return this;
    }

}
