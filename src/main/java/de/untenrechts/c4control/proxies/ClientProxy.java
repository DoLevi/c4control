package de.untenrechts.c4control.proxies;


import de.untenrechts.c4control.entrepreneur.Entrepreneur;
import de.untenrechts.c4control.entrepreneur.EntrepreneurEventHandlerClient;
import de.untenrechts.c4control.entrepreneur.EntrepreneurStorage;
import de.untenrechts.c4control.entrepreneur.IEntrepreneur;
import de.untenrechts.c4control.registration.CapabilityRegistrationHandler;
import de.untenrechts.c4control.network.C4ControlPacketHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy implements IProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        CapabilityManager.INSTANCE.register(IEntrepreneur.class, new EntrepreneurStorage(), Entrepreneur::new);

        MinecraftForge.EVENT_BUS.register(new CapabilityRegistrationHandler());
        MinecraftForge.EVENT_BUS.register(new EntrepreneurEventHandlerClient());

        C4ControlPacketHandler.init();
    }

    @Override
    public void init(FMLInitializationEvent event) {

    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {

    }
}
