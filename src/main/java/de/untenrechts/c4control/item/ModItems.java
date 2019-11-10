package de.untenrechts.c4control.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Arrays;
import java.util.List;

public class ModItems {

    public static List<ItemBase> items = Arrays.asList(
            new ItemBase("item_yen").setCreativeTab(CreativeTabs.MISC)
    );

    public static void register(IForgeRegistry<Item> registry) {
        registry.registerAll(items.toArray(new ItemBase[0]));
    }

    public static void registerModels() {
        items.forEach(ItemBase::registerItemModel);
    }
}
