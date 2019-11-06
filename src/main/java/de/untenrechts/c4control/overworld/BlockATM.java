package de.utnenrechts.c4control.overworld;


public class BlockATM extends Block {

	private static final String name = "atm";

	public BlockATM() {
		super(Material.rock);
		GameRegistry.registerBlock(this, name);
		setBlockName(name);
		setBlockTextureName(String.format("%s:%s", C4ControlMod.MODID, name));
		setCreativeTab(CreativeTabs.tabBlock);
	}

}
