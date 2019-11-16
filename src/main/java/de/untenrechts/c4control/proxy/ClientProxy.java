package de.untenrechts.c4control.proxy;

import de.untenrechts.c4control.C4Control;
import de.untenrechts.c4control.common.ModBlocks;
import de.untenrechts.c4control.common.ModItems;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
    }

    @Override
    public void registerItemRenderer(Item item, int meta, String id) {
        String location = String.format("%s:%s", C4Control.MOD_ID, id);
        ModelResourceLocation mrl = new ModelResourceLocation(location, "inventory");
        ModelLoader.setCustomModelResourceLocation(item, meta, mrl);
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent e) {
        ModBlocks.initModels();
        ModItems.initModels();
    }
}
