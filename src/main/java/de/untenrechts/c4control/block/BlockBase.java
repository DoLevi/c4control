package de.untenrechts.c4control.block;

import de.untenrechts.c4control.C4Control;
import de.untenrechts.c4control.item.ItemBase;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;

import javax.annotation.ParametersAreNonnullByDefault;

public class BlockBase extends Block {

    protected final String name;

    public BlockBase(Material material, String name) {
        super(material);
        this.name = name;
        setUnlocalizedName(this.name);
        setRegistryName(this.name);
    }

    public static void registerItemModel(ItemBlock itemBlock) {
        ResourceLocation registryName = itemBlock.getRegistryName();
        if (registryName != null) {
            C4Control.proxy.registerItemRenderer(itemBlock, 0, registryName.toString());
        }
        String error = String.format("registryName for %s has been initialized poorly.",
                itemBlock);
        throw new IllegalStateException(error);
    }

    public Item createItemBlock() {
        ResourceLocation registryName = getRegistryName();
        if (registryName != null) {
            // Or fix this
            return new ItemBlock(this).setRegistryName(registryName);
        }
        String error = String.format("registryName for %s has been initialized poorly.", name);
        throw new IllegalStateException(error);
    }

    @Override
    @ParametersAreNonnullByDefault
    public BlockBase setCreativeTab(CreativeTabs tabs) {
        super.setCreativeTab(tabs);
        return this;
    }
}
