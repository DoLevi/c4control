package de.untenrechts.c4control.proxies;


import de.untenrechts.c4control.entrepreneur.EntrepreneurEventHandlerClient;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy implements IProxy {

    @Override
    public void sidedPreInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new EntrepreneurEventHandlerClient());
    }

    @Override
    public void sidedInit(FMLInitializationEvent event) {

    }

    @Override
    public void sidedPostInit(FMLPostInitializationEvent event) {

    }
}
