package de.untenrechts.c4control.proxies;

import de.untenrechts.c4control.entrepreneur.Entrepreneur;
import de.untenrechts.c4control.entrepreneur.EntrepreneurStorage;
import de.untenrechts.c4control.entrepreneur.IEntrepreneur;
import de.untenrechts.c4control.network.C4ControlPacketHandler;
import de.untenrechts.c4control.registration.CapabilityRegistrationHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public interface IProxy {

    void sidedPreInit(FMLPreInitializationEvent event);

    void sidedInit(FMLInitializationEvent event);

    void sidedPostInit(FMLPostInitializationEvent event);

    default void preInit(FMLPreInitializationEvent event) {
        CapabilityManager.INSTANCE.register(IEntrepreneur.class, new EntrepreneurStorage(), Entrepreneur::new);
        MinecraftForge.EVENT_BUS.register(new CapabilityRegistrationHandler());
        C4ControlPacketHandler.init();

        sidedPreInit(event);
    }

    default void init(FMLInitializationEvent event) {
        sidedInit(event);
    }

    default void postInit(FMLPostInitializationEvent event) {
        sidedPostInit(event);
    }

}
