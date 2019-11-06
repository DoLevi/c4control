package de.untenrechts.c4control.overworld;


import cpw.mods.fml.common.registry.GameRegistry;
import de.untenrechts.c4control.C4ControlMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

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
