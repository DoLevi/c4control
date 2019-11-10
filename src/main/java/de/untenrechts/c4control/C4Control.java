package de.untenrechts.c4control;

import de.untenrechts.c4control.proxy.CommonProxy;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = C4Control.MODID, name = C4Control.NAME, version = C4Control.VERSION)
public class C4Control
{
    public static final String MODID = "c4control";
    public static final String NAME = "C for Control";
    public static final String VERSION = "1.12.2-0.0.1";

    @Mod.Instance(MODID)
    public static C4Control instance;

    @SidedProxy(serverSide = "de.untenrechts.c4control.proxy.CommonProxy",
            clientSide = "de.untenrechts.c4control.proxy.ClientProxy")
    public static CommonProxy proxy;

    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code
        logger.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }
}
