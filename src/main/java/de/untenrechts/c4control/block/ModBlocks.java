package de.untenrechts.c4control.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ModBlocks {

    public static List<BlockBase> blocks = Arrays.asList(
            new BlockATM("block_atm").setCreativeTab(CreativeTabs.MISC)
    );

    public static void register(IForgeRegistry<Block> registry) {
        registry.registerAll(blocks.toArray(new BlockBase[0]));
    }

    public static void registerItemBlocks(IForgeRegistry<Item> registry) {
        registry.registerAll(
                blocks.stream()
                        .map(BlockBase::createItemBlock)
                        .toArray(Item[]::new)
        );
        blocks.forEach(block -> registry.registerAll(block.createItemBlock()));
    }

    public static void registerModels() {
        blocks.stream()
                .map(BlockBase::createItemBlock)
                // Either fix this
                .forEach(BlockBase::registerItemModel);
    }

}
