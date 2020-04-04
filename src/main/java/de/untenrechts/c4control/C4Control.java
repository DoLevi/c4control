package de.untenrechts.c4control;

import de.untenrechts.c4control.proxies.IProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;


@Mod(modid = C4Control.MOD_ID, name = C4Control.MOD_NAME, version = C4Control.MOD_VERSION, useMetadata = true)
public class C4Control {

    public static final String MOD_ID = "c4control";
    public static final String MOD_NAME = "C4Control";
    public static final String MOD_VERSION = "0.2.0";

    @SidedProxy(serverSide = "de.untenrechts.c4control.proxies.ServerProxy", clientSide = "de.untenrechts.c4control.proxies.ClientProxy")
    public static IProxy proxy;

    public static Logger log;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        log = event.getModLog();
        log.info("{} has hit phase: {}!", MOD_NAME, event.getModState().toString());
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        log.info("{} has hit phase: {}!", MOD_NAME, event.getModState().toString());
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        log.info("{} has hit phase: {}!", MOD_NAME, event.getModState().toString());
        proxy.postInit(event);
    }

}
