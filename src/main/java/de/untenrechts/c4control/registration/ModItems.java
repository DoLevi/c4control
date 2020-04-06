package de.untenrechts.c4control.registration;

import de.untenrechts.c4control.C4Control;
import de.untenrechts.c4control.battle.ItemDirect;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;


@GameRegistry.ObjectHolder(C4Control.MOD_ID)
public class ModItems {

    public static final Item ITEM_DIRECT = new ItemDirect("item_direct");

    // ItemBlocks
    public static final Item ITEMBLOCK_BLOCK_ATM = new ItemBlock(ModBlocks.BLOCK_ATM)
            .setRegistryName(ModBlocks.BLOCK_ATM.getRegistryName());


    @Mod.EventBusSubscriber
    public static class RegistrationHandler {

        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> event) {
            event.getRegistry().registerAll(
                    ITEM_DIRECT,
                    ITEMBLOCK_BLOCK_ATM
            );
        }
    }
}
