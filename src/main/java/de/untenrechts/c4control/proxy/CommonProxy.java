package de.untenrechts.c4control.proxy;

import de.untenrechts.c4control.C4Control;
import de.untenrechts.c4control.block.BlockAtm;
import de.untenrechts.c4control.common.BlockBase;
import de.untenrechts.c4control.common.ModBlocks;
import de.untenrechts.c4control.common.ModItems;
import de.untenrechts.c4control.item.ItemMidasNote;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod.EventBusSubscriber
public class CommonProxy {

    public void preInit(FMLPreInitializationEvent e) {

    }

    public void init(FMLInitializationEvent e) {
        NetworkRegistry.INSTANCE.registerGuiHandler(C4Control.instance, new GuiProxy());
    }

    public void postInit(FMLPostInitializationEvent e) {

    }

    public void registerItemRenderer(Item item, int meta, String id) {

    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> e) {
        e.getRegistry().registerAll(
                new BlockAtm().setCreativeTab(CreativeTabs.MISC)
        );
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> e) {
        e.getRegistry().registerAll(
                // Items
                new ItemMidasNote().setCreativeTab(CreativeTabs.MISC),
                // ItemBlocks
                asItemBlock(ModBlocks.blockAtm).setCreativeTab(CreativeTabs.MISC)
        );
    }

    private static Item asItemBlock(BlockBase block) {
        return new ItemBlock(block)
                .setRegistryName(asRegistryName("item_" + block.getUnlocalizedName()));
    }

    public static String asRegistryName(String name) {
        return name;
    }

    public static String asUnlocalizedName(String name) {
        return String.format("%s.%s", C4Control.MOD_ID, name);
    }
}
