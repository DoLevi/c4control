package de.untenrechts.c4control;


import de.untenrechts.c4control.init.ModBlocks;

@Mod(modid = C4ControlMod.MODID, name = C4ControlMod.NAME, version = C4ControlMod.VERSION)
public class C4ControlMod {
	
	public static final String NAME = "C 4 Control";
	public static final String MODID = "c4control";
	public static final String VERSION = "1.7.10-0.0.1";

	@Mod.Instance("c4control")
	public static C4ControlMod instance;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ModBlocks.init();
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {}

}
