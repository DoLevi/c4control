package de.untenrechts.c4control.registration;

import de.untenrechts.c4control.C4Control;
import de.untenrechts.c4control.bank.AtmTileEntity;
import de.untenrechts.c4control.bank.BlockAtm;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;


@GameRegistry.ObjectHolder(C4Control.MOD_ID)
public class ModBlocks {

    public static final Block BLOCK_ATM = new BlockAtm("block_atm");

    @SuppressWarnings("unused")
    @Mod.EventBusSubscriber
    public static class RegistrationHandler {

        @SubscribeEvent
        public static void registerBlocks(RegistryEvent.Register<Block> event) {
            event.getRegistry().registerAll(
                    BLOCK_ATM
            );
            GameRegistry.registerTileEntity(AtmTileEntity.class, new ResourceLocation(C4Control.MOD_ID, "block_atm"));
        }
    }
}
