package de.untenrechts.c4control.eventhandlers;

import de.untenrechts.c4control.block.ModBlocks;
import de.untenrechts.c4control.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class RegistrationHandler {

    // Singleton
    private static final RegistrationHandler instance = new RegistrationHandler();
    private RegistrationHandler() {

    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        ModItems.register(event.getRegistry());
        ModBlocks.registerItemBlocks(event.getRegistry());
    }

    @SubscribeEvent
    public static void registerItems(ModelRegistryEvent event) {
        ModItems.registerModels();
        ModBlocks.registerModels();
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        ModBlocks.register(event.getRegistry());
    }

}
